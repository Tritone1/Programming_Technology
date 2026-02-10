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
public class RegularTriangleTest {
    
    private RegularTriangle instance;
    
    public RegularTriangleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new RegularTriangle(10.0, 20.0, 5.0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMinX method, of class RegularTriangle.
     */
    @Test
    public void testGetMinX() {
        System.out.println("getMinX");
        double expResult = 7.5;
        double result = instance.getMinX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxX method, of class RegularTriangle.
     */
    @Test
    public void testGetMaxX() {
        System.out.println("getMaxX");
        double expResult = 12.5;
        double result = instance.getMaxX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMinY method, of class RegularTriangle.
     */
    @Test
    public void testGetMinY() {
        System.out.println("getMinY");
        double expResult = 18.556624327025936;
        double result = instance.getMinY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxY method, of class RegularTriangle.
     */
    @Test
    public void testGetMaxY() {
        System.out.println("getMaxY");
        double expResult = 22.886751345948127;
        double result = instance.getMaxY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getType method, of class RegularTriangle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "RegularTriangle";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class RegularTriangle.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        double expResult = 5.0;
        double result = instance.getSize();
        assertEquals(expResult, result, 0.0);
    }
    
}
