package com.javarush.task.task39.task3913;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Sfill on 05.04.2018.
 */
public class Line {
    private String line;
   
    private String ip;
    private String user;
    private String data;
    private Date date;
    private String event;
    private String status;
    private int task;
    
    public Line(String line) {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);  // Не потокобезопастный, поэтому обявляем в методе, конструкторе, нельзя испольовать как статик.
        this.line=line;
        String[] parts = line.split("\\t");
        for (int i = 0; i <parts.length ; i++) {
            if (i==0){
                this.ip = parts[i];
            }
            if (i==1){
                this.user = parts[i];
            }
            if (i==2){
                this.data = parts[i];
                try {
                    date = dateFormat.parse(parts[i]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if (i == 3) {

                if (parts[i].split(" ").length > 1) {
                    this.event = parts[i].split(" ")[0];
                   // System.out.println(parts[i]);

                    if (parts[i].split(" ")[1] != null && !parts[i].split(" ")[1].isEmpty()) {
                        this.task = Integer.parseInt(parts[i].split(" ")[1]);
                    }
                }
                else {this.event=parts[i];
                   // System.out.println(parts[i]);
                    }
            }
            if (i==4){
                this.status = parts[i];
            }
        }

    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getDate() {return date;}

    public void setDate(Date date) { this.date = date; }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Line{" +
                "ip='" + ip + '\'' +
                ", user='" + user + '\'' +
                ", data='" + data + '\'' +
                ", event='" + event + '\'' +
                ", status='" + status + '\'' +
                ", task=" + task +
                '}';
    }
}
