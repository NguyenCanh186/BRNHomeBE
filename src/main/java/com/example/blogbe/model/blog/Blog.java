package com.example.blogbe.model.blog;

import com.example.blogbe.model.blog.picture.BlogPicture;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @OneToMany(mappedBy = "blog")
//    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<BlogPicture> blogPictures;

    public Blog() {
    }

    public Blog(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public Blog(String name, String title) {
        this.name = name;
        this.title = title;
    }
}
