/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication1;

/**
 *
 * @author WRJ1DB
 */
public class InvalidShapeException extends Exception {
    public InvalidShapeException(String message) {
        super(message);
    }
    
    public InvalidShapeException(String message, Throwable cause) {
        super(message, cause);
    }
}
