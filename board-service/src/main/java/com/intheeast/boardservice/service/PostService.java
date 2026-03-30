package com.intheeast.boardservice.service;

import com.intheeast.boardservice.domain.Post;
import com.intheeast.boardservice.dto.PostRequest;
import com.intheeast.boardservice.dto.PostResponse;
import com.intheeast.boardservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 기본적으로 읽기 전용으로 설정 (성능 최적화)
public class PostService {

    private final PostRepository postRepository;

    // 1. 게시글 작성
    @Transactional // 쓰기 작업이므로 readOnly 해제
    public Long createPost(PostRequest request) {
        Post post = Post.builder()
                .title(request.title())
                .content(request.content())
                .author(request.author())
                .build();
        return postRepository.save(post).getId();
    }

    // 2. 게시글 단건 조회
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return PostResponse.from(post);
    }

    // 3. 게시글 전체 조회 (최신순)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }

    // 4. 게시글 수정
    @Transactional
    public void updatePost(Long id, PostRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 엔티티 내부의 update 메서드 활용 (더티 체킹 발생)
        post.update(request.title(), request.content());
    }

    // 5. 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postRepository.delete(post);
    }
}