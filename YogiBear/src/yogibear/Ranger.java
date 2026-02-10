/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yogibear;
import java.awt.Image;

/**
 *
 * @author Kanan
 */
public class Ranger extends Sprite {
       private int velX;
    private int velY;
    private boolean movesHorizontally;

    public Ranger(int x, int y, int width, int height, Image image, boolean horizontal) {
        super(x, y, width, height, image);
        this.movesHorizontally = horizontal;
        if(horizontal){
            velX = 2;
            velY = 0;
        }else{
            velX = 0;
            velY = 2;
        }
    }
    public void move(int maxWidth,int maxHeight){
        x+=velX;
        y+=velY;

        if(x<=0 || x+width>=maxWidth){
            velX = -velX;
        }
        if(y<=0 || y+height>=maxHeight){
            velY = -velY;
        }
    }

    public void reverseDirection() {
        velX = -velX;
        velY = -velY;
    }

    public double distanceTo(Sprite other){
        int centerX=x+width/2;
        int centerY=y+height/2;
        int otherCenterX=other.getX()+other.getWidth()/2;
        int otherCenterY=other.getY()+other.getHeight()/2;

        return Math.sqrt(Math.pow(centerX-otherCenterX,2)+Math.pow(centerY-otherCenterY,2));
        
    }

}
