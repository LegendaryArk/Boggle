/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class displays the end game screen after the game ends.
 */
public class EndGameScreen extends JPanel {
	// Layered pane for the end screen layers.
	private JLayeredPane layeredPane;
	// Label to hold background image display.
	private JLabel background;

	// Player 1 Win animation.
	private ImageIcon playerOneAnimation;
	// Player 1 Win screen.
	private ImageIcon playerOneImage;

	// Player 2 Win animation.
	private ImageIcon playerTwoAnimation;
	// Player 2 Win screen.
	private ImageIcon playerTwoImage;

	// AI win animation.
	private ImageIcon lambdaBoggleAnimation;
	// AI win screen.
	private ImageIcon lambdaBoggleImage;

	// Menu button.
	private final OptionButton menuButton;
	// Default menu button.
	private final ImageIcon menuButtonDefault;
	// Hover menu button.
	private final ImageIcon menuButtonHover;
	// Press menu button.
	private final ImageIcon menuButtonPress;

	// Play again button.
	private final OptionButton playAgainButton;
	// Default play again button.
	private final ImageIcon playAgainButtonDefault;
	// Hover play again button.
	private final ImageIcon playAgainButtonHover;
	// Press play again button.
	private final ImageIcon playAgainButtonPress;
	
	// Exit button.
	private final OptionButton exitButton;
	// Default exit button.
	private final ImageIcon exitButtonDefault;
	// Hover exit button.
	private final ImageIcon exitButtonHover;
	// Press exit button.
	private final ImageIcon exitButtonPress;
	
	// Width.
	private int width;
	// Height.
	private int height;

	/**
	 * Outputs a screen displaying either that Player 1 won, Player 2 won, or
	 * AI won.
	 * @param mainFrame main frame for game
	 * @param winner the winner of the game;
	 *                  1 - Player 1, 2 - Player 2,3 - λBoggle.
	 */
	public EndGameScreen(Boggle mainFrame, int winner) {
		// Get width and height of the screen.
		this.width = mainFrame.getScreenWidth();
		this.height = mainFrame.getScreenHeight();

		// The constraints used to position the components.
		GridBagConstraints constraints;
		
		// Set background colour, layout and dimensions of panel.
		this.setBackground(Color.BLACK);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));
		
		// Set background colour, layout and dimensions of layers.
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setLayout(new GridBagLayout());
		layeredPane.setPreferredSize(new Dimension(width, height));
		
		// Set Player 1 win animation.
		playerOneAnimation = new ImageIcon(
				getClass().getResource("assets/Plr1WinBg.gif"));
		playerOneAnimation = new ImageIcon(playerOneAnimation.getImage()
				.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// Set Player 1 win screen.
		playerOneImage = new ImageIcon(
				getClass().getResource("assets/Plr1WinBg.png"));
		playerOneImage = new ImageIcon(playerOneImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH));

		// Set Player 2 win animation.
		playerTwoAnimation = new ImageIcon(
				getClass().getResource("assets/Plr2WinBg.gif"));
		playerTwoAnimation = new ImageIcon(playerTwoAnimation.getImage()
				.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// Set Player 2 win screen.
		playerTwoImage = new ImageIcon(
				getClass().getResource("assets/Plr2WinBg.png"));
		playerTwoImage = new ImageIcon(playerTwoImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		
		// Set AI win animation.
		lambdaBoggleAnimation = new ImageIcon(
				getClass().getResource("assets/AIWinBg.gif"));
		lambdaBoggleAnimation = new ImageIcon(lambdaBoggleAnimation.getImage()
				.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		// Set AI win screen.
		lambdaBoggleImage = new ImageIcon(
				getClass().getResource("assets/AIWinBg.png"));
		lambdaBoggleImage = new ImageIcon(lambdaBoggleImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		
		// Set the background image based on the winner.
		background = new JLabel();
		// Enhanced switch statement
// https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
		switch (winner) {
			case 1 -> background.setIcon(playerOneAnimation); // Player 1 win.
			case 2 -> background.setIcon(playerTwoAnimation); // Player 2 win.
			case 3 -> background.setIcon(lambdaBoggleAnimation); // AI win.
		}

		// Set background colour and grid constraints.
		background.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 3;
		constraints.gridheight = 1;
		layeredPane.add(background, constraints);
		layeredPane.setLayer(background, 0);

		// Set menu button's colour and grid constraints.
		menuButtonDefault = new ImageIcon(getClass()
				.getResource("assets/EndMenuBtnDefault.png"));
		menuButtonHover = new ImageIcon(getClass()
				.getResource("assets/EndMenuBtnHover.png"));
		menuButtonPress = new ImageIcon(getClass()
				.getResource("assets/EndMenuBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		menuButton = new OptionButton(false, 0.2 * width, 0.1 * height,
				menuButtonDefault, menuButtonHover, menuButtonPress,
				e -> mainFrame.menuScreen());
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, (int) (0.1 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		layeredPane.add(menuButton, constraints);
		layeredPane.setLayer(menuButton, 1);

		// Set play again button's colour and grid constraints.
		playAgainButtonDefault = new ImageIcon(getClass()
				.getResource("assets/PlayAgainBtnDefault.png"));
		playAgainButtonHover = new ImageIcon(getClass()
				.getResource("assets/PlayAgainBtnHover.png"));
		playAgainButtonPress = new ImageIcon(getClass()
				.getResource("assets/PlayAgainBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		playAgainButton = new OptionButton(false, 0.2 * width, 0.1 * height,
				playAgainButtonDefault, playAgainButtonHover,
				playAgainButtonPress,
				e -> mainFrame.gameScreen(true, mainFrame.isAI()));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, (int) (0.1 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		layeredPane.add(playAgainButton, constraints);
		layeredPane.setLayer(playAgainButton, 1);

		// Set exit button's 's colour and grid constraints.
		exitButtonDefault = new ImageIcon(getClass()
				.getResource("assets/EndExitBtnDefault.png"));
		exitButtonHover = new ImageIcon(getClass()
				.getResource("assets/EndExitBtnHover.png"));
		exitButtonPress = new ImageIcon(getClass()
				.getResource("assets/EndExitBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		exitButton = new OptionButton(false, 0.2 * width, 0.1 * height,
				exitButtonDefault, exitButtonHover, exitButtonPress,
				e -> mainFrame.exitScreen());
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.insets = new Insets(0, 0, (int) (0.1 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		layeredPane.add(exitButton, constraints);
		layeredPane.setLayer(exitButton, 1);

		// Timer to switch from animation to screen for 2 seconds.
		Timer stopGif = new Timer(2000, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Set the background image based on the winner.
			// Enhanced switch statement
// https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
			switch (winner) {
				case 1 -> background.setIcon(playerOneImage);
				case 2 -> background.setIcon(playerTwoImage);
				case 3 -> background.setIcon(lambdaBoggleImage);
			}
		});

		// Star timer.
		stopGif.start();
		
		// Adding the GIF to LayeredPane.
		this.add(layeredPane, new GridBagConstraints());
	}
}