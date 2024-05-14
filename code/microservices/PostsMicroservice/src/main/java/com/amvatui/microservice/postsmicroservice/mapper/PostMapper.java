package com.amvatui.microservice.postsmicroservice.mapper;

import com.amvatui.microservice.postsmicroservice.dto.PostDto;
import com.amvatui.microservice.postsmicroservice.dto.UserDto;
import com.amvatui.microservice.postsmicroservice.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    PostDto toDto(Post post);

    Post toEntity(PostDto postDto);

    default UserDto map(String value) {
        var userDto = new UserDto();
        userDto.setUsername(value);
        return userDto;
    }

    default String map(UserDto value) {
        return value == null ? null : value.getUsername();
    }
}
