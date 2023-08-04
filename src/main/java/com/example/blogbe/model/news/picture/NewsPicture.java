package com.example.blogbe.model.news.picture;

import com.example.blogbe.model.news.News;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "news_picture")
public class NewsPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;

    @ManyToOne
    @JoinColumn(name = "news_id")
    @JsonIgnore
    private News news;

    public NewsPicture(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public NewsPicture() {

    }
}
