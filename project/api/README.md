# 📝 API 명세서 (API Documentation)

### ✅ 팀원 정보 저장 및 조회 API 명세서

### 테이블 명세서
테이블명 : `members`

| 컬럼명 | 데이터 타입 | 기본값 | 키 종류 | 제약 조건 | 설명 |
| :---: | :---: | :---: | :---: | :---: | :---: | 
| id | BIGINT | AUTO_INCREMENT | PK | NOT NULL | 팀원 정보 고유 식별자 |
| name | VARCHAR(255) | - | - | NOT NULL | 팀원 이름 |
| age | Integer | - | - | NOT NULL | 팀원 나이 |
| mbti | VARCHAR(255) | - | - | NOT NULL | 성격 유형 검사 종류 |
 

---

### API 명세서
| 기능 | HTTP Method | URL | status Code|
| :---: | :---: | :---: | :---: |
| 팀원 생성 | POST | /api/members | 201 Created |
| 팀원 정보 상세 조회 | GET | /api/members/{Id}| 200 OK |
