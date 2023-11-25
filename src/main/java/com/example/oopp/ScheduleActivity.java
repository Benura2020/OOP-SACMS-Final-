package com.example.oopp;

public abstract class ScheduleActivity {
    private int eventId;
    private String title;
    private String date;
    private String time;
    private String location;
    private String description;


    public  int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ScheduleActivity(int scheduleId, String title, String date, String time, String location, String description) {
        this.eventId = scheduleId;
        this.title = title;
        this.date = date;
        this.time = time;
        this.location = location;
        this.description = description;

    }

}
