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


public class FontClass {
    String font;
    int sizeFont;
    int styleFont;
    
    public FontClass(String aFont, int aSize,int aStyle){
        font=aFont;
        sizeFont=aSize;
        styleFont=aStyle;
    }
    
    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getSizeFont() {
        return sizeFont;
    }

    public void setSizeFont(int sizeFont) {
        this.sizeFont = sizeFont;
    }

    public int getStyleFont() {
        return styleFont;
    }

    public void setStyleFont(int styleFont) {
        this.styleFont = styleFont;
    }
    
    
    
}
