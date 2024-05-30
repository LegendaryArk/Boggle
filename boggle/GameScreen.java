package boggle;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
	private JLayeredPane layers = new JLayeredPane();
	private JLabel background = new JLabel();
	private JLabel playerTurnInd = new JLabel();
	private JLabel wordDisplay = new JLabel();
	private JButton homeBtn = new JButton();
	private JButton settingsBtn = new JButton();
	private JLabel wordList = new JLabel();
	private JScrollPane wordListScroll = new JScrollPane();

	private Boggle mainFrame;

	public GameScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, w, h);

		layers.setBounds(0, 0, w, h);
		layers.setLayout(new BorderLayout());

		ImageIcon bg = new ImageIcon(getClass().getResource("assets/GameScreenBg.png"));
		background.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBounds(0, 0, w, h);
		layers.add(background, BorderLayout.CENTER, 0);

		this.add(layers, BorderLayout.CENTER);

		mainFrame.add(this);
	}
}
