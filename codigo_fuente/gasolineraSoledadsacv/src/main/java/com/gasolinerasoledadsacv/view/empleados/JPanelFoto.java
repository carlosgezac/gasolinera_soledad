package com.gasolinerasoledadsacv.view.empleados;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class JPanelFoto extends JPanel {

    private Image image;

    public JPanelFoto() {

    }

    public void repaintImage(Image image) {
        this.image = image;
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
