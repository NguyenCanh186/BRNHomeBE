package com.example.blogbe.model.blog;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "blog")
@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    @Column(columnDefinition = "LONGTEXT", length = 1000000)
    private String content;

    public Blog(String image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public Blog() {

    }
}
