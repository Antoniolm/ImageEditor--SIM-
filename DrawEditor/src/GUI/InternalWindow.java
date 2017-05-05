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

import sm.ALM.graficos.*;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Cursor;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

public class InternalWindow extends javax.swing.JInternalFrame {
    /**
     * Creates new form InternalWindow
     */
    MainWindow parent=null;
    
    public InternalWindow(MainWindow window) {
        initComponents();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        parent=window;
        setSize(300, 300);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        canvasPanel = new sm.ALM.graficos.canvas2DImagePanel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        canvasPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                canvasPanelMouseMoved(evt);
            }
        });
        canvasPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                canvasPanelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout canvasPanelLayout = new javax.swing.GroupLayout(canvasPanel);
        canvasPanel.setLayout(canvasPanelLayout);
        canvasPanelLayout.setHorizontalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 382, Short.MAX_VALUE)
        );
        canvasPanelLayout.setVerticalGroup(
            canvasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 262, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(canvasPanel);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        parent.changeCurrentIntWind(this);
    }//GEN-LAST:event_formInternalFrameActivated

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
               
    }//GEN-LAST:event_formMouseEntered

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        
    }//GEN-LAST:event_formMouseMoved

    private void canvasPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasPanelMouseMoved
        BufferedImage imgSrc=canvasPanel.getImage();
                
        if(evt.getX()<imgSrc.getWidth() && evt.getY()<imgSrc.getHeight()){
            int currentRGB=imgSrc.getRGB(evt.getX(),evt.getY());
                
            int A= (currentRGB >> 24) & 0xFF;
            int R= (currentRGB >> 16) & 0xFF;
            int G= (currentRGB >> 8 ) & 0xFF;
            int B= (currentRGB) & 0xFF;
            
            parent.setCursorState("("+evt.getX()+","+evt.getY()+") ("+R+","+G+","+B+",["+A+"])");
        }
        else
            parent.setCursorState("("+evt.getX()+","+evt.getY()+")");
    }//GEN-LAST:event_canvasPanelMouseMoved

    private void canvasPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_canvasPanelMouseExited
        parent.setCursorState("");
    }//GEN-LAST:event_canvasPanelMouseExited

    void setGeometry(GeometryType geom){
        canvasPanel.setGeometry(geom);
    }
    public canvas2DImagePanel getCanvas(){
        return canvasPanel;
    }
            
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private sm.ALM.graficos.canvas2DImagePanel canvasPanel;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
