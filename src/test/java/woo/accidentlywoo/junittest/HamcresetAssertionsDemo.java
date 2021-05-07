package woo.accidentlywoo.junittest;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HamcresetAssertionsDemo {
	private final Calculator calculator = new Calculator();

	@Test
	public void assertWithHamcresetMatcher(){
		assertThat(calculator.subtract(4, 1), is(equalTo(3)));
	}
}
