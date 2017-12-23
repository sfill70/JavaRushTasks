package com.javarush.task.task30.task3008.client;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;
import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sfill on 22.06.2017.
 */
public class BotClient extends Client{

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return new String("date_bot_"+(int) (Math.random()*100));
    }

    public class BotSocketThread extends SocketThread  {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(": ")&&message!=null&&!message.isEmpty()){
                Date date= Calendar.getInstance().getTime();
                String name=message.substring(0, message.indexOf(":"));
                String question=message.substring(message.indexOf(":")+2);
                SimpleDateFormat s = null;
                if(question.contains("дата")) {s = new SimpleDateFormat("d.MM.YYYY");} //Проходит contains и equalsю
                if(question.contains("день")) {s = new SimpleDateFormat("d");}
                if(question.equals("месяц")) {s = new SimpleDateFormat("MMMM");}
                if(question.equals("год")) {s = new SimpleDateFormat("YYYY");}
                if(question.contains("время")) {s = new SimpleDateFormat("H:mm:ss");}
                if(question.equals("час")) {s = new SimpleDateFormat("H");}
                if(question.equals("минуты")) {s = new SimpleDateFormat("m");}
                if(question.equals("секунды")) {s = new SimpleDateFormat("s");}
                if (s != null) {
                    sendTextMessage(String.format("Информация для %s: %s",name,s.format(date)));}
            }

          //  super.processIncomingMessage(message);
        }
    }

    public static void main(String[] args) {
        new BotClient().run();
    }
}
