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
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.List;

public class FigureManager {
    private Figure currentShape;
    private boolean isSelected;
    
    public FigureManager(){
        currentShape=null;
        isSelected=false;
    }
    
    /**
     *  It will draw our vector of shapes
     * @param g2d 
     */
    public void draw(Graphics2D g2d,boolean drawImage){
        if(currentShape!=null){
            currentShape.draw(g2d,drawImage);
        }
    }
    /**
     * It will create a new shape 
     * @return 
     */
    public void createShape(GeometryType geometry,Attribute attribute,Point2D initPos){
        switch(geometry){
            case POINT: //Case point geoometry
                currentShape=new Line2DFigure(initPos,new Attribute(attribute));
                break;
            case LINE: //Case line geoometry
                currentShape=new Line2DFigure(new Attribute(attribute));
                break;
            case RECTANGLE: //Case rectangle geoometry
                currentShape=new Rectangle2DFigure(new Attribute(attribute));
                break;
            case RRECTANGLE: //Case rectangle geoometry
                //currentShape=new RoundRectangle2D.Float();
                break;
            case CURVE: 
                currentShape=new Curve2DFigure(new Attribute(attribute));
                break;
            case TEXT:
                currentShape=new Text2DFigure(initPos,new Attribute(attribute));
            break;    
            case CIRCLE: //Case circle geoometry
                currentShape=new Ellipse2DFigure(new Attribute(attribute));
                break;

        }
    }
    
    /**
     * It will update the currentShape
     * @param point 
     */
    public void updateShape(Point2D point,Point2D initPos,GeometryType geometry){
        if(geometry!=GeometryType.POINT && geometry!=GeometryType.TEXT){
            currentShape.updatePosition(initPos, point);
        }
    }
    
    /**
     * It will return the shape that contain the point p
     * if point p is not in any shape then return null
     * @param p
     * @return shape 
     */
    public void getSelectedShape(Point2D p){
        isSelected=false;
        if(currentShape.wasSelected(p))
            isSelected=true;   
    }
    
    /**
     * it will change the location of our current shape
     * @param pos 
     */
    public void setLocationShape(Point2D pos){
        if(currentShape!=null && isSelected){
            currentShape.setPosition(pos);
        }
    }
        
    /**
     * 
     * @return 
     */
    public Figure getFigure(){
        return currentShape;
    }
        
    public void shapeInImage(){
        currentShape=null;
    }
    
    public void isEdited(boolean value){
        if(currentShape!=null){
            currentShape.setEdited(value);
       }             
    }
}
