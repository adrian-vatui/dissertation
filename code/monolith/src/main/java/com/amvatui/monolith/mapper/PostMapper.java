package com.amvatui.monolith.mapper;

import com.amvatui.monolith.dto.PostDto;
import com.amvatui.monolith.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);
}
