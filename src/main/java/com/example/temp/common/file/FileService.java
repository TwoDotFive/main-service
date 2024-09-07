package com.example.temp.common.file;

import com.amazonaws.services.s3.AmazonS3;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

import static com.amazonaws.HttpMethod.PUT;

@Slf4j
@Service
public class FileService {

    private final AmazonS3 amazonS3;
    private final String IMAGE_BUCKET_NAME;
    private final int PRESIGNED_URL_EXPIRATION_TIME_SECONDS;

    public FileService(
            @Autowired AmazonS3 amazonS3,
            @Value("${aws.s3.bucket}") String IMAGE_BUCKET_NAME,
            @Value("${aws.s3.presigned-url-expiration-time-seconds}") int PRESIGNED_URL_EXPIRATION_TIME_SECONDS
    ) {
        this.amazonS3 = amazonS3;
        this.IMAGE_BUCKET_NAME = IMAGE_BUCKET_NAME;
        this.PRESIGNED_URL_EXPIRATION_TIME_SECONDS = PRESIGNED_URL_EXPIRATION_TIME_SECONDS;
    }

    public String generatePresignedUrl() {
        Date expiration = LocalDateTime.now().plusSeconds(PRESIGNED_URL_EXPIRATION_TIME_SECONDS).toDate();
        return amazonS3.generatePresignedUrl(IMAGE_BUCKET_NAME, generateKey(), expiration, PUT).toString();
    }

    private String generateKey() {
        return UUID.randomUUID().toString();
    }

}
