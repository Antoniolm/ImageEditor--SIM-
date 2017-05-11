/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ALM.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author LENOVO
 */
public class Rectangle2DFigure extends Figure{

    public Rectangle2DFigure(){
        currentShape=new Rectangle();
        attribute=new Attribute();
    }
    
    @Override
    public void draw(Graphics2D g2d) {
        attribute.apply(g2d);
        
        if(!attribute.getFilled())
                g2d.draw(currentShape);
            else {
                g2d.fill(currentShape);
        }
    }

    @Override
    public void setPosition(Point2D newPos, Point2D offSet) {
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        ((Rectangle)currentShape).setLocation((Point)newPos);
    }

    @Override
    public boolean wasSelected(Point2D pos, Point2D offSet) {
        boolean result=false;
        
        if (currentShape.contains(pos)) {
            Point2D.Double point1 = new Point2D.Double(((Rectangle) currentShape).getX(), ((Rectangle) currentShape).getY());
            offSet.setLocation(Math.abs(pos.getX() - point1.getX()), Math.abs(pos.getY() - point1.getY()));
            result=true;
        }
        
        return result;    
    }
}
