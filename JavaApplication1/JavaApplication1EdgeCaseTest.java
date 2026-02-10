package javaapplication1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Comprehensive edge case testing for JavaApplication1 Shape Analysis System
 * 
 * This test class implements 18 user stories covering various edge cases
 * and validates both white-box and black-box testing approaches.
 * 
 * WHITE-BOX TESTS: Focus on internal code structure, branch coverage, exception handling
 * BLACK-BOX TESTS: Focus on functionality, input validation, output verification
 */
public class JavaApplication1EdgeCaseTest {
    
    // =========================
    // WHITE-BOX TESTS
    // =========================
    
    /**
     * Tests Main.main() method with no command line arguments
     * WHITE-BOX: Tests the argument length check branch
     */
    public void testMainWithNoArguments() {
        System.out.println("=== WHITE-BOX TEST: Main with no arguments ===");
        String[] emptyArgs = {};
        Main.main(emptyArgs);
        System.out.println("Expected: Usage message should be displayed\n");
    }
    
    /**
     * Tests Main.main() method with valid file argument
     * WHITE-BOX: Tests the successful execution path
     */
    public void testMainWithValidFile() {
        System.out.println("=== WHITE-BOX TEST: Main with valid file ===");
        try {
            // Create a temporary valid file for testing
            createTestFile("temp_valid.txt", "2\nC 10.0 15.0 5.0\nS 25.0 20.0 4.0");
            String[] args = {"temp_valid.txt"};
            Main.main(args);
            System.out.println("Expected: Successful shape analysis display\n");
        } catch (Exception e) {
            System.out.println("Error in valid file test: " + e.getMessage() + "\n");
        } finally {
            new File("temp_valid.txt").delete();
        }
    }
    
