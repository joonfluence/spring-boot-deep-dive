package com.joonfluence.service.posts;

import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsDeleteRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;

public interface PostsService {
    void create(PostsCreateRequestDto dto) throws IllegalAccessException, Exception;
    void update(PostsUpdateRequestDto dto) throws Exception;
    void find(PostsFindRequestDto dto) throws Exception;
    void findById(String id) throws Exception;
    void delete(String id) throws Exception;
}
