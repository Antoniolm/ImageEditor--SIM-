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

package GUI;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JInternalFrame;

enum InternalWindowType{
    IMAGE,
    CAMERA,
    RECORD,
    VIDEO,
    SOUND 
}

/**
 *
 * @author LENOVO
 */
public abstract class InternalWindow extends JInternalFrame{
    InternalWindowType type;
    
    public InternalWindowType getWindow(){
        return type;
    }
    public abstract void initComponents();
    
    public static InternalWindow getInstance(String extension,MainWindow parent,File file) throws IOException{
        InternalWindow result=null;
        System.out.println(extension);
        if(extension.equals("jpg")){
            result=new InternalWindowImage(parent);
            BufferedImage img = ImageIO.read(file);
            ((InternalWindowImage)result).getCanvas().setImage(img);
            result.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
            ((InternalWindowImage)result).getCanvas().setClip(new Rectangle2D.Float(1,1,img.getWidth()+1,img.getHeight()+1));
        }
        else if(extension.equals("wav")){
            result=new InternalWindowSound(file);
        }
        else if( extension.equals("avi")){
            result=InternalWindowJMFPlayer.getInstance(file);
        }
            
        return result;
    }
}
