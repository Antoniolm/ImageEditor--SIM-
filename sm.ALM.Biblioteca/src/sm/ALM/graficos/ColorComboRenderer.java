/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

public class ColorComboRenderer extends JPanel implements ListCellRenderer {
  protected Color currentColor = Color.black;

  public ColorComboRenderer() {
    super();
    setBorder(new CompoundBorder(
        new MatteBorder(5,5,5,20, Color.white), new LineBorder(
            Color.black)));
  }

  public Component getListCellRendererComponent(JList list, Object obj,int row, boolean sel, boolean hasFocus) {
    if (obj instanceof Color)
      currentColor = (Color) obj;
    return this;
  }

  public void paint(Graphics g) {
    setBackground(currentColor);
    super.paint(g);
  }
}
  
