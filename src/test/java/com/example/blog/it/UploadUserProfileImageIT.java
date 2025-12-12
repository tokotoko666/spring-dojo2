package com.example.blog.it;

import com.example.blog.model.UserProfileUploadURLDTO;
import com.example.blog.service.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UploadUserProfileImageIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private UserService userService;

    private static final String TEST_USERNAME = "test_username1";

    private static final String TEST_PASSWORD = "password10";

    private static final String DUMMY_SESSION_ID = "session_id_1";

    private static final String SESSION_COOKIE_NAME = "SESSION";

    @BeforeEach
    public void beforeEach() {
        userService.delete(TEST_USERNAME);
    }

    @AfterEach
    public void afterEach() {
        userService.delete(TEST_USERNAME);
    }

    @Test
    public void integrationTest() {

        var xsrfToken = getCsrfCookie();
        // ユーザー作成
        register(xsrfToken);

        //ログイン成功
        var sessionId = loginSuccess(xsrfToken);

        // Pre-signed URL の取得
        getUserProfileImageUploadURL(sessionId);

        // S3へのファイルアップロード

        // ファイルパスの登録
    }

    private String getCsrfCookie() {

        // ## Arrange ##

        // ## Act ##
        var responseSpec = webTestClient.get().uri("/csrf-cookie").exchange();

        // ## Assert ##
        responseSpec.expectStatus().isNoContent();

        var response = responseSpec.returnResult(String.class);
        var xsrfTokenOpt = Optional.ofNullable(response.getResponseCookies().getFirst("XSRF-TOKEN"));

        responseSpec.expectStatus().isNoContent();
        assertThat(xsrfTokenOpt).isPresent()
                .hasValueSatisfying(xsrfToken -> assertThat(xsrfToken.getValue()).isNotBlank());

        return xsrfTokenOpt.get().getValue();
    }

    private void register(String xsrfToken) {

        // ## Arrange ##
        var bodyJson = String.format(
                """
                        {
                          "username": "%s",
                          "password": "%s"
                        }
                        """, TEST_USERNAME, TEST_PASSWORD);

        // ## Act ##
        var responseSpec = webTestClient.post().uri("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .cookie("XSRF-TOKEN", xsrfToken)
                .header("X-XSRF-TOKEN", xsrfToken)
                .bodyValue(bodyJson)
                .exchange();

        // ## Assert ##
        responseSpec.expectStatus().isCreated();
    }

    private String loginSuccess(String xsrfToken) {

        // ## Arrange ##
        var bodyJson = String.format(
                """
                        {
                          "username": "%s",
                          "password": "%s"
                        }
                        """, TEST_USERNAME, TEST_PASSWORD);

        // ## Act ##
        var responseSpec = webTestClient.post().uri("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .cookie("XSRF-TOKEN", xsrfToken)
                .cookie(SESSION_COOKIE_NAME, DUMMY_SESSION_ID)
                .header("X-XSRF-TOKEN", xsrfToken)
                .bodyValue(bodyJson)
                .exchange();

        // ## Assert ##
        var response = responseSpec.returnResult(String.class);
        var sessionIdOpt = Optional.ofNullable(response.getResponseCookies().getFirst(SESSION_COOKIE_NAME));
        assertThat(sessionIdOpt)
                .isPresent()
                .hasValueSatisfying(sessionId -> assertThat(sessionId.getValue()).isNotBlank());
        return sessionIdOpt.get().getValue();
    }

    private void getUserProfileImageUploadURL(String loginSessionCokkie) {
        // ## Act ##
        var responseSpec = webTestClient
                .get().uri(uriBuilder -> uriBuilder
                        .path("/users/me/image-upload-url")
                        .queryParam("fileName", "my-profile.png")
                        .queryParam("contentType", "image.png")
                        .queryParam("contentLength", 104892)
                        .build()
                )
                .cookie(SESSION_COOKIE_NAME, loginSessionCokkie)
                .exchange();
        // ## Assert ##
        var actualResponseBody = responseSpec.expectStatus().isOk()
                .expectBody(UserProfileUploadURLDTO.class)
                .returnResult()
                .getResponseBody();

        assertThat(actualResponseBody).isNotNull();
        assertThat(actualResponseBody.getImagePath()).isNotBlank();
        assertThat(actualResponseBody.getImageUploadUrl().toString()).isNotBlank();
    }
}
