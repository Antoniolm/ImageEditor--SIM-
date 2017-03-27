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
package GUI;

import draweditor.Attribute;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;

/**
 *
 * @author LENOVO
 */
public class InternalWindow extends javax.swing.JInternalFrame {
    /**
     * Creates new form InternalWindow
     */
    
    
    public InternalWindow() {
        initComponents();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        canvasPanel = new GUI.CanvasPanel();

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        getContentPane().add(canvasPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void setGeometry(GeometryType geom){
        canvasPanel.setGeometry(geom);
    }
    void setFilled(boolean value){
        canvasPanel.setFilled(value);
    }
    
    public void setColor(Paint value){
        canvasPanel.setColor(value);
    }
    
    void setThickness(int value){
        canvasPanel.setThick(new BasicStroke(value));
    }
    
    void setTransparency(boolean value){
        if(value)
            canvasPanel.setTransparency(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        else
            canvasPanel.setTransparency(null);
    }
    
    void setRender(boolean value){
        if(value)
            canvasPanel.setRender(new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON));
        else
            canvasPanel.setRender(null);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private GUI.CanvasPanel canvasPanel;
    // End of variables declaration//GEN-END:variables
}
