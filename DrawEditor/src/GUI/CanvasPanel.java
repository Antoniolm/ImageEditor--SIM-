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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
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
    GeometryType type,selectedGeometry;
    public Point initPos;
    Shape currentShape;
    List<Shape> vShape;
    boolean editMode;
    
    public CanvasPanel() {
        initComponents();
        initPos=new Point(0,0);
        vShape = new ArrayList();
        editMode=false;
        
        type=POINT;
        initPos=new Point(0,0);
        setBackground(Color.white);
        
        //Added our mouseListener 
        addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e){
                    if(editMode) currentShape=getSelectedShape(e.getPoint());
                    else{
                        currentShape=createShape();
                        vShape.add(currentShape);
                        initPos=e.getPoint();       
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
                        switch (type) {
                        case POINT: //Case point geoometry
                           
                            break;
                        case LINE: //Case line geoometry
                            ((Line2D.Float)currentShape).setLine(initPos,e.getPoint());
                            break;
                        case RECTANGLE: //Case rectangle geoometry
                            ((Rectangle)currentShape).setFrameFromDiagonal(initPos,e.getPoint());
                            break;
                        case CIRCLE: //Case circle geoometry
                            ((Ellipse2D.Float)currentShape).setFrameFromDiagonal(initPos,e.getPoint());
                            break;
                        }
                    }
                repaint();                  
                } 
        }); 
    }
    
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        for(Shape s:vShape) g2d.draw(s);
        
            /*switch(type){
                case POINT: //Case point geoometry
                    g.fillOval(initialPos.x, initialPos.y, 10, 10);
                break;
                case LINE: //Case line geoometry
                    g.drawLine(initialPos.x, initialPos.y, currentPos.x, currentPos.y);
                break;
                case RECTANGLE: //Case rectangle geoometry
                    if(!filled) //Not filled
                        g.drawRect(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                    else //Filled
                        g.fillRect(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                break;
                case CIRCLE: //Case circle geoometry
                    if(!filled) //Not filled
                        g.drawOval(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                    else //Filled
                        g.fillOval(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                break;

            }*/
    }
    
     private Shape getSelectedShape(Point2D p){
        for(Shape s:vShape)
            if(s.contains(p)) return s;
        return null;
    }
     
    public void setType(GeometryType aType){
        type=aType;
    }
    
    public void setFilled(boolean value){
    }
    
    public void setColor(Color value){
    }
    
    public void resetCanvas(){
        repaint();
    }
    
    private Shape createShape(){
        Shape result=null;
        switch(type){
            case POINT: //Case point geoometry
                result=new Ellipse2D.Float(initPos.x,initPos.y,5,5);
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
