/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class displays the end game screen after the game ends
 */
public class EndGameScreen extends JPanel {
	private final int winner; // 0 - draw, 1 - player 1, 2 - player 2, 3 - λBoggle

	// Layered pane for the end screen layers.
	private final JLayeredPane layers = new JLayeredPane();
	// Label to hold background image display.
	private final JLabel background = new JLabel();

	// Draw animation .
	private ImageIcon drawGif = new ImageIcon(getClass().getResource("assets/DrawBg.gif"));
	// Draw Screen.
	private ImageIcon draw = new ImageIcon(getClass().getResource("assets/DrawBg.png"));

	// Player 1 Win animation.
	private ImageIcon plr1Gif = new ImageIcon(getClass().getResource("assets/Plr1WinBg.gif"));
	// Player 1 Win screen.
	private ImageIcon plr1 = new ImageIcon(getClass().getResource("assets/Plr1WinBg.png"));

	// Player 2 Win animation.
	private ImageIcon plr2Gif = new ImageIcon(getClass().getResource("assets/Plr2WinBg.gif"));
	// Player 2 Win screen.
	private ImageIcon plr2 = new ImageIcon(getClass().getResource("assets/Plr2WinBg.png"));

	// AI win animation.
	private ImageIcon lambdaBoggleGif = new ImageIcon(getClass().getResource("assets/AIWinBg.gif"));
	// AI win screen.
	private ImageIcon lambdaBoggle = new ImageIcon(getClass().getResource("assets/AIWinBg.png"));

	// Menu button
	private final OptionButton menuBtn;
	// Default menu button.
	private final ImageIcon menuDefault = new ImageIcon(getClass().getResource("assets/EndMenuBtnDefault.png"));
	// Hover menu button.
	private final ImageIcon menuHover = new ImageIcon(getClass().getResource("assets/EndMenuBtnHover.png"));
	// Press menu button.
	private final ImageIcon menuPress = new ImageIcon(getClass().getResource("assets/EndMenuBtnPress.png"));

	// Play again button
	private final OptionButton playAgainBtn;
	// Default play again button.
	private final ImageIcon playAgainDefault = new ImageIcon(getClass().getResource("assets/PlayAgainBtnDefault.png"));
	// Hover play again button.
	private final ImageIcon playAgainHover = new ImageIcon(getClass().getResource("assets/PlayAgainBtnHover.png"));
	// Press play again button.
	private final ImageIcon playAgainPress = new ImageIcon(getClass().getResource("assets/PlayAgainBtnPress.png"));
	
	// Exit button
	private final OptionButton exitBtn;
	// Default exit button.
	private final ImageIcon exitDefault = new ImageIcon(getClass().getResource("assets/EndExitBtnDefault.png"));
	// Hover exit button.
	private final ImageIcon exitHover = new ImageIcon(getClass().getResource("assets/EndExitBtnHover.png"));
	// Press exit button
	private final ImageIcon exitPress = new ImageIcon(getClass().getResource("assets/EndExitBtnPress.png"));

	// Main frame for gam
	private final Boggle mainFrame;
	// Width
	private final int w;
	// Height
	private final int h;

	/**
	 * Outputs a screen dislaying either that Player 1 won, Player 2 won, 
	 * AI won, or draw.
	 * @param mainFrame main frame for game
	 * @param winner the winner of the game.
	 */
	public EndGameScreen(Boggle mainFrame, int winner) {
		this.winner = winner;
		this.mainFrame = mainFrame;
		// Get width and height of the screen.
		this.w = mainFrame.getScreenWidth();
		this.h = mainFrame.getScreenHeight();

		GridBagConstraints c;
		
		// Set background colour, layout and dimensions of panel.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));
		
		// Set background colour, layout and dimensions of layers.
		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));
		
		// Set draw animation.
		drawGif = new ImageIcon(drawGif.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		// Set draw screen.
		draw = new ImageIcon(draw.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		
		// Set Player 1 win animation.
		plr1Gif = new ImageIcon(plr1Gif.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		// Set Player 1 win screen.
		plr1 = new ImageIcon(plr1.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));

		// Set Player 2 win animation.
		plr2Gif = new ImageIcon(plr2Gif.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		// Set Player 2 win screen.
		plr2 = new ImageIcon(plr2.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		
		// Set AI win animation.
		lambdaBoggleGif = new ImageIcon(lambdaBoggleGif.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		// Set AI win screen.
		lambdaBoggle = new ImageIcon(lambdaBoggle.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH));
		
		// Set the background image based on the winner
		switch (winner) {
			case 0 -> background.setIcon(drawGif); // Draw.
			case 1 -> background.setIcon(plr1Gif); // Player 1 wins.
			case 2 -> background.setIcon(plr2Gif); // Player 2 wins.
			case 3 -> background.setIcon(lambdaBoggleGif); // AI wins.
		}
		// Set background colour and grid constraints
		background.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 1;
		layers.add(background, c);
		layers.setLayer(background, 0);

		// Set menu button's colour and grid constraints
		menuBtn = new OptionButton(0.2 * w, 0.1 * h, menuDefault, menuHover, menuPress, e -> mainFrame.menuScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, (int) (0.1 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		layers.add(menuBtn, c);
		layers.setLayer(menuBtn, 1);

		// Set play again button's colour and grid constraints
		playAgainBtn = new OptionButton(0.2 * w, 0.1 * h, playAgainDefault, playAgainHover, playAgainPress, e -> mainFrame.gameScreen(mainFrame.isAI()));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, 0, (int) (0.1 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		layers.add(playAgainBtn, c);
		layers.setLayer(playAgainBtn, 1);

		// Set exit button's 's colour and grid constraints
		exitBtn = new OptionButton(0.2 * w, 0.1 * h, exitDefault, exitHover, exitPress, e -> mainFrame.exitScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets(0, 0, (int) (0.1 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		layers.add(exitBtn, c);
		layers.setLayer(exitBtn, 1);

		// Timer to switch from animation to screen for 2000 milliseconds
		Timer stopGif = new Timer(0, e -> {
			// Set the background image based on the winner
			switch (winner) {
				case 0 -> background.setIcon(draw);
				case 1 -> background.setIcon(plr1);
				case 2 -> background.setIcon(plr2);
				case 3 -> background.setIcon(lambdaBoggle);
			}
		});
		// Set the delay to 2000 milliseconds
		stopGif.setInitialDelay(2000);
		// Star timer
		stopGif.start();
		
		// Adding the GIF to LayeredPane.
		this.add(layers, new GridBagConstraints());
	}
}