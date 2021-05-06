# JUnut 5 공홈 정리
[JUnit5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

## Writing Tests
1. What us JUnit5

JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

JUnit Platform은 JVM에서 테스팅 프레임워크 실행을 위한 기반으로 동작한다.

또, 플랫폼에서 동작하는 테스트 프레임워크 개발을 위한 테스트 엔진 API를 정의한다. 
...

JUnit Jupiter는 테스트 작성과 JUnit5에서 확장을 위한 새로운 프로그래밍 모델과 확장 모델의 뽐짝이다.

JUnit Vinatage는 플랫폼에서 JUnit3와 JUnit4 실행을위한 엔진을 제공한다.

2. 지원하는 자바 버전
JUnit5는 실행시점에 Java8 이상의 버전이 필요하다. 

3. Test Class들과 Method들
- Test Class : 어떤 적어도 하나의 테스트 메소드를 갖는 top-level class, static member class, @Nested class

- Test Method : @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate 직접 또는 메타 어노테이션이 붙은 모든 인스턴스 메소드

- Lifecycle Method : @BeforeAll, @AfterAll, @BeforeEach, @AfterEach 가 직접 또는 메타 어노테이션이 붙은 모든 메소드

테스트 메소드와 라이프사이클 메소드는 테스트 클래스의 지역적으로 선언된다. 

테스트 메소드와 라이프사이클 메소드는 반드시 추상메소드이면 안되고, 리턴 값이 없어야한다. 

---

## 영어단어 갱킹
- prominent : 현저한
- glimpse : 일견
- subsequent : 후속
