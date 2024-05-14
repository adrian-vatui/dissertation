package com.amvatui.microservice.commentsmicroservice.mapper;

import com.amvatui.microservice.commentsmicroservice.dto.FullUserDto;
import com.amvatui.microservice.commentsmicroservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "roles", target = "authorities")
    User fromDto(FullUserDto userDto);
    default List<? extends GrantedAuthority> map(List<String> value) {
        return value.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
