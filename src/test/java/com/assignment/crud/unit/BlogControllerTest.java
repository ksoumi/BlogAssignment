package com.assignment.crud.unit;

import com.assignment.crud.controller.BlogController;
import com.assignment.crud.domain.Blog;
import com.assignment.crud.service.BlogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BlogController.class)
public class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BlogService blogService;


    @Test
    void createPost_ShouldReturnBadRequest_WhenTitleIsEmpty() throws Exception {
        String json = "{\"title\":\"\", \"content\":\"Some content\"}";

        mockMvc.perform(post("/blog/create")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createPost_ShouldReturnCreated_WhenValidInput() throws Exception {
        String json = "{\"title\":\"Valid Title\", \"content\":\"Some content\"}";

        when(blogService.createPost(any())).thenReturn(new Blog());

        mockMvc.perform(post("/blog/create")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().is2xxSuccessful());
    }
}
