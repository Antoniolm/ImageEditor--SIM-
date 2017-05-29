/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.image.BufferedImage;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import sm.ALM.graficos.Canvas2DPanel;
import sm.ALM.graficos.GeometryType;

/**
 *
 * @author LENOVO
 */
public class InternalWindowImage extends InternalWindow{
    /**
     * Creates new form InternalWindowImage
     */
    MainWindow parent=null;
    private Canvas2DPanel canvasPanel;
    private JScrollPane jScrollPane1;
    
    public InternalWindowImage(MainWindow window) {
        initComponents();
        parent=window;       
        type=InternalWindowType.IMAGE;
    }
    
    @Override
    public void initComponents() {
        internalWind=new JInternalFrame();
        internalWind.setClosable(true);
        internalWind.setIconifiable(true);
        internalWind.setMaximizable(true);
        internalWind.setResizable(true);
        internalWind.setSize(300, 300);
        jScrollPane1 = new JScrollPane();
        canvasPanel = new Canvas2DPanel();

        internalWind.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        internalWind.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });
        internalWind.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
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
        internalWind.addMouseListener(new java.awt.event.MouseAdapter() {
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

        internalWind.getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        internalWind.pack();
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

    void setGeometry(GeometryType geom){
        canvasPanel.setGeometry(geom);
    }
    
    public Canvas2DPanel getCanvas(){
        return canvasPanel;
    }

    
}
