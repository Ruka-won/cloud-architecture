package com.cloudarchitecture.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateImageResponseDto {

    //속성
    private final Long id;
    private final String message;
}
