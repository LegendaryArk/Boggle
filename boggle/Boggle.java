/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class contains all the boggle game elements
 * Extends JFrame to create a window
 */
public class Boggle extends JFrame {
	// Intro Screen.
	private IntroScreen introScreen;
	// Menu Screen.
	private MenuScreen menuScreen;
	// Mode Selection Screen.
	private ModeSelectionScreen modeSelectionScreen;
	// Game Screen.
	private GameScreen gameScreen;
	// Pause Overlay Screen.
	private PauseOverlay pauseOverlay;
	// End Game Screen.
	private EndGameScreen endgameScreen;
	// Guide Screen.
	private GuideScreen guideScreen;
	// Credits Screen.
	private CreditsScreen creditsScreen;
	// Settings Screen.
	private SettingsScreen settingsScreen;
	// Exit Screen.
	private ExitScreen exitScreen;

	// Height and width of the screen.
	private int screenWidth, screenHeight;
	// Aspect ratio of the screen.
	private final double ASPECT_RATIO = 16 / 9.0;
	
	// Minimum word length.
	private int minimumWordLength;
	// Target points.
	private int targetPoints;
	// Initial time.
	private int initialTime;
	// Time increment.
	private int timeIncrement;
	// AI difficulty
	private int AIDifficulty;
	// Background music type
	private int backgroundMusicType;

	/**
	 * Constructor
	 */
	public Boggle() {
		System.out.println("Launching Boggle... Get ready to play!");
		System.out.println("Verifying files... (This may take a few seconds)");

		// Set the dimensions of the screen based on the aspect ratio.
		screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		if (screenWidth > screenHeight) {
			// Adjust screen width.
			screenHeight = (int) (screenWidth / ASPECT_RATIO);
		} else {
			// Adjust screen height.
			screenWidth = (int) (screenHeight * ASPECT_RATIO);
		}
		// Set the screen and overlay.
		settingsScreen = new SettingsScreen(this);
		updateSettings();
		// Set the intro screen.
		introScreen = new IntroScreen(this);
		// Set the menu screen.
		menuScreen = new MenuScreen(this);
		// Set the mode selection screen.
		modeSelectionScreen = new ModeSelectionScreen(this);
		// Set the game screen.
		gameScreen = new GameScreen(this, false);
		// Set the pause overlay screen.
		pauseOverlay = new PauseOverlay(this);
		// Set the guide screen.
		guideScreen = new GuideScreen(this);
		// Set the credits screen
		creditsScreen = new CreditsScreen(this);
		// Set end game screen.
		endgameScreen = new EndGameScreen(this, 2);
		// Set exit screen.
		exitScreen = new ExitScreen(this);
		// Set the initial content to the intro screen.
		this.setContentPane(introScreen);

		// Set the frame properties.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setBackground(Color.black);
		this.setResizable(false);
		this.setTitle("Boggle");
		this.setVisible(true);

		// Start the intro screen.
		introScreen.start();
	}

	/**
	 * This method switches to the menu screen.
	 */
	public void menuScreen() {
		this.setContentPane(menuScreen);
		menuScreen.startBackgroundMusic();
		repaint();
	}

	/**
	 * This method switches to the mode selection screen.
	 */
	public void modeSelectionScreen() {
		this.setContentPane(modeSelectionScreen);
		repaint();
	}

	/**
	 * This method switches to the game screen.
	 * @param isAI if player selects player vs AI.
	 */
	public void gameScreen(boolean reset, boolean isAI) {
		this.setContentPane(gameScreen);
		if (reset) {
			gameScreen.resetGame(isAI);
		}
		gameScreen.resumeGame();
		gameScreen.startBackgroundMusic();
		repaint();
	}

	/**
	 * This method switches to the pause overlay screen.
	 */
	public void pauseOverlay() {
		this.setContentPane(pauseOverlay);
		repaint();
	}

	/**
	 * This method switches to the end game screen.
	 * @param winner to display the winner.
	 */
	public void endgameScreen(int winner) {
		endgameScreen = new EndGameScreen(this, winner);
		this.setContentPane(endgameScreen);
		repaint();
	}

	/**
	 * This method switches to the guide screen.
	 */
	public void guideScreen() {
		this.setContentPane(guideScreen);
		repaint();
	}

	/**
	 * This methdod switches to the settings screen.
	 */
	public void settingsScreen() {
		this.setContentPane(settingsScreen);
		repaint();
	}

	/**
	 * This method switches to the credits screen.
	 */
	public void creditsScreen() {
		this.setContentPane(creditsScreen);
		creditsScreen.start();
		repaint();
	}

	/**
	 * This method switches to the exit screen.
	 */
	public void exitScreen() {
		this.setContentPane(exitScreen);
		repaint();
		exitScreen.start();
	}

