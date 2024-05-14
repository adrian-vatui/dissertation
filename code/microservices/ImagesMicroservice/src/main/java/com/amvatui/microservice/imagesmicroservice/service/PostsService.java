package com.amvatui.microservice.imagesmicroservice.service;

import com.amvatui.microservice.imagesmicroservice.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class PostsService {
    @Value("${app.postsService.url}")
    private String url;
    @Autowired
    private RestTemplate restTemplate;

    public Optional<PostDto> getById(Long id, String authCookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.COOKIE, authCookie);

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<PostDto> response =
                restTemplate.exchange(url + "/posts/{id}", HttpMethod.GET, entity, PostDto.class, id);

        return response.getStatusCode() == HttpStatus.NOT_FOUND ? Optional.empty() : Optional.of(response.getBody());
    }
}
