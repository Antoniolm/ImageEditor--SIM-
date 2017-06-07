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

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * It will manage a line figure in our system
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class Line2DFigure extends Figure{
        
    /**
     * Constructor
     * @param anAtt the attribute of our shape 
     */
    public Line2DFigure(Attribute anAtt){
        super();
        attribute=anAtt;
        currentShape=new Line2D.Float();
    }
    
    /**
     * Constructor
     * @param initPos the initial position of our line
     * @param anAtt the attribute of our shape 
     */
    public Line2DFigure(Point2D initPos,Attribute anAtt){
        super();
        attribute=anAtt;
        currentShape=new Line2D.Float(initPos,initPos);
    }
    
    /**
     * It will draw our shape
     * @param g2d the graphic where the shape will be draw
     * @param drawImage true if is draw in an image
     */
    @Override
    public void draw(Graphics2D g2d,boolean drawImage){
        if(editMode && ! drawImage){
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
        g2d.draw(currentShape);
    }
         
    /**
     * It will update the position of our second position of our shape
     * @param initPos
     * @param point 
     */
    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        ((Line2D.Float)currentShape).setLine(initPos, point);
    }
    
    /**
     * It will return if the shape was selected or not (in edit mode)
     * @param pos the position of our mouse
     * @return 
     */
    @Override
    public boolean wasSelected(Point2D pos){
        boolean result =false;
        if (((Line2D.Float)currentShape).intersects(pos.getX(), pos.getY(), 6, 6)) {
            Point2D.Double point1 = new Point2D.Double(((Line2D.Float)currentShape).getX1(), ((Line2D.Float)currentShape).getY1());
            offSet.setLocation(pos.getX() - point1.getX(), pos.getY() - point1.getY());
            result=true;
        }   
        return result;
    }
    
    /**
     * It will set a new position for our shape
     * @param newPos 
     */
    @Override
    public void setPosition(Point2D newPos){
        Point2D point1 = ((Line2D.Float)currentShape).getP1();
        Point2D point2 = ((Line2D.Float)currentShape).getP2();
        Point2D diff= new Point2D.Double((newPos.getX() - point1.getX()),(newPos.getY() - point1.getY()));
        point2.setLocation(point2.getX() + diff.getX(), point2.getY() + diff.getY());
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
        ((Line2D.Float)currentShape).setLine(newPos, point2);
    }

}
