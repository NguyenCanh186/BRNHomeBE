package com.example.blogbe.model.story;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class StoryReq {
    private Long id;

    private String name;

    private String title;

    private MultipartFile image;
    private String titleImage;

    public StoryReq() {
    }

    public StoryReq(String name, String title, MultipartFile image, String titleImage) {
        this.name = name;
        this.title = title;
        this.image = image;
        this.titleImage = titleImage;
    }
}
