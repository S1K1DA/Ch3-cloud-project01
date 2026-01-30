# CH 4 클라우드_아키텍처 설계 & 배포

> # [Spring 2기] CH 4 클라우드_아키텍처 설계 & 배포

### 스파르타 내일배움캠프 Spring 백엔드 2기

<img width="50" height="150" alt="image" src="https://github.com/user-attachments/assets/14897d9a-cf51-4d15-8e7b-01f6324da540" />

### 3조 정은식
---

## 폴더 구조

<img width="300" height="500" alt="image" src="https://github.com/user-attachments/assets/f59dd6f7-5c26-4f6a-afff-c94ef58e7fd4" />


---

> ## ✅ 필수 기능

### LV 0 - 요금 폭탄 방지 AWS Budget 설정 `필수`

> 클라우드 실습 중 가장 중요한 것은 비용 관리입니다. 실수로 고가의 리소스를 켜두는 것을 방지하기 위해 예산 알림을 설정합니다.

- **설정 요구사항**
    - AWS Budgets에서 월 예산을 **$100** 로 설정하세요.
    - 예산의 80% 도달 시 이메일 알림이 오도록 설정하세요.
<img width="2542" height="1265" alt="image" src="https://github.com/user-attachments/assets/5243fcf7-6abe-4abf-a3eb-cd7f9113a6ec" />

### LV 1 - 네트워크 구축 및 핵심 기능 배포 `필수`

안전한 네트워크 환경을 만들고, '운영 가능한 상태'의 애플리케이션을 배포하여 외부 접속을 확인합니다.

- **인프라 구축 (VPC & EC2)**
    - **VPC 설계**
        - VPC을 설정하여 Public/Private Subnet 분리한다.
    <img width="2223" height="1088" alt="image" src="https://github.com/user-attachments/assets/fcd56e8d-1604-4764-82c2-1a79e1f43d9c" />

    - **EC2 생성**
        - Public Subnet에 EC2를 생성한다.
    <img width="2048" height="1080" alt="image" src="https://github.com/user-attachments/assets/fcbf955d-281a-4ef2-aac5-7ec97ebc31f5" />
    <img width="1010" height="370" alt="image" src="https://github.com/user-attachments/assets/0cdd065f-0702-44cd-ae65-4ac3e61bf06e" />


 
    
- **애플리케이션 개발 (API & Actuator)**
    - 팀원 정보 저장 및 조회 API 개발
        - `POST /api/members`
            - 팀원의 이름, 나이, MBTI를 JSON으로 받아 저장하는 API를 만드세요.
        - `GET /api/members/{id}`
            - 저장된 팀원 정보를 조회하는 API를 만드세요.

    - **운영 설정**
        - **Profile 분리**
            - 로컬에선 `H2`를 쓰고, 운영환경에선 `MySQL`을 쓰도록 `application.properties`을 `local/prod`로 분리하세요.
            <img width="467" height="334" alt="image" src="https://github.com/user-attachments/assets/95771607-3315-40e8-8549-c9966ea5b2d9" />

        - 로그 전략
            - API 요청이 들어올 때마다 `INFO` 레벨로 "[API - LOG] xx” 과 같은 로그를 남기세요.
            - 예외 처리를 구현하고, 에러 발생 시에는 `ERROR` 레벨로 스택트레이스를 남기세요.
    - **상태 모니터링 (Actuator) 추가**
        - `spring-boot-starter-actuator` 의존성을 추가하세요.
        - 설정파일에 헬스 체크 엔드포인트를 노출하세요
        
        ```graphql
        management.endpoints.web.exposure.include=health
        ```
        
- **배포 및 검증**
    - EC2에 프로젝트를 배포하고 실행하세요.
    - 로컬에서 `http://localhost:8080/actuator/health` 접속 시 아래와 같은 응답을 받아야 합니다.
 
    <img width="1338" height="1018" alt="image" src="https://github.com/user-attachments/assets/ce9790e7-92ee-4666-b488-e0b7650b132c" />

    
    ```java
    {"status": "UP"}
    ```

    <img width="1923" height="1077" alt="image" src="https://github.com/user-attachments/assets/9355e004-08a5-409f-a549-d2d9e778fedb" />
    <img width="1298" height="430" alt="image" src="https://github.com/user-attachments/assets/2d1fe5b1-ee19-4059-a8ac-918f534e09dc" />


    
### LV 2 - DB 분리 및 보안 연결하기 `필수`

> 로컬 테스트가 끝났으니 실제 배포를 진행합니다. 하지만 DB 비밀번호를 코드에 적을 순 없죠? AWS의 관리형 서비스를 이용해 안전하게 배포해봅시다.

