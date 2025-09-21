package com.example.blog.web.controller.article;

import com.example.blog.service.article.ArticleService;
import com.example.blog.service.user.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ArticleRestControllerListArticlesTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Test
    void setup() {
        // ## Arrange ##

        // ## Act ##

        // ## Assert ##
        assertThat(mockMvc).isNotNull();
        assertThat(articleService).isNotNull();
        assertThat(userService).isNotNull();
    }

    @Test
    @DisplayName("Get /articles: 記事の一覧を取得できる")
    void listArticles_success() throws Exception {
        // ## Arrange ##
        var exppectedUser1 = userService.register("test_username1", "test_password1");
        var exppectedUser2 = userService.register("test_username2", "test_password2");
        var expectedArticle1 = articleService.create(exppectedUser1.getId(), "test_title1", "test_body1");
        var expectedArticle2 = articleService.create(exppectedUser2.getId(), "test_title2", "test_body2");

        // ## Act ##
        var actual = mockMvc.perform(
                get("/articles")
                .contentType(MediaType.APPLICATION_JSON)
        );

        // ## Assert ##
        // response header
        actual
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        // response body items[0]
        actual
                .andExpect(jsonPath("$.items[0].id").value(expectedArticle1.getId()))
                .andExpect(jsonPath("$.items[0].title").value(expectedArticle1.getTitle()))
                .andExpect(jsonPath("$.items[0].createdAt").value(expectedArticle1.getCreatedAt()))
                .andExpect(jsonPath("$.items[0].updatedAt").value(expectedArticle1.getUpdatedAt()))
                .andExpect(jsonPath("$.items[0].author.id").value(exppectedUser1.getId()))
                .andExpect(jsonPath("$.items[0].author.username").value(exppectedUser1.getUsername()))
        ;

        // response body items[1]
        actual
                .andExpect(jsonPath("$.items[1].id").value(expectedArticle2.getId()))
                .andExpect(jsonPath("$.items[1].title").value(expectedArticle2.getTitle()))
                .andExpect(jsonPath("$.items[1].createdAt").value(expectedArticle2.getCreatedAt()))
                .andExpect(jsonPath("$.items[1].updatedAt").value(expectedArticle2.getUpdatedAt()))
                .andExpect(jsonPath("$.items[1].author.id").value(exppectedUser2.getId()))
                .andExpect(jsonPath("$.items[1].author.username").value(exppectedUser2.getUsername()))
        ;
    }

}