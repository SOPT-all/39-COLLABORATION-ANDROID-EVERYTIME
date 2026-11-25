# 39-COLLABORATION-ANDROID-EVERYTIME
### 합동세미나 앱 4조 에브리타임 안드로이드 레포지토리입니다

에브리타임의 강의 탐색과 시간표 필터, 강의평 조회 경험을 구현한 Android 프로젝트입니다.

---

## 📌 프로젝트 소개

사용자가 원하는 조건으로 강의를 탐색하고, 분반별 강의평을 확인할 수 있도록 구현했습니다.

- **강의 목록** — 강의와 분반 정보를 조회하고, 여러 분반을 아코디언으로 표시합니다.
- **강의 필터** — 전공·영역, 이수 여부, 수강 제한 여부에 따라 강의를 조회합니다.
- **시간 필터** — 시간표의 요일 또는 시간대를 선택해 해당 범위에 개설된 분반을 조회합니다.
- **강의평** — 선택한 분반의 평점과 강의평을 확인하고, 키워드별로 조회합니다.

---

## 👥 팀원 및 역할

| 정희진 | 안태훈(LEAD) | 이지상 |
| :---: | :---: | :---: |
| <img width="250" alt="image" src="https://github.com/user-attachments/assets/b8ee7182-551f-4d8f-a3d0-35d9cd98a992" /> | <img width="250" alt="image" src="https://github.com/user-attachments/assets/12c1f75c-2710-464b-8f13-7da2ae792ae8" /> | <img width="250" alt="image" src="https://github.com/user-attachments/assets/249de0ca-b5b7-43b8-a689-dd0bcd40a458" />|
| 강의평 뷰 및 전체 강의평 API 연동 | 강의 목록 뷰 및 강의목록 API연동 | 타임 테이블 뷰 및 키워드 강의평 API연동 |

---

## 📱 구현 화면 및 시연

### 주요 화면

| 강의 목록 | 시간 필터 | 강의평 |
| :---: | :---: | :---: |
| <img width="300" height="620" alt="image" src="https://github.com/user-attachments/assets/65d469af-df67-4b24-985b-4da2373747b7" /> | <img width="290" height="612" alt="image" src="https://github.com/user-attachments/assets/04a671bf-a975-48e8-8eb3-2aa4303f7fb4" /> | <img width="292" height="613" alt="image" src="https://github.com/user-attachments/assets/65235231-2e79-406d-a8c3-e7aad1ea0553" /> |
| 카테고리·숨김 필터 및 분반 목록 | 요일·시간대 선택 및 적용 | 분반별 평점·키워드·강의평 조회 |

### 시연 영상

| 강의평 조회/강의평 키워드 조회 | 강의목록 조회 |
| :---: | :---: |
| https://github.com/user-attachments/assets/c2ef295d-3de4-49df-a2e6-c59435fcb0e0 | https://github.com/user-attachments/assets/853bbc66-5842-414f-af96-2473a41e267b |
| 선택한 분반으로 이동, 키워드별 강의평 조회 | 카테고리 선택, 숨김 체크박스 적용 및 해제, 시간필터링, 전공영역 선택, 아코디언 펼침, Card펼침 |


### QA 반영
| 타임테이블 QA 적용 | 페이지네이션(강의평) QA 적용 |
| :---: | :---: |
| https://github.com/user-attachments/assets/7cdf1814-2468-46f0-abe0-c0b1c96580e9 | https://github.com/user-attachments/assets/72958faf-31f4-40f0-a341-fae4864c8a0f |

---

## 🛠 기술 스택

| 구분 | 기술 |
| --- | --- |
| Language | Kotlin |
| UI | Jetpack Compose, Material 3 |
| Architecture | MVVM, Single Activity Architecture, Google Recomended Architecture |
| Dependency Injection | Hilt |
| Asynchronous | Kotlin Coroutines, Flow, StateFlow |
| Network | Retrofit2, OkHttp3 |
| Navigation | Navigation Compose(Nav2) |
| Collaboration | GitHub, Figma, Swagger |

