/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import sm.sound.SMClipPlayer;
import sm.sound.SMPlayer;

/**
 * That class will create a internal window that will play and sound.
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class InternalWindowSound extends InternalWindow{
    
    /**
     * That variable will manage the played sound
     */
    SMPlayer player;
    
    /**
     * Constructor of our InternalWindowSound
     * @param f the file that will be played
     * @param window 
     */
    public InternalWindowSound(File file,MainWindow window) {
        initComponents();
        parent=window;
        
        type=InternalWindowType.SOUND;
        player = new SMClipPlayer(file);
        ((SMClipPlayer)player).addLineListener(new LineListener() {
            @Override
            public void update(LineEvent event) {
                if (event.getType() == LineEvent.Type.STOP) {
                    if(player!=null) player.stop();
                    stopButton.setSelected(true);
                    playButton.setIcon(new ImageIcon(getClass().getResource("/iconos/PlayPressed_48x48.png")));
                    stopButton.setIcon(new ImageIcon(getClass().getResource("/iconos/StopDisabled_48x48.png")));
                }
            }
            
        });
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        playButton = new javax.swing.JToggleButton();
        stopButton = new javax.swing.JToggleButton();

        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                if(player!=null ) player.stop();
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
        getContentPane().setLayout(new java.awt.FlowLayout());

        buttonGroup1.add(playButton);
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/PlayPressed_48x48.png"))); // NOI18N
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(player!=null) player.play();
                playButton.setSelected(true);
                playButton.setIcon(new ImageIcon(getClass().getResource("/iconos/PlayDisabled_48x48.png")));
                stopButton.setIcon(new ImageIcon(getClass().getResource("/iconos/StopNormalRed_48x48.png")));
            }
        });
        getContentPane().add(playButton);

        buttonGroup1.add(stopButton);
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/StopDisabled_48x48.png"))); // NOI18N
        stopButton.setSelected(true);
        stopButton.setToolTipText("");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if(player!=null ) player.stop();
            }
        });
        getContentPane().add(stopButton);

        pack();
    }
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {                                            
        parent.changeCurrentIntWind(this);
    } 
    
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton playButton;
    private javax.swing.JToggleButton stopButton;
    
}
