/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is the interactive game screen board
 * implements MouseListener
 */
public class Board implements MouseListener {
	// 2D array for the dice objects.
	private Dice[][] dice;
	// Layered pane for managing multiple elements.
	private JLayeredPane layeredPane;
	// Panel for the dice objects.
	private JPanel grid;
	// Panel for the lines in the board.
	private JPanel lineGrid;
	// Lines for the different connections on the board.
	private JLabel[][][][] lines;
	// Dictionary.
	private WordList dictionary;
	// Words that have been entered.
	private WordList wordsFound;
	// Combinations of dice faces.
	private final String[][] COMBINATIONS = {
			{"AAAFRS", "AEEGMU", "CEIILT", "DHHNOT", "FIPRSY"},
			{"AAEEEE", "AEGMNN", "CEILPT", "DHLNOR", "GORRVW"},
			{"AAFIRS", "AFIRSY", "CEIPST", "EIIITT", "HIPRRY"},
			{"ADENNN", "BJKQXZ", "DDLNOR", "EMOTTT", "NOOTUW"},
			{"AEEEEM", "CCNSTW", "DHHLOR", "ENSSSU", "OOOTTU"}
	};

	// Horizontal Connection Default Icon.
	private ImageIcon horizontalDefaultLine = new ImageIcon(getClass()
			.getResource("assets/DieDefaultHorizontal.png"));
	// Vertical Connection Default Icon.
	private ImageIcon verticalDefaultLine = new ImageIcon(getClass()
			.getResource("assets/DieDefaultVertical.png"));
	// Left Diagonal Connection Default Icon.
	private ImageIcon diagonalLeftDefaultLine = new ImageIcon(getClass()
			.getResource("assets/DieDefaultDiagonalLeft.png"));
	// Right Diagonal Connection Default Icon.
	private ImageIcon diagonalRightDefaultLine = new ImageIcon(getClass()
			.getResource("assets/DieDefaultDiagonalRight.png"));

	// Horizontal Hold Connection.
	private ImageIcon horizontalHoldLine = new ImageIcon(getClass()
			.getResource("assets/DieHoldHorizontal.png"));
	// Vertical Hold Connection.
	private ImageIcon verticalHoldLine = new ImageIcon(getClass()
			.getResource("assets/DieHoldVertical.png"));
	// Diagonal Left Hold Connection.
	private ImageIcon diagonalLeftHoldLine = new ImageIcon(getClass()
			.getResource("assets/DieHoldDiagonalLeft.png"));
	// Diagonal Right Hold Connection.
	private ImageIcon diagonalRightHoldLine = new ImageIcon(getClass()
			.getResource("assets/DieHoldDiagonalRight.png"));
	// Diagonal Right Left Hold Connection.
	private ImageIcon diagonalRightLeftHoldLine = new ImageIcon(getClass()
			.getResource("assets/DieHoldDiagonalRightLeft.png"));

	// Horizontal New Connection.
	private ImageIcon horizontalNewLine = new ImageIcon(getClass()
			.getResource("assets/DieNewHorizontal.png"));
	// Vertical New Connection.
	private ImageIcon verticalNewLine = new ImageIcon(getClass()
			.getResource("assets/DieNewVertical.png"));
	// Diagonal Left New Connection.
	private ImageIcon diagonalLeftNewLine = new ImageIcon(getClass()
			.getResource("assets/DieNewDiagonalLeft.png"));
	// Diagonal Right New Connection.
	private ImageIcon diagonalRightNewLine = new ImageIcon(getClass()
			.getResource("assets/DieNewDiagonalRight.png"));
	// Diagonal Right Left New Connection.
	private ImageIcon diagonalRightLeftNewLine = new ImageIcon(getClass()
			.getResource("assets/DieNewDiagonalRightLeft.png"));

	// Horizontal Old Connection.
	private ImageIcon horizontalOldLine = new ImageIcon(getClass()
			.getResource("assets/DieOldHorizontal.png"));
	// Vertical Old Connection.
	private ImageIcon verticalOldLine = new ImageIcon(getClass()
			.getResource("assets/DieOldVertical.png"));
	// Diagonal Left Old Connection.
	private ImageIcon diagonalLeftOldLine = new ImageIcon(getClass()
			.getResource("assets/DieOldDiagonalLeft.png"));
	// Diagonal Right Old Connection.

	private ImageIcon diagonalRightOldLine = new ImageIcon(getClass()
			.getResource("assets/DieOldDiagonalRight.png"));
	// Diagonal Right Left Old Connection.
	private ImageIcon diagonalRightLeftOldLine = new ImageIcon(getClass()
			.getResource("assets/DieOldDiagonalRightLeft.png"));

