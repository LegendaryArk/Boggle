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
	// Background panel of the board.
	private JPanel background;
	// 2D array for the dice objects.
	private final Dice[][] dice = new Dice[5][5];
	// Layered pane for managing multiple elements.
	private JLayeredPane layers = new JLayeredPane();
	// Panel for the dice objects.
	private JPanel grid = new JPanel();
	// Panel for the lines in the board.
	private JPanel lineGrid = new JPanel();
	// Lines for the different connections on the board.
	private JLabel[][][][] lines = new JLabel[5][5][3][3];
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
	private ImageIcon horizontalDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultHorizontal.png"));
	// Vertical Connection Default Icon.
	private ImageIcon verticalDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultVertical.png"));
	// Left Diagonal Connection Default Icon.
	private ImageIcon diagonalLeftDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultDiagonalLeft.png"));
	// Right Diagonal Connection Default Icon.
	private ImageIcon diagonalRightDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultDiagonalRight.png"));

	// Horizontal Hold Connection.
	private ImageIcon horizontalHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldHorizontal.png"));
	// Vertical Hold Connection.
	private ImageIcon verticalHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldVertical.png"));
	// Diagonal Left Hold Connection.
	private ImageIcon diagonalLeftHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldDiagonalLeft.png"));
	// Diagonal Right Hold Connection.
	private ImageIcon diagonalRightHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldDiagonalRight.png"));
	// Diagonal Right Left Hold Connection.
	private ImageIcon diagonalRightLeftHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldDiagonalRightLeft.png"));

	// Horizontal New Connection.
	private ImageIcon horizontalNewLine = new ImageIcon(getClass().getResource("assets/DieNewHorizontal.png"));
	// Vertical New Connection.
	private ImageIcon verticalNewLine = new ImageIcon(getClass().getResource("assets/DieNewVertical.png"));
	// Diagonal Left New Connection.
	private ImageIcon diagonalLeftNewLine = new ImageIcon(getClass().getResource("assets/DieNewDiagonalLeft.png"));
	// Diagonal Right New Connection.
	private ImageIcon diagonalRightNewLine = new ImageIcon(getClass().getResource("assets/DieNewDiagonalRight.png"));
	// Diagonal Right Left New Connection.
	private ImageIcon diagonalRightLeftNewLine = new ImageIcon(getClass().getResource("assets/DieNewDiagonalRightLeft.png"));

	// Horizontal Old Connection.
	private ImageIcon horizontalOldLine = new ImageIcon(getClass().getResource("assets/DieOldHorizontal.png"));
	// Vertical Old Connection.
	private ImageIcon verticalOldLine = new ImageIcon(getClass().getResource("assets/DieOldVertical.png"));
	// Diagonal Left Old Connection.
	private ImageIcon diagonalLeftOldLine = new ImageIcon(getClass().getResource("assets/DieOldDiagonalLeft.png"));
	// Diagonal Right Old Connection.
	private ImageIcon diagonalRightOldLine = new ImageIcon(getClass().getResource("assets/DieOldDiagonalRight.png"));
	// Diagonal Right Left Old Connection.
	private ImageIcon diagonalRightLeftOldLine = new ImageIcon(getClass().getResource("assets/DieOldDiagonalRightLeft.png"));

	// Currently selected word on the board.
	private String wordSelected = "";

	// Indicates whether a letter has been selected.
	private boolean startedSelection = false;
	// Coordinates of the previously selected dice on the board.
	private int px = -2, py = -2;

	// Main game frame
	private Boggle mainFrame;
	// Displays the current word being formed
	private JLabel wordDisplay;

	// Icon for the default word.
	private ImageIcon wordDisplayDefault = new ImageIcon(getClass().getResource("assets/WordDisplayDefault.png"));
	// Icon for the new word.
	private ImageIcon wordDisplayNew = new ImageIcon(getClass().getResource("assets/WordDisplayNew.png"));
	// Icon for the old word.
	private ImageIcon wordDisplayOld = new ImageIcon(getClass().getResource("assets/WordDisplayFound.png"));
	// Table to display all the words.
	private WordTable wordList;
	// Button to skip a turn
	private OptionButton passBtn;

	// Player 2 title.
	private JLabel plr2Label;
	// Player 2 total points display.
	private JLabel plr2PtsDisplay;
	// Player 2 time remaining display.
	private JLabel plr2TimeDisplay;

	// Player object representing player 1.
	private Player plr1;
	// Player object representing player 2, null if playing against AI.
	private Player plr2 = null;
	// AI object representing the AI.
	private AI ai = null;
	// Current player's turn.
	private int plrTurn = 0;
	// Indicator if current game is player vs AI.
	private boolean isAI;

	// Audio file for select dice sound effect.
	private AudioInputStream selectDiceSfx;
	// Clip for selecting dice.
	private Clip selectDiceClip;
	// Audio file for valid word sound effect.
	private AudioInputStream validWordSfx;
	// Clip for valid word.
	private Clip validWordClip;

	/**
	 * Constructor
	 * @param mainFrame main game frame.
	 * @param background background of the game.
	 * @param wordDisplay display of the current word.
	 * @param wordList list that contains all possible words.
	 * @param plr1Label player 1 title.
	 * @param plr1PtsDisplay player 1 points display.
	 * @param plr1TimeDisplay player 1 time display.
	 * @param ai AI if game is player vs AI.
	 * @param plr2Label player 2 title.
	 * @param plr2PtsDisplay player 2 points display.
	 * @param plr2TimeDisplay player 2 time display.
	 * @param passBtn button to skip turn.
	 */
	public Board(Boggle mainFrame, JPanel background, JLabel wordDisplay, WordTable wordList, JLabel plr1Label, JLabel plr1PtsDisplay, JLabel plr1TimeDisplay, boolean ai, JLabel plr2Label, JLabel plr2PtsDisplay, JLabel plr2TimeDisplay, OptionButton passBtn) {
		// Set the game mode based on if player vs AI is chosen.
		this.isAI = ai;
		// Set pass button.
		this.passBtn = passBtn;
		// Set player 2 label, points and time.
		this.plr2Label = plr2Label;
		this.plr2PtsDisplay = plr2PtsDisplay;
		this.plr2TimeDisplay = plr2TimeDisplay;

		// Initialize player 1 with their title, points and clock.
		this.plr1 = new Player(plr1Label, plr1PtsDisplay, new Clock(plr1TimeDisplay, mainFrame.getInitialTime(), this));
		if (!ai) {
			// Initialize player 2 with their title. points and clock.
			this.plr2 = new Player(plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, mainFrame.getInitialTime(), this));
		} else {
			// Intitalize AI with their difficulty,title, points and clock.
			this.ai = new AI(this, mainFrame.getAIDifficulty(), mainFrame.getMinimumWordLength(), plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, mainFrame.getInitialTime(), this));
		}
		// Display the board based on the game mode selected.
		setup(mainFrame, background, wordDisplay, wordList);

		// Start with player 1's turn.
		plr1.startTurn();
	}

	/**
	 * This method setups the game screen.
	 * @param mainFrame main game frame.
	 * @param bg background of the game.
	 * @param wordDisplay display of the current word.
	 * @param wordList list that contains all possible words.
	 */
	private void setup(Boggle mainFrame, JPanel bg, JLabel wordDisplay, WordTable wordList) {
		// Set frame, background, word display and list
		this.mainFrame = mainFrame;
		this.background = bg;
		this.wordDisplay = wordDisplay;
		this.wordList = wordList;

		// Scale the word display label to fit size.
		int wdWidth = wordDisplay.getMinimumSize().width, wdHeight = wordDisplay.getMinimumSize().height;
		wordDisplayDefault = new ImageIcon(wordDisplayDefault.getImage().getScaledInstance(wdWidth, wdHeight, Image.SCALE_SMOOTH));
		wordDisplayNew = new ImageIcon(wordDisplayNew.getImage().getScaledInstance(wdWidth, wdHeight, Image.SCALE_SMOOTH));
		wordDisplayOld = new ImageIcon(wordDisplayOld.getImage().getScaledInstance(wdWidth, wdHeight, Image.SCALE_SMOOTH));
		wordDisplay.setIcon(wordDisplayDefault);

		GridBagConstraints c;

		// Configure background layout and add the layers to GridBagLayout.
		bg.setLayout(new GridBagLayout());
		layers.setPreferredSize(bg.getPreferredSize());
		layers.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		bg.add(layers, new GridBagConstraints());

		// Set the layout for the grid.
		grid.setLayout(new GridLayout(5, 5, 5, 5));
		grid.setMinimumSize(bg.getMinimumSize());
		grid.setPreferredSize(bg.getPreferredSize());
		grid.setMaximumSize(bg.getPreferredSize());
		grid.setOpaque(false);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.gridheight = 5;
		layers.add(grid, c);
		layers.setLayer(grid, 1);

		// Set the layout for the lines in the board.
		lineGrid.setLayout(new GridBagLayout());
		lineGrid.setMinimumSize(bg.getMinimumSize());
		lineGrid.setPreferredSize(bg.getPreferredSize());
		lineGrid.setMaximumSize(bg.getPreferredSize());
		lineGrid.setOpaque(false);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		c.gridheight = 5;
		layers.add(lineGrid, c);
		layers.setLayer(lineGrid, 0);

		// Set the size of the dice based on width and height.
		int diceWidth = (bg.getPreferredSize().width - 40) / 5;
		int diceHeight = (bg.getPreferredSize().height - 40) / 5;

		// Scale and set default line image.
		horizontalDefaultLine = new ImageIcon(horizontalDefaultLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalDefaultLine = new ImageIcon(verticalDefaultLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftDefaultLine = new ImageIcon(diagonalLeftDefaultLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightDefaultLine = new ImageIcon(diagonalRightDefaultLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		// Scale and set hold line image.
		horizontalHoldLine = new ImageIcon(horizontalHoldLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalHoldLine = new ImageIcon(verticalHoldLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftHoldLine = new ImageIcon(diagonalLeftHoldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightHoldLine = new ImageIcon(diagonalRightHoldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftHoldLine = new ImageIcon(diagonalRightLeftHoldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		// Scale and set new line image.
		horizontalNewLine = new ImageIcon(horizontalNewLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalNewLine = new ImageIcon(verticalNewLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftNewLine = new ImageIcon(diagonalLeftNewLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightNewLine = new ImageIcon(diagonalRightNewLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftNewLine = new ImageIcon(diagonalRightLeftNewLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		// Scale and set old line image.
		horizontalOldLine = new ImageIcon(horizontalOldLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalOldLine = new ImageIcon(verticalOldLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftOldLine = new ImageIcon(diagonalLeftOldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightOldLine = new ImageIcon(diagonalRightOldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftOldLine = new ImageIcon(diagonalRightLeftOldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		// Setup the dice and connections
		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				dice[x][y] = new Dice(COMBINATIONS[x][y].toCharArray(), diceWidth, diceHeight);
				grid.add(dice[x][y]);
				dice[x][y].getCenter().addMouseListener(this);

				for (int dx = -1; dx <= 1; dx++) {
					for (int dy = -1; dy <= 1; dy++) {
						if (x + dx < 0 || x + dx > 4 || y + dy < 0 || y + dy > 4) {
							// Out of bounds
							continue;
						}
						if (dx == 0 && dy == 0) {
							// Same dice
							continue;
						}

						JLabel line = new JLabel();
						if (dy == 0) {
							line.setIcon(horizontalDefaultLine);
						} else if (dx == 0) {
							line.setIcon(verticalDefaultLine);
						} else if (dx == dy) {
							line.setIcon(diagonalRightDefaultLine);
						} else {
							line.setIcon(diagonalLeftDefaultLine);
						}
						c = new GridBagConstraints();
						c.gridx = dx < 0 ? x + dx : x;
						c.gridy = dy < 0 ? y + dy : y;
						c.gridwidth = dx == 0 ? 1 : 2;
						c.gridheight = dy == 0 ? 1 : 2;
						c.weightx = 1;
						c.weighty = 1;
						lineGrid.add(line, c);
						lines[x][y][dx + 1][dy + 1] = line;
					}
				}
			}
		}

		// 1.1e6 is scientific notation: 1.1 * 10^6
		dictionary = new WordList();
		initDict();

		wordsFound = new WordList();
	}

	/**
	 * This method initalizes the dictionary array
	 */
	public void initDict() {
		try {
			// Read lines from dictionary.txt
			Scanner sc = new Scanner(new File("src/Boggle/boggle/resources/dictionary.txt"));
			while (sc.hasNextLine()) {
				// Add each wrod and upper case them.
				dictionary.add(sc.nextLine().toUpperCase());
			}
			// Close scanner.
			sc.close();
		} catch (FileNotFoundException e) {
			// Handle if dictionary is not found.
			e.printStackTrace();
			System.err.println("Fatal error: Unable to find and load dictionary");
			// Exit game.
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
	 * This method gets the word list.
	 * @return the word list.
	 */
	public boggle.WordTable getWordList() {
		return wordList;
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
		// Set the AI
		this.isAI = isAI;
		if (!isAI) {
			// Initialize player 2 if game mode is not player vs player.
			this.plr2 = new Player(plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, mainFrame.getInitialTime(), this));
			this.ai = null;
		} else {
			// Initialize AI if game mode is player vs AI.
			this.ai = new AI(this, mainFrame.getAIDifficulty(), mainFrame.getMinimumWordLength(), plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, mainFrame.getInitialTime(), this));
			this.plr2 = null;
		}
	}

	/**
	 * This method sets the turn.
	 * @param turn which player/AI goes next.
	 */
	public void setTurn(int turn) {
		plrTurn = turn;
	}

	/**
	 * This method will reset the board.
	 */
	public void resetBoard() {
		// Reset the player 1 timer.
		plr1.getTimer().reset(mainFrame.getInitialTime());
		// Reset the points for player 1.
		plr1.resetPoints();
		if (!isAI) {
			// Reset player 2 timer.
			plr2.getTimer().reset(mainFrame.getInitialTime());
			// Reset player 2 points.
			plr2.resetPoints();
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
		return dictionary.contains(s) && s.length() >= mainFrame.getMinimumWordLength();
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
		// Add turn counter and % value by 2.
		plrTurn = ++plrTurn % 2; // Switch between 0 and 1.
		switch (plrTurn) {
			case 0:
				if (!isAI) {
					// End player 2 turn.
					plr2.endTurn();
					// Increment player 2 timer.
					plr2.getTimer().increment(mainFrame.getTimeIncrement());
				} else {
					// End AI turn.
					ai.endTurn();
					// Increment AI time.
					ai.getTimer().increment(mainFrame.getTimeIncrement());
					// Set pass button visible.
					passBtn.setVisible(true);
				}
				// Start player 1's turn.
				plr1.startTurn();
				break;
			case 1:
				// End player 1 turn.
				plr1.endTurn();
				// Increment player 1 timer.
				plr1.getTimer().increment(mainFrame.getTimeIncrement());
				if (!isAI) {
					// Start Player 2's turn.
					plr2.startTurn();
				} else {
					// Remove pass button from game screen.
					passBtn.setVisible(false);
					// Start AI's turn.
					ai.startTurn();
					// Set a timer for AI's turn.
					Timer waitUntil = new Timer(100, e -> {
						// Return AI's word
						if (!ai.isWordFound()) return;
						// Switch the turn to player 1.
						switchTurn();
						// Stop AI timer.
						((Timer) e.getSource()).stop();
					});
					// Exit the method.
					waitUntil.start();
					return;
				}
				break;
		}
	}

	/**
	 * This method will pause the game
	 */
	public void pause() {
		// Pause player 1 timer.
		plr1.getTimer().pause();
		if (!isAI) {
			// Pause player 2 timer.
			plr2.getTimer().pause();
		} else {
			// Pause AI timer.
			ai.getTimer().pause();
		}
	}

	/**
	 * This method will resume the game after pausing
	 */
	public void resume() {
		switch (plrTurn) {
			// Start player 1 timer.
			case 0 -> plr1.getTimer().start();
			case 1 -> {
				if (!isAI) {
					// Start Player 2 timer.
					plr2.getTimer().start();
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
				// Swap the character with the previous location.
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
	 * @param dx The x-coordinate of the second dice relative to the first dice.
	 *           Either -1, 0, or 1.
	 * @param dy The y-coordinate of the second dice relative to the first dice.
	 *           Either -1, 0, or 1.
	 * @param state The state of the connection
	 *                 0 for default, 1 for hold, 2 for new, 3 for old
	 */
	public void connect(int x, int y, int dx, int dy, int state) {
		if (lines[x][y][dx + 1][dy + 1] == null) return;

		ImageIcon hori = horizontalDefaultLine;
		ImageIcon vert = verticalDefaultLine;
		ImageIcon diagRight = diagonalRightDefaultLine;
		ImageIcon diagLeft = diagonalLeftDefaultLine;
		ImageIcon diagRightLeft = diagonalRightDefaultLine;
		ImageIcon prevDiagRight = diagonalRightDefaultLine;
		switch (state) {
			case 1:
				hori = horizontalHoldLine;
				vert = verticalHoldLine;
				diagRight = diagonalRightHoldLine;
				diagLeft = diagonalLeftHoldLine;
				diagRightLeft = diagonalRightLeftHoldLine;
				prevDiagRight = diagonalRightDefaultLine;
				break;
			case 2:
				hori = horizontalNewLine;
				vert = verticalNewLine;
				diagRight = diagonalRightNewLine;
				diagLeft = diagonalLeftNewLine;
				diagRightLeft = diagonalRightLeftNewLine;
				prevDiagRight = diagonalRightLeftHoldLine;
				break;
			case 3:
				hori = horizontalOldLine;
				vert = verticalOldLine;
				diagRight = diagonalRightOldLine;
				diagLeft = diagonalLeftOldLine;
				diagRightLeft = diagonalRightLeftOldLine;
				prevDiagRight = diagonalRightLeftHoldLine;
				break;
		}

		if (dy == 0) {
			lines[x][y][dx + 1][dy + 1].setIcon(hori);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(hori);
		} else if (dx == 0) {
			lines[x][y][dx + 1][dy + 1].setIcon(vert);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(vert);
		} else if (dx == dy) {
			lines[x][y][dx + 1][dy + 1].setIcon(diagRight);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(diagRight);
		} else {
			lines[x][y][dx + 1][dy + 1].setIcon(diagLeft);
			lines[x + dx][y + dy][-dx + 1][-dy + 1].setIcon(diagLeft);
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
			validWordSfx = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("assets/ValidWord.wav"));
			validWordClip = AudioSystem.getClip();
			// Open and play sound effect clip.
			validWordClip.open(validWordSfx);
		} catch (UnsupportedAudioFileException ex) {
			// Handle if file is invalid file type.
			System.err.println("Error: Invalid file type, unable to play sound effect");
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
		wordList.addWord(s, pts);
		switch (plrTurn) {
			case 0:
				// Add player 1 points.
				plr1.addPoints(pts);
				break;
			case 1:
				if (!isAI) {
					// Add player 2 points.
					plr2.addPoints(pts);
				} else {
					// Add ai points.
					ai.addPoints(pts);
				}
				break;
		}
	}

	/**
	 * This method will clear the board.
	 * @param state
	 */
	public void clearBoard(int state) {
		switch (state) {
			case 1:
				wordDisplay.setIcon(wordDisplayNew);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (dice[i][j].isSelected()) {
							dice[i][j].valid();
						}

						for (int dx = -1; dx <= 1; dx++) {
							for (int dy = -1; dy <= 1; dy++) {
								if (i + dx < 0 || i + dx > 4 || j + dy < 0 || j + dy > 4)
									continue;
								if (lines[j][i][dx + 1][dy + 1] == null)
									continue;
								if (lines[j][i][dx + 1][dy + 1].getIcon() == horizontalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == verticalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalRightDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalLeftDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalRightLeftHoldLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalRightLeftNewLine) {
									continue;
								}

								connect(j, i, dx, dy, 2);
							}
						}
					}
				}
				break;
			case 2:
				wordDisplay.setIcon(wordDisplayOld);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (dice[i][j].isSelected()) {
							dice[i][j].old();
						}

						for (int dx = -1; dx <= 1; dx++) {
							for (int dy = -1; dy <= 1; dy++) {
								if (i + dx < 0 || i + dx > 4 || j + dy < 0 || j + dy > 4) {
									// Out of bounds
									continue;
								}
								if (lines[j][i][dx + 1][dy + 1] == null) {
									// Not a valid connection
									continue;
								}
								if (lines[j][i][dx + 1][dy + 1].getIcon() == horizontalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == verticalDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalRightDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalLeftDefaultLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalRightLeftHoldLine ||
										lines[j][i][dx + 1][dy + 1].getIcon() == diagonalRightLeftOldLine) {
									continue;
								}

								connect(j, i, dx, dy, 3);
							}
						}
					}
				}
				break;
		}

		px = -2;
		py = -2;
		Timer clearSelected = new Timer(0, e -> {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (dice[i][j].isSelected()) {
						dice[i][j].deselect();
					}
				}
			}
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 5; y++) {
					for (int dx = -1; dx <= 1; dx++) {
						for (int dy = -1; dy <= 1; dy++) {
							if (lines[x][y][dx + 1][dy + 1] == null) {
								// Not a valid line
								continue;
							}

							connect(x, y, dx, dy, 0);
						}
					}
				}
			}

			wordDisplay.setIcon(wordDisplayDefault);
			wordSelected = "";
			wordDisplay.setText(wordSelected);
			((Timer) e.getSource()).stop();
		});
		if (state != 0) { // Valid or old word -> Show player for 500 ms before clearing
			clearSelected.setInitialDelay(500);
		} else { // Not a word -> Clear immediately
			clearSelected.setInitialDelay(0);
		}
		clearSelected.start();

		if (plr1.getPoints() > mainFrame.getTargetPoints()) {
			mainFrame.endgameScreen(1);
		} else if (!isAI && plr2.getPoints() > mainFrame.getTargetPoints()) {
			mainFrame.endgameScreen(2);
		} else if (isAI && ai.getPoints() > mainFrame.getTargetPoints()) {
			mainFrame.endgameScreen(3);
		}
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!wordSelected.isEmpty()) return;
		if (isAI && plrTurn == 1) return;

		try {
			selectDiceSfx = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("assets/SelectDice.wav"));
			selectDiceClip = AudioSystem.getClip();
			selectDiceClip.open(selectDiceSfx);
		} catch (UnsupportedAudioFileException ex) {
			System.err.println("Error: Invalid file type, unable to play sound effect");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Error: Unable to read sound file");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.err.println("Error: No line to read");
			ex.printStackTrace();
		}
		selectDiceClip.start();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == dice[i][j].getCenter()) {
					startedSelection = true;
					px = i;
					py = j;
					dice[i][j].select();
					wordSelected = String.valueOf(dice[i][j].getTopFace());
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
		if (wordSelected.isEmpty()) return;

		startedSelection = false;

		System.out.println(wordSelected);

		final int state;
		if (isValidWord(wordSelected)) {
			if (isNewWord(wordSelected)) {
				System.out.println("Valid word");
				state = 1;

				processValidWord(wordSelected);

				switchTurn();
			} else {
				System.out.println("Already entered");
				state = 2;
			}
		} else {
			System.out.println("Not a word");
			state = 0;
		}

		clearBoard(state);
	}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (!startedSelection) return;
		try {
			selectDiceSfx = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("assets/SelectDice.wav"));
			selectDiceClip = AudioSystem.getClip();
			selectDiceClip.open(selectDiceSfx);
		} catch (UnsupportedAudioFileException ex) {
			System.err.println("Error: Invalid file type, unable to play sound effect");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println("Error: Unable to read sound file");
			ex.printStackTrace();
		} catch (LineUnavailableException ex) {
			System.err.println("Error: No line to read");
			ex.printStackTrace();
		}
		selectDiceClip.start();

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == dice[i][j].getCenter() && Math.abs(i - px) <= 1 && Math.abs(j - py) <= 1) {
					if (dice[i][j].isSelected()) return;

					int dx = py - j, dy = px - i;
					connect(j, i, dx, dy, 1);
					px = i;
					py = j;
					dice[i][j].select();
					wordSelected += dice[i][j].getTopFace();
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
	public void mouseExited(MouseEvent e) {
	}
}