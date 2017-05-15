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

public class Ellipse2DFigure extends Figure{

    
    public Ellipse2DFigure(){
        super();
        currentShape=new Ellipse2D.Float();
    }
    
    @Override
    public void draw(Graphics2D g2d, Attribute attribute) {
        attribute.apply(g2d);
        
        if(!attribute.getFilled())
                g2d.draw(currentShape);
            else {
                g2d.fill(currentShape);
        }
        if(editMode){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            g2d.draw(currentShape.getBounds2D());
        }
        
    }

    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        ((Ellipse2D.Float) currentShape).setFrameFromDiagonal(initPos, point);
    }

    @Override
    public void setPosition(Point2D newPos, Point2D offSet) {
        Point2D.Double point1 = new Point2D.Double(((Ellipse2D) currentShape).getX(), ((Ellipse2D) currentShape).getY());
        Point2D.Double point2 = new Point2D.Double(((Ellipse2D) currentShape).getCenterX(), ((Ellipse2D) currentShape).getCenterY());
        point2.setLocation(newPos.getX() + Math.abs((point1.getX() - point2.getX())), newPos.getY() + Math.abs((point1.getY() - point2.getY())));
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
        ((Ellipse2D.Float) currentShape).setFrameFromCenter(newPos, point2);
    }

    @Override
    public boolean wasSelected(Point2D pos, Point2D offSet) {
        boolean result=false;
        
        if (currentShape.contains(pos)) {
            Point2D.Double point1 = new Point2D.Double(((Ellipse2D.Float) currentShape).getCenterX(), ((Ellipse2D.Float) currentShape).getCenterY());
            offSet.setLocation(pos.getX() - point1.getX(), pos.getY() - point1.getY());
            result=true;
        }
        
        return result;
    }
    
}
