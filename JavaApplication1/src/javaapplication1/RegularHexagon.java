/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
public class RegularHexagon extends Shape {
    private double side;
    
    public RegularHexagon(double centerX, double centerY, double side) {
        super(centerX, centerY);
        this.side = side;
    }
    
    @Override
    public double getMinX() { 
        // Hexagon with flat side at bottom extends side/2 to each side from center
        return centerX - side; 
    }
    
    @Override
    public double getMaxX() { 
        return centerX + side; 
    }
    
    @Override
    public double getMinY() { 
        // Bottom flat side is at centerY - apothem
        double apothem = side * Math.sqrt(3) / 2;
        return centerY - apothem;
    }
    
    @Override
    public double getMaxY() { 
        // Top flat side is at centerY + apothem  
        double apothem = side * Math.sqrt(3) / 2;
        return centerY + apothem;
    }
    
    @Override
    public String getType() { 
        return "RegularHexagon"; 
    }
    
    @Override
    public double getSize() { 
        return side; 
    }
}
