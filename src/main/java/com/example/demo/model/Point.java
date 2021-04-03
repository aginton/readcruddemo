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

    public boolean isWithinRectangle(Rectangle rectangle){
        return
                this.getX() >= rectangle.getBottomLeft().getX()
                && this.getX() <= (rectangle.getBottomLeft().getX()+rectangle.getWidth())
                && this.getY() >= rectangle.getBottomLeft().getY()
                && this.getY() <= (rectangle.getBottomLeft().getY()+rectangle.getHeight());
    }
}
