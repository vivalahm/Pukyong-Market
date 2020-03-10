# [부경 마켓:Pukyong University Used Book Fair]

* 이 프로젝트는 아두이노와 안드로이드 스튜디오를 이용해 제작

## [개발 배경, 필요성]

 새 학기마다 학생들이 책을 구매하는 것은 금전적으로 부담되는 경우가 많다. 이럴 때 중고장터를 많이 이용하지만, 책 상태, 품질을 직접 확인하기도 힘들다. 그리고 주문 후 책이 도착하기 전까지 상당한 시간도 소요하게 된다. 그래서 우리는 부경대 학생들이 전공별로 책을 쉽고 빠르게 구매할 수 있는 앱을 제작하려고 한다. 

## [개발 목표 및 개발 내용]

### [개발환경]

#### [운영체제 및 개발도구]
Windows 10, Android studio 3.5.3 Version (Gradle model Version= 5.4.1), FireBase (클라우드 DB)
#### [개발언어]
JAVA, JDK-11.0.2 and 11.0.4, JRE-10.0.1
#### [장비]
Windows Intel 칩셋 노트북(HAXM 가상화 사용)
#### [개발내용]
1. 게시판을 이용해 사진과 글을 첨부해 중고책을 판매한다.
2. 중고책을 구입하고 싶은 사용자는 댓글을 통해 판매자한테 알린다.
3. 사용자는 댓글을 확인하고 거래를 진행한다.

## [개발절차]

- 기본 디자인 구상 및 구현
 - 로그인 페이지 구현
 - 비회원을 위한 회원가입 구현
 - FireBase 안드로이드 스튜디오 연동
 - 회원 정보 표시와 메뉴 선택을 위한 내비게이션 바 구현
 - 로그아웃 페이지 구현
 - 사용법 페이지 구현
 - 개발자 코멘터리 페이지 구현
 - 책 판매를 위한 실시간 게시글 작성 구현
 - 책 구매를 위한 실시간 댓글 서비스 구현

## [초기 스토리보드]
![1](https://user-images.githubusercontent.com/48741014/76330791-a02be500-6331-11ea-9af1-d70ffeb3831e.PNG)
![2](https://user-images.githubusercontent.com/48741014/76330794-a0c47b80-6331-11ea-8a01-25a84257b5d1.PNG)
![3](https://user-images.githubusercontent.com/48741014/76330798-a1f5a880-6331-11ea-892c-db19045813bd.PNG)
![4](https://user-images.githubusercontent.com/48741014/76330785-9dc98b00-6331-11ea-9e99-65985f589ad6.PNG)

## [앱기능 설명]
![1-1](https://user-images.githubusercontent.com/48741014/76330939-c9e50c00-6331-11ea-9c18-f78c646c134f.PNG)
![1-2](https://user-images.githubusercontent.com/48741014/76330942-ca7da280-6331-11ea-8b26-fbca1da02b02.PNG)
![1-3](https://user-images.githubusercontent.com/48741014/76330947-cbaecf80-6331-11ea-9153-7a4042d5d436.PNG)
![1-4](https://user-images.githubusercontent.com/48741014/76330948-ccdffc80-6331-11ea-9230-aaa328609607.PNG)
![1-5](https://user-images.githubusercontent.com/48741014/76330933-c81b4880-6331-11ea-9754-4c9b71583d00.PNG)
![2-1](https://user-images.githubusercontent.com/48741014/76331058-f436c980-6331-11ea-9f3b-247f234c1d69.PNG)
![2-2](https://user-images.githubusercontent.com/48741014/76331061-f6992380-6331-11ea-8ccd-04bd89fbe358.PNG)

## [실행 환경설정]
Step 1. 윈도우 10 인텔 칩셋 노트북 사용(HAXM 가상화 설치), 안드로이드 3.5.3 최신버전 설치, Gradle 모델 버전 5.4.1 (주의: 안드로이드 스튜디오 버전이 3.5 이하면 제가 구현한 내비게이션 바가 동작 안 합니다. 최신 버전을 설치해주세요), JDK는 11.0.4버전과 11.0.2버전 그리고 JRE는 10.0.1 버전 사용 

Step 2. 안드로이드 스튜디오 컴파일 SDK 버전은 28버전으로 맞춰주세요. AVD는 안드로이드 버전 9.0의 NEXUS 6 API 28 사용해주세요. Gradle 버전이 28인지 정확히 확인

Step 3. FireBase 연동
FireBase 라이브러리 쓰기 위해 FireBase 연동하는 과정 필요 (FireBase는 구글 계정으로 무료로 이용가능)
어플 연동 방법:https://hamzzibari.tistory.com/58
안드로이드 패키지 이름:pknu.it.pknuapp
위 패키지 명은 자신의 환경에 맞게 수정 (Gradle과 자바 파일 패키지 및 함수 자신의 패키지에 맞게 수정 필요)
나머지 연동 과정은 올려둔 블로그 참고하시면 되고, Gradle 두 가지 다 올바르게 파이어베이스 라이브러리가 설정됐는지 확인 바랍니다. (제가 올린 파일 그대로 쓰시면 됩니다.)

Step 4. 어플 회원 가입 및 책 게시글 작성시 사용할 사용자 이미지와 책 이미지를 인터넷을 통해 내려받으시거나 제가 함께 올린 사용자 사진과 책 사진 폴더에서 이미지를 복사하여 쓰셔도 됩니다.

Step 5. 어플 실행 시 반드시 FireBase를 온라인으로 프로젝트 실행시켜 줘야 합니다. 

![3-1](https://user-images.githubusercontent.com/48741014/76331182-20524a80-6332-11ea-87ed-82532608ca02.PNG)

Step 6. 인증 탭에서 로그인 방법에서 이메일/비밀번호 사용 설정해야합니다.

![3-2](https://user-images.githubusercontent.com/48741014/76331263-3cee8280-6332-11ea-9fc1-146335ef9147.PNG)

<인증 탭의 로그인 방법 설정>
또한, 추가적으로 데이터베이스 와 저장소 역시 활성화해주어야 사용 가능합니다.
*간혹 인터넷 연결에 따라 연동이 한 번에 안되는 때도 있으니 에러가 떠도 당황하지 말고 다시 AVD 러닝을 시켜주시면 됩니다.

