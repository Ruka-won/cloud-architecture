package com.cloudarchitecture.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false, length = 255)
    private String mbti;

    @Column(nullable = true ,name = "profile_image_key")
    private String profileImageKey;


    public Member(String name, int age, String mbti) {
        this.name = name;
        this.age = age;
        this.mbti = mbti;
    }

    public void updateProfileImage(String profileImageKey) {
        this.profileImageKey = profileImageKey;
    }

}
