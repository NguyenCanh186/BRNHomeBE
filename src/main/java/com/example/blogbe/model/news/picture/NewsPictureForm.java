package com.example.blogbe.model.news.picture;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewsPictureForm {
    private Long id;
    private MultipartFile image;
    private String title;

    public NewsPictureForm(MultipartFile image, String title) {
        this.image = image;
        this.title = title;
    }

    public NewsPictureForm() {
    }

    public NewsPictureForm(Long id, MultipartFile image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }
}
