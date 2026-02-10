/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Barat
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Panel that displays and handles interaction with the game board.
 */
public class BoardPanel extends JPanel {
    private final Board board;
    private final int cellSize;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private GameFrame gameFrame;
    
    /**
     * Constructor for BoardPanel.
     * @param board The game board to display
     * @param cellSize The size of each cell in pixels
     */
    public BoardPanel(Board board, int cellSize) {
        this.board = board;
        this.cellSize = cellSize;
        
        int boardSize = board.getSize() * cellSize;
        setPreferredSize(new Dimension(boardSize, boardSize));
        setBackground(Color.WHITE);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getX(), e.getY());
            }
        });
    }
    
    /**
     * Sets the game frame reference for callbacks.
     * @param gameFrame The game frame
     */
    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
    
    /**
     * Handles mouse clicks on the board.
     * @param x The x coordinate of the click
     * @param y The y coordinate of the click
     */
    private void handleClick(int x, int y) {
        int row = y / cellSize;
        int col = x / cellSize;
        
        // Check if click is within bounds
        if (row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            return;
        }
        
        // If no piece is selected
        if (selectedRow == -1) {
            Doll doll = board.getDoll(row, col);
            if (doll != null && doll.getOwner() == board.getCurrentPlayer()) {
                selectedRow = row;
                selectedCol = col;
                repaint();
            }
        } else {
            // Try to move the selected piece
            if (board.makeMove(selectedRow, selectedCol, row, col)) {
                selectedRow = -1;
                selectedCol = -1;
                repaint();
                
                // Check for winner
                Player winner = board.checkWinner();
                if (winner != null) {
                    if (gameFrame != null) {
                        gameFrame.announceWinner(winner);
                    }
                } else {
                    // Switch player
                    board.switchPlayer();
                    if (gameFrame != null) {
                        gameFrame.updateStatusLabel();
                    }
                }
            } else {
                // Invalid move or selecting a different piece
                Doll doll = board.getDoll(row, col);
                if (doll != null && doll.getOwner() == board.getCurrentPlayer()) {
                    selectedRow = row;
                    selectedCol = col;
                } else {
                    selectedRow = -1;
                    selectedCol = -1;
                }
                repaint();
            }
        }
    }
    
    /**
     * Paints the board and game pieces.
     * @param g The graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw checkerboard pattern
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                if ((row + col) % 2 == 0) {
                    g2d.setColor(new Color(240, 217, 181));
                } else {
                    g2d.setColor(new Color(181, 136, 99));
                }
                g2d.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
            }
        }
        
        // Highlight selected cell
        if (selectedRow != -1 && selectedCol != -1) {
            g2d.setColor(new Color(255, 255, 0, 100));
            g2d.fillRect(selectedCol * cellSize, selectedRow * cellSize, cellSize, cellSize);
            
            g2d.setColor(new Color(255, 255, 0));
            g2d.setStroke(new BasicStroke(3));
            g2d.drawRect(selectedCol * cellSize, selectedRow * cellSize, cellSize, cellSize);
        }
        
        // Draw dolls
        int dollSize = cellSize * 3 / 4;
        int offset = (cellSize - dollSize) / 2;
        
        for (int row = 0; row < board.getSize(); row++) {
            for (int col = 0; col < board.getSize(); col++) {
                Doll doll = board.getDoll(row, col);
                if (doll != null) {
                    if (doll.getOwner() == Player.PLAYER1) {
                        g2d.setColor(new Color(30, 144, 255)); // Blue
                    } else {
                        g2d.setColor(new Color(220, 20, 60)); // Red
                    }
                    g2d.fillOval(col * cellSize + offset, row * cellSize + offset, 
                               dollSize, dollSize);
                    
                    // Draw border
                    g2d.setColor(Color.BLACK);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawOval(col * cellSize + offset, row * cellSize + offset, 
                               dollSize, dollSize);
                }
            }
        }
        
        // Draw grid lines
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i <= board.getSize(); i++) {
            g2d.drawLine(0, i * cellSize, board.getSize() * cellSize, i * cellSize);
            g2d.drawLine(i * cellSize, 0, i * cellSize, board.getSize() * cellSize);
        }
    }
    
    /**
     * Resets the selected piece.
     */
    public void resetSelection() {
        selectedRow = -1;
        selectedCol = -1;
    }
}
