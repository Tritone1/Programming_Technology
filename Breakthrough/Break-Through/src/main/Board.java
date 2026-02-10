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
 * Represents the game board and manages game state.
 */
public class Board {
    private final int size;
    private final Doll[][] grid;
    private Player currentPlayer;
    
    /**
     * Constructor for Board.
     * @param size The size of the board (6, 8, or 10)
     */
    public Board(int size) {
        this.size = size;
        this.grid = new Doll[size][size];
        this.currentPlayer = Player.PLAYER1;
        initializeBoard();
    }
    
    /**
     * Initializes the board with dolls in starting positions.
     */
    private void initializeBoard() {
        // Place Player 1's dolls in the first two rows
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Doll(Player.PLAYER1, row, col);
            }
        }
        
        // Place Player 2's dolls in the last two rows
        for (int row = size - 2; row < size; row++) {
            for (int col = 0; col < size; col++) {
                grid[row][col] = new Doll(Player.PLAYER2, row, col);
            }
        }
    }
    
    /**
     * Gets the board size.
     * @return The size of the board
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Gets the doll at the specified position.
     * @param row The row position
     * @param col The column position
     * @return The doll at the position, or null if empty
     */
    public Doll getDoll(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return null;
        }
        return grid[row][col];
    }
    
    /**
     * Gets the current player.
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    /**
     * Switches to the next player's turn.
     */
    public void switchPlayer() {
        currentPlayer = currentPlayer.getOpponent();
    }
    
    /**
     * Checks if a move is valid.
     * @param fromRow The starting row
     * @param fromCol The starting column
     * @param toRow The destination row
     * @param toCol The destination column
     * @return true if the move is valid, false otherwise
     */
    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if positions are within bounds
        if (fromRow < 0 || fromRow >= size || fromCol < 0 || fromCol >= size ||
            toRow < 0 || toRow >= size || toCol < 0 || toCol >= size) {
            return false;
        }
        
        Doll doll = grid[fromRow][fromCol];
        
        // Check if there's a doll at the starting position
        if (doll == null) {
            return false;
        }
        
        // Check if it's the current player's doll
        if (doll.getOwner() != currentPlayer) {
            return false;
        }
        
        Doll targetDoll = grid[toRow][toCol];
        int rowDiff = toRow - fromRow;
        int colDiff = Math.abs(toCol - fromCol);
        
        // Player 1 moves forward (increasing row), Player 2 moves backward (decreasing row)
        int expectedDirection = (currentPlayer == Player.PLAYER1) ? 1 : -1;
        
        // Check if moving in the correct direction (one step forward)
        if (rowDiff != expectedDirection) {
            return false;
        }
        
        // Check column difference (0 for straight, 1 for diagonal)
        if (colDiff > 1) {
            return false;
        }
        
        // Straight move: target must be empty
        if (colDiff == 0) {
            return targetDoll == null;
        }
        
        // Diagonal move: target must have opponent's doll
        if (colDiff == 1) {
            return targetDoll != null && targetDoll.getOwner() == currentPlayer.getOpponent();
        }
        
        return false;
    }
    
    /**
     * Executes a move on the board.
     * @param fromRow The starting row
     * @param fromCol The starting column
     * @param toRow The destination row
     * @param toCol The destination column
     * @return true if the move was executed, false otherwise
     */
    public boolean makeMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
            return false;
        }
        
        Doll doll = grid[fromRow][fromCol];
        grid[toRow][toCol] = doll;
        grid[fromRow][fromCol] = null;
        doll.setPosition(toRow, toCol);
        
        return true;
    }
    
    /**
     * Checks if the game has been won.
     * @return The winning player, or null if the game is not over
     */
    public Player checkWinner() {
        // Check if any Player 1 doll reached the last row
        for (int col = 0; col < size; col++) {
            Doll doll = grid[size - 1][col];
            if (doll != null && doll.getOwner() == Player.PLAYER1) {
                return Player.PLAYER1;
            }
        }
        
        // Check if any Player 2 doll reached the first row
        for (int col = 0; col < size; col++) {
            Doll doll = grid[0][col];
            if (doll != null && doll.getOwner() == Player.PLAYER2) {
                return Player.PLAYER2;
            }
        }
        
        return null;
    }
}
