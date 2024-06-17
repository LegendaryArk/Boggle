package boggle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Boggle extends JFrame {
	private IntroScreen introScreen;
	private MenuScreen menuScreen;
	private ModeSelectionScreen modeSelectionScreen;
	private GameScreen gameScreen;
	private PauseOverlay pauseOverlay;
	private EndGameScreen endgameScreen;
	private GuideScreen guideScreen;
	private CreditsScreen creditsScreen;
	private SettingsScreen settingsScreen;

	private int screenWidth, screenHeight;
	private final double aspectRatio = 16 / 9.0;

	private int minWordLen;
	private int targetPts;
	private int initTime;
	private int timeIncrement;
	private int aiDifficulty;
	private int bgmType;

	public Boggle() {
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		if (screenWidth > screenHeight) {
			screenHeight = (int) (screenWidth / aspectRatio);
		} else {
			screenWidth = (int) (screenHeight * aspectRatio);
		}

		settingsScreen = new SettingsScreen(this, 0);
		updateSettings();
		introScreen = new IntroScreen(this);
		menuScreen = new MenuScreen(this);
		modeSelectionScreen = new ModeSelectionScreen(this);
		gameScreen = new GameScreen(this, false);
		pauseOverlay = new PauseOverlay(this);
		guideScreen = new GuideScreen(this);
		creditsScreen = new CreditsScreen(this);
		this.setContentPane(introScreen);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setBackground(Color.black);
		this.setResizable(false);
		this.setTitle("Boggle");
		this.setVisible(true);

		introScreen.start();
	}

	public void menuScreen() {
		this.setContentPane(menuScreen);
		menuScreen.startBgm();
		repaint();
	}
	public void modeSelectionScreen() {
		this.setContentPane(modeSelectionScreen);
		repaint();
	}
	public void gameScreen(boolean isAI) {
		this.setContentPane(gameScreen);
		gameScreen.resetGame(isAI);
		gameScreen.resumeGame();
		gameScreen.startBgm();
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
	public void guideScreen() {
		this.setContentPane(guideScreen);
		repaint();
	}
	public void settingsScreen(int prevScreen) {
		settingsScreen.setPrevScreen(prevScreen);
		this.setContentPane(settingsScreen);
		repaint();
	}
	public void creditsScreen() {
		this.setContentPane(creditsScreen);
		repaint();
		creditsScreen.ret();
	}

	public MenuScreen getMenuScreen() {
		return menuScreen;
	}
	public ModeSelectionScreen getModeSelectionScreen() {
		return modeSelectionScreen;
	}
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	public PauseOverlay getPauseOverlay() {
		return pauseOverlay;
	}
	public EndGameScreen getEndgameScreen() {
		return endgameScreen;
	}
	public GuideScreen getGuideScreen() {
		return guideScreen;
	}
	public SettingsScreen getSettingsScreen() {
		return settingsScreen;
	}
	public CreditsScreen getCreditsScreen() {
		return creditsScreen;
	}

	public boolean isAgainstAI() {
		return gameScreen.isAI();
	}

	public int getMinWordLen() {
		return minWordLen;
	}
	public int getTargetPts() {
		return targetPts;
	}
	public int getInitTime() {
		return initTime;
	}
	public int getTimeIncrement() {
		return timeIncrement;
	}
	public int getAIDifficulty() {
		return aiDifficulty;
	}
	public int getBgmType() {
		return bgmType;
	}

	public void updateSettings() {
		minWordLen = settingsScreen.getMinWordLen();
		targetPts = settingsScreen.getTargetPts();
		initTime = settingsScreen.getInitTime();
		timeIncrement = settingsScreen.getTimeIncrement();
		aiDifficulty = settingsScreen.getAIDifficulty();
		bgmType = settingsScreen.getMusicType();
	}

	@Override
	public void repaint() {
		super.revalidate();
		super.repaint();
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
