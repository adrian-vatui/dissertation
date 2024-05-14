package com.amvatui.microservice.imagesmicroservice.controller;

import com.amvatui.microservice.imagesmicroservice.dto.ImageDto;
import com.amvatui.microservice.imagesmicroservice.dto.PostDto;
import com.amvatui.microservice.imagesmicroservice.entity.Image;
import com.amvatui.microservice.imagesmicroservice.mapper.ImageMapper;
import com.amvatui.microservice.imagesmicroservice.repository.ImageRepository;
import com.amvatui.microservice.imagesmicroservice.service.PostsService;
import com.amvatui.microservice.imagesmicroservice.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
    private PostsService postsService;

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
    public ResponseEntity<Void> create(@PathVariable Long id, @RequestParam("file") MultipartFile image,
                                       @RequestHeader(HttpHeaders.COOKIE) String authCookie) {
        Optional<PostDto> parent = postsService.getById(id, authCookie);

        if (parent.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String url = s3Service.uploadImage(image);
        Image imageEntity = new Image(url, parent.get().getId());
        imageRepository.save(imageEntity);

        return ResponseEntity.noContent().build();
    }
}
