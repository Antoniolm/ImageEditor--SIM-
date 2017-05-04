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
package sm.ALM.graficos;

import com.sun.javafx.iio.ImageStorage;
import java.awt.BasicStroke;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author LENOVO
 */
public class canvas2DImagePanel extends Canvas2DPanel {

    private BufferedImage image;
    /**
     * Creates new form canvas2DImagePanel
     */
    public canvas2DImagePanel() {
        initComponents();
        clipShape =new Rectangle2D.Float(1,1,widthSize-1,heightSize-1);
    }

    
    public void setImage(BufferedImage img){
        image = img;
        setPreferredSize(new Dimension(img.getWidth(),img.getHeight()));
    }
    
    public BufferedImage getImage(){
        return image;
    }
    
    public BufferedImage getImage(boolean drawVector){
        if (drawVector) {
            BufferedImage newImage=new BufferedImage(image.getWidth(),image.getHeight(),image.getType());
            paint(newImage.createGraphics());
            
            return newImage;
        }
        else
            return getImage();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(image!=null) g.drawImage(image,0,0,this);
        
        Graphics2D g2d = (Graphics2D)g;
        float[] patternLine = { 5.0F, 5.0F };
        g2d.setStroke(new BasicStroke(2.0F, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_MITER, 1.0F, patternLine, 0.0F));
        g2d.draw(clipShape);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(204, 204, 204));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        if(!editMode)
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        else
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_formMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
