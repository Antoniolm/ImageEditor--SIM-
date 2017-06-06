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

/**
 * That class will manage the attribute of our shapes
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class Attribute {
    Stroke stroke;        //stroke of our shape
    Integer strokeValue;  //thick of our shapes
    String strokeStyle;   //solid or broken line
    Color colorPaintT;    //Color Top
    Color colorPaintB;    //color bottom
    GradientPaint gPaint; //For our degrades 
    boolean filled;       //Is filled or not 
    String filledType;    //What type of filled has
    Composite comp;       //for our transparency feature
    int transValue;       //percentage of transparency
    RenderingHints render; //Soft edges
    FontClass font;        // font of our shape

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
     * @param g2d  the graphics where we apply the attributes
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
     * It will set the new value of our render
     * @param render the new value
     */
    public void setRender(RenderingHints render) {
        this.render = render;
    }
    
    /**
     * It will set the new value of our comp 
     * @param comp the new value
     */
    public void setComp(Composite comp,int value) {
        this.comp = comp;
        transValue=value;
    }
    
    /**
     * It will set the new value of our color top
     * @param aPaint the new value
     */
    public void setColorT(Color aColor) {
        this.colorPaintT = aColor;
    }
    
    /**
     * It will set the new value of our color bottom
     * @param aPaint the new value
     */
    public void setColorB(Color aColor) {
        this.colorPaintB= aColor;
    }

    /**
     * It will set the new value of our stroke
     * @param stroke the new value
     * @param value the new value
     */
    public void setStroke(Stroke stroke,int value) {
        this.stroke = stroke;
        strokeValue=value;
    }
    
    /**
     * It will set the new value of our stroke style
     * @param valueStyle the new value
     */
    public void setStrokeStyle(String valueStyle){
        strokeStyle=valueStyle;
    }

    /**
     * It will set the new value of our font
     * @param font the new value
     */
    public void setFont(FontClass font) {
        this.font = font;
    }
    
    /**
     * It will set the new value of our filled type
     * @param type the new value 
     */
    public void setFilledType(String type){
        filledType=type;
    }
    
    /**
     * It will return our stroke
     * @return the current stroke
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * It will return the thickness of our shape
     * @return the current thickness
     */
    public int getThickness(){
        return strokeValue;
    }
    
    /**
     * It will return the current style of line
     * @return the stroke style (can be solid or broken lines)
     */
    public String getStrokeStyle(){
        return strokeStyle;
    }
       
    /**
     * It will return the current top color
     * @return the current color of our ColorTop
     */
    public Paint getColorT() {
        return colorPaintT;
    }
    
    /**
     * It will return the current bottom color
     * @return the current color of our Colorbottom
     */
    public Paint getColorB() {
        return colorPaintB;
    }
    
    /**
     * It will return if the shape is filled or not
     * @return true if is filled and false if is not filled
     */
    public boolean getFilled() {
        return filled;
    }
    
    /**
     * It will return the type of filled of our shape
     * @return can be - horizontal degrade, vertical degrade, filled
     */
    public String getFilledType(){
        return filledType;
    }
    
    /**
     * It will return the transparency object
     * @return our transparency object
     */
    public Composite getComp() {
        return comp;
    }
    
    /**
     * It will return the percentage of transparency of our shape
     * @return percentage
     */
    public int getTransValue() {
        return transValue;
    }

    /**
     * It will return render object of our attribute
     * @return the render object
     */
    public RenderingHints getRender() {
        return render;
    }
    
    /**
     * It will return the current font of our shape
     * @return font of our attribute
     */ 
    public FontClass getFont() {
        return font;
    }
    
    /**
     * It will generate the filled of our shape
     * @param x the x position of our shape
     * @param y the y position of our shape
     * @param h the height size of our shape
     * @param w the width size of our shape
     */
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
