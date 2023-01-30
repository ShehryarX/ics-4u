package Logic;

import javax.swing.*;

public class Player implements Comparable {
    private String name;
    private int score;
    private JLabel icon;
    private JLabel[] selectionBoard;
    private boolean chosenToContinue;

    public Player(String name, String iconName) {
        this.name = name;
        this.score = 0;
        this.icon = new JLabel(new ImageIcon(iconName));
        
        this.selectionBoard = new JLabel[7];
        this.chosenToContinue = true;
    }

    public boolean hasChosenToContinue() {
        return chosenToContinue;
    }

    public void setChosenToContinue(boolean choseToContinue) {
        this.chosenToContinue = choseToContinue;
    }

    public JLabel[] getSelectionBoard() {
        return selectionBoard;
    }

    public JLabel getIcon() {
        return icon;
    }

    public void setIcon(JLabel icon) {
        this.icon = icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    @Override
    public int compareTo(Object o) {
        Player p = (Player) o;

        if (p.score > this.score) {
            return 1;
        } else if (p.score == this.score) {
            return 0;
        }

        return -1;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + '\'' + ", score=" + score + '}';
    }
}
