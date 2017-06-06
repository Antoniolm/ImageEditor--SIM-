// *********************************************************************
// **
// ** Copyright (C) 2017 Antonio David López Machado
// **
// ** This program is free software: you can redistribute it and/or modify
// ** it under the terms of the GNU General Public License as published by
// ** the Free Software Foundation, either version 3 of the License, or
// ** (at your option) any later version.
// **
// ** This program is distributed in the hope that it will be useful,
// ** but WITHOUT ANY WARRANTY; without even the implied warranty of
// ** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// ** GNU General Public License for more details.
// **
// ** You should have received a copy of the GNU General Public License
// ** along with this program.  If not, see <http://www.gnu.org/licenses/>.
// **
// *********************************************************************

package sm.ALM.imagen;

import java.awt.image.BufferedImage;
import sm.image.BufferedImageOpAdapter;

/**
 * That class will apply my own filter to our images.
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class MybufferedImageOp extends BufferedImageOpAdapter{
    /**
     * Constructor
     */
    public MybufferedImageOp() {
    }
    
    /**
     * It will apply the filter in our source image
     * @param src original image
     * @param dest result image
     * @return the filtered image
     */
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