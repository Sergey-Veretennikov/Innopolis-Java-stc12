package ru.innopolis.hw09;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    static int clients_count = 0;
    private MyServer myServer;
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private String message;
    private String name;

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            clients_count++;
            this.myServer = myServer;
            this.socket = socket;
            this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                myServer.broadcastMsg("Новый участник вошёл в чат!");
                myServer.broadcastMsg("Клиентов в чате = " + clients_count);
                break;
            }

            while ((message = in.readLine()) != null) {
                name = message;
                break;
            }

            while ((message = in.readLine()) != null) {
                System.out.println(name + ": " + message);
                myServer.broadcastMsg(name + ": " + message);
                if (message.equals("quit")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(String messeg) {
        try {
            out.write(messeg);
            out.write("\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        System.out.println("Client " + name + " disconnected");
        myServer.remove(this);
        clients_count--;
        myServer.broadcastMsg("Клиентов в чате = " + clients_count);
        socket.close();
    }
}
