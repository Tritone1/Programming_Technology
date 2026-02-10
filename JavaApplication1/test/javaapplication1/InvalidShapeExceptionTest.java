/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javaapplication1;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 *
 * @author Barat
 */
public class InvalidShapeExceptionTest {
    
    @Test
    public void testExceptionMessage() {
        String message = "Test message";
        InvalidShapeException exception = new InvalidShapeException(message);
        assertEquals(message, exception.getMessage());
    }
}
