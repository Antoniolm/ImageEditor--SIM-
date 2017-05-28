/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ALM.graficos;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author LENOVO
 */
public class Curve2DFigure extends Figure{    
    boolean created=false; 
    
    public Curve2DFigure(Attribute anAtt){
        super();
        attribute=anAtt;
        currentShape= new QuadCurve2D.Float();
        type=GeometryType.CURVE;
    }
    
    @Override
    public void draw(Graphics2D g2d,boolean drawImage) {
        
        if(editMode && !drawImage){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            g2d.draw(currentShape.getBounds2D());
            g2d.setStroke(new BasicStroke());
        }
        
        Rectangle bound=currentShape.getBounds();
        if(attribute.getFilled())
            attribute.generateFilled((int)(( Rectangle) bound).getX(),(int) (( Rectangle) bound).getY(),
            (int)(( Rectangle) bound).getHeight(),(int)(( Rectangle) bound).getWidth());
        
        attribute.apply(g2d);
        
        if(!attribute.getFilled())
                g2d.draw(currentShape);
        else {
                g2d.fill(currentShape);
        }
    }
    

    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        ((QuadCurve2D.Float) currentShape).setCurve(initPos, new Point2D.Float(0,0), point);
    }
    
    public void updateCurve(Point2D point){
        ((QuadCurve2D.Float) currentShape).setCurve(((QuadCurve2D.Float) currentShape).getP1() , point, ((QuadCurve2D.Float) currentShape).getP2());
    }
    
    public boolean isCreated(){
        return created;
    }
    
    public void setCreated(boolean value){
        created=value;
    }

    @Override
    public void setPosition(Point2D newPos) {
        
    }

    @Override
    public boolean wasSelected(Point2D pos) {
        boolean result =false;
        /*if (currentShape.getBounds2D().contains(pos)) {            
            offSet.setLocation(Math.abs(pos.getX() - .getX()), Math.abs(pos.getY() - position.getY()));
            result=true;
        } */  
        return result;
    }
    
}
