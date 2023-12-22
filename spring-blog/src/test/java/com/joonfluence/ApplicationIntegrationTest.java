package com.joonfluence;

import com.joonfluence.domain.posts.PostRepository;
import com.joonfluence.domain.posts.Posts;
import com.joonfluence.service.posts.PostsService;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ApplicationIntegrationTest {
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostsService postsService;

    PostsCreateRequestDto postCreateDto;
    PostsUpdateRequestDto postUpdateDto;
    PostsUpdateRequestDto postsUpdateRequestDto;
    Posts posts;

    @BeforeEach
    void init(){
        postCreateDto = PostsCreateRequestDto.builder().title("title").content("content").build();
        postUpdateDto = PostsUpdateRequestDto.builder().title("title2").content("content2").build();
        posts = new Posts(1L, postCreateDto.getTitle(), postCreateDto.getContent());
        postsUpdateRequestDto = PostsUpdateRequestDto.builder().title("title222").content("content3333").build();
    }


    @AfterEach
    void clearAll(){
        postRepository.deleteAll();
    }

    @DisplayName("Post 수정이 정상적으로 처리된다.")
    @Test
    public void posts_update() throws Exception {
        // given & when
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

    @DisplayName("Post 저장 할 때, 자동으로 생성/수정시간이 기록된다.")
    @Test
    public void is_base_time_entity_work(){
        // given
        LocalDateTime now = LocalDateTime.now();

        // when
        postRepository.save(postCreateDto.toEntity());
        List<Posts> posts1 = postRepository.findAll();

        // then
        Assertions.assertThat(posts1.get(0).getCreatedDate()).isAfter(now);
        Assertions.assertThat(posts1.get(0).getModifiedDate()).isAfter(now);
    }
}