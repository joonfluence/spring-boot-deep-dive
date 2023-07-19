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
public class PostsServiceImpl implements PostsService {
    private final PostRepository postRepository;

    @Override
    @Transactional
    public Posts create(PostsCreateRequestDto dto) throws Exception {
        try {
            return postRepository.save(dto.toEntity());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Posts update(Long id, PostsUpdateRequestDto dto) throws NoSuchElementException {
        try {
            Posts posts = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다"));
            posts.update(dto.getTitle(), dto.getContent());
            return posts;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Page<Posts> find(PostsFindRequestDto dto) throws Exception {
        try {
            Pageable pageable = PageRequest.of(dto.getPage(), dto.getPageSize());
            return postRepository.findAll(pageable);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Posts findById(Long id) throws NoSuchElementException {
        try {
            return postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다"));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) throws NoSuchElementException {
        try {
            postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("존재하지 않는 게시글입니다"));
            postRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
