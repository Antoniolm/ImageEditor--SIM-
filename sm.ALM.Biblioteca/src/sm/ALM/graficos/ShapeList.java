/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.ALM.graficos;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ShapeList {
    Shape currentShape;
    private List<Shape> vShape;
    private Attribute attribute;
    
    public ShapeList(){
        vShape = new ArrayList();        
        attribute =new Attribute();
    }
    
    
    public void draw(Graphics2D g2d){
        attribute.apply(g2d);
        
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
    /**
     * It will create a new shape 
     * @return 
     */
    public void createShape(GeometryType geometry,Point2D initPos){
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
        currentShape=result;
        vShape.add(currentShape);
    }
    
    /**
     * It will update the currentShape
     * @param point 
     */
    public void updateShape(Point2D point,Point2D initPos,GeometryType geometry){
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
    public void getSelectedShape(Point2D p,Point2D offSet){
        currentShape=null;
        for(Shape s:vShape){
            if(s instanceof RectangularShape){ //If is a rectangularShape
                if(s.contains(p)){ 
                    if( s instanceof Rectangle){
                    Point2D.Double point1=new Point2D.Double(((RectangularShape)s).getX(),((RectangularShape)s).getY());
                    offSet.setLocation(Math.abs(p.getX()-point1.getX()),Math.abs(p.getY()-point1.getY()));
                    }
                    else{
                        Point2D.Double point1=new Point2D.Double(((RectangularShape)s).getCenterX(),((RectangularShape)s).getCenterY());
                        offSet.setLocation(p.getX()-point1.getX(),p.getY()-point1.getY());
                    }
                    currentShape=s;
                }
            }
            if(s instanceof Line2D){ //If is a line
                if(s.intersects(p.getX(), p.getY(), 6, 6)){
                    Point2D.Double point1=new Point2D.Double(((Line2D)s).getX1(),((Line2D)s).getY1());
                    offSet.setLocation(p.getX()-point1.getX(),p.getY()-point1.getY());
                    currentShape=s;
                }  
            }
        }
        
    }
    
    /**
     * it will change the location of our current shape
     * @param pos 
     */
    public void setLocationShape(Point2D pos,Point2D offSet){
        if(currentShape!=null){
            if(currentShape instanceof Rectangle){ //If is a rectangle or a point
                pos.setLocation(pos.getX()-offSet.getX(),pos.getY()-offSet.getY());
                ((Rectangle)currentShape).setLocation((Point)pos);
            }
            if (currentShape instanceof Ellipse2D) { //If is a circle
                Point2D.Double point1 = new Point2D.Double(((Ellipse2D) currentShape).getX(), ((Ellipse2D) currentShape).getY());
                Point2D.Double point2 = new Point2D.Double(((Ellipse2D) currentShape).getCenterX(), ((Ellipse2D) currentShape).getCenterY());
                point2.setLocation(pos.getX() + Math.abs((point1.getX() - point2.getX())), pos.getY() + Math.abs((point1.getY() - point2.getY())));
                pos.setLocation(pos.getX()-offSet.getX(),pos.getY()-offSet.getY());
                point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
                ((Ellipse2D.Float) currentShape).setFrameFromCenter(pos, point2);
            }
            if (currentShape instanceof Line2D.Float) { //If is a line
                Point2D point1 = ((Line2D) currentShape).getP1();
                Point2D point2 = ((Line2D) currentShape).getP2();
                Point2D diff= new Point2D.Double((pos.getX() - point1.getX()),(pos.getY() - point1.getY()));
                point2.setLocation(point2.getX() + diff.getX(), point2.getY() + diff.getY());
                pos.setLocation(pos.getX()-offSet.getX(),pos.getY()-offSet.getY());
                point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
                ((Line2D) currentShape).setLine(pos, point2);
            }
        }
    }
}
