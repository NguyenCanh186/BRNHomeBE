package com.example.blogbe.controller;

import com.example.blogbe.config.custom.S3Util;
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

    @PostMapping
    private ResponseEntity<?> creditHome(@ModelAttribute HomeForm homeForm) throws IOException {
        Home home = new Home(homeForm.getTitle(), homeForm.getDescription());
        String fileName = homeForm.getImage().getOriginalFilename();
        S3Util.uploadFile(fileName, homeForm.getImage().getInputStream());
        home.setImage(fileName);
        homeService.save(home);
        return ResponseEntity.ok(home);
    }

    @PostMapping("/update")
    private ResponseEntity<?> updateHome(@ModelAttribute HomeForm homeForm) throws IOException {
        Home home = homeService.findAll().get(0);
        MultipartFile multipartFile = homeForm.getImage();
        if (multipartFile != null) {
            String fileName = homeForm.getImage().getOriginalFilename();
            S3Util.uploadFile(fileName, homeForm.getImage().getInputStream());
            home.setImage(fileName);
            home.setTitle(homeForm.getTitle());
            home.setDescription(homeForm.getDescription());
            homeService.save(home);
        } else {
            home.setTitle(homeForm.getTitle());
            home.setDescription(homeForm.getDescription());
            homeService.save(home);
        }
        return ResponseEntity.ok(home);
    }

}
