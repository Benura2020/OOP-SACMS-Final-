package com.example.oopp;

public class Event extends ScheduleActivity {
    private String eventType;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Event(int scheduleId, String title, String date, String time, String location, String descrition, String eventType) {
        super(scheduleId, title, date, time, location, descrition);
        this.eventType = eventType;

    }

}
