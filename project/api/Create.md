# 1. 팀원 생성

* **Domain**: members
* **API URL**: `api/members`
* **Method**: `POST`
* **Content-Type** : `application/json`
* **Description**: 팀원 정보 저장을 위한 API 생성
---

## 2. 요청 (Request)

### Request Body (JSON)
```json

{
  "name": "Jason Tatum",
  "age": 28,
  "mbti" : "ESTJ"
}
```
### Request Body 명세
| Field | Essential (Y/N) | DataType | description | 
| :---: | :---: | :---: | :---: | 
| name | Y | String | 팀원 이름 |
| age | Y | int | 팀원 나이 | 
| mbti | Y | String | 성격 유형 검사 종류 | 

<br>

---

## 3. 응답(Response)

### Response Body (JSON)
Status Code : 201 Created
```Json
{
  "id" : 1,
  "message" : "팀원 정보가 성공적으로 생성되었습니다.",
  "createAt" : "20261113T",
  "modifiedAt" : "20261113T"
}
```
### Response Body 상세 설명
| Field | DataType | description |
| :---: | :---: | :---: |
| id | Long | 고유 식별값 |
| message | String | 생성 확인 유무 메시지 |
| modifiedAt | LocalDateTime | 정보 생성일 |
| modifedAt | LocaldateTime | 정보 수정일 |

<br>

---
## 4. 에러 응답
```json
{
 
}
```
| Field | DataType | description |
| :---: | :---: | :---: |
| - | - | - |
| - | - | - |
