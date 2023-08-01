package com.example.blogbe.service.newsService;

import com.example.blogbe.model.news.picture.NewsPicture;
import com.example.blogbe.repo.IStoryPictureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsPictureService implements INewsPictureService {
    @Autowired
    private IStoryPictureRepo storyPictureRepo;
    @Override
    public List<NewsPicture> findAll() {
        return storyPictureRepo.findAll();
    }

    @Override
    public Optional<NewsPicture> findById(Long id) {
        return storyPictureRepo.findById(id);
    }

    @Override
    public NewsPicture save(NewsPicture newsPicture) {
        return storyPictureRepo.save(newsPicture);
    }

    @Override
    public void remove(Long id) {
        storyPictureRepo.deleteById(id);
    }

    public List<NewsPicture> findAllByStoryId(Long id) {
        return storyPictureRepo.findAllByStoryId(id);
    }
}
