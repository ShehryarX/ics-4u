package gui;


import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel {

    private Image img;

    /*
    @param img will create an image of maximum dimensions
     */
    ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    // paints the JPanel with the current image
    public void paintComponent(Graphics g) {
        g.drawImage(img, 0, 0, null);
    }
}