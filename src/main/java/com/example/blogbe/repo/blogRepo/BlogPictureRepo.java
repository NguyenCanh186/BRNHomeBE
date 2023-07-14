package com.example.blogbe.repo.blogRepo;

import com.example.blogbe.model.blog.picture.BlogPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPictureRepo extends JpaRepository<BlogPicture, Long> {
}
