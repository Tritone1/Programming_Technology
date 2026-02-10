/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
public class Square extends Shape {
    private double side;
    
    public Square(double centerX, double centerY, double side) {
        super(centerX, centerY);
        this.side = side;
    }
    
    @Override
    public double getMinX() { return centerX - side / 2; }
    
    @Override
    public double getMaxX() { return centerX + side / 2; }
    
    @Override
    public double getMinY() { return centerY - side / 2; }
    
    @Override
    public double getMaxY() { return centerY + side / 2; }
    
    @Override
    public String getType() { return "Square"; }
    
    @Override
    public double getSize() { return side; }
}
