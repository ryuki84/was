NHN 엔터테이먼트 기술과제 :: WAS 구현     

작성자 : 민용기

<스펙>
1. HTTP/1.1의 Host 헤더 해석
2. 다음 사항을 설정 파일로 관리
    - 파일 포맷은 JSON으로 자유롭게 구성
    - 몇 번 포트에서 동작하는지
    - HTTP/1.1의 Host 별로
        HTTP_ROOT 디렉토리를 다르게
        403, 404, 500에러일 때 출력할 HTML 파일 이름
3. 403, 404, 500를 처리
    - 해당 오류 발생 시 적절한 HTML 반환
    - 설정 파일에 적은 파일 이름을 이용
4. 다음과 같은 보안 규칙
    - 


6. 간단한 WAS를 구현
7. 현재 시각을 출력하는 SimpleServlet 구현
8. 구현한 여러 스펙을 검증하는 테스트 케이스를 JUnit4를 이용하여 작성