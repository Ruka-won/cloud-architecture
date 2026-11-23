package com.cloudarchitecture.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class CreateImageRequestDto {

    //속성
    private MultipartFile file;


}
