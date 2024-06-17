/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class is an implementation of an AI that plays Boggle with difficulty
 * settings.
 */
public class AI extends Player {
	// Current word that is highlighted.
	private String currentWord;
	// If a word is highlighted.
	private boolean wordHighlighted;
	// If a word has been found.
	private boolean wordFound;
	// Dictionary provided.
	private ArrayList<String> dictionary;
	// If indices (i, j) has been visited in the search.
	private boolean[][] visited;
	// Stores indices of the board of the current word.
	private ArrayList<Pair> indices;
	// Board.
	private Board board;
	// Difficulty of AI.
	private int difficulty;
	// Lengths of words that are present on the current board.
	private ArrayList<Integer> lengths;
	// All words present on the current board.
	private ArrayList<String> options;
	// Random number generator.
	private Random random;
	// Minimum word length of a valid word.
	private int minimumWordLength;
	// Code to find word on the board with a delay
	private boolean isFindingWord;

	/**
	 * Constructor
	 * @param board board of game
	 * @param difficulty difficulty of AI
	 * @param AIDisplay display of AI
	 * @param pointsDisplay points display of AI
	 * @param timer timer of AI
	 */
	public AI(Board board, int difficulty, int minimumWordLength,
	          JLabel AIDisplay, JLabel pointsDisplay, Clock timer) {
		// Instantiate variables.
		super(AIDisplay, pointsDisplay, timer);
		this.board = board;
		this.difficulty = difficulty;
		this.minimumWordLength = minimumWordLength;
		currentWord = "";
		wordHighlighted = false;
		wordFound = false;
		dictionary = new ArrayList<>();
		indices = new ArrayList<>();
		visited = new boolean[5][5];
		lengths = new ArrayList<>();
		options = new ArrayList<>();
		random = new Random();
		isFindingWord = false;

		// Read dictionary into ArrayList.
		Scanner sc = new Scanner(getClass()
				.getResourceAsStream("resources/dictionary.txt"));
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			// Remove non letter characters.
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) - 'a' < 0 || s.charAt(i) - 'a' > 26) {
					s = s.substring(0, i) + s.substring(i + 1);
				}
			}
			dictionary.add(s.toUpperCase());
		}
		sc.close();
	}

	/**
	 * This method brute force searches the board to see if a word is present.
	 * @param r row
	 * @param c column
	 * @param s word
	 * @param pos index in word
	 * @param isHighlighting if it is highlighting the word on the board
	 */
	private void search(int r, int c, String s, int pos,
	                    boolean isHighlighting) {
		if (!isFindingWord) return;

		// If the index is at the end of the word.
		if (pos == s.length() - 1) {
			// If not highlighting.
			if (!isHighlighting) {
				// If the word has already been found.
				if (board.getWordsFound().contains(s)) {
					return;
				}

				// If the length is distinct.
				if (!lengths.contains(s.length())) {
					lengths.add(s.length());
				}
				options.add(s);
			} else { // If highlighting.
				// If a word has not already been highlighted
				if (!wordHighlighted) {
					// Iterate through indices.
					for (int i = 0; i < indices.size(); i++) {
						int x = indices.get(i).getX();
						int y = indices.get(i).getY();
						// Highlight dice at index (x, y).
						board.getDiceGrid()[x][y].select();
						// If there is a previous dice highlighted.
						if (i > 0) {
							int px = indices.get(i - 1).getX();
							int py = indices.get(i - 1).getY();
							// Connect current dice with previous dice.
							board.connect(y, x, py - y, px - x, 1);
						}
					}
					wordHighlighted = true;
				}
			}
			return;
		}

		// Iterate through delta x and delta y that are adjacent with current
		// indices.
		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				// Initialize new indices that are adjacent with current
				// indices.
				int nr = r + dx, nc = c + dy;
				// If new indices are in bounds and the new indices have not
				// been visited and the dice at the new indices corresponds
				// with the next character in the word.
				if (0 <= nr && nr < 5 && 0 <= nc && nc < 5 && !visited[nr][nc]
						&& s.charAt(pos + 1) ==
						board.getDiceGrid()[nr][nc].getTopFace()) {
					visited[nr][nc] = true;
					if (isHighlighting) {
						indices.add(new Pair(nr, nc));
					}
					// search the next position of the word at the new indices.
					search(nr, nc, s, pos + 1, isHighlighting);
					if (isHighlighting) {
						indices.remove(indices.size() - 1);
					}
					visited[nr][nc] = false;
				}
			}
		}
	}

	/**
	 * This method highlights the current word.
	 */
	public void highlightCurrentWord() {
		// Iterate through all possible starting indices.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				// If dice at indices (i, j) corresponds with the first
				// character of the current word.
				if (currentWord.charAt(0) ==
						board.getDiceGrid()[i][j].getTopFace()) {
					indices.add(new Pair(i, j));
					visited[i][j] = true;
					// Search for the current word starting from (i, j).
					search(i, j, currentWord, 0, true);
					visited[i][j] = false;
					indices.remove(indices.size() - 1);
				}
				// If the word has been highlighted.
				if (wordHighlighted) {
					return;
				}
			}
		}
	}

	/**
	 * This method starts the turn.
	 */
	@Override
	public void startTurn() {
		// Reset variables.
		currentWord = "";
		wordFound = false;
		wordHighlighted = false;
		isFindingWord = true;
		// Start turn.
		super.startTurn();

		// Set a timer with delay depending on the difficulty.
		Timer findWord = new Timer(0, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Iterate through each word in the dictionary.
			for (String s : dictionary) {
				// If word is empty.
				if (s.isEmpty()) {
					continue;
				}
				// If the word has not been found.
				if (!board.getWordsFound().contains(s)) {
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							// If dice at indices (i, j) corresponds with the
							// first character of the word.
							if (s.charAt(0) ==
									board.getDiceGrid()[i][j].getTopFace()) {
								visited[i][j] = true;
								// Search for the word starting from (i, j).
								search(i, j, s, 0, false);
								visited[i][j] = false;
							}
						}
					}
				}
			}

			// Sort the lengths using Merge Sort.
			Boggle.sort(lengths);

			// Shuffle the options.
			for (int i = options.size(); i > 1; i--) {
				int randomIndex = random.nextInt(i);
				String temp = options.get(i - 1);
				options.set(i - 1, options.get(randomIndex));
				options.set(randomIndex, temp);
			}

			if (lengths.isEmpty()) return;

			// Remove all lengths less than the minimum word length.
			while (lengths.get(0) < minimumWordLength) {
				lengths.remove(0);
			}

			// Choice of length depending on difficulty.
			int length = 0;
			// Enhanced switch statement
