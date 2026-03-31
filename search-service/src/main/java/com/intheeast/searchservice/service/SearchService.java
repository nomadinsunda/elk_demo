package com.intheeast.searchservice.service;

import com.intheeast.searchservice.domain.PostDocument;
import com.intheeast.searchservice.repository.PostSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final PostSearchRepository postSearchRepository;

    public List<PostDocument> searchPosts(String keyword) {
        // 제목 또는 내용에서 키워드 검색
        return postSearchRepository.findByTitleContainingOrContentContainingOrAuthorContaining(keyword, keyword, keyword);    }
}