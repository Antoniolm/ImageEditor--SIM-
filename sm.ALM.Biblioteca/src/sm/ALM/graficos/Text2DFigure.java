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
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.geom.Point2D;

public class Text2DFigure extends Figure{
    private String text;
    private String font;
    private Point2D position;
    
    
    public Text2DFigure(Point2D initPos,Attribute anAtt){
        super();
        text="";
        position=initPos;
        TextDialog dialog = new TextDialog(new javax.swing.JFrame(), true, this);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dialog.dispose();
            }
        });
        dialog.setVisible(true);        
    }
    
    public void setString(String aText){
        text=aText;
    }
    
    @Override
    public void draw(Graphics2D g2d, Attribute attribute) {
        attribute.apply(g2d);
        g2d.translate(position.getX(), position.getY());
        getTextShape(g2d, new Font("Arial", Font.BOLD, 30));
        g2d.draw(currentShape);
        
        if(editMode){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            g2d.draw(currentShape.getBounds2D());
        }
    }
    
    public void getTextShape(Graphics2D g2d, Font font) {
        if(text!=""){
            FontRenderContext frc = g2d.getFontRenderContext();
            TextLayout tl = new TextLayout(text, font, frc);
            currentShape=tl.getOutline(null);
        }
    }

    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        
    }

    @Override
    public void setPosition(Point2D newPos, Point2D offSet) {
        position=newPos;
    }

    @Override
    public boolean wasSelected(Point2D pos, Point2D offSet) {
        boolean result =false;
        if (currentShape.getBounds2D().contains(pos)) {            
            result=true;
            System.out.println("is taked");
        }   
        return result;
    }
    
}
