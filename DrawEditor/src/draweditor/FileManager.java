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
import GUI.InternalWindowImage;
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
    
    /**
     * It will return the instance of our class
     * @return 
     */
    public static FileManager getSingletonInstance() {
        if (fileManager == null){
            fileManager = new FileManager();
        }
        return fileManager;
    }
    
    /**
     * It will get the extension of a file
     * @param name
     * @return 
     */
    public String getExtension(String name){
        String result="";
        for(int i=0;i<name.length();i++){
            if(name.charAt(i)=='.')
                result=name.substring(i+1);
        }
               
        return result;
    }
    
    /**
     * The method will create a new file 
     * @param currentIntWind
     * @param window
     * @return 
     */
    public InternalWindowImage newFile(InternalWindow currentIntWind,String title,MainWindow window){
        InternalWindowImage newIntWind = new InternalWindowImage(window);
        
        if(currentIntWind!= null){
            Point2D currentPositionWind=currentIntWind.getWindow().getLocation();
            currentPositionWind.setLocation(currentPositionWind.getX()+20, currentPositionWind.getY()+20);
            newIntWind.getWindow().setLocation((Point) currentPositionWind);
        }
        
         BufferedImage img;
         img = new BufferedImage((int)Canvas2DPanel.getWidthImage(),(int)Canvas2DPanel.getHeightImage(),BufferedImage.TYPE_INT_ARGB);
         newIntWind.getCanvas().setImage(img);
         newIntWind.getCanvas().setColorT(new Color(255,255,255));
         
         Graphics2D g2d =img.createGraphics();
         g2d.fillRect(0,0,img.getWidth(),img.getHeight());
         
         newIntWind.getCanvas().setColorT(new Color(0,0,0)); 
         return newIntWind;
    }
    
    /**
     * The method will create a new file 
     * @param currentIntWind
     * @param window
     * @return 
     */
    public InternalWindowImage newFile(InternalWindow currentIntWind,String title,MainWindow window,BufferedImage img){
        InternalWindowImage newIntWind = new InternalWindowImage(window);
        
        if(currentIntWind!= null){
            Point2D currentPositionWind=currentIntWind.getWindow().getLocation();
            currentPositionWind.setLocation(currentPositionWind.getX()+20, currentPositionWind.getY()+20);
            newIntWind.getWindow().setLocation((Point) currentPositionWind);
        }
        
         newIntWind.getCanvas().setImage(img);
         newIntWind.getWindow().setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
         
         Graphics2D g2d =img.createGraphics();
         
         newIntWind.getCanvas().setClip(new Rectangle2D.Float(1,1,img.getWidth()-1,img.getHeight()-1));
         return newIntWind;
    }
    
    /**
     * The method will open a new file 
     * @param window
     * @return 
     */
    public InternalWindowImage openFile(MainWindow window){
        String[] filterList=ImageIO.getWriterFileSuffixes();
        JFileChooser dlg = new JFileChooser();
        InternalWindowImage newIntWind=null;
                              
        for(int i=0;i<filterList.length;i=i+1){
            dlg.addChoosableFileFilter(new FileNameExtensionFilter(filterList[i], filterList[i]));
        }
        
        int resp = dlg.showOpenDialog(window);
        if( resp == JFileChooser.APPROVE_OPTION) {
             try{
                File f = dlg.getSelectedFile();
                BufferedImage img = ImageIO.read(f);
                newIntWind = new InternalWindowImage(window);
                newIntWind.getCanvas().setImage(img);
                newIntWind.getWindow().setTitle(f.getName());
                newIntWind.getWindow().setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
                
                FileManager.getSingletonInstance().getExtension(f.getName());

                newIntWind.getCanvas().setClip(new Rectangle2D.Float(1,1,img.getWidth()+1,img.getHeight()+1));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(window,"Error al abrir la imagen.","Save error",JOptionPane.ERROR_MESSAGE);
            }
        }
        return newIntWind;
    }
    
    /**
     * The method will save a file
     * @param currentIntWind
     * @param window 
     */
    public void saveFile(InternalWindowImage currentIntWind,MainWindow window){
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
                currentIntWind.getWindow().setTitle(f.getName());
            }
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(window,"Error al guardar la imagen.","Open error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
}
