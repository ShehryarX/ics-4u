package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.CardLayout;

public class ConsolePanel extends JPanel {

    private JTextArea textArea;

    public ConsolePanel() {

        setLayout(new CardLayout(0, 0));

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
        

    }

    public void setText(String text) {
        textArea.setText(text);
        
    }
    
    public void resetText(){
        textArea.setText("");
    }

}
