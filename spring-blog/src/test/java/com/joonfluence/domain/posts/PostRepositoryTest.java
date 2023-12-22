package com.joonfluence.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class PostRepositoryTest {

    private final PostRepository postRepository;

    @AfterEach
    void clearAll(){
        postRepository.deleteAll();
    }

    @Autowired
    PostRepositoryTest(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @DisplayName("Post 저장이 정상적으로 처리된다.")
    @Test
    public void posts_save(){
        // given
        Posts posts = Posts.builder().title("title").content("content").build();
        LocalDateTime now = LocalDateTime.now();
        // when
        postRepository.save(posts);
        List<Posts> posts1 = postRepository.findAll();

        // then
        Assertions.assertThat(posts1.get(0).getId()).isEqualTo(posts.getId());
        Assertions.assertThat(posts1.get(0).getTitle()).isEqualTo(posts.getTitle());
        Assertions.assertThat(posts1.get(0).getContent()).isEqualTo(posts.getContent());
    }
}