package com.amvatui.monolith.repository;

import com.amvatui.monolith.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentIdOrderByCreatedAtDesc(Long parentId);
}
