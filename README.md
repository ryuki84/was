NHN 엔터테이먼트 기술과제 :: WAS 구현     

작성자 : 민용기

<스펙>

1. HTTP/1.1의 Host 헤더 해석
2. 다음 사항을 설정 파일로 관리
3. 403, 404, 500를 처리
    404 : 해당 FILE이 없을 경우 404.html 로 연결되게.
4. 다음과 같은 보안 규칙
6. 간단한 WAS를 구현 :: simpleServelt getMainPage()
7. 현재 시각을 출력하는 SimpleServlet 구현 :: getNowDateTime()
8. 구현한 여러 스펙을 검증하는 테스트 케이스를 JUnit4를 이용하여 작성