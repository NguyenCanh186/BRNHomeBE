package com.example.blogbe.model.blog.picture;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "blog_picture")
public class BlogPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;

    public BlogPicture(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public BlogPicture() {

    }
}
