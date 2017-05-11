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

package sm.ALM.graficos;

import static sm.ALM.graficos.Canvas2DPanel.*;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import static sm.ALM.graficos.GeometryType.*;


public class Canvas2DPanel extends javax.swing.JPanel {
    GeometryType geometry;
    protected BufferedImage image;
    protected Point initPos;
    protected Point offSet;
    private int currentColor;
    static float widthSize=300;
    static float heightSize=300;
    Shape currentShape;
    FigureManager vShape;
    Shape clipShape;
    boolean editMode;
    
    public Canvas2DPanel() {
        initComponents();
        initPos=new Point(0,0);
        offSet=new Point(0,0);
        vShape = new FigureManager();
        editMode=false;
        geometry=POINT;
        setBackground(Color.white);
        currentColor=0;
        
        //Added our mouseListener 
        addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if(editMode) vShape.getSelectedShape(e.getPoint(),offSet);
                    else{
                        initPos=e.getPoint();   
                        
                        Graphics2D g2d=(Graphics2D) image.getGraphics();
                        vShape.draw(g2d);
                        
                        vShape.createShape(geometry,initPos);
                    }
                repaint();                  
                }
                @Override
                public void mouseReleased(MouseEvent e){
                    if(editMode)
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    else
                        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
                    repaint();
                }
        });
        
        //Added our mouseMotionListener 
        addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e){
                    if(editMode){
                        vShape.setLocationShape(e.getPoint(),offSet);
                        setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    }
                    else{
                        vShape.updateShape(e.getPoint(),initPos,geometry);
                    }
                repaint();                  
                }
                
        }); 
    }
    
    /**
     * 
     * @param g 
     */
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        if(clipShape!=null)
            g2d.clip(clipShape);
        
        vShape.draw(g2d);
    }
    
    /**
     * 
     * @return 
     */
    public GeometryType getGeometry(){
        return geometry;
    }
    
    /**
     * 
     * @param aType 
     */
    public void setGeometry(GeometryType aType){
        geometry=aType;
    }
    
    /**
     * 
     * @param value 
     */
    public void setColor(Paint value){
        vShape.getAttribute().setFilled(value);
        repaint();
    }
        
    /**
     * 
     * @param boolValue 
     */
    public void setFilled(boolean boolValue){
        vShape.getAttribute().filled=boolValue;
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getFilled(){            
        return vShape.getAttribute().getFilled();
    }
    
    /**
     * 
     * @param stroke
     * @param value 
     */
    public void setThick(Stroke stroke,Integer value){
        vShape.getAttribute().setStroke(stroke,value);
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public Integer getThick(){
        return vShape.getAttribute().getThickness();
    }
    
    /**
     * 
     * @param value 
     */
    public void setTransparency(Composite value){
        vShape.getAttribute().setComp(value);
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getTransparency(){
        boolean result=false;
        
        if(vShape.getAttribute().getComp()!=null)
            result=true;
            
        return result;
    }
    
    /**
     * 
     * @param value 
     */
    public void setRender(RenderingHints value){
        vShape.getAttribute().setRender(value);
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getRender(){
        boolean result=false;
        if(vShape.getAttribute().getRender()!=null)
            result=true;
        
        return result;
    }
    
    /**
     * 
     * @param value 
     */
    public void setEdit(boolean value){
        editMode=value;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getEdit(){
        return editMode;
    }
    
    /**
     * 
     * @param newClip 
     */
    public void setClip(Shape newClip){
        clipShape=newClip;
    }
    
    /**
     * 
     * @return 
     */
    public Shape getClip(){
        return clipShape;
    }
    
    /**
     * 
     * @param width
     * @param height 
     */
    public static void setSizeImage(float width,float height){
        heightSize=height;
        widthSize=width;
    }
    
    /**
     * 
     * @return 
     */
    public static float getHeightImage(){
        return heightSize;
    }
    
    /**
     * 
     * @return 
     */
    public static float getWidthImage(){
        return widthSize;
    }
    
    
    public void setCurrentColor(int value){
        currentColor=value;        
    }
    
    public int getCurrentColor(){
        return currentColor;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
