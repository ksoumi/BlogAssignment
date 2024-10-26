package com.assignment.crud.dto;

import com.assignment.crud.domain.Blog;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record BlogDto (
        @NotEmpty(message = "The title is required.")
        @Size(max = 255, message = "Title must not exceed 255 characters")
        String title,
        @NotEmpty(message = "The content is required.")
        String content){


//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }

    public Blog toDomain() {
        Blog blog = new Blog();
        blog.setTitle(this.title);
        blog.setContent(this.content);
        return blog;
    }
}
