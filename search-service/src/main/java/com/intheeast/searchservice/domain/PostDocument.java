package com.intheeast.searchservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Document(indexName = "posts") // Logstash가 만든 인덱스 이름과 일치해야 함
public class PostDocument {

    @Id
    private String id; // ES는 기본적으로 String ID를 사용 (Logstash에서 매핑한 id)

    @Field(type = FieldType.Text, analyzer = "standard")
    private String title;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String content;

    @Field(type = FieldType.Text, analyzer = "standard")
    private String author;
}