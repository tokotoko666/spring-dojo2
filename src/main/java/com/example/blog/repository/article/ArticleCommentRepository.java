package com.example.blog.repository.article;

import com.example.blog.service.article.ArticleCommentEntity;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface ArticleCommentRepository {

    @Insert("""
            INSERT INTO article_comments(body, user_id, article_id, created_at)
            VALUES (#{body}, #{author.id}, #{article.id}, #{createdAt});
            """)
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(ArticleCommentEntity entity);

    @Select("""
            SELECT
                ac.id         AS article__comment__id
              , ac.body       AS article__comment__body
              , ac.created_at AS article__comment__created_at
            
              , a.id          AS article__id
              , a.title       AS article__title
              , a.body        AS article__body
              , a.created_at  AS article__created_at
              , a.updated_at  AS article__updated_at
            
              , aa.id         AS article__author__id
              , aa.username   AS article__author__username
              , aa.enabled    AS article__author__enabled
            
              , ca.id         AS comment__author__id
              , ca.username   AS comment__author__username
              , ca.enabled    AS comment__author__enabled
            FROM article_comments ac
            JOIN articles a ON ac.article_id = a.id
            JOIN users aa ON a.user_id = aa.id
            JOIN users ca ON ac.user_id = ca.id
            WHERE ac.id = #{articleCommentId}
            """)
    @Results(value = {
            @Result(column = "article__comment__id", property = "id"),
            @Result(column = "article__comment__body", property = "body"),
            @Result(column = "article__comment__created_at", property = "createdAt"),

            @Result(column = "article__id", property = "article.id"),
            @Result(column = "article__title", property = "article.title"),
            @Result(column = "article__body", property = "article.body"),
            @Result(column = "article__created_at", property = "article.createdAt"),
            @Result(column = "article__updated_at", property = "article.updatedAt"),

            @Result(column = "article__author__id", property = "article.author.id"),
            @Result(column = "article__author__username", property = "article.author.username"),
            @Result(column = "article__author__enabled", property = "article.author.enabled"),

            @Result(column = "comment__author__id", property = "author.id"),
            @Result(column = "comment__author__username", property = "author.username"),
            @Result(column = "comment__author__enabled", property = "author.enabled"),
    })
    Optional<ArticleCommentEntity> selectById(long user_id);
}
