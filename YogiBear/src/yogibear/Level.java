/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yogibear;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 *
 * @author Kanan
 */
public class Level {
        private final int CELL_SIZE = 40;
    private ArrayList<Obstacle> obstacles;
    private ArrayList<Ranger> rangers;
    private ArrayList<Basket> baskets;
    private int startX, startY;

public Level(String levelPath) throws IOException {
        loadLevel(levelPath);
    }

private void loadLevel(String levelPath) throws IOException {
        obstacles = new ArrayList<>();
        rangers = new ArrayList<>();
        baskets = new ArrayList<>();
        
        BufferedReader reader = new BufferedReader(new FileReader(levelPath));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            
            if (parts[0].equals("M")) {
                String[] coords = parts[1].split(",");
                int x = Integer.parseInt(coords[0]) * CELL_SIZE;
                int y = Integer.parseInt(coords[1]) * CELL_SIZE;
                Image img = new ImageIcon("data/images/mountain.jpg").getImage();
                obstacles.add(new Obstacle(x, y, CELL_SIZE, CELL_SIZE, img));
            } else if (parts[0].equals("T")) {
                String[] coords = parts[1].split(",");
                int x = Integer.parseInt(coords[0]) * CELL_SIZE;
                int y = Integer.parseInt(coords[1]) * CELL_SIZE;
                Image img = new ImageIcon("data/images/tree.jpg").getImage();
                obstacles.add(new Obstacle(x, y, CELL_SIZE, CELL_SIZE, img));
            } else if (parts[0].equals("R")) {
                String[] coords = parts[1].split(",");
                int x = Integer.parseInt(coords[0]) * CELL_SIZE;
                int y = Integer.parseInt(coords[1]) * CELL_SIZE;
                boolean horizontal = parts[2].equals("H");
                Image img = new ImageIcon("data/images/ranger.jpg").getImage();
                rangers.add(new Ranger(x, y, CELL_SIZE, CELL_SIZE, img, horizontal));
            } else if (parts[0].equals("B")) {
                String[] coords = parts[1].split(",");
                int x = Integer.parseInt(coords[0]) * CELL_SIZE;
                int y = Integer.parseInt(coords[1]) * CELL_SIZE;
                Image img = new ImageIcon("data/images/basket.jpg").getImage();
                baskets.add(new Basket(x, y, CELL_SIZE, CELL_SIZE, img));
            } else if (parts[0].equals("START")) {
                String[] coords = parts[1].split(",");
                startX = Integer.parseInt(coords[0]) * CELL_SIZE;
                startY = Integer.parseInt(coords[1]) * CELL_SIZE;
            }
        }
        reader.close();
}


public void updateRangers(){
  for (Ranger ranger : rangers) {
            ranger.move(800, 600);
            for (Obstacle obs : obstacles) {
                if (ranger.collides(obs)) {
                    ranger.reverseDirection();
                }
            }
}
}


public boolean checkBasketCollection(Sprite yogi){
    for(Basket bask : baskets){
        if(!bask.isCollected() && yogi.collides(bask)){
            bask.collect();
            return true;
    }
        }
    return false;
}
 public boolean checkRangerProximity(Sprite yogi, int dangerDistance){
        for (Ranger ranger : rangers) {
            if (ranger.distanceTo(yogi) <= dangerDistance) {
                return true;
            }
        }
        return false;
    }
      public boolean isValidPosition(int x, int y, int width, int height) {
        if (x < 0 || y < 0 || x + width > 800 || y + height > 600) {
            return false;
        }
        Sprite tempSprite = new Sprite(x, y, width, height, null);
        for (Obstacle obs : obstacles) {
            if (tempSprite.collides(obs)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isComplete() {
        for (Basket basket : baskets) {
            if (!basket.isCollected()) {
                return false;
            }
        }
        return true;
    }
    
    public void draw(Graphics g) {
        for (Obstacle obs : obstacles) {
            obs.draw(g);
        }
        for (Ranger ranger : rangers) {
            ranger.draw(g);
        }
        for (Basket basket : baskets) {
            basket.draw(g);
        }
    }
     public int getStartX() { return startX; }
    public int getStartY() { return startY; }
    public int getTotalBaskets() { return baskets.size(); }

}
