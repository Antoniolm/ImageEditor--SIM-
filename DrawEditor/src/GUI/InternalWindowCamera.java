/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JInternalFrame;

/**
 *
 * @author LENOVO
 */
public class InternalWindowCamera extends InternalWindow{
    /**
     * Creates new form InternalWindowCamera
     */
    private Webcam camera = null;
    
    private InternalWindowCamera(MainWindow window) {
        initComponents();
        parent=window;
        
        type=InternalWindowType.CAMERA;
        camera = Webcam.getDefault();
        
        Dimension resoluciones[] = camera.getViewSizes();
        Dimension maxRes = resoluciones[resoluciones.length-1];
        camera.setViewSize(maxRes);
        
        WebcamPanel areaVisual;
        
        if (camera != null) {
            areaVisual = new WebcamPanel(camera);
            areaVisual.setFitArea(false);
            
            if (areaVisual!= null) {
                getContentPane().add(areaVisual, BorderLayout.CENTER);
                pack();
            }
        }

    }

    
    public static InternalWindowCamera getInstance(MainWindow window){
        InternalWindowCamera v = new InternalWindowCamera(window);
        return (v.camera!=null?v:null);
    }
     
    public BufferedImage getImage(){
         return camera.getImage();
    }
    
    public void close() {
        if (camera != null) {
            try {
                camera.close();
            } catch (Exception e) {
                System.err.println("VentanaInternaJMFPlayer: "+e);
            }
        }
    }
    
    @Override
    public void initComponents() {
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                close();
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

        pack();
    }
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {                                            
        parent.changeCurrentIntWind(this);
    } 
    
}
