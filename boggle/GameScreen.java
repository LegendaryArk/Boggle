/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static javax.swing.BorderFactory.createEmptyBorder;

/**
 * This class contains the panel for the game screen
 */
public class GameScreen extends JPanel {
	// JLayered Pane for layers.
	private JLayeredPane layeredPane;
	// Holds the background image.
	private JLabel background;
	// Holds the buttons of the game screen.
	private JPanel content;
	
	// Title for player 1.
	private JLabel playerOneLabel;
	// Displays the timer for player 1.
	private JLabel playerOneTimeDisplay;
	// Displays player 1's points.
	private JLabel playerOnePointsDisplay;
	// Title for player 2.
	private JLabel playerTwoLabel;
	// Displays the timer for player 2.
	private JLabel playerTwoTimeDisplay;
	// Displays player 2's points.
	private JLabel playerTwoPointsDisplay;

	// Table that displays the words.
	private WordTable wordTable;
	// Background for the word list.
	private JPanel wordTableBackground;
	// The scroll pane for the word list.
	private JScrollPane wordTableScrollPane;

	// Board for the game.
	private Board board;
	// Displays the word that is being selected.
	private JLabel wordDisplay;
	// Background for the board.
	private JPanel boardBackground;

	// Pause Button.
	private OptionButton pauseButton;
	// Default Pause Button Icon.
	private ImageIcon pauseDefault;
	// Hover Pause Button Icon.
	private ImageIcon pauseHover;
	// Press Pause Button Icon.
	private ImageIcon pausePress;

	// Pass Button.
	private OptionButton passButton;
	// Default Pass Button Icon.
	private ImageIcon passDefault;
	// Hover Pass Button Icon.
	private ImageIcon passHover;
	// Press Pass Button Icon.
	private ImageIcon passPress;

	// Shake Button.
	private OptionButton shakeButton;
	// Default Shake Button Icon.
	private ImageIcon shakeDefault;
	// Hover Shake Button Icon
	private ImageIcon shakeHover;
	// Press Shake Button Icon.
	private ImageIcon shakePress;

	// Calm background music.
	private AudioInputStream calmBackgroundMusic;
	// Intense background music.
	private AudioInputStream intenseBackgroundMusic;
	// Clip of the current background music.
	private Clip backgroundMusicClip;

	// Main game frame.
	private Boggle mainFrame;

	/**
	 * Constructor
	 * @param mainFrame main game frame.
	 * @param isAI check if mode is player vs AI.
	 */
	public GameScreen(Boggle mainFrame, boolean isAI) {
		// Initialize instance variables.
		this.mainFrame = mainFrame;
		// Get dimensions of the screen.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// Constraints to set the position of the components.
		GridBagConstraints constraints;

		// Set background, layout and dimensions of game screen.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Set background, layout and dimensions of game screen.
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.black);
		layeredPane.setLayout(new GridBagLayout());
		layeredPane.setPreferredSize(new Dimension(width, height));

