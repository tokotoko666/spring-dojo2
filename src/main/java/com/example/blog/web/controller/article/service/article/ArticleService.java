package com.example.blog.web.controller.article.service.article;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ArticleService {

    public ArticleEntity findById(long id) {
        return new ArticleEntity(
                id,
                "title",
                "content",
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
