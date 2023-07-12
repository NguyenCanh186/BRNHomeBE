package com.example.blogbe.controller;

import com.example.blogbe.model.home.Home;
import com.example.blogbe.service.home.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/get-data")
@CrossOrigin("*")
public class GetDataController {
    @Autowired
    private IHomeService homeService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        List<Home> list = homeService.findAll();
        Home home = list.get(0);
        return ResponseEntity.ok(home);
    }
}
