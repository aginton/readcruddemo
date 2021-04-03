package com.example.demo.model;

public class Circle {
    private Point center;
    private double radius;


    public Circle(){
        this.center = new Point();
        this.radius = 0.0;
    }

    public Circle(Circle c){
        this.center = c.center;
        this.radius = c.radius;
    }

    public Circle(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
