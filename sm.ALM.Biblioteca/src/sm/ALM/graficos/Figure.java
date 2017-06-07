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
import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 * It is the abstract class of our figure
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public abstract class Figure {
    Shape currentShape;  //the current shape of our figure
    Attribute attribute; //the attribute of its shape
    GeometryType type;   //its geometry type
    
    Point2D offSet;     //Its offset position
    boolean editMode;   //true if the application is in edit mode
    
    /**
     * Constructor
     */
    protected Figure(){
        currentShape=null;
        editMode=false;
        offSet=new Point2D.Float(0,0);
    }
    
    /**
     * It will draw our shape
     * @param g2d the graphic where the shape will be draw
     * @param drawImage true if is draw in an image
     */
    public abstract void draw(Graphics2D g2d,boolean drawImage);
    
    /**
     * It will update the position of our second position of our shape
     * @param initPos
     * @param point 
     */
    public abstract void updatePosition(Point2D initPos, Point2D point);
    
    /**
     * It will set a new position for our shape
     * @param newPos 
     */
    public abstract void setPosition(Point2D newPos);
    
    /**
     * It will return if the shape was selected or not (in edit mode)
     * @param pos the position of our mouse
     * @return 
     */
    public abstract boolean wasSelected(Point2D pos);
    
    /**
     * It will return the shape
     * @return 
     */
    public Shape getShape(){
        return currentShape;
    }
    
    /**
     * It will return the attribute of our shape
     * @return 
     */
    public Attribute getAttribute(){
        return attribute;
    }
    
    /**
     * It will return the geoemtry type of our shape
     * @return 
     */
    public GeometryType getType(){
        return type;
    }
    
    /**
     * It will set a new value for our shape
     * @param s 
     */
    public void setShape(Shape s){
        currentShape=s;
    }
    
    /**
     * It will set a new value for our attribute of our shape
     * @param att 
     */
    public void setAttribute(Attribute att){
        attribute=att;
    }
    
    /**
     * it will indicate is the shape is in edit mode or not
     * @return 
     */
    public boolean isEdited(){
        return editMode;
    }
    
    /**
     * It will set the value of our edit mode (true or false)
     * @param value 
     */
    public void setEdited(boolean value){
        editMode=value;
    }
}
