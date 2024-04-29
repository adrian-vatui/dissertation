package com.amvatui.monolith.mapper;

import com.amvatui.monolith.dto.CommentDto;
import com.amvatui.monolith.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto toDto(Comment comment);

    Comment toEntity(CommentDto commentDto);
}
