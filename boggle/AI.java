package boggle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AI extends Player {
	private String currWord = "";
	private boolean wordHighlighted = false;
	private boolean wordFound = false;
	private ArrayList<String> dict = new ArrayList<>();
	private boolean[][] vis = new boolean[5][5];

	private ArrayList<Pair> coord = new ArrayList<>();

	private Board board;

	private int difficulty;
	private ArrayList<Integer> lengths = new ArrayList<>();
	private ArrayList<String> options = new ArrayList<>();
	private Random rand = new Random();

	public AI(Board board, int difficulty, JLabel plrDisplay, JLabel ptsDisplay, Clock timer) {
		super(plrDisplay, ptsDisplay, timer);

		this.board = board;
		this.difficulty = difficulty;

		try {
			Scanner sc = new Scanner(new File("src/Boggle/boggle/resources/dictionary.txt"));
			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) - 'a' < 0 || s.charAt(i) - 'a' > 26) {
						s = s.substring(0, i) + s.substring(i + 1);
					}
				}
				dict.add(s);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Fatal error: Unable to find and load dictionary");
			System.exit(0);
		}
	}

	private void search(int r, int c, String s, int p, boolean found) {
		if (p == s.length() - 1) {
			if (!found) {
				if (board.getWordsEntered().query(s.toUpperCase())) {
					return;
				}

				options.add(s);
				if (!lengths.contains(s.length())) {
					lengths.add(s.length());
				}
			} else {
				for (int i = 0; i < coord.size(); i++) {
					int x = coord.get(i).x(), y = coord.get(i).y();
					if (!wordHighlighted) {
						board.getDiceGrid()[x][y].select();

						if (i == 0) continue;
						int lx = coord.get(i - 1).x(), ly = coord.get(i - 1).y();
						board.connect(y, x, ly - y, lx - x, 1);
					}
				}
				wordHighlighted = true;
			}
			return;
		}

		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				int nr = r + dx, nc = c + dy;
				if (0 <= nr && nr < 5 && 0 <= nc && nc < 5 && !vis[nr][nc] && Character.toUpperCase(s.charAt(p + 1)) == board.getDiceGrid()[nr][nc].getTopFace()) {
					vis[nr][nc] = true;
					if (found) coord.add(new Pair(nr, nc));
					search(nr, nc, s, p + 1, found);
					if (found) coord.remove(coord.size() - 1);
					vis[nr][nc] = false;
				}
			}
		}
	}

	public void get() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (currWord.isEmpty()) {
					continue;
				}

				if (currWord.toUpperCase().charAt(0) == board.getDiceGrid()[i][j].getTopFace()) {
					coord.add(new Pair(i, j));
					vis[i][j] = true;
					search(i, j, currWord, 0, true);
					vis[i][j] = false;
					coord.remove(coord.size() - 1);
				}

				if (wordHighlighted) {
					return;
				}
			}
		}
	}

	@Override
	public void startTurn() {
		currWord = "";
		wordFound = false;
		wordHighlighted = false;
		super.startTurn();

		Timer delay = new Timer(0, e -> {
			for (String s : dict) {
				if (!board.getWordsEntered().query(s.toUpperCase())) {
					for (int i = 0; i < 5; i++) {
						for (int j = 0; j < 5; j++) {
							if (s.isEmpty()) continue;

							if (s.toUpperCase().charAt(0) == board.getDiceGrid()[i][j].getTopFace()) {
								vis[i][j] = true;
								search(i, j, s, 0, false);
								vis[i][j] = false;
							}
						}
					}
				}
			}

			Boggle.sort(lengths);
			for (int i = options.size(); i > 1; i--) {
				int randInd = rand.nextInt(i);
				String tmp = options.get(i - 1);
				options.set(i - 1, options.get(randInd));
				options.set(randInd, tmp);
			}

			while (lengths.get(0) < 3) lengths.remove(0);

			int l = 0;
			switch (difficulty) {
				case 0 -> l = lengths.get(0);
				case 1 -> l = lengths.get(rand.nextInt(lengths.size() / 2));
				case 2 -> l = lengths.get(lengths.size() / 2 + rand.nextInt(lengths.size() / 2));
				case 3 -> l = lengths.get(lengths.size() - 1);
			}
			for (String s : options) {
				if (s.length() == l) {
					currWord = s;
				}
			}

			get();
			board.getWordDisplay().setText(currWord.toUpperCase());
			board.validWord(currWord.toUpperCase());
			board.clearBoard(1);
			System.out.println(currWord);
			wordFound = true;
		});
		switch (difficulty) {
			case 0 -> delay.setInitialDelay((int) (10000 * rand.nextDouble(0.6, 1.4)));
			case 1 -> delay.setInitialDelay((int) (6000 * rand.nextDouble(0.6, 1.4)));
			case 2 -> delay.setInitialDelay((int) (3000 * rand.nextDouble(0.6, 1.4)));
			case 3 -> delay.setInitialDelay(600); // Must be greater than 500
		}
		delay.setRepeats(false);
		delay.start();
	}

	public boolean isWordFound() {
		return wordFound;
	}
}
