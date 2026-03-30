package com.intheeast.boardservice.repository;

import com.intheeast.boardservice.domain.Comment;
import com.intheeast.boardservice.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 특정 게시글(Post)에 달린 모든 댓글 조회
    List<Comment> findByPost(Post post);

    // 게시글 ID(Long)를 이용해 직접 댓글 조회
    List<Comment> findByPostId(Long postId);

    // 특정 작성자가 쓴 댓글 조회
    List<Comment> findByAuthor(String author);
}