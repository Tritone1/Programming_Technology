/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javaapplication1;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Barat
 */
public class CircleTest {
    
    private Circle instance;
    
    public CircleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Circle(10.0, 20.0, 5.0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMinX method, of class Circle.
     */
    @Test
    public void testGetMinX() {
        System.out.println("getMinX");
        double expResult = 5.0;
        double result = instance.getMinX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxX method, of class Circle.
     */
    @Test
    public void testGetMaxX() {
        System.out.println("getMaxX");
        double expResult = 15.0;
        double result = instance.getMaxX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMinY method, of class Circle.
     */
    @Test
    public void testGetMinY() {
        System.out.println("getMinY");
        double expResult = 15.0;
        double result = instance.getMinY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxY method, of class Circle.
     */
    @Test
    public void testGetMaxY() {
        System.out.println("getMaxY");
        double expResult = 25.0;
        double result = instance.getMaxY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getType method, of class Circle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "Circle";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class Circle.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        double expResult = 5.0;
        double result = instance.getSize();
        assertEquals(expResult, result, 0.0);
    }
    
}
