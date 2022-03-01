package com.homework3.homework3.app.dto;


import lombok.Data;


@Data
public class CommentResponseDto {
    private String commentary;
    private Long userId;
    private Long productId;
}
