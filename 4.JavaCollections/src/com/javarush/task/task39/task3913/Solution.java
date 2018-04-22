package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);

        String sd0 = "03.10.2015";
        Date date0 = new Date();
        try {
            date0 = dateFormat.parse(sd0);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String sd = "03.10.2013";
        Date date = new Date();
        try {
            date = dateFormat.parse(sd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String sd2 = "03.10.2030";
        Date date2 = new Date();
        try {
            date2 = dateFormat.parse(sd2);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));
        System.out.println(logParser.getUniqueIPs(null, new Date()));
        System.out.println(logParser.getIPsForEvent(Event.DONE_TASK,null, new Date()));
        System.out.println(logParser.getIPsForStatus(Status.OK,null,new Date()));
        System.out.println(logParser.getIPsForUser("Amigo", null,new Date()));
        System.out.println(logParser.lineList.size());

        System.out.println("-----------------------/----------------------------");

        System.out.println(logParser.getAllUsers());
        System.out.println(logParser.getNumberOfUsers(date, new Date()));
        System.out.println(logParser.getNumberOfUserEvents("Vasya Pupkin",date, new Date()));
        System.out.println(logParser.getUsersForIP("127.0.0.1",date, new Date()));
        System.out.println(logParser.getLoggedUsers(date, new Date()));
        System.out.println(logParser.getDownloadedPluginUsers(null, new Date()));
        System.out.println(logParser.getWroteMessageUsers(date, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(date, new Date()));
        System.out.println(logParser.getSolvedTaskUsers(null, new Date(),18));
        System.out.println(logParser.getDoneTaskUsers(null, null));
        System.out.println(logParser.getDoneTaskUsers(null, null,15));
        System.out.println("--------------------------/----------------------------/-----------------------");

        System.out.println(logParser.getDatesForUserAndEvent("Vasya Pupkin",Event.SOLVE_TASK ,date, new Date()));
        System.out.println(logParser.getDatesWhenSomethingFailed(null, date2));
        System.out.println(logParser.getDatesWhenErrorHappened(null, date2));
        System.out.println(logParser.getDatesWhenErrorHappened(null, date2));
        System.out.println(logParser.getDateWhenUserLoggedFirstTime("Vasya Pupkin",date0, new Date()));
        //System.out.println(logParser.getDateWhenUserLoggedFirstTime2("Vasya Pupkin",date0, new Date()));
        System.out.println(logParser.getDateWhenUserSolvedTask("Amigo",18,null,null));
        System.out.println(logParser.getDateWhenUserDoneTask("Vasya Pupkin",15,null,null));
        System.out.println(logParser.getDatesWhenUserWroteMessage("Eduard Petrovich Morozko",null,null));
        System.out.println(logParser.getDatesWhenUserDownloadedPlugin("Eduard Petrovich Morozko",null,null));

        System.out.println("--------------------------------------/---------------------------------/-----------------------");

        System.out.println(logParser.getNumberOfAllEvents(null,null));
        System.out.println(logParser.getAllEvents(date,null));
        System.out.println(logParser.getEventsForIP("127.0.0.1",null,null));
        System.out.println(logParser.getEventsForUser("Eduard Petrovich Morozko",null,null));
        System.out.println(logParser.getFailedEvents(null,null));
        System.out.println(logParser.getErrorEvents(null,null));
        System.out.println(logParser.getNumberOfAttemptToSolveTask(18,null,null));
        System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(15,null,null));
        System.out.println(logParser.getAllSolvedTasksAndTheirNumber(null,null));
        System.out.println(logParser.getAllDoneTasksAndTheirNumber(null,null));

        System.out.println("--------------------------------/---------------------------------/----------------------");

        System.out.println(logParser.execute("get user"));
        System.out.println(logParser.execute("get event for date = \"03.01.2014 03:45:23\""));
        System.out.println(logParser.execute("get user for event = \"DONE_TASK\""));
        System.out.println(logParser.execute("get date for event  = \"DONE_TASK\""));

        System.out.println("--------------------------------/---------------------------------/----------------------");

        System.out.println(logParser.execute("get ip for user = \"Eduard Petrovich Morozko\" and date between \"11.12.2013 0:00:00\" and \"03.01.2014 23:59:59\""));
        System.out.println(logParser.execute("get date for event  = \"DONE_TASK\" and date between \"11.12.2010 0:00:00\" and \"03.01.2022 23:59:59\""));
        System.out.println(logParser.execute("get ip for date = \"03.01.2014 03:45:23\" and date between \"11.12.2008 0:00:00\" and \"03.01.2019 23:59:59\""));
        System.out.println(logParser.execute("get user for date = \"03.01.2014 03:45:23\" and date between \"11.12.2008 0:00:00\" and \"03.01.2019 23:59:59\""));




    }
}