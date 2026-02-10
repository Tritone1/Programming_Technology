/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javaapplication1;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

/**
 *
 * @author Barat
 */
public class ShapeLoaderTest {

    // Gray-Box Test: Checks the overall success functionality
    @Test
    public void testLoadShapes_Successful() throws Exception {
        System.out.println("loadShapes - Successful");
        List<Shape> result = ShapeLoader.loadShapes("shapes.txt");
        assertNotNull(result);
        assertEquals(5, result.size());
        assertTrue(result.get(0) instanceof Circle);
        assertTrue(result.get(3) instanceof RegularHexagon);
    }

    // Gray-Box Test: Checks the overall failure functionality for a file that doesn't exist
    @Test(expected = InvalidShapeException.class)
    public void testLoadShapes_FileNotFound() throws Exception {
        System.out.println("loadShapes - File Not Found");
        ShapeLoader.loadShapes("nonexistent.txt");
    }

    // White-Box Test: Targets the specific "if (line == null)" branch for empty files
    @Test(expected = InvalidShapeException.class)
    public void testLoadShapes_EmptyFile() throws Exception {
        System.out.println("loadShapes - Empty File");
        ShapeLoader.loadShapes("empty.txt");
    }

    // White-Box Test: Targets the specific "if (line == null)" branch inside the loop
    @Test(expected = InvalidShapeException.class)
    public void testLoadShapes_IncompleteFile() throws Exception {
        System.out.println("loadShapes - Incomplete File");
        ShapeLoader.loadShapes("incomplete.txt");
    }

    // White-Box Test: Targets the specific "if (parts.length != 4)" branch
    @Test(expected = InvalidShapeException.class)
    public void testLoadShapes_MalformedLine() throws Exception {
        System.out.println("loadShapes - Malformed Line");
        ShapeLoader.loadShapes("malformed.txt");
    }

    // White-Box Test: Targets the "default" case in the switch statement
    @Test(expected = InvalidShapeException.class)
    public void testLoadShapes_UnknownType() throws Exception {
        System.out.println("loadShapes - Unknown Type");
        ShapeLoader.loadShapes("unknown.txt");
    }
}
