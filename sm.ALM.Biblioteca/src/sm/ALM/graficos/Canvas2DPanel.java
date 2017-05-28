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
    private int currentColor;
    static float widthSize=300;
    static float heightSize=300;
    FigureManager vShape;
    Attribute attribute;
    Shape clipShape;
    boolean editMode;
    
    public Canvas2DPanel() {
        initComponents();
        initPos=new Point(0,0);
        vShape = new FigureManager();
        attribute=new Attribute();
        editMode=false;
        geometry=POINT;
        setBackground(Color.white);
        currentColor=0;
        
        //Added our mouseListener 
        addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if(editMode) vShape.getSelectedShape(e.getPoint());
                    else{
                        initPos=e.getPoint();   
                        
                        if(vShape.getFigure()!=null && vShape.getFigure().getType()==GeometryType.CURVE && !((Curve2DFigure)vShape.getFigure()).isCreated()){
                             ((Curve2DFigure)vShape.getFigure()).setCreated(true);
                        }
                        
                        else {
                            drawInImage();
                            vShape.createShape(geometry,attribute,initPos);
                        }
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
                        vShape.setLocationShape(e.getPoint());
                        setCursor(new Cursor(Cursor.MOVE_CURSOR));
                    }
                    else{
                        vShape.updateShape(e.getPoint(),initPos,geometry);
                    }
                repaint();                  
                }
                
                @Override
                public void mouseMoved(MouseEvent e) {
                    if(vShape.getFigure()!=null && vShape.getFigure().getType()==GeometryType.CURVE && !((Curve2DFigure)vShape.getFigure()).isCreated() ){
                        ((Curve2DFigure)vShape.getFigure()).updateCurve(e.getPoint());
                        repaint();
                    }
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
        
        vShape.draw(g2d,false);
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
    
    
    public FigureManager getFigure(){
        return vShape;
    }
    
    public Attribute getAttribute(){
        return attribute;
    }
    
    /**
     * 
     * @param value 
     */
    public void setColor(Color value){
        attribute.setColorT(value);
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().setColorT(value);
        }
        repaint();
              
    }
        
    /**
     * 
     * @param boolValue 
     */
    public void setFilled(boolean boolValue){
        attribute.filled=boolValue;
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().filled=boolValue;
        }
        
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getFilled(){            
        return attribute.getFilled();
    }
    
    /**
     * 
     * @param stroke
     * @param value 
     */
    public void setThick(Stroke stroke,Integer value){
        attribute.setStroke(stroke,value);
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().setStroke(stroke,value);
        }
        
        repaint();
    }
    
    /**
     * 
     * @param style 
     */
    public void setStrokeStyle(String style){
        attribute.setStrokeStyle(style);
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().setStrokeStyle(style);
        }
        
        repaint();
    }
        
    /**
     * 
     * @param value 
     */
    public void setTransparency(Composite value,int transValue){
        attribute.setComp(value,transValue);
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().setComp(value,transValue);
        }
        
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getTransparency(){
        boolean result=false;
        
        if(attribute.getComp()!=null)
            result=true;
            
        return result;
    }
    
    /**
     * 
     * @param value 
     */
    public void setRender(RenderingHints value){
        attribute.setRender(value);
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().setRender(value);
        }
        
        repaint();
    }
    
    /**
     * 
     * @return 
     */
    public boolean getRender(){
        boolean result=false;
        if(attribute.getRender()!=null)
            result=true;
        
        return result;
    }
    
    /**
     * 
     * @param value 
     */
    public void setEdit(boolean value){
        editMode=value;
        vShape.isEdited(value);
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null){
                attribute=new Attribute(fig.getAttribute());
            }
        }
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
    
    public void drawInImage(){
        Graphics2D g2d=(Graphics2D) image.getGraphics();
        vShape.draw(g2d,true);
        vShape.shapeInImage();
    }
        
    public Color getCurrentColor(){
        return (Color)attribute.getColorT();
    }
    
    public void setFont(String font,int size,int style){
        attribute.setFont(new FontClass(font,size,style));
        if(editMode){
            Figure fig=vShape.getFigure();
            if(fig!=null)
                fig.getAttribute().setFont(new FontClass(font,size,style));
        }
        
        repaint();
    }
    
    public FontClass getFontClass(){
        return attribute.getFont();
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
