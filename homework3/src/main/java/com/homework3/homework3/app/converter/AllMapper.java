package com.homework3.homework3.app.converter;

import com.homework3.homework3.app.dto.*;
import com.homework3.homework3.app.entity.AppUser;
import com.homework3.homework3.app.entity.Comment;
import com.homework3.homework3.app.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AllMapper {


    AllMapper INSTANCE = Mappers.getMapper(AllMapper.class);

    Product convertToProduct(ProductSaveRequestDto productSaveRequestDto);

    ProductResponseDto convertToProductResponseDto(Product product);

    List<ProductDto> convertToProductDtoList(List<Product> productList);


    List<UserDto> convertToUserDtoList(List<AppUser> appUserList);

    AppUser convertToUser(UserSaveRequestDto userSaveRequestDto);

    UserResponseDto convertToUserResponseDto(AppUser appUser);

    Comment convertToComment(CommentSaveRequestDto commentSaveRequestDto);

    CommentResponseDto convertToCommentResponseDto(Comment comment);

    List<CommentDto> convertToCommentDtoList(List<Comment> commentList);
}
