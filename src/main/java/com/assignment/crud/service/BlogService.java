package com.assignment.crud.service;

import com.assignment.crud.dto.BlogDto;
import com.assignment.crud.domain.Blog;
import com.assignment.crud.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllPosts() {
        return blogRepository.findAll();
    }

    // Get a post by ID
    public Optional<Blog> getPostById(Long id) {
        return blogRepository.findById(id);
    }

    // Create a new post
    public Blog createPost(BlogDto blogDto) {
        Blog blog = blogDto.toDomain();
        return blogRepository.save(blog);
    }

    // Update an existing post
    public Blog updatePost(Long id, BlogDto blogDtoDetails) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        Blog blogDetails = blogDtoDetails.toDomain();
        blog.setContent(blogDetails.getContent());
        blog.setTitle(blogDetails.getTitle());
        return blogRepository.save(blog);
    }

    // Delete a post
    public void deletePost(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found with id " + id));
        blogRepository.delete(blog);
    }
}
