package boggle;

import javax.swing.*;
import java.awt.*;

public class EndGameScreen extends JPanel {
	private int winner; // 0 - draw, 1 - player 1, 2 - player 2, 3 - λBoggle

	private JLayeredPane layers = new JLayeredPane();
	private JLabel background = new JLabel();

	private ImageIcon draw = new ImageIcon(getClass().getResource("assets/DrawBg.gif"));
	private ImageIcon plr1 = new ImageIcon(getClass().getResource("assets/Plr1WinBg.gif"));
	private ImageIcon plr2 = new ImageIcon(getClass().getResource("assets/Plr2WinBg.gif"));
	private ImageIcon lambdaBoggle = new ImageIcon(getClass().getResource("assets/Plr2WinBg.gif"));

	private JButton homeBtn = new JButton();
	private JButton replayBtn = new JButton();
	private JButton exitBtn = new JButton();

	private Boggle mainFrame;
	private int w, h;

	public EndGameScreen(Boggle mainFrame, int winner) {
		this.winner = winner;
		this.mainFrame = mainFrame;
		this.w = mainFrame.getScreenWidth();
		this.h = mainFrame.getScreenHeight();

		GridBagConstraints c;

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		draw = new ImageIcon(draw.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		plr1 = new ImageIcon(plr1.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		plr2 = new ImageIcon(plr2.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		lambdaBoggle = new ImageIcon(lambdaBoggle.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		switch (winner) {
			case 0 -> background.setIcon(draw);
			case 1 -> background.setIcon(plr1);
			case 2 -> background.setIcon(plr2);
			case 3 -> background.setIcon(lambdaBoggle);
		}
		background.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(background, c);
		layers.setLayer(background, 0);

		this.add(layers, new GridBagConstraints());
	}
}