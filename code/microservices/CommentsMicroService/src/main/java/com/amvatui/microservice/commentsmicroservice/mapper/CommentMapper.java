package com.amvatui.microservice.commentsmicroservice.mapper;

import com.amvatui.microservice.commentsmicroservice.dto.CommentDto;
import com.amvatui.microservice.commentsmicroservice.dto.UserDto;
import com.amvatui.microservice.commentsmicroservice.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);

    default UserDto map(String value) {
        var userDto = new UserDto();
        userDto.setUsername(value);
        return userDto;
    }

    default String map(UserDto value) {
        return value == null ? null : value.getUsername();
    }
}
