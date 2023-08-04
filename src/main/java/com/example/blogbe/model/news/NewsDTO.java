package com.example.blogbe.model.news;

import com.example.blogbe.model.news.picture.NewsPicture;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class NewsDTO {
    private Long id;

    private String cover;

    private String title;

    private List<NewsPicture> newsPictures;

    private Date date;

    private int category;

    public NewsDTO() {
    }
}
