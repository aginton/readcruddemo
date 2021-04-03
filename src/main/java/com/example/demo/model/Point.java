package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Point {

    @Column(columnDefinition = "double default 0.0")
    private double x;
    @Column(columnDefinition = "double default 0.0")
    private double y;

    public Point(){
        this.x = 0.0;
        this.y = 0.0;
    }

    public Point(Point p){
        setX(p.getX());
        setY(p.getY());
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public static double getDistance(Point p1, Point p2){
        double deltaX = p1.getX()-p2.getX();
        double deltaY = p1.getY() - p2.getY();
        return Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
    }

    public boolean isWithinCircle(Circle circle){
        return getDistance(this, circle.getCenter()) <= circle.getRadius();
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
