package interfaces;

import java.awt.Color;
import java.util.Random;

public interface Config {
    // panel dimensions
    int PANEL_WIDTH = 1000;
    int PANEL_HEIGHT = 510;
    int HUMAN_DIM = 10;

    // organism information
    int ZOMBIE_LIFESPAN = 100;
    int HUMAN_LIFESPAN = 100;
    int BABY_LENGTH = 30;
    int BREEDING_CHANCE = 25;
    int HEALING_CHANCE = 50;
    int KILLING_CHANCE = 25;
    int INFECTION_CHANCE = 100;
    Color ZOMBIE_COLOUR = new Color(20,200,10);
    Color IMMUNE_COLOUR = Color.RED;

    // random number generator
    Random GENERATOR = new Random(System.currentTimeMillis());

    // background colours
    Color BACKGROUNDCOLOR_0 = new Color(200, 234, 217);
    Color BACKGROUNDCOLOR_1 = new Color(180, 228, 205);
    Color BACKGROUNDCOLOR_2 = new Color(162, 221, 192);
    Color BACKGROUNDCOLOR_3 = new Color(144, 213, 179);
    Color BACKGROUNDCOLOR_4 = new Color(63, 166, 118);

    // pane colours
    Color PANELCOLORONE = new Color(153, 230, 153);
    Color PANELCOLORTWO = new Color(173, 235, 173);

    // buttons
    Color HUMANBUTTON = new Color(51, 102, 255);
    Color DOCTORBUTTON = new Color(255, 133, 102);
    Color ZOMBIEBUTTON = new Color(128, 128, 0);
    Color KILLBUTTON = new Color(163, 194, 194);

    // dimension specification of buttons
    int BWIDTH = 25;
    int BLENGTH = 250;
    int NLENGTH = 70;
    int NWIDTH = 25;

    // organism value customization
    String[] HUMANARRAY = {
            "0","30","50","80","100", "200","250","300",
            "400","500", "600","700","750",
            "800"};
    String[] ZOMBIEARRAY = {
            "0","30","50","80","100", "200","250","300",
            "400","500", "600","700","750","800"};
    String[] DOCTORARRAY = {
            "0","25","50","75","100"};
}