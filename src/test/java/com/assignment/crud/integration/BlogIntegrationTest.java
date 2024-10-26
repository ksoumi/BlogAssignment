package com.assignment.crud.integration;

import com.assignment.crud.domain.Blog;
import com.assignment.crud.repository.BlogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BlogIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BlogRepository blogRepository;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:16-alpine"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void createPost_ShouldReturnCreated_WhenValidInput() throws Exception {
        /*
         * Given that
         */
        Blog blog = new Blog();
        blog.setTitle("Valid Title");
        blog.setContent("Some Content");

        /*
         * Invoke api
         */
        ResponseEntity<Blog> createResponse = restTemplate.postForEntity("/blog/create",blog, Blog.class);

        /*
         * Verify if http status = 200 and post was saved in database
         */
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        Blog savedBlog = createResponse.getBody();

        // Query the database to verify the post was created
        Optional<Blog> createdBlog = blogRepository.findById(savedBlog.getId());
        assertEquals("Valid Title", createdBlog.get().getTitle());
        assertEquals("Some Content", createdBlog.get().getContent());
    }
}
