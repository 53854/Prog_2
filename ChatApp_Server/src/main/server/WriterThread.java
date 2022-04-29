package main.server;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

public class WriterThread extends Thread {

	public ArrayList<PrintWriter> clients = new ArrayList<PrintWriter>();

	private final BlockingQueue<String> messages;

	public WriterThread(BlockingQueue<String> messages) {
		this.messages = messages;
	}

	public void run() {
		while (true) {
			if (!this.messages.isEmpty() && !this.clients.isEmpty()) {
				String msg = this.messages.poll();

				for (PrintWriter cOut : clients) {
					cOut.println(msg);
				}
			}
		}
	}
}
