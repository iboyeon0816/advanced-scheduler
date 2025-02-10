# 일정 관리 앱 Develop
사용자가 자신의 일정을 관리할 수 있도록 돕는 일정 관리 애플리케이션을 개발하는 프로젝트이다. 

Spring 프레임워크를 기반으로 하며, JPA를 이용한 데이터베이스 연동과 쿠키 및 세션을 활용한 인증 및 인가 기능을 구현하는 것을 목표로 한다.

<br>

## 개발 환경
- **언어**: Java 17
- **프레임워크**:  Spring (Spring Boot 3.4.2)
- **IDE**: IntelliJ IDEA
- **빌드 도구**: Gradle
- **데이터베이스**: MySQL

<br>

## 프로젝트 구조
```
src/
├── main/
│   └── java/com/example/scheduler2/
│   │   ├── auth/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── domain/
│   │   ├── dto/
│   │   ├── exception/
│   │   ├── repository/
│   │   ├── service/
│   │   └── Scheduler2Application.java
│   └── resources/
│   │   └── application.properties
``` 

<br>

## 프로젝트 실행 방법
### 1. GitHub에서 프로젝트 클론
터미널에서 아래 명령어를 통해 프로젝트를 클론한다.
```bash
git clone https://github.com/iboyeon0816/advanced-scheduler.git
cd scheduler
```

### 2. 데이터베이스 연결 정보 설정
`src/main/resources` 디렉토리에 `application-SECRET.properties` 파일을 생성하고, 아래 내용을 추가한다.
```properties
spring.datasource.password=your_db_password
```

### 3. 데이터베이스 생성
MySQL에서 `schedule.sql` 내 스크립트를 실행하여 필요한 데이터베이스 및 테이블을 생성한다.
- `schedule.sql` 파일은 프로젝트 루트에 위치한다.

### 4. 애플리케이션 실행
IntelliJ에서 프로젝트를 열고, `SchedulerApplication.java` 파일을 찾아 메인 메소드를 실행한다.
- 우클릭 > Run 'SchedulerApplication'을 클릭하여 애플리케이션을 실행한다.
- 애플리케이션이 실행되면 `http://localhost:8080`에서 API를 확인할 수 있다.

<br>

## 주요 기능
- **일정 CRUD 기능**: 사용자가 일정을 생성, 조회, 수정, 삭제할 수 있다.
- **사용자 CRUD 기능**: 사용자 계정을 생성, 조회, 수정, 삭제할 수 있다.
- **댓글 CRUD 기능**: 사용자는 일정에 대해 댓글을 생성, 조회, 수정, 삭제할 수 있다.
- **사용자 인증 및 인가**: 쿠키 및 세션을 이용하여 로그인 및 접근 제어를 구현한다.

<br>

## ERD
<img width="600" alt="erd" src="https://github.com/user-attachments/assets/9abe082f-885b-46ab-9df5-d0b67fec3682" />

<br>
<br>

## Database Schema
```sql
DROP DATABASE IF EXISTS scheduler2;
CREATE DATABASE scheduler2;

USE scheduler2;

CREATE TABLE user (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(10) NOT NULL,
  email VARCHAR(30) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP
);

CREATE TABLE schedule (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  title VARCHAR(30) NOT NULL,
  contents VARCHAR(100),
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);

CREATE TABLE comment (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  schedule_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  contents VARCHAR(100) NOT NULL,
  created_at TIMESTAMP,
  updated_at TIMESTAMP,
  FOREIGN KEY(schedule_id) REFERENCES schedule(id) ON DELETE CASCADE,
  FOREIGN KEY(user_id) REFERENCES user(id) ON DELETE CASCADE
);
```

<br>

## API 명세서
[API 명세서 보기](https://documenter.getpostman.com/view/28485807/2sAYX9mzX9)
