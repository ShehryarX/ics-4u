import interfaces.Config;
import gui.MainPanel;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class Contagion implements Config {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new MainPanel();
				frame.setTitle("Contagion");
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setSize(WORLD_WIDTH * SCALING_FACTOR+250, WORLD_HEIGHT * SCALING_FACTOR+100);
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
			}
		});
	}
}
