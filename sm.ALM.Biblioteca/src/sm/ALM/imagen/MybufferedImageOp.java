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

        int mediaR=0,mediaG=0,mediaB=0;
        int nPixel=0;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                int currentRGB=src.getRGB(x, y);
                
                int R= (currentRGB >> 16) & 0xFF;
                int G= (currentRGB >> 8 ) & 0xFF;
                int B= (currentRGB) & 0xFF;
                
                mediaR+=R;
                mediaG+=G;
                mediaB+=B;
                nPixel++;
            }
        }
        
        mediaR/=nPixel;
        mediaG/=nPixel;
        mediaB/=nPixel;
        
        int umbralMedia=(mediaR+mediaG+mediaB)/3;
        
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                int currentRGB=src.getRGB(x, y);
                
                int R= (currentRGB >> 16) & 0xFF;
                int G= (currentRGB >> 8 ) & 0xFF;
                int B= (currentRGB) & 0xFF;
                
                if((R+G+B)/3>=umbralMedia){
                    dest.setRGB(x, y, (R << 16) | (G << 8) | B);
                }
                else{
                    dest.setRGB(x, y, (0 << 16) | (0 << 8) | 0);
                }
            }
        }
        
        return dest;
    } 
}