package com.assignment.crud.repository;

import com.assignment.crud.domain.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
            "postgres:15-alpine"
    );

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    public void testBlogSaveAndFindById() {
        /*
         * Given that
         */

        Blog blog = new Blog();
        blog.setTitle("My Title");
        blog.setContent("My Content");

        /*
         * Save blog
         */
        blogRepository.save(blog);

        /*
         * Verify if blog was saved
         */
        Optional<Blog> result = blogRepository.findById(blog.getId());
        assertTrue(result.isPresent());

        Blog blogFromGet = result.get();

        assertEquals("My Title", blogFromGet.getTitle());
        assertEquals("My Content", blogFromGet.getContent());

    }


    @Test
    public void testBlogCRUD() {

        Blog blog = new Blog();
        blog.setTitle("Title");
        blog.setContent("Content");

        blogRepository.save(blog);

        // find post by id
        Optional<Blog> savedResult = blogRepository.findById(blog.getId());
        assertTrue(savedResult.isPresent());

        Blog savedBlog = savedResult.get();

        Long blogId = savedBlog.getId();

        assertEquals("Title", savedBlog.getTitle());
        assertEquals("Content", savedBlog.getContent());

        // update post
        blog.setTitle("Hello");
        blogRepository.save(blog);

        // find post by id
        Optional<Blog> updatedResult = blogRepository.findById(blogId);
        assertTrue(updatedResult.isPresent());

        Blog updatedBlog = updatedResult.get();

        assertEquals("Hello", updatedBlog.getTitle());
        assertEquals("Content", updatedBlog.getContent());

        // delete a post
        blogRepository.delete(blog);

        // should be empty
        assertTrue(blogRepository.findById(blogId).isEmpty());


    }
}
