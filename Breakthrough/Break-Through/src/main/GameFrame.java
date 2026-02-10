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

/**
 * Main game window frame that contains the board and controls.
 */
public class GameFrame extends JFrame {
    private Board board;
    private BoardPanel boardPanel;
    private JLabel statusLabel;
    private int currentBoardSize;
    
    /**
     * Constructor for GameFrame.
     * Initializes the game with default board size of 8x8.
     */
    public GameFrame() {
        setTitle("Breakthrough Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Create menu bar
        createMenuBar();
        
        // Start with 8x8 board
        currentBoardSize = 8;
        initializeGame(currentBoardSize);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    /**
     * Creates the menu bar with game options.
     */
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu gameMenu = new JMenu("Game");
        
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> initializeGame(currentBoardSize));
        
        JMenu boardSizeMenu = new JMenu("Board Size");
        
        JMenuItem size6Item = new JMenuItem("6x6");
        size6Item.addActionListener(e -> changeBoardSize(6));
        
        JMenuItem size8Item = new JMenuItem("8x8");
        size8Item.addActionListener(e -> changeBoardSize(8));
        
        JMenuItem size10Item = new JMenuItem("10x10");
        size10Item.addActionListener(e -> changeBoardSize(10));
        
        boardSizeMenu.add(size6Item);
        boardSizeMenu.add(size8Item);
        boardSizeMenu.add(size10Item);
        
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        
        gameMenu.add(newGameItem);
        gameMenu.add(boardSizeMenu);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        
        menuBar.add(gameMenu);
        
        setJMenuBar(menuBar);
    }
    
    /**
     * Changes the board size and starts a new game.
     * @param size The new board size (6, 8, or 10)
     */
    private void changeBoardSize(int size) {
        currentBoardSize = size;
        initializeGame(size);
    }
    
    /**
     * Initializes a new game with the specified board size.
     * @param size The board size
     */
    private void initializeGame(int size) {
        // Remove existing components
        getContentPane().removeAll();
        
        // Create new board
        board = new Board(size);
        
        // Calculate cell size based on board size to keep window reasonable
        int cellSize;
        if (size == 6) {
            cellSize = 80;
        } else if (size == 8) {
            cellSize = 70;
        } else { // size == 10
            cellSize = 60;
        }
        
        // Create board panel
        boardPanel = new BoardPanel(board, cellSize);
        boardPanel.setGameFrame(this);
        
        // Create status label
        statusLabel = new JLabel("Current Player: " + board.getCurrentPlayer().getName() + 
                                " (" + board.getCurrentPlayer().getColor() + ")", 
                                SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Layout
        setLayout(new BorderLayout());
        add(boardPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        revalidate();
        repaint();
    }
    
    /**
     * Updates the status label to show the current player.
     */
    public void updateStatusLabel() {
        statusLabel.setText("Current Player: " + board.getCurrentPlayer().getName() + 
                           " (" + board.getCurrentPlayer().getColor() + ")");
    }
    
    /**
     * Announces the winner and starts a new game.
     * @param winner The winning player
     */
    public void announceWinner(Player winner) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this,
                winner.getName() + " (" + winner.getColor() + ") has won the game!",
                "Game Over",
                JOptionPane.INFORMATION_MESSAGE);
            
            // Start a new game automatically
            initializeGame(currentBoardSize);
        });
    }
}
