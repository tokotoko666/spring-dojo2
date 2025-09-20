package com.example.blog.service.article;

import com.example.blog.service.article.ArticleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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
            VALUES (999, 'title_999', 'body_999', '2022-01-01 00:00:00', '2022-02-01 00:00:00');
            """

    })
    public void findBy_returnArticleEntity() {
        // ## Arrange ##

        // ## Act ##
        var actual = cut.findById(999);

        // ## Assert ##
        assertThat(actual).isPresent().hasValueSatisfying(article -> {
            assertThat(article.getId()).isEqualTo(999);
            assertThat(article.getTitle()).isEqualTo("title_999");
            assertThat(article.getBody()).isEqualTo("body_999");
            assertThat(article.getCreatedAt()).isEqualTo("2022-01-01T00:00:00");
            assertThat(article.getUpdatedAt()).isEqualTo("2022-02-01T00:00:00");

        });
    }

    @Test
    @DisplayName("findById: 指定されたIDのArticleが存在しないとき、Optional.Emptyを返す")
    public void findBy_returnEmpty() {
        // ## Arrange ##

        // ## Act ##
        var actual = cut.findById(999);

        // ## Assert ##
        assertThat(actual).isEmpty();
    }
}