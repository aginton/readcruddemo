package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalTime;


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

    @Embedded
    private WorkWeek workWeek = new WorkWeek();
    //@ElementCollection
//    @OneToMany
//    private List<WorkDay> workWeek;

    public Employee(){}
    public Employee(String name, String address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
        //this.workWeek;
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

    public void updateWorkDay(Integer day, LocalTime start, LocalTime end){
        workWeek.updateDay(day, start, end);
    }
//
    public WorkWeek getWorkWeek() {
        return workWeek;
    }

    public void setWorkWeek(WorkWeek workWeek) {
        this.workWeek = workWeek;
    }

    public boolean isWorkingNow(int day, LocalTime now) {
        if (getWorkWeek() == null) return false;
        System.out.printf("Does %s work today?\n", getName());
        WorkDay workDay = workWeek.getWorkDay(day);
        if (workDay != null){
            System.out.printf("%s is working today!\n", getName());
            return workDay.worksAtTime(now);
        }
        System.out.printf("%s does NOT work today!\n", getName());
        return false;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