	// Currently selected word on the board.
	private String wordSelected;

	// Indicates whether a letter has been selected.
	private boolean startedSelection;
	// Coordinates of the previously selected dice on the board.
	private int px;
	private int py;

	// Main game frame
	private Boggle mainFrame;
	// Displays the current word being formed
	private JLabel wordDisplay;

	// Icon for the default word.
	private ImageIcon wordDisplayDefault;
	// Icon for the new word.
	private ImageIcon wordDisplayNew;
	// Icon for the old word.
	private ImageIcon wordDisplayOld;
	// Table to display all the words.
	private WordTable wordTable;
	// Button to skip a turn
	private OptionButton passButton;

	// Player 2 title.
	private JLabel playerTwoLabel;
	// Player 2 total points display.
	private JLabel playerTwoPointsDisplay;
	// Player 2 time remaining display.
	private JLabel playerTwoTimeDisplay;

	// Player object representing player 1.
	private Player playerOne;
	// Player object representing player 2, null if playing against AI.
	private Player playerTwo;
	// AI object representing the AI.
	private AI ai;
	// Current player's turn.
	private int playerTurn;
	// Indicator if current game is player vs AI.
	private boolean isAI;

	// Audio file for select dice sound effect.
	private AudioInputStream selectDiceSoundEffect;
	// Clip for selecting dice.
	private Clip selectDiceClip;
	// Audio file for valid word sound effect.
	private AudioInputStream validWordSoundEffect;
	// Clip for valid word.
	private Clip validWordClip;

	/**
	 * Constructor
	 * @param mainFrame main game frame.
	 * @param background background of the game.
	 * @param wordDisplay display of the current word.
	 * @param wordTable list that contains all possible words.
	 * @param playerOneLabel player 1 title.
	 * @param playerOnePointsDisplay player 1 points display.
	 * @param playerOneTimeDisplay player 1 time display.
	 * @param isAI AI if game is player vs AI.
	 * @param playerTwoLabel player 2 title.
	 * @param playerTwoPointsDisplay player 2 points display.
	 * @param playerTwoTimeDisplay player 2 time display.
	 * @param passButton button to skip turn.
	 */
	public Board(Boggle mainFrame, JPanel background, JLabel wordDisplay,
	             WordTable wordTable, JLabel playerOneLabel, JLabel playerOnePointsDisplay,
	             JLabel playerOneTimeDisplay, boolean isAI, JLabel playerTwoLabel,
	             JLabel playerTwoPointsDisplay, JLabel playerTwoTimeDisplay,
	             OptionButton passButton) {
		// Set the game mode based on if player vs AI is chosen.
		this.isAI = isAI;
		// Set pass button.
		this.passButton = passButton;
		// Set player 2 label, points and time.
		this.playerTwoLabel = playerTwoLabel;
		this.playerTwoPointsDisplay = playerTwoPointsDisplay;
		this.playerTwoTimeDisplay = playerTwoTimeDisplay;

		// Initialize variables.
		wordSelected = "";
		startedSelection = false;
		px = -2;
		py = -2;
		playerTwo = null;
		ai = null;
		playerTurn = 0;

		// Initialize player 1 with their title, points and clock.
		this.playerOne = new Player(playerOneLabel, playerOnePointsDisplay,
				new Clock(playerOneTimeDisplay, mainFrame.getInitialTime(), this));
		if (!isAI) {
			// Initialize player 2 with their title. points and clock.
			this.playerTwo = new Player(playerTwoLabel, playerTwoPointsDisplay,
					new Clock(playerTwoTimeDisplay, mainFrame.getInitialTime(),
							this));
		} else {
			// Initialize AI with their difficulty,title, points and clock.
			this.ai = new AI(this, mainFrame.getAIDifficulty(),
					mainFrame.getMinimumWordLength(), playerTwoLabel,
					playerTwoPointsDisplay, new Clock(playerTwoTimeDisplay,
					mainFrame.getInitialTime(), this));
		}
		// Display the board based on the game mode selected.
		setup(mainFrame, background, wordDisplay, wordTable);

		// Start with player 1's turn.
		playerOne.startTurn();
	}

