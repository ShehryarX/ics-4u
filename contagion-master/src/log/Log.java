package log;

import entities.Doctor;
import entities.Entity;
import entities.Human;
import entities.Zombie;
import gui.TerrainPanel;
import interfaces.Config;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Log implements Config {
    /*
    - saves a text file using world
     */
    public static void save() {
        // get the world array
        Entity[][] world = TerrainPanel.getRecentWorld();

        // Get the file name to save
        String fileName = JOptionPane.showInputDialog("File Name:");

        // verify input
        if (fileName != null && !fileName.equals("")) {
            // add a .txt if needed
            if (!fileName.endsWith(".txt")) fileName += ".txt";

            // create PrintWriter
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(fileName); // get file stream
            } catch (FileNotFoundException fnfe) { // otherwise leave method
                System.out.println("File not found!");
                fnfe.printStackTrace();
                return;
            }

            // write to file
            // print file dimensions
            pw.println(world.length);
            pw.println(world[0].length);

            // print world
            for (int row = 0; row < world.length; row++) {
                for (int col = 0; col < world[row].length; col++) {
                    Entity e = world[row][col]; // current entity

                    if (e != null) pw.print(e); // print regular object
                    else pw.print("{null}"); // print empty object

                    if (col != world[row].length - 1) pw.append("-"); // add comma for next object
                    // also ensures that the last object doesn't have a trailing comma
                }
                pw.println(); // go to next line
            }

            // close output stream
            pw.close();

            // print success message
            System.out.println("Successfully saved to file " + fileName + ".");
        }
    }

    /*
    - Loads text file
    - Returns new Entity[][] world
     */
    public static void load() {
        // File name
        String fileName = "first.txt";

        // ensure .txt ending
        if(!fileName.endsWith(".txt")) fileName += ".txt";

        // To be populated
        Entity[][] world;

        // attempt to access stream to read file
        Scanner sc;

        try {
            sc = new Scanner(new File(fileName)); // get file stream
        } catch (FileNotFoundException fnfe) {// otherwise leave method
            System.out.println("File not found!");
            fnfe.printStackTrace();
            return;
        }

        // read dimensions first
        world = new Entity[sc.nextInt()][sc.nextInt()];

        // now read each adjacent pair of brackets separated by commas and new lines
        for (int row = 0; row < world.length; row++) {
            String[] raw = sc.nextLine().split("-"); // get next line

            // ensure it's valid
            while(raw.length < 2) {
                raw = sc.nextLine().split("-"); // get next line
            }

            for (int col = 0; col < world[0].length; col++) {
                if (raw[col].contains("null")) continue; // skip if current is null

                // try to parse object
                String[] object_info = raw[col].split(",");

                // extract the following information
                int x, y, radius, health;

                // remove all characters other than numbers
                x = Integer.parseInt(object_info[1].replaceAll("[\\D]", "")); // 1st index is x
                y = Integer.parseInt(object_info[2].replaceAll("[\\D]", "")); // 2nd index is y
                radius = Integer.parseInt(object_info[3].replaceAll("[\\D]", "")); // 3rd index is radius
                health = Integer.parseInt(object_info[4].replaceAll("[\\D]", "")); // 4th index is health

                // create the object and assign to world
                String object_type = object_info[0];

                if (object_type.contains("Human")) {
                    world[row][col] = new Human(x, y, radius, health);
                } else if (object_type.contains("Doctor")) {
                    world[row][col] = new Doctor(x, y, radius, health);
                } else if (object_type.contains("Zombie")) {
                    world[row][col] = new Zombie(x, y, radius, health);
                }
            }
        }

        // close the Scanner
        sc.close();

        // set the current world to the loaded world
        TerrainPanel.setWorld(world);

        // print success log
        System.out.println("Successfully loaded file " + fileName + ".");
    }
}
