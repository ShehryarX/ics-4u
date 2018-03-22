package entities;

import interfaces.Config;

import java.awt.*;

public class Doctor extends Organism implements Config {

    /**
     * Doctor constructor
     * - organism heals the humans that have been infected by zombies and tries to kill the zombies in the simulation.
     * - immune to zombie infection and will not fall ill
     *
     * @param infected - whether is Infected or not
     * @param dayNum   - number of days alive
     * @param location - location of the doctor
     */
    public Doctor(boolean infected, int dayNum, Point location) {
        super('D', infected, dayNum, 'N', location);
    }

    /**
     * doctor covers a given area of the terrain
     * the position is regenerated within a 10 pixel distance
     */
    public void move() {
        //randomly generates coordinates
        //is around a 10 unit radius of previous point
        int x = GENERATOR.nextInt(3);
        int y = GENERATOR.nextInt(3);

        if (x == 2)
            x = -1;

        if (y == 2)
            y = -1;

        //updates new location
        int preX = this.getMyLocation().x;
        int preY = this.getMyLocation().y;

        preX += (x * 10);
        preY += (y * 10);


        //sets the doctor within the boundaries
        if (preX > PANEL_WIDTH)
            preX = 0;

        if (preY > PANEL_HEIGHT)
            preY = 0;
        //if the doctors position was out of bounds
        //allows for doctors to wrap around
        if (preX < 0)
            preX = PANEL_WIDTH - HUMAN_DIM;

        if (preY < 0)
            preY = PANEL_HEIGHT - HUMAN_DIM;

        //updates to the new points
        Point newLocation = new Point(preX, preY);
        this.setMyLocation(newLocation);

    }


    /**
     * toString method
     * - returns the name of the species, which is "doctor"
     */
    @Override
    public String toString() {
        return "DOCTOR";
    }

}
