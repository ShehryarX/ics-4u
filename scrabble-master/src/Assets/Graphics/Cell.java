package Assets.Graphics;

import GUI.Location;
import Interfaces.GUIConstants;

import javax.swing.*;

public class Cell extends JLabel implements GUIConstants, Comparable {
    private Tile tile;
    private Location location;
    private String identifier;

    public Cell(int row, int col, String identifier) {
        super(new ImageIcon("images\\unmarked\\" + identifier.toLowerCase() + ".jpg"));
        this.location = new Location(row, col);
        this.identifier = identifier;
    }

    public Cell(Location location, String identifier) {
        super(new ImageIcon("images\\unmarked\\" + identifier.toLowerCase() + ".jpg"));
        this.location = location;
        this.identifier = identifier;
    }

    public Location getLocationOnBoard() {
        return this.location;
    }

    public boolean isEmpty() {
        return this.tile == null;
    }

    public boolean isDoubleLetter() {
        return this.identifier.equals(DL);
    }

    public boolean isTripleLetter() {
        return this.identifier.equals(TL);
    }

    public boolean isDoubleWord() {
        return this.identifier.equals(DW);
    }

    public boolean isTripleWord() {
        return this.identifier.equals(TW);
    }

    public boolean isCenter() {
        return this.identifier.equals(CENTER_PIECE);
    }

    public void disableBonus() {
        this.identifier = EMPTY;
    }

    public void place(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }

    public void removeTile() {
        this.tile = null;
    }

    @Override
    public int compareTo(Object o) {
        Cell other = (Cell) o;
        Location otherLocation = other.getLocationOnBoard();
        int row = otherLocation.getRow();
        int col = otherLocation.getCol();

        if(row < location.getRow() || col < location.getCol()) {
            return 1;
        } else if(row == location.getCol() && col == location.getCol()) {
            return 0;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "tile: " + tile.getLetter();
    }
}