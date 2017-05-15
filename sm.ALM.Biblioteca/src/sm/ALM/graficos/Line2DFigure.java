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
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 *
 * @author Antonio David López Machado
 */
public class Line2DFigure extends Figure{
        
    public Line2DFigure(){
        super();
        currentShape=new Line2D.Float();
    }
    
    public Line2DFigure(Point2D initPos){
        super();
        currentShape=new Line2D.Float(initPos,initPos);
    }
    
    @Override
    public void draw(Graphics2D g2d,Attribute attribute){
        attribute.apply(g2d);
        g2d.draw(currentShape);
        
        if(editMode){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            g2d.draw(currentShape.getBounds2D());
        }
    }
         
    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        ((Line2D.Float)currentShape).setLine(initPos, point);
    }
    
    @Override
    public boolean wasSelected(Point2D pos,Point2D offSet){
        boolean result =false;
        if (((Line2D.Float)currentShape).intersects(pos.getX(), pos.getY(), 6, 6)) {
            Point2D.Double point1 = new Point2D.Double(((Line2D.Float)currentShape).getX1(), ((Line2D.Float)currentShape).getY1());
            offSet.setLocation(pos.getX() - point1.getX(), pos.getY() - point1.getY());
            result=true;
        }   
        return result;
    }
    
    @Override
    public void setPosition(Point2D newPos,Point2D offSet){
        Point2D point1 = ((Line2D.Float)currentShape).getP1();
        Point2D point2 = ((Line2D.Float)currentShape).getP2();
        Point2D diff= new Point2D.Double((newPos.getX() - point1.getX()),(newPos.getY() - point1.getY()));
        point2.setLocation(point2.getX() + diff.getX(), point2.getY() + diff.getY());
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        point2.setLocation(point2.getX()-offSet.getX(),point2.getY()-offSet.getY());
        ((Line2D.Float)currentShape).setLine(newPos, point2);
    }

}
