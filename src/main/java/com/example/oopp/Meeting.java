package com.example.oopp;

public class Meeting extends ScheduleActivity{
    private String agenda;

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public Meeting(int scheduleId, String title, String date, String time, String location, String descrition, String agenda) {
        super(scheduleId, title, date, time, location, descrition);
        this.agenda = agenda;
    }

}
