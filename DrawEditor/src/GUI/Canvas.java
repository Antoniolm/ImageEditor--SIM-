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

package GUI;

import static GUI.GeometryType.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

enum GeometryType{
    POINT,
    LINE,
    RECTANGLE,
    CIRCLE
}

public class Canvas extends JPanel{
    GeometryType type;
    Point initialPos,currentPos;
    boolean isClicked,filled;
    Color currentColor;
    
    
    public Canvas(){
        super();
        type=POINT;
        currentColor=Color.BLACK;
        isClicked=false;
        filled=false;
        initialPos=new Point();
        currentPos=new Point();
        setBackground(Color.white);
        
        addMouseListener(new MouseAdapter() {
                private Color background;

                @Override
                public void mousePressed(MouseEvent e){
                    initialPos=e.getPoint();
                    isClicked=true;
                    repaint();                    
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    isClicked=false;
                }
        });
        
        addMouseMotionListener(new MouseAdapter() {
                private Color background;

                @Override
                public void mouseDragged(MouseEvent e){
                    currentPos=e.getPoint();
                    repaint();                    
                }
        });
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.setColor(currentColor);
        //if(isClicked){
            switch(type){
                case POINT:
                    g.fillOval(initialPos.x, initialPos.y, 10, 10);
                break;
                case LINE:
                    g.drawLine(initialPos.x, initialPos.y, currentPos.x, currentPos.y);
                break;
                case RECTANGLE:
                    if(!filled)
                        g.drawRect(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                    else
                        g.fillRect(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                break;
                case CIRCLE:
                    if(!filled)
                        g.drawOval(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                    else
                        g.fillOval(Math.min(currentPos.x, initialPos.x), Math.min(currentPos.y, initialPos.y),
                                Math.abs(initialPos.x - currentPos.x), Math.abs(initialPos.y - currentPos.y));
                break;

            }
        //}
    }
    
    public void setType(GeometryType aType){
        type=aType;
    }
    
    public void setFilled(boolean value){
        filled=value;
    }
    
    public void setColor(Color value){
        currentColor=value;
    }
    
    
}
