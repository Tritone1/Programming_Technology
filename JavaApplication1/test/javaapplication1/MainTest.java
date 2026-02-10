/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package javaapplication1;

import org.junit.Test;

/**
 *
 * @author Barat
 */
public class MainTest {
    
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = {"shapes.txt"};
        Main.main(args);
    }
    
    @Test
    public void testMain_FileNotFound() {
        System.out.println("main - File Not Found");
        String[] args = {"nonexistent.txt"};
        Main.main(args);
    }
    
    @Test
    public void testMain_NoArgs() {
        System.out.println("main - No Args");
        String[] args = {};
        Main.main(args);
    }
}
