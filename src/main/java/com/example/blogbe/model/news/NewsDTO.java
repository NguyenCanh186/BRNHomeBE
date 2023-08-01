package com.example.blogbe.model.news;

import com.example.blogbe.model.news.picture.NewsPicture;
import lombok.Data;

import java.util.List;
@Data
public class NewsDTO {
    private Long id;

    private String name;

    private String title;

    private List<NewsPicture> newsPictures;

    public NewsDTO() {
    }

    public NewsDTO(Long id, String name, String title, List<NewsPicture> newsPictures) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.newsPictures = newsPictures;
    }

    public NewsDTO(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }
}
