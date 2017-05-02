/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ALM.imagen;

import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 *
 * @author LENOVO
 */
public class MybufferedImageOp extends BufferedImageOpAdapter{
    public MybufferedImageOp() {
    }
    
    public BufferedImage filter(BufferedImage src, BufferedImage dest){
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                int currentRGB=src.getRGB(x, y);
                
                int R= (currentRGB >> 16) & 0xFF;
                int G= (currentRGB >> 8 ) & 0xFF;
                int B= (currentRGB) & 0xFF;
                
                G=(R+G)%255;
                B=(G+B)%255;
                
                dest.setRGB(x, y, (R << 16) | (G << 8) | B);
            }
        }
        return dest;
    } 
}