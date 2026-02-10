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
public class ShapeTest {
    
    private Shape instance;
    
    public ShapeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ShapeImpl();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getCenterX method, of class Shape.
     */
    @Test
    public void testGetCenterX() {
        System.out.println("getCenterX");
        double expResult = 10.0;
        double result = instance.getCenterX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getCenterY method, of class Shape.
     */
    @Test
    public void testGetCenterY() {
        System.out.println("getCenterY");
        double expResult = 20.0;
        double result = instance.getCenterY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMinX method, of class Shape.
     */
    @Test
    public void testGetMinX() {
        System.out.println("getMinX");
        double expResult = 5.0;
        double result = instance.getMinX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxX method, of class Shape.
     */
    @Test
    public void testGetMaxX() {
        System.out.println("getMaxX");
        double expResult = 15.0;
        double result = instance.getMaxX();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMinY method, of class Shape.
     */
    @Test
    public void testGetMinY() {
        System.out.println("getMinY");
        double expResult = 15.0;
        double result = instance.getMinY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxY method, of class Shape.
     */
    @Test
    public void testGetMaxY() {
        System.out.println("getMaxY");
        double expResult = 25.0;
        double result = instance.getMaxY();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getType method, of class Shape.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        String expResult = "ShapeImpl";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSize method, of class Shape.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        double expResult = 5.0;
        double result = instance.getSize();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toString method, of class Shape.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "ShapeImpl(center=(10.00,20.00), size=5.00)";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    public class ShapeImpl extends Shape {

        public ShapeImpl() {
            super(10.0, 20.0);
        }

        @Override
        public double getMinX() {
            return 5.0;
        }

        @Override
        public double getMaxX() {
            return 15.0;
        }

        @Override
        public double getMinY() {
            return 15.0;
        }

        @Override
        public double getMaxY() {
            return 25.0;
        }

        @Override
        public String getType() {
            return "ShapeImpl";
        }

        @Override
        public double getSize() {
            return 5.0;
        }
    }
    
}
