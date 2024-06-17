/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PauseOverlay extends JPanel implements MouseListener {

	// Layered pane.
	private JLayeredPane layeredPane;
	// Background panel.
	private JLabel background;
	// Options label.
	private JPanel options;

	// Restart button.
	private OptionButton restartButton;

	// Restart button images.
	private ImageIcon restartButtonDefault;
	private ImageIcon restartButtonHover;
	private ImageIcon restartButtonPress;

	// Home button.
	private OptionButton homeButton;
	
	// Home button images.
	private ImageIcon homeButtonDefault;
	private ImageIcon homeButtonHover;
	private ImageIcon homeButtonPress;
	
	// Main frame.
	Boggle mainFrame;

	/**
	 * Constructor, this method creates a screen which shows up when the player
	 * clicks the pause button.
	 * @param mainFrame
	 */
	public PauseOverlay(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		// Set the dimensions of the panel.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// Add GridBagLayout.
		GridBagConstraints constraints;

		// Set the colour and dimensions of the panel.
		this.setBackground(Color.BLACK);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));
		// Add MouseListener to the panel.
		this.addMouseListener(this);

		// Set the colour and dimensions of the layered pane.
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setLayout(new GridBagLayout());
		layeredPane.setPreferredSize(new Dimension(width, height));

		// Instantiate background label.
		background = new JLabel();
		ImageIcon backgroundImage = new ImageIcon(getClass().
				getResource("assets/PauseOverlayBg.png"));
		background.setIcon(new ImageIcon(backgroundImage.getImage().
				getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		background.setBackground(Color.BLACK);
		// Position the background using GridBagConstraints.
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		// Add the background to the layered pane.
		layeredPane.add(background, constraints);
		// Set the background to the 0th layer of the layered pane.
		layeredPane.setLayer(background, 0);

		// Position the options panel.
		options = new JPanel();
		options.setLayout(new GridBagLayout());
		options.setOpaque(false);
		options.setPreferredSize(new Dimension(width, height));
		
		// Instantiate home button images.
		homeButtonDefault = new ImageIcon(getClass().
				getResource("assets/HomeBtnDefault.png"));
		homeButtonHover = new ImageIcon(getClass().
				getResource("assets/HomeBtnHover.png"));
		homeButtonPress = new ImageIcon(getClass()
				.getResource("assets/HomeBtnPress.png"));
		
		// Instantiating the home button.
		homeButton = new OptionButton(false,
				0.2 * width, 0.15 * height,
				homeButtonDefault, homeButtonHover, homeButtonPress, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			mainFrame.getGameScreen().stopBackgroundMusic();
			mainFrame.menuScreen();
		});

		// Positioning the home button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				0, 0, (int) (0.05 * height), (int) (0.05 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		options.add(homeButton, constraints);
		
		// Instantiate restart button images.
		restartButtonDefault = new ImageIcon(getClass().
				getResource("assets/RestartBtnDefault.png"));
		restartButtonHover = new ImageIcon(getClass().
				getResource("assets/RestartBtnHover.png"));
		restartButtonPress = new ImageIcon(getClass().
				getResource("assets/RestartBtnPress.png"));
		
		// Instantiate restart button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		restartButton = new OptionButton(false,
				0.2 * width, 0.15 * height,
				restartButtonDefault, restartButtonHover, restartButtonPress,
				e -> mainFrame.gameScreen(true, mainFrame.isAI()));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				0, (int) (0.05 * width), (int) (0.05 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		// Adding the restart button to the options panel.
		options.add(restartButton, constraints);
		
		// Adding the options panel to the layered pane.
		layeredPane.add(options, new GridBagConstraints());
		// Setting the options panel as the first layer on the layered pane.
		layeredPane.setLayer(options, 1);

		// Adding the layered pane to the main panel.
		this.add(layeredPane, new GridBagConstraints());
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mainFrame.gameScreen(false, mainFrame.isAI());
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	/**
	 * Invoked when the mouse exits a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
}