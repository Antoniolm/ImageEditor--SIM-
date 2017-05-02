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

public class UmbralizacionOp  extends BufferedImageOpAdapter{
    private int umbral;

    public UmbralizacionOp(int umbral) {
        this.umbral = umbral;
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
                
                if(R>=umbral ||G >=umbral || B>=umbral){
                    dest.setRGB(x, y, (255 << 16) | (255 << 8) | 255);
                }
                else{
                    dest.setRGB(x, y, (0 << 16) | (0 << 8) | 0);
                }         
            }
        }
        return dest;
    } 
}