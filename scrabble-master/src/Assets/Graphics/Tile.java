package Assets.Graphics;

import Interfaces.GUIConstants;

import javax.swing.*;

public class Tile extends JLabel implements GUIConstants {
    private static int[] TILE_VALUE = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1,
            1, 4, 4, 8, 4, 10};
    private char letter;
    private int value;

    public Tile(char letter) {
        super(new ImageIcon("images\\unmarked\\letter_" + ("" + letter).toUpperCase() + ".jpg"));
        this.letter = letter;
        this.value = TILE_VALUE[letter - 'a'];
    }

    public void setMarked() {
        super.setIcon(new ImageIcon("images\\marked\\letter_" + ("" + letter).toUpperCase() + ".jpg"));
    }

    public char getLetter() {
        return letter;
    }

    public int getValue() {
        return value;
    }
}