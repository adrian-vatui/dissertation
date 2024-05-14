package com.amvatui.microservice.postsmicroservice.mapper;

import com.amvatui.microservice.postsmicroservice.dto.FullUserDto;
import com.amvatui.microservice.postsmicroservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromDto(FullUserDto userDto);
    default List<? extends GrantedAuthority> map(List<String> value) {
        return value.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
