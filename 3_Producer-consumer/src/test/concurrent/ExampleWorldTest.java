package test.concurrent;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class ExampleWorldTest {

	private void waitMilliseconds(final int milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void simulateWorld() {
		// Arrange

		// Act

		// Assert

	}
}
