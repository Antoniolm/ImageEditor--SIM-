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

public abstract  class Figure {
    Attribute attribute;
    Shape currentShape;
    
    public abstract void draw(Graphics2D g2d);
    public abstract void setPosition(Point2D newPos,Point2D offSet);
    public abstract boolean wasSelected(Point2D pos,Point2D offSet);
    public Attribute getAttribute(){
        return attribute;
    }
    public Shape getShape(){
        return currentShape;
    }
}
