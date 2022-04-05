package main.observer.naive;

import java.util.concurrent.TimeUnit;


public class TemperatureObserver extends Observer {
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
		// ANSI_RESET
		System.out.println("\u001B[0m" + this.name + ": starting task for " + this.connectionDelay + "ms");
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
			int adjustment = this.approximateTemperature - currentTemperature;

			// Placeholder for communicating Temperature adjustment to AC unit
			TimeUnit.MILLISECONDS.sleep(this.connectionDelay);

			/** Adjustment Command output

			 StringBuilder adjustmentCommand = new StringBuilder()
			 .append(this.name)
			 .append(": ");

			 // Selecting adjustment command
			 if (adjustment > 0) {
			 //ANSI_BLUE
			 adjustmentCommand.append("\u001B[34m" + "Temperature too low, adjusting by ")
			 .append(adjustment)
			 .append("°C");
			 } else if (adjustment < 0) {
			 //ANSI_RED
			 adjustmentCommand.append("\u001B[31m" + "Temperature too high, adjusting by ")
			 .append(adjustment)
			 .append("°C");
			 } else {
			 //ANSI_Green
			 adjustmentCommand.append("\u001B[32m" + "Intended Temperature of ")
			 .append(this.approximateTemperature)
			 .append("°C is reached");
			 }

			 // Printing Adjustment
			 System.out.println(adjustmentCommand);*/

			//ANSI_GREEN
			System.out.println("\u001B[34m" + this.name + ": finished update");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
