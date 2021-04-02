package com.example.demo.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.jdbc.Work;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
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
    @Column(columnDefinition = "int default 0")
    private Integer status = 0;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}
