package boggle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Boggle extends JFrame {
	private IntroScreen introScreen;
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private PauseOverlay pauseOverlay;
	private EndGameScreen endgameScreen;
	private CreditsScreen creditsScreen;

	private int screenWidth, screenHeight;
	private final double aspectRatio = 16 / 9.0;

	public Boggle() {
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		if (screenWidth > screenHeight) {
			screenHeight = (int) (screenWidth / aspectRatio);
		} else {
			screenWidth = (int) (screenHeight * aspectRatio);
		}

//		introScreen = new IntroScreen(this);
		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this, true);
		pauseOverlay = new PauseOverlay(this);
		creditsScreen = new CreditsScreen(this);
		this.setContentPane(gameScreen);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setBackground(Color.black);
		this.setResizable(false);
		this.setTitle("Boggle");
		this.setVisible(true);

//		introScreen.start();
	}

	public void menuScreen() {
		this.setContentPane(menuScreen);
		repaint();
	}
	public void gameScreen() {
		this.setContentPane(gameScreen);
		gameScreen.resumeGame();
		repaint();
	}
	public void pauseOverlay() {
		this.setContentPane(pauseOverlay);
		repaint();
	}
	public void endgameScreen(int winner) {
		endgameScreen = new EndGameScreen(this, winner);
		this.setContentPane(endgameScreen);
		repaint();
	}
	public void creditsScreen() {
		this.setContentPane(creditsScreen);
		repaint();
		creditsScreen.ret();
	}

	public void repaint() {
		super.revalidate();
		super.repaint();
	}

	public void restartGame() {
		gameScreen = new GameScreen(this, true);
		gameScreen();
	}

	public int getScreenWidth() {
		return screenWidth;
	}
	public int getScreenHeight() {
		return screenHeight;
	}
	public double getAspectRatio() {
		return aspectRatio;
	}

	public static void sort(ArrayList<Integer> arr) {
		if (arr.size() <= 1) {
			return;
		}

		ArrayList<Integer> left = new ArrayList<>(), right = new ArrayList<>();

		int mid = arr.size() / 2;
		for (int i = 0; i < arr.size(); i++) {
			if (i < mid) left.add(arr.get(i));
			else right.add(arr.get(i));
		}

		sort(left);
		sort(right);
		merge(left, right, arr);
	}

	private static void merge(ArrayList<Integer> left, ArrayList<Integer> right, ArrayList<Integer> arr) {
		int l = 0, r = 0, i = 0;

		while (l < left.size() && r < right.size()) {
			if (left.get(l) < right.get(r)) arr.set(i++, left.get(l++));
			else arr.set(i++, right.get(r++));
		}

		while (l < left.size()) {
			arr.set(i++, left.get(l++));
		}
		while (r < right.size()) {
			arr.set(i++, right.get(r++));
		}
	}
}
