package com.example.blogbe.service.newsService;

import com.example.blogbe.model.news.News;
import com.example.blogbe.service.IGeneralService;
import org.springframework.data.domain.Page;

public interface INewsService extends IGeneralService<News> {
    Page<News> findAllByTitleContaining(String title, Long page, Long size, String sort);
}
