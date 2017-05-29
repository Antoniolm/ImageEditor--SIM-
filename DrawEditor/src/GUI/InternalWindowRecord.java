/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import draweditor.SoundManager;
import java.io.File;
import javax.swing.JInternalFrame;
import sm.sound.SMRecorder;
import sm.sound.SMSoundPlayerRecorder;

/**
 *
 * @author LENOVO
 */
public class InternalWindowRecord extends InternalWindow{
    
    SMRecorder recorder;
    /**
     * Creates new form InternalWindowRecord
     */
    public InternalWindowRecord(File file) {
        initComponents();
        type=InternalWindowType.RECORD;
        recorder=new SMSoundPlayerRecorder(file);
        ((SMSoundPlayerRecorder)recorder).addLineListener(new SoundManager());
    }

    @Override
    public void initComponents() {
        internalWind=new JInternalFrame();
        internalWind.setClosable(true);
        internalWind.setIconifiable(true);
        internalWind.setMaximizable(true);
        internalWind.setResizable(true);
        buttonGroup1 = new javax.swing.ButtonGroup();
        recordButton = new javax.swing.JToggleButton();
        stopButton = new javax.swing.JToggleButton();

        internalWind.addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
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
        internalWind.getContentPane().setLayout(new java.awt.FlowLayout());

        buttonGroup1.add(recordButton);
        recordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/RecordPressed_48x48.png"))); // NOI18N
        recordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordButtonActionPerformed(evt);
            }
        });
        internalWind.getContentPane().add(recordButton);

        buttonGroup1.add(stopButton);
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/StopDisabled_48x48.png"))); // NOI18N
        stopButton.setSelected(true);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });
        internalWind.getContentPane().add(stopButton);

        internalWind.pack();
    }
    
    private void formInternalFrameClosing(javax.swing.event.InternalFrameEvent evt) {                                          
        if(recorder!= null) recorder.stop();
    }                                         

    private void recordButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        if(recorder!= null) recorder.record();
    }                                            

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        if(recorder!= null) recorder.stop();
    } 
    
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JToggleButton recordButton;
    private javax.swing.JToggleButton stopButton;
}
