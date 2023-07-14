package com.example.blogbe.service.blogService;

import com.example.blogbe.model.blog.picture.BlogPicture;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogPictureService implements IBlogPictureService {
    @Override
    public List<BlogPicture> findAll() {
        return null;
    }

    @Override
    public Optional<BlogPicture> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public BlogPicture save(BlogPicture blogPicture) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
