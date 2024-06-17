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

	// Creating the LayeredPane, panel, and background label.
	private JLayeredPane layeredPane;
	private JLabel background;
	private JPanel content;

	private OptionButton playerVersusPlayerButton;

	// Path to the player vs player button default image.
	private ImageIcon playerVersusPlayerButtonDefault;
	// Path to the player vs player button hover image.
	private ImageIcon playerVersusPlayerButtonHover;
	// Path to the player vs player button pressed image.
	private ImageIcon playerVersusPlayerButtonPress;

	private OptionButton playerVersusAIButton;
	// Path to the player vs AI button default image.
	private ImageIcon playerVersusAIButtonDefault;
	// Path to the player vs AI button hover image.
	private ImageIcon playerVersusAIButtonHover;
	// Path to the player vs AI button pressed image.
	private ImageIcon playerVersusAIButtonPress;

	// Path to the background image.
	private final ImageIcon backgroundImage;

	/**
	 * Constructor, this method displays a frame where the player has the option
	 * to play againist another player or the AI.
	 * @param mainFrame
	 */
	public ModeSelectionScreen(Boggle mainFrame) {

		// Setting the dimensions of the Panel.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// GridBagConstraints.
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
		background.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(width, height,
				Image.SCALE_SMOOTH)));
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

		// Adding GridBagLayout for content panel.
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setOpaque(false);

		// Setting the dimensions of the content screen.
		content.setPreferredSize(new Dimension(width, height));

		// Instantiate player vs. player button images.
		playerVersusPlayerButtonDefault = new ImageIcon(getClass().
				getResource("assets/PlrVsPlrBtnDefault.png"));
		playerVersusPlayerButtonHover = new ImageIcon(getClass().
				getResource("assets/PlrVsPlrBtnHover.png"));
		playerVersusPlayerButtonPress = new ImageIcon(getClass().
				getResource("assets/PlrVsPlrBtnPress.png"));

		// Instantiate player vs. AI button images.
		playerVersusAIButtonDefault = new ImageIcon(getClass().
				getResource("assets/PlrVsAIBtnDefault.png"));
		playerVersusAIButtonHover = new ImageIcon(getClass().
				getResource("assets/PlrVsAIBtnHover.png"));
		playerVersusAIButtonPress = new ImageIcon(getClass().
				getResource("assets/PlrVsAIBtnPress.png"));

		// Creating the Player vs Player button.
		playerVersusPlayerButton = new OptionButton(0.3 * width, 0.2 * height, playerVersusPlayerButtonDefault, playerVersusPlayerButtonHover, playerVersusPlayerButtonPress, e -> {
			mainFrame.getMenuScreen().stopBgm();
			mainFrame.gameScreen(false);
		});
		// Setting the position of the button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.35 * height), 0, 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		// Adding the player vs player button to the content panel.
		content.add(playerVersusPlayerButton, constraints);

		// Creating the Player vs AI button using.
		playerVersusAIButton = new OptionButton(0.3 * width, 0.2 * height, playerVersusAIButtonDefault, playerVersusAIButtonHover, playerVersusAIButtonPress, e -> {
			mainFrame.getMenuScreen().stopBgm();
			mainFrame.gameScreen(true);
		});
		// Setting the position of the button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(0, 0, (int) (0.2 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		// Adding the player vs player button to the content panel.
		content.add(playerVersusAIButton, constraints);

		// Adding the content Label to the LayeredPane
		layeredPane.add(content, new GridBagConstraints());
		layeredPane.setLayer(content, 1);

		// Adding the LayeredPane to the main panel.
		this.add(layeredPane, new GridBagConstraints());
	}
}