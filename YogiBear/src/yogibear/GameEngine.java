/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yogibear;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
/**
 *
 * @author Kanan
 */
public class GameEngine extends JPanel {
    private final int FPS = 60;
    private final int YOGI_SIZE = 40;
    private final int YOGI_SPEED = 3;
    private final int RANGER_DANGER_DISTANCE = 60; 
    private boolean paused = false;
    private Image background;
    private int levelNum = 0;
    private int lives = 3;
    private int basketsCollected = 0;
    private long startTime;
    private Level level;
    private YogiSprite yogi;
    private Timer newFrameTimer;
    private boolean wPressed = false;
    private boolean aPressed = false;
    private boolean sPressed = false;
    private boolean dPressed = false;
    
   public GameEngine() {
        super();
        setFocusable(true);
        requestFocusInWindow();
        background = new ImageIcon("data/images/background.jpg").getImage();
        setupKeyBindings();
        restart();
        newFrameTimer = new Timer(1000 / FPS, new NewFrameListener());
        newFrameTimer.start();
    }
     private void setupKeyBindings() {
        this.getInputMap().put(KeyStroke.getKeyStroke("pressed W"), "press w");
        this.getActionMap().put("press w", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                wPressed = true;
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released W"), "release w");
        this.getActionMap().put("release w", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                wPressed = false;
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("pressed A"), "press a");
        this.getActionMap().put("press a", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                aPressed = true;
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released A"), "release a");
        this.getActionMap().put("release a", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                aPressed = false;
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("pressed S"), "press s");
        this.getActionMap().put("press s", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                sPressed = true;
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released S"), "release s");
        this.getActionMap().put("release s", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                sPressed = false;
            }
        });
        
        this.getInputMap().put(KeyStroke.getKeyStroke("pressed D"), "press d");
        this.getActionMap().put("press d", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                dPressed = true;
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("released D"), "release d");
        this.getActionMap().put("release d", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                dPressed = false;
            }
        });

        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "escape");
        this.getActionMap().put("escape", new AbstractAction() {
            public void actionPerformed(ActionEvent ae) {
                paused = !paused;
            }
        });
    }
    public void restart() {
        try {
            // Reset key states
            wPressed = false;
            aPressed = false;
            sPressed = false;
            dPressed = false;
            
            level = new Level("data/levels/level0" + levelNum + ".txt");
            startTime = System.currentTimeMillis();
            lives = 3;
            basketsCollected = 0;
            Image yogiImage = new ImageIcon("data/images/YogiBear.jpg").getImage();
            yogi = new YogiSprite(level.getStartX(), level.getStartY(), 
                                  YOGI_SIZE, YOGI_SIZE, yogiImage);
        } catch (IOException ex) {
            System.err.println("Error loading level: " + ex.getMessage());
        }
    }
    
    public void restartGame() {
        levelNum = 0;
        paused = false;
        restart();
        requestFocusInWindow();  
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(background, 0, 0, 800, 600, null);
        level.draw(g);
        yogi.draw(g);
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Lives: " + lives, 10, 20);
        g.drawString("Baskets: " + basketsCollected + "/" + level.getTotalBaskets(), 10, 40);
        g.drawString("Time: " + getElapsedTime() + "s", 10, 60);
        g.drawString("Level: " + (levelNum + 1), 10, 80);
        
        if (paused) {
            g.drawString("PAUSED", 350, 300);
        }
    }
    private long getElapsedTime() {
        return (System.currentTimeMillis() - startTime) / 1000;
    }
    
    class NewFrameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent ae) {
            if (!paused) {
                updateYogiPosition();
                
                level.updateRangers();
                
                if (level.checkBasketCollection(yogi)) {
                    basketsCollected++;
                }
                
                if (level.checkRangerProximity(yogi, RANGER_DANGER_DISTANCE)) {
                    lives--;
                    if (lives > 0) {
                        respawnYogi();
                    } else {
                        
                    }
                }
                
                if (level.isComplete()) {
                    levelNum++;
                    if (levelNum >= 3) {
                        levelNum = 0;
                        gameOver(); 
                    }
                    restart();
                }
            }
            repaint();
        }
    }
    
    private void updateYogiPosition() {
        int newX = yogi.getX();
        int newY = yogi.getY();
        
        if (wPressed) newY -= YOGI_SPEED;
        if (sPressed) newY += YOGI_SPEED;
        if (aPressed) newX -= YOGI_SPEED;
        if (dPressed) newX += YOGI_SPEED;
        
        if (level.isValidPosition(newX, newY, YOGI_SIZE, YOGI_SIZE)) {
            yogi.setX(newX);
            yogi.setY(newY);
        }
    }
    private void respawnYogi() {
        wPressed = false;
        aPressed = false;
        sPressed = false;
        dPressed = false;
        
        yogi.setX(level.getStartX());
        yogi.setY(level.getStartY());
    }
    private void gameOver() {
        paused = true;
        int score = basketsCollected * 100 - (int)getElapsedTime();
        
        String name = JOptionPane.showInputDialog(this, 
            "Game Over! Score: " + score + "\nEnter your name:");
        
        if (name != null && !name.trim().isEmpty()) {
            HighScoreManager.saveScore(name, score, levelNum + 1, getElapsedTime());
        }
        
        String[] options = {"Restart Game", "View High Scores", "Exit"};
        int choice = JOptionPane.showOptionDialog(this,
            "Your Score: " + score + "\nWhat would you like to do?",
            "Game Over",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        if (choice == 0) {
            levelNum = 0;
            restart();
            paused = false;
        } else if (choice == 1) {
            showHighScores();
            levelNum = 0;
            restart();
            paused = false;
        } else {
            System.exit(0);
        }
    }
    
    public void showHighScores() {
        paused = true;
        String scores = HighScoreManager.getTopScoresString();
        JOptionPane.showMessageDialog(this, scores, "High Scores", 
                                      JOptionPane.INFORMATION_MESSAGE);
        paused = false;
        requestFocusInWindow();  
    }

}
