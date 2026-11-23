# 📗 CH4 클라우드 아키텍처 설계&배포 과제 

⭐ 네트워크를 구축, DB와 파일 저장소를 분리하여 서버가 죽어도 안전한 Stateless 아키텍처 완성 

프로젝트 기록 : [Velog](https://velog.io/@datamong/AWS-CH4-%ED%81%B4%EB%9D%BC%EC%9A%B0%EB%93%9C-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%84%A4%EA%B3%84-%EB%B0%8F-%EB%B0%B0%ED%8F%AC-%EA%B3%BC%EC%A0%9C)

---

## Level 0 -  AWS Budgets 설정

### 0-1. 설정 요구 사항
- 월 예산 &100 설정
- 예산 80& 도달 시 이메일 알람 설정<br>

### 0-2.과제 제출 요구사항

#### <설정완료 이미지 첨부>
![img.png](images/Budget예산설정.png)

#### <예산 알림 설정>
![img_1.png](images/Budget예산알림설정.png)

---

## Level 1 -  네트워크 구축 및 핵심 기능 배포
### 1-1. 인프라 구축

- VPC 설계 (Public/Private 분리 설정)

![VPC-설계.png](images/VPC-%EC%84%A4%EA%B3%84.png)

- EC2 생성 (Public subnet 1번으로 설정)
  - 인스턴스명 : CH4-Cloud-Architecture
  - 인스턴스 유형 : t4g.small
  - 가용 영역 : ap-northeast-2a
### 1-2. 애플리케이션
- 팀원 정보 저장 및 조회 API 개발 (생성 및 조회)
  - 파일경로 `project/api` API 및 테이블 명세서 작성


- 운영 설정(Profile 분리 및 로그 전략)
  - `application.yml` 형식
  - 로컬 H2 테스트 DB 사용
  - 운영 환경 MySQL 사용
  - 공용 / local / prod 구조로 분리 
  - 로그 전략 (interceptor 및 GlobalException 구현)


- 상태모니터링 (Actuator)
  - build.gradle 의존성 추가
  - yml 엔트포인트 설정 (health)


- 배포 및 검증
  - `active = local` 설정하여 배포 검증 완료


### 1-3. 배포 및 검증

**[EC2 배포 검증]**
  
![EC2-배포-검증.png](images/EC2-%EB%B0%B0%ED%8F%AC-%EA%B2%80%EC%A6%9D.png)


**[EC2 Public IP]**

![EC2-Public-IP.png](images/EC2-Public-IP.png)

---

## Level 2 - DB 분리 및 보안 연결하기

### 2-1. 인프라 요구사항
#### RDS 구축

Actuator info URL : http://3.35.13.245:8080/actuator/info

  - 엔진 옵션 : MySQL
  - 인스턴스 크기 : 프리티어(샌드박스) 설정
  - 자격 증명 관리 : 자체 관리로 설정
  - 초기 데이터베이스 이름 : CH4
  - 퍼블릭 액세스 : “예”
  - 기존 VPC 보안 그룹 : CH4-sg 설정
  - 보안 그룹 인바이트 추가 (3306)

  ![RDS구축.png](images/RDS%EA%B5%AC%EC%B6%95.png)

#### 보안 그룹 체이닝
  ![RDS보안그룹.png](images/RDS%EB%B3%B4%EC%95%88%EA%B7%B8%EB%A3%B9.png)

#### Parameter store
- AWS Parameter Store (url, name, password) 값 생성
  - `/cloud-architecture-app/prod/**` 이름 설정 

### 2-2. 애플리케이션 요구사항
- Spring Boot 실행 Parameter Store 값 주입받아 RDS 연결

- Actuator info 확장
  - 공통 파일에 endpoint 설정 및 info `team-name` 외부 주입 설정
  - Prod 파일에 import 주소 설정

### 2-3. 검증
![Actuator-info-검증.png](images/Actuator-info-%EA%B2%80%EC%A6%9D.png)

![인바운드규칙.png](images/%EC%9D%B8%EB%B0%94%EC%9A%B4%EB%93%9C%EA%B7%9C%EC%B9%99.png)




## Level 3 - 프로필 사진 기능 추가와 권환 관리

### 3-1. 인프라 요구사항

- S3 버킷 생성
  - ![s3-public-access.png](images/s3-public-access.png)
- IAM Role, IAM Policy
  - ![IAM-Role-Policy.png](images/IAM-Role-Policy.png)

### 3-2. API 요구사항
- `S3Client` 이용하여 S3 버킷 업로드
- `S3Presinger` Presigned URL 생성
- `Duration.ofDays(7)` 유효기간 7일 설정
- 파일경로 `project/api` API 명세서 작성

### 3-3. 과제 제출 요구사항
**[Presigned URL]**

[이미지 다운로드 URL](https://ch4-cloud-architecutre-rukawon-files.s3.ap-northeast-2.amazonaws.com/profile-images/6/cb5edb9f-8ee8-4d31-a846-fb2bbdaa5ef4?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEKL%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDmFwLW5vcnRoZWFzdC0yIkYwRAIgKU7XMX%2BWm1S6Duy3epO0YT3TVZoCxPXBVlyCVwF0tNcCIGsOk%2B9EyVvlEdTNIAfEvvF0U8TPZbrQF3RzF8NuIWQbKskFCGsQABoMMTEwNzA0MTE2ODM4Igz4TnIte7ClER%2B0dfAqpgWcNSiVMqePKilYXDPSAFxMIInndtUe%2BC9M%2FudjyFc4mf3B9ixkVG9tHwiK5TtACFCm5OSCyJAFMnzg7cEv4ADmEHGmjIA29ktXTPiEahexn0Wzf4kBdd6aPu%2Bv49D56MYA8ZUE%2B8MdvHzPHOHGH7VgYkIPkdmkbp9D%2BBLi1CLOb4HzRPWC3qMo6O1sr%2BmQrEMjh7GMW6UV9GKXQ3dBpBL7nMfP%2FYpKBRr3iDYIo3GpzrsdASWqvtGxLm0dzGnRRFrtSDH1OaUbVTYQTrVeH1mmPi0CAWbszQeHW6McHS%2FtcR9raK8ygUaLonl9fJh14cgfTR6QucSt%2FyQjPrb9R%2FaHh%2Ft2ReMcyaeXN2TZMPlZ6dTaLunV%2B%2FONyKBJGd0o9o%2BigTnWMpGmJnrZgLDUsn67CdC%2Bl9fDppCRbiXhg1PHi1TzD6DDGiqPKn9yEdxzPm%2Bzd%2BDbY0R8mk4bg0TnzvQeo16SH7GrCEI7HA%2FOPT6pDkSYf%2BkpDQrAdJmJU3NdxE2SUM2C1iFQHSKU7zJ68Lzo3%2Fyvevn8EkuVukGbNfFs4LRloQNq7Dr%2B1%2Bfe1FKc4FMKf7wI2%2Fcf4j3nsAlS85JdCq1oxDxn9V1NJlaikSmU%2FXnQGy1bktDqRSJXikOroIIr299RZ6AUkhF%2FG4WrH0HmquQ3G0mXbdQ6RAtveRnnjJwcd0oEu38TOTPKDGJspMxSi1T5ip5HxqgJ%2BmROPqKzwmZIlgk1NcwCLXfwvasgGbZKkLal8vb7z%2Fhkiu4cgl43xUqY7XSjpbm6zt%2F44btN%2Bs4fnS1WkNjpbc8%2FOa5G9l%2FnaDgk106HikgoHUEFmCcL25pfoQKsSOcRgpRl3e3Cs8o0aJdsZLF6n9onT%2BnULf0CHbFS%2B9p8HimLqd64NHMBjKrKuZ4wgryO2AY6sgFSPaCU0osyOuiumTaveczUa6i1nmAlQQf7ZSV6oIHi%2BhvgDQ4uQVJyofmUaJ%2BfwE1OhmxLd96blntdyXwnTqAB%2FyZ%2B4fVfMgOITWMGGUJLfd2Y7NkVngUX4%2BvWdg2CVFN8yQBZwaL3HefWle%2FajfcUxOtI25%2BzblyFCHRobvSCcwTe%2FoQqHpYAT0iquqi54QpywXS4iuVa4QD3Dw5YGp5PiPekFcuDpIVUlV619YgECTYx&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20261123T021333Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIARTRT2YRTKAUOEM3Z%2F20261123%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=b6098e82bcd59967435819a54659b623d0f6ee131ec2f75a9de3fe6348c00a5e)
- 발급일: 2026-11-23
- 만료일: 2026-11-30
- 유효기간: 7일


**[접근성공]**
![Presigned-URL.png](images/Presigned-URL.png)


## Level 4 - Docker & CI/CD 파이프라인 구축

### 4-1. Docker & CI/CD
- Docker 도입
  - Dockerfile 작성
  - `docker build -t cloud-architecture-api:1.0 .` 빌드하며 태그 지정
  - `github/workflows/deploy.yml` 파일 생성

- Github Action CI/CD
  - `Github -> Settings -> Secrets and variables -> Action` 키 설정
- CI
  - `on : push : branches : main` 실행 지정 부분 설정
  - `Ubuntu` 실행 환경을 통해 테스트



### 4-2. 검증


### 4-3. 과제 제출 요구사항
