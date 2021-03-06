package ru.innopolis.hw09;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

public class MyServer {
    private final Integer SERVER_PORT = 4999;
    private final Scanner scanner = new Scanner(System.in);
    private String mes;
    private Socket socket;
    private List<ClientHandler> clientHandlerList = new CopyOnWriteArrayList<>();

    public MyServer() {
        myServerListens();
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server created. Waiting for client...");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Client connected");
                ClientHandler clientHandler = new ClientHandler(this, socket);
                clientHandlerList.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server closed");
        }
    }
    /**
     * Не совсем нравиться реализация, но пока так.
     */
    private void myServerListens() {
        new Thread(() -> {
            while ((mes = scanner.nextLine()) != "") {
                if (mes.equals("END") && clientHandlerList.isEmpty()) {
                    System.out.println("Server closed");
                    System.exit(0);
                }
            }
        }).start();
    }

    public synchronized void remove(ClientHandler clientHandler) {
        clientHandlerList.remove(clientHandler);
    }

    public synchronized void broadcastMsg(String messeg) {
        for (ClientHandler clHan : clientHandlerList) {
            clHan.sendMsg(messeg);
        }
    }
}
