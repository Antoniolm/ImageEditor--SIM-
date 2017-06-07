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
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * It will manage a ellipse figure in our system
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class Ellipse2DFigure extends Figure{

    /**
     * Constructor
     * @param anAtt the attribute of our shape 
     */
    public Ellipse2DFigure(Attribute anAtt){
        super();
        attribute=anAtt;
        currentShape=new Ellipse2D.Float();
        type=GeometryType.CIRCLE;
    }
    
    /**
     * It will draw our shape
     * @param g2d the graphic where the shape will be draw
     * @param drawImage true if is draw in an image
     */
    @Override
    public void draw(Graphics2D g2d,boolean drawImage) {
        if(editMode && !drawImage){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            g2d.draw(currentShape.getBounds2D());
            g2d.setStroke(new BasicStroke());
        }
        
        if(attribute.getFilled())
            attribute.generateFilled((int)((Ellipse2D.Float) currentShape).getX(),(int) ((Ellipse2D.Float) currentShape).getY(),
            (int)((Ellipse2D.Float) currentShape).getHeight(),(int)((Ellipse2D.Float) currentShape).getWidth());
        
        attribute.apply(g2d);
        
        if(!attribute.getFilled())
                g2d.draw(currentShape);
            else {
                g2d.fill(currentShape);
        }

    }

    /**
     * It will update the position of our second position of our shape
     * @param initPos
     * @param point 
     */
    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        ((Ellipse2D.Float) currentShape).setFrameFromDiagonal(initPos, point);
    }

    /**
     * It will set a new position for our shape
     * @param newPos 
     */
    @Override
    public void setPosition(Point2D newPos) {
        Point2D.Double point1 = new Point2D.Double(((Ellipse2D) currentShape).getX(), ((Ellipse2D) currentShape).getY());
        Point2D.Double point2 = new Point2D.Double(((Ellipse2D) currentShape).getCenterX(), ((Ellipse2D) currentShape).getCenterY());
        point2.setLocation(newPos.getX() + Math.abs((point1.getX() - point2.getX())), newPos.getY() + Math.abs((point1.getY() - point2.getY())));
        newPos.setLocation(Math.abs(newPos.getX()-offSet.getX()),Math.abs(newPos.getY()-offSet.getY()));
        point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
        //System.out.println("("+newPos.toString()+","+point2.toString()+")");
        ((Ellipse2D.Float) currentShape).setFrameFromCenter(newPos, point2);
    }

    /**
     * It will return if the shape was selected or not (in edit mode)
     * @param pos the position of our mouse
     * @return 
     */
    @Override
    public boolean wasSelected(Point2D pos) {
        boolean result=false;
        
        if (currentShape.contains(pos)) {
            Point2D.Double point1 = new Point2D.Double(((Ellipse2D.Float) currentShape).getCenterX(), ((Ellipse2D.Float) currentShape).getCenterY());
            offSet.setLocation(pos.getX() - point1.getX(), pos.getY() - point1.getY());
            result=true;
        }
        
        return result;
    }
    
}
