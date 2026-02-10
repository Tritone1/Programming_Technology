/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yogibear;
import java.awt.Image;
import java.awt.Graphics;
/**
 *
 * @author Kanan
 */
public class Basket extends Sprite {
  private boolean collected=false;

  public Basket(int x, int y, int width, int height, Image image) {
      super(x, y, width, height, image);
  }
    public boolean isCollected() {
        return collected;
    }
    public void collect() {
        this.collected = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!collected) {
            super.draw(g);
        }
    }
    
}



