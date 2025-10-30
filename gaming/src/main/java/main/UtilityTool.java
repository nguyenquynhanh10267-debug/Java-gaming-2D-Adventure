package main;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;


public class UtilityTool {
    public BufferedImage scaleImage(BufferedImage original, int width, int height){
        // khung ve, truyen hinh anh,...
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        // Draw the original image to the scaled image with scaling
        Graphics2D g2 = scaledImage.createGraphics();
         // g2 ve hinh anh voi ti le Size x tileSize
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();
        
        return scaledImage; 
    }
}
