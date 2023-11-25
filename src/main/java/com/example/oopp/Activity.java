package com.example.oopp;

public class Activity extends ScheduleActivity{
    private String activityType;

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Activity(int scheduleId, String title, String date, String time, String location, String descrition, String activityType) {
        super(scheduleId, title, date, time, location, descrition);
        this.activityType = activityType;
    }
}
