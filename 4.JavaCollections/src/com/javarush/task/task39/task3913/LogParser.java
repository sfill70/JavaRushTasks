package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
    private Path logDir;
    private List<String> linesList;
    public List<Line> lineList;


    public LogParser(Path logDir) {
        this.logDir = logDir;
        linesList = getLinesList();
        lineList = getLinsList(linesList);
    }

    private List<String> getLinesList() {
        String[] files = logDir.toFile().list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".log");
            }
        });

        List<String> lines = new ArrayList<>();
        for (String file : files) {
            try {
                lines.addAll(Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()));
                //    System.out.println((Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset())));
                //  Files.readAllLines(Paths.get(logDir + File.separator + file), Charset.defaultCharset()).forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return lines;
    }

    private List<Line> getLinsList(List<String> lines) {
        List<Line> lineList = new ArrayList<>();
        lines.stream().forEach((p) -> lineList.add(new Line(p)));
        return lineList;
    }

    // Другой вариант этого метода
    private List<Line> getLinsList1(List<String> lines) {
        return lines.stream().map((p) -> new Line(p)).collect(Collectors.toList());

    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after, before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getCompatibleDate(after, before).stream().map(p -> p.getIp()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {

        return getCompatibleDate(after, before).stream().filter(p -> p.getUser().equals(user)).map(p -> p.getIp()).collect(Collectors.toSet());
    }


    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {

        return getCompatibleDate(after, before).stream().filter(p -> (event.toString().equals(p.getEvent().split(" ")[0]))).map(p -> p.getIp()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {

        return getCompatibleDate(after, before).stream().filter(p -> (status.toString().equals(p.getStatus()))).map(p -> p.getIp()).collect(Collectors.toSet());
    }


    public Set<Line> getCompatibleDate(Date after, Date before) {

        return lineList.stream().filter(p -> isCompatibleDate(p.getDate().getTime(), after, before)).collect(Collectors.toSet());

    }

    private boolean isCompatibleDate(long lineDateTime, Date after, Date before) {
        if (after == null && before == null) {
            return true;
        } else if (after == null) {
            if (lineDateTime <= before.getTime()) {
                return true;
            }
        } else if (before == null) {
            if (lineDateTime >= after.getTime()) {
                return true;
            }
        } else {
            if (lineDateTime > after.getTime() && lineDateTime < before.getTime()) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Set<String> getAllUsers() {
        return lineList.stream().map(p -> p.getUser()).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return getCompatibleDate(after, before).stream().map(p -> p.getUser()).
                collect(Collectors.toSet()).size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> p.getUser().equals(user)).map(p -> p.getEvent()).
                collect(Collectors.toSet()).size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> p.getIp().equals(ip)).map(p -> p.getUser()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.LOGIN.toString().equals(p.getEvent())).map(p -> p.getUser()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.DOWNLOAD_PLUGIN.toString().equals(p.getEvent())).map(p -> p.getUser()).
                /*filter(p-> {System.out.println(p);
            return true;}).*/
                        collect(Collectors.toSet());
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.WRITE_MESSAGE.toString().equals(p.getEvent())).map(p -> p.getUser()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.SOLVE_TASK.toString().equals(p.getEvent())).map(p -> p.getUser()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.SOLVE_TASK.toString().equals(p.getEvent())).filter(p -> task == p.getTask()).map(p -> p.getUser()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.DONE_TASK.toString().equals(p.getEvent())).filter(p -> p.getTask() != 0).map(p -> p.getUser()).
                collect(Collectors.toSet());
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return getCompatibleDate(after, before).stream().filter(p -> Event.DONE_TASK.toString().equals(p.getEvent())).filter(p -> task == p.getTask()).map(p -> p.getUser()).
               /* filter(p-> {System.out.println(p);
            return true;}).*/
                       collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).filter(p -> event.toString().equals(p.getEvent())).map(p -> p.getDate()).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Status.FAILED.toString().equals(p.getStatus())).map(p -> p.getDate()).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Status.ERROR.toString().equals(p.getStatus())).map(p -> p.getDate()).collect(Collectors.toSet());
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        try {
            return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).filter(p -> Event.LOGIN.toString().equals(p.getEvent())).map(p -> p.getDate()).min(Date::compareTo).get();
        } catch (Exception e) {
            return null;
        }
    }


    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        try {
            return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).filter(p -> Event.SOLVE_TASK.toString().equals(p.getEvent())).filter(p -> task == p.getTask()).map(p -> p.getDate()).min(Date::compareTo).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        try {
            return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).filter(p -> Event.DONE_TASK.toString().equals(p.getEvent())).filter(p -> task == p.getTask()).map(p -> p.getDate()).min(Date::compareTo).get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).filter(p -> Event.WRITE_MESSAGE.toString().equals(p.getEvent())).map(p -> p.getDate()).collect(Collectors.toSet());
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {

        return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).filter(p -> Event.DOWNLOAD_PLUGIN.toString().equals(p.getEvent())).map(p -> p.getDate()).collect(Collectors.toSet());
    }


    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        return getCompatibleDate(after, before).stream().map(p -> p.getEvent()).collect(Collectors.toSet()).size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        return getCompatibleDate(after, before).stream().map(p -> Event.valueOf(p.getEvent())).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> ip.equals(p.getIp())).map(p -> Event.valueOf(p.getEvent())).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> user.equals(p.getUser())).map(p -> Event.valueOf(p.getEvent())).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Status.FAILED.toString().equals(p.getStatus())).map(p -> Event.valueOf(p.getEvent())).collect(Collectors.toSet());
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        return getCompatibleDate(after, before).stream().filter(p -> Status.ERROR.toString().equals(p.getStatus())).map(p -> Event.valueOf(p.getEvent())).collect(Collectors.toSet());
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getCompatibleDate(after, before).stream().filter(p -> Event.SOLVE_TASK.toString().equals(p.getEvent())).filter(p -> task == p.getTask()).count();
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        return (int) getCompatibleDate(after, before).stream().filter(p -> Event.DONE_TASK.toString().equals(p.getEvent())).filter(p -> task == p.getTask()).count();
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        HashMap<Integer, Integer> map = new HashMap<>();
        getCompatibleDate(after, before).stream().filter(p -> Event.SOLVE_TASK.toString().equals(p.getEvent())).filter(p -> !map.containsKey(p.getTask()))
                .forEach(p -> map.put(p.getTask(), getNumberOfAttemptToSolveTask(p.getTask(), after, before)));

        /*getCompatibleDate(after,before).stream().filter(p->Event.SOLVE_TASK.toString().equals(p.getEvent())).filter(p->{
            System.out.println(p.getUser()+" - "+p.getTask());
            return true;
        }).map(p->p.getTask()).collect(Collectors.toMap(p->p,p->getNumberOfAttemptToSolveTask(p,after,before)));*/
        return map;

    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        HashMap<Integer, Integer> map = new HashMap<>();
        getCompatibleDate(after, before).stream().filter(p -> Event.DONE_TASK.toString().equals(p.getEvent())).filter(p -> !map.containsKey(p.getTask()))
                .forEach(p -> map.put(p.getTask(), getNumberOfSuccessfulAttemptToSolveTask(p.getTask(), after, before)));
        return map;
    }

    @Override


    public Set<Object> execute(String query) {
        String field1 = new String();
        String field2 = new String();
        Object value = new Object();
        Object value1 = new Object();
        Object value2 = new Object();
        List<String> values = new ArrayList<>();

        Pattern pattern = Pattern.compile("\"[\\w \\.:]+\"");
        Matcher matcher = pattern.matcher(query);

        while (matcher.find()) {
            values.add(matcher.group().replace("\"", ""));
        }
        if (query.split(" ").length > 2) {
            field1 = query.split(" ")[1];
            field2 = query.split(" ")[3];
            value = values.get(0);
            if (field2.equals("date")) {
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);

                try {
                    value = dateFormat.parse(value.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }


            if (values.size() > 1) {
                value1 = values.get(1);
                value2 = values.get(2);
                Date after = new Date();
                Date before = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.ENGLISH);
                try {
                    after = dateFormat.parse(value1.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    before = dateFormat.parse(value2.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                HashSet<Line> set = new HashSet<>(getSetLine(after, before, value, field2));
                return execute2(set, field1);
            }
        } else {
            field1 = query.split(" ")[1];
            HashSet<Line> set = new HashSet<>(lineList);
            return execute2(set, field1);
        }
        HashSet<Line> set = new HashSet<>(getSetLine(null, null, value, field2));
        return execute2(set, field1);
    }


    private Set<Line> getSetLine(Date after, Date before, Object value, String f) {
        //   System.out.println(" after - "+after+" before - "+ before+ " v - "+ value+" filed2 - " +f );
        //        System.out.println(value+ " -"+value.getClass().getSimpleName()+ "    /    "+ f+ " -"+ f.getClass().getSimpleName()+"  /   ");
        if (f.equals("user")) {
            return getCompatibleDate(after, before).stream().filter(p -> {
                return value.equals(p.getUser());
            }).collect(Collectors.toSet());
        } else if (f.equals("event")) {
            return getCompatibleDate(after, before).stream().filter(p -> {
                return value.equals(p.getEvent());
            }).collect(Collectors.toSet());
        } else if (f.equals("status")) {
            return getCompatibleDate(after, before).stream().filter(p -> {
                return value.equals(p.getStatus());
            }).collect(Collectors.toSet());
        } else if (f.equals("date")) {
            return getCompatibleDate(after, before).stream().filter(p -> {
                return value.equals(p.getDate());
            }).collect(Collectors.toSet());
        } else if (f.equals("ip")) {
            return getCompatibleDate(after, before).stream().filter(p -> {
                return value.equals(p.getIp());
            }).collect(Collectors.toSet());
        } else return null;
    }

    public Set<Object> execute2(Set<Line> lineList, String query) {
        String[] array = new String[(query.split(" ")).length];
        switch (query) {
            case "ip":
                return lineList.stream().map(p -> p.getIp()).collect(Collectors.toSet());
            case "user":
                return lineList.stream().map(p -> p.getUser()).collect(Collectors.toSet());
            case "date":
                return lineList.stream().map(p -> p.getDate()).
                        /*filter(p->{
                            System.out.println(p.getClass().toString());
                            return true;
                        }).*/
                                collect(Collectors.toSet());
            case "event":
                return lineList.stream().map(p -> Event.valueOf(p.getEvent())).collect(Collectors.toSet());
            case "status":
                return lineList.stream().map(p -> Status.valueOf(p.getStatus())).collect(Collectors.toSet());
        }
        return null;
    }


}