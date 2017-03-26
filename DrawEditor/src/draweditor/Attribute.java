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
package draweditor;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Attribute {
    Stroke stroke;
    Paint filled;
    Composite comp;
    RenderingHints render;   
    public Attribute(){
        stroke=null;
        filled=null;
        comp=null;
        render=null;
    }
   
    public void apply(Graphics2D g2d){
        if(stroke!=null)
            g2d.setStroke(stroke);
        
        if(filled!=null)
            g2d.setPaint(filled);
        
        if(comp!=null)
            g2d.setComposite(comp);
        
        if(render!=null)
            g2d.setRenderingHints(render);
    }
    
    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Paint getFilled() {
        return filled;
    }

    public void setFilled(Paint filled) {
        this.filled = filled;
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
