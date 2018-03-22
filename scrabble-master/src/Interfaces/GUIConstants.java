package Interfaces;

import Assets.TileBag;
import GUI.Board;

import javax.swing.*;

public interface    GUIConstants {
    JLayeredPane J_LAYERED_PANE = Board.getjLayeredPane();
    int BOARD_SIZE = 15;
    String EMPTY = "Empty";
    String DL = "Double Letter";
    String TL = "Triple Letter";
    String DW = "Double Word";
    String TW = "Triple Word";
    String CENTER_PIECE = "Center";
    TileBag TILE_BAG = new TileBag();
}