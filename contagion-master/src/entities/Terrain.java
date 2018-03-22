package entities;

import interfaces.Config;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Terrain implements Config {
    /**
     * randomly generated location points
     */
    private List<Point> myPointList;
    /**
     * list of organisms
     */
    private List<Organism> myOrganismList;

    /**
     * number of days since day 0
     */
    private int myDayCounter;

    /**
     * count for number of humands infected
     */
    private int myHumansInfected;

    /**
     * count for number of immune humans
     */
    private int immune;

    /**
     * population of zombies
     */
    private int myZombies;

    /**
     * number of healthy humans
     */
    private int myHumansHealthy;

    /**
     * number of people to create
     */
    private int myNumberOfPeopleToCreate;

    /**
     * number of zombies to create
     */
    private int myNumberOfZombiesToCreate;

    /**
     * number of doctors to create
     */
    private int myNumberOfDoctorsToCreate;

    /**
     * life longevity of zombie
     */
    private int zombieLife = ZOMBIE_LIFESPAN;
    /**
     * life longevity of human
     */
    private int humanLife = HUMAN_LIFESPAN;
    /**
     * infectio rate
     */
    private int infectionRate = INFECTION_CHANCE;

    /**
     * generates a new terrain
     * resets all the numbers for population and days to zero
     * generates points for the new
     */
    public Terrain() {

        // creates a new organism list
        myOrganismList = new CopyOnWriteArrayList<Organism>();

        myPointList = new ArrayList<Point>();

        // sets all values to zeroes
        myNumberOfPeopleToCreate = 0;
        myNumberOfZombiesToCreate = 0;
        myNumberOfDoctorsToCreate = 0;
        myDayCounter = 0;
        myHumansInfected = 0;
        myHumansHealthy = 0;
        myZombies = 0;
        immune = 0;

        // generates random points
        generatePoints();
    }

    /**
     * resets the data for the config
     *
     * @param s - which value to alter
     * @param k - what to reset it to
     */
    public void updateInfo(char s, int k) {
        // resets the human life duration
        if (s == 'h') {
            humanLife = k;
        }
        // resets the zombie life duration
        else if (s == 'z') {
            zombieLife = k;
        }
        // resets the infection rate
        else if (s == 'i') {
            infectionRate = k;
        }
    }

    /**
     * kills organisms at 40% death rate
     */
    public void killOrganism() {
        for (int i = 0; i < myOrganismList.size(); i++) {
            if (GENERATOR.nextInt(100) < 40) {
                myOrganismList.get(i).setMyDaysEaten(Integer.MAX_VALUE);
                ;

            }
        }
    }

    /**
     * adds new organism
     *
     * @param s - species
     * @param p - point
     */
    public void addOrganism(char s, Point p) {
        Organism e = null;
        // adds a doctor
        if (s == 'd') {
            e = new Doctor(false, 0, p);
        }
        // adds a human
        else if (s == 'h') {
            char gender;
            gender = (GENERATOR.nextInt(2) == 0) ? 'M' : 'F';
            e = new Human(false, 0, gender, p);
        }
        // adds a zombie
        else if (s == 'z') {
            e = new Zombie(false, 0, p);
        }

        if (e != null) {
            myOrganismList.add(e);
        }
    }

    /**
     * creates a list of humans and adds to the organism chart
     */
    public void createHumans() {

        for (int i = 0; i < myNumberOfPeopleToCreate; i++) {
            char gender;

            // randomly generates the gender
            gender = (i % 2 == 0) ? 'M' : 'F';

            // chooses a point from the point list
            int index = GENERATOR.nextInt(myPointList.size());
            Point point = myPointList.get(index);
            myPointList.remove(index);

            // creates new human
            Human human = new Human(false, 0, gender, point);
            // adds to stat count
            myHumansHealthy++;
            myOrganismList.add(human);

        }

    }

    /**
     * creates zombie
     */
    public void createZombies() {

        for (int i = 0; i < myNumberOfZombiesToCreate; i++) {
            int index = GENERATOR.nextInt(myPointList.size());
            Point point = myPointList.get(index);
            myPointList.remove(index);
            Zombie zombie = new Zombie(false, 0, point);
            zombie.setInfected(true);
            myZombies++;
            myOrganismList.add(zombie);
        }

    }

    /**
     * creates doctors
     */
    public void createDoctors() {
        for (int i = 0; i < myNumberOfDoctorsToCreate; i++) {
            int index = GENERATOR.nextInt(myPointList.size());
            Point point = myPointList.get(index);
            myPointList.remove(index);
            Doctor doctor = new Doctor(false, 0, point);
            doctor.healOrganism();
            myOrganismList.add(doctor);

            immune++;
        }
    }

    /**
     * logic--> makes the organisms move : doctors heal humans and kill zombies,
     * zombies infect humans, humans reproduce
     */
    public void performGameMechs() {
        for (Organism mainOrganism : myOrganismList) {

            mainOrganism.move();

            for (Organism secondaryOrganism : myOrganismList) {

                infect(mainOrganism, secondaryOrganism); // between infected and healthy
                healWithDoctors(mainOrganism, secondaryOrganism); // heal only humans
                breed(mainOrganism, secondaryOrganism);
                killWithDoctors(mainOrganism, secondaryOrganism); // kills the zombie

            }

            kill(mainOrganism);
        }
        recount();

    }

    /**
     * recounts the stats
     */
    private void recount() {
        resetStats();

        for (Organism mainOrganism : myOrganismList) {
            if (mainOrganism.isAZombie()) {
                myZombies++;
            } else if (mainOrganism.isImmune()) {
                immune++;
            } else if (mainOrganism.isInfected()) {
                myHumansInfected++;
            } else if (mainOrganism.isHuman() && mainOrganism.isHealthy()) {
                myHumansHealthy++;
            }
        }
    }

    /**
     * where humans breed
     *
     * @param mainOrganism      - first organism
     * @param secondaryOrganism - second organism to breed with will only breed if
     *                          opposite gender
     */
    private void breed(Organism mainOrganism, Organism secondaryOrganism) {
        if (mainOrganism.samePosition(secondaryOrganism) && mainOrganism.isHuman()
                && secondaryOrganism.isHuman()) {
            if (mainOrganism.isHealthy() && mainOrganism.isHealthy()
                    && mainOrganism.oppositeSex(secondaryOrganism)) {

                Human mainHuman = (Human) mainOrganism;
                Human secondHuman = (Human) secondaryOrganism;

                if (!mainHuman.getNewBorn() && !secondHuman.getNewBorn()) {

                    if (BREEDING_CHANCE >= GENERATOR.nextInt(99) + 1) {

                        char gender = (myPointList.size() % 2 == 0) ? 'M' : 'F';
                        Human human = new Human('H', false, 0, gender,
                                mainOrganism.getMyLocation(), true);
                        myOrganismList.add(human);
                    }
                }
            }
        }
    }

    /**
     * kills if past life time
     *
     * @param OrganismToKill
     */
    private void kill(Organism OrganismToKill) {

        if (OrganismToKill.isAZombie()) {
            if (OrganismToKill.getDaysSinceEaten() > zombieLife) {
                OrganismToKill.killOrganism();
                Point point = OrganismToKill.getMyLocation();
                myPointList.add(point);
                myOrganismList.remove(OrganismToKill);

            }
        } else if (OrganismToKill.isHuman()) {
            if (OrganismToKill.getDaysSinceEaten() > humanLife) {
                OrganismToKill.killOrganism();
                Point point = OrganismToKill.getMyLocation();
                myPointList.add(point);
                myOrganismList.remove(OrganismToKill);

            }
        }
    }

    /**
     * resets the stats
     */
    public void reset() {
        myPointList.clear();
        myOrganismList.clear();
        generatePoints();
    }

    /**
     * randomly generate points
     */
    private void generatePoints() {
        int width = PANEL_WIDTH + 10;
        int height = PANEL_HEIGHT + 10;

        for (int i = 0; i < width; i += HUMAN_DIM) {
            for (int j = 0; j < height; j += HUMAN_DIM) {
                Point point = new Point(i, j);
                myPointList.add(point);
            }
        }
    }

    /**
     * @return number of myNumberOfPeopleToCreate
     */
    public int getMyNumberOfPeopleToCreate() {
        return myNumberOfPeopleToCreate;
    }

    /**
     * sets the number of population
     *
     * @param myNumberOfPeopleToCreate
     */
    public void setMyNumberOfPeopleToCreate(int myNumberOfPeopleToCreate) {
        this.myNumberOfPeopleToCreate = myNumberOfPeopleToCreate;
    }

    /**
     * @return myNumberOfZombiesToCreate
     */
    public int getMyNumberOfZombiesToCreate() {
        return myNumberOfZombiesToCreate;
    }

    /**
     * sets the number of population
     *
     * @param myNumberOfZombiesToCreate
     */
    public void setMyNumberOfZombiesToCreate(int myNumberOfZombiesToCreate) {
        this.myNumberOfZombiesToCreate = myNumberOfZombiesToCreate;
    }

    /**
     * gets the organism list
     *
     * @return myOrganismList
     */

    public List<Organism> getMyOrganismList() {
        return myOrganismList;
    }

    /**
     * @returnmyDayCounter
     */
    public int getMyDayCounter() {
        return myDayCounter;
    }

    /**
     * adds to days
     */
    public void incrementMyDayCounter() {
        myDayCounter++;
    }

    /**
     * @return number of immune people
     */
    public int getImmune() {
        return immune;
    }

    /**
     * @return number of myHumansHealthy
     */
    public int getMyHumansHealthy() {
        return myHumansHealthy;
    }

    /**
     * @return number of myZombies
     */
    public int getMyZombies() {
        return myZombies;
    }

    /**
     * @return number of organisms that are zombies
     */
    public int getEntitiesInfected() {
        return myZombies + myHumansInfected;
    }

    /**
     * resets the count
     */
    public void resetStats() {
        myHumansHealthy = 0;
        myZombies = 0;
        immune = 0;
        myHumansInfected = 0;
    }

    /**
     * infected is the roles are correctly assigned
     *
     * @param OrganismToInfect      - if a zombie or infected human
     * @param OrganismWithInfection - is in a healthy human that is not immune
     */
    private void infect(Organism OrganismToInfect, Organism OrganismWithInfection) {

        if (OrganismToInfect.samePosition(OrganismWithInfection)
                && (OrganismWithInfection.isInfected() || OrganismWithInfection.isAZombie())
                && OrganismToInfect.isHuman() && OrganismToInfect.isHealthy())
            if (infectionRate >= GENERATOR.nextInt(100) + 1) {

                OrganismToInfect.setInfected(true); // ie. make the humans a zombie
                OrganismToInfect.setMyDaysEaten(0); // ie. makes the humans basically a zombie
                OrganismWithInfection.setMyDaysEaten(0); // ie. shows zombies have eaten

            }

    }

    /**
     * heals if the roles are correctly assigned
     *
     * @param doctor  - if if a doctor
     * @param patient - if is an infected human
     */
    public void healWithDoctors(Organism doctor, Organism patient) {

        if (doctor.samePosition(patient) && doctor.isDoctor() && patient.isHuman()
                && patient.isInfected()) {

            if (HEALING_CHANCE >= GENERATOR.nextInt(100) + 1) {
                patient.healOrganism();
                patient.setMyDaysEaten(0);

            }
        }

    }

    /**
     * kills the second organism
     *
     * @param doctor - primary organism
     * @param zombie - secondary organism
     *               will kill if the roels are correctly assigned
     */
    public void killWithDoctors(Organism doctor, Organism zombie) {
        if (doctor.samePosition(zombie) && doctor.isDoctor() && zombie.isAZombie()) {
            if (KILLING_CHANCE >= GENERATOR.nextInt(100) + 1) {
                zombie.setMyDaysEaten(zombieLife + 1);// kills the zombie
            }
        }
    }

    /**
     * @return number of myNumberOfDoctorsToCreate
     */
    public int getMyNumberOfDoctorsToCreate() {
        return myNumberOfDoctorsToCreate;
    }

    /**
     * sets the number of population
     *
     * @param myDoctorsHealthy
     */
    public void setMyNumberOfDoctorsToCreate(int myDoctorsHealthy) {
        this.myNumberOfDoctorsToCreate = myDoctorsHealthy;
    }

}
