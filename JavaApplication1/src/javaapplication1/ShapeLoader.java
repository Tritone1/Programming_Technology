/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
import java.io.*;
import java.util.*;

public class ShapeLoader {
    public static List<Shape> loadShapes(String filename) throws InvalidShapeException {
        List<Shape> shapes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line == null) throw new InvalidShapeException("File is empty");
            int n = Integer.parseInt(line.trim());
            for (int i = 0; i < n; i++) {
                line = br.readLine();
                if (line == null) throw new InvalidShapeException("Not enough shape lines");
                String[] parts = line.trim().split("\\s+");
                if (parts.length != 4) throw new InvalidShapeException("Invalid shape line: " + line);
                String type = parts[0];
                double x = Double.parseDouble(parts[1]);
                double y = Double.parseDouble(parts[2]);
                double size = Double.parseDouble(parts[3]);
                switch (type) {
                    case "C": shapes.add(new Circle(x, y, size)); break;
                    case "T": shapes.add(new RegularTriangle(x, y, size)); break;
                    case "S": shapes.add(new Square(x, y, size)); break;
                    case "H": shapes.add(new RegularHexagon(x, y, size)); break;
                    default: throw new InvalidShapeException("Unknown shape type: " + type);
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new InvalidShapeException("Error reading file", e);
        }
        return shapes;
    }
}
