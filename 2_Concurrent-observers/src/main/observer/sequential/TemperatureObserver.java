package main.observer.sequential;

import java.util.concurrent.TimeUnit;


public class TemperatureObserver implements Observer {
	private int temperatureReading;
	private int approximateTemperature;
	private TemperatureObservee sensor;

	/**
	 * <p>Creates a temperature observer</p>
	 * <p>Prints out temperature regulation commands based on sensor readings</p>
	 *
	 * @param approximateTemperature Goal temperature the observer wil adjust towards
	 */
	public TemperatureObserver(int approximateTemperature) {
		this.approximateTemperature = approximateTemperature;
	}

	/**
	 * @return Current temperature reading stored in the observer
	 */
	public int getTemperatureReading() {
		return temperatureReading;
	}

	/**
	 * <p>Sets the Temperature the Observer will approximate</p>
	 *
	 * @param newApprxTemp new Temperature
	 */
	public void setApproximateTemperature(int newApprxTemp) {
		this.approximateTemperature = newApprxTemp;
	}

	/**
	 * <p>Connects the observer to a temperature sensor</p>
	 *
	 * @param sensor Temperature sensor which supplies Readings to the Observer
	 */
	public void setSensor(final TemperatureObservee sensor) {
		this.sensor = sensor;
	}


	/**
	 * <p>Reacts on each change of the connected sensors state</p>
	 */
	@Override
	public void update() {
		regulateTemperature(this.sensor.getTemperature());
	}


	/**
	 * <p>Updates current temperature reading</p>
	 * <p>Prints out adjustment command </p>
	 *
	 * @param currentTemperature temperature reading from attached sensor
	 */
	private void regulateTemperature(int currentTemperature) {
		try {
			// Update current temperature reading
			this.temperatureReading = currentTemperature;

			// Calculate Temperature adjustment
			int adjustment = this.approximateTemperature - this.temperatureReading;

			// Placeholder for communicating Temperature adjustment to AC unit
			TimeUnit.MILLISECONDS.sleep(500);

			// Printing adjustment command
			if (adjustment > 0) {
				System.out.println(/*ANSI_CYAN*/"\u001B[36m" +
						"Temperature too low, adjusting by " + adjustment + "°C");
			} else if (adjustment < 0) {
				System.out.println(/*ANSI_RED*/"\u001B[31m" +
						"Temperature too high, adjusting by " + adjustment + "°C");
			} else {
				System.out.println(/*ANSI_Green*/"\u001B[32m" +
						"Intended Temperature of " + this.approximateTemperature + "°C is reached");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
