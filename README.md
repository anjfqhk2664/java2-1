# 이상현 202130421

## 3월 29일 강의
조건문
    Ex num1=100, num2=50;
       num1 < num2 ? T : F
            num2출력
            
    if문 / 가장 많이 사용하는 조건문
        else if, else

    switch문
        case, break, default

반복문
    for문 / 가장 많이 사용하는 반복문
    while문 / 조건식이 '참'인 동안 반복 실행
    do-while문 / 조건식이 '참'인 동안 반복 실행, 작업문은 한 번 반드시 실행

비트개념
    논리 연산: AND, OR, XOR, NOT 연산
        AND = &, OR = |, XOR = ^, NOT = ~a
    시프트 연산: 비트를 오른쪽이나 왼쪽으로 이동
    
배열(array)
    인덱스와 인덱스에 대응하는 데이터들로 이루어진 자료 구조(배열을 이용하면 한 번에 많은 메모리 공간 선언 가능)
    배열은 같은 타입의 데이터들이 순차적으로 저장되는 공간
    
    배열 인덱스
        0부터 시작
        인덱스는 배열의 시작 위치에서부터 데이터가 있는 상대 위치

        intArray = new int[5] / 배열 생성
        int intArray[] = (4,3,2,0,1) / 배열 초기화

    배열의 크기 length
        int size = intArray.length;

    2차원 배열
        int intArray[][]; / 2차원 배열 선언
        intArray = new int[2][5];  int intArray[] = new int[2][5]; // 배열 선언과 생성 동시

## 3월 22일 강의
ctrl + shift + p > java pro > java create java project > 파일 선택 > 파일 이름

자바 : .java > class
자바 사용 장점 : 한번 작성된 코드는 모든 플랫폼에 구애받지 않고(WORA, Write Once Run Anywhere) 돌아감
자바 사용 단점 : JVM(Java Virtual Machine)이 깔려있어야 함

개발하는사람은 JDK를 깔면 되고,
실행하는사람은 JRE를 깔면 된다

식별자란 : 클래스, 변수, 상수, 메소드 등에 붙이는 이름이다
대소문자 구별, Num과 num은 다른 변수이다.

변수 : 값을 임시 저장하기 위한 공간
리터럴 : 값(정수, 실수, 문자, 논리, 문자열 리터럴이 있다)

var키워드
    타입을 생략하고 변수 선언 가능
    컨파일러가 추론하여 변수 타입 결정
    
    변수 선언 시 초깃값이 주어지지 않으면 컴파일 오류 / 초기화 필수

타입변환
    한 타입의 값을 다른 타입의 값으로 변환
## 3월 15일 강의
내용 정리

이번에는 올리지마!!!