package com.example.blogbe.model.news;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class NewsReq {
    private Long id;

    private MultipartFile cover;

    private String title;

    private Long pictureId;

    private MultipartFile image;
    private String titleImage;

    private String listIdPicture;

    public NewsReq() {
    }

}
