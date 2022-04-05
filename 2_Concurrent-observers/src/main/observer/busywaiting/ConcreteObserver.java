package main.observer.busywaiting;

import java.util.concurrent.TimeUnit;

public class ConcreteObserver extends Observer {

	private int value;
	private ConcreteObservee observee;

	public int getValue() {
		return value;
	}

	public void setObservee(final ConcreteObservee observee) {
		this.observee = observee;
	}

	@Override
	public void update() {
		doSomething();
	}

	private void doSomething() {
		final var waitTime = this.observee.getValue();
		try {
			TimeUnit.MILLISECONDS.sleep(waitTime);
			this.value = waitTime;
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
	}
}
