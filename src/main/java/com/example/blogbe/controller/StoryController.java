package com.example.blogbe.controller;

import com.example.blogbe.config.custom.S3Util;
import com.example.blogbe.model.story.Story;
import com.example.blogbe.model.story.StoryReq;
import com.example.blogbe.model.story.picture.StoryPicture;
import com.example.blogbe.service.storyService.StoryPictureService;
import com.example.blogbe.service.storyService.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/story")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @Autowired
    private StoryPictureService storyPictureService;

    @Value("${upload.path}")
    private String fileUpload;
    
    @PostMapping("/update")
    private ResponseEntity<?> update(@ModelAttribute StoryReq storyReq) throws IOException {
        if (storyReq.getListIdPicture() != null) {
            String[] listIdPicture = storyReq.getListIdPicture().substring(0, storyReq.getListIdPicture().length() - 1).split(",");
            Long[] numbers = new Long[listIdPicture.length];
            for (int i = 0; i < listIdPicture.length; i++) {
                numbers[i] = Long.parseLong(listIdPicture[i]);
            }
            for (Long id : numbers) {
                storyPictureService.remove(id);
            }
        }
        Optional<Story> story = storyService.findById(storyReq.getId());
        story.get().setName(storyReq.getName());
        story.get().setTitle(storyReq.getTitle());
        storyService.save(story.get());
        StoryPicture storyPicture = new StoryPicture();
        if (storyReq.getPictureId() != null) {
            Optional<StoryPicture> storyPicture1 = storyPictureService.findById(storyReq.getPictureId());
            if (storyReq.getImage() != null) {
                MultipartFile multipartFile = storyReq.getImage();
                String fileName = multipartFile.getOriginalFilename();
                try {
                    FileCopyUtils.copy(storyReq.getImage().getBytes(), new File(this.fileUpload + fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                storyPicture1.get().setTitle(storyReq.getTitleImage());
                storyPicture1.get().setImage(fileName);
                storyPicture1.get().setStory(story.get());
                storyPictureService.save(storyPicture1.get());
            } else {
                storyPicture1.get().setTitle(storyReq.getTitleImage());
                storyPicture1.get().setStory(story.get());
                storyPictureService.save(storyPicture1.get());
            }
        } else {
            MultipartFile multipartFile = storyReq.getImage();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(storyReq.getImage().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            storyPicture.setTitle(storyReq.getTitleImage());
            storyPicture.setImage(fileName);
            storyPicture.setStory(story.get());
            storyPictureService.save(storyPicture);
        }
        return null;
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute StoryReq storyReq) throws IOException {
        Story storyCheck = storyService.findByName(storyReq.getName());
        if (storyCheck != null) {
            StoryPicture storyPicture = new StoryPicture();
            String fileName = storyReq.getImage().getOriginalFilename();
            S3Util.uploadFile(fileName, storyReq.getImage().getInputStream());
            storyPicture.setTitle(storyReq.getTitleImage());
            storyPicture.setImage(fileName);
            storyPicture.setStory(storyCheck);
            storyPictureService.save(storyPicture);
        } else {
            Story story = new Story();
            story.setName(storyReq.getName());
            story.setTitle(storyReq.getTitle());
            storyService.save(story);
            Story storyCallBack = storyService.findByName(storyReq.getName());
            StoryPicture storyPicture = new StoryPicture();
            String fileName = storyReq.getImage().getOriginalFilename();
            S3Util.uploadFile(fileName, storyReq.getImage().getInputStream());
            storyPicture.setTitle(storyReq.getTitleImage());
            storyPicture.setImage(fileName);
            storyPicture.setStory(storyCallBack);
            storyPictureService.save(storyPicture);
        }
        return ResponseEntity.ok("Xong");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable Long id) {
        List<StoryPicture> storyPictures = storyPictureService.findAllByStoryId(id);
        for (int i = 0; i < storyPictures.size(); i++) {
            storyPictureService.remove(storyPictures.get(i).getId());
        }
        storyService.remove(id);
        return ResponseEntity.ok("Đã xóa");
    }
}
