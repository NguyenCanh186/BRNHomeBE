package com.example.blogbe.model.home;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "home")
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;
    private String description;

    public Home(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Home() {
    }
}
