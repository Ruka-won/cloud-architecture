# 1. 팀원 정보 상세 조회

* **Domain**: members
* **API URL**: `api/members/{id}`
* **Method**: `GET`
* **Content-Type** : `application/json`
* **Description**: 팀원 정보 상세 조회 API
---

## 2. 요청 (Request)

### a. Parameter & QueryString
```json
example -> http://localhost:8080/api/members/{id}

http://localhost:8080/api/members/1
```
| Field | Essential (Y/N) | DataType | description | 
| :---: | :---: | :---: | :---: | 
| id | Y | Long | 팀원 정보 고유 식별자 |
| - | - | - | - | 
| - | - | - | - | 



### b. Request Body (JSON)
```json

{
  
  
}
```
### Request Body 명세
| Field | Essential (Y/N) | DataType | description | 
| :---: | :---: | :---: | :---: | 
| - | - | - | - |
| - | - | - | - | 
| - | - | - | - | 

<br>

---

## 3. 응답(Response)

### Response Body (JSON)
Status Code : 201 Created
```Json
{
  "id" : 1,
  "name" : "Jason Tatum",
  "age": "28",
  "mbti": "ESTJ",
  "createAt" : "20261113T",
  "modifiedAt" : "20261113T"
}
```
### Response Body 상세 설명
| Field | DataType | description |
| :---: | :---: | :---: |
| id | Long | 고유 식별값 |
| name | String | 팀원 이름 |
| age | int | 팀원 나이 |
| mbti | String | 성격 유형 검사 종류 |
| modifiedAt | LocalDateTime | 정보 생성일 (yyyymmdd) |
| modifedAt | LocaldateTime | 정보 수정일 (yyyymmdd) |

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
