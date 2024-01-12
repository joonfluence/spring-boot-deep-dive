package com.joonfluence.service.posts;

import com.joonfluence.domain.posts.PostRepository;
import com.joonfluence.domain.posts.Posts;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostRepository postRepository;

    @Transactional
    public Posts create(PostsCreateRequestDto dto) {
        return postRepository.save(dto.toEntity());
    }

    @Transactional
    public Posts update(Long id, PostsUpdateRequestDto dto) {
        Posts posts = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다"));
        posts.update(dto.getTitle(), dto.getContent());
        return posts;
    }

    public Page<Posts> find(PostsFindRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getPageSize());
        return postRepository.findAll(pageable);
    }

    public Posts findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다"));
    }

    @Transactional
    public void delete(Long id) {
        postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다"));
        postRepository.deleteById(id);
    }
}
