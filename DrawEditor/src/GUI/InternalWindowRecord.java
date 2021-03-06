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

import java.io.File;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import sm.sound.SMRecorder;
import sm.sound.SMSoundPlayerRecorder;

/**
 * That class will create a internal window that will let us record a sound.
 * @author Antonio David López Machado antoniolm@correo.ugr.es
 */
public class InternalWindowRecord extends InternalWindow{
    /**
     * It will be manage the recorded sound
     */
    SMRecorder recorder;
    
    /**
     * Constructor of our internalWindowCamera
     * @param file the file where the sound will be record
     * @param window The parent of our internal window
     */
    public InternalWindowRecord(File file,MainWindow window) {
        initComponents();
        parent=window;
        type=InternalWindowType.RECORD;
        recorder=new SMSoundPlayerRecorder(file);
    }

    /**
     * It will initialize the components of our internal window
     */
    @Override
    public void initComponents() {
        new JInternalFrame();
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        buttonGroup1 = new javax.swing.ButtonGroup();
        recordButton = new javax.swing.JToggleButton();
        stopButton = new javax.swing.JToggleButton();

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
        getContentPane().setLayout(new java.awt.FlowLayout());

        buttonGroup1.add(recordButton);
        recordButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/RecordPressed_48x48.png"))); // NOI18N
        recordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recordButton.setSelected(true);
                recordButton.setIcon(new ImageIcon(getClass().getResource("/iconos/RecordDisabled_48x48.png")));
                stopButton.setIcon(new ImageIcon(getClass().getResource("/iconos/StopNormalRed_48x48.png")));
                recordButtonActionPerformed(evt);
            }
        });
        getContentPane().add(recordButton);

        buttonGroup1.add(stopButton);
        stopButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/StopDisabled_48x48.png"))); // NOI18N
        stopButton.setSelected(true);
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButton.setSelected(true);
                recordButton.setIcon(new ImageIcon(getClass().getResource("/iconos/RecordPressed_48x48.png")));
                stopButton.setIcon(new ImageIcon(getClass().getResource("/iconos/StopDisabled_48x48.png")));
                stopButtonActionPerformed(evt);
            }
        });
        getContentPane().add(stopButton);

        pack();
    }
    
    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {                                            
        parent.changeCurrentIntWind(this);
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
