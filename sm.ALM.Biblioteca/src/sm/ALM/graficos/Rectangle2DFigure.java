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
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class Rectangle2DFigure extends Figure{

    public Rectangle2DFigure(){
        super();
        currentShape=new Rectangle();
    }
    
    @Override
    public void draw(Graphics2D g2d,Attribute attribute) {
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
