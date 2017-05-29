/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.io.File;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.Player;
import javax.swing.JInternalFrame;

/**
 *
 * @author LENOVO
 */
public class InternalWindowJMFPlayer extends InternalWindow{

     private Player player = null;

    
    private InternalWindowJMFPlayer(File f) {
        initComponents();
        
        String sfichero = "file:" + f.getAbsolutePath();
        MediaLocator ml = new MediaLocator(sfichero);
        try {
            player = Manager.createRealizedPlayer(ml);
            Component vc = player.getVisualComponent();
            if(vc!=null)internalWind.add(vc, java.awt.BorderLayout.CENTER);
                Component cpc = player.getControlPanelComponent();
            if(cpc!=null)internalWind.add(cpc, java.awt.BorderLayout.SOUTH);
                internalWind.pack();
        }catch(Exception e) {
            System.err.println("InternalWindowJMFPlayer: "+e);
            player = null;
        }
    }
    
    public static InternalWindowJMFPlayer getInstance(File f){
        InternalWindowJMFPlayer v = new InternalWindowJMFPlayer(f);
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
    
    @Override
    public void initComponents() {
        internalWind=new JInternalFrame();
        internalWind.setClosable(true);
        internalWind.setIconifiable(true);
        internalWind.setMaximizable(true);
        internalWind.setResizable(true);
        internalWind.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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

        internalWind.pack();
    }
    
}
