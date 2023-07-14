package com.joonfluence.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PostController.class)
public class PostsControllerTest {

    private final MockMvc mvc;
    private final static String baseUrl = "/posts";


    @Autowired
    PostsControllerTest(MockMvc mvc) {
        this.mvc = mvc;
    }

    @Test
    public void posts_확인() throws Exception {

        // given : "/"로 갔을 때 me 리턴하도록 페이지 구성.
        // when : 사용자가 "/" 디렉토리로 갔을 때.
        String posts = "posts";
        // then : 응답값이 users 로 나와야 한다.
        mvc.perform(get(baseUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(posts));

    }
}