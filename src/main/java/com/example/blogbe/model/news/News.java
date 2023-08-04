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

    @JsonIgnore
    @OneToMany(mappedBy = "news")
    private List<NewsPicture> newsPictures;

    private Date date;

    private int category;

    public News() {
    }
}
