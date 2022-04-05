package test.observer.naive;

import main.observer.naive.TemperatureObservee;
import main.observer.naive.TemperatureObserver;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExampleWorldTest {

	private static final Logger LOGGER = Logger.getLogger(ExampleWorldTest.class.getName());

	@Test
	public void simulateTemperatureUpdatesTest() {
		final var sensor = new TemperatureObservee();
		final var temperatureReader1 = new TemperatureObserver(22,25);
		final var temperatureReader2 = new TemperatureObserver(19,25);
		final var temperatureReader3 = new TemperatureObserver(25,25);
/*		final var temperatureReader4 = new TemperatureObserver(22,50);
		final var temperatureReader5 = new TemperatureObserver(20,80);
		final var temperatureReader6 = new TemperatureObserver(23,50);
		final var temperatureReader7 = new TemperatureObserver(21,69);
		final var temperatureReader8 = new TemperatureObserver(28,50);
		final var temperatureReader9 = new TemperatureObserver(20,42);*/

		temperatureReader1.setName("TO_01");
		temperatureReader2.setName("TO_02");
		temperatureReader3.setName("TO_03");
/*		temperatureReader4.setName("TO_04");
		temperatureReader5.setName("TO_05");
		temperatureReader6.setName("TO_06");
		temperatureReader7.setName("TO_07");
		temperatureReader8.setName("TO_08");
		temperatureReader9.setName("TO_09");*/

		sensor.register(temperatureReader1);
		sensor.register(temperatureReader2);
		sensor.register(temperatureReader3);
/*		sensor.register(temperatureReader4);
		sensor.register(temperatureReader5);
		sensor.register(temperatureReader6);
		sensor.register(temperatureReader7);
		sensor.register(temperatureReader8);
		sensor.register(temperatureReader9);*/

		temperatureReader1.setSensor(sensor);
		temperatureReader2.setSensor(sensor);
		temperatureReader3.setSensor(sensor);
/*		temperatureReader4.setSensor(sensor);
		temperatureReader5.setSensor(sensor);
		temperatureReader6.setSensor(sensor);
		temperatureReader7.setSensor(sensor);
		temperatureReader8.setSensor(sensor);
		temperatureReader9.setSensor(sensor);*/

		final var sensorTemperature = 22;

		final var start = Instant.now();
		sensor.changeState(sensorTemperature);

		final var finish = Instant.now();
		final var elapsedTime = Duration.between(start, finish).toMillis();
		LOGGER.info(String.format("\u001B[33m" + "Elapsed time in milliseconds: %d \n\n", elapsedTime));

		assertEquals(sensorTemperature, temperatureReader1.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader2.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader3.getTemperatureReading());
/*		assertEquals(sensorTemperature, temperatureReader4.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader5.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader6.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader7.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader8.getTemperatureReading());
		assertEquals(sensorTemperature, temperatureReader9.getTemperatureReading());*/
	}
}
