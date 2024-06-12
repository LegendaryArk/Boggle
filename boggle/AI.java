package boggle;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AI extends Player {
	private String currWord = "";
	private ArrayList<String> dict = new ArrayList<>();
	private boolean[][] vis = new boolean[5][5];

	private ArrayList<Long> coord = new ArrayList<>();

	private Board board;

	public AI(Board board, JLabel plrDisplay, JLabel ptsDisplay, Clock timer) {
		super(plrDisplay, ptsDisplay, timer);

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

		this.board = board;
	}

	private void search(int r, int c, String s, int p, boolean found) {
		if (p == s.length() - 1) {
			if (!found) {
				if (s.length() > currWord.length()) currWord = s;
			} else {
				for (long e : coord) {
					int x = (int) (e >> 32), y = (int) e;
					board.getDiceGrid()[x][y].select();
				}
			}
			return;
		}

		for (int dx = -1; dx <= 1; dx++) {
			for (int dy = -1; dy <= 1; dy++) {
				int nr = r + dx, nc = c + dy;
				if (0 <= nr && nr < 5 && 0 <= nc && nc < 5 && !vis[nr][nc] && Character.toUpperCase(s.charAt(p + 1)) == board.getDiceGrid()[nr][nc].getTopFace()) {
					vis[nr][nc] = true;
					if (found) coord.add((long) nr << 32 | nc);
					search(nr, nc, s, p + 1, found);
					if (found) coord.remove(coord.size() - 1);
					vis[nr][nc] = false;
				}
			}
		}
	}

	public void show() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (currWord.isEmpty()) continue;

				if (currWord.toUpperCase().charAt(0) == board.getDiceGrid()[i][j].getTopFace()) {
					coord.add((long) i << 32 | j);
					vis[i][j] = true;
					search(i, j, currWord, 0, true);
					vis[i][j] = false;
					coord.remove(coord.size() - 1);
				}
			}
		}
	}

	@Override
	public void startTurn() {
		currWord = "";
		super.startTurn();

		for (int k = 0; k < dict.size(); k++) {
			if (!board.getWordsEntered().query(dict.get(k).toUpperCase())) {
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						if (dict.get(k).isEmpty()) continue;

						if (dict.get(k).toUpperCase().charAt(0) == board.getDiceGrid()[i][j].getTopFace()) {
							vis[i][j] = true;
							search(i, j, dict.get(k), 0, false);
							vis[i][j] = false;
						}
					}
				}
			}
		}
		show();
		board.validWord(currWord.toUpperCase());
		System.out.println(currWord);
	}
}
