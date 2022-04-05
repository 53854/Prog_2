package main.exercise;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class CounterThread extends Thread {

	private final List<Integer> targetList;

	public CounterThread(List<Integer> targetList) {
		this.targetList = targetList;
	}

	@Override
	public void run() {
		try {
			while (!interrupted()) {
				this.targetList.add(ThreadLocalRandom.current().nextInt(0, 101));
				sleep(10);
			}
		} catch (InterruptedException e) {
			// dies
		}
	}
}
