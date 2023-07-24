package com.example.blogbe.controller;

import com.example.blogbe.model.home.Home;
import com.example.blogbe.model.story.Story;
import com.example.blogbe.model.story.StotyDTO;
import com.example.blogbe.model.story.picture.StoryPicture;
import com.example.blogbe.service.blogService.IBlogService;
import com.example.blogbe.service.home.IHomeService;
import com.example.blogbe.service.storyService.StoryPictureService;
import com.example.blogbe.service.storyService.StoryService;
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
    private IHomeService homeService;

    @Autowired
    private StoryService storyService;

    @Autowired
    private IBlogService blogService;

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

    @GetMapping("/story/{id}")
    public ResponseEntity<StotyDTO> getStoryById(@PathVariable Long id) {
        Optional<Story> story = storyService.findById(id);
        if (!story.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        StotyDTO stotyDTO = new StotyDTO();
        stotyDTO.setId(id);
        stotyDTO.setName(story.get().getName());
        stotyDTO.setTitle(story.get().getTitle());
        List<StoryPicture> storyPictureList = storyPictureService.findAllByStoryId(id);
        stotyDTO.setStoryPictures(storyPictureList);
        return ResponseEntity.ok(stotyDTO);
    }

    @GetMapping("/getBlog")
    public ResponseEntity<?> getBlog(){
        return ResponseEntity.ok(blogService.findAll());
    }

    @GetMapping("/getBlog/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable Long id){
        return ResponseEntity.ok(blogService.findById(id).get());
    }
}
