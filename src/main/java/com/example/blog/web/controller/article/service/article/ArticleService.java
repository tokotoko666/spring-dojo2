package com.example.blog.web.controller.article.service.article;

import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArticleService {

    public Optional<ArticleEntity> findById(long id) {

        Optional.of(new ArticleEntity(
                id,
                "title",
                "content",
                LocalDateTime.now(),
                LocalDateTime.now()
        ));
    }
}
