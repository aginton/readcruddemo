package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Embeddable
public class WorkDay {
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
        System.out.println("worksAtTime called!");
        return ((startTime.equals(now) || startTime.isBefore(now)) && endTime.isAfter(now));
    }

    @Override
    public String toString() {
        return "WorkDay{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
