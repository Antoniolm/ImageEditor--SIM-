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
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;

public class Attribute {
    Stroke stroke;
    Integer strokeValue;
    String strokeStyle;
    Color colorPaintT;
    Color colorPaintB;
    GradientPaint gPaint;
    boolean filled;   
    String filledType;
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
        colorPaintT=new Color(0,0,0);
        colorPaintB=new Color(0,0,0);
        gPaint=null;
        filled=false;
        filledType="Filled";
        comp=null;
        transValue=10;
        render=null;
        font=new FontClass("Arial",15,false,false,false);
        strokeValue=1;
    }
   
    
    /**
     * Copy constructor
     */
    public Attribute(Attribute att){
        stroke=att.stroke;
        colorPaintT=att.colorPaintT;
        colorPaintB=att.colorPaintB;
        filled=att.filled;
        filledType=att.filledType;
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
        
        if(filledType=="Filled" || gPaint==null)
            g2d.setPaint(colorPaintT);
        else
            g2d.setPaint(gPaint);
        
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
    public void setColorT(Color aColor) {
        this.colorPaintT = aColor;
    }
    
    /**
     * 
     * @param aPaint 
     */
    public void setColorB(Color aColor) {
        this.colorPaintB= aColor;
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
    
    /**
     * 
     * @param valueStyle 
     */
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
     * @param type 
     */
    public void setFilledType(String type){
        filledType=type;
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
    public Paint getColorT() {
        return colorPaintT;
    }
    
    /**
     * 
     * @return 
     */
    public Paint getColorB() {
        return colorPaintB;
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
    public String getFilledType(){
        return filledType;
    }
    
    /**
     * 
     * @return 
     */
    public Composite getComp() {
        return comp;
    }
    
    /**
     * 
     * @return 
     */
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
    
    public void generateFilled(int x,int y,int h,int w){
        switch(filledType){
            case "Filled" :
                gPaint=null;
            break;
            case "Vertical degrade" :
                gPaint = new GradientPaint(x, y+(h/2), colorPaintT, x+w, y+(h/2), colorPaintB);
                break;
            case "Horizontal degrade" : 
                gPaint = new GradientPaint(x+(w/2), y, colorPaintT, x+(w/2), y+h, colorPaintB);
                break;
        }
    }

    
    
}
