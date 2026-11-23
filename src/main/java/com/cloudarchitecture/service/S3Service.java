package com.cloudarchitecture.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.UUID;

@Service
public class S3Service {

    // 속성
    private final S3Client s3Client;
    private final S3Presigner s3Presigner;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;


    //생성자
    public S3Service(S3Client s3Client, S3Presigner s3Presigner) {
        this.s3Client = s3Client;
        this.s3Presigner = s3Presigner;
    }

    //기능
    public String uploadImage(Long memberId, MultipartFile file) {

        // 1. S3에 저장할 파일 경로 및 이름 생성
        String key = "profile-images/" + memberId + "/"
                + UUID.randomUUID();

        // 2. S3 업로드 요청 정보 생성
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try {
            // 3. 실제 파일을 AWS S3에 업로드
            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(
                            file.getInputStream(),
                            file.getSize()
                    )
            );

            // 4. S3 업로드한 파일의 Key를 DB로 저장하기 위해 반환
            return key;

        } catch (IOException e) {
            throw new RuntimeException("S3 이미지 업로드 실패", e);
        }
    }

    // Presigned URL 생성
    public String generatePresignedUrl(String imageKey) {

        // 1. 다운로드 할 이미지 지정
        // 1-1. buketName : S3 버킷 지정
        // 1-2. key : DB에 저장된 이미지의 S3 Object Key 지정
        // 1-3. build : 생성
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(imageKey)
                .build();

        // 2. Presigned 생성 조건 설정
        GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofDays(7))
                .getObjectRequest(getObjectRequest)
                .build();

        // 3. Presigned URL 생성
        PresignedGetObjectRequest presignedGetObjectRequest =
                s3Presigner.presignGetObject(presignRequest);


        // 4. 생성된 URL 문자로열 변환 후 반환
        return presignedGetObjectRequest.url().toString();

    }
}