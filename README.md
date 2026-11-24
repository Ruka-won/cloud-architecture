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

---

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

[이미지 다운로드 URL](https://ch4-cloud-architecutre-rukawon-files.s3.ap-northeast-2.amazonaws.com/profile-images/7/d798851e-bd68-4dd5-91cb-b7f931c44789?X-Amz-Security-Token=IQoJb3JpZ2luX2VjELn%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDmFwLW5vcnRoZWFzdC0yIkcwRQIhAM9wfdPGUpjOIzGHV0tgroHhOF6VAt4lqwvAaTTnwbdDAiB5nB2n4tB%2BLxbxskkWRCVft%2BBIJUHOkNwO540U8K3qLCrSBQiC%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDExMDcwNDExNjgzOCIMJTeS8bKppawmBTDkKqYFbFV1XLHBkjig6DqKwo066yIOmpvWFjFHgTUt5UQ6Rj%2BVH3cIY3UCXx%2FDa9fB95%2FWnYZQbXBpO0%2BUz4hVp9FyDM6zdZez4Yfo4Dj8knJPukLVijs8wH%2B1glF1itSJUOVPvhUIsNC%2Bp9aHFzSNfQNxRM0fkGePOnGkJB05fjlGpYLDMdot2nZRvCce%2FN4KQiVx4ryTaBqZ%2BVKxnL8joY0R%2BCbmAhfRb5yaG4zznh1db24H7Ip40QD5vN8cVets5ndi7Pf7ZI6jJh9DE2Fom%2BcOoaqeZNch122%2BqlVzeBmtOqA8zoXymZVUwAXOs2sXwl6ARkJQvzH2nu%2FbRyaE9fY8lq6iqGiS%2F0WdWWrwLq6buks%2FqtxkwUn159SKA2pWqO%2BdHIhJJqBTR8vUT6%2FCqIUlcj%2BfnBYZmqWFrib56i7gfM6Xg538Yv9cZPSwUD28G1iqwXlz799Wg8CgsHUs285ZkXFsGDwXnN0TYa13VH8LeJlP3J3heZuXQZLG2c%2FUfke0tTse3pFA607iDYBK2jdA4QiP0IEYLDyGTDgOecqK%2FxLJM7d%2FbDf5iym4IbRjawVjJ8v%2BUN6AcmjfqXRqlPv5%2FY4xHJtoRdFu8J%2FE8zqAEssJhZD1FQNpvZh3krwV06HGcoLjzhJeHq%2B3sWYPuPp8j9%2BwMaHBMn9CnfSsl48%2FL%2F6AYCOrTHD%2F0setdohuY0RykIMq9cqgAOHjdUYLPNBJtRhct0Jd2WnDFFQ%2FPgmKF%2FbNV1ulnVfZY7D4bUKx8EN9fWqk%2B0%2B%2F5GNjy68ElINNtPZKYkfwsGBIWcPhGMiMFMMDz8wQ%2BaYXMdRd%2FbVzSt%2FaDDYJwVpN6f5Ry52pljpxBpgZCe4EdaJAbZgtOplDQgjqFFSgbg89NFkcCyIx%2BDdKiciizJ8VMNrIk9gGOrEBeBRFmLr%2BvaE1k9GLHHPkhf9DaLxJyXOUvsvC1puNTxZiWjaGF5SPByiTf87GbRYvuiEE7dV5ID%2BHy5SkhPy4YJiddtSGqimXgRbuMj0FRvaxZpNae5%2B4gmJVICMXAkN3BxId2dtzhCUNpgjWlqbTlDEoskqL6h%2BNjIq8JUkqERqGUaJFXyrv7%2BRe1fMHVEMXP1VbvkBK1opqsU6QCp%2Bh81Dc1DFe9VmowgBB8PBhZxwR&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20261124T014438Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIARTRT2YRTALMN7ZPT%2F20261124%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=890aa0ff6994a15544ec757cf9efb927936113f75c2132e4b44c255c80141f89)
- 발급일: 2026-11-24
- 만료일: 2026-12-01
- 유효기간: 7일


**[접근성공]**
![Presigned-URL.png](images/Presigned-URL.png)

---

## Level 4 - Docker & CI/CD 파이프라인 구축

### 4-1. Docker & CI/CD
- Docker 도입
  - Dockerfile 작성
  - `docker build -t cloud-architecture-api:1.0 .` 빌드하며 태그 지정
  - `github/workflows/deploy.yml` 파일 생성

- Github Action
  - `Github -> Settings -> Secrets and variables -> Action` 키 설정
    - `AWS_ACCESS_KEY (AWS/IAM/USer)`
    - `AWS_REGION`
    - `AWS_SECRET_ACCESS_KEY`
    - `DOCKERHUB_TOKEN`
    - `DOCKERHUB_USERNAME`
    - `EC2_HOST`
    - `EC2_SSH_KEY`
- CI/CD 파이프라인
CI (Continuous Integration)
  - GitHub main 브랜치 Push 시 Workflow 실행
  - Ubuntu 환경에서 Java 17 설정
  - Gradle 빌드 및 테스트 실행

Docker Image Build & Push
  - Docker Hub 로그인
  - Docker Image 빌드
  - AMD64 / ARM64 멀티 플랫폼 이미지 생성
  - Docker Hub에 이미지 Push

CD (Continuous Deployment)
  - GitHub Actions를 통한 EC2 SSH 접속
  - Docker Hub에서 최신 이미지 Pull
  - 기존 컨테이너 중지 및 삭제
  - 신규 Docker 컨테이너 실행


### 4-2. 검증
**[test]**
![Github-Push-test.png](images/Github-Push-test.png)


### 4-3. 과제 제출 요구사항
**[Gtihub Actions]**
![Github-Actions.png](images/Github-Actions.png)

**[EC2 terminal]**
![EC2-terminal.png](images/EC2-terminal.png)