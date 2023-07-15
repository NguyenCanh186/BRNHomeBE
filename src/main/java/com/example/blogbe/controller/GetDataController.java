package com.example.blogbe.controller;

import com.example.blogbe.model.home.Home;
import com.example.blogbe.model.story.Story;
import com.example.blogbe.model.story.StotyDTO;
import com.example.blogbe.model.story.picture.StoryPicture;
import com.example.blogbe.service.home.IHomeService;
import com.example.blogbe.service.storyService.StoryPictureService;
import com.example.blogbe.service.storyService.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/get-data")
@CrossOrigin("*")
public class GetDataController {
    @Autowired
    private IHomeService homeService;

    @Autowired
    private StoryService storyService;

    @Autowired
    private StoryPictureService storyPictureService;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile() {
        List<Home> list = homeService.findAll();
        Home home = list.get(0);
        return ResponseEntity.ok(home);
    }

    @GetMapping("/story")
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
}
