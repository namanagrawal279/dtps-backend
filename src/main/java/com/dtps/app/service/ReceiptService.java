package com.dtps.app.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.nio.charset.StandardCharsets;

@Service
public class ReceiptService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    public ReceiptService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadReceipt(String content, String fileName) {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        s3Client.putObject(request,
                software.amazon.awssdk.core.sync.RequestBody.fromBytes(
                        content.getBytes(StandardCharsets.UTF_8)
                ));
    }
}
