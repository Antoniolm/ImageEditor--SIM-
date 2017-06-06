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

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.media.Buffer;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JInternalFrame;

/**
 * That class will create a internal window that will show our video.
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class InternalWindowJMFPlayer extends InternalWindow{

    /**
     * That variable will manage the played video
     */
     private Player player = null;

    /**
     * Constructor of our internalWindowJMFPlayer
     * @param f the file that will be played
     * @param window 
     */
    private InternalWindowJMFPlayer(File f,MainWindow window) {
        initComponents();
        parent=window;
        
        type=InternalWindowType.VIDEO;
        String sfichero = "file:" + f.getAbsolutePath();
        MediaLocator ml = new MediaLocator(sfichero);
        try {
            player = Manager.createRealizedPlayer(ml);
            Component vc = player.getVisualComponent();
            if(vc!=null)add(vc, java.awt.BorderLayout.CENTER);
                Component cpc = player.getControlPanelComponent();
            if(cpc!=null)add(cpc, java.awt.BorderLayout.SOUTH);
                pack();
        }catch(Exception e) {
            System.err.println("InternalWindowJMFPlayer: "+e);
            player = null;
        }
    }
    
    /**
     * It will return the instance of our internal window video
     * @param f the file that will be played
     * @param window The parent of our internal window
     * @return 
     */
    public static InternalWindowJMFPlayer getInstance(File f,MainWindow window){
        InternalWindowJMFPlayer v = new InternalWindowJMFPlayer(f,window);
        if(v.player!=null) return v;
        else return null;
    }
    
    /**
     * It will play our current video
     */
    public void play() {
        if (player != null) {
            try {
                player.start();
            } catch (Exception e) {
                System.err.println("VentanaInternaJMFPlayer: "+e);
            }
        }
    }

    /**
     * It will be close our current video
     */
    public void close() {
        if (player != null) {
            try {
                player.close();
            } catch (Exception e) {
                System.err.println("VentanaInternaJMFPlayer: "+e);
            }
        }
    }
    
    /**
     * It will return the current frame of our video
     * @return buffered image
     */
    public BufferedImage getFrame(){
        FrameGrabbingControl fgc;
        String claseCtr = "javax.media.control.FrameGrabbingControl";
        fgc = (FrameGrabbingControl)player.getControl(claseCtr);
        Buffer bufferFrame = fgc.grabFrame();
        BufferToImage bti;
        bti=new BufferToImage((VideoFormat)bufferFrame.getFormat());
        Image img = bti.createImage(bufferFrame);
        return (BufferedImage)img;
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
