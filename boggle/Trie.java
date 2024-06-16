package boggle;

public class Trie {
	private int[][] ch;
	private boolean[] vis;
	private int cnt;

	public Trie(int N, int M) {
		ch = new int[N][M];
		vis = new boolean[N];
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
