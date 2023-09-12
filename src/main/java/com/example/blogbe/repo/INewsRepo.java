package com.example.blogbe.repo;

import com.example.blogbe.model.news.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsRepo extends JpaRepository<News, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM news ORDER BY id DESC")
    List<News> findAllDESC();
    @Query(nativeQuery = true, value = "select * FROM news where title = ?;")
    News findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM news WHERE (:title IS NULL OR title LIKE %:title%)")
    Page<News> findAllByTitleContaining(String title, Pageable pageable);
}
