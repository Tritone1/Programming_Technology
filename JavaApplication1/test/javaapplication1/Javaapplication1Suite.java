/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4Suite.java to edit this template
 */
package javaapplication1;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Barat
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({javaapplication1.SquareTest.class, javaapplication1.MainTest.class, javaapplication1.ShapeLoaderTest.class, javaapplication1.CircleTest.class, javaapplication1.ShapeTest.class, javaapplication1.InvalidShapeExceptionTest.class, javaapplication1.RegularHexagonTest.class, javaapplication1.RegularTriangleTest.class})
public class Javaapplication1Suite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
