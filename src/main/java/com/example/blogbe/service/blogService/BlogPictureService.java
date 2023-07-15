package com.example.blogbe.service.blogService;

import com.example.blogbe.model.story.picture.StoryPicture;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BlogPictureService implements IBlogPictureService {
    @Override
    public List<StoryPicture> findAll() {
        return null;
    }

    @Override
    public Optional<StoryPicture> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public StoryPicture save(StoryPicture blogPicture) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
