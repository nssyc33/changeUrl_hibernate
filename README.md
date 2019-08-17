프로젝트 설명
===========================

## 개요
실존하는 웹 서비스 URL을 이력 받고 가상의 URL을 생성합니다.

가상의 URL을 이용해서 입력했던 웹 서비스를 사용할 수 있습니다.

## 환경
java version : 1.5

spring version 3.2.3.RELEASE


## 소스
프로젝트명 : exSubUrl

![structure](./images/structure.png)


**■ MakeShortUrlController**

@RequestMapping("/exSubUrl/view.do") : URL 조회 및 입력 핸들러

@RequestMapping("/exSubUrl/insertData.do") : URL 입력 핸드러

@RequestMapping("/{subName}") : 대체된 URL 매핑 핸들러

**■ MakeShortUrlName**

public boolean duplicateExistsYn(HashMap asMap, ArrayList list) : 존재하는 URL인지 판단하는 함수

public String makeShortUrl(ArrayList list , int nowSec) : key 생성하는 함수

private String makeShortUrl_step1(int nowSec) : key 생성시 기준 key 생성하는 함수

private String makeshortUrlName_step2(String asIsKey) : key 생성시 존재하는 key이면 다른 key 생성하는 함수

public String getOriUrl(String searchKey, ArrayList list) : 대체 URL 호출 시 실제 URL 호출하는 함수
