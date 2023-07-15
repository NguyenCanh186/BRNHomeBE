package com.example.blogbe.controller;

import com.example.blogbe.model.story.Story;
import com.example.blogbe.model.story.StoryListWrapper;
import com.example.blogbe.model.story.StoryReq;
import com.example.blogbe.model.story.StotyDTO;
import com.example.blogbe.model.story.picture.StoryPicture;
import com.example.blogbe.model.story.picture.StoryPictureForm;
import com.example.blogbe.service.storyService.StoryPictureService;
import com.example.blogbe.service.storyService.StoryService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
    private StoryService storyService;

    @Autowired
    private StoryPictureService storyPictureService;

    @Value("${upload.path}")
    private String fileUpload;

    @GetMapping
    public ResponseEntity<List<StotyDTO>> getStory() {
        List<StoryPicture> storyPictureList = storyPictureService.findAll();
        List<Story> storyList = storyService.findAll();
        List<StotyDTO> stotyDTOS = new ArrayList<>();
        for (int i = 0; i < storyList.size(); i++) {
            StotyDTO stotyDTO = new StotyDTO();
            stotyDTO.setId(storyList.get(i).getId());
            stotyDTO.setName(storyList.get(i).getName());
            stotyDTO.setTitle(storyList.get(i).getTitle());
            stotyDTOS.add(stotyDTO);
            for (StoryPicture storyPicture : storyPictureList) {
                if (storyPicture.getStory().getId() == stotyDTOS.get(i).getId()) {
                    if (stotyDTOS.get(i).getStoryPictures() == null) {
                        List<StoryPicture> list = new ArrayList<>();
                        stotyDTOS.get(i).setStoryPictures(list);
                        stotyDTOS.get(i).getStoryPictures().add(storyPicture);
                    } else {
                        stotyDTOS.get(i).getStoryPictures().add(storyPicture);
                    }
                }
            }
        }
        return ResponseEntity.ok(stotyDTOS);
    }

    @PostMapping
    public ResponseEntity<?> create(@ModelAttribute StoryReq storyReq) throws IOException {
        Story storyCheck = storyService.findByName(storyReq.getName());
        if (storyCheck != null) {
            StoryPicture storyPicture = new StoryPicture();
            MultipartFile multipartFile = storyReq.getImage();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(storyReq.getImage().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
            MultipartFile multipartFile = storyReq.getImage();
            String fileName = multipartFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(storyReq.getImage().getBytes(), new File(this.fileUpload + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            storyPicture.setTitle(storyReq.getTitleImage());
            storyPicture.setImage(fileName);
            storyPicture.setStory(storyCallBack);
            storyPictureService.save(storyPicture);
        }
        return ResponseEntity.ok("Xong");
    }
}
