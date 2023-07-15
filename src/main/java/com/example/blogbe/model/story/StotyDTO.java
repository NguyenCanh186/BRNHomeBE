package com.example.blogbe.model.story;

import com.example.blogbe.model.story.picture.StoryPicture;
import lombok.Data;

import java.util.List;
@Data
public class StotyDTO {
    private Long id;

    private String name;

    private String title;

    private List<StoryPicture> storyPictures;

    public StotyDTO() {
    }

    public StotyDTO(Long id, String name, String title, List<StoryPicture> storyPictures) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.storyPictures = storyPictures;
    }

    public StotyDTO(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}
