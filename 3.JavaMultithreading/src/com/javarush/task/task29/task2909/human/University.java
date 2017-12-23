package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private String name;
    private int age;
    private List<Student> students = new ArrayList<>();

    public University(String name,int age) {
        this.age = age;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student studentResult = null;
        for (Student student: students){
            if(student.getAverageGrade()==averageGrade)
            {
                studentResult = student;
                break;
            }
        }
        return studentResult;
        //TODO:

    }

    public Student getStudentWithMaxAverageGrade() {
        Student studentResult = students.get(0);
        for (Student student: students){
            if(student.getAverageGrade()>studentResult.getAverageGrade())
            {
                studentResult = student;
            }
        }
        return studentResult;//TODO:
     //   return null;
    }
public Student  getStudentWithMinAverageGrade(){
    Student studentResult = students.get(0);
        for (Student student: students){
            if(student.getAverageGrade()<studentResult.getAverageGrade())
            {
                studentResult = student;
            }
        }
        return studentResult;
    }
    public void expel(Student student){students.remove(student);}


    /*public void getStudentWithMinAverageGradeAndExpel() {

        //TODO:
    }*/
}