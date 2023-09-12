package com.example.blogbe.service.newsService;

import com.example.blogbe.model.news.News;
import com.example.blogbe.repo.INewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements INewsService {
    @Autowired
    private INewsRepo storyRepo;
    @Override
    public List<News> findAll() {
        return storyRepo.findAllDESC();
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

    @Override
    public Page<News> findAllByTitleContaining(String title, Long page, Long size, String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), "date"); // sortDirection: "ASC" hoặc "DESC"
        Pageable pageable = PageRequest.of(Math.toIntExact(page), Math.toIntExact(size), sort);
        return storyRepo.findAllByTitleContaining(title, pageable);
    }
}
