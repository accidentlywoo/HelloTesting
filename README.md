# HelloTesting
1. JUnit
2. Mickito
3. Testcontainers (feat. Docker)
4. JMeter

---

## 1. JUnit 5
[JUnit5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
대체제 : TestNG, Spock, ..

- JUnit 5는 Platform 기반 테스트 런처 제공 

  : JUnit4는 jar기반, 라이브러리라고 불렸지만, JUnit5는 프레임워크라고 부르는 이유인가?
    -> Test class와 Test 메소드가 public일 필요는 없다. 자동생성시 붙여주지 않음.
    -> JPA EntityManager 주입에서 이상동작이 있어서 class에 public을 붙여야했던 경험이 있음.
  
  : JUnit Platform 위에 Jupiter 모듈(JUnit5)과 Vintage 모듈(JUnit3,4 대응)이 있다.

### 기본 어노테이션
- @Test
- @BeforeAll / @AfterAll
- @BeforeEach / @AfterEach
- @Disabled

### 테스트 이름 표시하기
- @DisplayNameGeneration
    : Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정
    : 기본 구현체러 ReplaceUnderscores 제공
    
- @DisplayName
    : 어떤 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션
    : @DisplayNameGeneration 보다 우선 순위가 높다.
    
### Assertion : 단언하기!
org.unit.jupiter.api.Assertions.*

- assertEquals(expected, actual)
- assertNotNull(actual)
- assertTrue(boolean)
- assertAll(executables...) : 여러 assertion을 Supplier로 실행하면 한번에 실패 체크를 할 수 있다.
- assertThrows(expectedType, executable)
- assertTimeout(duration, executable)

마지막 매개변수로 String이나 Supplier<String> 타입의 인스턴스를 람다 형태로 제공할 수 있다. String타입은 무조건 문자열연산 실행, Supplier사용하면 실패한 경우에만 메시지를 생성하기때문에 성능에 이점을 줄 수 있다.
  
  AssertJ, Hemcrest, Truth 등의 라이브러리를 추가적으로 사용해 가독성을 높일 수 있다.
  
  스프링부트(2.2이상)에서 AssertJ, Hemcrest 라이브러리 기본적으로 끌고온다.

### Assumption : 가정하기!
org.unit.jupiter.api.Assumption.*

- assumeTrue(조건)
- assumingThat(조건, 테스트)

조건 테스트는 더 공부를 한 뒤에 활용

@Enabled ~ 와 @Disabled
- OnOS
- OnJre
- IfSystemProperty
- IfEnvironmentVariable
- If

조건(condition)을 주고 테스트 메소드를 실행할 수 있게 해준다.

## 2. Mickito
## 3. Testcontainers (feat. Docker)
## 4. JMeter
