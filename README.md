# MyBatis Board MVC Practice

## ✨ 개요

이 프로젝트는 **MyBatis**를 활용하여 **MVC 패턴**을 연습하기 위한 콘솔 기반 게시판 애플리케이션입니다. 사용자는 게시글을 **작성, 조회, 수정, 삭제, 검색**할 수 있습니다.

## ✨ 사용 기술

- **Java (JDK 8 이상)**: 프로젝트의 기본 언어
- **MyBatis**: SQL 매퍼 프레임워크로 DB 연동 처리
- **JDBC (Oracle DB)**: 데이터베이스 연결 및 쿼리 실행
- **MVC 패턴**: Model-View-Controller 구조로 설계
- **DAO 패턴**: 데이터 접근 객체를 분리하여 캡슐화
- **Singleton 패턴**: Service 및 DAO 클래스의 인스턴스 관리
- **XML 기반 MyBatis 설정**: `mybatis-config.xml`, `board-mapper.xml` 활용

## ✨ 프로젝트 구조

```
src/
├── board/controller/BoardController.java   // 사용자 입력 및 실행 흐름 관리
├── board/service/IBoardService.java        // 서비스 인터페이스
├── board/service/BoardServiceImpl.java     // 서비스 구현체
├── board/dao/IBoardDao.java                // DAO 인터페이스
├── board/dao/BoardDaoImpl.java             // DAO 구현체 (DB 연동)
├── board/vo/BoardVO.java                   // 게시글 정보를 담는 Value Object
├── board/util/MyBatisUtil.java             // MyBatis 설정 및 SqlSession 관리
resources/
├── mybatis/config/mybatis-config.xml       // MyBatis 전체 설정 파일
├── mybatis/mappers/board-mapper.xml        // SQL 쿼리 매핑 파
```

## ✨ 주요 기능

1. **게시글 작성**
    - 제목, 작성자, 내용 입력 후 DB에 저장
2. **게시글 조회**
    - 게시글 번호 입력 후 상세 정보 출력
    - 조회수 증가 기능 포함
3. **게시글 수정**
    - 제목 및 내용 수정 가능
4. **게시글 삭제**
    - 특정 번호의 게시글 삭제
5. **게시글 검색**
    - 제목을 기준으로 검색 후 결과 출력
6. **전체 게시글 조회**
    - 모든 게시글을 목록 형태로 출력

## 실행 방법

1. **데이터베이스 설정**
    - `db.properties` 파일을 설정하여 Oracle DB 접속 정보를 입력
    - `JDBC_BOARD` 테이블 생성 (컬럼: `BOARD_NO`, `BOARD_TITLE`, `BOARD_WRITER`, `BOARD_CNT`, `BOARD_CONTENT`, `BOARD_DATE`)
2. **프로젝트 빌드 및 실행**
    
    ```
    javac -d bin src/board/**/*.java
    java -cp bin board.controller.BoardControlle
    ```
    

## ✨ 데이터베이스 테이블 (예제)

```sql
CREATE TABLE JDBC_BOARD (
    BOARD_NO NUMBER PRIMARY KEY,
    BOARD_TITLE VARCHAR2(200) NOT NULL,
    BOARD_WRITER VARCHAR2(100) NOT NULL,
    BOARD_CNT NUMBER DEFAULT 0,
    BOARD_CONTENT CLOB,
    BOARD_DATE DATE DEFAULT SYSDATE
);
```

