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
 * It is the abstract class of our internal windows
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public abstract class InternalWindow extends JInternalFrame{
    /** 
     * The type of our internal window
     */
    protected InternalWindowType type;
    
    /**
     * The parent of our internal window (Will be a MainWindow object) 
     */
    protected MainWindow parent=null;
    
    /**
     * It will return the type of our internal window
     * @return 
     */    
    public InternalWindowType getType(){
        return type;
    }
    
    /**
     * It will initialize the components of our internal window
     */
    public abstract void initComponents();
    
    /**
     * It will return a new object. That object will be able to be a image,video or audio internal window
     * the type is chosen for the extension.
     * @param extension The extension of our file
     * @param parent the parent of our internals windows
     * @param file the file that will show our internal window
     * @return
     * @throws IOException 
     */
    public static InternalWindow getInstance(String extension,MainWindow parent,File file) throws IOException{
        InternalWindow result=null;
        System.out.println(extension);
        if(extension.equals("jpg") || extension.equals("bmp") || extension.equals("gif") || extension.equals("png") || extension.equals("jpeg") || extension.equals("wbmp")){
            result=new InternalWindowImage(parent);
            BufferedImage img = ImageIO.read(file);
            ((InternalWindowImage)result).getCanvas().setImage(img);
            result.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
            ((InternalWindowImage)result).getCanvas().setClip(new Rectangle2D.Float(1,1,img.getWidth()+1,img.getHeight()+1));
        }
        else if(extension.equals("wav") || extension.equals("aiff") ||extension.equals("au") || extension.equals("rmf") || extension.equals("mp3")){
            result=new InternalWindowSound(file,parent);
        }
        else if(extension.equals("avi") || extension.equals("mov") || extension.equals("mpeg")){
            result=InternalWindowJMFPlayer.getInstance(file,parent);
        }
            
        return result;
    }
}
