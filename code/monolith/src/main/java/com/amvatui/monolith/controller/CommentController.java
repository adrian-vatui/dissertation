package com.amvatui.monolith.controller;

import com.amvatui.monolith.dto.CommentDto;
import com.amvatui.monolith.entity.Comment;
import com.amvatui.monolith.entity.Post;
import com.amvatui.monolith.entity.User;
import com.amvatui.monolith.mapper.CommentMapper;
import com.amvatui.monolith.repository.CommentRepository;
import com.amvatui.monolith.repository.PostRepository;
import com.amvatui.monolith.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts/{id}/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAll(@PathVariable Long id) {
        List<Comment> comments = commentRepository.findByParentIdOrderByCreatedAtDesc(id);

        return ResponseEntity.ok(comments.stream()
                .map(CommentMapper.INSTANCE::toDto).toList());
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@PathVariable Long id, @RequestBody CommentDto commentDto,
                                             @AuthenticationPrincipal User author) {
        Optional<Post> parent = postRepository.findById(id);

        if (parent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Comment comment = CommentMapper.INSTANCE.toEntity(commentDto);
        comment.setParent(parent.get());
        comment.setAuthor(author);

        return ResponseEntity.ok(CommentMapper.INSTANCE.toDto(commentRepository.save(comment)));
    }

}
