package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {

	private final int MAX_CONNECTIONS;
	private final int PORT;

	private final ServerSocket server;
	private final WriterThread writerThread;

	private final ArrayList<Socket> connections = new ArrayList<Socket>();
	private final BlockingQueue<String> messages = new LinkedBlockingQueue<String>();

	public Server(int port, int max_connections) throws IOException {
		this.PORT = port;
		this.MAX_CONNECTIONS = max_connections;

		this.server = new ServerSocket(PORT, MAX_CONNECTIONS);

		this.writerThread = new WriterThread(messages);
		this.writerThread.start();
	}

	public static void main(String[] args) {
		try {
			Server sv = new Server(1234, 10);
			sv.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void start() {
		System.out.println("Starting server on port " + PORT + " with a maximum of " + MAX_CONNECTIONS + " connections");

		try {
			while (this.connections.size() < this.MAX_CONNECTIONS) {

				System.out.println("Listening for new clients...");

				Socket clientSocket = this.server.accept();
				System.out.println("Client " + clientSocket + " connected");
				connections.add(clientSocket);
				System.out.println("Currently " + this.connections.size() + " clients connected");

				ConnectionThread connection = new ConnectionThread(clientSocket, this.writerThread, messages);
				connection.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
