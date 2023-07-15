package com.example.blogbe.repo;

import com.example.blogbe.model.story.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStoryRepo extends JpaRepository<Story, Long> {
    @Query(nativeQuery = true, value = "select * FROM story where name = ?;")
    Story findByName(String name);
}
