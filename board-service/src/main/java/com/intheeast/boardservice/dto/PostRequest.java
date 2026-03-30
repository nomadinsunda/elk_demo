package com.intheeast.boardservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        @Size(max = 100, message = "제목은 100자 이내여야 합니다.")
        String title,

        @NotBlank(message = "내용은 필수 입력 값입니다.")
        String content,

        @NotBlank(message = "작성자는 필수 입력 값입니다.")
        String author
) {}