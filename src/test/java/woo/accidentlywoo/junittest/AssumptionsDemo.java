package woo.accidentlywoo.junittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsDemo {

	private final Calculator calculator = new Calculator();

	@Test
	public void testOnlyOnCiServer(){
		assumeTrue("CI".equals(System.getenv("ENV")));
	}

	@Test
	public void testOnlyOnDeveloperWorkstation(){
		assumeTrue("DEV".equals(System.getenv("ENV")),
				() -> "Aborting test: not on developer workstation");
	}

	@Test
	public void testInAllEnvironments(){
		assumingThat("CI".equals(System.getenv("ENV")),
				() -> {
					assertEquals(2, calculator.divide(4, 2));
				});
		assertEquals(42, calculator.multiply(6, 7));
	}
}
