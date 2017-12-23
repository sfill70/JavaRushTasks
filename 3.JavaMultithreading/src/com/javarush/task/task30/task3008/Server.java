package com.javarush.task.task30.task3008;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Sfill on 20.06.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap= new ConcurrentHashMap();

    public static void sendBroadcastMessage(Message message){
        try {
            for (String key: connectionMap.keySet()){
                connectionMap.get(key).send(message);
            }

        }catch (Exception e){
            ConsoleHelper.writeMessage("Error sending message");
        }
    }

    public static void sendUserMessage (String userName, Message message){

        try {
            connectionMap.get(userName).send(message);

        }catch (Exception e){
            ConsoleHelper.writeMessage("Error sending message");
        }
    }

    private static class Handler extends Thread {
        public Socket socket;

      public Handler (Socket socket){
          this.socket=socket;
         }

         private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
             String name;
             while (true) {
                 connection.send(new Message(MessageType.NAME_REQUEST  ));
                 Message messageRecive = connection.receive();
                 if (messageRecive.getType()!=MessageType.USER_NAME) {
                     continue;
                 }
                 if (messageRecive.getData().isEmpty()){
                     continue;
                 }
                 if( connectionMap.containsKey(messageRecive.getData())){
                     continue;
                 }
                 name=messageRecive.getData();
                 connectionMap.put(name,connection);
                 connection.send(new Message(MessageType.NAME_ACCEPTED));
                 break;
             }
             return name;
         }

         private void sendListOfUsers(Connection connection, String userName) throws IOException {

             for (String key: connectionMap.keySet()) {
               if (!key.equals(userName)) {
                   String name = key;
                  connection.send(new Message(MessageType.USER_ADDED,name));
                 }
             }
         }
         private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

             while (true) {
                 Message message= connection.receive();
                 if (message.getType()==(MessageType.TEXT)){
                     Message messageSend= new Message(MessageType.TEXT, userName+": "+message.getData());
                     sendBroadcastMessage (messageSend);
                 }
                 else {  ConsoleHelper.writeMessage("Error, message is not text");    }
             }
         }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("A new connection with a remote address is established: "+socket.getRemoteSocketAddress());
            String userName=null;
          try {
                Connection connection = new Connection(socket);
                userName=serverHandshake (connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers (connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException | ClassNotFoundException e) {
              try {
                  socket.close();
              } catch (IOException e1) {
                  ConsoleHelper.writeMessage("An error occurred while communicating");
              }

          } 
          finally {
              if (userName!=null){
                  connectionMap.remove(userName);
                  sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                  ConsoleHelper.writeMessage(String.format("Connection with remote address %s is closed", socket.getRemoteSocketAddress()));
              }
            }
            /*try (Connection connection = new Connection(socket);) {
                userName=serverHandshake (connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers (connection, userName);
                serverMainLoop(connection, userName);
            }
            catch (IOException | ClassNotFoundException e){
                ConsoleHelper.writeMessage("An error occurred while communicating");
            }
            if (userName!=null) {
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                ConsoleHelper.writeMessage(String.format("Connection with remote address %s is closed", socket.getRemoteSocketAddress()));
            }*/
      }
    }

    public static void main(String[] args) throws IOException {
ConsoleHelper.writeMessage("Entr N port.");
       int port=ConsoleHelper.readInt();
        ServerSocket ss = new ServerSocket(port);
        ConsoleHelper.writeMessage("Server started on port: " + ss.getLocalPort() + "\n");

        while (true) {
            /*try (Socket socket = ss.accept()) {
            new Handler(socket).start();
                 }
            catch (IOException e) {
                System.out.println(e);
                ss.close();
                break;
            }*/

            try {
                Socket socket = ss.accept();
                new Handler(socket).start();
            } catch (IOException e) {
                System.out.println(e);
                ss.close();
                break;
            }

        }
    }
}
