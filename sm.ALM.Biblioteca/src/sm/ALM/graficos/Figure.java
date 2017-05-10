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

public class Figure {
    private Shape currentShape;
    private Attribute attribute;
    private boolean isSelected;
    
    public Figure(){
        currentShape=null;       
        isSelected=false;
        attribute =new Attribute();
    }
    
    /**
     *  It will draw our vector of shapes
     * @param g2d 
     */
    public void draw(Graphics2D g2d){
        attribute.apply(g2d);
        
        if(currentShape!=null){
            if(!attribute.getFilled())
                g2d.draw(currentShape);
            else {
                if(currentShape instanceof Line2D) 
                    g2d.draw(currentShape);
                else 
                    g2d.fill(currentShape);
            }
        }
    }
    /**
     * It will create a new shape 
     * @return 
     */
    public void createShape(GeometryType geometry,Point2D initPos){
        switch(geometry){
            case POINT: //Case point geoometry
                currentShape=new Line2D.Float(initPos,initPos);
                break;
            case LINE: //Case line geoometry
                currentShape=new Line2D.Float();
                break;
            case RECTANGLE: //Case rectangle geoometry
                currentShape=new Rectangle();
                break;
            case CIRCLE: //Case circle geoometry
                currentShape=new Ellipse2D.Float();
                break;

        }
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
        isSelected=false;
        if (currentShape!= null && currentShape instanceof RectangularShape) { //If is a rectangularShape
            if (currentShape.contains(p)) {
                if (currentShape instanceof Rectangle) {
                    Point2D.Double point1 = new Point2D.Double(((RectangularShape) currentShape).getX(), ((RectangularShape) currentShape).getY());
                    offSet.setLocation(Math.abs(p.getX() - point1.getX()), Math.abs(p.getY() - point1.getY()));
                } else {
                    Point2D.Double point1 = new Point2D.Double(((RectangularShape) currentShape).getCenterX(), ((RectangularShape) currentShape).getCenterY());
                    offSet.setLocation(p.getX() - point1.getX(), p.getY() - point1.getY());
                }
                isSelected=true;
            }
        }
        if (currentShape instanceof Line2D) { //If is a line
            if (currentShape.intersects(p.getX(), p.getY(), 6, 6)) {
                Point2D.Double point1 = new Point2D.Double(((Line2D) currentShape).getX1(), ((Line2D) currentShape).getY1());
                offSet.setLocation(p.getX() - point1.getX(), p.getY() - point1.getY());
                isSelected=true;
            }
        }
        
    }
    
    /**
     * it will change the location of our current shape
     * @param pos 
     */
    public void setLocationShape(Point2D pos,Point2D offSet){
        if(currentShape!=null && isSelected){
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
    
    /**
     * It will return the attribute of our vector
     * @return 
     */
    public Attribute getAttribute(){
        return attribute;
    }
}
