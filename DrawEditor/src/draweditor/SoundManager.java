/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draweditor;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 *
 * @author LENOVO
 */
public class SoundManager implements LineListener {
    @Override
    public void update(LineEvent event) {
        if (event.getType() == LineEvent.Type.START) {
            System.out.println("START"+event.getSource().getClass());
        }
        if (event.getType() == LineEvent.Type.STOP) {
            System.out.println("STOP"+event.getSource().getClass());
        }
        if (event.getType() == LineEvent.Type.CLOSE) {
            System.out.println("CLOSE"+event.getSource().getClass());
        }
    }
}