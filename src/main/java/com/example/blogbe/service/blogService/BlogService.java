package com.example.blogbe.service.blogService;

import com.example.blogbe.model.blog.Blog;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogService implements IBlogService{
    @Override
    public List<Blog> findAll() {
        return null;
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Blog save(Blog blog) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
