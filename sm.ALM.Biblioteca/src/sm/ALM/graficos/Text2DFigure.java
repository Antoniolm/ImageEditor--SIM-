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
import java.awt.geom.Point2D;

public class Text2DFigure extends Figure{
    private String text;
    
    public Text2DFigure(Point2D initPos){
        super();
        //new dialog
    }
    
    @Override
    public void draw(Graphics2D g2d, Attribute attribute) {
        
    }

    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        
    }

    @Override
    public void setPosition(Point2D newPos, Point2D offSet) {
        
    }

    @Override
    public boolean wasSelected(Point2D pos, Point2D offSet) {
        return false;
    }
    
}
