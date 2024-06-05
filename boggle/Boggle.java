package boggle;

import javax.swing.*;
import java.awt.*;

public class Boggle extends JFrame {
	private GameScreen gameScreen;

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

		//gameScreen = new GameScreen(this);
		MenuScreen menuScreen = new MenuScreen(this);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setBackground(Color.black);
		this.setResizable(false);
		this.setTitle("Boggle");
		this.setVisible(true);
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
