package com.example.blog.web.controller.article.service.article;

import java.time.LocalDateTime;

public record ArticleEntity (
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){
}