- **인프라 요구사항**
    - **RDS 구축**
        - 로컬 접속용 Public Subnet 에 MySQL RDS를 만드세요. 
        (로컬 PC에서 접속 테스트가 가능해야 함)
    <img width="2170" height="487" alt="image" src="https://github.com/user-attachments/assets/95ed5c3e-2dd9-48dc-8750-c3aaa8a49fb7" />
    <img width="1178" height="1030" alt="image" src="https://github.com/user-attachments/assets/fa66333d-f8ad-4517-84b2-e0c6577b2dda" />


    - **보안 그룹 체이닝**
        - RDS의 보안 그룹(Inbound)에는 IP 주소를 적지 말고, 
        **Step 1에서 만든 EC2의 보안 그룹 ID**만 허용하세요. (EC2 ↔ RDS 연결 보장)
        <img width="1816" height="272" alt="image" src="https://github.com/user-attachments/assets/ec7ba7e8-3b89-44f5-968a-615e9094c368" />
  
    - **Parameter Store**
        - DB 접속 정보(`url`, `username`, `password`)와 **확인용 파라미터**를 저장.
        <img width="1255" height="605" alt="image" src="https://github.com/user-attachments/assets/1d4d097b-513d-45ac-9651-734af626b96e" />

- **애플리케이션 요구사항**
    - Spring Boot 실행 시 Parameter Store 값을 주입받아 RDS에 연결.
    - **Actuator Info 확장**
        - Parameter Store에 저장한 `team-name` 값이 `/actuator/info` 엔드포인트에서 
        조회되도록 설정하세요.
- **검증**
    - `http://{EC2_Public_IP}:8080/actuator/info` 접속 시, Parameter Store에
    저장한 팀 이름이 출력되어야 합니다.
  <img width="2000" height="856" alt="image" src="https://github.com/user-attachments/assets/c1cc9aa5-070a-46dc-86f0-6ffb4303c191" />
  <img width="2457" height="1093" alt="image" src="https://github.com/user-attachments/assets/ee817b49-10ff-431c-8ebf-c0677a8f6e68" />


- **과제 제출 요구사항**
    1. **Actuator Info 엔드포인트 URL**
        - `/actuator/info`에 접속했을 때, Parameter Store에 저장했던 또는 확인용 파라미터 값이 JSON으로 출력되는 URL을 README.md에 작성하세요
      http://43.203.229.185:8080/actuator/info
  
    2. **RDS 보안 그룹 스크린샷**
        - AWS 콘솔 > RDS > 보안 그룹 > **[인바운드 규칙]** 탭을 캡처하세요.
        - 소스(Source) 부분에 IP 주소(`0.0.0.0/0`)가 아닌, EC2의 보안 그룹 ID (`sg-xxxxx`)가 등록되어 있음을 보여주어야 합니다.
       <img width="1816" height="272" alt="image" src="https://github.com/user-attachments/assets/e1a1b5f8-2eb2-41b1-a822-a3805955af69" />

### LV 3 - 프로필 사진 기능 추가와 권한 관리 `필수`

> 팀원 정보에 "프로필 사진" 기능이 추가되었습니다. 서버 디스크에 저장하면 서버가 죽을 때 사진도 
날아가니, S3를 써야 합니다.

- **인프라 요구사항**
    - **S3 버킷 생성**
        - **"모든 퍼블릭 액세스 차단"** 설정을 켜고 버킷을 생성하세요.
    - **IAM Role**
        - Access Key를 코드에 넣지 말고, S3 접근 권한이 있는 **IAM Role**을 생성해 
        EC2에 연결하세요.
- **API 요구사항**
    - `POST /api/members/{id}/profile-image`
        - MultipartFile로 이미지를 받아 S3 버킷에 업로드하고, 이미지 URL을 DB에 업데이트하는 기능을 추가하세요
    - `GET /api/members/{id}/profile-image`
        - Presigned URL 을 생성하여 반환하세요. 클라이언트는 이 URL을 통해서만 이미지를 다운로드할 수 있어야 합니다.
        - Presigned URL의 유효기간을 꼭 7일로 설정해주세요
- **과제 제출 요구사항**
    
    <aside>
    💡
    
    Presigned URL은 유효기간이 지나면 접근이 불가능합니다. 채점을 위해 **과제 제출일 
    기준 유효한 URL**을 새롭게 생성해야 합니다. 
    
    🚨 **유효기간 7일 설정 필수!**
    
    </aside>
    
    - 발급받은 **Presigned URL 1개**와 해당 URL의 만료 시간을 README.md에 기재하세요.

       
