package com.dtps.app.service;

import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class S3Service {

    private final String bucketName = "dtps-receipts-naman";
    private final Region region = Region.of("ap-southeast-2");

    public void uploadReceipt(String content) {

        S3Client s3Client = S3Client.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        String fileName = "receipt-" + UUID.randomUUID() + ".txt";

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType("text/plain")
                .build();

        s3Client.putObject(
                putObjectRequest,
                RequestBody.fromString(content, StandardCharsets.UTF_8)
        );

        s3Client.close();
    }
}
