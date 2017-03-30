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
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Attribute {
    Stroke stroke;
    Integer strokeValue;
    Paint colorPaint;
    boolean filled;
    Composite comp;
    RenderingHints render;
    
    /**
     * Constructor
     */
    public Attribute(){
        stroke=null;
        colorPaint=new Color(0,0,0);
        filled=false;
        comp=null;
        render=null;
        strokeValue=1;
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
    
    public Stroke getStroke() {
        return stroke;
    }

    public Integer getThickness(){
        return strokeValue;
    }
    
    public void setStroke(Stroke stroke,Integer value) {
        this.stroke = stroke;
        strokeValue=value;
    }

    public Paint getColor() {
        return colorPaint;
    }
    
    public boolean getFilled() {
        return filled;
    }

    public void setFilled(Paint aPaint) {
        this.colorPaint = aPaint;
    }

    public Composite getComp() {
        return comp;
    }

    public void setComp(Composite comp) {
        this.comp = comp;
    }

    public RenderingHints getRender() {
        return render;
    }

    public void setRender(RenderingHints render) {
        this.render = render;
    }
}
