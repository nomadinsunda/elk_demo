package com.intheeast.searchservice.repository;

import com.intheeast.searchservice.domain.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostSearchRepository extends ElasticsearchRepository<PostDocument, String> {

    // 제목이나 내용에 키워드가 포함된 게시글 검색
    List<PostDocument> findByTitleContainingOrContentContainingOrAuthorContaining(String title, String content, String author);
    // 특정 작성자의 글 검색
    List<PostDocument> findByAuthor(String author);
}