package com.example.blogbe.model.blog;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class BlogForm {
    private Long id;
    private MultipartFile fileImage;
    private String title;
    private String content;
}
