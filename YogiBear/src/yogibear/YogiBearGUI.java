/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yogibear;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Kanan
 */
public class YogiBearGUI {
    private JFrame frame;
    private GameEngine gameArea;
    
    public YogiBearGUI() {
        frame = new JFrame("Yogi Bear - Picnic Basket Collector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Game");
        
        JMenuItem newGameItem = new JMenuItem("New Game");
        JMenuItem highScoresItem = new JMenuItem("High Scores");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        newGameItem.addActionListener(e -> {
            gameArea.restartGame();
        });
        
        highScoresItem.addActionListener(e -> {
            gameArea.showHighScores();
        });
        
        exitItem.addActionListener(e -> {
            System.exit(0);
        });
        
        gameMenu.add(newGameItem);
        gameMenu.add(highScoresItem);
        gameMenu.addSeparator();  
        gameMenu.add(exitItem);
        
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);
        
        gameArea = new GameEngine();
        frame.getContentPane().add(gameArea);
        
        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
