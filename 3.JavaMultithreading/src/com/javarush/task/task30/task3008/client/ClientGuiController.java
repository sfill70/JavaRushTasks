package com.javarush.task.task30.task3008.client;

/**
 * Created by Sfill on 23.06.2017.
 */
public class ClientGuiController extends Client {

   private ClientGuiModel model=new ClientGuiModel();

   private ClientGuiView view = new ClientGuiView(this);

    @Override
    protected SocketThread getSocketThread() {return new GuiSocketThread ();}

    @Override
    public void run() {
        getSocketThread().run();
        }

    public String getServerAddress(){
        return view.getServerAddress();
    }

    public int getServerPort() {
        return view.getServerPort();
    }

    public String getUserName() {
        return view.getUserName();
    }

    public ClientGuiModel getModel() {
        return model;
    }




    public class GuiSocketThread extends SocketThread{

      public void processIncomingMessage(String message) {                        // должен устанавливать новое сообщение у модели и вызывать обновление вывода сообщений у представления.
          model.setNewMessage(message);
          view.refreshMessages();
        }
        public void informAboutAddingNewUser(String userName) {             // должен добавлять нового пользователя в модель и вызывать обновление вывода пользователей у отображения.
            model.addUser(userName);
            view.refreshUsers();
        }
        public void informAboutDeletingNewUser(String userName) {                   // должен удалять пользователя из модели и вызывать обновление вывода пользователей у отображения.
            model.deleteUser(userName);
            view.refreshUsers();
        }
        public void notifyConnectionStatusChanged(boolean clientConnected) {                          // должен вызывать аналогичный метод у представления.
                view.notifyConnectionStatusChanged(clientConnected);
        }


    }

    public static void main(String[] args) {

        ClientGuiController clientGuiController = new ClientGuiController();
        clientGuiController.run();
    }

}
