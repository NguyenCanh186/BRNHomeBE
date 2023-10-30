package com.example.blogbe.controller;

import com.example.blogbe.model.Mailer;
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
    public ResponseEntity<?> getNewsCustomer() {
        return ResponseEntity.ok(newsService.findAll());
    }
    @GetMapping("/newsCustomer")
    public ResponseEntity<?> getNews(@RequestParam Long page, @RequestParam Long size, @RequestParam(required = false) String title) {
        return ResponseEntity.ok(newsService.findAllByTitleContaining(title, page - 1, size, "DESC"));
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<Optional<News>> getNewsById(@PathVariable Long id) {
        Optional<News> news = newsService.findById(id);
        if (!news.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(news);
    }
    @PostMapping("/sendMail")
    public ResponseEntity<?> sendMail(@RequestBody Mailer mailer) {
        SendEmail.send(mailer);
        return ResponseEntity.ok("Xong");
    }
}
