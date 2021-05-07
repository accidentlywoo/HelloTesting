# JUnut 5 공홈 정리
[JUnit5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

---

## Writing Tests
### 1. What us JUnit5

JUnit 5 = JUnit Platform + JUnit Jupiter + JUnit Vintage

JUnit Platform은 JVM에서 테스팅 프레임워크 실행을 위한 기반으로 동작한다.

또, 플랫폼에서 동작하는 테스트 프레임워크 개발을 위한 테스트 엔진 API를 정의한다. 
...

JUnit Jupiter는 테스트 작성과 JUnit5에서 확장을 위한 새로운 프로그래밍 모델과 확장 모델의 뽐짝이다.

JUnit Vinatage는 플랫폼에서 JUnit3와 JUnit4 실행을위한 엔진을 제공한다.

### 2. 지원하는 자바 버전

JUnit5는 실행시점에 Java8 이상의 버전이 필요하다. 

### 3. Test Class들과 Method들

- Test Class : 어떤 적어도 하나의 테스트 메소드를 갖는 top-level class, static member class, @Nested class

- Test Method : @Test, @RepeatedTest, @ParameterizedTest, @TestFactory, @TestTemplate 직접 또는 메타 어노테이션이 붙은 모든 인스턴스 메소드

- Lifecycle Method : @BeforeAll, @AfterAll, @BeforeEach, @AfterEach 가 직접 또는 메타 어노테이션이 붙은 모든 메소드

테스트 메소드와 라이프사이클 메소드는 테스트 클래스의 지역적으로 선언된다. 

테스트 메소드와 라이프사이클 메소드는 반드시 추상메소드이면 안되고, 리턴 값이 없어야한다. 

- [자바예제 보기](./src/test/java/woo/accidentlywoo/junittest/AssertionsDemoTest.java)
- [코틀린예제 보기](./src/test/java/woo/accidentlywoo/junittest/AssertionsDemoTest2.kt)

### 4. Third - party Assertion Libraries

matcher같은 추가적인 기능을 사용하고 싶을 때가 있다. 이때는 third party 라이브러리인 AssertJ, Hamcrest, Truth, etc.. 등이 있다.

예를 들어, matcher와 유창한(?) API의 조합은 가독성이 좋고, 설명 하기 쉽다.

ex. Hamcreset

- [자바예제 보기](./src/test/java/woo/accidentlywoo/junittest/HamcresetAssertionsDemo.java)

### 5. Assumptions

- [자바예제 보기](./src/test/java/woo/accidentlywoo/junittest/AssumptionsDemo.java)

### 6. Disabling Tests

테스트 클래스 전체 또는 테스트 메소드 개발적으로 @Disabled 어노테이션으로 동작안하게 할 수 있다.

테스트 클래스 전체 무력화
```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Disabled until bug #99 has been fixed")
class DisabledClassDemo {

    @Test
    void testWillBeSkipped() {
    }

}
```

테스트 메소드 부분 무력화
```java
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DisabledTestsDemo {

    @Disabled("Disabled until bug #42 has been resolved")
    @Test
    void testWillBeSkipped() {
    }

    @Test
    void testWillBeExecuted() {
    }

}
```

### 7. Conditional Test Execution

실행조건은 JUnit Jupiter의 확장된 API가 특정 환경에서 컨테이너나 프로그래밍적으로 특정 조건의 테스트를 활성화 비활성화 처리를 한다. 

1. Operating System Conditions
컨테이너에서나 테스트에서 특정 OS에서 @EnabledOnOS, @DisabledOnOs 어노테이션으로 활성, 비활성화 처리 할 수 있다.
   
```java
class Test {
    @Test
    @EnabledOnOs(MAC)
    void onlyOnMacOs() {
        // ...
    }
    
    @TestOnMac
    void testOnMac() {
        // ...
    }
    
    @Test
    @EnabledOnOs({ LINUX, MAC })
    void onLinuxOrMac() {
        // ...
    }
    
    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows() {
        // ...
    }
    
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(MAC)
    @interface TestOnMac {
    }
}
```

2. Java Runtime Environment Conditions 

컨테이너나 테스트를 @EnabledOnJre, @DisabledOnJre 어노테이션이나 @EnabledForJreRange, @DisabledForJreRange 어노테이션으로 특정 JRM 버전에서 활설화, 비활성화할 수 있다.

```java
class Test {
	@Test
	@EnabledOnJre(JAVA_8)
	void onlyOnJava8() {
		// ...
	}

	@Test
	@EnabledOnJre({ JAVA_9, JAVA_10 })
	void onJava9Or10() {
		// ...
	}

	@Test
	@EnabledForJreRange(min = JAVA_9, max = JAVA_11)
	void fromJava9to11() {
		// ...
	}

	@Test
	@EnabledForJreRange(min = JAVA_9)
	void fromJava9toCurrentJavaFeatureNumber() {
		// ...
	}

	@Test
	@EnabledForJreRange(max = JAVA_11)
	void fromJava8To11() {
		// ...
	}

	@Test
	@DisabledOnJre(JAVA_9)
	void notOnJava9() {
		// ...
	}

	@Test
	@DisabledForJreRange(min = JAVA_9, max = JAVA_11)
	void notFromJava9to11() {
		// ...
	}

	@Test
	@DisabledForJreRange(min = JAVA_9)
	void notFromJava9toCurrentJavaFeatureNumber() {
		// ...
	}

	@Test
	@DisabledForJreRange(max = JAVA_11)
	void notFromJava8to11() {
		// ...
	}
}
```

3. Systme Property Conditions

컨테이너나 테스트는 named라고 불리는 JVM 프로퍼티값을 @EnabledIfSystemProperty, @DisabledIfSystemProperty 어노테이션을 통해 활성화, 비활성화 처리될 수 있다.

```java
class Test {
	@Test
	@EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
	void onlyOn64BitArchitectures() {
		// ...
	}

	@Test
	@DisabledIfSystemProperty(named = "ci-server", matches = "true")
	void notOnCiServer() {
		// ...
	}
}
```

4. Environment Variable Conditions

컨테이너나 테스트는 named라고 불리는 OS 환경 변수값을 @EnabledIfEnvironmentVariable, @DisabledIfEnvironmentVariable 어노테이션을 통해 활성화, 비활성화 처리할 수 있다.

```java
class Test {
	@Test
	@EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
	void onlyOnStagingServer() {
		// ...
	}

	@Test
	@DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
	void notOnDeveloperWorkstation() {
		// ...
	}
}
```

5. Custom Conditions

컨테이너나 테스트는 사용자가 생성한 메소드 boolean return 값을 @EnabledId, @DisabledId 어노테이션을 통해 활성화, 비활성화 처리를 할 수 있다.

```java
class Test {
	@Test
	@EnabledIf("customCondition")
	void enabled() {
		// ...
	}

	@Test
	@DisabledIf("customCondition")
	void disabled() {
		// ...
	}

	boolean customCondition() {
		return true;
	}
}
```



---

### 8. Tagging and Filtering

테스트 클래스와 메소드는 @Tag 어노테이션으로 태그될 수 있다.

이 태그는 테스트 디스커버리와 실행에서 필터링될 수 있다.

1. Syntax Rules for Tags

- 태그는 null과 공백이 될 수 없다.
- 트림(trim)된 태그는 공백을 포함하면 안된다.
- 트림(trim)된 태그는 ISO 컨트롤 문자는 포함될 수 없다.
- 트림(trim)된 태그는 아래 문자를 포함하면 안된다.
    - , 
    - (
    - )
    - &
    - |
    - !
    
```java
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("fast")
@Tag("model")
class TaggingDemo {

    @Test
    @Tag("taxes")
    void testingTaxCalculation() {
    }

}
```

## 영어단어 갱킹
- prominent : 현저한
- glimpse : 일견
- subsequent : 후속
