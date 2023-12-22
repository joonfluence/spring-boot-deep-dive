package com.joonfluence.service.posts;

import com.joonfluence.domain.posts.PostRepository;
import com.joonfluence.domain.posts.Posts;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostsServiceImplTest {
    @Mock
    PostRepository postRepository;
    @InjectMocks
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

    @DisplayName("Post 저장이 정상적으로 처리된다.")
    @Test
    public void posts_save() throws Exception {
        // given
        when(postRepository.save(Mockito.any(Posts.class))).thenReturn(posts);

        // when
        Posts posts2 = postsService.create(postCreateDto);

        // then
        Assertions.assertThat(posts2.getTitle()).isEqualTo(postCreateDto.getTitle());
        Assertions.assertThat(posts2.getContent()).isEqualTo(postCreateDto.getContent());
    }

    @DisplayName("없는 Post는 수정이 되면 안된다.")
    @Test
    void posts_update_error() {
        // give, when, then
        when(postRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.ofNullable(null));
        org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> postsService.update(2L, postUpdateDto));
    }
}