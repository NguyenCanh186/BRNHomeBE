package com.example.blogbe.controller;

import com.example.blogbe.model.home.Home;
import com.example.blogbe.model.home.HomeForm;
import com.example.blogbe.service.home.IHomeService;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/home")
@CrossOrigin("*")
@Controller
public class HomeController {
    @Autowired
    private IHomeService homeService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping
    private ResponseEntity<?> creditHome(@ModelAttribute HomeForm homeForm) {
        Home home = new Home(homeForm.getTitle(), homeForm.getDescription());
        MultipartFile multipartFile = homeForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(homeForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        home.setImage(fileName);
        homeService.save(home);
        return ResponseEntity.ok(home);
    }

    @PostMapping("/update")
    private ResponseEntity<?> updateHome(@ModelAttribute HomeForm homeForm) {
        Home home = homeService.findAll().get(0);
        MultipartFile multipartFile = homeForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        try {
            FileCopyUtils.copy(homeForm.getImage().getBytes(), new File(this.fileUpload + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        home.setImage(fileName);
        home.setTitle(homeForm.getTitle());
        home.setDescription(homeForm.getDescription());
        homeService.save(home);
        return ResponseEntity.ok(home);
    }

}
