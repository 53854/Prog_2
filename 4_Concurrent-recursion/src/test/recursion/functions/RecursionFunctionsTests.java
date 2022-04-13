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

	// ## TODO: Factorial

	// ## TODO: Power

	// ## Fibonacci

	@ParameterizedTest
	@CsvSource({ "1,1", "2,1", "3,2", "4,3", "5,5", "6,8", "7,13", "8,21", "32,2178309" })
	void testFib(final long input, final long expected) {
		assertEquals(expected, RecursionAlgorithms.fib(input));
	}

	@Test
	void testFibBig() {
		final var start = Instant.now();
		RecursionAlgorithms.fib(42);
		final var finish = Instant.now();
		LOGGER.info("Elapsed Time: " + Duration.between(start, finish).toMillis());
	}

	// TODO Fibonacci Concurrent & Smart
}
