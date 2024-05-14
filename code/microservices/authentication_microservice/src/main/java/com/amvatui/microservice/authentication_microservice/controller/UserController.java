package com.amvatui.microservice.authentication_microservice.controller;

import com.amvatui.microservice.authentication_microservice.dto.FullUserDto;
import com.amvatui.microservice.authentication_microservice.dto.UserDto;
import com.amvatui.microservice.authentication_microservice.mapper.UserMapper;
import com.amvatui.microservice.authentication_microservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{username}")
    public ResponseEntity<FullUserDto> getByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper.INSTANCE::toFullDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
