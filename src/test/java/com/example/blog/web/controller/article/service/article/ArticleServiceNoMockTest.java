package com.example.blog.web.controller.article.service.article;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ArticleServiceNoMockTest {

    @Autowired
    private ArticleService cut;

    @Test
    public void cut() {
        assertThat(cut).isNotNull();
    }

    @Test
    @DisplayName("findById: 指定されたIDのArticleが存在するとき、ArticleEntityを返す")
    @Sql(statements = {
            """
             INSERT INTO articles (id, title, body, created_at, updated_at)
             VALUES (999, "title_999", "body_999", "2022-01-01 10:00:00", "2022-02-01 11:00:00");
             """
    })
    public void selectById_returnArticleEntity() {
        // ## Arrange ##

        // ## Act ##
        var actual = cut.findById(999);

        // ## Assert ##
        assertThat(actual).isPresent()
                .hasValueSatisfying(article -> {
                    assertThat(article.id()).isEqualTo(999);
                    assertThat(article.title()).isEqualTo("title_999");
                    assertThat(article.content()).isEqualTo("body_999");
                    assertThat(article.createdAt()).isEqualTo("2022-01-01T10:00:00");
                    assertThat(article.updatedAt()).isEqualTo("2022-02-01T11:00:00");
                });
    }

    @Test
    @DisplayName("findById: 指定されたIDのArticleが存在しないとき、Optiona.Emptyを返す")
    public void selectById_returnEmpty() {
        // ## Arrange ##

        // ## Act ##
        var actual = cut.findById(-999);

        // ## Assert ##
        assertThat(actual).isEmpty();
    }
}