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
    private NewsService storyService;

    @Autowired
    private NewsPictureService storyPictureService;

//    @GetMapping("/story")
//    public ResponseEntity<List<NewsDTO>> getStory() {
//        List<NewsPicture> newsPictureList = storyPictureService.findAll();
//        List<News> newsList = storyService.findAll();
//        List<NewsDTO> newsDTOS = new ArrayList<>();
//        for (int i = 0; i < newsList.size(); i++) {
//            NewsDTO newsDTO = new NewsDTO();
//            newsDTO.setId(newsList.get(i).getId());
//            newsDTO.setName(newsList.get(i).getName());
//            newsDTO.setTitle(newsList.get(i).getTitle());
//            newsDTOS.add(newsDTO);
//            for (NewsPicture newsPicture : newsPictureList) {
//                if (newsPicture.getNews().getId() == newsDTOS.get(i).getId()) {
//                    if (newsDTOS.get(i).getNewsPictures() == null) {
//                        List<NewsPicture> list = new ArrayList<>();
//                        newsDTOS.get(i).setNewsPictures(list);
//                        newsDTOS.get(i).getNewsPictures().add(newsPicture);
//                    } else {
//                        newsDTOS.get(i).getNewsPictures().add(newsPicture);
//                    }
//                }
//            }
//        }
//        return ResponseEntity.ok(newsDTOS);
//    }

//    @GetMapping("/story/{id}")
//    public ResponseEntity<NewsDTO> getStoryById(@PathVariable Long id) {
//        Optional<News> story = storyService.findById(id);
//        if (!story.isPresent()) {
//            return ResponseEntity.notFound().build();
//        }
//        NewsDTO newsDTO = new NewsDTO();
//        newsDTO.setId(id);
//        newsDTO.setName(story.get().getName());
//        newsDTO.setTitle(story.get().getTitle());
//        List<NewsPicture> newsPictureList = storyPictureService.findAllByStoryId(id);
//        newsDTO.setNewsPictures(newsPictureList);
//        return ResponseEntity.ok(newsDTO);
//    }

}
