package com.example.oopp;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class Student {
    private String studentId;
    private String name;
    private String password;

    //store the clubs that the student has joined
    private List<Club> joinedClubs;

    public Student(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
        this.joinedClubs = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Club> getJoinedClubs() {
        return joinedClubs;
    }

    public void setJoinedClubs(List<Club> joinedClubs) {
        this.joinedClubs = joinedClubs;
    }

    //when student joins a club.It'll add the istance to this
    public void joinClub(Club club){
        joinedClubs.add(club);
        System.out.println("You joined the club: "+ club.getClubName());  //change it to alert
    }
}
