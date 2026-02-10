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
 * Represents a doll (game piece) on the board.
 */
public class Doll {
    private final Player owner;
    private int row;
    private int col;
    
    /**
     * Constructor for Doll.
     * @param owner The player who owns this doll
     * @param row The row position of the doll
     * @param col The column position of the doll
     */
    public Doll(Player owner, int row, int col) {
        this.owner = owner;
        this.row = row;
        this.col = col;
    }
    
    /**
     * Gets the owner of this doll.
     * @return The player who owns this doll
     */
    public Player getOwner() {
        return owner;
    }
    
    /**
     * Gets the current row position.
     * @return The row position
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Gets the current column position.
     * @return The column position
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Sets the position of this doll.
     * @param row The new row position
     * @param col The new column position
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

