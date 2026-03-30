package com.intheeast.boardservice.controller;

import com.intheeast.boardservice.dto.PostRequest;
import com.intheeast.boardservice.dto.PostResponse;
import com.intheeast.boardservice.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 1. 게시글 생성 (POST /api/posts)
    @PostMapping
    public ResponseEntity<Long> createPost(@RequestBody @Valid PostRequest request) {
        Long postId = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(postId);
    }

    // 2. 게시글 단건 조회 (GET /api/posts/{id})
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse response = postService.getPost(id);
        return ResponseEntity.ok(response);
    }

    // 3. 게시글 목록 조회 (GET /api/posts)
    @GetMapping
    public ResponseEntity<List<PostResponse>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 4. 게시글 수정 (PUT /api/posts/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long id,
            @RequestBody @Valid PostRequest request) {
        postService.updatePost(id, request);
        return ResponseEntity.ok().build();
    }

    // 5. 게시글 삭제 (DELETE /api/posts/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}