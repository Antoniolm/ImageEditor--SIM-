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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Rectangle2DFigure extends Figure{

    public Rectangle2DFigure(Attribute anAtt){
        super();
        attribute=anAtt;
        currentShape=new Rectangle();
    }
    
    @Override
    public void draw(Graphics2D g2d,boolean drawImage) {
        if(editMode && !drawImage){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            Rectangle2D bound=currentShape.getBounds2D();
            bound.setFrameFromDiagonal(((Rectangle)currentShape).getX()-1,((Rectangle)currentShape).getY()-1,
                                       ((Rectangle)currentShape).getX() +((Rectangle)currentShape).getWidth()+2,
                                       ((Rectangle)currentShape).getY() +((Rectangle)currentShape).getHeight()+2);
            g2d.draw(bound);
            g2d.setStroke(new BasicStroke());
        }
        
        if(attribute.getFilled())
            attribute.generateFilled((int)((Rectangle) currentShape).getX(),(int) ((Rectangle) currentShape).getY(),
            (int)((Rectangle) currentShape).getHeight(),(int)((Rectangle) currentShape).getWidth());
        
        attribute.apply(g2d);
        
        if(!attribute.getFilled())
                g2d.draw(currentShape);
            else {
                g2d.fill(currentShape);
        }        

    }

    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        ((Rectangle)currentShape).setFrameFromDiagonal(initPos, point);
    }
    
    @Override
    public void setPosition(Point2D newPos) {
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        ((Rectangle)currentShape).setLocation((Point)newPos);
    }

    @Override
    public boolean wasSelected(Point2D pos) {
        boolean result=false;
        
        if (currentShape.contains(pos)) {
            Point2D.Double point1 = new Point2D.Double(((Rectangle) currentShape).getX(), ((Rectangle) currentShape).getY());
            offSet.setLocation(Math.abs(pos.getX() - point1.getX()), Math.abs(pos.getY() - point1.getY()));
            result=true;
        }
        
        return result;    
    }
}
