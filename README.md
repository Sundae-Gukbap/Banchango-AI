# Capstone_RecipeRecommander
딥러닝 기반 레시피 추천 및 식자재 관리 어플리케이션 프로젝트 입니다.

## 프로젝트 소개
주제 선정에 앞서 식자재와 관련한 자취생의 어려움을 확인하기 위해 설문조사를 진행하였고, 주제 선정의 근거가되는 응답을 다음과 같이 확인하였습닌다.

### 주제 선정 이유
<img src="https://github.com/user-attachments/assets/fcd14452-e446-45aa-84ee-1e2284106467" width="600"/><br>
응답자중 32.6%가 30% 이상의 식자재를 사용하지 않고 버린다고 응답하였습니다.<br><br>

<img src="https://github.com/user-attachments/assets/f00e4106-e7fd-4a1e-8895-23f9a5edf108" width="600"/><br>
그 중 82%나 되는 상당수의 응답자가 소비기한을 넘겼다고 응답했고, 어떤 요리를 해야할지 모르는 경우도 꽤 있었습니다.<br><br>

<img src="https://github.com/user-attachments/assets/09d8e73b-9865-496d-a9c4-20527f9c45b9" width="600"/><br>
소비기한을 넘긴 이유는 체계적으로 관리하지 못하고, 기억과 감에 의존하기 때문이었습니다.<br><br>

### 주제 및 요구사항
설문조사에서 확인한 자취생들의 식자재 관리에 대한 어려움을 해결하기 위해 다음과 같은 요구사항을 포함한 주제를 선정하였습니다.<br>

- 주제
  - 딥러닝 기반 레시피 추천 및 식자재 관리 어플리케이션
<br>

- 요구사항
  - 재료관리
    - 보관될 식자재를 유통기한과 함께 등록하고 일괄적으로 조회합니다.
  - 레시피 추천
    - 레시피를 학습한 AI를 활용해 현재 보관하고 있는 식자재가 사용되는 레시피를 추천합니다.

## 개발 환경
### AI
- Pytorch
  - Python기반 딥러닝 라이브러리로서 베이스라인 코드를 기반으로 다양한 머신러닝 추천시스템 기
법을 이용할 예정. 그 밖에 다양한 라이브러리(LightGBM, UltraGCN, CatBoost)를 사용해서 다양한 앙상블
기법등을 이용하여 성능을 올릴 예정.
- FastAPI 0.110.3
  - Starlette(비동기 웹 프레임워크)와 Pydantic(데이터 유효성 검사 도구)을 기반으로 하여 매우 빠름. 이는 A
PI를 통해 대량의 요청을 처리해야 하는 AI 시스템에 적합.
### 서버
- 의존성
  - Java 17
    - 2021년 9월에 출시된 장기 지원(LTS) 버전으로서, 오라클로부터 8년간의 지원을 받고 있음
  - Springboot 3.0.5
    - 인기있는 Java 애플리케이션 프레임워크
    - Java17이 기본 Java로 지원
    - javax → jakarta로 namespace 완전 이전을 지원
    - Spring MVC 모듈을 사용하기에 매우 적합
  - Spingdoc 2.3.0
    - OpenAPI3.0 규격을 완벽히 지원한 RESTful API 문서 자동화 도구
  - Lombok
    - Java 개발 과정에서 보일러플레이터 제거를 위한 어노테이션 사용
    - Java 코드의 가독성을 높이고 반복적인 코드를 줄여 생산성을 향상시키기 위해 사용
- DB
  - MariaDB 
    - RDBMS인 MariaDB를 사용하여 사용자의 남은 식재료, 직접 작성한 레시피등에 대한 데이터들을 관리
    - 오픈 소스 관계형 데이터베이스 관리 시스템(RDBMS)
    - MySQL과 높은 호환성을 가지며 보다 향상된 기능을 가짐
    - 암호화, 역할 기반 액세스 제어 등의 기능 추가로 인한 보안 강화
- IDE
  - IntelliJ IDEA
    - JetBrains에서 개발한 Java 중점의 통합 개발 환경
    - 다양한 플러그인 제공
    - 섬세한 디버깅 가능
    - 스마트 코드 편집기(코드 자동 완성, 심볼 검색, Navigation 등)
- Deploy
  - Amazone Web Service에서 클라우드 컴퓨팅을 사용한 서버 및 RDS 배포

### 클라이언트
- Android
  - 안드로이드의 새로운 뷰 체계 Compose 를 공부하고 프로젝트에 적용한다. 객체 인식을 위해 카
메라 라이브러리를 적용할 예정
- Version Control
  - kotlin : 1.9.22
  - jdk: 17
  - targetSdk : 34
  - minSdk : 28
  - compose-compiler: 1.5.8
- IDE
  - Android Studio
- Library
  - Retrofit2 : OkHttp3 를 추상화하는 통신을 위함
  - Moshi : 파싱을 위해 사용. Gson, Kotlin Serialization 에 비해 성능적으로 우수하며 계속 개발되고
있음.
  - Room : SQLite 를 추상화한 Android 내장 DB
  - Glide : 이미지 로딩
  - dagger-hilt : 의존성 자동 주입 라이브러리
- 아키텍처 : 구글 권장 아키텍처
  - https://developer.android.com/topic/architecture
  - Data → Domain(optional) → UI

## 기능 구현

### 식자재 관리 
<img src="https://github.com/user-attachments/assets/752bed55-e5fe-4dd7-9947-ef1ca84c2cf2" width="300"/>
<img src="https://github.com/user-attachments/assets/5bb2b82b-352d-4357-8d29-24bbacf78c59" width="300"/>
<img src="https://github.com/user-attachments/assets/b6b9db07-dc99-473e-a567-e13f1b83efe5" width="300"/>




### 레시피 추천 및 조회

<img src="https://github.com/user-attachments/assets/bb3c8f51-08d9-44dd-9c7f-632b881adad7" width="300"/>
<img src="https://github.com/user-attachments/assets/794791d4-cf84-4cf8-9801-f6e94473d00f" width="300"/>


- 추천 받은 레시피에 대한 상세 조회 화면 구현
- recipeId 로 Banchango API 에 GET 요청 후 데이터 json 형식 파싱
- 움직이는 바텀 시트로 많은 양의 정보를 편리하게 확인 가능
- 레시피 난이도, 양, 시간, 설명 제공





