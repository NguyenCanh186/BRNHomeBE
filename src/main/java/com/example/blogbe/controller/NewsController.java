package com.example.blogbe.controller;

import com.example.blogbe.model.news.News;
import com.example.blogbe.model.news.NewsReq;
import com.example.blogbe.service.newsService.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute NewsReq newsReq) throws IOException {
        News news = new News();
        if (newsReq.getCover() != null) {
            MultipartFile multipartFile = newsReq.getCover();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(newsReq.getCover().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            news.setCover(fileName);
        }
        news.setTitle(newsReq.getTitle());
        news.setContent(newsReq.getContent());
        news.setDescription(newsReq.getDescription());
        news.setDate(new Date());
        news.setCategory(newsReq.getCategory());
        newsService.save(news);
        return ResponseEntity.ok("Xong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable Long id) {
        newsService.remove(id);
        return ResponseEntity.ok("Đã xóa");
    }
    
    @PostMapping("/update")
    private ResponseEntity<?> update(@ModelAttribute NewsReq NewsReq) throws IOException {
        News news = newsService.findById(NewsReq.getId()).get();
        if (NewsReq.getCover() != null) {
            MultipartFile multipartFile = NewsReq.getCover();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(NewsReq.getCover().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            news.setCover(fileName);
        }
        news.setTitle(NewsReq.getTitle());
        news.setContent(NewsReq.getContent());
        news.setDescription(NewsReq.getDescription());
        news.setCategory(NewsReq.getCategory());
        newsService.save(news);
        return ResponseEntity.ok("Xong");
    }


}
