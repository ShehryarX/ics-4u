package gui;

import entities.Human;
import entities.Organism;
import entities.Terrain;
import interfaces.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.util.List;

public class TerrainPanel extends JPanel implements Config {

    /**
     * terrain
     */
    private Terrain myTerrain;
    /**
     * list of organisms
     */
    private List<Organism> OrganismList;

    private boolean grid;

    /**
     * constructor
     *
     * @param terrainData - gets the terrain
     */
    public TerrainPanel(Terrain terrainData) {

        super();
        myTerrain = terrainData;
        OrganismList = myTerrain.getMyOrganismList();
        setBackground(Color.WHITE);
        Dimension mDim = new Dimension(PANEL_WIDTH, PANEL_HEIGHT);
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
        setMaximumSize(mDim);
        setMinimumSize(mDim);
        setPreferredSize(mDim);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        grid = false;
    }

    /**
     * resets the terrain to empty
     */
    public void reset() {
        myTerrain.reset();
        myTerrain.resetStats();
        updateUI();
    }

    /**
     * paint with graphics
     */
    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (grid) {
            drawGrid(g2);
        }

        for (Organism item : OrganismList) {

            drawHumans(g2, item);
            drawBabies(g2, item);
            drawDoctors(g2, item);
            drawZombies(g2, item);

        }
    }

    /**
     * draws the grid and boundaries
     *
     * @param g2
     */
    private void drawGrid(Graphics2D g2) {

        for (int i = 0; i < this.getWidth(); i += 10) {

            g2.setColor(Color.gray);
            g2.drawLine(i, 0, i, this.getHeight());
        }
        for (int i = 0; i < this.getHeight(); i += 10) {
            g2.setColor(Color.gray);
            g2.drawLine(0, i, this.getWidth(), i);
        }

    }

    /**
     * draws babies as little ellipse
     *
     * @param g2
     * @param item - ogranism
     */
    private void drawBabies(Graphics2D g2, Organism item) {
        if (item.isHuman()) {
            Human human = (Human) item; // cast to human
            if (human.getNewBorn() && item.isHuman() && item.isHealthy()) {
                // draw organism
                Ellipse2D ellipse =
                        new Ellipse2D.Double(item.getMyLocation().x, item.getMyLocation().y,
                                HUMAN_DIM - 5, HUMAN_DIM - 5);
                g2.setColor(Color.PINK);
                g2.fill(ellipse);
                g2.draw(ellipse);
            } else if (human.getNewBorn() && item.isHuman() && item.isImmune()) {
                // draw organism
                Ellipse2D ellipse =
                        new Ellipse2D.Double(item.getMyLocation().x, item.getMyLocation().y,
                                HUMAN_DIM - 5, HUMAN_DIM - 5);

                g2.setColor(IMMUNE_COLOUR);
                g2.fill(ellipse);
                g2.draw(ellipse);
            } else if (human.getNewBorn() && item.isInfected()) {
                // draw organism
                Ellipse2D ellipse =
                        new Ellipse2D.Double(item.getMyLocation().x, item.getMyLocation().y,
                                HUMAN_DIM - 5, HUMAN_DIM - 5);
                g2.setColor(ZOMBIE_COLOUR);
                g2.fill(ellipse);
                g2.draw(ellipse);
            }
        }

    }

    /**
     * draws humans, will vary based on gender and health state
     *
     * @param g2
     * @param item
     */
    private void drawHumans(final Graphics2D g2, Organism item) {
        Human human = null; // assume null

        // try to cast to Human
        if (item.getClass().getSimpleName().equalsIgnoreCase("Human")) {
            human = (Human) item;
        }

        // if it is a human and alive
        if (item.toString().equalsIgnoreCase("HUMAN") && !item.isDead()
                && !human.getNewBorn()) {
            // get the dimensions
            int x = item.getMyLocation().x;
            int y = item.getMyLocation().y;

            // draw accordingly
            Ellipse2D ellipse = new Ellipse2D.Double(x, y, HUMAN_DIM, HUMAN_DIM * 1.5);

            if (item.isInfected()) {
                // conversion to zombie
                Image Zombie;
                try {
                    Zombie = ImageIO.read(new File("res/zombie.png"));
                    g2.drawImage(Zombie, x, y, null);
                } catch (Exception e) {

                }
            } else {
                // conversion to woman
                if (item.getMyGender() == 'F' && item.isHuman() && item.isHealthy()) {
                    Image WOMAN;
                    try {
                        WOMAN = ImageIO.read(new File("res/woman.png"));
                        g2.drawImage(WOMAN, x, y, null);
                    } catch (Exception e) {

                    }
                } else if (item.isImmune()) {
                    g2.setColor(IMMUNE_COLOUR); // is immune
                    g2.fill(ellipse);
                } else {
                    // conversion to man
                    Image MAN;
                    try {
                        MAN = ImageIO.read(new File("res/man.png"));
                        g2.drawImage(MAN, x, y, null);
                    } catch (Exception e) {

                    }
                }

            }
        }
    }

    /**
     * draws zombies
     *
     * @param g2
     * @param item
     */
    private void drawZombies(final Graphics2D g2, Organism item) {

        if (item.toString().equalsIgnoreCase("ZOMBIE") && !item.isDead()) {
            int x = item.getMyLocation().x;
            int y = item.getMyLocation().y;
            Image Zombie;
            try {
                Zombie = ImageIO.read(new File("res/zombie.png"));
                g2.drawImage(Zombie, x, y, null);
            } catch (Exception e) {

            }

        }
    }

    /**
     * Draw a doctor
     *
     * @param g2
     * @param item
     */
    private void drawDoctors(final Graphics2D g2, Organism item) {
        // if it's a doctor
        if (item.toString().equalsIgnoreCase("DOCTOR") && !item.isDead()) {
            int x = item.getMyLocation().x;
            int y = item.getMyLocation().y;
            Image doctor;

            // place a doctor image
            try {
                doctor = ImageIO.read(new File("res/doctor.png"));
                g2.drawImage(doctor, x, y, null);
            } catch (Exception e) {

            }
        }
    }
}
