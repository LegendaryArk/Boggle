package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board implements MouseListener {
	private JPanel bg;
	private Dice[][] dice = new Dice[5][5];
	private JLabel[][][][] connections = new JLabel[5][5][5][5];
	private Trie dictionary;
	private Trie wordsEntered;

	private final String[][] combinations = {
			{"AAAFRS", "AEEGMU", "CEIILT", "DHHNOT", "FIPRSY"},
			{"AAEEEE", "AEGMNN", "CEILPT", "DHLNOR", "GORRVW"},
			{"AAFIRS", "AFIRSY", "CEIPST", "EIIITT", "HIPRRY"},
			{"ADENNN", "BJKQXZ", "DDLNOR", "EMOTTT", "NOOTUW"},
			{"AEEEEM", "CCNSTW", "DHHLOR", "ENSSSU", "OOOTTU"}
	};

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
	private Player plr2;
	private int plrTurn = 0;

	public Board(Boggle mainFrame, JPanel bg, JLabel wordDisplay, WordList wordList, JLabel plr1Label, JLabel plr1PtsDisplay, JLabel plr1TimeDisplay, JLabel plr2Label, JLabel plr2PtsDisplay, JLabel plr2TimeDisplay) {
		this.plr1 = new Player(plr1Label, plr1PtsDisplay, new Clock(plr1TimeDisplay, 1 * 60 * 1000, this));
		this.plr2 = new Player(plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, 1 * 60 * 1000, this));
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

		bg.setLayout(new GridLayout(5, 5, 10, 10));

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				dice[i][j] = new Dice(combinations[i][j].toCharArray(), (bg.getPreferredSize().width - 40) / 5, (bg.getPreferredSize().height - 40) / 5);
				bg.add(dice[i][j]);
				dice[i][j].getCenter().addMouseListener(this);
			}
		}

		dictionary = new Trie((int) 1.1e6, 26);
		dictionary.initDict();

		wordsEntered = new Trie((int) 1.1e6, 26);
	}

	public boolean isValidWord(String s) {
		return dictionary.query(s) && s.length() > 2;
	}
	public boolean isNewWord(String s) {
		return !wordsEntered.query(s);
	}

	public void switchTurn() {
		switch (plrTurn) {
			case 0:
				plr1.endTurn();
				plr1.getTimer().increment(2000);
				plr2.startTurn();
				break;
			case 1:
				plr2.endTurn();
				plr2.getTimer().increment(2000);
				plr1.startTurn();
				break;
		}
		plrTurn = ++plrTurn % 2; // Switch between 0 and 1
	}

	public void pause() {
		plr1.getTimer().pause();
		plr2.getTimer().pause();
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

				wordsEntered.insert(wordSelected);
				int pts = getPoints(wordSelected);
				wordList.addWord(wordSelected, pts);
				switch (plrTurn) {
					case 0 -> plr1.addPoints(pts);
					case 1 -> plr2.addPoints(pts);
				}

				switchTurn();
			} else {
				System.out.println("Already entered");
				state = 2;
			}
		} else {
			System.out.println("Not a word");
			state = 0;
		}

		switch (state) {
			case 1:
				wordDisplay.setIcon(wordDisplayNew);
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (dice[i][j].isSelected()) {
							dice[i][j].valid();
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
					}
				}
				break;
		}

		lx = -2; ly = -2;
		Timer clearSelected = new Timer(0, event -> {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					if (dice[i][j].isSelected()) {
						dice[i][j].deselect();
					}
				}
			}

			wordDisplay.setIcon(wordDisplayDefault);
			wordSelected = "";
			wordDisplay.setText(wordSelected);
			((Timer) event.getSource()).stop();
		});
		if (state != 0) { // Valid or old word -> Show player for 500 ms before clearing
			clearSelected.setInitialDelay(500);
		} else { // Not a word -> Clear immediately
			clearSelected.setInitialDelay(0);
		}
		clearSelected.start();
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