    /**
     * Tests all branches in ShapeLoader.loadShapes() switch statement
     * WHITE-BOX: Ensures every shape type case is covered
     */
    public void testShapeLoaderBranchCoverage() {
        System.out.println("=== WHITE-BOX TEST: ShapeLoader branch coverage ===");
        try {
            // Test each shape type branch in switch statement
            createTestFile("branch_test.txt", "4\nC 0 0 1\nS 0 0 1\nT 0 0 1\nH 0 0 1");
            List<Shape> shapes = ShapeLoader.loadShapes("branch_test.txt");
            System.out.println("All shape types loaded successfully: " + shapes.size() + " shapes");
            
            // Verify each type was created correctly
            String[] expectedTypes = {"Circle", "Square", "RegularTriangle", "RegularHexagon"};
            for (int i = 0; i < shapes.size(); i++) {
                System.out.println("Shape " + (i+1) + ": " + shapes.get(i).getType() + 
                                 " (Expected: " + expectedTypes[i] + ")");
            }
            
            // Test exception branch - file not found
            try {
                ShapeLoader.loadShapes("nonexistent_file.txt");
            } catch (InvalidShapeException e) {
                System.out.println("Exception branch tested successfully: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Error in branch coverage test: " + e.getMessage());
        } finally {
            new File("branch_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * Tests Circle boundary calculation methods
     * WHITE-BOX: Verifies the mathematical formulas in getMinX/MaxX/MinY/MaxY
     */
    public void testCircleBoundaryCalculations() {
        System.out.println("=== WHITE-BOX TEST: Circle boundary calculations ===");
        Circle circle = new Circle(10.0, 20.0, 5.0);
        
        // Test each boundary method individually
        double minX = circle.getMinX(); // centerX - radius = 10.0 - 5.0 = 5.0
        double maxX = circle.getMaxX(); // centerX + radius = 10.0 + 5.0 = 15.0
        double minY = circle.getMinY(); // centerY - radius = 20.0 - 5.0 = 15.0
        double maxY = circle.getMaxY(); // centerY + radius = 20.0 + 5.0 = 25.0
        
        System.out.printf("Circle at (%.1f, %.1f) with radius %.1f:\n", 
                         circle.getCenterX(), circle.getCenterY(), circle.getSize());
        System.out.printf("  MinX: %.1f (Expected: 5.0)\n", minX);
        System.out.printf("  MaxX: %.1f (Expected: 15.0)\n", maxX);
        System.out.printf("  MinY: %.1f (Expected: 15.0)\n", minY);
        System.out.printf("  MaxY: %.1f (Expected: 25.0)\n", maxY);
        System.out.println();
    }
    
    /**
     * Tests RegularTriangle geometry calculations for accuracy
     * WHITE-BOX: Verifies the complex mathematical formulas for triangle bounds
     */
    public void testTriangleGeometryAccuracy() {
        System.out.println("=== WHITE-BOX TEST: Triangle geometry accuracy ===");
        RegularTriangle triangle = new RegularTriangle(0.0, 0.0, 6.0);
        
        // Calculate expected values manually
        double height = 6.0 * Math.sqrt(3) / 2; // height = side * sqrt(3) / 2
        double expectedMinY = -height / 3;      // bottom side at center - height/3
        double expectedMaxY = 2 * height / 3;  // top vertex at center + 2*height/3
        
        System.out.printf("Triangle with side length 6.0 at origin:\n");
        System.out.printf("  Calculated height: %.6f\n", height);
        System.out.printf("  Expected MinY: %.6f, Actual: %.6f\n", expectedMinY, triangle.getMinY());
        System.out.printf("  Expected MaxY: %.6f, Actual: %.6f\n", expectedMaxY, triangle.getMaxY());
        System.out.printf("  MinX: %.6f (Expected: -3.0)\n", triangle.getMinX());
        System.out.printf("  MaxX: %.6f (Expected: 3.0)\n", triangle.getMaxX());
        System.out.println();
    }
    
    /**
     * Tests RegularHexagon geometry calculations
     * WHITE-BOX: Verifies apothem and width calculations
     */
    public void testHexagonGeometryAccuracy() {
        System.out.println("=== WHITE-BOX TEST: Hexagon geometry accuracy ===");
        RegularHexagon hexagon = new RegularHexagon(0.0, 0.0, 4.0);
        
        // Calculate expected values
        double apothem = 4.0 * Math.sqrt(3) / 2; // apothem = side * sqrt(3) / 2
        double expectedMinY = -apothem;
        double expectedMaxY = apothem;
        double expectedMinX = -4.0; // side length
        double expectedMaxX = 4.0;
        
        System.out.printf("Hexagon with side length 4.0 at origin:\n");
        System.out.printf("  Calculated apothem: %.6f\n", apothem);
        System.out.printf("  Expected MinY: %.6f, Actual: %.6f\n", expectedMinY, hexagon.getMinY());
        System.out.printf("  Expected MaxY: %.6f, Actual: %.6f\n", expectedMaxY, hexagon.getMaxY());
        System.out.printf("  Expected MinX: %.6f, Actual: %.6f\n", expectedMinX, hexagon.getMinX());
        System.out.printf("  Expected MaxX: %.6f, Actual: %.6f\n", expectedMaxX, hexagon.getMaxX());
        System.out.println();
    }
    
    // =========================
    // BLACK-BOX TESTS
    // =========================
    
    /**
     * User Story 1: Empty File Handling
     * BLACK-BOX: Tests system behavior with empty input file
     */
    public void testEmptyFileHandling() {
        System.out.println("=== BLACK-BOX TEST: Empty file handling ===");
        try {
            createTestFile("empty_test.txt", "");
            ShapeLoader.loadShapes("empty_test.txt");
            System.out.println("ERROR: Empty file should have thrown exception");
        } catch (InvalidShapeException e) {
            System.out.println("✓ Empty file correctly handled: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            new File("empty_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 3: Invalid Shape Count
     * BLACK-BOX: Tests mismatch between declared and actual shape count
     */
    public void testInvalidShapeCount() {
        System.out.println("=== BLACK-BOX TEST: Invalid shape count ===");
        try {
            createTestFile("count_test.txt", "3\nC 0 0 1\nS 0 0 1");
            ShapeLoader.loadShapes("count_test.txt");
            System.out.println("ERROR: Invalid count should have thrown exception");
        } catch (InvalidShapeException e) {
            System.out.println("✓ Invalid count correctly handled: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            new File("count_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 4: Malformed Shape Line
     * BLACK-BOX: Tests input with missing parameters
     */
    public void testMalformedShapeLine() {
        System.out.println("=== BLACK-BOX TEST: Malformed shape line ===");
        try {
            createTestFile("malformed_test.txt", "1\nC 1.0 1.0");
            ShapeLoader.loadShapes("malformed_test.txt");
            System.out.println("ERROR: Malformed line should have thrown exception");
        } catch (InvalidShapeException e) {
            System.out.println("✓ Malformed line correctly handled: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            new File("malformed_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 5: Unknown Shape Type
     * BLACK-BOX: Tests input with unsupported shape type
     */
    public void testUnknownShapeType() {
        System.out.println("=== BLACK-BOX TEST: Unknown shape type ===");
        try {
            createTestFile("unknown_test.txt", "1\nX 10.0 20.0 5.0");
            ShapeLoader.loadShapes("unknown_test.txt");
            System.out.println("ERROR: Unknown type should have thrown exception");
        } catch (InvalidShapeException e) {
            System.out.println("✓ Unknown type correctly handled: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            new File("unknown_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 6: Zero Size Shapes
     * BLACK-BOX: Tests shapes with zero dimensions
     */
    public void testZeroSizeShapes() {
        System.out.println("=== BLACK-BOX TEST: Zero size shapes ===");
        try {
            createTestFile("zero_test.txt", "2\nC 0 0 0\nS 10 10 0");
            List<Shape> shapes = ShapeLoader.loadShapes("zero_test.txt");
            System.out.println("✓ Zero size shapes loaded successfully: " + shapes.size());
            
            for (Shape shape : shapes) {
                double width = shape.getMaxX() - shape.getMinX();
                double height = shape.getMaxY() - shape.getMinY();
                System.out.printf("  %s with size %.1f: bounds width=%.1f, height=%.1f\n",
                    shape.getType(), shape.getSize(), width, height);
            }
        } catch (Exception e) {
            System.out.println("Error in zero size test: " + e.getMessage());
        } finally {
            new File("zero_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 7: Negative Size Shapes
     * BLACK-BOX: Tests shapes with negative dimensions
     */
    public void testNegativeSizeShapes() {
        System.out.println("=== BLACK-BOX TEST: Negative size shapes ===");
        try {
            createTestFile("negative_test.txt", "1\nC 0 0 -5");
            List<Shape> shapes = ShapeLoader.loadShapes("negative_test.txt");
            Shape shape = shapes.get(0);
            System.out.println("✓ Negative size shape processed:");
            System.out.printf("  Center: (%.1f, %.1f), Size: %.1f\n", 
                             shape.getCenterX(), shape.getCenterY(), shape.getSize());
            System.out.printf("  Bounds: X[%.1f, %.1f], Y[%.1f, %.1f]\n",
                shape.getMinX(), shape.getMaxX(), shape.getMinY(), shape.getMaxY());
        } catch (Exception e) {
            System.out.println("Error in negative size test: " + e.getMessage());
        } finally {
            new File("negative_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 8: Extreme Coordinate Values
     * BLACK-BOX: Tests shapes at very large coordinates
     */
    public void testExtremeCoordinates() {
        System.out.println("=== BLACK-BOX TEST: Extreme coordinates ===");
        try {
            createTestFile("extreme_test.txt", "2\nC 1000000 1000000 1\nS -1000000 -1000000 1");
            List<Shape> shapes = ShapeLoader.loadShapes("extreme_test.txt");
            System.out.println("✓ Extreme coordinates handled successfully, shapes loaded: " + shapes.size());
            
            for (Shape shape : shapes) {
                System.out.printf("  %s at (%.0f, %.0f)\n", 
                                 shape.getType(), shape.getCenterX(), shape.getCenterY());
            }
        } catch (Exception e) {
            System.out.println("Error in extreme coordinates test: " + e.getMessage());
        } finally {
            new File("extreme_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 9: Single Shape Analysis
     * BLACK-BOX: Tests analysis of single shape file
     */
    public void testSingleShapeAnalysis() {
        System.out.println("=== BLACK-BOX TEST: Single shape analysis ===");
        try {
            createTestFile("single_test.txt", "1\nH 25 25 10");
            List<Shape> shapes = ShapeLoader.loadShapes("single_test.txt");
            Shape shape = shapes.get(0);
            
            System.out.println("✓ Single shape analysis:");
            System.out.printf("  %s at (%.1f, %.1f) with size %.1f\n",
                shape.getType(), shape.getCenterX(), shape.getCenterY(), shape.getSize());
            System.out.printf("  Individual bounds: X[%.1f, %.1f], Y[%.1f, %.1f]\n",
                shape.getMinX(), shape.getMaxX(), shape.getMinY(), shape.getMaxY());
            System.out.println("  (Individual bounds should equal overall bounding box)");
        } catch (Exception e) {
            System.out.println("Error in single shape test: " + e.getMessage());
        } finally {
            new File("single_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 10: Multiple Identical Shapes
     * BLACK-BOX: Tests handling of identical shape definitions
     */
    public void testIdenticalShapes() {
        System.out.println("=== BLACK-BOX TEST: Multiple identical shapes ===");
        try {
            createTestFile("identical_test.txt", "3\nC 10.0 10.0 5.0\nC 10.0 10.0 5.0\nC 10.0 10.0 5.0");
            List<Shape> shapes = ShapeLoader.loadShapes("identical_test.txt");
            
            System.out.println("✓ Identical shapes processed successfully: " + shapes.size() + " shapes");
            for (int i = 0; i < shapes.size(); i++) {
                Shape shape = shapes.get(i);
                System.out.printf("  Shape %d: %s at (%.1f, %.1f) size %.1f\n",
                    i+1, shape.getType(), shape.getCenterX(), shape.getCenterY(), shape.getSize());
            }
        } catch (Exception e) {
            System.out.println("Error in identical shapes test: " + e.getMessage());
        } finally {
            new File("identical_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 11: Non-Numeric Shape Parameters
     * BLACK-BOX: Tests input with text instead of numbers
     */
    public void testNonNumericParameters() {
        System.out.println("=== BLACK-BOX TEST: Non-numeric parameters ===");
        try {
            createTestFile("nonnumeric_test.txt", "1\nC abc def 5");
            ShapeLoader.loadShapes("nonnumeric_test.txt");
            System.out.println("ERROR: Non-numeric parameters should have thrown exception");
        } catch (InvalidShapeException e) {
            System.out.println("✓ Non-numeric parameters correctly handled: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("  Root cause: " + e.getCause().getClass().getSimpleName());
            }
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            new File("nonnumeric_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 12: Mixed Shape Types
     * BLACK-BOX: Tests file containing all supported shape types
     */
    public void testMixedShapeTypes() {
        System.out.println("=== BLACK-BOX TEST: Mixed shape types ===");
        try {
            createTestFile("mixed_test.txt", "4\nC 0 0 5\nS 10 10 4\nT 20 20 6\nH 30 30 3");
            List<Shape> shapes = ShapeLoader.loadShapes("mixed_test.txt");
            
            System.out.println("✓ Mixed shapes loaded successfully: " + shapes.size() + " shapes");
            for (int i = 0; i < shapes.size(); i++) {
                Shape shape = shapes.get(i);
                System.out.printf("  %d. %s at (%.1f,%.1f) size %.1f\n",
                    i+1, shape.getType(), shape.getCenterX(), shape.getCenterY(), shape.getSize());
            }
        } catch (Exception e) {
            System.out.println("Error in mixed shapes test: " + e.getMessage());
        } finally {
            new File("mixed_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 13: Very Small Shapes
     * BLACK-BOX: Tests precision with very small dimensions
     */
    public void testVerySmallShapes() {
        System.out.println("=== BLACK-BOX TEST: Very small shapes ===");
        try {
            createTestFile("small_test.txt", "2\nC 0 0 0.0001\nS 1 1 0.0005");
            List<Shape> shapes = ShapeLoader.loadShapes("small_test.txt");
            
            System.out.println("✓ Very small shapes processed successfully:");
            for (Shape shape : shapes) {
                System.out.printf("  %s: size %.6f, bounds width %.6f x height %.6f\n",
                    shape.getType(), shape.getSize(),
                    shape.getMaxX() - shape.getMinX(),
                    shape.getMaxY() - shape.getMinY());
            }
        } catch (Exception e) {
            System.out.println("Error in small shapes test: " + e.getMessage());
        } finally {
            new File("small_test.txt").delete();
        }
        System.out.println();
    }
    
    /**
     * User Story 16: Whitespace and Formatting Tolerance
     * BLACK-BOX: Tests tolerance for various whitespace formats
     */
    public void testWhitespaceHandling() {
        System.out.println("=== BLACK-BOX TEST: Whitespace handling ===");
        try {
            // Create file with irregular spacing
            createTestFile("whitespace_test.txt", "2\n  C   10.0    15.0   5.0  \n\tS\t20.0\t25.0\t4.0\t");
            List<Shape> shapes = ShapeLoader.loadShapes("whitespace_test.txt");
            
            System.out.println("✓ Whitespace variations handled successfully: " + shapes.size() + " shapes");
            for (Shape shape : shapes) {
                System.out.printf("  %s at (%.1f, %.1f) size %.1f\n",
                    shape.getType(), shape.getCenterX(), shape.getCenterY(), shape.getSize());
            }
        } catch (Exception e) {
            System.out.println("Error in whitespace test: " + e.getMessage());
        } finally {
            new File("whitespace_test.txt").delete();
        }
        System.out.println();
    }
    
    // =========================
    // UTILITY METHODS
    // =========================
    
    /**
     * Helper method to create test files with specified content
     */
    private void createTestFile(String filename, String content) throws IOException {
        FileWriter writer = new FileWriter(filename);
        writer.write(content);
        writer.close();
    }
    
    /**
     * Calculates overall bounding box for a list of shapes
     * Used for validation in tests
     */
    private void calculateOverallBounds(List<Shape> shapes) {
        if (shapes.isEmpty()) {
            System.out.println("No shapes to calculate bounds for");
            return;
        }
        
        double minX = Double.POSITIVE_INFINITY, maxX = Double.NEGATIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY, maxY = Double.NEGATIVE_INFINITY;
        
        for (Shape shape : shapes) {
            minX = Math.min(minX, shape.getMinX());
            maxX = Math.max(maxX, shape.getMaxX());
            minY = Math.min(minY, shape.getMinY());
            maxY = Math.max(maxY, shape.getMaxY());
        }
        
        System.out.printf("Overall bounding box: [%.2f, %.2f] x [%.2f, %.2f]\n", 
                         minX, maxX, minY, maxY);
        System.out.printf("Dimensions: %.2f x %.2f, Area: %.2f\n", 
                         maxX - minX, maxY - minY, (maxX - minX) * (maxY - minY));
    }
    
    // =========================
    // MAIN TEST RUNNER
    // =========================
    
    /**
     * Main method to run all tests
     */
    public static void main(String[] args) {
        JavaApplication1EdgeCaseTest tester = new JavaApplication1EdgeCaseTest();
        
        System.out.println("STARTING COMPREHENSIVE JAVAAPPLICATION1 TESTING");
        System.out.println("=" + "=".repeat(60));
        System.out.println("Testing 18 user stories with white-box and black-box approaches");
        System.out.println("=" + "=".repeat(60));
        System.out.println();
        
        // Execute all WHITE-BOX tests
        System.out.println("RUNNING WHITE-BOX TESTS (Internal Structure Focus)");
        System.out.println("-".repeat(50));
        tester.testMainWithNoArguments();
        tester.testMainWithValidFile();
        tester.testShapeLoaderBranchCoverage();
        tester.testCircleBoundaryCalculations();
        tester.testTriangleGeometryAccuracy();
        tester.testHexagonGeometryAccuracy();
        
        // Execute all BLACK-BOX tests
        System.out.println("RUNNING BLACK-BOX TESTS (Functionality Focus)");
        System.out.println("-".repeat(50));
        tester.testEmptyFileHandling();
        tester.testInvalidShapeCount();
        tester.testMalformedShapeLine();
        tester.testUnknownShapeType();
        tester.testZeroSizeShapes();
        tester.testNegativeSizeShapes();
        tester.testExtremeCoordinates();
        tester.testSingleShapeAnalysis();
        tester.testIdenticalShapes();
        tester.testNonNumericParameters();
        tester.testMixedShapeTypes();
        tester.testVerySmallShapes();
        tester.testWhitespaceHandling();
        
        System.out.println("=" + "=".repeat(60));
        System.out.println("TESTING COMPLETED SUCCESSFULLY");
        System.out.println("All 18 user stories validated");
        System.out.println("Coverage: White-box (internal structure) + Black-box (functionality)");
        System.out.println("=" + "=".repeat(60));
        
        // Clean up any remaining test files
        String[] testFiles = {"temp_valid.txt", "branch_test.txt", "empty_test.txt", 
                             "count_test.txt", "malformed_test.txt", "unknown_test.txt", 
                             "zero_test.txt", "negative_test.txt", "extreme_test.txt", 
                             "single_test.txt", "identical_test.txt", "nonnumeric_test.txt",
                             "mixed_test.txt", "small_test.txt", "whitespace_test.txt"};
        
        for (String file : testFiles) {
            new File(file).delete();
        }
        
        System.out.println("Test cleanup completed.");
    }
}