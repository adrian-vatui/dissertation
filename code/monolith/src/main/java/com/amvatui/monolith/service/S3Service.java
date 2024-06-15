package com.amvatui.monolith.service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class S3Service {
    private static final String BUCKET_NAME = "social-media-app-monolith-single";
    private final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.EU_WEST_1)
            .build();

    @PostConstruct
    private void init() {
        if (!s3.doesBucketExistV2(BUCKET_NAME)) {
            log.info("Creating bucket {}", BUCKET_NAME);
            s3.createBucket(BUCKET_NAME);
        }
    }

    @SneakyThrows
    public String uploadImage(MultipartFile image) {
        String key = UUID.randomUUID().toString();
        ObjectMetadata data = new ObjectMetadata();
        data.setContentType(image.getContentType());
        data.setContentLength(image.getSize());
        s3.putObject(BUCKET_NAME, key, image.getInputStream(), data);

        URL url = s3.generatePresignedUrl(BUCKET_NAME, key, DateUtils.addDays(new Date(), 7));

        log.info("Image uploaded to {}", url);
        return url.toString();
    }
}
