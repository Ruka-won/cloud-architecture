package com.cloudarchitecture.controller;


import com.cloudarchitecture.dto.*;
import com.cloudarchitecture.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/members")
public class MemberController {

    //속성
    private final MemberService memberService;

    //생성자
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //기능

   //1. 팀원 생성
    @PostMapping
    public ResponseEntity<MemberCreateResponseDto> createMember(@RequestBody MemberCreateRequestDto request) {

        // 1. 서비스 처리
        MemberCreateResponseDto responseDto = memberService.memberSave(request);

        // 2. 응답 객체 생성 및 반환
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    // 2. 팀원 정보 상세 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberGetDetailResponseDto> getDetailMember(@PathVariable("memberId") Long id) {

        // 1. 서비스 처리
        MemberGetDetailResponseDto responseDto = memberService.getDetailMember(id);

        // 2. 응답 객체 생성 및 반환
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    // 3. 이미지 저장
    @PostMapping("/{memberId}/profile-image")
    public ResponseEntity<CreateImageResponseDto> uploadProfileImage(@PathVariable("memberId") Long id,
                                                                     @ModelAttribute CreateImageRequestDto request) {

        // 1. 서비스 처리
        CreateImageResponseDto responseDto = memberService.saveImage(id, request);

        // 2. 응답 객체 생성 및 반환
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    // 4. 이미지 조회
    @GetMapping("/{memberId}/profile-image")
    public ResponseEntity<GetImageResponseDto> GetProfileImage(@PathVariable("memberId") Long id) {

        // 1. 서비스 처리
        GetImageResponseDto responseDto = memberService.getProfileImage(id);

        // 2. 응답 객체 생성 및 반환
        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }
}
