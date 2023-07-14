package com.example.blogbe.repo.blogRepo;

import com.example.blogbe.model.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Long> {
}
