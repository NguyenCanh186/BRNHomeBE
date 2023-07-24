package com.example.blogbe.service.blogService;

import com.example.blogbe.model.blog.Blog;
import com.example.blogbe.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogService implements IBlogService{
    
    @Autowired
    private BlogRepo blogRepo;
    @Override
    public List<Blog> findAll() {
        return blogRepo.findAll();
    }

    @Override
    public Optional<Blog> findById(Long id) {
        return blogRepo.findById(id);
    }

    @Override
    public Blog save(Blog blog) {
        return blogRepo.save(blog);
    }

    @Override
    public void remove(Long id) {
        blogRepo.deleteById(id);
    }
}
