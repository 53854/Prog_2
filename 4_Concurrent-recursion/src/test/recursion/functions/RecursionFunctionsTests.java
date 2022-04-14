package test.recursion.functions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.recursion.functions.RecursionAlgorithms;

public class RecursionFunctionsTests {

	private static final Logger LOGGER = Logger.getLogger(RecursionFunctionsTests.class.getName());

	// ## Factorial
	@ParameterizedTest
	@CsvSource({ "0,0", "1,1", "2,2", "3,6", "4,24", "5,120", "6,720", "7,5040", "8,40320" })
	void testFactorialBig(int input, int expected){
		assertEquals(expected, RecursionAlgorithms.factorial(input));
	}

	// ## Power
	@ParameterizedTest
	@CsvSource({ "1,1,1", "2,2,4", "3,3,27", "4,4,256", "5,5,3125", "6,6,46656", "7,7,823543", "8,8,16777216"})
	void testPowerBig(int input_A, int input_B, int expected){
		assertEquals(expected, RecursionAlgorithms.power(input_A, input_B));
	}

	// ## Efficient Power
	@ParameterizedTest
	@CsvSource({ "1,1,1", "2,2,4", "3,3,27", "4,4,256", "5,5,3125", "6,6,46656", "7,7,823543", "8,8,16777216"})
	void testPowerFastBig(int input_A, int input_B, int expected){
		assertEquals(expected, RecursionAlgorithms.powerFast(input_A, input_B));
	}

	// ## Fibonacci
	@ParameterizedTest
	@CsvSource({ "1,1", "2,1", "3,2", "4,3", "5,5", "6,8", "7,13", "8,21", "32,2178309" })
	void testFib(final long input, final long expected) {
		assertEquals(expected, RecursionAlgorithms.fib(input));
	}

	// ## Fibonacci Concurrent
	@ParameterizedTest
	@CsvSource({ "1,1", "2,1", "3,2", "4,3", "5,5", "6,8", "7,13", "8,21", "32,2178309" })
	void testFibConcurrent(final long input, final long expected) {
		assertEquals(expected, RecursionAlgorithms.fibConcurrent(input));
	}

	// ## Fibonacci Smart
	@ParameterizedTest
	@CsvSource({ "1,1", "2,1", "3,2", "4,3", "5,5", "6,8", "7,13", "8,21", "32,2178309" })
	void testFibSmart(final long input, final long expected) {
		assertEquals(expected, RecursionAlgorithms.fibSmart(input));
	}


	// runtime test
	@Test
	void testFibBig() {
		final var start = Instant.now();
		System.out.println("Fib: " + RecursionAlgorithms.fib(42));
		final var finish = Instant.now();
		LOGGER.info("Elapsed Time: " + Duration.between(start, finish).toMillis());
	}

	@Test
	void testFibConcurrent(){
		final var start = Instant.now();
		System.out.println("FibConcurrent: " + RecursionAlgorithms.fibConcurrent(42));
		final var finish = Instant.now();
		LOGGER.info("Elapsed Time: " + Duration.between(start, finish).toMillis());
	}

	@Test
	void testFibSmart(){
		final var start = Instant.now();
		System.out.println("FibSmart: " + RecursionAlgorithms.fibSmart(42));
		final var finish = Instant.now();
		LOGGER.info("Elapsed Time: " + Duration.between(start, finish).toMillis());
	}
}
