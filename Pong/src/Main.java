import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {

	private JPanel jContentPane = null;

	private PanelPelota panel = null; 

	private PanelPelota getPanel() {
		if (panel == null) {
			panel = new PanelPelota(); // Creates the panel
		}
		return panel;
	}


	public Main() {
		super();
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
	private void formKeyPressed(KeyEvent evt)
	{
		panel.keyPressed(evt);
	}

	// The method that will send the key released to the game class
	private void formKeyReleased(KeyEvent evt)
	{
		panel.keyReleased(evt);
	}

	private void initialize() {
		this.setResizable(false);
		this.setBounds(new Rectangle(312, 184, 250, 250)); // Position on the desktop
		this.setMinimumSize(new Dimension(600, 300));
		this.setMaximumSize(new Dimension(600, 300));
		this.setContentPane(getJContentPane());
		this.setTitle("Pong");
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
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