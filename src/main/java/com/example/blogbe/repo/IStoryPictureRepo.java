package com.example.blogbe.repo;

import com.example.blogbe.model.story.picture.StoryPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoryPictureRepo extends JpaRepository<StoryPicture, Long> {
}
