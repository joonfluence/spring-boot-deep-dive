package com.joonfluence.controller;

import com.joonfluence.domain.posts.Posts;
import com.joonfluence.service.posts.PostsService;
import com.joonfluence.web.dto.ResponseDto;
import com.joonfluence.web.dto.ResponseMessage;
import com.joonfluence.web.dto.posts.PostsCreateRequestDto;
import com.joonfluence.web.dto.posts.PostsFindRequestDto;
import com.joonfluence.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostsService postsService;

    @GetMapping
    public ResponseEntity<ResponseDto> findPostList(PostsFindRequestDto dto) throws Exception {
        Page<Posts> posts = postsService.find(dto);
        ResponseDto responseDto = ResponseDto.builder().data(posts).message(ResponseMessage.READ_POST.getMessage()).status(HttpStatus.OK.value()).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDto> createPost(@RequestBody PostsCreateRequestDto dto) throws Exception {
        Posts posts = postsService.create(dto);
        ResponseDto responseDto = ResponseDto.builder().data(posts).message(ResponseMessage.CREATE_POST.getMessage()).status(HttpStatus.CREATED.value()).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updatePost(@PathVariable Long id, @RequestBody PostsUpdateRequestDto dto) throws Exception {
        Posts posts = postsService.update(id, dto);
        ResponseDto responseDto = ResponseDto.builder().data(posts).message(ResponseMessage.UPDATE_POST.getMessage()).status(HttpStatus.OK.value()).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> findPostById(@PathVariable Long id) throws Exception {
        Posts posts = postsService.findById(id);
        ResponseDto responseDto = ResponseDto.builder().data(posts).message(ResponseMessage.READ_POST.getMessage()).status(HttpStatus.OK.value()).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deletePostById(@PathVariable Long id) throws Exception {
        postsService.delete(id);
        ResponseDto responseDto = ResponseDto.builder().message(ResponseMessage.DELETE_POST.getMessage()).status(HttpStatus.OK.value()).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }
}
