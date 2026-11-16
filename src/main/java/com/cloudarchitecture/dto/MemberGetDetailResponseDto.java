package com.cloudarchitecture.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class MemberGetDetailResponseDto {
    //속성
    private final Long id;
    private final String name;
    private final int age;
    private final String mbti;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;


}
