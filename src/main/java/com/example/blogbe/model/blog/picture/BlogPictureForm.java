package com.example.blogbe.model.blog.picture;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BlogPictureForm {
    private Long id;
    private MultipartFile image;
    private String title;

    public BlogPictureForm(MultipartFile image, String title) {
        this.image = image;
        this.title = title;
    }

    public BlogPictureForm() {
    }

    public BlogPictureForm(Long id, MultipartFile image, String title) {
        this.id = id;
        this.image = image;
        this.title = title;
    }
}
