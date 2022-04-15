package main.server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class WriterThread extends Thread {
	public ArrayList<PrintWriter> connections = new ArrayList<>();

	private boolean isRunning;
	private BlockingQueue<String> messages;

	public WriterThread(BlockingQueue<String> messages) {
		this.messages = messages;
	}

	@Override
	public void run() {
		isRunning = true;
		try {
			while (true) {
				if (!messages.isEmpty()) {
					for (PrintWriter writer : connections) {
						writer.println(messages.take());
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
}
