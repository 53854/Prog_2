package client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String username;
    private final int userNameLength;
    private final PrintWriter output;
    private final Scanner input;
    private final Socket connection;

    public Client(String host, int port, String uName) throws IOException {
        // Setting up user and connection
        this.username = uName;
        this.userNameLength = this.username.length();
        connection = new Socket(host, port);

        InputStream ins = connection.getInputStream();
        OutputStream outs = connection.getOutputStream();

        output = new PrintWriter(outs, true);
        input = new Scanner(ins);

        System.out.println((this.input.nextLine()));

        this.readerThread();
        this.writeMessage(output);
    }

    private void readerThread() {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                while (connection.isConnected()) {
                    String msg = Client.this.input.nextLine();
                    if (!senderIsSelf(msg)) System.out.println(msg);
                }
            }
        })).start();
    }

    private void writeMessage(PrintWriter printWriter) throws IOException {
        Scanner uIn = new Scanner(System.in);
        while (connection.isConnected()) {
            String msg = uIn.nextLine();
            if (msg.equals("!QUIT")) quit();
            printWriter.println(this.username + ": " + msg);
        }
        uIn.close();
    }

    private boolean senderIsSelf(String msg) {
        if (msg.length() <= userNameLength) return false;
        return msg.substring(0, userNameLength).equals(username);
    }

    private void quit() throws IOException {
        output.println("User " + username + " disconnected ~");
        System.out.println("disconnecting...");
        connection.close();
        System.exit(0);
    }

    public static void main(String[] args) {

        System.out.println("Username:");
        Scanner keyboard = new Scanner(System.in);
        String username = keyboard.next();

        try {
            new Client("127.0.0.1", 1234, username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
