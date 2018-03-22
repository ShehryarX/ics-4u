package entities;

import interfaces.Config;

import java.awt.*;

public abstract class Organism implements Comparable<Organism>, Config {

    protected int dirX; // x direction of organism
    protected int dirY; // y direction of organism
    /**
     * number of days the object hasn't eaten.
     */
    protected int myDaysEaten;
    private Point myLocation; // location of the organism
    /**
     * object's state: whether zombie or human.
     */
    private boolean isInfected;
    /**
     * object's type.
     */
    private char myType;
    /**
     * whether is dead or not
     */
    private boolean isDead;

    /**
     * whether is immune to zombies are not
     */
    private boolean isImmune;

    /**
     * whether is a female or male
     */
    private char myGender;

    /**
     * organism constructor
     *
     * @param type   is a character
     * @param infected is if it has been infected
     * @param days is how long the organism has been alive or how long since
     *                    it has eaten
     * @param gender is the gender of the organism
     * @param location  is the location of the object
     */
    public Organism(final char type, final boolean infected, final int days, final char gender,
                    final Point location) {
        myType = type;
        isInfected = infected;
        myDaysEaten = days;
        myLocation = location;
        isDead = false;
        isImmune = false;
        myGender = gender;
    }

    /**
     * species accessor
     *
     * @return species
     */
    public char getMyType() {
        return myType;
    }

    /**
     * location accessor method
     *
     * @return myLocation
     */
    public Point getMyLocation() {
        return myLocation;
    }

    /**
     * sets the new location of the organism
     *
     * @param location - new locatio point
     */
    public void setMyLocation(Point location) {
        myLocation = location;
    }

    /**
     * days eaten method tells how many days the organism has been alive since last
     * meal
     *
     * @return number of days since last eaten
     */
    public int getMyDaysEaten() {
        return myDaysEaten;
    }

    /**
     * mutator method: sets days since eaten
     *
     * @param daysSick
     */
    public void setMyDaysEaten(int daysSick) {
        myDaysEaten = daysSick;
    }

    /**
     * mutator method: increments days since eaten
     */
    public void incrementDaysEaten() {
        myDaysEaten++;
    }

    /**
     * moves the organism
     */
    public void move() {
        // gets previous location point
        int preX = this.getMyLocation().x;
        int preY = this.getMyLocation().y;

        // changes the previous point based on direction of the organism
        // determined in the subclasses
        preX += (dirX * GENERATOR.nextInt(9) + 1);
        preY += (dirY * GENERATOR.nextInt(9) + 1);

        // keeps organism within bounds
        if (preX > PANEL_WIDTH) {
            dirX = -1;
            preX = PANEL_WIDTH;
        }
        if (preY > PANEL_HEIGHT) {
            dirY = -1;
            preY = PANEL_HEIGHT;
        }
        if (preX < 0) {
            dirX = 1;
            preX = 0;
        }
        if (preY < 0) {
            dirY = 1;
            preY = 0;
        }

        // sets the new location of the organism
        Point newLocation = new Point(preX, preY);
        this.setMyLocation(newLocation);
    }

    /**
     * Compares two world objects based on their health status.
     *
     * @param obj != null
     * @return is 1 if self healthy while other sick, 0 if both equal, -1 if self
     * sick while other healthy
     */
    @Override
    public int compareTo(Organism obj) {
        if (obj == null) {
            System.out.println("Object is null!");
            throw new IllegalArgumentException();
        } else if (!this.isInfected() && obj.isInfected()) {
            return 1;
        } else if (this.isInfected() && obj.isInfected()) {
            return 0;
        } else if (!this.isInfected() && obj.isInfected()) {
            return 0;
        } else {
            return -1;
        }

    }

    /**
     * return the state of the animal
     *
     * @return true if dead
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * kills the organism
     */
    public void killOrganism() {
        isDead = true;
    }

    /**
     * heals the organism
     */
    public void healOrganism() {
        isInfected = false;
        myDaysEaten = 0;
        isDead = false;
        isImmune = true;

    }

    /**
     * gets the gender of the organism
     *
     * @return - f if female
     * - m if male
     */
    public char getMyGender() {
        return myGender;
    }

    /**
     * returns if the objects are within 5 units away from eachother
     *
     * @param obj - the other organism
     * @return - true if within range
     */
    public boolean samePosition(Organism obj) {
        int x = this.getMyLocation().x;
        int otherx = (obj.getMyLocation().x);
        int y = this.getMyLocation().y;
        int othery = (obj.getMyLocation().y);
        if (Math.sqrt(Math.pow(x - otherx, 2) + Math.pow(y - othery, 2)) < 5) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns if the object is near the point
     *
     * @param p point
     * @return true if within range
     */
    public boolean samePosition(Point p) {
        int x = this.getMyLocation().x;
        int otherx = (p.x);
        int y = this.getMyLocation().y;
        int othery = (p.y);
        if (Math.sqrt(Math.pow(x - otherx, 2) + Math.pow(y - othery, 2)) < 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * state of the organism
     *
     * @return true is infected
     */
    public boolean isInfected() {
        if (isInfected && !isDead) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * sets the organism as infected true or or not infected
     *
     * @param s - boolean for is infected or not
     */
    public void setInfected(boolean s) {
        isInfected = s;
    }

    /**
     * health of the organism
     *
     * @return true if healthy
     */
    public boolean isHealthy() {

        if (!isDead && !isImmune && !isInfected) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * immunity of the organism
     *
     * @return true if immune
     */
    public boolean isImmune() {

        if (!isDead && isImmune && !isInfected) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * determines if the organisms are different genders
     *
     * @param secondaryOrganism
     * @return true if different genders
     */
    public boolean oppositeSex(Organism secondaryOrganism) {
        if (this.getMyGender() != secondaryOrganism.getMyGender()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * doctor status
     *
     * @return true if is a doctor
     */
    public boolean isDoctor() {
        if ("Doctor".equalsIgnoreCase(this.getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * status
     *
     * @return true if is a human
     */
    public boolean isHuman() {
        if ("Human".equalsIgnoreCase(this.getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * status
     *
     * @return true if is a zombie
     */
    public boolean isAZombie() {
        if ("Zombie".equalsIgnoreCase(this.getClass().getSimpleName())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return days since eaten
     */
    public int getDaysSinceEaten() {
        return myDaysEaten;
    }
}
