package com.example.demo.model;

public class Rectangle {
    private Point bottomLeft;
    private double width;
    private double height;

    public Rectangle(){
        this.bottomLeft = new Point();
        this.width = 0.0;
        this.height = 0.0;
    }

    public Rectangle(Point p, double width, double height){
        this.bottomLeft = p;
        this.width = width;
        this.height = height;
    }

    public Point getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Point bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
