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


public class FontClass {
    String fontType;
    int sizeFont;
    boolean isBold;
    boolean isItalic;
    boolean isUnderLine;
    
    public FontClass(String aFont, int aSize,boolean bold, boolean italic, boolean underLight){
        fontType=aFont;
        sizeFont=aSize;
        isBold=bold;
        isItalic=italic;
        isUnderLine=underLight;
    }
    
    public String getFont() {
        return fontType;
    }

    public void setFont(String font) {
        this.fontType = font;
    }

    public int getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }

    public boolean isBold() {
        return isBold;
    }
    
    public boolean isItalic() {
        return isItalic;
    }

    public boolean isUnder() {
        return isUnderLine;
    }
    
    public void setStyle(boolean bold, boolean italic, boolean underLight) {
        isBold=bold;
        isItalic=italic;
        isUnderLine=underLight;
    }
    
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
