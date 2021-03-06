/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.util.ArrayList;
import javax.swing.*;

import Logic.Player;


/**
 *
 * @author Jennifer
 */
public class Intro extends javax.swing.JPanel {

    /**
     * Creates new form Intro
     */


    ArrayList <Player> players = new ArrayList<Player>(); //The players are listed here
    private int [] place = {0,0,0,0};
    
    public Intro() {
        initComponents();
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        icon1 = new javax.swing.JLabel();
        buttonLeft1 = new javax.swing.JButton();
        buttonRight1 = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        icon2 = new javax.swing.JLabel();
        buttonLeft2 = new javax.swing.JButton();
        buttonRight2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        icon3 = new javax.swing.JLabel();
        buttonLeft3 = new javax.swing.JButton();
        buttonRight3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        icon4 = new javax.swing.JLabel();
        buttonLeft4 = new javax.swing.JButton();
        buttonRight4 = new javax.swing.JButton();

        icon1.setIcon(new ImageIcon("images\\profile\\face (" + place[0] + ").png"));
        icon2.setIcon(new ImageIcon("images\\profile\\face (" + place[1] + ").png"));
        icon3.setIcon(new ImageIcon("images\\profile\\face (" + place[2] + ").png"));
        icon4.setIcon(new ImageIcon("images\\profile\\face (" + place[3] + ").png"));
        
        
        setBackground(new java.awt.Color(90, 189, 222));
        setMaximumSize(new java.awt.Dimension(954, 730));
        setMinimumSize(new java.awt.Dimension(954, 730));
        setPreferredSize(new java.awt.Dimension(954, 730));
        setLayout(null);

        jPanel1.setBackground(new java.awt.Color(90, 189, 222));
        jPanel1.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        jLabel1.setText("Player 1");

        jTextField1.setBackground(jPanel1.getBackground());
        jTextField1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTextField1.setText("Name");
        jTextField1.setBorder(null);
        
        icon1.setBackground(new java.awt.Color(255, 255, 255));
        icon1.setText("icon");
        //icon1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        icon1.setPreferredSize(new java.awt.Dimension(90, 90));

        buttonLeft1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonLeft1.setText("<");
        buttonLeft1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLeft1ActionPerformed(evt);
            }
        });


        buttonRight1.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonRight1.setText(">");
        buttonRight1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRight1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(buttonLeft1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(icon1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLeft1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRight1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        icon1.getAccessibleContext().setAccessibleName("icon1");

        add(jPanel1);
        jPanel1.setBounds(41, 95, 400, 200);
        jPanel1.getAccessibleContext().setAccessibleName("");
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        startButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        startButton.setText("Start!");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        add(startButton);
        startButton.setBounds(325, 615, 288, 62);

        jPanel2.setBackground(new java.awt.Color(90, 189, 222));
        jPanel2.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        jLabel2.setText("Player 2");

        jTextField2.setBackground(jPanel1.getBackground());
        jTextField2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTextField2.setText("Name");
        jTextField2.setBorder(null);
        
        icon2.setBackground(new java.awt.Color(255, 255, 255));
        icon2.setText("icon");
        //icon2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        icon2.setPreferredSize(new java.awt.Dimension(90, 90));

        buttonLeft2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonLeft2.setText("<");
        buttonLeft2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLeft2ActionPerformed(evt);
            }
        });

        buttonRight2.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonRight2.setText(">");
        buttonRight2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRight2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(buttonLeft2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRight2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(icon2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLeft2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRight2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        add(jPanel2);
        jPanel2.setBounds(41, 351, 400, 200);

        jPanel3.setBackground(new java.awt.Color(90, 189, 222));
        jPanel3.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        jLabel3.setText("Player 3");

        jTextField3.setBackground(jPanel1.getBackground());
        jTextField3.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTextField3.setText("Name");
        jTextField3.setBorder(null);

        icon3.setBackground(new java.awt.Color(255, 255, 255));
        icon3.setText("icon");
        //icon3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        icon3.setPreferredSize(new java.awt.Dimension(90, 55));

        buttonLeft3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonLeft3.setText("<");
        buttonLeft3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLeft3ActionPerformed(evt);
            }
        });

        buttonRight3.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonRight3.setText(">");
        buttonRight3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRight3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(buttonLeft3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRight3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(icon3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLeft3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRight3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        add(jPanel3);
        jPanel3.setBounds(499, 95, 400, 200);

        jPanel4.setBackground(new java.awt.Color(90, 189, 222));
        jPanel4.setPreferredSize(new java.awt.Dimension(350, 200));

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 48)); // NOI18N
        jLabel4.setText("Player 4");

        jTextField4.setBackground(jPanel1.getBackground());
        jTextField4.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jTextField4.setText("Name");
        jTextField4.setBorder(null);

        icon4.setBackground(new java.awt.Color(255, 255, 255));
        icon4.setText("icon");
        //icon4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        icon4.setPreferredSize(new java.awt.Dimension(55, 55));

        buttonLeft4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonLeft4.setText("<");
        buttonLeft4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLeft4ActionPerformed(evt);
            }
        });

        buttonRight4.setFont(new java.awt.Font("Tahoma", 0, 8)); // NOI18N
        buttonRight4.setText(">");
        buttonRight4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRight4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(buttonLeft4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRight4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(icon4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel4)
                .addGap(1, 1, 1)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(icon4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLeft4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRight4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        add(jPanel4);
        jPanel4.setBounds(499, 351, 400, 200);
    }// </editor-fold>                        

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        
        if(!jTextField1.getText().isEmpty()&&("Name").indexOf(jTextField1.getText())==-1){
        	players.add(new Player(jTextField1.getText(),"images\\profile1\\face (" + place[0] + ").png"));
        }if(!jTextField2.getText().isEmpty()&&("Name").indexOf(jTextField2.getText())==-1){
            players.add(new Player(jTextField2.getText(),"images\\profile1\\face (" + place[1] + ").png"));
        }if(!jTextField3.getText().isEmpty()&&("Name").indexOf(jTextField3.getText())==-1){
        	players.add(new Player(jTextField3.getText(),"images\\profile1\\face (" + place[2] + ").png"));
        }if(!jTextField4.getText().isEmpty()&&("Name").indexOf(jTextField4.getText())==-1){
        	players.add(new Player(jTextField4.getText(),"images\\profile1\\face (" + place[3] + ").png"));
        }
        
        
        
    }                                           

    private void buttonRight1ActionPerformed(java.awt.event.ActionEvent evt) {                                             

        place[0]++;
        if (place[0] > 20) {
            place[0] = 0;
        }
        icon1.setIcon(new ImageIcon("images\\profile\\face (" + place[0] + ").png"));
        repaint();
    }                                            

    private void buttonRight2ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        place[1]++;
        if (place[1] > 20) {
            place[1] = 0;
        }
        icon2.setIcon(new ImageIcon("images\\profile\\face (" + place[1] + ").png"));
        repaint();
    }                                            
                                       

    private void buttonRight3ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        place[2]++;
        if (place[2] > 20) {
            place[2] = 0;
        }
        icon3.setIcon(new ImageIcon("images\\profile\\face (" + place[2] + ").png"));
        repaint();
    }                                            

    private void buttonRight4ActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
        place[3]++;
        if (place[3] > 20) {
            place[3] = 0;
        }
        icon4.setIcon(new ImageIcon("images\\profile\\face (" + place[3] + ").png"));
        repaint();
    }                                            

    private void buttonLeft4ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        place[3]--;
        if (place[3] < 0) {
            place[3] = 20;
        }
        icon4.setIcon(new ImageIcon("images\\profile\\face (" + place[3] + ").png"));
        repaint();
    }                                           

    private void buttonLeft3ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        place[2]--;
        if (place[2] < 0) {
            place[2] = 20;
        }
        icon3.setIcon(new ImageIcon("images\\profile\\face (" + place[2] + ").png"));
        repaint();
    }                                           

    private void buttonLeft2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        place[1]--;
        if (place[1] < 0) {
            place[1] = 20;
        }
        icon2.setIcon(new ImageIcon("images\\profile\\face (" + place[1] + ").png"));
        repaint();
    }                                           

    private void buttonLeft1ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
        place[0]--;
        if (place[0] < 0) {
            place[0] = 20;
        }
        icon1.setIcon(new ImageIcon("images\\profile\\face (" + place[0] + ").png"));
        repaint();
    }   

    // Variables declaration - do not modify                     
    private javax.swing.JButton buttonLeft1;
    private javax.swing.JButton buttonLeft2;
    private javax.swing.JButton buttonLeft3;
    private javax.swing.JButton buttonLeft4;
    private javax.swing.JButton buttonRight1;
    private javax.swing.JButton buttonRight2;
    private javax.swing.JButton buttonRight3;
    private javax.swing.JButton buttonRight4;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon3;
    private javax.swing.JLabel icon4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton startButton;
    // End of variables declaration                   
}
