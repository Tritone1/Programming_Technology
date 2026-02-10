/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;


public class Circle extends Shape {
    private double radius;
    
    public Circle(double centerX, double centerY, double radius) {
        super(centerX, centerY);
        this.radius = radius;
    }
    
    @Override
    public double getMinX() { return centerX - radius; }
    
    @Override
    public double getMaxX() { return centerX + radius; }
    
    @Override
    public double getMinY() { return centerY - radius; }
    
    @Override
    public double getMaxY() { return centerY + radius; }
    
    @Override
    public String getType() { return "Circle"; }
    
    @Override
    public double getSize() { return radius; }
}
