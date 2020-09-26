package by.bsuir.pokos.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Runnable {

    private final String address = "127.0.0.1"; // это IP-адрес компьютера, где исполняется наша серверная программа
    private final int serverPort = 2424; // здесь обязательно нужно указать порт к которому привязывается сервер
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Socket socket = null;

    public void run() {

        try {

            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект который отображает вышеописанный IP-адрес
            socket = new Socket(ipAddress, serverPort); // создаем сокет используя IP-адрес и порт сервера

            // Конвертируем потоки в другой тип, чтоб легче обрабатывать текстовые сообщения
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream(socket.getInputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public Socket getSocket() {
        return socket;
    }
}
