package gui;

import com.sun.istack.internal.NotNull;
import entities.Terrain;
import interfaces.Config;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameFrame extends JFrame implements Config {

    // instance variables

    // images
    private ImagePanel gameImage;
    private ImagePanel startImage;

    // gui entities
    private JPanel mainPanel;
    private TerrainPanel mGamePanel;
    private JCheckBox repeatCheckbox;
    private JCheckBox RandomCheckbox;
    private ButtonListener mButtonsListener;
    private JLabel zombies;
    private JLabel humans;
    private JLabel days;
    private JLabel immune;
    private JSlider ageOfHumansSlider;
    private JSlider ageOfZombiesSlider;
    private JSlider chanceOfInfectionSlider;
    private JSlider speedSlider;

    // buttons
    private OrganismButtonListener mOrganismBtnListener;
    private char state = ' ';

    // Listeners
    private ComboBoxListener mComboBoxListener;

    // timers
    private Timer startTimer;

    // logic
    private Terrain myTerrain;

    /**
     * constructor - creates the jframe of the game
     */
    public GameFrame() {

        // ====================MAIN============================
        mainPanel = new JPanel();
        setContentPane(mainPanel);
        mainPanel.setLayout(new BorderLayout());
        startImage = new ImagePanel(new ImageIcon("res/title.png").getImage());
        startImage.addMouseListener(new MyMouseListener());
        mainPanel.add(startImage);
        //init();
        pack();
    }

    private void init() {
        // ===============LOGIC==============================
        myTerrain = new Terrain();
        mButtonsListener = new ButtonListener();
        mOrganismBtnListener = new OrganismButtonListener();
        mComboBoxListener = new ComboBoxListener();

        // ==================ADDS THE TERRAIN=============
        mGamePanel = new TerrainPanel(myTerrain);
        mGamePanel.setMaximumSize(mGamePanel.getSize());
        mGamePanel.setMinimumSize(mGamePanel.getSize());
        mGamePanel.setPreferredSize(mGamePanel.getSize());
        mainPanel.add(mGamePanel, BorderLayout.CENTER);

        mGamePanel.setBackground(Color.BLACK);
        gameImage = new ImagePanel(new ImageIcon("res/title.png").getImage());
        mGamePanel.add(gameImage);

        initPanels();

        // ============ADDS LISTENERS======================
        MouseAdapter mouseAdapter = new MyMouseAdapter();
        mGamePanel.addMouseListener(mouseAdapter);

        // =============INITIALIZES VALUES=================
        myTerrain.setMyNumberOfPeopleToCreate(500);
        myTerrain.setMyNumberOfZombiesToCreate(50);
        myTerrain.setMyNumberOfDoctorsToCreate(30);

        startTimer = new Timer(200, new startTimerHandler());
    }

    /**
     * initiates the sub panels, which is a lot of them, ordered in a borderlayout
     */
    private void initPanels() {

        // =========STAT PANEL============
        JPanel statPanel = new JPanel();
        statPanel.setLayout(new GridLayout(12, 0));
        statPanel.setBackground(PANELCOLORTWO);
        mainPanel.add(statPanel, BorderLayout.WEST);

        // ==============CHECK BOXES FOR RANDOMIZE AND REPEAT===================
        repeatCheckbox = new JCheckBox("Repeat Game");
        repeatCheckbox.setSelected(false);
        repeatCheckbox.setBackground(PANELCOLORTWO);
        statPanel.add(repeatCheckbox);
        RandomCheckbox = new JCheckBox("Randomize Mode");
        RandomCheckbox.setSelected(false);
        RandomCheckbox.setBackground(PANELCOLORONE);
        statPanel.add(RandomCheckbox);


        // ==============SPEED====================
        statPanel.add(new JLabel("Speed"));
        speedSlider = new JSlider();
        speedSlider.setValue(10);
        speedSlider.setPaintLabels(true);
        speedSlider.setPaintTicks(true);
        speedSlider.setMajorTickSpacing(50);
        speedSlider.setMaximum(200);
        speedSlider.setName("SPEED");
        speedSlider.setBackground(PANELCOLORTWO);
        statPanel.add(speedSlider);
        speedSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                updateSpeed(speedSlider.getValue());
            }
        });

        // ================USER INTERATION FEATURES=================
        // ================HUMAN CREATION=================
        JPanel subStatPanelP = new JPanel();
        subStatPanelP.setLayout(new GridLayout(0, 2));
        subStatPanelP.setBackground(PANELCOLORONE);
        subStatPanelP.add(new JLabel("Number Of People:"));
        JComboBox peopleComboBox = new JComboBox(HUMANARRAY);
        peopleComboBox.setEditable(true);
        peopleComboBox.setName("peopleComboBox");
        peopleComboBox.setSelectedIndex(12);
        peopleComboBox.addActionListener(mComboBoxListener);
        subStatPanelP.add(peopleComboBox);
        statPanel.add(subStatPanelP);

        // ===================DOCTOR CREATION=================
        JPanel subStatPanelD = new JPanel();
        subStatPanelD.setLayout(new GridLayout(0, 2));
        subStatPanelD.setBackground(PANELCOLORTWO);
        subStatPanelD.add(new JLabel("Number Of Doctor:"));
        JComboBox doctorComboBox = new JComboBox(DOCTORARRAY);
        doctorComboBox.setEditable(true);
        doctorComboBox.setName("doctorComboBox");
        doctorComboBox.setSelectedIndex(3);
        doctorComboBox.addActionListener(mComboBoxListener);
        subStatPanelD.add(doctorComboBox);
        statPanel.add(subStatPanelD);

        // ==============ZOMBIE CREATION====================
        JPanel subStatPanelZ = new JPanel();
        subStatPanelZ.setLayout(new GridLayout(0, 2));
        subStatPanelZ.setBackground(PANELCOLORONE);
        subStatPanelZ.add(new JLabel("Number Of zombies:"));
        JComboBox zombieComboBox = new JComboBox(ZOMBIEARRAY);
        zombieComboBox.setEditable(true);
        zombieComboBox.setName("zombieComboBox");
        zombieComboBox.setSelectedIndex(4);
        zombieComboBox.addActionListener(mComboBoxListener);
        subStatPanelZ.add(zombieComboBox);
        statPanel.add(subStatPanelZ);


        // =============STAT PANEL=====================
        Dimension dim = new Dimension(statPanel.getPreferredSize().width, PANEL_HEIGHT);
        statPanel.setMaximumSize(dim);
        statPanel.setMinimumSize(dim);
        statPanel.setPreferredSize(dim);

        // =================NUMBER COUNTS====================
        days = new JLabel("Days: " + myTerrain.getMyDayCounter());
        zombies = new JLabel("Zombies: " + myTerrain.getMyZombies());
        humans = new JLabel("Humans: " + myTerrain.getMyHumansHealthy());
        immune = new JLabel("Immune: " + myTerrain.getMyHumansHealthy());
        statPanel.add(days);
        statPanel.add(zombies);
        statPanel.add(humans);
        statPanel.add(immune);
        initButtons();

        // ===========BOTTOM SLIDERS=======
        JPanel sliderPanel = new JPanel();
        //sliderPanel.setLayout(new GridLayout(2, 3));
        sliderPanel.setLayout(new GridLayout(1, 3));
        sliderPanel.setBackground(PANELCOLORONE);
        mainPanel.add(sliderPanel, BorderLayout.SOUTH);

        // =================HUMANS================
        JPanel sliderHPanel = new JPanel();
        sliderHPanel.setLayout(new GridLayout(2, 1));
        sliderHPanel.setBackground(PANELCOLORONE);
        sliderHPanel.add(new JLabel("\tHuman Age"));
        ageOfHumansSlider = new JSlider();
        ageOfHumansSlider.setValue(10);
        ageOfHumansSlider.setPaintLabels(true);
        ageOfHumansSlider.setPaintTicks(true);
        ageOfHumansSlider.setMajorTickSpacing(10);
        ageOfHumansSlider.setMaximum(HUMAN_LIFESPAN);
        ageOfHumansSlider.setName("ageOfHumans");
        ageOfHumansSlider.setBackground(PANELCOLORONE);
        ageOfHumansSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                updateInfo('h', ageOfHumansSlider.getValue());
            }
        });
        sliderHPanel.add(ageOfHumansSlider);
        sliderPanel.add(sliderHPanel);

        // =================ZOMBIES================
        JPanel sliderZPanel = new JPanel();
        sliderZPanel.setLayout(new GridLayout(2, 1));
        sliderZPanel.setBackground(PANELCOLORTWO);
        sliderZPanel.add(new JLabel("\tZombie Age"));
        ageOfZombiesSlider = new JSlider();
        ageOfZombiesSlider.setValue(10);
        ageOfZombiesSlider.setPaintLabels(true);
        ageOfZombiesSlider.setPaintTicks(true);
        ageOfZombiesSlider.setMajorTickSpacing(10);
        ageOfZombiesSlider.setMaximum(ZOMBIE_LIFESPAN);
        ageOfZombiesSlider.setName("ageOfHumans");
        ageOfZombiesSlider.setBackground(PANELCOLORTWO);
        ageOfZombiesSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                updateInfo('z', ageOfZombiesSlider.getValue());
            }
        });
        sliderZPanel.add(ageOfZombiesSlider);
        sliderPanel.add(sliderZPanel);

        // =================INFECTION================
        JPanel sliderInfectionPanel = new JPanel();
        sliderInfectionPanel.setLayout(new GridLayout(2, 1));
        sliderInfectionPanel.setBackground(PANELCOLORONE);
        sliderInfectionPanel.add(new JLabel("\tInfection Rate"));
        chanceOfInfectionSlider = new JSlider();
        chanceOfInfectionSlider = new JSlider();
        chanceOfInfectionSlider.setValue(10);
        chanceOfInfectionSlider.setPaintLabels(true);
        chanceOfInfectionSlider.setPaintTicks(true);
        chanceOfInfectionSlider.setMajorTickSpacing(10);
        chanceOfInfectionSlider.setMaximum(INFECTION_CHANCE);
        chanceOfInfectionSlider.setName("ageOfHumans");
        chanceOfInfectionSlider.setBackground(PANELCOLORONE);
        chanceOfInfectionSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent event) {
                updateInfo('i', chanceOfInfectionSlider.getValue());
            }
        });
        sliderInfectionPanel.add(chanceOfInfectionSlider);
        sliderPanel.add(sliderInfectionPanel);

    }

    /**
     * initializes the buttons and adds action listeners
     */
    private void initButtons() {
        // =================BUTTONS PANEL================
        JPanel ButtonsPanel = new JPanel();
        ButtonsPanel.setLayout(new GridLayout(1, 4));
        ButtonsPanel.setSize(1000, 200);
        mainPanel.add(ButtonsPanel, BorderLayout.NORTH);

        // =================WORLDGENERATOR================
        JButton newGameBtn = new JButton(new ImageIcon(new ImageIcon("res/createBtn.png").getImage().getScaledInstance(BLENGTH, BWIDTH, Image.SCALE_SMOOTH)));
        newGameBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        newGameBtn.setBackground(new Color(231, 240, 248));
        newGameBtn.setForeground(Color.BLUE);
        newGameBtn.setName("NewGame");
        newGameBtn.addActionListener(mButtonsListener);
        ButtonsPanel.add(newGameBtn);

        // =================START================
        JButton startBtn = new JButton(new ImageIcon(new ImageIcon("res/startBtn.png").getImage().getScaledInstance(BLENGTH, BWIDTH, Image.SCALE_SMOOTH)));
        startBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        startBtn.setBackground(new Color(231, 240, 248));
        startBtn.setForeground(Color.BLUE);
        startBtn.setName("START");
        startBtn.addActionListener(mButtonsListener);
        ButtonsPanel.add(startBtn);

        // =================STOP================
        JButton stopBtn = new JButton(new ImageIcon(new ImageIcon("res/stopBtn.png").getImage().getScaledInstance(BLENGTH, BWIDTH, Image.SCALE_SMOOTH)));
        stopBtn.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        stopBtn.setBackground(new Color(231, 240, 248));
        stopBtn.setForeground(Color.BLUE);
        stopBtn.setName("STOP");
        stopBtn.addActionListener(mButtonsListener);
        ButtonsPanel.add(stopBtn);

        // =================USER INTERACTION BUTTONS================
        JPanel subButtonsPanel = new JPanel();
        subButtonsPanel.setLayout(new GridLayout(1, 3));
        ButtonsPanel.add(subButtonsPanel);

        // =================ADD DOCTORS================
        JButton doctorBtn = new JButton(new ImageIcon(new ImageIcon("res/doctorBtn.png").getImage().getScaledInstance(NLENGTH, NWIDTH, Image.SCALE_SMOOTH)));
        doctorBtn.setName("doctor");
        doctorBtn.setBackground(DOCTORBUTTON);
        doctorBtn.setOpaque(true);
        doctorBtn.addActionListener(mOrganismBtnListener);
        subButtonsPanel.add(doctorBtn);

        // =================ADD HUMANS================
        JButton humanBtn = new JButton(new ImageIcon(new ImageIcon("res/humanBtn.png").getImage().getScaledInstance(NLENGTH, NWIDTH, Image.SCALE_SMOOTH)));
        humanBtn.setName("human");
        humanBtn.setBackground(HUMANBUTTON);
        humanBtn.setOpaque(true);
        humanBtn.addActionListener(mOrganismBtnListener);
        subButtonsPanel.add(humanBtn);

        // =================ADD ZOMBIES================
        JButton zombieBtn = new JButton(new ImageIcon(new ImageIcon("res/zombieBtn.png").getImage().getScaledInstance(NLENGTH, NWIDTH, Image.SCALE_SMOOTH)));
        zombieBtn.setName("zombie");
        zombieBtn.setBackground(ZOMBIEBUTTON);
        zombieBtn.setOpaque(true);
        zombieBtn.addActionListener(mOrganismBtnListener);
        subButtonsPanel.add(zombieBtn);

        // =================KILL ORGANISMS================
        JButton killBtn = new JButton(new ImageIcon(new ImageIcon("res/killBtn.png").getImage().getScaledInstance(NLENGTH, NWIDTH, Image.SCALE_SMOOTH)));
        killBtn.setName("kill");
        killBtn.setOpaque(true);
        killBtn.setBackground(KILLBUTTON);
        killBtn.addActionListener(mOrganismBtnListener);
        subButtonsPanel.add(killBtn);

    }

    /**
     * runs the terrain
     *
     * @throws IOException
     */
    private void step() throws IOException {
        if (myTerrain.getMyHumansHealthy() == 0) { // all humans that aren't immune
            // have died
            checkTimer();
            if (repeatCheckbox.isSelected()) {
                reRandomizeGenerateGame();
            }
        }

        if (myTerrain.getEntitiesInfected() == 0) { // all infected and dead are
            // zero
            checkTimer();
            if (repeatCheckbox.isSelected()) {
                reRandomizeGenerateGame();
            }
        } else {
            myTerrain.performGameMechs();
            myTerrain.incrementMyDayCounter();
            updateLabels();
            mGamePanel.updateUI();

        }
    }

    /**
     * updates the labesl under stats and changes the background based on which
     * organism is winning
     */
    private void updateLabels() {
        days.setText("Days: " + myTerrain.getMyDayCounter());
        zombies.setText("Zombies: " + myTerrain.getEntitiesInfected());
        humans.setText("Humans: " + myTerrain.getMyHumansHealthy());
        immune.setText("Immune: " + myTerrain.getImmune());

        if (myTerrain.getMyHumansHealthy() > 400)
            mGamePanel.setBackground(BACKGROUNDCOLOR_0);
        else if (myTerrain.getMyHumansHealthy() > 250)
            mGamePanel.setBackground(BACKGROUNDCOLOR_1);
        else if (myTerrain.getMyHumansHealthy() > 150)
            mGamePanel.setBackground(BACKGROUNDCOLOR_2);
        else if (myTerrain.getMyHumansHealthy() > 40)
            mGamePanel.setBackground(BACKGROUNDCOLOR_3);
        else if (myTerrain.getMyHumansHealthy() > 0)
            mGamePanel.setBackground(BACKGROUNDCOLOR_4);

    }

    /**
     * creates a randomized world
     */
    private void reRandomizeGenerateGame() {
        randomizeWorld();
        doNewGame();
        startTimer.start();

    }

    /**
     * checks if timer is running, and stops it
     */
    private void checkTimer() {
        if (startTimer != null) {
            startTimer.stop();
        }
    }

    /**
     * randomly generates number of humans and doctors ans zombies to create
     */
    private void randomizeWorld() {
        myTerrain.setMyNumberOfDoctorsToCreate(GENERATOR.nextInt(100));
        myTerrain.setMyNumberOfZombiesToCreate(GENERATOR.nextInt(800));
        myTerrain.setMyNumberOfPeopleToCreate(GENERATOR.nextInt(800));
    }

    /**
     * generates new game for terrain
     */
    private void doNewGame() {
        if (myTerrain.getMyNumberOfPeopleToCreate() == 0
                && myTerrain.getMyNumberOfZombiesToCreate() == 0) {
            return;
        }

        if (startTimer != null) {
            startTimer.stop();
        }

        mGamePanel.reset();

        myTerrain.createHumans();
        myTerrain.createZombies();
        myTerrain.createDoctors();

        mGamePanel.updateUI();
    }

    /**
     * updates info
     *
     * @param i - species
     * @param s - number of species
     */
    private void updateInfo(char i, int s) {
        if (i != ' ') {
            myTerrain.updateInfo(i, s);
        }
    }

    /**
     * update speed of the runner
     *
     * @param s - ideal speed
     */
    public void updateSpeed(int s) {
        startTimer.setDelay(s);
    }

    /**
     * button listeners for the organisms chages states of what was the last thing
     * clicked
     */
    private class OrganismButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent anEvent) {
            JButton source = (JButton) anEvent.getSource();
            if (source.getName().equalsIgnoreCase("doctor")) {
                state = 'd';
            }
            if (source.getName().equalsIgnoreCase("human")) {
                state = 'h';
            }
            if (source.getName().equalsIgnoreCase("zombie")) {
                state = 'z';
            }
            if (source.getName().equalsIgnoreCase("kill")) {
                myTerrain.killOrganism();
            }

        }
    }

    /**
     * combo box listener tells value of the boxes and how many organisms to add
     *
     * @author Jennifer
     */
    private class ComboBoxListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                JComboBox source = (JComboBox) event.getSource();
                if (source.getName().equalsIgnoreCase("peopleComboBox")) {
                    myTerrain.setMyNumberOfPeopleToCreate(Integer
                            .parseInt(HUMANARRAY[source.getSelectedIndex()]));
                } else if (source.getName().equalsIgnoreCase("doctorComboBox")) {
                    myTerrain.setMyNumberOfDoctorsToCreate(Integer
                            .parseInt(DOCTORARRAY[source.getSelectedIndex()]));
                } else if (source.getName().equalsIgnoreCase("zombieComboBox")) {
                    myTerrain.setMyNumberOfZombiesToCreate(Integer
                            .parseInt(ZOMBIEARRAY[source.getSelectedIndex()]));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * adds mouse adapter
     *
     * @author Jennifer
     */
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent event) {

            Point pt = event.getPoint();
            int newX = pt.x - (pt.x % 10);
            int newY = pt.y - (pt.y % 10);
            pt = new Point(newX, newY);
            if (state != 'k') {
                myTerrain.addOrganism(state, pt);
            }
            mGamePanel.updateUI();
        }
    }

    /**
     * button listener for the top buttons
     *
     * @author Jennifer
     */
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            JButton source = (JButton) event.getSource();

            if (source.getName().equalsIgnoreCase("NewGame")) {
                mGamePanel.remove(gameImage);

                if (RandomCheckbox.isSelected()) {
                    randomizeWorld();
                    mGamePanel.updateUI();
                }

                doNewGame();
                updateLabels();
            }

            if (source.getName().equalsIgnoreCase("START")) {

                if (myTerrain.getMyOrganismList().isEmpty()) {
                    return;
                }
                startTimer.start();
            }
            if (source.getName().equalsIgnoreCase("STOP")) {

                if (startTimer == null) {
                    return;
                } else {
                    startTimer.stop();
                    mGamePanel.updateUI();
                }

            }

        }
    }

    private class MyMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent event) {
            mainPanel.remove(startImage);
            startImage.setVisible(false);
            init();
        }

        public void mouseEntered(MouseEvent event) {
        }

        public void mouseExited(MouseEvent event) {
        }

        public void mousePressed(MouseEvent event) {
        }

        public void mouseReleased(MouseEvent event) {
        }
    } // inner class clickListener

    /**
     * adds start time
     *
     * @author Jennifer
     */
    private class startTimerHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent anEvent) {
            try {
                step();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
