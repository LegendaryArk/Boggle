package boggle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameScreen extends JPanel {
	private JLayeredPane layers = new JLayeredPane();
	private JLabel background = new JLabel();

	private JPanel content = new JPanel();

	private Player plr1;
	private Player plr2;
	private JLabel plr1Label = new JLabel();
	private JLabel plr1TimeDisplay = new JLabel();
	private JLabel plr1PtsDisplay = new JLabel();
	private JLabel plr2Label = new JLabel();
	private JLabel plr2TimeDisplay = new JLabel();
	private JLabel plr2PtsDisplay = new JLabel();

	private WordList wordList;
	private JPanel wordListBg = new JPanel();
	private JScrollPane wordListScroll = new JScrollPane();

	private Board board;
	private JLabel wordDisplay = new JLabel();
	private JPanel boardBg = new JPanel();

	private JButton homeBtn = new JButton();
	private JButton settingsBtn = new JButton();

	private Boggle mainFrame;

	public GameScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		GridBagConstraints c;

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		ImageIcon bg = new ImageIcon(getClass().getResource("assets/GameScreenBg.png"));
		background.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBounds(0, 0, w, h);
		background.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(background, c);
		layers.setLayer(background, 0);

		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(w, h));

		wordDisplay.setMinimumSize(new Dimension((int) (0.365 * w), (int) (0.09 * h)));
		wordDisplay.setPreferredSize(new Dimension((int) (0.365 * w), (int) (0.09 * h)));
		wordDisplay.setHorizontalTextPosition(JLabel.CENTER);
		wordDisplay.setVerticalTextPosition(JLabel.CENTER);
		wordDisplay.setHorizontalAlignment(JLabel.CENTER);
		wordDisplay.setVerticalAlignment(JLabel.CENTER);
		wordDisplay.setFont(new Font("Arial", Font.PLAIN, 40));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.0395 * h), (int) (0.059 * w), (int) (0.02 * h), (int) (0.05 * w));
		c.weightx = 1;
		c.weighty = 1;
		content.add(wordDisplay, c);

		wordListBg.setLayout(new GridBagLayout());
		wordListBg.setMinimumSize(new Dimension((int) (0.22 * w), (int) (1.2 * h)));
		wordListBg.setPreferredSize(new Dimension((int) (0.22 * w), (int) (1.2 * h)));
		wordListBg.setOpaque(false);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 5;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.16 * h), (int) (0.023 * w), 0, (int) (0.00625 * w));
		c.weightx = 1;
		c.weighty = 1;

		wordListScroll.setViewportView(wordListBg);
		wordListScroll.setMinimumSize(new Dimension((int) (0.23475 * w), (int) (0.802 * h)));
		wordListScroll.setPreferredSize(new Dimension((int) (0.23475 * w), (int) (0.802 * h)));
		wordListScroll.setOpaque(false);
		content.add(wordListScroll, c);

		wordList = new WordList(mainFrame, wordListBg, wordListScroll);

		plr1Label.setMinimumSize(new Dimension((int) (0.22 * w), (int) (0.09 * h)));
		plr1Label.setPreferredSize(new Dimension((int) (0.22 * w), (int) (0.09 * h)));
		plr1Label.setHorizontalTextPosition(JLabel.CENTER);
		plr1Label.setHorizontalAlignment(JLabel.CENTER);
		plr1Label.setText("Player 1");
		plr1Label.setFont(new Font("Verdana", Font.BOLD, 50));
		plr1Label.setForeground(Color.WHITE);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.ABOVE_BASELINE;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.0875 * h), 0, 0, (int) (0.01 * w));
		content.add(plr1Label, c);

		plr1TimeDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1TimeDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1TimeDisplay.setHorizontalAlignment(JLabel.CENTER);
		plr1TimeDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets((int) (0.088 * h), (int) (0.016 * w), 0, (int) (0.007 * w));
		content.add(plr1TimeDisplay, c);

		plr1PtsDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1PtsDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1PtsDisplay.setHorizontalAlignment(JLabel.CENTER);
		plr1PtsDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 0;
		c.insets = new Insets((int) (0.088 * h), 0, 0, (int) (0.024 * w));
		content.add(plr1PtsDisplay, c);

		plr1 = new Player(plr1Label, plr1PtsDisplay, new Clock(plr1TimeDisplay, 1 * 60 * 1000));

		plr2Label.setMinimumSize(new Dimension((int) (0.22 * w), (int) (0.09 * h)));
		plr2Label.setPreferredSize(new Dimension((int) (0.22 * w), (int) (0.09 * h)));
		plr2Label.setHorizontalTextPosition(JLabel.CENTER);
		plr2Label.setHorizontalAlignment(JLabel.CENTER);
		plr2Label.setText("Player 2");
		plr2Label.setFont(new Font("Verdana", Font.BOLD, 50));
		plr2Label.setForeground(Color.WHITE);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.ABOVE_BASELINE;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 0.5;
		c.insets = new Insets((int) (0.056 * h), 0, 0, (int) (0.01 * w));
		content.add(plr2Label, c);

		plr2TimeDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2TimeDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2TimeDisplay.setHorizontalAlignment(JLabel.CENTER);
		plr2TimeDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.026 * h), (int) (0.016 * w), 0, (int) (0.007 * w));
		content.add(plr2TimeDisplay, c);

		plr2PtsDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2PtsDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2PtsDisplay.setHorizontalAlignment(JLabel.CENTER);
		plr2PtsDisplay.setFont(new Font("Verdana", Font.PLAIN, 40));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.026 * h), 0, 0, (int) (0.024 * w));
		content.add(plr2PtsDisplay, c);

		plr2 = new Player(plr2Label, plr2PtsDisplay, new Clock(plr2TimeDisplay, 1 * 60 * 1000));

		boardBg.setMinimumSize(new Dimension((int) (0.44 * w), (int) (0.44 * w)));
		boardBg.setPreferredSize(new Dimension((int) (0.44 * w), (int) (0.44 * w)));
		boardBg.setOpaque(false);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.ABOVE_BASELINE;
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 4;
		c.insets = new Insets(0, (int) (0.01 * w), (int) (0.06 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		content.add(boardBg, c);

		board = new Board(mainFrame, boardBg, wordDisplay, wordList, plr1, plr2);

		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);

		this.add(layers, new GridBagConstraints());

		mainFrame.setContentPane(this);
	}
}
