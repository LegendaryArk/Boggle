/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class displays the credit screen.
 */
public class CreditsScreen extends JPanel {
	// Credits animation.
	private ImageIcon creditsAnimation;
	// Credits image.
	private ImageIcon creditsImage;
	// Background.
	private JLabel creditsBackground;
	// Layers for background and button.
	private JLayeredPane layeredPane;
	// Holds the buttons
	private JPanel content;

	// Return button.
	private OptionButton returnButton;
	// Default return button.
	private ImageIcon returnDefault;
	// Hover return button.
	private ImageIcon returnHover;
	// Press return button.
	private ImageIcon returnPress;

	// mainFrame width and height.
	private int width;
	private int height;

	/**
	 * Constructor.
	 * @param mainFrame main game frame.
	 */
	public CreditsScreen(Boggle mainFrame) {
		// Initialize width and height.
		this.width = mainFrame.getScreenWidth();
		this.height = mainFrame.getScreenHeight();

		// Used to set the location of the components.
		GridBagConstraints constraints;

		// Setting the layout and dimensions of the Panel.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Setting the colour and dimensions of the LayeredPane.
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.black);
		layeredPane.setLayout(new GridBagLayout());
		layeredPane.setPreferredSize(new Dimension(width, height));

		// Set credits animation.
		creditsAnimation = new ImageIcon(getClass().
				getResource("assets/Credits.gif"));
		creditsImage = new ImageIcon(getClass()
				.getResource("assets/Credits.png"));
		creditsBackground = new JLabel();
		creditsBackground.setIcon(new ImageIcon(creditsAnimation.getImage().
				getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		creditsBackground.setBackground(Color.black);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		layeredPane.add(creditsBackground, constraints);
		layeredPane.setLayer(creditsBackground, 0);

		// Set layout and dimensions of content.
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(width, height));

		// Path to the return button icons.
		returnDefault = new ImageIcon(
				getClass().getResource("assets/ReturnBtnDefault.png"));
		returnHover = new ImageIcon(
				getClass().getResource("assets/ReturnBtnHover.png"));
		returnPress = new ImageIcon(
				getClass().getResource("assets/ReturnBtnPress.png"));

		// Setting the return button size and location.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		returnButton = new OptionButton(false, 0.06 * width, 0.06 * width,
				returnDefault, returnHover, returnPress,
				e -> mainFrame.menuScreen());
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0,0);
		constraints.weightx = 1;
		constraints.weighty = 1;

		// Adding return button to content panel.
		content.add(returnButton, constraints);

		// Adding content panel to layeredPane.
		layeredPane.add(content, new GridBagConstraints());
		layeredPane.setLayer(content, 1);

		// Adding LayeredPane to Panel.
		this.add(layeredPane, new GridBagConstraints());
	}

	/**
	 * This method starts the credits screen and stops the animation once it is
	 * complete.
	 */
	public void start() {
		creditsBackground.setIcon(new ImageIcon(creditsAnimation.getImage()
				.getScaledInstance(width, height, Image.SCALE_DEFAULT)));

		// Stop animation after 2 seconds.
		Timer stopAnimation = new Timer(0, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			creditsBackground.setIcon(new ImageIcon(creditsImage.getImage()
					.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		});
		stopAnimation.setInitialDelay(7000); // 2 seconds delay
		stopAnimation.setRepeats(false);
		stopAnimation.start();
	}
}