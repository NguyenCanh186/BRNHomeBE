package com.example.blogbe.controller;

import com.example.blogbe.model.news.News;
import com.example.blogbe.service.newsService.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/get-data")
@CrossOrigin("*")
public class GetDataController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<List<News>> getNews() {
        List<News> newsList = newsService.findAll();
        return ResponseEntity.ok(newsList);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<Optional<News>> getNewsById(@PathVariable Long id) {
        Optional<News> news = newsService.findById(id);
        if (!news.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news);
    }
}
