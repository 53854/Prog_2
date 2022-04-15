package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ConnectionThread extends Thread {

	private final BlockingQueue<String> messages;
	private final WriterThread writerThread;
	private final PrintWriter output;
	private final Scanner input;
	private final Socket client;

	public ConnectionThread(Socket clientSocket, WriterThread writerThread, BlockingQueue<String> messages) throws IOException {
		this.client = clientSocket;

		OutputStream outputStream = this.client.getOutputStream();
		this.output = new PrintWriter(outputStream, true);

		InputStream inputStream = this.client.getInputStream();
		this.input = new Scanner(inputStream);

		this.writerThread = writerThread;
		this.messages = messages;

		this.initConnection();
	}

	public void run() {
		String msg;
		while (client.isConnected()) {
			if (this.input.hasNext()) {
				msg = this.input.nextLine();
				System.out.println(msg);
				messages.add(msg);
			}
		}
		writerThread.clients.remove(output);
	}

	public void initConnection() {
		this.output.println("Connected successfully\nType \"!QUIT\" to quit");
		this.writerThread.clients.add(this.output);
	}
}
