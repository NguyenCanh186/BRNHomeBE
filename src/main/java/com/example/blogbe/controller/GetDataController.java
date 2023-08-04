package com.example.blogbe.controller;

import com.example.blogbe.model.news.News;
import com.example.blogbe.model.news.NewsDTO;
import com.example.blogbe.model.news.picture.NewsPicture;
import com.example.blogbe.service.newsService.NewsPictureService;
import com.example.blogbe.service.newsService.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/get-data")
@CrossOrigin("*")
public class GetDataController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsPictureService newsPictureService;

    @GetMapping("/news")
    public ResponseEntity<List<NewsDTO>> getNews() {
        List<NewsPicture> newsPictureList = newsPictureService.findAll();
        List<News> newsList = newsService.findAll();
        List<NewsDTO> newsDTOS = new ArrayList<>();
        for (int i = 0; i < newsList.size(); i++) {
            NewsDTO newsDTO = new NewsDTO();
            newsDTO.setId(newsList.get(i).getId());
            newsDTO.setCover(newsList.get(i).getCover());
            newsDTO.setTitle(newsList.get(i).getTitle());
            newsDTO.setDate(newsList.get(i).getDate());
            newsDTO.setCategory(newsList.get(i).getCategory());
            newsDTOS.add(newsDTO);
            for (NewsPicture newsPicture : newsPictureList) {
                if (newsPicture.getNews().getId() == newsDTOS.get(i).getId()) {
                    if (newsDTOS.get(i).getNewsPictures() == null) {
                        List<NewsPicture> list = new ArrayList<>();
                        newsDTOS.get(i).setNewsPictures(list);
                        newsDTOS.get(i).getNewsPictures().add(newsPicture);
                    } else {
                        newsDTOS.get(i).getNewsPictures().add(newsPicture);
                    }
                }
            }
        }
        return ResponseEntity.ok(newsDTOS);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<NewsDTO> getNewsById(@PathVariable Long id) {
        Optional<News> news = newsService.findById(id);
        if (!news.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(id);
        newsDTO.setCover(news.get().getCover());
        newsDTO.setTitle(news.get().getTitle());
        newsDTO.setDate(news.get().getDate());
        newsDTO.setCategory(news.get().getCategory());
        List<NewsPicture> newsPictureList = newsPictureService.findAllByNewsId(id);
        newsDTO.setNewsPictures(newsPictureList);
        return ResponseEntity.ok(newsDTO);
    }
}
