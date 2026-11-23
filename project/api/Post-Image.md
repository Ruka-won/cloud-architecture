# 3. 이미지 저장 

* **Domain**: members
* **API URL**: `api/members/{id}/profile-image`
* **Method**: `POST`
* **Content-Type** : `multipart/form-data`
* **Description**: 이미지 저장을 위한 API 생성
---

## 2. 요청 (Request)

### a. Parameter & QueryString
```json
example -> http://3.35.13.245:8080/api/members/{id}/profile-image

http://3.35.13.245:8080/api/members/1/profile-image
```
| Field | Essential (Y/N) | DataType | description | 
| :---: | :---: | :---: | :---: | 
| id | Y | Long | 팀원 정보 고유 식별자 |
|  | - | - | - | 
| - | - | - | - | 


### b. Request Body (JSON)
```json

{
 "file" : profile.png
}
```
### Request Body 명세
| Field | Essential (Y/N) | DataType | description | 
| :---: | :---: | :---: | :---: | 
| file | Y | MultipartFile | 업로드할 이미지 파일 |
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
  "message" : "프로필 이미지 업로드가 완료되었습니다."
}
```
### Response Body 상세 설명
| Field | DataType | description |
| :---: | :---: | :---: |
| id | Long | 팀원 고유 식별값 |
| message | String | 생성 확인 유무 메시지 |

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
