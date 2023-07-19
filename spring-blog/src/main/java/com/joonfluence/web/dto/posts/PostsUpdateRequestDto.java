package com.joonfluence.web.dto.posts;

import com.joonfluence.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts toEntity(){
        return Posts.builder().title(title).content(content).build();
    }
}
