/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ALM.graficos;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Antonio David López Machado
 */
public class Line2DFigure extends Line2D.Float{

    private Attribute attribute;
    
    public Line2DFigure(){
        super();
        attribute=new Attribute();
    }
    
     public void draw(Graphics2D g2d){
        attribute.apply(g2d);
        g2d.draw(this);
    }
         
    public boolean wasSelected(Point2D pos,Point2D offSet){
        boolean result =false;
        if (intersects(pos.getX(), pos.getY(), 6, 6)) {
            Point2D.Double point1 = new Point2D.Double(getX1(), getY1());
            offSet.setLocation(pos.getX() - point1.getX(), pos.getY() - point1.getY());
            result=true;
        }   
        return result;
    }
    
    public void setPosition(Point2D newPos,Point2D offSet){
        Point2D point1 = getP1();
        Point2D point2 = getP2();
        Point2D diff= new Point2D.Double((newPos.getX() - point1.getX()),(newPos.getY() - point1.getY()));
        point2.setLocation(point2.getX() + diff.getX(), point2.getY() + diff.getY());
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
        setLine(newPos, point2);
    }
}
