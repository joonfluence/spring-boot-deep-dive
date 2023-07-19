package com.joonfluence.service.posts;

import com.joonfluence.domain.posts.Posts;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import org.springframework.data.domain.Page;

public interface PostsService {
    Posts create(PostsCreateRequestDto dto) throws IllegalAccessException, Exception;
    Posts update(Long id, PostsUpdateRequestDto dto) throws Exception;
    Page<Posts> find(PostsFindRequestDto dto) throws Exception;

    Posts findById(Long id) throws Exception;

    void delete(Long id) throws Exception;
}
