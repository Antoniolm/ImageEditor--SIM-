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
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;
import static sm.ALM.graficos.GeometryType.*;


public class Canvas2DPanel extends javax.swing.JPanel {
    static GeometryType geometry=POINT;
    public Point initPos;
    Shape currentShape;
    List<Shape> vShape;
    Shape clip;
    static boolean editMode=false;
    Attribute attribute;
    
    public Canvas2DPanel() {
        initComponents();
        initPos=new Point(0,0);
        vShape = new ArrayList();
        editMode=false;
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
                        setLocationShape(e.getPoint());
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
        g2d.clip(clip);
        
        if(!attribute.getFilled())
            for(Shape s:vShape)
                g2d.draw(s);
        else
            for(Shape s:vShape){
                if(s instanceof Line2D)
                    g2d.draw(s);
                else g2d.fill(s);
            }
        
        
    }
    
    public void setGeometry(GeometryType aType){
        geometry=aType;
    }
    
    public void setColor(Paint value){
        attribute.setFilled(value);
        repaint();
    }
        
    public void setFilled(boolean boolValue){
        attribute.filled=boolValue;
        repaint();
    }
    
    public boolean getFilled(){            
        return attribute.getFilled();
    }
    
    public void setThick(Stroke stroke,Integer value){
        attribute.setStroke(stroke,value);
        repaint();
    }
    
    public Integer getThick(){
        return attribute.getThickness();
    }
    public void setTransparency(Composite value){
        attribute.setComp(value);
        repaint();
    }
    
    public boolean getTransparency(){
        boolean result=false;
        
        if(attribute.getComp()!=null)
            result=true;
            
        return result;
    }
    
    public void setRender(RenderingHints value){
        attribute.setRender(value);
        repaint();
    }
    
    public boolean getRender(){
        boolean result=false;
        if(attribute.getRender()!=null)
            result=true;
        
        return result;
    }
    
    public void setEdit(boolean value){
        editMode=value;
    }
    
    public boolean getEdit(){
        return editMode;
    }
    
    public void setClip(Shape newClip){
        clip=newClip;
    }
    public Shape getClip(){
        return clip;
    }
    
    /**
     * It will create a new shape 
     * @return 
     */
    private Shape createShape(){
        Shape result=null;
        switch(geometry){
            case POINT: //Case point geoometry
                result=new Line2D.Float(initPos,initPos);
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
     * It will update the currentShape
     * @param point 
     */
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
     * It will return the shape that contain the point p
     * if point p is not in any shape then return null
     * @param p
     * @return shape 
     */
    private Shape getSelectedShape(Point2D p){
        for(Shape s:vShape){
            if(s instanceof RectangularShape) //If is a rectangularShape
                if(s.contains(p)) return s;
            if(s instanceof Line2D) //If is a line
                if(s.intersects(p.getX(), p.getY(), 6, 6)) return s;  
        }
        return null;
    }
    
    /**
     * it will change the location of our current shape
     * @param pos 
     */
    private void setLocationShape(Point2D pos){
        if(currentShape!=null){
            if(currentShape instanceof Rectangle) //If is a rectangle or a point
                ((Rectangle)currentShape).setLocation((Point)pos);
            if (currentShape instanceof Ellipse2D) { //If is a circle
                Point2D.Double point1 = new Point2D.Double(((Ellipse2D) currentShape).getX(), ((Ellipse2D) currentShape).getY());
                Point2D.Double point2 = new Point2D.Double(((Ellipse2D) currentShape).getCenterX(), ((Ellipse2D) currentShape).getCenterY());
                point2.setLocation(pos.getX() + Math.abs((point1.getX() - point2.getX())), pos.getY() + Math.abs((point1.getY() - point2.getY())));
                ((Ellipse2D.Float) currentShape).setFrameFromCenter(pos, point2);
            }
            if (currentShape instanceof Line2D.Float) { //If is a line
                Point2D point1 = ((Line2D) currentShape).getP1();
                Point2D point2 = ((Line2D) currentShape).getP2();
                Point2D diff= new Point2D.Double((pos.getX() - point1.getX()),(pos.getY() - point1.getY()));
                point2.setLocation(point2.getX() + diff.getX(), point2.getY() + diff.getY());
                ((Line2D) currentShape).setLine(pos, point2);
            }
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
