package com.example.blogbe.repo;

import com.example.blogbe.model.story.picture.StoryPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoryPictureRepo extends JpaRepository<StoryPicture, Long> {
    @Query(nativeQuery = true, value = "select * from story_picture where story_id  = ?;")
    List<StoryPicture> findAllByStoryId(Long id);
}
