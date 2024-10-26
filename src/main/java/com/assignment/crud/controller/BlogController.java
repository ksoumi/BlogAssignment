package com.assignment.crud.controller;

import com.assignment.crud.domain.Blog;
import com.assignment.crud.dto.BlogDto;
import com.assignment.crud.service.BlogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping
    public List<Blog> getAllPosts() {
        return blogService.getAllPosts();
    }

    @PostMapping("/create")
    public Blog createPost(@RequestBody @Valid BlogDto blogDto) {
        logger.info("Creating blog post with title: {}", blogDto.title());
        return blogService.createPost(blogDto);
    }

    @PutMapping("update/{id}")
    public Blog updatePost(@PathVariable Long id, @RequestBody @Valid BlogDto blogDto) {
        logger.info("Updating blog post with id: {}", id);
        // Validate the id
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID provided");
        }
        return blogService.updatePost(id, blogDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        logger.info("Deleting blog post with id: {}", id);
        // Validate the id
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID provided");
        }
        blogService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public Blog getPostById(@PathVariable Long id) {
        logger.info("Get blog post for id: {}", id);
        // Validate the id
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid ID provided");
        }
        return blogService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found with id " + id));
    }

}
