package com.amvatui.microservice.imagesmicroservice.service;

import com.amvatui.microservice.imagesmicroservice.dto.FullUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class AuthService {
    @Value("${app.authService.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    public Optional<FullUserDto> findByUsername(String username) {
        ResponseEntity<FullUserDto> response =
                restTemplate.getForEntity(url + "/users/{username}", FullUserDto.class, username);

        return response.getStatusCode() == HttpStatus.NOT_FOUND ? Optional.empty() : Optional.of(response.getBody());
    }
}
