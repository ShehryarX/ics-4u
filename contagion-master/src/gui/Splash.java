package gui;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {
    private JLayeredPane pane; // gui management
    private JLabel jLabel; // jlabel to display the image

    /*
    Creates a splash screen that exists upon mouse click

    @param name is the name of the splash screen
     */
    public Splash(String name) {
        pane = getLayeredPane();
        setSize(new Dimension(3400 / 4, 1900 / 4));

        // cretes the jlabel
        switch (name.toLowerCase()) {
            case "zombie":
                initImage("res/zombiesWin.png");
                break;
            case "human":
                initImage("res/humansWin.jpg");
                break;
            default:
                initImage("res/splash.jpg");
        }

        display();
    }

    /*
    Given image path it loads the image and resizes it.

    @param path is the path of the image
    */
    private void initImage(String path) {
        // get image path
        jLabel = new JLabel(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(
                3400 / 4, 1900 / 4, Image.SCALE_SMOOTH)));
        jLabel.setBounds(0, 0, 3400 / 4, 1900 / 4);
    }

    /*
    Adds splash screen to gui and adds mouse listener to image.
     */
    private void display() {
        pane.add(jLabel);
    }
}