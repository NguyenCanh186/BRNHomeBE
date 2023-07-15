package com.example.blogbe.model.story;

import com.example.blogbe.model.story.picture.StoryPicture;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "story")
    private List<StoryPicture> storyPictures;

    public Story() {
    }

    public Story(Long id, String name, String title) {
        this.id = id;
        this.name = name;
        this.title = title;
    }

    public Story(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public Story(Long id, String name, String title, List<StoryPicture> storyPictures) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.storyPictures = storyPictures;
    }

    public Story(String name, String title, List<StoryPicture> storyPictures) {
        this.name = name;
        this.title = title;
        this.storyPictures = storyPictures;
    }
}
