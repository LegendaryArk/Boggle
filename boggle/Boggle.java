package boggle;

import javax.swing.*;
import java.awt.*;

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
//		menuScreen = new MenuScreen(this);
		gameScreen = new GameScreen(this, true);
		pauseOverlay = new PauseOverlay(this);
//		creditsScreen = new CreditsScreen(this);
		this.setContentPane(gameScreen);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setBackground(Color.black);
		this.setResizable(false);
		this.setTitle("Boggle");
		this.setVisible(true);
	}

	public void menuScreen() {

	}
	public void gameScreen() {
		this.setContentPane(gameScreen);
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
	}

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
}
