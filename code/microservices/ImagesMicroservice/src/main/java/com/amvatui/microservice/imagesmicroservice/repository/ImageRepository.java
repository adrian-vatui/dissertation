package com.amvatui.microservice.imagesmicroservice.repository;

import com.amvatui.microservice.imagesmicroservice.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByParentId(Long parentId);
}
