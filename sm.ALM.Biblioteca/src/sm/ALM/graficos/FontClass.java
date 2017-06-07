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

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
 * That class will manage all the feature of a font 
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class FontClass {
    String fontType;    //Font type of our text
    int sizeFont;       //size of our text
    boolean isBold;     //true if the text use bold style
    boolean isItalic;   //true if the text use italic style
    boolean isUnderLine;//true if the text use underline style
    
    /**
     * Constructor
     * @param aFont //the font type
     * @param aSize //the size of our text
     * @param bold  //true if is activated that style
     * @param italic //true if is activated that style
     * @param underLight  //true if is activated that style
     */
    public FontClass(String aFont, int aSize,boolean bold, boolean italic, boolean underLight){
        fontType=aFont;
        sizeFont=aSize;
        isBold=bold;
        isItalic=italic;
        isUnderLine=underLight;
    }
    
    /**
     * It will set the new value of our font type
     * @param font 
     */
    public void setFont(String font) {
        this.fontType = font;
    }
    
    /**
     * It will set the size of word
     * @param sizeFont 
     */
    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }

    /**
     * It will return the current size of word
     * @return 
     */
    public int getSizeFont() {
        return sizeFont;
    }
    
    /**
     * It will return the font tpye
     * @return 
     */
    public String getFont() {
        return fontType;
    }

    /**
     * It will return if the bold style is activated or not
     * @return true if bold is activated
     */
    public boolean isBold() {
        return isBold;
    }
    
    /**
     * It will return if the italic style is activated or not
     * @return true if italic is activated
     */
    public boolean isItalic() {
        return isItalic;
    }

    /**
     * It will return if the underline style is activated or not
     * @return true if underline is activated 
     */
    public boolean isUnder() {
        return isUnderLine;
    }
    
    /**
     * It will set the style of our text
     * @param bold
     * @param italic
     * @param underLight 
     */
    public void setStyle(boolean bold, boolean italic, boolean underLight) {
        isBold=bold;
        isItalic=italic;
        isUnderLine=underLight;
    }
    
    /**
     * It will generate the current font
     * @return the font with all the feautes
     */
    public Font generateFont(){
        int style=0;
        if(isBold)
            style=style | Font.BOLD;
        if(isItalic)
            style=style | Font.ITALIC;
        
        Font font=new Font(fontType,style,sizeFont);
        
        if(isUnderLine){
            Map atributosTexto = font.getAttributes();
            atributosTexto.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
            font=font.deriveFont(atributosTexto);
        }
        
        return font;
    }
    
    
    
}
