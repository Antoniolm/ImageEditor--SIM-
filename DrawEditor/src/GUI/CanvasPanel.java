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

import static GUI.GeometryType.*;
import draweditor.Attribute;
import java.awt.Color;
import java.awt.Composite;
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
import java.util.ArrayList;
import java.util.List;

enum GeometryType{
    POINT,
    LINE,
    RECTANGLE,
    CIRCLE
}


public class CanvasPanel extends javax.swing.JPanel {
    static GeometryType geometry=POINT;
    public Point initPos;
    Shape currentShape;
    List<Shape> vShape;
    boolean editMode,isFilled;
    Attribute attribute;
    
    public CanvasPanel() {
        initComponents();
        initPos=new Point(0,0);
        vShape = new ArrayList();
        editMode=false;
        isFilled=false;
        attribute=new Attribute();
        setBackground(Color.white);
        
        //Added our mouseListener 
        addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if(editMode) currentShape=getSelectedShape(e.getPoint());
                    else{
                        initPos=e.getPoint();       
                        currentShape=createShape();
                        vShape.add(currentShape);
                    }
                repaint();                  
                }
        });
        
        //Added our mouseMotionListener 
        addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e){
                if(editMode){
                    if(currentShape!=null)
                        ((Rectangle)currentShape).setLocation(e.getPoint());
                    }
                    else{
                        updateShape(e.getPoint());
                    }
                repaint();                  
                } 
        }); 
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        attribute.apply(g2d);
        
        if(!isFilled)
            for(Shape s:vShape) g2d.draw(s);
        else
            for(Shape s:vShape) g2d.fill(s);
    }
    
     private Shape getSelectedShape(Point2D p){
        for(Shape s:vShape)
            if(s.contains(p)) return s;
        return null;
    }
     
    public void setGeometry(GeometryType aType){
        geometry=aType;
    }
    
    public void setColor(Paint value){
        attribute.setFilled(value);
    }
    
    public void setFilled(boolean boolValue){
        isFilled=boolValue;
        repaint();
    }
    
    public void setThick(Stroke value){
        attribute.setStroke(value);
        repaint();
    }
    
    public void setTransparency(Composite value){
        attribute.setComp(value);
        repaint();
    }
    
    public void setRender(RenderingHints value){
        attribute.setRender(value);
        repaint();
    }
    
    public void setColor(Color value){
    }
    
    public void resetCanvas(){
        repaint();
    }
    
    private Shape createShape(){
        Shape result=null;
        switch(geometry){
            case POINT: //Case point geoometry
                result=new Rectangle(initPos);
                break;
            case LINE: //Case line geoometry
                result=new Line2D.Float();
                break;
            case RECTANGLE: //Case rectangle geoometry
                result=new Rectangle();
                break;
            case CIRCLE: //Case circle geoometry
                result=new Ellipse2D.Float();
                break;

        }
        return result;
    }
    
    private void updateShape(Point2D point){
        switch (geometry) {
            case POINT: //Case point geoometry
                break;
            case LINE: //Case line geoometry
                ((Line2D.Float) currentShape).setLine(initPos, point);
                break;
            case RECTANGLE: //Case rectangle geoometry
                ((Rectangle) currentShape).setFrameFromDiagonal(initPos, point);
                break;
            case CIRCLE: //Case circle geoometry
                ((Ellipse2D.Float) currentShape).setFrameFromDiagonal(initPos, point);
                break;
        }
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
