package com.example.blogbe.service.blogService;

import com.example.blogbe.model.story.Story;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogService implements IBlogService{
    @Override
    public List<Story> findAll() {
        return null;
    }

    @Override
    public Optional<Story> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Story save(Story blog) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
