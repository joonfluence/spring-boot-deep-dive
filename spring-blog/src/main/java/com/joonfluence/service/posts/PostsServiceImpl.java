package com.joonfluence.service.posts;

import com.joonfluence.domain.posts.PostRepository;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostsServiceImpl implements PostsService {

    private final PostRepository postRepository;

    @Override
    public void create(PostsCreateRequestDto dto) throws Exception {
        throw new IllegalAccessException("method not implemented");
    }

    @Override
    public void update(PostsUpdateRequestDto dto) throws Exception {
        throw new IllegalAccessException("method not implemented");
    }

    @Override
    public void find(PostsFindRequestDto dto) throws Exception {
        throw new IllegalAccessException("method not implemented");

    }

    @Override
    public void findById(String id) throws Exception {
        throw new IllegalAccessException("method not implemented");

    }

    @Override
    public void delete(String id) throws Exception{
        throw new IllegalAccessException("method not implemented");

    }
}
