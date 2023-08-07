package com.example.blogbe.config.custom;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

public class S3Util {

    private static final String BUCKET = "anhcuatoi";
    private static final Region AWS_REGION = Region.AP_SOUTHEAST_1; // Thay bằng mã khu vực AWS S3 của bạn

    public static void uploadFile(String fileName, InputStream inputStream) throws IOException {
        S3Client client = S3Client.builder()
                .region(AWS_REGION)
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("AKIAVIPDITI5JRF7DBXC", "dcPNCfI09KLjLepM1e1x982Tp3S0Pia+Sf09VL/B")))
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(BUCKET)
                .key(fileName)
                .build();
        client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));
    }
}
