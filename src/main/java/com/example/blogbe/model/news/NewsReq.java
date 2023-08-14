package com.example.blogbe.model.news;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class NewsReq {
    private Long id;

    private MultipartFile cover;

    private String title;

    private String content;

    private String description;

    private int category;
    public NewsReq() {
    }

}
