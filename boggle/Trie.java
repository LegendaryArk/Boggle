package boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Trie {
	private int[][] ch;
	private boolean[] vis;
	private int cnt;

	public Trie(int N, int M) {
		ch = new int[N][M];
		vis = new boolean[N];
	}

	public void init() {
		try {
			Scanner sc = new Scanner(new File("src/Boggle/boggle/resources/dictionary.txt"));
			while (sc.hasNextLine()) {
				insert(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Fatal error: Unable to find and load dictionary");
			System.exit(0);
		}
	}

	public void insert(String s) {
		s = s.toLowerCase();

		int p = 0;
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			if (c < 0 || c > 26) continue;
			if (ch[p][c] == 0) ch[p][c] = ++cnt;
			p = ch[p][c];
		}
		vis[p] = true;
	}
	public boolean query(String s) {
		s = s.toLowerCase();

		int p = 0;
		for (int i = 0; i < s.length(); i++) {
			int c = s.charAt(i) - 'a';
			if (ch[p][c] == 0) return false;
			p = ch[p][c];
		}
		return vis[p];
	}
}