---

## 🏗 아키텍처

화면 단위로 `Route`, `Screen`, `ViewModel`, `Contract`를 구성합니다.

| 구성 요소 | 역할 |
| --- | --- |
| Route | ViewModel 상태 구독, 화면 콜백 연결, 이벤트 수집 |
| Screen | 상태를 바탕으로 UI 표시, 사용자 동작을 콜백으로 전달 |
| ViewModel | 조회 조건과 화면 상태 관리, API 조회 및 결과 반영 |
| Contract | 화면에서 사용하는 UiState와 SideEffect 정의 |
| UI Model · Mapper | API 응답을 컴포넌트에 필요한 표시 데이터로 변환 |
| Repository | 화면에서 필요한 데이터 조회 함수 제공 |
| Service · DTO | API 요청 선언과 응답 JSON 구조 정의 |

---

## 📂 패키지 구조

```text
com.cookandroid.everytime
├── core
│   ├── designsystem
│   │   ├── component
│   │   └── theme
│   ├── navigation
│   └── util
├── data
│   ├── di
│   ├── dto
│   │   ├── base
│   │   ├── lecturelist
│   │   └── review
│   ├── network
│   ├── repository
│   │   ├── lecturelist
│   │   ├── review
│   │   └── timetable
│   └── service
│       ├── lecturelist
│       ├── review
│       └── timetable
└── presentation
    ├── main
    ├── navigation
    ├── lecturelist
    │   ├── component
    │   ├── LecturelistContract.kt
    │   ├── LecturelistScreen.kt
    │   └── LecturelistViewModel.kt
    ├── review
    └── timetable
```

---

## 🤝 그라운드 룰

<img width="772" height="411" alt="image" src="https://github.com/user-attachments/assets/a659871d-caf9-45aa-b350-aa9236a54476" />


---

## 💬 커밋 메시지 컨벤션

```text
[태그/#이슈번호] 작업 내용
```

| 태그 | 의미 |
| --- | --- |
| `feat` | 새로운 기능 추가 |
| `chore` | 코드 수정 |
| `add` | 파일 추가 |
| `del` | 파일 삭제 |
| `fix` | 버그 수정 |
| `refactor` | 동작을 유지하는 코드 구조 개선 |

```text
[feat/#80] 강의 목록 API 연결
[fix/#80] 분반 ID 전달 수정
[refactor/#80] 강의 목록 컴포넌트 분리
```

---

## 🧑‍💻 코드 컨벤션

| 구분 | 규칙 | 예시 |
| --- | --- | --- |
| 일반 함수·변수 | `lowerCamelCase` | `loadCourses`, `selectedCategory` |
| Composable 함수 | `UpperCamelCase` | `EverytimeLectureCard` |
| 클래스·인터페이스 | `UpperCamelCase` | `LecturelistViewModel` |
| 상수 | `SCREAMING_SNAKE_CASE` | `DEFAULT_PAGE_SIZE` |
| 응답 DTO | `UpperCamelCase` + `Response` | `CourseListResponse` |
| Retrofit 함수 | HTTP 메서드를 접두사로 사용 | `getCourses`, `getReviews` |
| Drawable 파일 | `snake_case` | `ic_close_mini.xml` |

---

## 🌿 브랜치 및 작업 흐름

| 브랜치 | 용도 |
| --- | --- |
| `main` | 배포 기준 브랜치 |
| `develop` | 개발 결과를 통합하는 브랜치 |
| `feature/#이슈번호-작업내용` | 기능 개발 |
| `fix/#이슈번호-작업내용` | 버그 수정 |
| `refactor/#이슈번호-작업내용` | 구조 개선 |

1. Issue를 작성하고 작업 범위를 정합니다.
2. 최신 `develop`에서 작업 브랜치를 생성합니다.
3. 구현과 동작 확인 후 원격 브랜치에 push합니다.
4. `develop`을 대상으로 PR을 작성합니다.
5. 코드 리뷰를 반영하고 팀의 병합 기준에 따라 merge합니다.
