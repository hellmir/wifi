# 위치 기반 공공 WI-FI 정보 제공 서비스
<br>

## 프로젝트 설명
- 현재 위치에서 가장 가까운 공공 WI-FI의 위치 20곳을 알려 주는 서비스
<br><br>
## Skills and Environment

- Gradle 8.2.1
- IntelliJ IDEA
<br><br>

- Back-end
    - JDK 1.8
    - Spring Boot 2.7.14
    - SQLite
<br><br>

- Front-end
    - HTML5
    - CSS3
    - JavaScript(ES5)
<br><br>

## Server Endpoints

### WI-FI
- **공공 WI-FI 위치 정보 저장: /load-wifi** (Request method)
- **가까운 공공 WI-FI 위치 검색: /wifi?latitude=value&longitude=value** (GET method)
- **공공 WI-FI 상세정보 조회: /detail?mgrNo=value** (GET method)
<br><br>

### 히스토리(현재 위치 기록)
- **기본 페이지 렌더링: /history** (GET method)
- **히스토리 삭제: /history?id=value** (DELETE method)
<br><br>

### 북마크 그룹
- **기본 페이지 렌더링: /bookmark-group** (GET method)
  <br><br>

- **북마크 그룹 추가 페이지 렌더링: /bookmark-group-add** (GET method)
- **북마크 그룹 추가: /bookmark-group-add** (POST method)
  <br><br>

- **북마크 그룹 수정 페이지 렌더링: /bookmark-group-edit?id=value** (GET method)
- **북마크 그룹 수정: /bookmark-group-edit?id=value** (PATCH method)
  <br><br>

- **북마크 그룹 삭제 페이지 렌더링: /bookmark-group-delete?id=value** (GET method)
- **북마크 그룹 삭제: /bookmark-group-delete?id=value** (DELETE method)
<br><br>

### 북마크
- **기본 페이지 렌더링: /bookmark-list** (GET method)
  <br><br>

- **북마크 추가: /bookmark-add** (POST method)
  <br><br>

- **북마크 삭제 페이지 렌더링: /bookmark-delete?id=value** (GET method)
- **북마크 삭제: /bookmark-delete?id=value** (DELETE method)
<br><br>

## 사용 방법(영상)
https://github.com/hellmir/wifi/assets/128391669/9f5ddd06-def6-4d21-98a1-482993429d72
