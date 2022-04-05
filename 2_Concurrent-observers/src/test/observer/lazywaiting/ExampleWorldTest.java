package test.observer.lazywaiting;

import main.observer.lazywaiting.ConcreteObservee;
import main.observer.lazywaiting.ConcreteObserver;
import main.observer.lazywaiting.TemperatureObservee;
import main.observer.lazywaiting.TemperatureObserver;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleWorldTest {
	private static final Logger LOGGER = Logger.getLogger(ExampleWorldTest.class.getName());
	private final int waitTime = 500;

	private void waitMilliseconds(int milliseconds) {
		try {
			TimeUnit.MILLISECONDS.sleep(milliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void simulateUpdatesTest() {
		// Arrange
		final var observee = new ConcreteObservee();
		final var observer1 = new ConcreteObserver();
		final var observer2 = new ConcreteObserver();
		final var observer3 = new ConcreteObserver();

		observee.register(observer1);
		observee.register(observer2);
		observee.register(observer3);

		observer1.setObservee(observee);
		observer2.setObservee(observee);
		observer3.setObservee(observee);

		final var waitTime = 500;

		// Act
		final var start = Instant.now();

		observee.changeState(waitTime);

		// Assert
		waitMilliseconds(waitTime * 2);
		assertEquals(waitTime, observer1.getValue());
		assertEquals(waitTime, observer2.getValue());
		assertEquals(waitTime, observer3.getValue());

		final var finish = Instant.now();
		final var elapsedTime = Duration.between(start, finish).toMillis();
		LOGGER.info(String.format("Elapsed time in milliseconds: %d ", elapsedTime));
	}

	@Test
	public void simulateTemperatureUpdatesTest() {
		final var sensor = new TemperatureObservee();
		final var temperatureReader1 = new TemperatureObserver(22, waitTime);
		final var temperatureReader2 = new TemperatureObserver(19, waitTime);
		final var temperatureReader3 = new TemperatureObserver(25, waitTime);
		final var temperatureReader4 = new TemperatureObserver(22, waitTime);
		final var temperatureReader5 = new TemperatureObserver(20, waitTime);
		final var temperatureReader6 = new TemperatureObserver(23, waitTime);
		final var temperatureReader7 = new TemperatureObserver(21, waitTime);
		final var temperatureReader8 = new TemperatureObserver(28, waitTime);
		final var temperatureReader9 = new TemperatureObserver(20, waitTime);

		temperatureReader1.setName("TO_01");
		temperatureReader2.setName("TO_02");
		temperatureReader3.setName("TO_03");
		temperatureReader4.setName("TO_04");
		temperatureReader5.setName("TO_05");
		temperatureReader6.setName("TO_06");
		temperatureReader7.setName("TO_07");
		temperatureReader8.setName("TO_08");
		temperatureReader9.setName("TO_09");

		sensor.register(temperatureReader1);
		sensor.register(temperatureReader2);
		sensor.register(temperatureReader3);
		sensor.register(temperatureReader4);
		sensor.register(temperatureReader5);
		sensor.register(temperatureReader6);
		sensor.register(temperatureReader7);
		sensor.register(temperatureReader8);
		sensor.register(temperatureReader9);

		temperatureReader1.setSensor(sensor);
		temperatureReader2.setSensor(sensor);
		temperatureReader3.setSensor(sensor);
		temperatureReader4.setSensor(sensor);
		temperatureReader5.setSensor(sensor);
		temperatureReader6.setSensor(sensor);
		temperatureReader7.setSensor(sensor);
		temperatureReader8.setSensor(sensor);
		temperatureReader9.setSensor(sensor);

		final var sensorTemperature = 22;

		final var start = Instant.now();
		sensor.changeState(sensorTemperature);


		waitMilliseconds(waitTime * 2); //Buffer wait time before assertion
		assertEquals(sensorTemperature, temperatureReader1.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader2.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader3.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader4.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader5.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader6.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader7.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader8.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader9.getTemperatureReading());

		final var finish = Instant.now();
		final var elapsedTime = Duration.between(start, finish).toMillis();
		LOGGER.info(String.format("\u001B[33m" + "Elapsed time in milliseconds: %d \n\n", elapsedTime));
	}
}

