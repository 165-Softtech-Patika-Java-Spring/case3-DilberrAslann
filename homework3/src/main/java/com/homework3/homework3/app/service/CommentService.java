package com.homework3.homework3.app.service;

import com.homework3.homework3.app.dto.CommentDto;
import com.homework3.homework3.app.dto.CommentResponseDto;
import com.homework3.homework3.app.dto.CommentSaveRequestDto;
import com.homework3.homework3.app.service.entityService.CommentEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentEntityService commentEntityService;

    public CommentResponseDto save(CommentSaveRequestDto commentSaveRequestDto) {
        return commentEntityService.save(commentSaveRequestDto);
    }


    public void deleteComment(Long id) {

        commentEntityService.delete(id);
    }


    public List<CommentDto> findAllByUserId(Long userId) {
        return commentEntityService.findAllByUserId(userId);
    }

    public List<CommentDto> findAllByProductId(Long productId) {
        return commentEntityService.findAllByProductId(productId);
    }


}


