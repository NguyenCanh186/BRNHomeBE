package com.example.blogbe.model.home;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class HomeForm {
    private Long id;
    private MultipartFile image;
    private String title;
    private String description;

    public HomeForm(Long id, MultipartFile image, String title, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public HomeForm(MultipartFile image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public HomeForm() {
    }
}
