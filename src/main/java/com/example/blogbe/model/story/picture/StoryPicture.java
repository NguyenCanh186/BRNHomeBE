package com.example.blogbe.model.story.picture;

import com.example.blogbe.model.story.Story;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "story_picture")
public class StoryPicture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String title;

    @ManyToOne
    @JoinColumn(name = "story_id")
    private Story story;

    public StoryPicture(String image, String title) {
        this.image = image;
        this.title = title;
    }

    public StoryPicture() {

    }
}
