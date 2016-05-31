import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.Color;

public class Main extends JFrame {

	public JPanel jContentPane = null;


	public Ball panel = null; 

	public Ball getPanel() {
		if (panel == null) {
			panel = new Ball(); // Creates the panel
		}
		return panel;
	}


	public Main() {
		super();
		setBackground(new Color(248, 248, 255));
		initialize();
		// Listeners for the keyboard
		this.addKeyListener(new KeyAdapter() {
			//Method for the key pressed
			public void keyPressed(KeyEvent evt) {
				formKeyPressed(evt);
			}
			// Method for the key released
			public void keyReleased(KeyEvent evt) {
				formKeyReleased(evt);
			}
		});

	}

	// The method that will send the key pressed to the game class
	public void formKeyPressed(KeyEvent evt)
	{
		panel.keyPressed(evt);
	}

	// The method that will send the key released to the game class
	public void formKeyReleased(KeyEvent evt)
	{
		panel.keyReleased(evt);
	}

public JPanel getJContentPane() {
if (jContentPane == null) {
jContentPane = new JPanel();
jContentPane.setLayout(new BorderLayout());
jContentPane.add(getPanel(), BorderLayout.CENTER);
jContentPane.setBackground(Color.BLACK);

}
return jContentPane;
}
	
	public void initialize() {
		this.setResizable(false);
		this.setBounds(new Rectangle(312, 184, 250, 250)); // Position on the desktop
		this.setMinimumSize(new Dimension(600, 300));
		this.setMaximumSize(new Dimension(600, 300));
		this.setContentPane(getJContentPane());
		this.setTitle("Pong");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main thisClass = new Main();
				thisClass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				thisClass.setVisible(true);
			}
		});
	}
}