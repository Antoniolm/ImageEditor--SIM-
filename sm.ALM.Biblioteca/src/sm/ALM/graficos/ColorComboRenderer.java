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

public class ColorComboRenderer extends JPanel implements ListCellRenderer {
  protected Color currentColor;

  public ColorComboRenderer() {
    super();
    setBorder(new CompoundBorder(
        new MatteBorder(5,5,5,20, Color.white), new LineBorder(
            Color.GRAY)));
  }

  public Component getListCellRendererComponent(JList list, Object obj,int row, boolean sel, boolean hasFocus) {
    currentColor = (Color) obj;
    setBackground(currentColor);
    return this;
  }

  public void paint(Graphics g) {
    setBackground(currentColor);
    super.paint(g);
  }
}
  
