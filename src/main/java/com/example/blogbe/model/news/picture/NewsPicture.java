package com.example.blogbe.model.news.picture;

import com.example.blogbe.model.news.News;
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
    @Column(columnDefinition = "LONGTEXT", length = 1000000)
    private String title;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    public NewsPicture(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public NewsPicture() {

    }
}
