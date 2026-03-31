package com.intheeast.searchservice.controller;

import com.intheeast.searchservice.domain.PostDocument;
import com.intheeast.searchservice.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    /**
     * 키워드로 게시글 검색 (제목 또는 내용)
     * GET /api/search?keyword=안녕
     */
    @GetMapping
    public ResponseEntity<List<PostDocument>> search(@RequestParam(name = "keyword") String keyword) {
        // 검색어가 비어있을 경우에 대한 처리 (선택사항)
        if (keyword == null || keyword.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        List<PostDocument> results = searchService.searchPosts(keyword);
        return ResponseEntity.ok(results);
    }
}