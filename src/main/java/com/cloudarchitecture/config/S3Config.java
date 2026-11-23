package com.cloudarchitecture.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {


    // S3 업로드 기능
    @Bean
    public S3Client s3Client() {
        S3Client s3Client = S3Client.create();
        return s3Client;
    }
    // Presigned URL 생성 기능
    @Bean
    public S3Presigner s3Presigner() {
        return S3Presigner.create();
    }
}