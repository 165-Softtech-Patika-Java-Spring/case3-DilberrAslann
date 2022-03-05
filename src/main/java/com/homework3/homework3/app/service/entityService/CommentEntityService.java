package com.homework3.homework3.app.service.entityService;

import com.homework3.homework3.app.converter.AllMapper;
import com.homework3.homework3.app.dao.CommentDao;
import com.homework3.homework3.app.dao.ProductDao;
import com.homework3.homework3.app.dao.UserDao;
import com.homework3.homework3.app.dto.CommentDto;
import com.homework3.homework3.app.dto.CommentResponseDto;
import com.homework3.homework3.app.dto.CommentSaveRequestDto;
import com.homework3.homework3.app.entity.AppUser;
import com.homework3.homework3.app.entity.Comment;

import com.homework3.homework3.app.entity.Product;
import com.homework3.homework3.gen.entity.BaseAdditionalFields;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentEntityService {

    private final CommentDao commentDao;
    private final ProductDao productDao;
    private final UserDao userDao;


    public CommentResponseDto save(CommentSaveRequestDto commentSaveRequestDto) {

        Comment comment = AllMapper.INSTANCE.convertToComment(commentSaveRequestDto);

        setAdditionalFields(comment);//Bu satırı yok edersek, hangi tarihte oluşturduğu bilgisine ulaşamayız.

        comment = commentDao.save(comment);

        CommentResponseDto commentResponseDto = AllMapper.INSTANCE.convertToCommentResponseDto(comment);

        return commentResponseDto;
    }

    private void setAdditionalFields(Comment comment) {
        BaseAdditionalFields baseAdditionalFields = comment.getBaseAdditionalFields();


        if (baseAdditionalFields == null){
            baseAdditionalFields = new BaseAdditionalFields();
            comment.setBaseAdditionalFields(baseAdditionalFields);
        }if (comment.getId() == null){
            baseAdditionalFields.setCreateDate(new Date());
        }
        baseAdditionalFields.setUpdateDate(new Date());
    }

    public void delete(Long id) {
        Comment comment = getById(id);

        commentDao.delete(comment);
    }


    private Comment getById(Long id) {

        Optional<Comment> commentOptional = commentDao.findById(id);

        Comment comment;

        if (commentOptional.isPresent()) {

            comment = commentOptional.get();

        } else {
            throw new RuntimeException("Item not found!");
        }

        return comment;
    }

    public List<CommentDto> findAllByUserId(Long userId) {


        Optional<List<Comment>>commentListOptional = Optional.ofNullable(commentDao.findAllByUserId(userId));

        List<Comment> commentList= commentListOptional.orElseThrow(() -> new RuntimeException("Comment not found!"));

        AppUser user = userDao.getById(userId);

        List<CommentDto> commentDtoList= AllMapper.INSTANCE.convertToCommentDtoList(commentList);
        if(commentDtoList.isEmpty()){
            throw new NotFoundException(user.getName()+ " " + user.getSurname() + " hasn't left any comments yet." );
        }
        return commentDtoList;
    }

    public List<CommentDto> findAllByProductId(Long productId) {

        Optional<List<Comment>>commentListOptional = Optional.ofNullable(commentDao.findAllByProductId(productId));

        List<Comment> commentList= commentListOptional.orElseThrow(() -> new RuntimeException("Comment not found!"));
        Product product = productDao.getById(productId);
        List<CommentDto> commentDtoList= AllMapper.INSTANCE.convertToCommentDtoList(commentList);
        if(commentDtoList.isEmpty()){
            throw new NotFoundException("There are no comments for " + product.getName() +" product yet.");

        }
        return commentDtoList;
    }

}
