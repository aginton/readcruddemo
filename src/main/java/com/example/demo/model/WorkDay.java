package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Embeddable
public class WorkDay {
//    private Time startTime;
//    private Time endTime;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkDay(){}

    public WorkDay(WorkDay day){
        this.startTime=day.getStartTime();
        this.endTime=day.getEndTime();
    }

    public WorkDay(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //    public Time getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(Time startTime) {
//        this.startTime = startTime;
//    }
//
//    public Time getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(Time endTime) {
//        this.endTime = endTime;
//    }


    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean worksAtTime(LocalTime now) {
        return ((startTime.equals(now) || startTime.isBefore(now)) && endTime.isAfter(now));
    }
}
