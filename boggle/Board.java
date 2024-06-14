package boggle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Board implements MouseListener {
	private JPanel bg;
	private Dice[][] dice = new Dice[5][5];
	private JLayeredPane layers = new JLayeredPane();
	private JPanel grid = new JPanel();
	private JPanel lineGrid = new JPanel();
	private JLabel[][][][] lines = new JLabel[5][5][3][3];
	private Trie dictionary;
	private Trie wordsEntered;

	private final String[][] combinations = {
			{"AAAFRS", "AEEGMU", "CEIILT", "DHHNOT", "FIPRSY"},
			{"AAEEEE", "AEGMNN", "CEILPT", "DHLNOR", "GORRVW"},
			{"AAFIRS", "AFIRSY", "CEIPST", "EIIITT", "HIPRRY"},
			{"ADENNN", "BJKQXZ", "DDLNOR", "EMOTTT", "NOOTUW"},
			{"AEEEEM", "CCNSTW", "DHHLOR", "ENSSSU", "OOOTTU"}
	};

	ImageIcon horizontalDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultHorizontal.png"));
	ImageIcon verticalDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultVertical.png"));
	ImageIcon diagonalLeftDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultDiagonalLeft.png"));
	ImageIcon diagonalRightDefaultLine = new ImageIcon(getClass().getResource("assets/DieDefaultDiagonalRight.png"));

	ImageIcon horizontalHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldHorizontal.png"));
	ImageIcon verticalHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldVertical.png"));
	ImageIcon diagonalLeftHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldDiagonalLeft.png"));
	ImageIcon diagonalRightHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldDiagonalRight.png"));
	ImageIcon diagonalRightLeftHoldLine = new ImageIcon(getClass().getResource("assets/DieHoldDiagonalRightLeft.png"));

	ImageIcon horizontalNewLine = new ImageIcon(getClass().getResource("assets/DieNewHorizontal.png"));
	ImageIcon verticalNewLine = new ImageIcon(getClass().getResource("assets/DieNewVertical.png"));
	ImageIcon diagonalLeftNewLine = new ImageIcon(getClass().getResource("assets/DieNewDiagonalLeft.png"));
	ImageIcon diagonalRightNewLine = new ImageIcon(getClass().getResource("assets/DieNewDiagonalRight.png"));
	ImageIcon diagonalRightLeftNewLine = new ImageIcon(getClass().getResource("assets/DieNewDiagonalRightLeft.png"));

	ImageIcon horizontalOldLine = new ImageIcon(getClass().getResource("assets/DieOldHorizontal.png"));
	ImageIcon verticalOldLine = new ImageIcon(getClass().getResource("assets/DieOldVertical.png"));
	ImageIcon diagonalLeftOldLine = new ImageIcon(getClass().getResource("assets/DieOldDiagonalLeft.png"));
	ImageIcon diagonalRightOldLine = new ImageIcon(getClass().getResource("assets/DieOldDiagonalRight.png"));
	ImageIcon diagonalRightLeftOldLine = new ImageIcon(getClass().getResource("assets/DieOldDiagonalRightLeft.png"));

	private String wordSelected = "";

	private boolean startedSelection = false;
	private int lx = -2, ly = -2;

	private Boggle mainFrame;
	private JLabel wordDisplay;
	private ImageIcon wordDisplayDefault = new ImageIcon(getClass().getResource("assets/WordDisplayDefault.png"));
	private ImageIcon wordDisplayNew = new ImageIcon(getClass().getResource("assets/WordDisplayNew.png"));
	private ImageIcon wordDisplayOld = new ImageIcon(getClass().getResource("assets/WordDisplayFound.png"));
	private WordList wordList;

	private Player plr1;
	private Player plr2 = null;
	private AI ai = null;
	private int plrTurn = 0;
	private boolean isAI;

	public Board(Boggle mainFrame, JPanel bg, JLabel wordDisplay, WordList wordList, JLabel plr1Label, JLabel plr1PtsDisplay, JLabel plr1TimeDisplay, boolean ai, JLabel plr2Label, JLabel plr2PtsDisplay, JLabel plr2TimeDisplay) {
		this.isAI = ai;

		this.plr1 = new Player(plr1Label, plr1PtsDisplay, new Clock(plr1TimeDisplay, 1 * 60 * 1000, this));
		if (!ai) {
			this.plr2 = new Player(plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, 1 * 60 * 1000, this));
		} else {
			this.ai = new AI(this, plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, 1 * 60 * 1000, this));
		}
		setup(mainFrame, bg, wordDisplay, wordList);

		plr1.startTurn();
	}

	private void setup(Boggle mainFrame, JPanel bg, JLabel wordDisplay, WordList wordList) {
		this.mainFrame = mainFrame;
		this.bg = bg;
		this.wordDisplay = wordDisplay;
		this.wordList = wordList;

		int wdWidth = wordDisplay.getMinimumSize().width, wdHeight = wordDisplay.getMinimumSize().height;
		wordDisplayDefault = new ImageIcon(wordDisplayDefault.getImage().getScaledInstance(wdWidth, wdHeight, Image.SCALE_SMOOTH));
		wordDisplayNew = new ImageIcon(wordDisplayNew.getImage().getScaledInstance(wdWidth, wdHeight, Image.SCALE_SMOOTH));
		wordDisplayOld = new ImageIcon(wordDisplayOld.getImage().getScaledInstance(wdWidth, wdHeight, Image.SCALE_SMOOTH));
		wordDisplay.setIcon(wordDisplayDefault);

		GridBagConstraints c;

		bg.setLayout(new GridBagLayout());
		layers.setPreferredSize(bg.getPreferredSize());
		layers.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		bg.add(layers, new GridBagConstraints());

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

		int diceWidth = (bg.getPreferredSize().width - 40) / 5;
		int diceHeight = (bg.getPreferredSize().height - 40) / 5;

		horizontalDefaultLine = new ImageIcon(horizontalDefaultLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalDefaultLine = new ImageIcon(verticalDefaultLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftDefaultLine = new ImageIcon(diagonalLeftDefaultLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightDefaultLine = new ImageIcon(diagonalRightDefaultLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		horizontalHoldLine = new ImageIcon(horizontalHoldLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalHoldLine = new ImageIcon(verticalHoldLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftHoldLine = new ImageIcon(diagonalLeftHoldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightHoldLine = new ImageIcon(diagonalRightHoldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftHoldLine = new ImageIcon(diagonalRightLeftHoldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		horizontalNewLine = new ImageIcon(horizontalNewLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalNewLine = new ImageIcon(verticalNewLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftNewLine = new ImageIcon(diagonalLeftNewLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightNewLine = new ImageIcon(diagonalRightNewLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftNewLine = new ImageIcon(diagonalRightLeftNewLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		horizontalOldLine = new ImageIcon(horizontalOldLine.getImage().getScaledInstance(diceWidth, (int) (0.3 * diceHeight), Image.SCALE_SMOOTH));
		verticalOldLine = new ImageIcon(verticalOldLine.getImage().getScaledInstance((int) (0.3 * diceWidth), diceHeight, Image.SCALE_SMOOTH));
		diagonalLeftOldLine = new ImageIcon(diagonalLeftOldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightOldLine = new ImageIcon(diagonalRightOldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));
		diagonalRightLeftOldLine = new ImageIcon(diagonalRightLeftOldLine.getImage().getScaledInstance(diceWidth, diceHeight, Image.SCALE_SMOOTH));

		for (int x = 0; x < 5; x++) {
			for (int y = 0; y < 5; y++) {
				dice[x][y] = new Dice(combinations[x][y].toCharArray(), diceWidth, diceHeight);
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
		dictionary = new Trie((int) 1.1e6, 26);
		initDict();

		wordsEntered = new Trie((int) 1.1e6, 26);
	}

	public void initDict() {
		try {
			Scanner sc = new Scanner(new File("src/Boggle/boggle/resources/dictionary.txt"));
			while (sc.hasNextLine()) {
				dictionary.insert(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Fatal error: Unable to find and load dictionary");
			System.exit(0);
		}
	}

	public Dice[][] getDiceGrid() {
		return dice;
	}
	public Trie getWordsEntered() {
		return wordsEntered;
	}
	public WordList getWordList() {
		return wordList;
	}
	public JLabel getWordDisplay() {
		return wordDisplay;
	}

	public boolean isValidWord(String s) {
		return dictionary.query(s) && s.length() > 2;
	}
	public boolean isNewWord(String s) {
		return !wordsEntered.query(s);
	}

	public void switchTurn() {
		plrTurn = ++plrTurn % 2; // Switch between 0 and 1

		switch (plrTurn) {
			case 0:
				if (!isAI) {
					plr2.endTurn();
					plr2.getTimer().increment(5000);
				} else {
					ai.endTurn();
					ai.getTimer().increment(5000);
				}
				plr1.startTurn();
				break;
			case 1:
				plr1.endTurn();
				plr1.getTimer().increment(5000);
				if (!isAI) {
					plr2.startTurn();
				} else {
					ai.startTurn();
					Timer waitUntil = new Timer(100, e -> {
						if (!ai.isWordFound()) return;

						switchTurn();
						((Timer) e.getSource()).stop();
					});
					waitUntil.start();
					return;
				}
				break;
		}
	}

	public void pause() {
		plr1.getTimer().pause();
		if (!isAI) {
			plr2.getTimer().pause();
		} else {
			ai.getTimer().pause();
		}
	}
	public void resume() {
		switch (plrTurn) {
			case 0 -> plr1.getTimer().start();
			case 1 -> {
				if (!isAI) {
					plr2.getTimer().start();
				} else {
					ai.getTimer().start();
				}
			}
		}
	}

	public void shuffle() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				Random rand = new Random();
				int x = rand.nextInt(5);
				int y = rand.nextInt(5);
				char[] tmp = dice[x][y].getFaces();
				dice[x][y].setFaces(dice[i][j].getFaces());
				dice[i][j].setFaces(tmp);
				dice[i][j].roll();
				dice[x][y].roll();
			}
		}
	}

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

	public void validWord(String s) {
		int pts = getPoints(s);
		wordsEntered.insert(s);
		wordList.addWord(s, pts);
		switch (plrTurn) {
			case 0:
				plr1.addPoints(pts);
				break;
			case 1:
				if (!isAI) {
					plr2.addPoints(pts);
				} else {
					ai.addPoints(pts);
				}
				break;
		}
	}

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
								if (i + dx < 0 || i + dx > 4 || j + dy < 0 || j + dy > 4) continue;
								if (lines[j][i][dx + 1][dy + 1] == null) continue;
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

		lx = -2; ly = -2;
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

		/*if (plr1.getPoints() > 15) {
			mainFrame.endgameScreen(1);
		} else if (!isAI && plr2.getPoints() > 15) {
			mainFrame.endgameScreen(2);
		} else if (isAI && ai.getPoints() > 15) {
			mainFrame.endgameScreen(3);
		}*/
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if (!wordSelected.isEmpty()) return;
		if (isAI && plrTurn == 1) return;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == dice[i][j].getCenter()) {
					startedSelection = true;
					lx = i; ly = j;
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

				validWord(wordSelected);

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

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (e.getSource() == dice[i][j].getCenter() && Math.abs(i - lx) <= 1 && Math.abs(j - ly) <= 1) {
					if (dice[i][j].isSelected()) return;

					int dx = ly - j, dy = lx - i;
					connect(j, i, dx, dy, 1);
					lx = i; ly = j;
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
	public void mouseExited(MouseEvent e) {}
}