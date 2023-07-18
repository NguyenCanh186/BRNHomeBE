package com.example.blogbe.service.storyService;

import com.example.blogbe.model.story.picture.StoryPicture;
import com.example.blogbe.repo.IStoryPictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StoryPictureService implements IStoryPictureService{
    @Autowired
    private IStoryPictureRepo storyPictureRepo;
    @Override
    public List<StoryPicture> findAll() {
        return storyPictureRepo.findAll();
    }

    @Override
    public Optional<StoryPicture> findById(Long id) {
        return storyPictureRepo.findById(id);
    }

    @Override
    public StoryPicture save(StoryPicture storyPicture) {
        return storyPictureRepo.save(storyPicture);
    }

    @Override
    public void remove(Long id) {
        storyPictureRepo.deleteById(id);
    }

    public List<StoryPicture> findAllByStoryId(Long id) {
        return storyPictureRepo.findAllByStoryId(id);
    }
}
