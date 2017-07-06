package ru.yanchenko.vlad.graphicsapps.beans;
import java.awt.image.BufferedImage;

/**
 * This is a ball that stands still on a screen and also the one that a scattered ball is to come to.
 * @author Влад
 */
public class Ball extends Dot {
    
    // An image to draw a ball. Images are placed in a pics folder.
    private BufferedImage image;

    // Constructor populates a coordinate of this ball.
    public Ball(int x, int y, boolean random) {
        super(x, y, random);
    }
    
    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    
    /**
     * @return the image
     */
    public BufferedImage getImage() {
        return image;
    }
    
    /**
     * @param image the image to set
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    //</editor-fold>
    
}
