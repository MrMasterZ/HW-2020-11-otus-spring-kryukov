package otus.student.kryukov.dz.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import otus.student.kryukov.dz.domain.dto.BookDto;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.is;

@SpringBootTest
public class BookControllerTest {

    @Autowired
    private BookController bookController;

    @Test
    public void testBookList() {
        WebTestClient client = WebTestClient
                .bindToController(bookController)
                .build();

        String jsonResponse = new String(
            client.post()
                .uri("/book")
                .bodyValue(
                        new BookDto("1", "title", "author", "genre")
                )
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.title")
                .value(is("title"))
                .jsonPath("$.author")
                .value(is("author"))
                .jsonPath("$.genre")
                .value(is("genre"))
                .returnResult()
                    .getResponseBody());

                String id = jsonResponse.split("\"id\":\"")[1].split("\"")[0];

        client.delete()
                .uri("/book/" + id)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .consumeWith(response ->
                        Assertions.assertThat(
                                response.getResponseBody())
                                    .isEqualTo("ok".getBytes(StandardCharsets.UTF_8))
                            );
    }
}