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

import java.awt.image.BufferedImage;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import sm.ALM.graficos.Canvas2DPanel;
import sm.ALM.graficos.GeometryType;

/**
 * That class will create a internal window that will show an image.
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class InternalWindowImage extends InternalWindow{
    /**
     * Canvas that contain the image of our internal window
     */
    private Canvas2DPanel canvasPanel;
    
    /**
     * Constructor of our internalWindowImage
     * @param window The parent of our internal window
     */
    public InternalWindowImage(MainWindow window) {
        initComponents();
        parent=window;       
        type=InternalWindowType.IMAGE;
    }   
    
    /**
     * It will assign a new value to our geometry type in 
     * the canvas2DPanel
     * @param geom the new value for our geometry type
     */
    void setGeometry(GeometryType geom){
        canvasPanel.setGeometry(geom);
    }
    
    /**
     * It will return our canvas
     * @return our current canvas2DPanel
     */
    public Canvas2DPanel getCanvas(){
        return canvasPanel;
    }
    
    /**
     * It will initialize the components of our internal window
     */
    @Override
    public void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setSize(300, 300);
        JScrollPane jScrollPane1 = new JScrollPane();
        canvasPanel = new Canvas2DPanel();

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
                formInternalFrameClosing(evt);
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
    }
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {                                            
        parent.changeCurrentIntWind(this);
    }                                           

    private void formMouseEntered(java.awt.event.MouseEvent evt) {                                  
               
    }                                 

    private void formMouseMoved(java.awt.event.MouseEvent evt) {                                
        
    }                               

    private void canvasPanelMouseMoved(java.awt.event.MouseEvent evt) {                                       
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
    }                                      

    private void canvasPanelMouseExited(java.awt.event.MouseEvent evt) {                                        
        parent.setCursorState("");
    }                                       

    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {                                          
        parent.changeCurrentIntWind(null);
    }                                         
    
}
