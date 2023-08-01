package com.example.blogbe.controller;

import com.example.blogbe.model.news.News;
import com.example.blogbe.model.news.NewsReq;
import com.example.blogbe.model.news.picture.NewsPicture;
import com.example.blogbe.service.newsService.NewsPictureService;
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
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private NewsService storyService;

    @Autowired
    private NewsPictureService storyPictureService;

    @Value("${upload.path}")
    private String fileUpload;
    
//    @PostMapping("/update")
//    private ResponseEntity<?> update(@ModelAttribute NewsReq newsReq) throws IOException {
//        if (newsReq.getListIdPicture() != null) {
//            String[] listIdPicture = newsReq.getListIdPicture().substring(0, newsReq.getListIdPicture().length() - 1).split(",");
//            Long[] numbers = new Long[listIdPicture.length];
//            for (int i = 0; i < listIdPicture.length; i++) {
//                numbers[i] = Long.parseLong(listIdPicture[i]);
//            }
//            for (Long id : numbers) {
//                storyPictureService.remove(id);
//            }
//        }
//        Optional<News> story = storyService.findById(newsReq.getId());
//        story.get().setName(newsReq.getName());
//        story.get().setTitle(newsReq.getTitle());
//        storyService.save(story.get());
//        NewsPicture newsPicture = new NewsPicture();
//        if (newsReq.getPictureId() != null) {
//            Optional<NewsPicture> storyPicture1 = storyPictureService.findById(newsReq.getPictureId());
//            if (newsReq.getImage() != null) {
//                MultipartFile multipartFile = newsReq.getImage();
//                String fileName = multipartFile.getOriginalFilename();
//                try {
//                    FileCopyUtils.copy(newsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//                storyPicture1.get().setTitle(newsReq.getTitleImage());
//                storyPicture1.get().setImage(fileName);
//                storyPicture1.get().setNews(story.get());
//                storyPictureService.save(storyPicture1.get());
//            } else {
//                storyPicture1.get().setTitle(newsReq.getTitleImage());
//                storyPicture1.get().setNews(story.get());
//                storyPictureService.save(storyPicture1.get());
//            }
//        } else {
//            MultipartFile multipartFile = newsReq.getImage();
//            String fileName = multipartFile.getOriginalFilename();
//            try {
//                FileCopyUtils.copy(newsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            newsPicture.setTitle(newsReq.getTitleImage());
//            newsPicture.setImage(fileName);
//            newsPicture.setNews(story.get());
//            storyPictureService.save(newsPicture);
//        }
//        return null;
//    }
//
//    @PostMapping
//    public ResponseEntity<?> create(@ModelAttribute NewsReq newsReq) throws IOException {
//        News newsCheck = storyService.findByName(newsReq.getName());
//        if (newsCheck != null) {
//            NewsPicture newsPicture = new NewsPicture();
//            MultipartFile multipartFile = newsReq.getImage();
//            String fileName = multipartFile.getOriginalFilename();
//            try {
//                FileCopyUtils.copy(newsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            newsPicture.setTitle(newsReq.getTitleImage());
//            newsPicture.setImage(fileName);
//            newsPicture.setNews(newsCheck);
//            storyPictureService.save(newsPicture);
//        } else {
//            News news = new News();
//            news.setName(newsReq.getName());
//            news.setTitle(newsReq.getTitle());
//            storyService.save(news);
//            News newsCallBack = storyService.findByName(newsReq.getName());
//            NewsPicture newsPicture = new NewsPicture();
//            MultipartFile multipartFile = newsReq.getImage();
//            String fileName = multipartFile.getOriginalFilename();
//            try {
//                FileCopyUtils.copy(newsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            newsPicture.setTitle(newsReq.getTitleImage());
//            newsPicture.setImage(fileName);
//            newsPicture.setNews(newsCallBack);
//            storyPictureService.save(newsPicture);
//        }
//        return ResponseEntity.ok("Xong");
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable Long id) {
        List<NewsPicture> newsPictures = storyPictureService.findAllByStoryId(id);
        for (int i = 0; i < newsPictures.size(); i++) {
            storyPictureService.remove(newsPictures.get(i).getId());
        }
        storyService.remove(id);
        return ResponseEntity.ok("Đã xóa");
    }
}
