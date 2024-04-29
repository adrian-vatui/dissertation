package com.amvatui.monolith.repository;

import com.amvatui.monolith.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByParentId(Long parentId);
}
