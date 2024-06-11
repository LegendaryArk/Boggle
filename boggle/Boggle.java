package boggle;

import javax.swing.*;
import java.awt.*;

public class Boggle extends JFrame {
	private MenuScreen menuScreen;
	private GameScreen gameScreen;
	private EndGameScreen endgameScreen;

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

//		menuScreen = new MenuScreen(this);
//		menuScreen.setVisible(false);
		gameScreen = new GameScreen(this);
//		endgameScreen = new EndGameScreen(this, 1);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setBackground(Color.black);
		this.setResizable(false);
		this.setTitle("Boggle");
		this.setVisible(true);
	}

	public void menuScreen() {
//		menuScreen.setVisible(true);
		gameScreen.setVisible(false);
	}
	public void gameScreen() {
//		menuScreen.setVisible(false);
		gameScreen.setVisible(true);
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
