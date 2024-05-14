package com.amvatui.microservice.commentsmicroservice.controller;

import com.amvatui.microservice.commentsmicroservice.dto.CommentDto;
import com.amvatui.microservice.commentsmicroservice.dto.PostDto;
import com.amvatui.microservice.commentsmicroservice.entity.Comment;
import com.amvatui.microservice.commentsmicroservice.entity.User;
import com.amvatui.microservice.commentsmicroservice.mapper.CommentMapper;
import com.amvatui.microservice.commentsmicroservice.repository.CommentRepository;
import com.amvatui.microservice.commentsmicroservice.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostsService postsService;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll(@PathVariable Long id) {
        List<Comment> comments = commentRepository.findByParentIdOrderByCreatedAtDesc(id);

        return ResponseEntity.ok(comments.stream()
                .map(CommentMapper.INSTANCE::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@PathVariable Long id, @RequestBody CommentDto commentDto,
                                             @AuthenticationPrincipal User author,
                                             @RequestHeader(HttpHeaders.COOKIE) String authCookie) {
        Optional<PostDto> parent = postsService.getById(id, authCookie);

        if (parent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comment comment = CommentMapper.INSTANCE.toEntity(commentDto);
        comment.setParentId(parent.get().getId());
        comment.setAuthor(author.getUsername());

        return ResponseEntity.ok(CommentMapper.INSTANCE.toDto(commentRepository.save(comment)));
    }

}
