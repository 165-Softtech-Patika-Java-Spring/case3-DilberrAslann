package com.homework3.homework3.app.controller;

import com.homework3.homework3.app.dto.CommentResponseDto;
import com.homework3.homework3.app.dto.CommentSaveRequestDto;
import com.homework3.homework3.app.dto.CommentDto;
import com.homework3.homework3.app.service.CommentService;
import com.homework3.homework3.gen.dto.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //Yeni bir yorum yazma
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CommentSaveRequestDto commentSaveRequestDto){

        CommentResponseDto commentResponseDto = commentService.save(commentSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(commentResponseDto));
        //return ResponseEntity.ok(commentResponseDto);
    }

    //
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        commentService.deleteComment(id);

        return ResponseEntity.ok(RestResponse.empty());
        //return ResponseEntity.ok(Void.TYPE);
    }


    //Bir kullanıcının yaptığı yorumlari getirme
    @GetMapping("/findAllByUserId/{userId}")
    public ResponseEntity findAllByUserId(@PathVariable Long userId){

        List<CommentDto> commentDtoList = commentService.findAllByUserId(userId);

        return ResponseEntity.ok(RestResponse.of(commentDtoList));
        //return ResponseEntity.ok(commentDtoList);
    }


    // Bir ürüne yapılan tüm yorumları getirme
    @GetMapping("/findAllByProductId/{productId}")
    public ResponseEntity findAllByProductId(@PathVariable Long productId){

        List<CommentDto> commentDtoList = commentService.findAllByProductId(productId);

        return ResponseEntity.ok(RestResponse.of(commentDtoList));
        //return ResponseEntity.ok(commentDtoList);
    }

}
