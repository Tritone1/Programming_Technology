/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author Barat
 */
import javax.swing.SwingUtilities;

/**
 * Main class to start the Breakthrough game application.
 * This is the entry point of the program.
 */
public class Main {
    /**
     * Main method that starts the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new GameFrame();
        });
    }
}
