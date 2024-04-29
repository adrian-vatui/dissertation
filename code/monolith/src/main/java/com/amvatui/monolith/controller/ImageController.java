package com.amvatui.monolith.controller;

import com.amvatui.monolith.dto.ImageDto;
import com.amvatui.monolith.entity.Image;
import com.amvatui.monolith.entity.Post;
import com.amvatui.monolith.mapper.ImageMapper;
import com.amvatui.monolith.repository.ImageRepository;
import com.amvatui.monolith.repository.PostRepository;
import com.amvatui.monolith.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/posts/{id}/images", produces = MediaType.APPLICATION_JSON_VALUE)
public class ImageController {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private S3Service s3Service;

    @GetMapping
    public ResponseEntity<List<ImageDto>> getAll(@PathVariable Long id) {
        List<ImageDto> images = imageRepository.findByParentId(id).stream()
                .map(ImageMapper.INSTANCE::toDto)
                .toList();

        return ResponseEntity.ok(images);
    }

    @PostMapping
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestParam("file") MultipartFile image) {
        Optional<Post> parent = postRepository.findById(id);

        if (parent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String url = s3Service.uploadImage(image);
        Image imageEntity = new Image(url, parent.get());
        imageRepository.save(imageEntity);

        return ResponseEntity.noContent().build();
    }
}