// https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
			switch (difficulty) {
				// Get minimum word length.
				case 0 -> length = lengths.get(0);
				// Get random word length in the first half.
				case 1 -> length = lengths.get(
						random.nextInt(lengths.size() / 2));
				// Get random word length in the second half.
				case 2 -> length = lengths.get(lengths.size() / 2 +
						random.nextInt(lengths.size() / 2));
				// Get maximum word length.
				case 3 -> length = lengths.get(lengths.size() - 1);
			}

			// Iterate through all words present on the current board.
			for (String s : options) {
				// If the length of the word is equal to the length that is
				// chosen.
				if (s.length() == length) {
					currentWord = s;
				}
			}
			
			// Check if AI was stopped prematurely through stopSearching()
			if (!isFindingWord) {
				currentWord = "";
				((Timer) e.getSource()).stop();
			}
			isFindingWord = false;
			// Highlight current word.
			highlightCurrentWord();
			// Display current word.
			board.getWordDisplay().setText(currentWord);
			// Process current word (always valid).
			board.processValidWord(currentWord);
			// Clear board.
			board.clearBoard(1);
			// Debugging output.
			System.out.println(currentWord);
			wordFound = true;
		});

		// Set the delay of the AI based on difficulty.
		// Must be greater than 500 ms
		// Enhanced switch statement
// https://www.geeksforgeeks.org/enhancements-for-switch-statement-in-java-13/
		switch (difficulty) {
			case 0 -> findWord.setInitialDelay(
					(int) (10000 * random.nextDouble(0.6, 1.4)));
			case 1 -> findWord.setInitialDelay(
					(int) (6000 * random.nextDouble(0.6, 1.4)));
			case 2 -> findWord.setInitialDelay(
					(int) (3000 * random.nextDouble(0.6, 1.4)));
			case 3 -> findWord.setInitialDelay(600);
		}
		findWord.setRepeats(false);
		findWord.start();
	}

	/**
	 * Prematurely stops the AI from searching for a word
	 * (e.g. when the game is stopped while the AI is taking its turn)
	 */
	public void stopSearching() {
		isFindingWord = false;
	}
}