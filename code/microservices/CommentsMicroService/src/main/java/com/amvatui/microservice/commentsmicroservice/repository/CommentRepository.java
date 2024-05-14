package com.amvatui.microservice.commentsmicroservice.repository;

import com.amvatui.microservice.commentsmicroservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentIdOrderByCreatedAtDesc(Long parentId);
}
