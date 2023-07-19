package com.joonfluence.service.posts;

import com.joonfluence.domain.posts.PostRepository;
import com.joonfluence.domain.posts.Posts;
import com.joonfluence.web.dto.PaginationQueryDto;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@SpringBootTest
class PostsServiceImplTest {

    private final PostsService postsService;
    private final PostRepository postRepository;

    @Autowired
    PostsServiceImplTest(PostsService postsService, PostRepository postRepository) {
        this.postsService = postsService;
        this.postRepository = postRepository;
    }

    @AfterEach
    void clearAll(){
        postRepository.deleteAll();
    }

    @DisplayName("Post 저장이 정상적으로 처리된다.")
    @Test
    public void posts_save() throws Exception {
        // given
        PostsCreateRequestDto postCreateDto = PostsCreateRequestDto.builder().title("title").content("content").build();

        // when
        postsService.create(postCreateDto);
        List<Posts> posts1 = postRepository.findAll();

        // then
        Assertions.assertThat(posts1.get(0).getTitle()).isEqualTo(postCreateDto.getTitle());
        Assertions.assertThat(posts1.get(0).getContent()).isEqualTo(postCreateDto.getContent());
    }

    @DisplayName("없는 Post는 수정이 되면 안된다.")
    @Test
    void posts_update_error() {
        // given
        PostsUpdateRequestDto postUpdateDto = PostsUpdateRequestDto.builder().title("title2").content("content2").build();
        // when, then
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> postsService.update(1L, postUpdateDto));
    }

    @DisplayName("Post 수정이 정상적으로 처리된다.")
    @Test
    public void posts_update() throws Exception {
        // given
        PostsCreateRequestDto postCreateDto = PostsCreateRequestDto.builder().title("title").content("content").build();
        PostsUpdateRequestDto postsUpdateRequestDto = PostsUpdateRequestDto.builder().title("title222").content("content3333").build();

        // when
        Posts posts = postRepository.save(postCreateDto.toEntity());
        Posts updatePost = postsService.update(posts.getId(), postsUpdateRequestDto);
        Optional<Posts> posts1 = postRepository.findById(updatePost.getId());

        // then
        Assertions.assertThat(posts1.get().getTitle()).isEqualTo(postsUpdateRequestDto.getTitle());
        Assertions.assertThat(posts1.get().getContent()).isEqualTo(postsUpdateRequestDto.getContent());
    }

    @Test
    void 아무것도_반환되지_않는다() throws Exception {
        // given
        PostsFindRequestDto postsFindRequestDto = new PostsFindRequestDto();
        postsFindRequestDto.setPage(1);
        postsFindRequestDto.setPageSize(10);

        // when
        Page<Posts> posts1 = postsService.find(postsFindRequestDto);

        // then
        Assertions.assertThat(posts1.getTotalPages()).isEqualTo(0);
        Assertions.assertThat(posts1.getTotalElements()).isEqualTo(0L);
    }

    @Test
    void delete() {

    }
}