		// Set the background for the game screen.
		background = new JLabel();
		ImageIcon backgroundImage = new ImageIcon(getClass()
				.getResource("assets/GameScreenBg.png"));
		background.setIcon(new ImageIcon(backgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 6;
		layeredPane.add(background, constraints);
		layeredPane.setLayer(background, 0);

		// Set layout and dimensions of content.
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(width, height));

		// Set the dimensions and positions of the word display.
		wordDisplay = new JLabel();
		wordDisplay.setMinimumSize(
				new Dimension((int) (0.365 * width), (int) (0.09 * height)));
		wordDisplay.setPreferredSize(
				new Dimension((int) (0.365 * width), (int) (0.09 * height)));
		wordDisplay.setHorizontalTextPosition(JLabel.CENTER);
		wordDisplay.setVerticalTextPosition(JLabel.CENTER);
		wordDisplay.setHorizontalAlignment(JLabel.CENTER);
		wordDisplay.setVerticalAlignment(JLabel.CENTER);
		wordDisplay.setFont(new Font("Arial", Font.PLAIN, 40));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.0395 * height),
				(int) (0.059 * width), (int) (0.02 * height),
				(int) (0.05 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		content.add(wordDisplay, constraints);

		// Set the dimensions and positions of the word table.
		wordTableBackground = new JPanel();
		wordTableBackground.setLayout(new GridBagLayout());
		wordTableBackground.setMinimumSize(
				new Dimension((int) (0.22 * width), (int) (1.2 * height)));
		wordTableBackground.setPreferredSize(
				new Dimension((int) (0.22 * width), (int) (1.2 * height)));
		wordTableBackground.setOpaque(false);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridwidth = 1;
		constraints.gridheight = 6;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.17 * height),
				(int) (0.023 * width), 0, (int) (0.00625 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;

		// Set the dimensions and position of the word table scroll.
		wordTableScrollPane = new JScrollPane();
		wordTableScrollPane.setViewportView(wordTableBackground);
		wordTableScrollPane.setMinimumSize(
				new Dimension((int) (0.23475 * width), (int) (0.79 * height)));
		wordTableScrollPane.setPreferredSize(
				new Dimension((int) (0.23475 * width), (int) (0.79 * height)));
		wordTableScrollPane.setOpaque(false);
		wordTableScrollPane.getViewport().setOpaque(false);
		wordTableScrollPane.setBorder(createEmptyBorder());
		content.add(wordTableScrollPane, constraints);

		wordTable = new WordTable(mainFrame, wordTableBackground,
				wordTableScrollPane);

		// Set the dimensions and positions of the player 1 title.
		playerOneLabel = new JLabel();
		playerOneLabel.setMinimumSize(
				new Dimension((int) (0.22 * width), (int) (0.09 * height)));
		playerOneLabel.setPreferredSize(
				new Dimension((int) (0.22 * width), (int) (0.09 * height)));
		playerOneLabel.setHorizontalTextPosition(JLabel.CENTER);
		playerOneLabel.setHorizontalAlignment(JLabel.CENTER);
		playerOneLabel.setText("Player 1");
		playerOneLabel.setFont(new Font("Verdana", Font.BOLD, 50));
		playerOneLabel.setForeground(Color.WHITE);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.ABOVE_BASELINE;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(
				(int) (0.08 * height), 0, 0, (int) (0.01 * width));
		content.add(playerOneLabel, constraints);

		// Set the dimensions and positions of the time display for player 1.
		playerOneTimeDisplay = new JLabel();
		playerOneTimeDisplay.setMinimumSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerOneTimeDisplay.setPreferredSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerOneTimeDisplay.setHorizontalAlignment(JLabel.CENTER);
		playerOneTimeDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new Insets((int) (0.075 * height),
				(int) (0.016 * width), 0, (int) (0.007 * width));
		content.add(playerOneTimeDisplay, constraints);

		// Set the dimensions and positions of the points display for player 1.
		playerOnePointsDisplay = new JLabel();
		playerOnePointsDisplay.setMinimumSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerOnePointsDisplay.setPreferredSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerOnePointsDisplay.setHorizontalAlignment(JLabel.CENTER);
		playerOnePointsDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 4;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new Insets(
				(int) (0.075 * height), 0, 0, (int) (0.024 * width));
		content.add(playerOnePointsDisplay, constraints);

		// Set the dimensions and positions of the player 2 title.
		playerTwoLabel = new JLabel();
		playerTwoLabel.setMinimumSize(
				new Dimension((int) (0.22 * width), (int) (0.09 * height)));
		playerTwoLabel.setPreferredSize(
				new Dimension((int) (0.22 * width), (int) (0.09 * height)));
		playerTwoLabel.setHorizontalTextPosition(JLabel.CENTER);
		playerTwoLabel.setHorizontalAlignment(JLabel.CENTER);
		// Uses shorthand if-else blocks (ternary operator).
		// https://www.w3schools.com/java/java_conditions_shorthand.asp.
		playerTwoLabel.setText(isAI ? "λBoggle" : "Player 2");
		playerTwoLabel.setFont(new Font("Verdana", Font.BOLD, 50));
		playerTwoLabel.setForeground(Color.WHITE);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.ABOVE_BASELINE;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new Insets(
				(int) (0.05 * height), 0, 0, (int) (0.01 * width));
		content.add(playerTwoLabel, constraints);

		// Set dimensions and positions for player 2 timer display.
		playerTwoTimeDisplay = new JLabel();
		playerTwoTimeDisplay.setMinimumSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerTwoTimeDisplay.setPreferredSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerTwoTimeDisplay.setHorizontalAlignment(JLabel.CENTER);
		playerTwoTimeDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new Insets((int) (0.086 * height),
				(int) (0.016 * width), 0, (int) (0.007 * width));
		content.add(playerTwoTimeDisplay, constraints);

		// Set dimensions and positions for player 2 points display.
		playerTwoPointsDisplay = new JLabel();
		playerTwoPointsDisplay.setMinimumSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerTwoPointsDisplay.setPreferredSize(new Dimension(
				(int) (0.109375 * width), (int) (0.111 * height)));
		playerTwoPointsDisplay.setHorizontalAlignment(JLabel.CENTER);
		playerTwoPointsDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.weightx = 1;
		constraints.weighty = 0;
		constraints.insets = new Insets(
				(int) (0.086 * height), 0, 0, (int) (0.024 * width));
		content.add(playerTwoPointsDisplay, constraints);

		// Instantiate pass button images.
		passDefault = new ImageIcon(getClass()
				.getResource("assets/PassBtnDefault.png"));
		passHover = new ImageIcon(getClass()
				.getResource("assets/PassBtnHover.png"));
		passPress = new ImageIcon(getClass()
				.getResource("assets/PassBtnPress.png"));

		// Instantiating the pass button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		passButton = new OptionButton(false, 0.05 * width, 0.05 * width,
				passDefault, passHover, passPress, e -> board.switchTurn());
		// Position the pass button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.gridx = 3;
		constraints.gridy = 4;
		constraints.insets = new Insets(
				(int) (0.055 * height), 0, 0, (int) (0.025 * width));
		constraints.weightx = 1;
		constraints.weighty = 0;
		content.add(passButton, constraints);

