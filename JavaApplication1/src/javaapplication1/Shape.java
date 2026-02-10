/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
public abstract class Shape {
    protected double centerX;
    protected double centerY;

    public Shape(double centerX, double centerY) {
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public double getCenterX() {
        return centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public abstract double getMinX();
    public abstract double getMaxX();
    public abstract double getMinY();
    public abstract double getMaxY();
    public abstract String getType();
    public abstract double getSize(); // returns side length or radius
    
    @Override
    public String toString() {
        return String.format("%s(center=(%.2f,%.2f), size=%.2f)", 
                           getType(), centerX, centerY, getSize());
    }
}
