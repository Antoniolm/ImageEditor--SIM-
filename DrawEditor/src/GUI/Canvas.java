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
    int initialX,initialY;
    int currentX,currentY;
    Boolean isClicked;
    
    public Canvas(){
        super();
        type=POINT;
        isClicked=false;
        addMouseListener(new MouseAdapter() {
                private Color background;

                @Override
                public void mousePressed(MouseEvent e) {
                    isClicked=true;
                    initialX=e.getX();
                    initialY=e.getY();
                    repaint();
                }
                
                @Override
                public void mouseReleased(MouseEvent e) {
                    System.out.println("noClicked");
                    isClicked=false;
                }
        });
    }
    
    public void paint(Graphics g){
        super.paint(g);
        //if(isClicked){
            System.out.println("isClicked");
            switch(type){
                case POINT:
                    g.drawOval(initialX, initialY, 50, 100);
                break;
                case LINE:
                    g.drawOval(5, 5, 20, 20);
                break;
                case RECTANGLE:
                    g.drawOval(5, 5, 20, 20);
                break;
                case CIRCLE:
                    g.drawOval(5, 5, 20, 20);
                break;

            }
        //}
    }
    
    public void setType(GeometryType aType){
        type=aType;
    }
    
    
    
}
