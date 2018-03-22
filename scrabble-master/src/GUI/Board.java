package GUI;

import Assets.Graphics.Cell;
import Assets.Graphics.Tile;
import Interfaces.GUIConstants;
import Logic.Player;
import Logic.Result;
import Logic.WordChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board extends JFrame implements GUIConstants {
    private static JLayeredPane jLayeredPane;

    private JLabel background;
    private JLabel tileCount;
    private JLabel[][] recentScoreTracker;
    private JLabel[] playerNames;
    private JLabel[] playerIcon;
    private JLabel[] playerScore;
    private JLabel[][] board;
    private JLabel doneButton;
    private JLabel shuffleButton;
    private JLabel swapButton;
    private ArrayList<Cell> moves;
    private int spacing;
    private int initialX;
    private int initialY;
    private List<Player> players;
    private int currentPlayer;
    private JLabel[] currentMoveState;
    private JLabel greenMoveState;
    private JLabel[] currentSelectionBoard;

    public Board(List<Player> players) {
        jLayeredPane = getLayeredPane();

        background = new JLabel(new ImageIcon("images\\board.png"));
        updateTileCount();
        recentScoreTracker = new JLabel[5][4];
        this.players = players;
        this.currentPlayer = -1;
        playerNames = new JLabel[players.size()];
        playerScore = new JLabel[players.size()];
        playerIcon = new JLabel[players.size()];

        board = new JLabel[BOARD_SIZE][BOARD_SIZE];
        doneButton = new JLabel(new ImageIcon("images\\play.png"));
        spacing = 55;
        initialX = 349;
        initialY = 5;
        currentMoveState = new JLabel[players.size()];
        populateActionButtons();

        background.setBounds(0, 0, 954, 700);
        jLayeredPane.add(background);

        initBoard();
        initCurrentMoveState();
        initPlayerDisplay();

        nextMove();
    }

    private void initCurrentMoveState() {
        for (int i = 0; i < currentMoveState.length; i++) {
            currentMoveState[i] = new JLabel(new ImageIcon(new ImageIcon("images\\redMark.png").getImage().getScaledInstance(
                    333, 67, Image.SCALE_SMOOTH)));
            currentMoveState[i].setBounds(10, 145 + 69 * i, 333, 67);
            jLayeredPane.add(currentMoveState[i]);
            jLayeredPane.moveToFront(currentMoveState[i]);
        }

        greenMoveState = new JLabel(new ImageIcon(new ImageIcon("images\\greenMark.png").getImage().getScaledInstance(
                333, 67, Image.SCALE_SMOOTH)));

        greenMoveState.setBounds(10, 145, 333, 67);
        jLayeredPane.add(greenMoveState);
        jLayeredPane.moveToFront(greenMoveState);
    }

    private void nextMove() {
        boolean allFalse = true;

        for (Player p : players) {
            if (p.hasChosenToContinue()) {
                allFalse = false;
            }
        }

        if (allFalse) {
            endScreen(); // DEBUG
        } else {
            if (currentPlayer == -1) {
            } else if (currentSelectionBoard != null) {
                for (Player p : players) {
                    for (JLabel j : p.getSelectionBoard()) {
                        if (j != null) jLayeredPane.remove(j);
                    }
                }
            }

            int nextPlayer = currentPlayer + 1;

            if (nextPlayer >= this.players.size()) {
                currentPlayer = 0;
            } else {
                currentPlayer++;
            }

            this.currentSelectionBoard = this.players.get(currentPlayer).getSelectionBoard();

            populateSelectionBoard();

            doneButton.setVisible(true);
        }
    }

    // recentScoreTracker
    public void updatePlayerScore(Player player, String string, int wordScore) {
        int freeSpace = -1;

        Font f = null;

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 16);
        } catch (Exception e) {
        }

        for (int i = 0; i < 5; i++) {
            if (recentScoreTracker[i][0] == null) {
                freeSpace = i;
                break;
            }
        }

        if (freeSpace == -1) {
            for (int i = 0; i < 4; i++) {
                for (int k = 0; k < 4; k++) {
                    if (recentScoreTracker[i][k] != null) {
                        recentScoreTracker[i][k].setVisible(false);
                    }
                    recentScoreTracker[i][k] = new JLabel(recentScoreTracker[i + 1][k].getText());
                }
            }

            for (int k = 0; k < 4; k++) {
                if (recentScoreTracker[4][k] != null) {
                    recentScoreTracker[4][k].setVisible(false);
                }
            }
            recentScoreTracker[4][0] = new JLabel(player.getName().toUpperCase());
            recentScoreTracker[4][1] = new JLabel(string);
            recentScoreTracker[4][2] = new JLabel(Integer.toString(wordScore));
            recentScoreTracker[4][3] = new JLabel(Integer.toString(player.getScore()));

        } else {
            for (int i = 0; i < freeSpace; i++) {
                for (int k = 0; k < 4; k++) {
                    //if (recentScoreTracker[i][k] != null) {
                    jLayeredPane.remove(recentScoreTracker[i][k]);
                    //}
                }
            }

            recentScoreTracker[freeSpace][0] = new JLabel(player.getName().toUpperCase());
            recentScoreTracker[freeSpace][1] = new JLabel(string);
            recentScoreTracker[freeSpace][2] = new JLabel(Integer.toString(wordScore));
            recentScoreTracker[freeSpace][3] = new JLabel(Integer.toString(player.getScore()));
        }

        int[] xPos = {16, 120, 225, 294};
        int y = 449;
        int spacing = 20;
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 4; k++) {
                if (recentScoreTracker[i][k] != null) {
                    recentScoreTracker[i][k].setFont(f);
                    recentScoreTracker[i][k].setVisible(true);
                    recentScoreTracker[i][k].setBounds(xPos[k], y, 100, 50);
                    jLayeredPane.add(recentScoreTracker[i][k]);
                    jLayeredPane.moveToFront(recentScoreTracker[i][k]);
                }

            }
            y += spacing;
        }
    }

    public static JLayeredPane getjLayeredPane() {
        return jLayeredPane;
    }

    private void populateSelectionBoard() {
        moves = new ArrayList<>();

        int currentX = 501;
        int yValue = 638;

        for (int i = 0; i < currentSelectionBoard.length; i++) {
            if (currentSelectionBoard[i] == null && !TILE_BAG.isEmpty()) {
                Tile tile = TILE_BAG.nextTile();
                tile.setVisible(true);

                tile.setBounds(currentX, yValue, 52, 52);

                currentSelectionBoard[i] = tile;

                tile.setName("" + i);
                addHoverListener(tile);
                addDraggableLayer(tile);

                jLayeredPane.add(tile);
                jLayeredPane.moveToFront(tile);
            } else {
                currentSelectionBoard[i].setVisible(true);
                currentSelectionBoard[i].setBounds(currentX, yValue, 52, 52);
            }

            if (jLayeredPane.getPosition(currentSelectionBoard[i]) < 0) {
                currentSelectionBoard[i].setVisible(true);
                currentSelectionBoard[i].setBounds(currentX, yValue, 52, 52);
                jLayeredPane.add(currentSelectionBoard[i]);
                jLayeredPane.moveToFront(currentSelectionBoard[i]);
            }

            currentX += spacing;
        }

        updateTileCount();
        updateScores();
    }

    private void repositionSelectionBoard() {
        int currentX = 501;
        int yValue = 638;

        for (int i = 0; i < currentSelectionBoard.length; i++) {
            JLabel curr = currentSelectionBoard[i];

            Point tileLocation = new Point(curr.getX(), curr.getY());
            int cellX = (tileLocation.x - initialX) / 38;
            int cellY = (tileLocation.y - initialY) / 38;

            if (cellX >= BOARD_SIZE || cellX < 0 || cellY >= BOARD_SIZE || cellY < 0) {
                Tile tile = (Tile) currentSelectionBoard[i];
                tile.setIcon(
                        new ImageIcon("images\\unmarked\\letter_" + Character.toUpperCase(tile.getLetter()) + ".jpg"));
                currentSelectionBoard[i].setBounds(currentX + spacing * Integer.parseInt(tile.getName()), yValue, 52, 52);
            }
        }
    }

    // GRAPHICS
    private void updateTileCount() {
        if (tileCount != null) {
            tileCount.setVisible(false);
        }

        tileCount = new JLabel(TILE_BAG.getSize() + "");

        Font f = null;

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 40);
        } catch (Exception e) {
        }

        tileCount.setFont(f);
        tileCount.setForeground(Color.WHITE);
        tileCount.setVisible(true);
        tileCount.setBounds(30, 590, 100, 100);
        jLayeredPane.add(tileCount);
        jLayeredPane.moveToFront(tileCount);
    }


    private void populateActionButtons() {
        shuffleButton = new JLabel(new ImageIcon(
                new ImageIcon("images\\shuffle.png").getImage().getScaledInstance(65, 63, Image.SCALE_SMOOTH)));
        shuffleButton.setBounds(423, 620, 65, 63);
        jLayeredPane.add(shuffleButton);

        swapButton = new JLabel(new ImageIcon(
                new ImageIcon("images\\swap.png").getImage().getScaledInstance(67, 65, Image.SCALE_SMOOTH)));
        swapButton.setBounds(352, 619, 67, 65);
        jLayeredPane.add(swapButton);

        shuffleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < 20; i++) {
                    int first = (int) (Math.random() * 7);
                    int second = (int) (Math.random() * 7);

                    JLabel temp = currentSelectionBoard[first];
                    currentSelectionBoard[first].setName("" + second);
                    currentSelectionBoard[second].setName("" + first);
                    currentSelectionBoard[first] = currentSelectionBoard[second];
                    currentSelectionBoard[second] = temp;
                }

                repositionSelectionBoard();
            }
        });

        swapButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Tile[] converted = new Tile[7];

                for (int i = 0; i < 7; i++) {
                    converted[i] = (Tile) currentSelectionBoard[i];
                }

                TILE_BAG.giveBack(converted);
                players.get(currentPlayer).setChosenToContinue(true);

                for (int i = 0; i < currentSelectionBoard.length; i++) {
                    currentSelectionBoard[i].setVisible(false);
                    jLayeredPane.remove(currentSelectionBoard[i]);
                    removeAllMouseListeners((Tile) currentSelectionBoard[i]);
                    currentSelectionBoard[i] = null; //ADDED
                }

                // ADDED
                for (Cell c : moves) {
                    for (int a = 0; a < BOARD_SIZE; a++) {
                        for (int b = 0; b < BOARD_SIZE; b++) {
                            if (board[a][b] == c) {
                                Cell cell = (Cell) board[a][b];
                                cell.removeTile();
                            }
                        }
                    }
                }

                nextMove();
                greenMoveState.setBounds(10, 145 + 69 * currentPlayer, 333, 67);

                repositionSelectionBoard();
            }
        });

        // doneButton.setVisible(true);
        doneButton.setVisible(false);
        doneButton.setBounds(885, 625, 62, 57);
        jLayeredPane.moveToFront(doneButton);
        jLayeredPane.add(doneButton);

        addClickListener(doneButton);
    }

    private void addClickListener(JLabel button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (moves.size() == 0) {
                    players.get(currentPlayer).setChosenToContinue(false);
                    nextMove();
                    greenMoveState.setBounds(10, 145 + 69 * currentPlayer, 333, 67);
                    populateSelectionBoard();
                } else {
                    Result r = WordChecker.validPlacement(moves, board);
                    if (r.isPossible()) {
                        players.get(currentPlayer).setChosenToContinue(true);
                        JOptionPane.showMessageDialog(null, "Valid Move");

                        for (Cell a : moves) {
                            a.getTile().setMarked();
                            removeAllMouseListeners(a.getTile());
                            removeTileFromSelectionBoard(a);
                        }

                        players.get(currentPlayer).addScore(r.getScore());
                        updatePlayerScore(players.get(currentPlayer), r.getWord(), r.getScore());

                        moves = new ArrayList<>();

                        nextMove();
                        greenMoveState.setBounds(10, 145 + 69 * currentPlayer, 333, 67);

                        populateSelectionBoard();
                    } else {
                        JOptionPane.showMessageDialog(null, "INVALID move");
                    }
                }
            }
        });
    }

    private void removeTileFromSelectionBoard(Cell cell) {
        for (int i = 0; i < currentSelectionBoard.length; i++) {
            if (currentSelectionBoard[i] == cell.getTile()) {
                currentSelectionBoard[i] = null;
                return;
            }
        }
    }

    private void removeAllMouseListeners(Tile tile) {
        MouseListener[] mouseListeners = tile.getMouseListeners();
        MouseMotionListener[] mouseMotionListeners = tile.getMouseMotionListeners();

        for (MouseListener mouseListener : mouseListeners) {
            tile.removeMouseListener(mouseListener);
        }

        for (MouseMotionListener mouseMotionListener : mouseMotionListeners) {
            tile.removeMouseMotionListener(mouseMotionListener);
        }
    }

    private void addHoverListener(Tile tile) {
        tile.addMouseListener(new MouseAdapter() {
            final int originalX = tile.getX();
            final int originalY = tile.getY();

            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getComponent().getX() == originalX && e.getComponent().getY() == originalY) {
                    e.getComponent().setLocation(e.getComponent().getX(), e.getComponent().getY() - 10);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getComponent().getY() == originalY - 10) {
                    e.getComponent().setLocation(e.getComponent().getX(), e.getComponent().getY() + 10);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                Point tileLocation = new Point(e.getComponent().getX(), e.getComponent().getY());
                int cellX = (tileLocation.x - initialX) / 38;
                int cellY = (tileLocation.y - initialY) / 38;

                for (int i = 0; i < BOARD_SIZE; i++) {
                    for (int j = 0; j < BOARD_SIZE; j++) {
                        Cell curr = (Cell) board[i][j];

                        if (curr.getTile() == tile) {
                            moves.remove(curr);
                            curr.removeTile();
                        }
                    }
                }

                try {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            doneButton.setVisible(false);

                            Result r = WordChecker.validPlacement(moves, board);
                            doneButton.setVisible(r.isPossible());
                        }
                    }).start();
                } catch (Exception x) {
                }

                if (cellX < BOARD_SIZE && cellX >= 0 && cellY < BOARD_SIZE && cellY >= 0) {
                    doneButton.setVisible(false);

                    Point targetLocation = new Point(cellX * 39 + initialX - 4, cellY * 39 + initialY + 13);

                    Cell current = (Cell) (board[cellY][cellX]);

                    if (current.isEmpty()) {
                        Tile tile = (Tile) e.getComponent();
                        tile.setIcon(new ImageIcon(
                                "images\\unmarked_small\\letter_" + Character.toUpperCase(tile.getLetter()) + ".jpg"));

                        e.getComponent().setLocation(targetLocation);
                        current.place(tile);
                        moves.add(current);

                        try {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Result r = WordChecker.validPlacement(moves, board);
                                    doneButton.setVisible(r.isPossible());
                                }
                            }).start();
                        } catch (Exception x) {
                        }
                    } else {
                        e.getComponent().setLocation(1200, 300);
                    }
                } else {
                    if (moves.size() == 0) {
                        doneButton.setVisible(true);
                    }

                    cellX = (tileLocation.x - 501) / 52;
                    int targetIndex = cellX;
                    int tileIndex = Integer.parseInt(tile.getName());

                    JLabel temp = currentSelectionBoard[tileIndex];

                    if (targetIndex >= 0 && targetIndex < 7 && tileIndex != targetIndex) {
                        JLabel pointer = currentSelectionBoard[targetIndex];
                        pointer.setName("" + tileIndex);

                        currentSelectionBoard[tileIndex] = pointer;
                        currentSelectionBoard[targetIndex] = temp;

                        tile.setName("" + targetIndex);
                    }
                }

                repositionSelectionBoard();
            }
        });
    }

    private void addDraggableLayer(Tile tile) {
        Point clickLocation = new Point(0, 0);

        tile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickLocation.setLocation(e.getX(), e.getY());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                jLayeredPane.moveToFront(e.getComponent());
            }
        });

        tile.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JComponent jc = (JComponent) e.getSource();
                jLayeredPane.moveToFront(jc);
                jc.setVisible(true);
                jc.setLocation(jc.getX() + e.getX() - clickLocation.x, jc.getY() + e.getY() - clickLocation.y);
            }
        });
    }

    private void endScreen() {

        JLabel jLabel = new JLabel();
        jLabel.setBackground(new Color(0, 0, 0, 192));
        jLabel.setVisible(true);
        jLabel.setOpaque(true);
        jLabel.setBounds(210, 150, 504, 400);
        jLayeredPane.add(jLabel);
        jLayeredPane.moveToFront(jLabel);

        Collections.sort(players);

        System.out.println(players);

        Font f = null;

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 40);
        } catch (Exception e) {
        }

        JLabel title = new JLabel("WHAT A GAME!");
        title.setFont(f);
        title.setForeground(Color.WHITE);
        title.setVisible(true);
        title.setBounds(335, 160, 400, 100);
        jLayeredPane.add(title);
        jLayeredPane.moveToFront(title);
        // Print out the score like the way it's done in the playerBoard method

        JLabel[] names = new JLabel[players.size()];
        JLabel[] pic = new JLabel[players.size()];
        JLabel[] score = new JLabel[players.size()];

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 20);
        } catch (Exception e) {
        }

        int yN = 250;
        int xN = 400;
        int xI = 300;
        int xS = 650;
        for (int i = 0; i < players.size(); i++) {
            //place
            JLabel place = new JLabel(Integer.toString(i + 1));
            place.setFont(f);
            place.setForeground(Color.WHITE);
            place.setVisible(true);
            place.setBounds(240, yN, 20, 50);
            jLayeredPane.add(place);
            jLayeredPane.moveToFront(place);

            // Name

            names[i] = new JLabel(players.get(i).getName().toUpperCase());
            names[i].setFont(f);
            names[i].setForeground(Color.WHITE);
            names[i].setVisible(true);
            names[i].setBounds(xN, yN, 150, 50);

            jLayeredPane.add(names[i]);
            jLayeredPane.moveToFront(names[i]);

            // icon
            JLabel image = players.get(i).getIcon();
            image.setVisible(true);
            image.setBounds(xI, yN, 50, 50);

            jLayeredPane.add(image);
            jLayeredPane.moveToFront(image);


            //score
            score[i] = new JLabel(Integer.toString(players.get(i).getScore()));
            score[i].setFont(f);
            score[i].setForeground(Color.WHITE);
            score[i].setVisible(true);
            score[i].setBounds(xS, yN, 50, 50);

            jLayeredPane.add(score[i]);
            jLayeredPane.moveToFront(score[i]);

            yN += spacing + 10;

        }
    }

    private void initPlayerDisplay() {
        int xN = 90;// xcomp for name
        int yN = 140;// ycomp for name
        int xI = 19;// xComp for icon
        int yI = 152;// ycomp for icon
        int spacing = 68;
        int size = 55;

        Font f = null;

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 20);
        } catch (Exception e) {
        }

        for (int i = 0; i < players.size(); i++) {
            // Name

            playerNames[i] = new JLabel(players.get(i).getName().toUpperCase());
            playerNames[i].setFont(f);
            playerNames[i].setVisible(true);
            playerNames[i].setBounds(xN, yN, 150, 50);

            jLayeredPane.add(playerNames[i]);
            jLayeredPane.moveToFront(playerNames[i]);

            // icon
            playerIcon[i] = players.get(i).getIcon();
            playerIcon[i].setVisible(true);
            playerIcon[i].setBounds(xI, yI, size, size);

            jLayeredPane.add(playerIcon[i]);
            jLayeredPane.moveToFront(playerIcon[i]);


            // Score
            updateScores();
            yN += spacing;
            yI += spacing;

        }
    }

    private void updateScores() {
        int x = 100;
        int y = 178;
        int spacing = 68;

        Font f = null;

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 24);
        } catch (Exception e) {
        }

        for (int i = 0; i < players.size(); i++) {
            if (playerScore[i] != null) {
                playerScore[i].setVisible(false);
            }
            playerScore[i] = new JLabel(Integer.toString(players.get(i).getScore()));
            playerScore[i].setVisible(true);
            playerScore[i].setFont(f);
            playerScore[i].setBounds(x, y, 100, 35);

            jLayeredPane.add(playerScore[i]);
            jLayeredPane.moveToFront(playerScore[i]);
            y += spacing;
        }
    }

    private void initBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Cell(i, j, EMPTY);
            }
        }

        initDoubleLetters();
        initTripleLetters();
        initDoubleWords();
        initTripleWords();

        board[7][7] = new Cell(7, 7, CENTER_PIECE);

        int currentX = initialX;
        int currentY = initialX;

        int maxRowSize = spacing * 15 + currentX;

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (currentX >= maxRowSize) {
                    currentX = initialX;
                }

                JLabel cell = board[i][j];
                cell.setVisible(true);
                cell.setBounds(currentX, currentY, 35, 35);

                jLayeredPane.add(cell);

                currentX += spacing;
            }

            currentY += spacing;
        }
    }

    private void initDoubleLetters() {
        Location[] locations = {new Location(0, 3), new Location(0, 11), new Location(2, 6), new Location(2, 8),
                new Location(3, 0), new Location(3, 7), new Location(3, 14), new Location(6, 2), new Location(6, 6),
                new Location(6, 8), new Location(6, 12)};

        for (Location p : locations) {
            int row = p.getRow();
            int col = p.getCol();

            board[row][col] = new Cell(row, col, DL);
            board[14 - row][col] = new Cell(14 - row, col, DL);
        }
    }

    private void initTripleLetters() {
        Location[] locations = {new Location(1, 5), new Location(1, 9), new Location(5, 1), new Location(5, 5),
                new Location(5, 9), new Location(5, 13)};

        for (Location p : locations) {
            int row = p.getRow();
            int col = p.getCol();

            board[row][col] = new Cell(row, col, TL);
            board[14 - row][col] = new Cell(14 - row, col, TL);
        }
    }

    private void initDoubleWords() {
        Location[] locations = new Location[8];

        for (int i = 0; i < 4; i++) {
            locations[i] = new Location(i + 1, i + 1);
            locations[i + 4] = new Location(i + 1, 13 - i);
        }

        for (Location p : locations) {
            int row = p.getRow();
            int col = p.getCol();

            board[row][col] = new Cell(row, col, DW);
            board[14 - row][col] = new Cell(14 - row, col, DW);
        }
    }

    private void initTripleWords() {
        Location[] locations = {new Location(0, 0), new Location(0, 7), new Location(0, 14), new Location(7, 0),
                new Location(7, 14)};

        for (Location p : locations) {
            int row = p.getRow();
            int col = p.getCol();

            board[row][col] = new Cell(row, col, TW);
            board[14 - row][col] = new Cell(14 - row, col, TW);
        }
    }
}