package woo.accidentlywoo.junittest;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemoTest {

	private final Calculator calculator = new Calculator();

	private final Person person = new Person("yeon hui", "Woo");

	@Test
	public void 기본_Assertions(){
	    assertEquals(2, calculator.add(1, 1));
	    assertEquals(4, calculator.multiply(2, 2),
			    "The optional failure message is now the last parameter");
			assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
				+ "to avoid constructing complex messages unnecessarily.");
	}

	@Test
	public void 묶음_Assertions(){
		assertAll("person",
				() -> assertEquals("yeon hui", person.getFirstName()),
				() -> assertEquals("Woo", person.getLastName())
		);
	}

	@Test
	public void 의존_Assertions(){
		assertAll("properties",
				() -> {
					String firstName = person.getFirstName();
					assertNotNull(firstName);

					assertAll("first name",
							() -> assertTrue(firstName.startsWith("y")),
							() -> assertTrue(firstName.endsWith("i"))
							);
				},
				() -> {
					String lastName = person.getLastName();
					assertNotNull(lastName);

					assertAll("last name",
							() -> assertTrue(lastName.startsWith("W")),
							() -> assertTrue(lastName.endsWith("o"))
							);
				}
		);
	}

	@Test
	public void exception_testing(){
		Exception exception = assertThrows(ArithmeticException.class, () ->
				calculator.divide(1, 0));
		assertEquals("/ by zero", exception.getMessage());
	}

	@Test
	public void timeout_not_exceeded(){
		assertTimeout(Duration.ofMinutes(2), () -> {
			System.out.println("꺩꼵");
		});
	}

	@Test
	public void timeout_not_exceeded_with_result(){
		String actual_result = assertTimeout(Duration.ofMinutes(2), () -> {
			return "a result";
		});
		assertEquals("a result", actual_result);
	}

	@Test
	public void timeout_not_exceeded_with_method(){
		String actual_greeting = assertTimeout(Duration.ofMinutes(2), AssertionsDemoTest::greeting);
		assertEquals("Hell", actual_greeting);
	}

	@Test
	public void timeout_exceeded(){
		assertTimeout(Duration.ofMillis(10), () -> {
			Thread.sleep(100);
		});
	}

	@Test
	public void timeout_exceeded_with_preemptive_termination(){
		assertTimeoutPreemptively(Duration.ofMillis(10), () -> {
			new CountDownLatch(1).await();
		});
	}

	private static String greeting(){
		return "Hell";
	}
}
