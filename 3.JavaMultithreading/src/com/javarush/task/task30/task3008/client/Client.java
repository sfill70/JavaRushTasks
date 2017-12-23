package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Sfill on 21.06.2017.
 */
public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;

    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Enter the server address");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Enter the port address");
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        ConsoleHelper.writeMessage("Enter the User name");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {

        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Message delivery failed.");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread t = getSocketThread();
        t.setDaemon(true);
        t.start();
        synchronized (this) {
            try {
                this.wait();
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Connection is interrupted ");
                return;
                //System.exit(1);
            }
        }
        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду ‘exit’.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
        while (clientConnected) {
            String txt = ConsoleHelper.readString();
            if (txt.equals("exit")) {
                break;
            }
            if (shouldSendTextFromConsole()) {
                sendTextMessage(txt);
            }
        }

    }

    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {    // должен выводить текст message в консоль.
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {     // должен выводить в консоль информацию о том, что участник с именем userName присоединился к чату.
            ConsoleHelper.writeMessage(userName + " : Joined chat ");
        }

        protected void informAboutDeletingNewUser(String userName) {    //должен выводить в консоль, что участник с именем userName покинул чат.
            ConsoleHelper.writeMessage(userName + " : Exit chat");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {     // этот метод должен:
            Client.this.clientConnected = clientConnected;                                    //Устанавливать значение поля clientConnected внешнего объекта Client в соответствии с переданным параметром.

            synchronized (Client.this) {                                 //Оповещать (пробуждать ожидающий) основной поток класса Client.
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message messageRecive = connection.receive();
                if (messageRecive.getType() == MessageType.NAME_REQUEST) {
                    String userName = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, userName));
                } else if (messageRecive.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    break;
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {

            while (true) {
                Message messageRecive = connection.receive();
                if (messageRecive.getType() == MessageType.TEXT) {
                    processIncomingMessage(messageRecive.getData());
                } else if (messageRecive.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(messageRecive.getData());
                } else if (messageRecive.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(messageRecive.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        @Override
        public void run() {

            try {
                String addres=getServerAddress();
                int port=getServerPort();
                Socket socket = new Socket(addres,port);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException  | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
                //e.printStackTrace();
            }
        }



    }
//localhost
    public static void main(String[] args) {

        /*for (int i=0; i<3; i++) {
            new Client().run();
        }*/

         Client client1= new Client();
         Client client2= new Client();
         Client client3= new Client();
         client1.run();



    }
}
