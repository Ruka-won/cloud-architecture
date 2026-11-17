package com.cloudarchitecture.service;


import com.cloudarchitecture.dto.MemberCreateRequestDto;
import com.cloudarchitecture.dto.MemberCreateResponseDto;
import com.cloudarchitecture.dto.MemberGetDetailResponseDto;
import com.cloudarchitecture.entity.Member;
import com.cloudarchitecture.exception.MemberNotFoundException;
import com.cloudarchitecture.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberService {


    //속성
    private final MemberRepository memberRepository;


    //생성자
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //기능

    /**
     * 1. 팀원 생성
     */
    @Transactional
    public MemberCreateResponseDto memberSave(MemberCreateRequestDto request) {

        // 1. 데이터 준비
        Member member = new Member(
                request.getName(),
                request.getAge(),
                request.getMbti()
        );

        // 2. 데이터 저장
        Member savedMember = memberRepository.save(member);

        // 3. 반환
        return new MemberCreateResponseDto(
                savedMember.getId(), "팀원 정보가 성공적으로 생성되었습니다.");


    }
    @Transactional(readOnly = true)
    public MemberGetDetailResponseDto getDetailMember(Long id) {

        // 1. 조회 후 저장 값 반환 받기 (GlobalException 사용해보기 예정)
        Member saveMember = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("해당 팀원이 존재하지 않습니다.")
        );

        // 2. 반환
        return new MemberGetDetailResponseDto(
                saveMember.getId(),
                saveMember.getName(),
                saveMember.getAge(),
                saveMember.getMbti(),
                saveMember.getCreateAt(),
                saveMember.getUpdateAt()
        );

    }
}
