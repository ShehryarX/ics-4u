package gui;

import log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OptionPanel extends JPanel implements ActionListener {
    /*
    - Creates JPanel of specified width and height
     */
    public OptionPanel(int width, int height) {
        setPreferredSize(new Dimension(width, height)); // set dimensions

        // create buttons
        initButtons();
    }

    /*
    - Creates all options at the top of the JPanel
     */
    public void initButtons() {
        // create new drop down for file IO
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");;

        // add action listener to save file
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Log.save();
            }
        });

        // add action listener to load file
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });



        // add to panel
        add(save);
        add(load);
    }

    /*
    - Handles mouse clicks to buttons and more options
     */
    public void actionPerformed(ActionEvent e) {

    }

}