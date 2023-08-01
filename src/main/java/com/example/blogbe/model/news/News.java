package com.example.blogbe.model.news;

import com.example.blogbe.model.news.picture.NewsPicture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cover;

    private String title;

    private Date date;

    @JsonIgnore
    @OneToMany(mappedBy = "news")
    private List<NewsPicture> newsPictures;

    public News() {
    }

    public News(String cover, String title, Date date, List<NewsPicture> newsPictures) {
        this.cover = cover;
        this.title = title;
        this.date = date;
        this.newsPictures = newsPictures;
    }
}
