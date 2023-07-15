package com.example.blogbe.model.story.picture;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoryPictureForm {
    private Long id;
    private MultipartFile image;
    private String title;

    public StoryPictureForm(MultipartFile image, String title) {
        this.image = image;
        this.title = title;
    }

    public StoryPictureForm() {
    }

    public StoryPictureForm(Long id, MultipartFile image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }
}
