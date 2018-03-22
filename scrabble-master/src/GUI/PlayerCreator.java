package GUI;

import Logic.Player;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class PlayerCreator extends JFrame {
	private static ArrayList<Player> players;
	private static JLayeredPane jLayeredPane;
	private static JButton[] icons;
	private static int[] place;
	private static JTextField[] names = new JTextField[4];
	// private static JButton[] currentIcon;

	public PlayerCreator() {
		super("Scrabble");
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		players = new ArrayList<>();
		jLayeredPane = getLayeredPane();
		icons = new JButton[4];
		place = new int[4];
		setVisible(true);
		setResizable(false);
		setSize(504, 440);
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		for (int i = 0; i < 4; i++) {
			place[i] = (int) (Math.random() * 21);
			String path = "images\\profile\\face (" + place[i] + ").png";
			icons[i] = new JButton(
					new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH)));
			// icons.get(i).setText(i + "");
		}

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getX() >= 212 && e.getX() <= 296 && e.getY() >= 387 && e.getY() <= 413) {
					int validPlayers = 0;

					for (int i = 0; i < 4; i++) {
						if (!names[i].getText().equals("") && !names[i].getText().equals("Name")) {
							players.add(new Player(names[i].getText(), null));

							String path = "images\\profile\\face (" + place[i] + ").png";
							players.get(i).setIcon(new JLabel(new ImageIcon(
									new ImageIcon(path).getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH))));
						}
						else {
							players.add(null);
						}
					}
					
					for (Player p : players) {
						if (p == null) {
						} else {
							if (!(p.getName().equals("") || p.getName() == null || p.getIcon() == null)) {
								validPlayers++;
							}
						}
					}

					if ( validPlayers < 2) {
						JOptionPane.showMessageDialog(null, "Please enter at least two players!");
					} else {
						setVisible(false);
						createBoard();
					}
				}
			}
		});

		initBackground();
	}

	/*
	 * private static JButton nextIcon(JButton jButton) { int curr =
	 * Integer.parseInt(jButton.getText());
	 * 
	 * if (curr >= 20) { curr = 0; } else { ++curr; }
	 * 
	 * JButton j = new JButton(icons[curr].getIcon()); //j.setText(curr + "");
	 * return j; }
	 */
	private static void createBoard() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ArrayList<Player> ps =new ArrayList<>();
				for(Player p : players) if(p!=null)ps.add(p);
				
				JFrame frame = new Board(ps);
				frame.setTitle("Scrabble");
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setSize(954, 730);
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			}
		});
	}

	private void initBackground() {
        JLabel jLabel = new JLabel(new ImageIcon(new ImageIcon("images\\playerCreation.PNG").getImage().getScaledInstance(
                504, 400, Image.SCALE_SMOOTH)));
        jLabel.setBackground(new Color(0, 0, 0, 192));
        jLabel.setVisible(true);
        jLabel.setOpaque(true);
        jLabel.setBounds(0, 0, 504, 400);
        jLayeredPane.add(jLabel);
        jLayeredPane.moveToFront(jLabel);

        

        Font f = null;

        try {
            f = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\font.otf"));
            f = f.deriveFont(Font.BOLD, 40);
        } catch (Exception e) {
        }

        int yN = 73;
        int xN = 130;

        for (int i = 0; i < names.length; i++) {
            names[i] = new JTextField("Name");
            names[i].setFont(f);
            names[i].setVisible(true);
            names[i].setForeground(Color.WHITE);
            names[i].setBounds(xN, yN, 320, 50);
            names[i].setOpaque(false);
            names[i].setBorder(null);

            final int x = i;
            names[i].getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    updateIt();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    updateIt();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    updateIt();
                }
                
                public void updateIt() {
                }
            });
            
            names[x].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                	if(names[x].getText().equals("Name")) {
                		names[x].setText("");
                	}
                	
                	for(int a = 0; a < 4; a++) {
                		if(a!=x) {
                			if(names[a].getText().equals("")) {
                        		names[a].setText("Name");
                        	}
                		}
                	}
                	
                }
            });

            icons[i].setOpaque(false);
        	icons[i].setContentAreaFilled(false);
        	icons[i].setBorderPainted(false);
           	icons[i].setBounds(50, yN, 60, 60);
           	icons[i].setFocusable(false);
            final int yNN = yN;
            icons[i].addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                	//icons[x].setVisible(false);
                	place[x] ++;
                	if(place[x]>20) {
                		place[x]=0;
                	}
                	String path = "images\\profile\\face (" + place[x] + ").png";
                    icons[x].setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(
                            55, 55, Image.SCALE_SMOOTH)));
                    //icons[x].setBounds(50, yNN, 60, 60);
                    //jLayeredPane.add(icons[x]);
                    //jLayeredPane.moveToFront(icons[x]);
                	repaint();
                	icons[x].setOpaque(false);
                	icons[x].setContentAreaFilled(false);
                	icons[x].setBorderPainted(false);
                    //players[x].setIcon(new JLabel(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(
                            //55, 55, Image.SCALE_SMOOTH))));
                }
            });
            //icons[x].setVisible(true);
            jLayeredPane.add(icons[i]);
            jLayeredPane.moveToFront(icons[i]);
            jLayeredPane.add(names[i]);
            jLayeredPane.moveToFront(names[i]);

            yN += 71;
        }
    }
}
