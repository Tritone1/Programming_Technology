/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename;
        
        try {
            if (args.length > 0) {
                filename = args[0];
            } else {
                System.out.println("Usage: java javaapplication1.Main <filename>");
                return;
            }
            
            List<Shape> shapes = null;
            try {
                shapes = ShapeLoader.loadShapes(filename);
            } catch (InvalidShapeException e) {
                System.err.println("Error loading shapes: " + e.getMessage());
                if (e.getCause() != null) {
                    System.err.println("Caused by: " + e.getCause().getMessage());
                }
                return;
            }
            
            if (shapes == null || shapes.isEmpty()) {
                System.out.println("No shapes loaded.");
                return;
            }
            
            System.out.println("=== SHAPE ANALYSIS ===");
            System.out.println("Loaded " + shapes.size() + " shapes:");
            
            // Display all shapes with their individual bounding boxes
            for (int i = 0; i < shapes.size(); i++) {
                Shape shape = shapes.get(i);
                System.out.printf("%d. %s at (%.2f, %.2f) - size: %.2f\n", 
                    i + 1, shape.getType(), shape.getCenterX(), shape.getCenterY(), shape.getSize());
                System.out.printf("   Bounds: X[%.2f, %.2f], Y[%.2f, %.2f]\n", 
                    shape.getMinX(), shape.getMaxX(), shape.getMinY(), shape.getMaxY());
            }
            
            // Calculate bounding box
            double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
            
            for (Shape shape : shapes) {
                minX = Math.min(minX, shape.getMinX());
                maxX = Math.max(maxX, shape.getMaxX());
                minY = Math.min(minY, shape.getMinY());
                maxY = Math.max(maxY, shape.getMaxY());
            }
            
            System.out.println("\n=== BOUNDING BOX ===");
            System.out.printf("Bottom-left corner: (%.2f, %.2f)\n", minX, minY);
            System.out.printf("Top-right corner: (%.2f, %.2f)\n", maxX, maxY);
            System.out.printf("Width: %.2f\n", maxX - minX);
            System.out.printf("Height: %.2f\n", maxY - minY);
            System.out.printf("Area: %.2f\n", (maxX - minX) * (maxY - minY));
            
        } finally {
            scanner.close();
        }
    }
}
