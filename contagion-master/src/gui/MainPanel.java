package gui;

import entities.Doctor;
import entities.Entity;
import entities.Human;
import entities.Zombie;
import interfaces.Config;
import log.Log;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame implements Config {
    private JPanel content; // GUI management
    private double human_chance, doctor_chance, zombie_chance; // keep track of chances for generation

    private JCheckBox repeatCheckbox;
    private JCheckBox showGridCheckbox;
    private JCheckBox RandomCheckbox;
    
    private ButtonListener mButtonsListener;

    private JSlider numberOfZombieSlider;
    private JSlider numberOfDoctorSlider;
    private JSlider numberOfPeopleSlider;
    private ConsolePanel consolePanel;
    
    public MainPanel() {
        // create board using layout manager
        content = new JPanel();
        content.setPreferredSize(new Dimension(WORLD_WIDTH+200,WORLD_HEIGHT+100));
        content.setLayout(new BorderLayout());

        // create 2-d board to keep track of entities
        Entity[][] world = new Entity[WORLD_HEIGHT][WORLD_WIDTH];

        // set up the default chances for entity generation
        initDefaultChances();

        // generate the entities randomly at first
        generateEntities(world);

        // create a new JPanel to display the world array and display it
        JPanel terrain = new TerrainPanel(WORLD_WIDTH * SCALING_FACTOR, WORLD_HEIGHT * SCALING_FACTOR, world);
        terrain.setLayout(new FlowLayout());
        terrain.setBorder(BorderFactory.createLineBorder(Color.black));
        content.add(terrain, BorderLayout.CENTER);

        // initiatliz and start a timer to refresh the terrain
        Timer t = new Timer(Config.TIMER_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                terrain.repaint();
            }
        });

        t.start();

        // Creates a panel where all the information is displayed
        // JPanel information = new InformationPanel(100, 100);
        // information.setLayout(new FlowLayout());
        // content.add(information, BorderLayout.EAST);

        // Creates a panel where all options are displayed
       // JPanel options = new OptionPanel(500, 100);
        //options.setLayout(new FlowLayout());
        //content.add(options, BorderLayout.NORTH);
        
        initSettingPanels();
        initButtonPanel();
        
        consolePanel = new ConsolePanel();
        consolePanel.setLayout(new GridLayout(1, 1));
        content.add(consolePanel, BorderLayout.SOUTH);
        
        // resort to default conventions
        setContentPane(content);
        pack();
        setLocationRelativeTo(null);
    }

    private void initDefaultChances() {
        // initialize the chance variables to the ones specified in Config
        human_chance = DEFAULT_HUMAN_CHANCE;
        doctor_chance = DEFAULT_DOCTOR_CHANCE;
        zombie_chance = DEFAULT_ZOMBIE_CHANCE;
    }
    
    
    private void initSettingPanels() {
         JPanel statPanel = new JPanel();
         statPanel.setLayout(new BorderLayout(0, 0));
         content.add(statPanel, BorderLayout.EAST);

         JPanel subStatPanel = new JPanel();
         statPanel.add(subStatPanel);
         subStatPanel.setLayout(new GridLayout(15, 0));
         
         repeatCheckbox = new JCheckBox("Repeat Game");
         repeatCheckbox.setSelected(false);
         subStatPanel.add(repeatCheckbox);
         showGridCheckbox = new JCheckBox("Show Grids");
         showGridCheckbox.setSelected(false);
         subStatPanel.add(showGridCheckbox);
         RandomCheckbox = new JCheckBox("Randomize Mode");
         RandomCheckbox.setSelected(false);
         subStatPanel.add(RandomCheckbox);
         
         subStatPanel.add(new JLabel("Number Of People"));
         numberOfPeopleSlider = new JSlider();
         numberOfPeopleSlider.setValue(400);
         numberOfPeopleSlider.setMajorTickSpacing(100);
         numberOfPeopleSlider.setPaintTicks(true);
         numberOfPeopleSlider.setPaintLabels(true);
         numberOfPeopleSlider.setMinorTickSpacing(50);
         numberOfPeopleSlider.setMaximum(600);
         numberOfPeopleSlider.setName("PEOPLE");
         subStatPanel.add(numberOfPeopleSlider);
        // numberOfPeopleSlider.addChangeListener(mSliderListener);
         
         subStatPanel.add(new JLabel("Number Of Doctor"));
         numberOfDoctorSlider = new JSlider();
         numberOfDoctorSlider.setValue(400);
         numberOfDoctorSlider.setPaintTicks(true);
         numberOfDoctorSlider.setPaintLabels(true);
         numberOfDoctorSlider.setMinorTickSpacing(30);
         numberOfDoctorSlider.setMaximum(800);
         numberOfDoctorSlider.setMajorTickSpacing(100);
         numberOfDoctorSlider.setName("Doctor");
         subStatPanel.add(numberOfDoctorSlider);
       //  numberOfBirdSlider.addChangeListener(mSliderListener);
         
         subStatPanel.add(new JLabel("Number Of Zombies"));
         numberOfZombieSlider = new JSlider();
         numberOfZombieSlider.setValue(400);
         numberOfZombieSlider.setPaintTicks(true);
         numberOfZombieSlider.setPaintLabels(true);
         numberOfZombieSlider.setMinorTickSpacing(30);
         numberOfZombieSlider.setMaximum(800);
         numberOfZombieSlider.setMajorTickSpacing(100);
         numberOfZombieSlider.setName("Zombie");
         subStatPanel.add(numberOfZombieSlider);
       //  numberOfBirdSlider.addChangeListener(mSliderListener);
         
         
         Dimension dim = new Dimension(statPanel.getPreferredSize().width+20,WORLD_HEIGHT);
         statPanel.setMaximumSize(dim);
         statPanel.setMinimumSize(dim);
         statPanel.setPreferredSize(dim); 
         
   
     }
     
     
     private void initButtonPanel(){
         JPanel ButtonsPanel = new JPanel();
         ButtonsPanel.setLayout(new GridLayout(1, 4));
        // ButtonsPanel.setSize(WORLD_WIDTH*8+200, 200);
         content.add(ButtonsPanel, BorderLayout.NORTH);
                 
         JButton newGameBtn = new JButton("New Game");
         newGameBtn.setName("NewGame");
         newGameBtn.addActionListener(mButtonsListener);
         ButtonsPanel.add(newGameBtn);

         JButton startBtn = new JButton("Start");
         startBtn.setName("START");
         startBtn.addActionListener(mButtonsListener);
         ButtonsPanel.add(startBtn);
         JButton stopBtn = new JButton("Stop");
         stopBtn.setName("STOP");
         stopBtn.addActionListener(mButtonsListener);
         ButtonsPanel.add(stopBtn);
         JButton saveBtn = new JButton("Save");
         saveBtn.setName("SAVE");

         // temp
         saveBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Log.save();
             }
         });

         saveBtn.addActionListener(mButtonsListener);
         ButtonsPanel.add(saveBtn);
         JButton loadBtn = new JButton("Load");
         loadBtn.setName("LOAD");

         // temp
         loadBtn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Log.load();
             }
         });

         loadBtn.addActionListener(mButtonsListener);
         ButtonsPanel.add(loadBtn);
     }
 
    
    

    /*
    - Generates entities on the board using human_chance, doctor_chance, zombie_chance, and empty_chance
    - Generates x and y coordinates for each entity
     */
    public void generateEntities(Entity[][] world) {
        // used to determine chances
        double[] chances = {human_chance, human_chance + doctor_chance, human_chance + doctor_chance + zombie_chance};

        // iterate through every object
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                // generate random number between 0.0 and 100.0
                double random = Math.random() * 100;

                // generate randoom coordinates
                int x = (int) (Math.random() * WORLD_WIDTH);
                int y = (int) (Math.random() * WORLD_HEIGHT);

                // creates new object accordingly, or leaves it as null
                if (random < chances[0]) {
                    world[i][j] = new Human(x, y);
                } else if (random < chances[1]) {
                    world[i][j] = new Doctor(x, y);
                } else if (random < chances[2]) {
                    world[i][j] = new Zombie(x, y);
                }
            }
        }
    }
    
    
      
    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent anEvent) {
            JButton source = (JButton) anEvent.getSource();
            if (source.getName().equalsIgnoreCase("NewGame")) {
                if(RandomCheckbox.isSelected()){
                   
                } 
                else{
                	
                }
                    
            }
            if (source.getName().equalsIgnoreCase("START")) {
               
            }
            if (source.getName().equalsIgnoreCase("STOP")) {

            }
        }
     }
    
    
    
}
