package com.example.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Integer age;
    private Point location = new Point();

    @ElementCollection
    private Map<String, WorkDay> workWeek;


    public Employee(){}

    public Employee(Employee employee_details){
        setName(employee_details.getName());
        setLocation(employee_details.getLocation());
        setAddress(employee_details.getAddress());
        setId(employee_details.getId());
        setAge(employee_details.getAge());
        setWorkWeek(employee_details.getWorkWeek());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public Map<String, WorkDay> getWorkWeek() {
        return workWeek;
    }

    public void setWorkWeek(Map<String, WorkDay> workWeek) {
        this.workWeek = new HashMap<>();
        List<String> days = CONSTANTS.getDaysOfWeek();

        for (Map.Entry<String, WorkDay> entry: workWeek.entrySet()){
            if (CONSTANTS.getDaysOfWeek().contains(entry.getKey())){
                this.workWeek.put(entry.getKey(), new WorkDay(entry.getValue()));
            }
        }
    }

    public boolean isWorkingNow(int day, LocalTime now) {
        System.out.printf("Is %s working today?: ", getName());
        if (getWorkWeek() == null){
            System.out.printf("getWorkWeek() for %s returned null!", getName());
            return false;
        }
        if (day < 1 || day > 7){
            System.out.printf("getWorkWeek() called with invalid day id %d!", day);
            return false;
        }
        String day_str = CONSTANTS.getDaysOfWeek().get(day-1);
        boolean ans = false;
        WorkDay workDay = workWeek.get(day_str);
        if (workDay != null){
            ans = workDay.worksAtTime(now);
        }
        return ans;
    }


    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
