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

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Attribute {
    Stroke stroke;
    Integer strokeValue;
    String strokeStyle;
    Paint colorPaint;
    boolean filled;    
    Composite comp;
    int transValue;
    RenderingHints render;
    FontClass font;

    /**
     * Constructor
     */
    public Attribute(){
        stroke=null;
        strokeStyle="Solid line";
        colorPaint=new Color(0,0,0);
        filled=false;
        comp=null;
        transValue=10;
        render=null;
        font=new FontClass("Arial",15,0);
        strokeValue=1;
    }
   
    
    /**
     * Copy constructor
     */
    public Attribute(Attribute att){
        stroke=att.stroke;
        colorPaint=att.colorPaint;
        filled=att.filled;
        comp=att.comp;
        transValue=att.transValue;
        render=att.render;
        font=att.font;
        strokeValue=att.strokeValue;
        strokeStyle=att.strokeStyle;
    }
    
    /**
     * It will apply the current attribue to a graphics2D object
     * @param g2d 
     */
    public void apply(Graphics2D g2d){
        if(stroke!=null)
            g2d.setStroke(stroke);
        
        g2d.setPaint(colorPaint);
        
        if(comp!=null)
            g2d.setComposite(comp);
        
        if(render!=null)
            g2d.setRenderingHints(render);
    }
    
    /**
     * 
     * @param render 
     */
    public void setRender(RenderingHints render) {
        this.render = render;
    }
    
    /**
     * 
     * @param comp 
     */
    public void setComp(Composite comp,int value) {
        this.comp = comp;
        transValue=value;
    }
    
    /**
     * 
     * @param aPaint 
     */
    public void setFilled(Paint aPaint) {
        this.colorPaint = aPaint;
    }

    /**
     * 
     * @param stroke
     * @param value 
     */
    public void setStroke(Stroke stroke,int value) {
        this.stroke = stroke;
        strokeValue=value;
    }
    
    public void setStrokeStyle(String valueStyle){
        strokeStyle=valueStyle;
    }

    /**
     * 
     * @param font 
     */
    public void setFont(FontClass font) {
        this.font = font;
    }
    
    
    /**
     * 
     * @return 
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * 
     * @return 
     */
    public int getThickness(){
        return strokeValue;
    }
    
    /**
     * 
     * @return 
     */
    public String getStrokeStyle(){
        return strokeStyle;
    }
       
    /**
     * 
     * @return 
     */
    public Paint getColor() {
        return colorPaint;
    }
    
    /**
     * 
     * @return 
     */
    public boolean getFilled() {
        return filled;
    }
    
    /**
     * 
     * @return 
     */
    public Composite getComp() {
        return comp;
    }
    
    public int getTransValue() {
        return transValue;
    }

    /**
     * 
     * @return 
     */
    public RenderingHints getRender() {
        return render;
    }
    
    /**
     * 
     * @return 
     */ 
    public FontClass getFont() {
        return font;
    }

    
    
}
