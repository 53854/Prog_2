package main.chatapp;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class ConnectionThread extends Thread {

	private BlockingQueue<String> messages;
	private OutputStream outputStream;
	private PrintWriter out;
	private InputStream inputStream;
	private Scanner in;
	private WriterThread writer;
	private Socket socket;

	private boolean isRunning;

	public ConnectionThread(Socket socket, WriterThread writerThread, BlockingQueue<String> messages){
		try {
			this.socket = socket;
			this.messages = messages;

			this.outputStream = this.socket.getOutputStream();
			this.out = new PrintWriter(outputStream, true);

			this.inputStream = this.socket.getInputStream();
			this.in = new Scanner(inputStream);

			this.writer = writerThread;
			this.writer.connections.add(this.out);

			initConnection();
		} catch (IOException e){
			// Dedd
		}
	}

	@Override
	public void run() {
		isRunning = true;

		String msg = "Connection thread started looking for user input";
		System.out.println(msg + socket.isConnected());

		while (socket.isConnected()) {
			if(in.hasNextLine()){
				msg = in.nextLine();
				System.out.println(msg);
				messages.add(msg);
			}
		}
		isRunning = false;
	}

	public void initConnection() {
		out.println("\u001B[32mSuccessfully connected!\u001B[0m");
	}
}
