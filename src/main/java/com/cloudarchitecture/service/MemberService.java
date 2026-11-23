package com.cloudarchitecture.service;


import com.cloudarchitecture.dto.*;
import com.cloudarchitecture.entity.Member;
import com.cloudarchitecture.exception.MemberNotFoundException;
import com.cloudarchitecture.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {


    //속성
    private final MemberRepository memberRepository;
    private final S3Service s3Service;


    //생성자
    public MemberService(MemberRepository memberRepository, S3Service s3Service) {
        this.memberRepository = memberRepository;
        this.s3Service = s3Service;
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
    // 2. 팀원 조회
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

    // 3. 이미지 저장
    @Transactional
    public CreateImageResponseDto saveImage(Long id, CreateImageRequestDto request) {

        // 1. 팀원 존재 유무 확인
        Member saveMember = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("해당 팀원이 존재하지 않습니다.")
        );

        // 2. S3 이미지 업로드
        String imageKey = s3Service.uploadImage(saveMember.getId(), request.getFile());

        // 3. 회원 Entity에 이미지 Key 반영
        saveMember.updateProfileImage(imageKey);

        // 4. DB 저장
        memberRepository.save(saveMember);

        // 5. 반환
        return new CreateImageResponseDto(saveMember.getId(), "프로필 이미지 업로드가 완료되었습니다.");

    }
    // 4. 이미지 조회 
    public GetImageResponseDto getProfileImage(Long id) {

        // 1. 팀원 존재 유무 확인
        Member saveMember = memberRepository.findById(id).orElseThrow(
                () -> new MemberNotFoundException("해당 팀원이 존재하지 않습니다.")

        );

        // 2. ImageKey 가져오기
        String imageKey = saveMember.getProfileImageKey();

        // 3. PresignedUrl 생성
        String imageUrl = s3Service.generatePresignedUrl(imageKey);

        // 4. 반환
        return new GetImageResponseDto(imageUrl);


        
                
                
                
    }
}