	/**
	 * This method gets the menu screen.
	 * @return the menu screen.
	 */
	public MenuScreen getMenuScreen() {
		return menuScreen;
	}

	/**
	 * This method gets the get mode screen.
	 * @return the mode selection screen.
	 */
	public ModeSelectionScreen getModeSelectionScreen() {
		return modeSelectionScreen;
	}

	/**
	 * This method gets the game screen.
	 * @return the game screen.
	 */
	public GameScreen getGameScreen() {
		return gameScreen;
	}

	/**
	 * This method gets the pause overlay screen.
	 * @return the pause overlay screen.
	 */
	public PauseOverlay getPauseOverlay() {
		return pauseOverlay;
	}

	/**
	 * This method gets the end game screen.
	 * @return the end game screen.
	 */
	public EndGameScreen getEndgameScreen() {
		return endgameScreen;
	}

	/**
	 * This method gets the guide screen.
	 * @return the guide screen.
	 */
	public GuideScreen getGuideScreen() {
		return guideScreen;
	}

	/**
	 * This method gets the settings screen.
	 * @return the settings screen.
	 */
	public SettingsScreen getSettingsScreen() {
		return settingsScreen;
	}

	/**
	 * This method gets the credits screen.
	 * @return the credits screen.
	 */
	public CreditsScreen getCreditsScreen() {
		return creditsScreen;
	}

	/**
	 * This method gets the AI.
	 * @return the AI.
	 */
	public boolean isAI() {
		return gameScreen.isAI();
	}

	/**
	 * This method gets the minimum word length.
	 * @return the minimum word length.
	 */
	public int getMinimumWordLength() {
		return minimumWordLength;
	}

	/**
	 * This method gets the target points.
	 * @return the target points.
	 */
	public int getTargetPoints() {
		return targetPoints;
	}

	/**
	 * This method gets the initial time.
	 * @return the initial time.
	 */
	public int getInitialTime() {
		return initialTime;
	}

	/**
	 * This method gets the time increment.
	 * @return the time increment.
	 */
	public int getTimeIncrement() {
		return timeIncrement;
	}

	/**
	 * This method gets the AI Difficulty.
	 * @return the AI Difficulty.
	 */
	public int getAIDifficulty() {
		return AIDifficulty;
	}

	/**
	 * This method gets the background music type.
	 * @return the background music type.
	 */
	public int getBackgroundMusicType() {
		return backgroundMusicType;
	}

	/**
	 * This method updates the game settings based on the settings screen.
	 */
	public void updateSettings() {
		minimumWordLength = settingsScreen.getMinimumWordLength();
		targetPoints = settingsScreen.getTargetPoints();
		initialTime = settingsScreen.getInitialTime();
		timeIncrement = settingsScreen.getTimeIncrement();
		AIDifficulty = settingsScreen.getAIDifficulty();
		backgroundMusicType = settingsScreen.getMusicType();
	}

	/**
	 * This method updates the main frame to show changes.
	 */
	@Override
	public void repaint() {
		super.revalidate();
		super.repaint();
	}

	/**
	 * This method gets the screen width.
	 * @return screen width.
	 */
	public int getScreenWidth() {
		return screenWidth;
	}

	/**
	 * This method gets the screen height.
	 * @return screen height.
	 */
	public int getScreenHeight() {
		return screenHeight;
	}

	/**
	 * This method sorts an array list of integers using merge sort.
	 * @param list ArrayList of integers.
	 */
	public static void sort(ArrayList<Integer> list) {
		if (list.size() <= 1) {
			return;
		}

		ArrayList<Integer> left = new ArrayList<>(), right = new ArrayList<>();

		int m = list.size() / 2;
		for (int i = 0; i < list.size(); i++) {
			if (i < m) {
				left.add(list.get(i));
			} else {
				right.add(list.get(i));
			}
		}

		sort(left);
		sort(right);
		merge(left, right, list);
	}

	/**
	 * Helper method for merge sort.
	 * @param left left auxiliary array.
	 * @param right right auxiliary array.
	 * @param list parent array.
	 */
	private static void merge(ArrayList<Integer> left,
	                          ArrayList<Integer> right,
	                          ArrayList<Integer> list) {
		int l = 0, r = 0, i = 0;

		while (l < left.size() && r < right.size()) {
			if (left.get(l) < right.get(r)) {
				list.set(i++, left.get(l++));
			} else {
				list.set(i++, right.get(r++));
			}
		}

		while (l < left.size()) {
			list.set(i++, left.get(l++));
		}
		while (r < right.size()) {
			list.set(i++, right.get(r++));
		}
	}
}