	/**
	 * This method setups the game screen.
	 * @param mainFrame main game frame.
	 * @param background background of the game.
	 * @param wordDisplay display of the current word.
	 * @param wordTable list that contains all possible words.
	 */
	private void setup(Boggle mainFrame, JPanel background, JLabel wordDisplay,
	                   WordTable wordTable) {
		// Set frame, background, word display and list
		this.mainFrame = mainFrame;
		this.wordDisplay = wordDisplay;
		this.wordTable = wordTable;

		// Scale the word display label to fit size.
		int wordDisplayWidth = wordDisplay.getMinimumSize().width;
		int wordDisplayHeight = wordDisplay.getMinimumSize().height;
		wordDisplayDefault = new ImageIcon(getClass()
				.getResource("assets/WordDisplayDefault.png"));
		wordDisplayDefault = new ImageIcon(wordDisplayDefault.getImage()
				.getScaledInstance(wordDisplayWidth, wordDisplayHeight,
						Image.SCALE_SMOOTH));
		wordDisplayNew = new ImageIcon(getClass()
				.getResource("assets/WordDisplayNew.png"));
		wordDisplayNew = new ImageIcon(wordDisplayNew.getImage()
				.getScaledInstance(wordDisplayWidth, wordDisplayHeight,
						Image.SCALE_SMOOTH));
		wordDisplayOld = new ImageIcon(getClass()
				.getResource("assets/WordDisplayFound.png"));
		wordDisplayOld = new ImageIcon(wordDisplayOld.getImage()
				.getScaledInstance(wordDisplayWidth, wordDisplayHeight,
						Image.SCALE_SMOOTH));
		wordDisplay.setIcon(wordDisplayDefault);

		GridBagConstraints constraints;

		// Configure background layout and add the layers to GridBagLayout.
		background.setLayout(new GridBagLayout());
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(background.getPreferredSize());
		layeredPane.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_START;
		background.add(layeredPane, new GridBagConstraints());

		// Set the layout for the grid.
		grid = new JPanel();
		grid.setLayout(new GridLayout(5, 5, 5, 5));
		grid.setMinimumSize(background.getMinimumSize());
		grid.setPreferredSize(background.getPreferredSize());
		grid.setMaximumSize(background.getPreferredSize());
		grid.setOpaque(false);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 5;
		layeredPane.add(grid, constraints);
		layeredPane.setLayer(grid, 1);

		// Set the layout for the lines in the board.
		lineGrid = new JPanel();
		lineGrid.setLayout(new GridBagLayout());
		lineGrid.setMinimumSize(background.getMinimumSize());
		lineGrid.setPreferredSize(background.getPreferredSize());
		lineGrid.setMaximumSize(background.getPreferredSize());
		lineGrid.setOpaque(false);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
		constraints.gridheight = 5;
		layeredPane.add(lineGrid, constraints);
		layeredPane.setLayer(lineGrid, 0);

		// Set the size of the dice based on width and height.
		int diceWidth = (background.getPreferredSize().width - 40) / 5;
		int diceHeight = (background.getPreferredSize().height - 40) / 5;

		// Scale and set default line image.
		horizontalDefaultLine = new ImageIcon(horizontalDefaultLine.getImage()
				.getScaledInstance(diceWidth, (int) (0.3 * diceHeight),
						Image.SCALE_SMOOTH));
		verticalDefaultLine = new ImageIcon(verticalDefaultLine.getImage()
				.getScaledInstance((int) (0.3 * diceWidth), diceHeight,
						Image.SCALE_SMOOTH));
		diagonalLeftDefaultLine = new ImageIcon(diagonalLeftDefaultLine
				.getImage().getScaledInstance(diceWidth, diceHeight,
						Image.SCALE_SMOOTH));
		diagonalRightDefaultLine = new ImageIcon(diagonalRightDefaultLine
				.getImage().getScaledInstance(diceWidth, diceHeight,
						Image.SCALE_SMOOTH));

		// Scale and set hold line image.
		horizontalHoldLine = new ImageIcon(horizontalHoldLine.getImage()
				.getScaledInstance(diceWidth, (int) (0.3 * diceHeight),
						Image.SCALE_SMOOTH));
		verticalHoldLine = new ImageIcon(verticalHoldLine.getImage()
				.getScaledInstance((int) (0.3 * diceWidth), diceHeight,
						Image.SCALE_SMOOTH));
		diagonalLeftHoldLine = new ImageIcon(diagonalLeftHoldLine.getImage()
				.getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightHoldLine = new ImageIcon(diagonalRightHoldLine.getImage()
				.getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftHoldLine = new ImageIcon(diagonalRightLeftHoldLine
				.getImage().getScaledInstance(diceWidth, diceHeight,
						Image.SCALE_SMOOTH));

		// Scale and set new line image.
		horizontalNewLine = new ImageIcon(horizontalNewLine.getImage()
				.getScaledInstance(diceWidth, (int) (0.3 * diceHeight),
						Image.SCALE_SMOOTH));
		verticalNewLine = new ImageIcon(verticalNewLine.getImage()
				.getScaledInstance((int) (0.3 * diceWidth), diceHeight,
						Image.SCALE_SMOOTH));
		diagonalLeftNewLine = new ImageIcon(diagonalLeftNewLine.getImage()
				.getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightNewLine = new ImageIcon(diagonalRightNewLine.getImage()
				.getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftNewLine = new ImageIcon(diagonalRightLeftNewLine
				.getImage().getScaledInstance(diceWidth, diceHeight,
						Image.SCALE_SMOOTH));

		// Scale and set old line image.
		horizontalOldLine = new ImageIcon(horizontalOldLine.getImage()
				.getScaledInstance(diceWidth, (int) (0.3 * diceHeight),
						Image.SCALE_SMOOTH));
		verticalOldLine = new ImageIcon(verticalOldLine.getImage()
				.getScaledInstance((int) (0.3 * diceWidth), diceHeight,
						Image.SCALE_SMOOTH));
		diagonalLeftOldLine = new ImageIcon(diagonalLeftOldLine.getImage()
				.getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightOldLine = new ImageIcon(diagonalRightOldLine.getImage()
				.getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftOldLine = new ImageIcon(diagonalRightLeftOldLine
				.getImage().getScaledInstance(diceWidth, diceHeight,
						Image.SCALE_SMOOTH));

		// Set up the dice and connections.
		dice = new Dice[5][5];
		lines = new JLabel[5][5][3][3];
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				// Set up the dice grid.
				dice[x][y] = new Dice(COMBINATIONS[x][y].toCharArray(),
						diceWidth, diceHeight);
				grid.add(dice[x][y]);
				dice[x][y].getCenter().addMouseListener(this);

				// Set up the connections.
				for (int dx = -1; dx <= 1; dx++) {
					for (int dy = -1; dy <= 1; dy++) {
						if (x + dx < 0 || x + dx > 4 || y + dy < 0
								|| y + dy > 4) {
							// Out of bounds.
							continue;
						}
						if (dx == 0 && dy == 0) {
							// Same dice.
							continue;
						}

						JLabel line = new JLabel();
						if (dy == 0) { // Horizontal line.
							line.setIcon(horizontalDefaultLine);
						} else if (dx == 0) { // Vertical line.
							line.setIcon(verticalDefaultLine);
						} else if (dx == dy) { // Right Diagonal.
							line.setIcon(diagonalRightDefaultLine);
						} else { // Left Diagonal.
							line.setIcon(diagonalLeftDefaultLine);
						}
						constraints = new GridBagConstraints();
						// Uses shorthand if-else blocks (ternary operator).
// https://www.w3schools.com/java/java_conditions_shorthand.asp.
						constraints.gridx = dx < 0 ? x + dx : x;
						constraints.gridy = dy < 0 ? y + dy : y;
						constraints.gridwidth = dx == 0 ? 1 : 2;
						constraints.gridheight = dy == 0 ? 1 : 2;
						constraints.weightx = 1;
						constraints.weighty = 1;
						lineGrid.add(line, constraints);
						lines[x][y][dx + 1][dy + 1] = line;
					}
				}
			}
		}

		dictionary = new WordList();
		initializeDictionary();

		wordsFound = new WordList();
	}

	/**
	 * This method initializes the dictionary array
	 */
	public void initializeDictionary() {
		try {
			// Read lines from dictionary.txt.
			Scanner sc = new Scanner(
					new File("src/Boggle/boggle/resources/dictionary.txt"));
			while (sc.hasNextLine()) {
				// Add each word and upper case them.
				dictionary.add(sc.nextLine().toUpperCase());
			}
			// Close scanner.
			sc.close();
		} catch (FileNotFoundException e) {
			// Handle if dictionary is not found.
			System.err.println(
					"Fatal error: Unable to find and load dictionary");
			e.printStackTrace();
			// Exit game because the game cannot be played without the
			// dictionary.
			System.exit(0);
		}
	}

	/**
	 * This method gets the dice grid.
	 * @return the dice.
	 */
	public Dice[][] getDiceGrid() {
		return dice;
	}

	/**
	 * This method gets the words found.
	 * @return the words found.
	 */
	public WordList getWordsFound() {
		return wordsFound;
	}

	/**
	 * This method gets the word display.
	 * @return the word display.
	 */
	public JLabel getWordDisplay() {
		return wordDisplay;
	}

	/**
	 * This method gets if the game mode is player vs AI
	 * @return isAI
	 */
	public boolean isAI() {
		return isAI;
	}

	/**
	 * This method sets the AI by replacing player 2
	 * @param isAI
	 */
	public void setAI(boolean isAI) {
		// Reset the board.
		resetBoard();

		this.isAI = isAI;
		if (!isAI) {
			// Initialize player 2 if game mode is not player vs player.
			this.playerTwo = new Player(playerTwoLabel, playerTwoPointsDisplay,
					new Clock(playerTwoTimeDisplay, mainFrame.getInitialTime(),
							this));
			this.ai = null;
		} else {
			// Initialize AI if game mode is player vs AI.
			this.ai = new AI(this, mainFrame.getAIDifficulty(),
					mainFrame.getMinimumWordLength(), playerTwoLabel,
					playerTwoPointsDisplay, new Clock(playerTwoTimeDisplay,
					mainFrame.getInitialTime(), this));
			this.playerTwo = null;
		}
	}

	/**
	 * This method sets the turn.
	 * @param turn which player/AI goes next.
	 */
	public void setTurn(int turn) {
		playerTurn = turn;
	}

	/**
	 * This method will reset the board.
	 */
	public void resetBoard() {
		// Reset the player 1 timer.
		playerOne.getTimer().reset(mainFrame.getInitialTime());
		// Reset the points for player 1.
		playerOne.resetPoints();
		if (!isAI) {
			// Reset player 2 timer.
			playerTwo.getTimer().reset(mainFrame.getInitialTime());
			// Reset player 2 points.
			playerTwo.resetPoints();
		} else {
			// Reset AI timer.
			ai.getTimer().reset(mainFrame.getInitialTime());
			// Reset AI points
			ai.resetPoints();
		}
		// Reset words found.
		wordsFound = new WordList();
		// Shake the board.
		shuffle();
	}

	/**
	 * This method checks if the word is valid.
	 * @param s the current word.
	 * @return the word in the dictionary.
	 */
	public boolean isValidWord(String s) {
		return dictionary.contains(s) && s.length() >=
				mainFrame.getMinimumWordLength();
	}

	/**
	 * This method checks if the word is new.
	 * @param s the current word.
	 * @return the word.
	 */
	public boolean isNewWord(String s) {
		return !wordsFound.contains(s);
	}

	/**
	 * This method switches the turn
	 */
	public void switchTurn() {
		playerTurn = ++playerTurn % 2; // Switch between 0 and 1.
		switch (playerTurn) {
			case 0:
				if (!isAI) {
					// End player 2 turn.
					playerTwo.endTurn();
					// Increment player 2 timer.
					playerTwo.getTimer().increment(mainFrame.getTimeIncrement());
				} else {
					// End AI turn.
					ai.endTurn();
					// Increment AI time.
					ai.getTimer().increment(mainFrame.getTimeIncrement());
					// Set pass button visible.
					passButton.setVisible(true);
				}
				System.out.println("Player 1's Turn");
				// Start player 1's turn.
				playerOne.startTurn();
				break;
			case 1:
				// End player 1 turn.
				playerOne.endTurn();
				// Increment player 1 timer.
				playerOne.getTimer().increment(mainFrame.getTimeIncrement());
				if (!isAI) {
					// Start Player 2's turn.
					System.out.println("Player 2's Turn");
					playerTwo.startTurn();
				} else {
					// Remove pass button from game screen.
					passButton.setVisible(false);
					// Start AI's turn.
					System.out.println("Lambda Boggle's Turn");
					ai.startTurn();
					// Set a timer for AI's turn.
					Timer waitUntil = new Timer(100, e -> {
						// Wait until the AI has found a word.
						if (!ai.isWordFound()) return;
						// Switch the turn to player 1.
						switchTurn();
						// Stops looping.
						((Timer) e.getSource()).stop();
					});
					waitUntil.start();
					return;
				}
				break;
		}
	}

	/**
	 * This method will pause the game.
	 */
	public void pause() {
		// Pause player 1 timer.
		playerOne.getTimer().pause();
		if (!isAI) {
			// Pause player 2 timer.
			playerTwo.getTimer().pause();
		} else {
			// Pause AI timer.
			ai.getTimer().pause();
		}
	}

	/**
	 * This method will resume the game after pausing.
	 */
	public void resume() {
		// Enhanced switch statement
// https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
		switch (playerTurn) {
			// Start player 1 timer.
			case 0 -> playerOne.getTimer().start();
			case 1 -> {
				if (!isAI) {
					// Start Player 2 timer.
					playerTwo.getTimer().start();
				} else {
					// Start AI timer.
					ai.getTimer().start();
				}
			}
		}
	}

	/**
	 * This method shuffles the board.
	 */
	public void shuffle() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Random rand = new Random();
				// Set a random x coordinate.
				int x = rand.nextInt(5);
				// Set a random y coordinate.
				int y = rand.nextInt(5);
				// Set the new character at the x and y coordinate.
				char[] tmp = dice[x][y].getFaces();
				// Swap the dices with the previous location.
				dice[x][y].setFaces(dice[i][j].getFaces());
				dice[i][j].setFaces(tmp);
				dice[i][j].roll();
				dice[x][y].roll();
			}
		}
	}

	/**
	 * This method draws the connection between two dice
	 * @param x The x-coordinate of the first dice.
	 * @param y The y-coordinate of the first dice.
	 * @param dx The x-coordinate of the second dice relative to the first
	 *              dice.
	 *           Either -1, 0, or 1.
	 * @param dy The y-coordinate of the second dice relative to the first
	 *              dice.
	 *           Either -1, 0, or 1.
	 * @param state The state of the connection
	 *                 0 for default, 1 for hold, 2 for new, 3 for old
	 */
	public void connect(int x, int y, int dx, int dy, int state) {
		// Skip if there is no line at position
		if (lines[x][y][dx + 1][dy + 1] == null) return;

		// Set the current icons to the default icon.
		ImageIcon hori = horizontalDefaultLine;
		ImageIcon vert = verticalDefaultLine;
		ImageIcon diagRight = diagonalRightDefaultLine;
		ImageIcon diagLeft = diagonalLeftDefaultLine;
		ImageIcon diagRightLeft = diagonalRightDefaultLine;
		ImageIcon prevDiagRight = diagonalRightDefaultLine;

		// Switch icon based on state.
		switch (state) {
			case 1:
				// Holding down a selected word.
				hori = horizontalHoldLine;
				vert = verticalHoldLine;
				diagRight = diagonalRightHoldLine;
				diagLeft = diagonalLeftHoldLine;
				diagRightLeft = diagonalRightLeftHoldLine;
				prevDiagRight = diagonalRightDefaultLine;
				break;
			case 2:
				// The word is a new word.
				hori = horizontalNewLine;
				vert = verticalNewLine;
				diagRight = diagonalRightNewLine;
				diagLeft = diagonalLeftNewLine;
				diagRightLeft = diagonalRightLeftNewLine;
				prevDiagRight = diagonalRightLeftHoldLine;
				break;
			case 3:
				// The word is an old word
				hori = horizontalOldLine;
				vert = verticalOldLine;
				diagRight = diagonalRightOldLine;
				diagLeft = diagonalLeftOldLine;
				diagRightLeft = diagonalRightLeftOldLine;
				prevDiagRight = diagonalRightLeftHoldLine;
				break;
		}
		// Check if change is horizontal.
		if (dy == 0) {
			// Set icons of the lines.
			lines[x][y][dx + 1][dy + 1].setIcon(hori);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(hori);
		}
		// Check if change is vertical.
		else if (dx == 0) {
			// Set icons of the lines.
			lines[x][y][dx + 1][dy + 1].setIcon(vert);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(vert);
		}
		// Check if change is diagonal.
		else if (dx == dy) {
			// Set icons of the lines
			lines[x][y][dx + 1][dy + 1].setIcon(diagRight);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(diagRight);
		}
		// Handle the opposite diagonal.
		else {
			// Set the icons of the lines
			lines[x][y][dx + 1][dy + 1].setIcon(diagLeft);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(diagLeft);

			// Check if the diagonal is diagonal right, update icons.
			if (lines[x][y + dy][dx + 1][-dy + 1].getIcon() == prevDiagRight) {
				lines[x][y + dy][dx + 1][-dy + 1].setIcon(diagRightLeft);
			}
			if (lines[x + dx][y][-dx + 1][dy + 1].getIcon() == prevDiagRight) {
				lines[x + dx][y][-dx + 1][dy + 1].setIcon(diagRightLeft);
			}
		}
	}

	/**
	 * This method gets the points based of the word.
	 * @param s the current word
	 * @return the points
	 */
	public int getPoints(String s) {
		// Enhanced switch statement
// https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
		return switch (s.length()) {
			case 0, 1, 2 -> 0;
			case 3, 4 -> 1;
			case 5 -> 2;
			case 6 -> 3;
			case 7 -> 5;
			default -> 11;
		};
	}

	/**
	 * This method processes a valid word.
	 * @param s the current word.
	 */
	public void processValidWord(String s) {
		try {
			// Set audio file for the valid word sound effect
			validWordSoundEffect = AudioSystem.getAudioInputStream(getClass().
					getResourceAsStream("assets/ValidWord.wav"));
			validWordClip = AudioSystem.getClip();
			// Open and play sound effect clip.
			validWordClip.open(validWordSoundEffect);
		} catch (UnsupportedAudioFileException ex) {
			// Handle if file is invalid file type.
			System.err.println(
					"Error: Invalid file type, unable to play sound effect");
			ex.printStackTrace();
		} catch (IOException ex) {
			// Handle if unable to read sound file.
			System.err.println("Error: Unable to read sound file");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			// Handle if there is no line to read.
			System.err.println("Error: No line to read");
			ex.printStackTrace();
		}
		// Play the valid sound effect.
		validWordClip.start();

		// Set points based on the points for the word.
		int pts = getPoints(s);
		// Add points to the game screen table.
		wordsFound.add(s);
		wordTable.addWord(s, pts);
		switch (playerTurn) {
			case 0:
				// Add player 1's points.
				playerOne.addPoints(pts);
				break;
			case 1:
				if (!isAI) {
					// Add player 2's points.
					playerTwo.addPoints(pts);
				} else {
					// Add ai's points.
					ai.addPoints(pts);
				}
				break;
		}
	}

	/**
	 * This method will clear the board.
	 * @param state The state of the currently selected word.
	 *              0 - invalid word, 1 - new word, 2 - old word.
	 */
	public void clearBoard(int state) {
		// Check the state of the word on the bord.
		switch (state) {
			case 1:
				// Set the word display into the new icons.
				wordDisplay.setIcon(wordDisplayNew);
				// Iterate through the grid.
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						// If the dice at this position is selected, validate.
						if (dice[i][j].isSelected()) {
							dice[i][j].valid();
						}

						// Check surroundings of the current die.
						for (int dx = -1; dx <= 1; dx++) {
							for (int dy = -1; dy <= 1; dy++) {
								if (i + dx < 0 || i + dx > 4 || j + dy < 0
										|| j + dy > 4) {
									// Out of bounds.
									continue;
								}
								if (lines[j][i][dx + 1][dy + 1] == null) {
									// Not a valid connection.
									continue;
								}
								if (lines[j][i][dx + 1][dy + 1].getIcon() ==
										horizontalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== verticalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalRightDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalLeftDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalRightLeftHoldLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalRightLeftNewLine) {
									// Not part of the selected word.
									continue;
								}

								// Connect the die with the next die.
								connect(j, i, dx, dy, 2);
							}
						}
					}
				}
				break;
			case 2:
				// Set the icon for the old icons for die.
				wordDisplay.setIcon(wordDisplayOld);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (dice[i][j].isSelected()) {
							// Mark the die as old when selected.
							dice[i][j].old();
						}
						// Check the surroundings of the current die.
						for (int dx = -1; dx <= 1; dx++) {
							for (int dy = -1; dy <= 1; dy++) {
								if (i + dx < 0 || i + dx > 4 || j + dy < 0
										|| j + dy > 4) {
									// Out of bounds
									continue;
								}
								if (lines[j][i][dx + 1][dy + 1] == null) {
									// Not a valid connection
									continue;
								}
								if (lines[j][i][dx + 1][dy + 1].getIcon()
										== horizontalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== verticalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalRightDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalLeftDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalRightLeftHoldLine ||
										lines[j][i][dx + 1][dy + 1].getIcon()
												== diagonalRightLeftOldLine) {
									// Not part of the selected word.
									continue;
								}

								// Connect the die with the next die.
								connect(j, i, dx, dy, 3);
							}
						}
					}
				}
				break;
		}

		// Reset positions
		px = -2;
		py = -2;
		// Clear all dice to unselected. Use timer to delay before clearing.
		Timer clearSelected = new Timer(0, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Deselect all selected dice by checking all selected dice.
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (dice[i][j].isSelected()) {
						dice[i][j].deselect();
					}
				}
			}
			// Iterate through the grid of dice
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {
					// Check the positions of the current die.
					for (int dx = -1; dx <= 1; dx++) {
						for (int dy = -1; dy <= 1; dy++) {
							if (lines[x][y][dx + 1][dy + 1] == null) {
								// Not a valid line
								continue;
							}
							// Connect the die with the next die.
							connect(x, y, dx, dy, 0);
						}
					}
				}
			}

			// Set word display with default icons.
			wordDisplay.setIcon(wordDisplayDefault);
			// Set the current word selected as empty.
			wordSelected = "";
			// Set the word display to the current selected word.
			wordDisplay.setText(wordSelected);
		});

		if (state != 0) {
			// Valid or old word: Show player for 500 ms before clearing.
			clearSelected.setInitialDelay(500);
		} else {
			// Invalid word: Clear immediately
			clearSelected.setInitialDelay(0);
		}
		clearSelected.setRepeats(false);
		clearSelected.start();

		// Check if player 1 reaches target points.
		if (playerOne.getPoints() >= mainFrame.getTargetPoints()) {
			// Display player 1 win screen.
			System.out.println("Player 1 Wins");
			mainFrame.endgameScreen(1);
		}
		// Check if player 2 reaches target points.
		else if (!isAI && playerTwo.getPoints() >=
				mainFrame.getTargetPoints()) {
			// Display player 2 win screen.
			System.out.println("Player 2 Wins");
			mainFrame.endgameScreen(2);
		}
		// Check if AI reaches target points.
		else if (isAI && ai.getPoints() >= mainFrame.getTargetPoints()) {
			// Display AI win screen
			System.out.println("Lambda Boggle Wins");
			mainFrame.endgameScreen(3);
		}
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 * @param e the event to be processed.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 * @param e the event to be processed.
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Skip if a connection is being made.
		if (!wordSelected.isEmpty()) {
			return;
		}
		// Skip if it is the AI's turn.
		if (isAI && playerTurn == 1) {
			return;
		}

		try {
			// Set sound effect for the dice select.
			selectDiceSoundEffect = AudioSystem.getAudioInputStream(getClass().
					getResourceAsStream("assets/SelectDice.wav"));
			selectDiceClip = AudioSystem.getClip();
			// Open and play the dice select sound effect.
			selectDiceClip.open(selectDiceSoundEffect);
		} catch (UnsupportedAudioFileException ex) {
			// Handle if file type is invalid.
			System.err.println("" +
					"Error: Invalid file type, unable to play sound effect");
			ex.printStackTrace();
		} catch (IOException ex) {
			// Handle if unable to read sound file.
			System.err.println("Error: Unable to read sound file");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			// Handle if there is no line to read.
			System.err.println("Error: No line to read");
			ex.printStackTrace();
		}
		// Play the sound effect.
		selectDiceClip.start();

		// Iterate through the grid
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// Check if mouse pressed is on the grid.
				if (e.getSource() == dice[i][j].getCenter()) {
					startedSelection = true;
					// Get the x and y position of the die.
					px = i;
					py = j;
					// Select the die at that position
					dice[i][j].select();
					// Get the string value of the die
					wordSelected = String.valueOf(dice[i][j].getTopFace());
					// Update the word display
					wordDisplay.setText(wordSelected);
				}
			}
		}
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Skip if a connection is being made.
		if (wordSelected.isEmpty()) {
			return;
		}
		
		// Stop selection
		startedSelection = false;
		// Print the current word selected.
		System.out.println(wordSelected);

		int state;
		// Check if word is valid.
		if (isValidWord(wordSelected)) {
			// Check if word is new.
			if (isNewWord(wordSelected)) {
				// Print the valid word.
				System.out.println("Valid word");
				// Set state as the new icons.
				state = 1;
				// Process the valid word.
				processValidWord(wordSelected);
				// Switch the turn to the next player.
				switchTurn();
			} else {
				// Print if the word is already entered.
				System.out.println("Already entered");
				// Set the state as the old icons.
				state = 2;
			}
		} else {
			// Print if the word does not exist.
			System.out.println("Not a word");
			// Set the state to default.
			state = 0;
		}
		// Clear the board based on the state.
		clearBoard(state);
	}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// Skip if there is no selection being made
		if (!startedSelection) {
			return;
		}
		
		try {
			// Set sound effect for selecting dice
			selectDiceSoundEffect = AudioSystem.getAudioInputStream(getClass().
					getResourceAsStream("assets/SelectDice.wav"));
			selectDiceClip = AudioSystem.getClip();
			// Open and play select dice sound effect.
			selectDiceClip.open(selectDiceSoundEffect);
		} catch (UnsupportedAudioFileException ex) {
			// Handle if file type is invalid.
			System.err.println(
					"Error: Invalid file type, unable to play sound effect");
			ex.printStackTrace();
		} catch (IOException ex) {
			// Handle if unable to read sound file.
			System.err.println("Error: Unable to read sound file");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			// Handle if there is no line to read.
			System.err.println("Error: No line to read");
			ex.printStackTrace();
		}
		// Play the sound effect.
		selectDiceClip.start();

		// Iterate through the board.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// Check if the mouse pressed is in the center and adjacent.
				if (e.getSource() == dice[i][j].getCenter() &&
						Math.abs(i - px) <= 1 && Math.abs(j - py) <= 1) {
					// Skip if the dice is already selected.
					if (dice[i][j].isSelected()) {
						return;
					}
					
					// Set positions for the previous to current die.
					int dx = py - j, dy = px - i;
					// Connect the current die with previous die.
					connect(j, i, dx, dy, 1);
					
					// Get positions of the current die
					px = i;
					py = j;
					// Select the current die.
					dice[i][j].select();
					// Add the letter to the current word selected.
					wordSelected += dice[i][j].getTopFace();
					// Update the word display.
					wordDisplay.setText(wordSelected);
				}
			}
		}
	}

	/**
	 * Invoked when the mouse exits a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
}