/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains a screen where the user can choose to play against AI or
 * player.
 */
public class ModeSelectionScreen extends JPanel {
	// Layered pane to hold all components.
	private JLayeredPane layeredPane;
	// Background for the mode selection screen.
	private JLabel background;
	// Panel Holds buttons.
	private JPanel content;

	// Button for player vs player game mode.
	private OptionButton playerVersusPlayerButton;

	// Player versus player button default image.
	private ImageIcon playerVersusPlayerButtonDefault;
	// Player versus player button hover image.
	private ImageIcon playerVersusPlayerButtonHover;
	// Path to the player versus player button pressed image.
	private ImageIcon playerVersusPlayerButtonPress;

	private OptionButton playerVersusAIButton;
	// Player versus AI button default image.
	private ImageIcon playerVersusAIButtonDefault;
	// Player versus AI button hover image.
	private ImageIcon playerVersusAIButtonHover;
	// Player versus AI button pressed image.
	private ImageIcon playerVersusAIButtonPress;

	// Background image.
	private ImageIcon backgroundImage;

	/**
	 * Constructor, this method displays a frame where the player has the option
	 * to play against another player or the AI.
	 * @param mainFrame The parent frame of the game.
	 */
	public ModeSelectionScreen(Boggle mainFrame) {
		// Dimensions of the frame.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// Used to set the position of the components.
		GridBagConstraints constraints;

		// Setting the colour and dimensions of the background.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Setting the colour and dimensions of the LayeredPane.
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.black);
		layeredPane.setLayout(new GridBagLayout());
		layeredPane.setPreferredSize(new Dimension(width, height));

		// Adding the Image to the background.
		background = new JLabel();
		backgroundImage = new ImageIcon(getClass().
				getResource("assets/GameOptionScreenBg.png"));
		background.setIcon(new ImageIcon(backgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		// Setting its position using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		// Adding the background to the Layered LayeredPane.
		layeredPane.add(background, constraints);
		// Setting the background as the 0th layer on the LayeredPane.
		layeredPane.setLayer(background, 0);

		// Set layout to GridBagLayout for content panel.
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setOpaque(false);

		// Setting the dimensions of the content screen.
		content.setPreferredSize(new Dimension(width, height));

		// Instantiate player versus player button images.
		playerVersusPlayerButtonDefault = new ImageIcon(getClass().
				getResource("assets/PlrVsPlrBtnDefault.png"));
		playerVersusPlayerButtonHover = new ImageIcon(getClass().
				getResource("assets/PlrVsPlrBtnHover.png"));
		playerVersusPlayerButtonPress = new ImageIcon(getClass().
				getResource("assets/PlrVsPlrBtnPress.png"));

		// Instantiate player versus AI button images.
		playerVersusAIButtonDefault = new ImageIcon(getClass().
				getResource("assets/PlrVsAIBtnDefault.png"));
		playerVersusAIButtonHover = new ImageIcon(getClass().
				getResource("assets/PlrVsAIBtnHover.png"));
		playerVersusAIButtonPress = new ImageIcon(getClass().
				getResource("assets/PlrVsAIBtnPress.png"));

		// Instantiating the player versus player button.
		playerVersusPlayerButton = new OptionButton(false,
				0.3 * width, 0.2 * height,
				playerVersusPlayerButtonDefault, playerVersusPlayerButtonHover,
				playerVersusPlayerButtonPress, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			mainFrame.getMenuScreen().stopBackgroundMusic();
			mainFrame.gameScreen(true, false);
		});
		// Setting the position of the button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.35 * height),
				0, 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		// Adding the player versus player button to the content panel.
		content.add(playerVersusPlayerButton, constraints);

		// Instantiating the player versus AI button.
		playerVersusAIButton = new OptionButton(false,
				0.3 * width, 0.2 * height,
				playerVersusAIButtonDefault, playerVersusAIButtonHover,
				playerVersusAIButtonPress, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			mainFrame.getMenuScreen().stopBackgroundMusic();
			mainFrame.gameScreen(true, true);
		});
		// Setting the position of the button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0,
				(int) (0.2 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		// Adding the player vs player button to the content panel.
		content.add(playerVersusAIButton, constraints);

		// Adding the content panel to the LayeredPane
		layeredPane.add(content, new GridBagConstraints());
		layeredPane.setLayer(content, 1);

		// Adding the LayeredPane to the main panel.
		this.add(layeredPane, new GridBagConstraints());
	}
}