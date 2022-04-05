package main.exercise;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

public class TimeThread extends Thread {

	private int waitSeconds = 0;
	private String _name = "";

	/**
	 * Creates a new Threat logging the current time every waitTime seconds
	 * @param waitTime delay between logs, full seconds
	 * @param name name of the thread
	 */
	public TimeThread(int waitTime, String name) {
		this.waitSeconds = waitTime;
		_name = name;
	}

	@Override
	public void run() {

		System.out.println("\u001B[32mStarting time teller thread " + _name +
				" | updating every " + waitSeconds + " secodns\n" +
				"enter 'q' to exit\u001B[0m");

		try {
			while (!interrupted()) {
				sleep(TimeUnit.SECONDS.toMillis(waitSeconds));
				System.out.println(
						"Time: " +
								java.time.LocalTime.now().truncatedTo(ChronoUnit.SECONDS)
				);
			}
		} catch (InterruptedException e) {
			System.out.println("\u001B[31mStopping time teller thread");
		}
	}

	/**
	 * Cancels the thread by interruption
	 */
	public void cancel() {
		interrupt();
	}
}