		// Instantiate pause button images.
		pauseDefault = new ImageIcon(getClass()
				.getResource("assets/PauseBtnDefault.png"));
		pauseHover = new ImageIcon(getClass()
				.getResource("assets/PauseBtnHover.png"));
		pausePress = new ImageIcon(getClass()
				.getResource("assets/PauseBtnPress.png"));

		// Instantiating the pause button.
		pauseButton = new OptionButton(false, 0.05 * width, 0.05 * width,
				pauseDefault, pauseHover, pausePress, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			board.pause();
			mainFrame.pauseOverlay();
		});
		// Position the pause button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.insets = new Insets(
				0, 0, (int) (0.04 * height), (int) (0.025 * width));
		constraints.weightx = 1;
		constraints.weighty = 0;
		// Adding the pause button to the content panel.
		content.add(pauseButton, constraints);

		// Instantiate shake button images.
		shakeDefault = new ImageIcon(getClass()
				.getResource("assets/ShakeBtnDefault.png"));
		shakeHover = new ImageIcon(getClass()
				.getResource("assets/ShakeBtnHover.png"));
		shakePress = new ImageIcon(getClass()
				.getResource("assets/ShakeBtnPress.png"));

		// Instantiating the shake button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		shakeButton = new OptionButton(false, 0.05 * width, 0.05 * width,
				shakeDefault, shakeHover, shakePress, e -> board.shuffle());
		// Position the shake button using GridBagLayout.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.gridx = 4;
		constraints.gridy = 4;
		constraints.insets = new Insets(
				(int) (0.055 * height), (int) (0.016 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 0;
		// Adding the shake button to the content panel.
		content.add(shakeButton, constraints);

		// Setting the dimensions of the boardBackground panel.
		boardBackground = new JPanel();
		boardBackground.setMinimumSize(
				new Dimension((int) (0.43 * width), (int) (0.43 * width)));
		boardBackground.setPreferredSize(
				new Dimension((int) (0.43 * width), (int) (0.43 * width)));
		boardBackground.setOpaque(false);
		// Positioning the boardBackground panel using GridBagConstraints.
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.ABOVE_BASELINE;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridheight = 5;
		constraints.insets = new Insets(
				0, (int) (0.017 * width), (int) (0.06 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		// Adding boardBackground to the content panel.
		content.add(boardBackground, constraints);

		// Instantiates the board.
		board = new Board(mainFrame, boardBackground, wordDisplay, wordTable,
				playerOneLabel, playerOnePointsDisplay, playerOneTimeDisplay,
				isAI, playerTwoLabel, playerTwoPointsDisplay,
				playerTwoTimeDisplay, passButton);

		// Adding the content Label to the LayeredPane.
		layeredPane.add(content, new GridBagConstraints());
		// Setting content Label as the 1st layer on the LayeredPane.
		layeredPane.setLayer(content, 1);

		// Adding the LayeredPane to the main screen.
		this.add(layeredPane, new GridBagConstraints());

		pauseGame();
	}

	/**
	 * This method gets the board.
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * This method will pause the game.
	 */
	public void pauseGame() {
		board.pause();
	}

	/**
	 * This method will resume game after pausing.
	 */
	public void resumeGame() {
		board.resume();
	}

	/**
	 * This method will reset the game.
	 * @param isAI to set game.
	 */
	public void resetGame(boolean isAI) {
		// Set the player text based on the game mode.
		// Uses shorthand if-else blocks (ternary operator).
		// https://www.w3schools.com/java/java_conditions_shorthand.asp.
		playerTwoLabel.setText(isAI ? "λBoggle" : "Player 2");
		// Clear the word list.
		wordTable.clear();
		board.setTurn(0);
		// Set the game mode of the board.
		board.setAI(isAI);
	}

	/**
	 * This method will play the background music.
	 */
	public void startBackgroundMusic() {
		try {
			// Set the background music of calm and intense.
			calmBackgroundMusic = AudioSystem.getAudioInputStream(getClass()
					.getResource("assets/CalmBGM.wav"));
			intenseBackgroundMusic = AudioSystem.getAudioInputStream(getClass()
					.getResource("assets/IntenseBGM.wav"));
			// Play the background music.
			backgroundMusicClip = AudioSystem.getClip();
			switch (mainFrame.getBackgroundMusicType()) {
				case 0:
					if (isAI()) {
						// Set calm bgm music if difficulty is easy or medium.
						if (mainFrame.getAIDifficulty() <= 1) {
							backgroundMusicClip.open(calmBackgroundMusic);
						}
						// Set intense bgm if difficulty is hard or impossible.
						else {
							backgroundMusicClip.open(intenseBackgroundMusic);
						}
					}
					else {
						// Handle other exception as intense bgm.
						backgroundMusicClip.open(intenseBackgroundMusic);
					}
					break;
				case 1:
					// Play the calm background music.
					backgroundMusicClip.open(calmBackgroundMusic);
					break;
				case 2:
					// Play the intense background music.
					backgroundMusicClip.open(intenseBackgroundMusic);
					break;
			}
		} catch (Exception e) {
			// Handle if unable to open the bgm.
			System.err.println("Error: Unable to start BGM");
			e.printStackTrace();
		}
		// Loop the bgm indefinitely.
		backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * This method will stop the background music.
	 */
	public void stopBackgroundMusic() {
		// Stop and close background music clip.
		backgroundMusicClip.stop();
		backgroundMusicClip.close();
		try {
			// Close calm and intense bgm.
			calmBackgroundMusic.close();
			intenseBackgroundMusic.close();
		} catch (IOException e) {
			// Handle if unable to stop bgm.
			System.err.println("Error: Unable to reset BGM");
			e.printStackTrace();
		}
	}

	/**
	 * This method checks the game mode of the board.
	 * @return true if AI, false if player vs player.
	 */
	public boolean isAI() {
		return board.isAI();
	}
}