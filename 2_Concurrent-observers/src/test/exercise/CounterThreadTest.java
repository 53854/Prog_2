package test.exercise;

import main.exercise.CounterThread;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CounterThreadTest {

	/**
	 * Default ArrayList
	 */
	private static final List<Integer> targetList = new ArrayList<>();

	/**
	 * Threadsafe syncList
	 */
	private static List<Integer> threadsafe_targetList =
			Collections.synchronizedList(new ArrayList<Integer>());

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		runCounterThreads(sc);

		sc.close();
		System.exit(0);
	}

	static void runCounterThreads(Scanner sc) {
		CounterThread ct1 = new CounterThread(threadsafe_targetList);
		CounterThread ct2 = new CounterThread(threadsafe_targetList);
		ct1.start();
		ct2.start();

		final var start = Instant.now();

		while (ct1.isAlive() && ct2.isAlive()) {
			if (Duration.between(start, Instant.now()).toMillis() > 1000) {
				ct1.interrupt();
				ct2.interrupt();
			}
		}

		System.out.print("\u001B[33mTargetlist contains "
				+ threadsafe_targetList.size()
				+ " elements\u001B[0m  |  enter y to redo: ");

		if (sc.next().equals("y")) {
			threadsafe_targetList.clear();
			runCounterThreads(sc);
		}
	}

}
