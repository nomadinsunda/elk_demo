package com.intheeast.boardservice.repository;

import com.intheeast.boardservice.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 작성자 이름으로 게시글 찾기
    List<Post> findByAuthor(String author);

    // 제목에 특정 키워드가 포함된 게시글 검색 (페이징 지원)
    Page<Post> findByTitleContaining(String keyword, Pageable pageable);

    // 최신순으로 모든 게시글 가져오기 (기본 findAll보다 명시적)
    List<Post> findAllByOrderByIdDesc();
}