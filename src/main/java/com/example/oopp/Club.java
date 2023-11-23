package com.example.oopp;

import java.util.List;

public class Club {
    private String name;
    private String description;
    private String advisor;
    private List<String> members;

    public Club(String name, String description, String advisor, List<String> members) {
        this.name = name;
        this.description = description;
        this.advisor = advisor;
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }
}
