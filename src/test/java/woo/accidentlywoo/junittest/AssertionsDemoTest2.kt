package woo.accidentlywoo.junittest

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.lang.ArithmeticException
import java.time.Duration

class AssertionsDemoTest2 {
    private val person = Person("yeon hui", "Woo");
    private val peole = setOf(person, Person("yeon hui", "Woo"));

    @Test
    fun `exception absence testing`() {
        val calculator = Calculator()
        val result = assertDoesNotThrow("Should not throw an exception") {
            calculator.divide(0, 1)
        }
        assertEquals(0, result);
    }

    @Test
    fun `expected exception testing`() {
        val calculator = Calculator()
        val exception = assertThrows<ArithmeticException>("Should throw an exception") {
            calculator.divide(1, 0)
        }
        assertEquals("/ by zero", exception.message)
    }

    @Test
    fun `grouped assertions`() {
        assertAll("Person properties",
            { assertEquals("yeon hui", person.firstName) },
            { assertEquals("Woo", person.lastName) }
        )
    }

    @Test
    fun `grouped assertions from a stream`() {
        assertAll("People with first name starting with W",
            peole
                .stream()
                .map {
                    { assertTrue(it.firstName.startsWith("y"))}
                }
            )
    }

    @Test
    fun `grouped assertions from a collection`() {
        assertAll("People with last name of Woo",
            peole.map { { assertEquals("Woo", it.lastName) } }
        )
    }

    @Test
    fun `timeout not exceeded testing`() {
        val fibonacciCalculator = Calculator();
        val result = assertTimeout(Duration.ofMillis(1000)) {
            fibonacciCalculator.multiply(14, 1)
        }
        assertEquals(14, result)
    }

    @Test
    fun `timeout exceeded with preemptive termination`() {
        assertTimeoutPreemptively(Duration.ofMillis(10)){
            Thread.sleep(100)
        }
    }
}