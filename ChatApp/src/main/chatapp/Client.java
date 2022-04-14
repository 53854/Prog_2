package main.chatapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	final String username;
	final Socket socket;
	final InputStream in;
	final OutputStream out;
	final Scanner input;
	final PrintWriter writer;

	public Client(Socket socket, String username) throws IOException {
		// connect to server
		this.username = username;
		this.socket = socket;

		// Get input from server
		this.in = this.socket.getInputStream();
		this.input = new Scanner(in);

		// Send output to server
		this.out = this.socket.getOutputStream();
		this.writer = new PrintWriter(out, true);

		// Start listeners
		ReaderThread();
		writeMessage();
	}

	private void ReaderThread() {
		new Thread(() -> {
			while (socket.isConnected()) {
				if (input.hasNextLine()) {
					String msg = input.nextLine();
					if(!msg.matches("^"+username+":")) {
						System.out.println("" + msg);
					}
				}
			}
			input.close();
		}).start();
	}

	private void writeMessage() {
		Scanner sc = new Scanner(System.in);
		while (socket.isConnected()) {
			if (sc.hasNextLine()) {
				writer.println(username + ": " + sc.nextLine());
			}
		}
		sc.close();
	}

	public static void main(String[] args) throws IOException {

		// Get username
		Scanner sc = new Scanner(System.in);
		System.out.print("Username: ");
		String username = sc.nextLine();

		// Instantiate new client
		Socket socket = new Socket("LAPTOP-GDSFRPTA", 1234);
		Client client = new Client(socket, username);

		sc.close();
	}
}
