package entities;

import interfaces.Config;

import java.awt.*;

public class Human extends Organism implements Config {


    private boolean isNewBorn;  //determines whether or not is a baby
    private int daysAsABaby;    //determines days as a baby

    /**
     * constructor
     *
     * @param infected - boolean if is infected
     * @param dayNum   - days alive
     * @param gender   - gender of the human
     * @param location - location of the human
     */
    public Human(boolean infected, int dayNum, char gender, Point location) {
        super('H', infected, dayNum, gender, location);
        daysAsABaby = 0;
        //generated direction to go
        generateDir();
    }

    /**
     * @param type     - species
     * @param infected - boolean if is infected
     * @param dayNum   - days alive
     * @param gender   - gender of the human
     * @param location - location of the human
     * @param newborn  - determine new born or not
     */
    public Human(char type, boolean infected, int dayNum, char gender, Point location,
                 boolean newborn) {
        super(type, infected, dayNum, gender, location);
        isNewBorn = newborn;
        daysAsABaby = 0;
        //generates direction to go
        generateDir();
    }

    /**
     * generates a direction to go
     */
    private void generateDir() {
        if (GENERATOR.nextInt(2) == 0) {
            dirX = 1;
        } else {
            dirX = -1;
        }
        if (GENERATOR.nextInt(2) == 0) {
            dirY = 1;
        } else {
            dirY = -1;
        }
    }

    /**
     * heals Organism
     * makes it no longer infected
     */
    public void healOrganism() {
        super.healOrganism();

    }

    /**
     * allows the human to move around the terrain
     * tries to avoid the zombies and goes to doctors
     */
    @Override
    public void move() {
        super.move();

        if (!this.isDead()) {    //is an undead/zombie
            incrementDaysEaten();
        }

        if (daysAsABaby > BABY_LENGTH) {
            isNewBorn = false;
        }

        if (isNewBorn) {
            daysAsABaby++;
        }

    }

    @Override
    public String toString() {
        return "HUMAN";
    }

    /**
     * getNewBorn
     *
     * @return whether or not is a new born baby
     */
    public boolean getNewBorn() {
        return isNewBorn;
    }

    /**
     * sets the human as a baby or not a baby
     *
     * @param newBorn - boolean for is is or is not a baby
     */
    public void setNewBorn(boolean newBorn) {
        this.isNewBorn = newBorn;
    }

}
