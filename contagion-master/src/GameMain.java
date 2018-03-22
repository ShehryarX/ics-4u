

import gui.GameFrame;
import gui.Splash;

import javax.swing.*;

import java.awt.EventQueue;

public final class GameMain {


    private GameMain() {

    }

    /**
     * @param aRgs command line arguments, assumes none
     */
    public static void main(String[] aRgs) {
        //runs the game
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final JFrame screen = new Splash("splash");
                screen.setTitle("Welcome");
                screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                screen.setVisible(true);
                screen.setLocationRelativeTo(null);
                screen.setResizable(false);
            }
        });

        //runs the game
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                final GameFrame mframe = new GameFrame();
                mframe.setTitle("Terrain");
                mframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mframe.setVisible(true);
                mframe.setLocationRelativeTo(null);
                mframe.setResizable(false);
            }
        });
    }
}
