package test.concurrent;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import main.concurrent.consumer.ConcreteConsumer;
import main.concurrent.producer.ConcreteProducer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleWorldTest {

	private void waitMilliseconds(final int milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static final Logger LOGGER = Logger.getLogger(ExampleWorldTest.class.getName());

	@Test
	public void simulateWorld() {
		// Arrange

		// Create producer
		ConcreteProducer cp1 = new ConcreteProducer(3, "p1");
		ConcreteProducer cp2 = new ConcreteProducer(5, "p2");

		// Create consumer
		ConcreteConsumer cc1 = new ConcreteConsumer("c1");
		ConcreteConsumer cc2 = new ConcreteConsumer("c2");

		// attach consumer to producer
		cp1.register(cc1);
		cp2.register(cc2);

		cc1.setProducer(cp1);
		cc2.setProducer(cp2);

		// Act
		final var start = Instant.now();


		cp1.run();
		cp2.run();
		cc1.run();
		cc2.run();


		final var expectedValue = cp1.producerState();
		var actualValue = cc1.consumerState();

		final var finish = Instant.now();
		final var elapsedTime = Duration.between(start, finish).toMillis();
		LOGGER.info(String.format("Elapsed time in milliseconds: %d \n\n", elapsedTime));

		// Assert
		assertEquals(expectedValue, actualValue);
	}
}
