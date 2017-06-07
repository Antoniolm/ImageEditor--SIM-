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
/*
 * Código consultado para realizar este renderer es de la pagina java2s.com
 * Referencia ha dicho codigo : http://www.java2s.com/Code/Java/Swing-JFC/Colorcomboboxrenderer.htm
 * El código es poco flexible por lo que las modificaciones realizadas han sido pocas.
 */
package sm.ALM.graficos;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

/**
 * That class let us define the stlye of our color combo
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class ColorComboRenderer extends JPanel implements ListCellRenderer {
  protected Color currentColor; //Current color in our combo 

  /**
   * Constructor
   */
  public ColorComboRenderer() {
    super();
    setBorder(new CompoundBorder(
        new MatteBorder(5,5,5,5, Color.white), new LineBorder(
            Color.GRAY)));
  }

  /**
   * It will let us obtain the current component
   * @param list 
   * @param obj
   * @param row
   * @param sel
   * @param hasFocus
   * @return 
   */
  public Component getListCellRendererComponent(JList list, Object obj,int row, boolean sel, boolean hasFocus) {
    currentColor = (Color) obj;
    setBackground(currentColor);
    return this;
  }

  /**
   * Draw the current state of our combo
   * @param g 
   */
  public void paint(Graphics g) {
    setBackground(currentColor);
    super.paint(g);
  }
}
  
