package com.example.blogbe.repo;

import com.example.blogbe.model.news.picture.NewsPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsPictureRepo extends JpaRepository<NewsPicture, Long> {
    @Query(nativeQuery = true, value = "select * from news_picture where news_id  = ?;")
    List<NewsPicture> findAllByNewsId(Long id);
}
