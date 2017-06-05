/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author LENOVO
 */
public class InternalWindowJMFPlayer extends InternalWindow{

     private Player player = null;

    
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
    
    public static InternalWindowJMFPlayer getInstance(File f,MainWindow window){
        InternalWindowJMFPlayer v = new InternalWindowJMFPlayer(f,window);
        if(v.player!=null) return v;
        else return null;
    }
    
    public void play() {
        if (player != null) {
            try {
                player.start();
            } catch (Exception e) {
                System.err.println("VentanaInternaJMFPlayer: "+e);
            }
        }
    }

    public void close() {
        if (player != null) {
            try {
                player.close();
            } catch (Exception e) {
                System.err.println("VentanaInternaJMFPlayer: "+e);
            }
        }
    }
    
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
