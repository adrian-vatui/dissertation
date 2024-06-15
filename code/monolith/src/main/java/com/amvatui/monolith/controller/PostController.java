package com.amvatui.monolith.controller;

import com.amvatui.monolith.dto.PostDto;
import com.amvatui.monolith.entity.Post;
import com.amvatui.monolith.entity.User;
import com.amvatui.monolith.mapper.PostMapper;
import com.amvatui.monolith.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity<List<PostDto>> getAll() {
        final PageRequest pageRequest = PageRequest.of(0, 50,
                Sort.by("createdAt").descending());

        return ResponseEntity.ok(postRepository.findAll(pageRequest).stream()
                .map(PostMapper.INSTANCE::toDto)
                .toList());
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto, @AuthenticationPrincipal User author) {
        Post post = PostMapper.INSTANCE.toEntity(postDto);
        post.setAuthor(author);
        return ResponseEntity.ok(PostMapper.INSTANCE.toDto(postRepository.save(post)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById(@PathVariable Long id) {
        return postRepository.findById(id)
                .map(PostMapper.INSTANCE::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
