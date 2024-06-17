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
	private ImageIcon creditsImg;
	// Background.
	private JLabel creditsBackground;
	// Layers for background and button.
	private JLayeredPane layers;
	// Holds the buttons
	private JPanel content;

	// Return button.
	private OptionButton returnBtn;
	// Default return button.
	private ImageIcon returnDefault;
	// Hover return button.
	private ImageIcon returnHover;
	// Press return button.
	private ImageIcon returnPress;

	// Main game frame.
	private Boggle mainFrame;
	private int width;
	private int height;

	/**
	 * Constructor.
	 * @param mainFrame main game frame.
	 */
	public CreditsScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		this.width = mainFrame.getScreenWidth();
		this.height = mainFrame.getScreenHeight();

		// Used to set the location of the components.
		GridBagConstraints c;

		// Setting the layout and dimensions of the Panel.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Setting the colour and dimensions of the LayeredPane.
		layers = new JLayeredPane();
		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(width, height));

		// Set credits animation.
		creditsAnimation = new ImageIcon(getClass().
				getResource("assets/Credits.gif"));
		creditsImg = new ImageIcon(getClass()
				.getResource("assets/Credits.png"));
		creditsBackground = new JLabel();
		creditsBackground.setIcon(new ImageIcon(creditsAnimation.getImage().
				getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		creditsBackground.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(creditsBackground, c);
		layers.setLayer(creditsBackground, 0);

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
		returnBtn = new OptionButton(0.06 * width, 0.06 * width,
				returnDefault, returnHover, returnPress,
				e -> mainFrame.menuScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0,0);
		c.weightx = 1;
		c.weighty = 1;

		// Adding return button to content panel.
		content.add(returnBtn, c);

		// Adding content panel to layeredPane.
		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);

		// Adding LayeredPane to Panel.
		this.add(layers, new GridBagConstraints());
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
			creditsBackground.setIcon(new ImageIcon(creditsImg.getImage()
					.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		});
		stopAnimation.setInitialDelay(7000); // 2 seconds delay
		stopAnimation.setRepeats(false);
		stopAnimation.start();
	}
}