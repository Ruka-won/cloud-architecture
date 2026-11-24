# 4. 이미지 조회

* **Domain**: members
* **API URL**: `api/members/{id}/profile-image`
* **Method**: `GET`
* **Description**: 이미지 조회 
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

<br>

---

## 3. 응답(Response)

### Response Body (JSON)
Status Code : 200 Ok
```Json
{
    "imageUrl": "https://ch4-cloud-architecutre-rukawon-files.s3.ap-northeast-2.amazonaws.com/profile-images/6/cb5edb9f-8ee8-4d31-a846-fb2bbdaa5ef4?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEKL%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaDmFwLW5vcnRoZWFzdC0yIkYwRAIgKU7XMX%2BWm1S6Duy3epO0YT3TVZoCxPXBVlyCVwF0tNcCIGsOk%2B9EyVvlEdTNIAfEvvF0U8TPZbrQF3RzF8NuIWQbKskFCGsQABoMMTEwNzA0MTE2ODM4Igz4TnIte7ClER%2B0dfAqpgWcNSiVMqePKilYXDPSAFxMIInndtUe%2BC9M%2FudjyFc4mf3B9ixkVG9tHwiK5TtACFCm5OSCyJAFMnzg7cEv4ADmEHGmjIA29ktXTPiEahexn0Wzf4kBdd6aPu%2Bv49D56MYA8ZUE%2B8MdvHzPHOHGH7VgYkIPkdmkbp9D%2BBLi1CLOb4HzRPWC3qMo6O1sr%2BmQrEMjh7GMW6UV9GKXQ3dBpBL7nMfP%2FYpKBRr3iDYIo3GpzrsdASWqvtGxLm0dzGnRRFrtSDH1OaUbVTYQTrVeH1mmPi0CAWbszQeHW6McHS%2FtcR9raK8ygUaLonl9fJh14cgfTR6QucSt%2FyQjPrb9R%2FaHh%2Ft2ReMcyaeXN2TZMPlZ6dTaLunV%2B%2FONyKBJGd0o9o%2BigTnWMpGmJnrZgLDUsn67CdC%2Bl9fDppCRbiXhg1PHi1TzD6DDGiqPKn9yEdxzPm%2Bzd%2BDbY0R8mk4bg0TnzvQeo16SH7GrCEI7HA%2FOPT6pDkSYf%2BkpDQrAdJmJU3NdxE2SUM2C1iFQHSKU7zJ68Lzo3%2Fyvevn8EkuVukGbNfFs4LRloQNq7Dr%2B1%2Bfe1FKc4FMKf7wI2%2Fcf4j3nsAlS85JdCq1oxDxn9V1NJlaikSmU%2FXnQGy1bktDqRSJXikOroIIr299RZ6AUkhF%2FG4WrH0HmquQ3G0mXbdQ6RAtveRnnjJwcd0oEu38TOTPKDGJspMxSi1T5ip5HxqgJ%2BmROPqKzwmZIlgk1NcwCLXfwvasgGbZKkLal8vb7z%2Fhkiu4cgl43xUqY7XSjpbm6zt%2F44btN%2Bs4fnS1WkNjpbc8%2FOa5G9l%2FnaDgk106HikgoHUEFmCcL25pfoQKsSOcRgpRl3e3Cs8o0aJdsZLF6n9onT%2BnULf0CHbFS%2B9p8HimLqd64NHMBjKrKuZ4wgryO2AY6sgFSPaCU0osyOuiumTaveczUa6i1nmAlQQf7ZSV6oIHi%2BhvgDQ4uQVJyofmUaJ%2BfwE1OhmxLd96blntdyXwnTqAB%2FyZ%2B4fVfMgOITWMGGUJLfd2Y7NkVngUX4%2BvWdg2CVFN8yQBZwaL3HefWle%2FajfcUxOtI25%2BzblyFCHRobvSCcwTe%2FoQqHpYAT0iquqi54QpywXS4iuVa4QD3Dw5YGp5PiPekFcuDpIVUlV619YgECTYx&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20261123T021333Z&X-Amz-SignedHeaders=host&X-Amz-Credential=ASIARTRT2YRTKAUOEM3Z%2F20261123%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Expires=604800&X-Amz-Signature=b6098e82bcd59967435819a54659b623d0f6ee131ec2f75a9de3fe6348c00a5e"
}
```
### Response Body 상세 설명
| Field | DataType | description |
| :---: | :---: | :---: |
| imageUrl | String | 유효기간 7일 Presigned URL |

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
