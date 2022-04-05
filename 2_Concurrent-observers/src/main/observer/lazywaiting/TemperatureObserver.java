package main.observer.lazywaiting;

import java.util.concurrent.TimeUnit;


public class TemperatureObserver extends Observer {

	/** Whether log text is printed upon sensor updates
	 * */
	public boolean printAdjustmentLog = true;

	private String name = "TO_00";
	private int connectionDelay = 50, temperatureReading, approximateTemperature;
	private TemperatureObservee sensor;


	/**
	 * <p>Creates a temperature observer</p>
	 * <p>Prints out temperature regulation commands based on sensor readings</p>
	 *
	 * @param approximateTemperature Goal temperature the observer wil adjust towards
	 * @param connectionDelay        Time in MS simulating connection delay to ac unit
	 */
	public TemperatureObserver(int approximateTemperature, int connectionDelay) {
		this.approximateTemperature = approximateTemperature;
		this.connectionDelay = connectionDelay;
	}

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
	 * Sets name for Observer unit
	 *
	 * @param name <p>Distinguishable name displayed in adjustment command, defaults to "TO_00"</p>
	 */
	public void setName(String name) {
		this.name = name;
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
	public void update() {
		// ANSI_RED
		System.out.println("\u001B[31m" + this.name + ": starting task for " + this.connectionDelay + "ms");
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
			// Placeholder for communicating Temperature adjustment to AC unit
			TimeUnit.MILLISECONDS.sleep(this.connectionDelay);

			// Printing Adjustment text
			if(printAdjustmentLog) {
				printAdjustmentText(this.approximateTemperature - currentTemperature);
			}

			// Update current temperature reading
			this.temperatureReading = currentTemperature;

			//ANSI_GREEN
			System.out.println("\u001B[32m" + this.name + ": finished update");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * prints text for temperature adjustments to console
	 * @param adjustment amount of temperature to adjust, can be negative
	 */
	private void printAdjustmentText(int adjustment) {
		String text = "adjustment text";
		if (adjustment == 0) text = "\u001B[33m%s: Intended Temperature of %d°C is reached";
		else {
			if (adjustment > 0) text = "\u001B[34m%s: Temperature too low, adjusting by %d°C";
			else text = "\u001B[36m%s: Temperature too high, adjusting by %d°C";
		}
		System.out.printf((text) + "%n", this.name, adjustment);
	}
}
