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
@RequestMapping("/news")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @Autowired
    private NewsPictureService newsPictureService;

    @Value("${upload.path}")
    private String fileUpload;

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute NewsReq newsReq) throws IOException {
        News newsCheck = newsService.findByName(newsReq.getTitle());
        if (newsCheck == null) {
            News news = new News();
            MultipartFile multipartFileCover = newsReq.getCover();
            String fileCover = multipartFileCover.getOriginalFilename();
            try {
                FileCopyUtils.copy(newsReq.getCover().getBytes(), new File(this.fileUpload + fileCover));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            news.setCover(fileCover);
            news.setTitle(newsReq.getTitle());
            news.setDate(new Date());
            news.setCategory(newsReq.getCategory());
            News newsCallBack = newsService.save(news);
            checkNull(newsReq, newsCallBack);
        } else {
            checkNull(newsReq, newsCheck);
        }
        return ResponseEntity.ok("Xong");
    }

    private void checkNull(@ModelAttribute NewsReq newsReq, News newsCallBack) {
        if (newsReq.getImage() != null) {
            NewsPicture newsPicture = new NewsPicture();
            MultipartFile multipartFile = newsReq.getImage();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(newsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newsPicture.setTitle(newsReq.getTitleImage());
            newsPicture.setImage(fileName);
            newsPicture.setNews(newsCallBack);
            newsPictureService.save(newsPicture);
        } else {
            NewsPicture newsPicture = new NewsPicture();
            newsPicture.setTitle(newsReq.getTitleImage());
            newsPicture.setNews(newsCallBack);
            newsPictureService.save(newsPicture);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable Long id) {
        List<NewsPicture> newsPictures = newsPictureService.findAllByNewsId(id);
        for (int i = 0; i < newsPictures.size(); i++) {
            newsPictureService.remove(newsPictures.get(i).getId());
        }
        newsService.remove(id);
        return ResponseEntity.ok("Đã xóa");
    }
    
    @PostMapping("/update")
    private ResponseEntity<?> update(@ModelAttribute NewsReq NewsReq) throws IOException {
        if (NewsReq.getListIdPicture() != null) {
            String[] listIdPicture = NewsReq.getListIdPicture().substring(0, NewsReq.getListIdPicture().length() - 1).split(",");
            Long[] numbers = new Long[listIdPicture.length];
            for (int i = 0; i < listIdPicture.length; i++) {
                numbers[i] = Long.parseLong(listIdPicture[i]);
            }
            for (Long id : numbers) {
                newsPictureService.remove(id);
            }
        }
        Optional<News> news = newsService.findById(NewsReq.getId());
        if (NewsReq.getCover() != null) {
            MultipartFile multipartFile = NewsReq.getCover();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(NewsReq.getCover().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            news.get().setCover(fileName);
            news.get().setTitle(NewsReq.getTitle());
            news.get().setCategory(NewsReq.getCategory());
            newsService.save(news.get());
        } else {
            news.get().setCategory(NewsReq.getCategory());
            news.get().setTitle(NewsReq.getTitle());
            newsService.save(news.get());
        }
        if (NewsReq.getPictureId() != null) {
            Optional<NewsPicture> storyPicture1 = newsPictureService.findById(NewsReq.getPictureId());
            if (NewsReq.getImage() != null) {
                MultipartFile multipartFile = NewsReq.getImage();
                String fileName = multipartFile.getOriginalFilename();
                try {
                    FileCopyUtils.copy(NewsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                storyPicture1.get().setTitle(NewsReq.getTitleImage());
                storyPicture1.get().setImage(fileName);
                storyPicture1.get().setNews(news.get());
                newsPictureService.save(storyPicture1.get());
            } else {
                if (NewsReq.getIsDeletePicture() != null) {
                    storyPicture1.get().setImage(null);
                }
                storyPicture1.get().setTitle(NewsReq.getTitleImage());
                storyPicture1.get().setNews(news.get());
                newsPictureService.save(storyPicture1.get());
            }
        } else {
            NewsPicture newsPicture = new NewsPicture();
            if (NewsReq.getImage() != null) {
                MultipartFile multipartFile = NewsReq.getImage();
                String fileName = multipartFile.getOriginalFilename();
                try {
                    FileCopyUtils.copy(NewsReq.getImage().getBytes(), new File(this.fileUpload + fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                newsPicture.setTitle(NewsReq.getTitleImage());
                newsPicture.setImage(fileName);
                newsPicture.setNews(news.get());
                newsPictureService.save(newsPicture);
            } else {
                newsPicture.setTitle(NewsReq.getTitleImage());
                newsPicture.setNews(news.get());
                newsPictureService.save(newsPicture);
            }
        }
        return null;
    }


}
