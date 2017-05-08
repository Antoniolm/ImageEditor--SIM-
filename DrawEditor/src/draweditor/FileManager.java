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

package draweditor;

import GUI.InternalWindow;
import GUI.MainWindow;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sm.ALM.graficos.Canvas2DPanel;

public class FileManager {
     
    private static FileManager fileManager;  
    
    private FileManager() {
    }

    public static FileManager getSingletonInstance() {
        if (fileManager == null){
            fileManager = new FileManager();
        }
        return fileManager;
    }
    
    public String getExtension(String name){
        String result="";
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)=='.')
                result=name.substring(i+1);
        }
               
        return result;
    }
    
    public InternalWindow newFile(InternalWindow currentIntWind,MainWindow window){
        InternalWindow newIntWind = new InternalWindow(window);
        
        if(currentIntWind!= null){
            Point2D currentPositionWind=currentIntWind.getLocation();
            currentPositionWind.setLocation(currentPositionWind.getX()+20, currentPositionWind.getY()+20);
            newIntWind.setLocation((Point) currentPositionWind);
        }
        
         BufferedImage img;
         img = new BufferedImage((int)Canvas2DPanel.getWidthImage(),(int)Canvas2DPanel.getHeightImage(),BufferedImage.TYPE_INT_ARGB);
         newIntWind.getCanvas().setImage(img);
         newIntWind.getCanvas().setColor(new Color(255,255,255));
         
         Graphics2D g2d =img.createGraphics();
         g2d.fillRect(0,0,img.getWidth(),img.getHeight());
         
         newIntWind.getCanvas().setColor(new Color(0,0,0)); 
         return newIntWind;
    }
    
    public InternalWindow openFile(MainWindow window){
        String[] filterList=ImageIO.getWriterFileSuffixes();
        JFileChooser dlg = new JFileChooser();
        InternalWindow newIntWind=null;
                              
        for(int i=0;i<filterList.length;i=i+1){
            dlg.addChoosableFileFilter(new FileNameExtensionFilter(filterList[i], filterList[i]));
        }
        
        int resp = dlg.showOpenDialog(window);
        if( resp == JFileChooser.APPROVE_OPTION) {
             try{
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                newIntWind = new InternalWindow(window);
                newIntWind.getCanvas().setImage(img);
                newIntWind.setTitle(f.getName());
                newIntWind.setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
                
                FileManager.getSingletonInstance().getExtension(f.getName());

                newIntWind.getCanvas().setClip(new Rectangle2D.Float(1,1,img.getWidth()-1,img.getHeight()-1));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(window,"Error al guardar la imagen.","Save error",JOptionPane.ERROR_MESSAGE);
            }
        }
        return newIntWind;
    }
    
    public void saveFile(InternalWindow currentIntWind,MainWindow window){
        String[] filterList=ImageIO.getWriterFileSuffixes();
        JFileChooser dlg = new JFileChooser();
       
        for(int i=0;i<filterList.length;i=i+1){
            dlg.addChoosableFileFilter(new FileNameExtensionFilter(filterList[i], filterList[i]));
        }
        
        int resp = dlg.showSaveDialog(window);
        if( resp == JFileChooser.APPROVE_OPTION) {
            try {
            BufferedImage img = currentIntWind.getCanvas().getImage(true);
            if (img != null) {
                File f = dlg.getSelectedFile();
                ImageIO.write(img, getExtension(f.getName()), f);
                currentIntWind.setTitle(f.getName());
            }
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(window,"Error al abrir la imagen.","Open error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
}
