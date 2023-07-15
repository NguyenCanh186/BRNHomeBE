package com.example.blogbe.service.storyService;

import com.example.blogbe.model.story.Story;
import com.example.blogbe.repo.IStoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoryService implements IStoryService{
    @Autowired
    private IStoryRepo storyRepo;
    @Override
    public List<Story> findAll() {
        return storyRepo.findAll();
    }

    @Override
    public Optional<Story> findById(Long id) {
        return storyRepo.findById(id);
    }

    @Override
    public Story save(Story story) {
        return storyRepo.save(story);
    }

    @Override
    public void remove(Long id) {
        storyRepo.deleteById(id);
    }

    public Story findByName(String name) {
        return storyRepo.findByName(name);
    }
}
