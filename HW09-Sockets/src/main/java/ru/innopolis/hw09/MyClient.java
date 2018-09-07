package ru.innopolis.hw09;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyClient {
    final String SERVER_ADDR = "localhost";
    final Integer SERVER_PORT = 4999;
    private final Scanner scanner = new Scanner(System.in);
    private BufferedWriter out;
    private BufferedReader in;
    private String message;

    public MyClient() {
        try {
            Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MyClient().run();
    }

    public void run() {
        myClientReads();
        myClientListens();
    }

    private void myClientReads() {
        new Thread(() -> {
            try {
                Thread.sleep(500);
                System.out.println("введите имя:");
                while ((message = scanner.nextLine()) != "") {
                    out.write(message);
                    out.write("\n");
                    out.flush();
                    System.out.println("Для выхода наберите 'quit'");
                    if (message.equals("quit")) {
                        break;
                    }
                    Thread.sleep(100);
                }
            } catch (Exception e) {
            }
        }).start();
    }

    private void myClientListens() {
        new Thread(() -> {
            try {
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                    if (message.equals("quit")) {
                        break;
                    }
                    Thread.sleep(100);
                }
            } catch (Exception e) {
            }
        }).start();
    }
}
