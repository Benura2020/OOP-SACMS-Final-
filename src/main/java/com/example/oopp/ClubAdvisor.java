package com.example.oopp;

import java.util.ArrayList;
import java.util.List;
public class ClubAdvisor {

    private String teacherId;
    private String name;
    private String password;

    //store the clubs that the club advisor manages
    private List<Club> managedClubs;

    public ClubAdvisor(String teacherId, String name, String password) {
        this.teacherId = teacherId;
        this.name = name;
        this.password = password;
        this.managedClubs = new ArrayList<>();
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
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

    public List<Club> getManagedClubs() {
        return managedClubs;
    }

    public void setManagedClubs(List<Club> managedClubs) {
        this.managedClubs = managedClubs;
    }

    //when club advisor creates a club.It'll add the istance to this
    public void createClub(String clubName){
        Club newClub = new Club(clubName);
        managedClubs.add(newClub);
        System.out.println(name + " created the club : " + clubName);  //change it to alert
    }

}
