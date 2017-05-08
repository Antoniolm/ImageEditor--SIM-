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
 *
 * @author Antonio david lopez machado
 */
public class SepiaOp extends BufferedImageOpAdapter{
    public SepiaOp () {
     }
    
    public BufferedImage filter(BufferedImage src, BufferedImage dest){
        if (src == null) {
            throw new NullPointerException("src image is null");
        }
        if (dest == null) {
            dest = createCompatibleDestImage(src, null);
        }

        int currentRGB,R,G,B,sepiaR,sepiaG,sepiaB,newRGB;
        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {
                currentRGB=src.getRGB(x, y);
                
                R= (currentRGB >> 16) & 0xFF;
                G= (currentRGB >> 8 ) & 0xFF;
                B= (currentRGB) & 0xFF;
                
                sepiaR = (int) Math.min(255 ,0.393*R + 0.769*G + 0.189*B);
                sepiaG = (int) Math.min(255, 0.349*R + 0.686*G + 0.168*B);
                sepiaB = (int) Math.min(255, 0.272*R + 0.534*G + 0.131*B);
                
                newRGB = (sepiaR << 16) | (sepiaG << 8) | sepiaB; 
                dest.setRGB(x, y, newRGB);
            }
        }
        return dest;
    }
    
}
