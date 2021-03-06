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
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Map;

/**
 * It will manage a text figure in our system
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class Text2DFigure extends Figure{
    private String text;    //The text will be show in our shape
    private String font;    //the font that will be used for our shape
    private Point2D position; //the position of our shape in the canvas
    
    /**
     * Constructor
     * @param initPos the initial position of our text
     * @param anAtt the attribute of our shape 
     */
    public Text2DFigure(Point2D initPos,Attribute anAtt){
        super();
        text=" ";
        position=initPos;
        attribute=anAtt;
        TextDialog dialog = new TextDialog(new javax.swing.JFrame(), true, this);
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                dialog.dispose();
            }
        });
        dialog.setVisible(true);
    }
    
    /**
     * It will set the string that our shape will show
     * @param aText the string to show
     */
    public void setString(String aText){
        text=aText;
    }
    
    /**
     * It will draw our shape
     * @param g2d the graphic where the shape will be draw
     * @param drawImage true if is draw in an image
     */
    @Override
    public void draw(Graphics2D g2d,boolean drawImage) {
        
        if(editMode && ! drawImage){
            g2d.setColor(Color.GRAY);
            g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F,new float[]{ 5.0F, 5.0F }, 0.0F));
            g2d.draw(currentShape.getBounds2D());
            g2d.setStroke(new BasicStroke());
        }
        
        getTextShape(g2d, attribute.getFont().generateFont());
                
        AffineTransform trans=new AffineTransform();
        trans.translate(position.getX(), position.getY());
        currentShape=trans.createTransformedShape(currentShape);
        
        Rectangle bound=currentShape.getBounds();
        if(attribute.getFilled())
            attribute.generateFilled((int)(( Rectangle) bound).getX(),(int) (( Rectangle) bound).getY(),
            (int)(( Rectangle) bound).getHeight(),(int)(( Rectangle) bound).getWidth());
        
        attribute.apply(g2d);
        
        if(!attribute.getFilled())
                g2d.draw(currentShape);
        else {
                g2d.fill(currentShape);
        }
    }
    
    /**
     * It will generate our shape with the current text
     * @param g2d the current graphics
     * @param font the font that will be use our text
     */
    public void getTextShape(Graphics2D g2d, Font font) {
        if(text!=""){          
            FontRenderContext frc = g2d.getFontRenderContext();
            TextLayout tl = new TextLayout(text, font, frc);
            currentShape=tl.getOutline(null);
        }
    }

    /**
     * It will update the position of our second position of our shape
     * @param initPos
     * @param point 
     */
    @Override
    public void updatePosition(Point2D initPos, Point2D point) {
        
    }

    /**
     * It will set a new position for our shape
     * @param newPos 
     */
    @Override
    public void setPosition(Point2D newPos) {
        newPos.setLocation(newPos.getX()-offSet.getX(),newPos.getY()-offSet.getY());
        position=newPos;
    }

    /**
     * It will return if the shape was selected or not (in edit mode)
     * @param pos the position of our mouse
     * @return 
     */
    @Override
    public boolean wasSelected(Point2D pos) {
        boolean result =false;
        if (currentShape.getBounds2D().contains(pos)) {            
            offSet.setLocation(Math.abs(pos.getX() - position.getX()), Math.abs(pos.getY() - position.getY()));
            result=true;
        }   
        return result;
    }
    
}
