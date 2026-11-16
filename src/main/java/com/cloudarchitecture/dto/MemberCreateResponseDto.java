package com.cloudarchitecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateResponseDto {
    //속성
    private final Long id;
    private final String message;

}
