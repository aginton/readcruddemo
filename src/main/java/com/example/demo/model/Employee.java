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
        setId(employee_details.getId());
        setName(employee_details.getName());
        setAge(employee_details.getAge());
        setAddress(employee_details.getAddress());
        setLocation(employee_details.getLocation());
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

    public boolean isWorkingNow(int day, LocalTime now) throws Exception {
        if (getWorkWeek() == null){
            throw new Exception("getWorkWeek() for " + getName() + " returned null!");
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", location=" + location +
                ", workWeek=" + workWeek +
                '}';
    }
}
