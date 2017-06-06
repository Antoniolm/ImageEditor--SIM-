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

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.swing.JInternalFrame;

/**
 * That class will create a internal window that will show our webcam.
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class InternalWindowCamera extends InternalWindow{
    /**
     * It will be manage our webcam
     */
    private Webcam camera = null;
    
    
    /**
     * Constructor of our internalWindowCamera
     * @param window The parent of our internal window
     */
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

    /**
     * It will return the instance of our internal window camera
     * @param window The parent of our internal window
     * @return 
     */
    public static InternalWindowCamera getInstance(MainWindow window){
        InternalWindowCamera v = new InternalWindowCamera(window);
        return (v.camera!=null?v:null);
    }
     
    /**
     * It will return a capture of our webCam 
     * @return 
     */
    public BufferedImage getImage(){
         return camera.getImage();
    }
    
    /**
     * It will close our WebCam
     */
    public void close() {
        if (camera != null) {
            try {
                camera.close();
            } catch (Exception e) {
                System.err.println("VentanaInternaJMFPlayer: "+e);
            }
        }
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
