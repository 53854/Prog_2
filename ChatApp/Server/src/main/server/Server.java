package main.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Server {

	private final int MAX_CONNECTIONS;
	private final int PORT;
	private final ServerSocket server;

	private ArrayList<ConnectionThread> connections = new ArrayList<>();
	private BlockingQueue<String> messages = new LinkedBlockingDeque<>(100);
	private WriterThread writerThread;

	public Server(int PORT, int MAX_CONNECTIONS) throws IOException {
		this.MAX_CONNECTIONS = MAX_CONNECTIONS;
		this.PORT = PORT;
		this.server = new ServerSocket(this.PORT, this.MAX_CONNECTIONS);

		this.writerThread = new WriterThread(messages);
		this.writerThread.start();
	}

	public void start() {
		System.out.println("\u001B[32mServer started\u001B[0m");
		try {
			while (connections.size() <= MAX_CONNECTIONS) {

				Socket clientSocket = server.accept();
				System.out.println("new incoming connection");

				ConnectionThread ct = new ConnectionThread(clientSocket, writerThread, messages);
				connections.add(ct);
				ct.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		try {
			Server sv = new Server(1234, 20);
			sv.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
