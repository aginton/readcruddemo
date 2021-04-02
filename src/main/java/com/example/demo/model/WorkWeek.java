package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Embeddable
public class WorkWeek {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="sunday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="sunday_end"))
    })

    private WorkDay sunday;
    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="monday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="monday_end"))
    })
    private WorkDay monday;


    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="tuesday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="tuesday_end"))
    })
    private WorkDay tuesday;
    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="wednesday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="wednesday_end"))
    })
    private WorkDay wednesday;
    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="thursday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="thursday_end"))
    })
    private WorkDay thursday;
    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="friday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="friday_end"))
    })
    private WorkDay friday;

    @AttributeOverrides({
            @AttributeOverride(name="startTime", column=@Column(name="saturday_start")),
            @AttributeOverride(name="endTime", column=@Column(name="saturday_end"))
    })
    private WorkDay saturday;

    public WorkWeek() { }

    public WorkWeek(WorkWeek week){
        this.setSunday(week.getSunday());
        this.setMonday(week.getMonday());
        this.setTuesday(week.getTuesday());
        this.setWednesday(week.getWednesday());
        this.setThursday(week.getThursday());
        this.setFriday(week.getFriday());
        this.setSaturday(week.getSaturday());
    }


    public WorkDay getWorkDay(int day){
        System.out.println("fdafdjsa");
        switch (day){
            case 1:
                return getSunday();
            case 2:
                return getMonday();
            case 3:
                return getTuesday();
            case 4:
                return getWednesday();
            case 5:
                return getThursday();
            case 6:
                return getFriday();
            case 7:
                return getSaturday();
            default:
                return null;
        }

    }


    public WorkDay getSunday() {
        return sunday;
    }

    public void setSunday(WorkDay sunday) {
        this.sunday = sunday;
    }

    public WorkDay getMonday() {
        return monday;
    }

    public void setMonday(WorkDay monday) {
        this.monday = monday;
    }

    public WorkDay getTuesday() {
        return tuesday;
    }

    public void setTuesday(WorkDay tuesday) {
        this.tuesday = tuesday;
    }

    public WorkDay getWednesday() {
        return wednesday;
    }

    public void setWednesday(WorkDay wednesday) {
        this.wednesday = wednesday;
    }

    public WorkDay getThursday() {
        return thursday;
    }

    public void setThursday(WorkDay thursday) {
        this.thursday = thursday;
    }

    public WorkDay getFriday() {
        return friday;
    }

    public void setFriday(WorkDay friday) {
        this.friday = friday;
    }

    public WorkDay getSaturday() {
        return saturday;
    }

    public void setSaturday(WorkDay saturday) {
        this.saturday = saturday;
    }

    public void updateDay(Integer dayOfWeek, LocalTime start, LocalTime end){
        if (dayOfWeek < 1 || dayOfWeek > 7)
            return;
        switch (dayOfWeek){
            case 1:
                setSunday(new WorkDay(start, end));
                break;
            case 2:
                setMonday(new WorkDay(start, end));
                break;
            case 3:
                setTuesday(new WorkDay(start, end));
                break;
            case 4:
                setWednesday(new WorkDay(start, end));
                break;
            case 5:
                setThursday(new WorkDay(start, end));
                break;
            case 6:
                setFriday(new WorkDay(start, end));
                break;
            case 7:
                setSaturday(new WorkDay(start, end));
                break;
            default:
                System.out.println("Invalid day of week");
        }
    }
}

