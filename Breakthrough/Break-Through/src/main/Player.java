/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Barat
 */

/**
 * Represents a player in the Breakthrough game.
 */
public enum Player {
    PLAYER1("Player 1", "Blue"),
    PLAYER2("Player 2", "Red");
    
    private final String name;
    private final String color;
    
    /**
     * Constructor for Player enum.
     * @param name The display name of the player
     * @param color The color representing the player
     */
    Player(String name, String color) {
        this.name = name;
        this.color = color;
    }
    
    /**
     * Gets the display name of the player.
     * @return The player's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the color representing the player.
     * @return The player's color
     */
    public String getColor() {
        return color;
    }
    
    /**
     * Gets the opponent of this player.
     * @return The opponent player
     */
    public Player getOpponent() {
        return this == PLAYER1 ? PLAYER2 : PLAYER1;
    }
}
