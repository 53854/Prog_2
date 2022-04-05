package test.exercise;

import main.exercise.TimeThread;

import java.util.Scanner;

public class TimeThreadTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		TimeThread timeThread = new TimeThread(2, "TimeTellerThread");
		timeThread.start();

		while(timeThread.isAlive()){
			if (sc.next().equals("q")) {
				timeThread.cancel();
			}
		}

		sc.close();

		System.exit(0);
	}
}
