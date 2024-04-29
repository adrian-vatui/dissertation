package com.amvatui.monolith.mapper;

import com.amvatui.monolith.dto.UserDto;
import com.amvatui.monolith.entity.ERole;
import com.amvatui.monolith.entity.Role;
import com.amvatui.monolith.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);

    default List<String> map(Set<Role> value) {
        return value.stream()
                .map(Role::getRole)
                .map(ERole::name)
                .toList();
    }

    default Set<Role> map(List<String> value) {
        return value.stream()
                .map(ERole::valueOf)
                .map(eRole -> Role.builder().role(eRole).build())
                .collect(Collectors.toSet());
    }
}
