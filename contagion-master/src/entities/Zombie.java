package entities;

import interfaces.Config;

import java.awt.*;

public class Zombie extends Organism implements Config {

    /**
     * constructor
     *
     * @param infected - if infected
     * @param dayNum   - days since eaten
     * @param location - location of zombie
     */
    public Zombie(boolean infected, int dayNum, Point location) {
        super('Z', infected, dayNum, 'N', location);
        generateDir(); // generates the direction
    }

    /**
     * generates the direction
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
     * moves the zombie
     */
    public void move() {
        super.move();
        /*
         * increments days since eaten
         * 
         */
        if (!this.isDead()) {
            incrementDaysEaten();
        }
    }

    /**
     * returns string of the organism
     */
    @Override
    public String toString() {
        return "ZOMBIE";
    }

}
