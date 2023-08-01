package com.example.blogbe.service.newsService;

import com.example.blogbe.model.news.News;
import com.example.blogbe.repo.IStoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {
    @Autowired
    private IStoryRepo storyRepo;
    @Override
    public List<News> findAll() {
        return storyRepo.findAll();
    }

    @Override
    public Optional<News> findById(Long id) {
        return storyRepo.findById(id);
    }

    @Override
    public News save(News news) {
        return storyRepo.save(news);
    }

    @Override
    public void remove(Long id) {
        storyRepo.deleteById(id);
    }

    public News findByName(String name) {
        return storyRepo.findByName(name);
    }
}
