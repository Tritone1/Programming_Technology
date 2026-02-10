/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
public class RegularTriangle extends Shape {
    private double side;
    
    public RegularTriangle(double centerX, double centerY, double side) {
        super(centerX, centerY);
        this.side = side;
    }
    
    @Override
    public double getMinX() { 
        return centerX - side / 2; 
    }
    
    @Override
    public double getMaxX() { 
        return centerX + side / 2; 
    }
    
    @Override
    public double getMinY() { 
        // Bottom side is at centerY - height/3
        double height = side * Math.sqrt(3) / 2;
        return centerY - height / 3;
    }
    
    @Override
    public double getMaxY() { 
        // Top vertex is at centerY + 2*height/3
        double height = side * Math.sqrt(3) / 2;
        return centerY + 2 * height / 3;
    }
    
    @Override
    public String getType() { 
        return "RegularTriangle"; 
    }
    
    @Override
    public double getSize() { 
        return side; 
    }
}
