package boggle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameScreen extends JPanel {
	private JLayeredPane layers = new JLayeredPane();
	private JPanel content = new JPanel();
	private JLabel background = new JLabel();
	private JLabel playerTurnInd = new JLabel();
	private JLabel wordDisplay = new JLabel();
	private Board board;
	private JPanel boardBg = new JPanel();
	private JLabel plr1TimeDisplay = new JLabel();
	private JLabel plr1PtsDisplay = new JLabel();
	private JLabel plr2TimeDisplay = new JLabel();
	private JLabel plr2PtsDisplay = new JLabel();
	private JButton homeBtn = new JButton();
	private JButton settingsBtn = new JButton();
	private WordList wordList;
	private JPanel wordListBg = new JPanel();
	private JScrollPane wordListScroll = new JScrollPane();

	private Boggle mainFrame;

	public GameScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		GridBagConstraints c;
//		layers.setBounds(0, 0, w, h);
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
//		wordDisplay.setBorder(new LineBorder(new Color(146, 146, 146), 15, true));
		wordDisplay.setHorizontalTextPosition(JLabel.CENTER);
		wordDisplay.setVerticalTextPosition(JLabel.CENTER);
		wordDisplay.setHorizontalAlignment(JLabel.CENTER);
		wordDisplay.setVerticalAlignment(JLabel.CENTER);
		wordDisplay.setFont(new Font("Arial", Font.PLAIN, 40));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
//		c.ipadx = (int) (0.39 * w);
//		c.ipady = (int) (0.111 * h);
		c.insets = new Insets((int) (0.0395 * h), (int) (0.059 * w), (int) (0.02 * h), (int) (0.05 * w));
		c.weightx = 1;
		c.weighty = 1;
		content.add(wordDisplay, c);
//		layers.setLayer(wordDisplay, 1);

		wordListBg.setLayout(new GridBagLayout());
		wordListBg.setMinimumSize(new Dimension((int) (0.22 * w), (int) (1.2 * h)));
		wordListBg.setPreferredSize(new Dimension((int) (0.22 * w), (int) (1.2 * h)));
		wordListBg.setBorder(new LineBorder(Color.gray, 5));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.gridheight = 4;
		c.gridx = 0;
		c.gridy = 1;
//		c.ipadx = (int) (0.44 * w) / 2;
//		c.ipady = (int) (0.234375 * h) / 2;
		c.insets = new Insets((int) (0.01 * h), (int) (0.023 * w), 0, (int) (0.00625 * w));
		c.weightx = 1;
		c.weighty = 1;
//		content.add(wordList, c);
//		layers.setLayer(wordList, 1);

		wordListScroll.setViewportView(wordListBg);
		wordListScroll.setMinimumSize(new Dimension((int) (0.23475 * w), (int) (0.802 * h)));
		wordListScroll.setPreferredSize(new Dimension((int) (0.23475 * w), (int) (0.802 * h)));
		content.add(wordListScroll, c);

		wordList = new WordList(mainFrame, wordListBg, wordListScroll);
		wordList.addWord("Help", 4);

		plr1TimeDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1TimeDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1TimeDisplay.setBorder(new LineBorder(Color.gray, 5));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.108 * h), (int) (0.016 * w), 0, (int) (0.007 * w));
		content.add(plr1TimeDisplay, c);

		plr1PtsDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1PtsDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr1PtsDisplay.setBorder(new LineBorder(Color.gray, 5));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.108 * h), 0, 0, (int) (0.024 * w));
		content.add(plr1PtsDisplay, c);

		plr2TimeDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2TimeDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2TimeDisplay.setBorder(new LineBorder(Color.gray, 5));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 3;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.054 * h), (int) (0.016 * w), 0, (int) (0.007 * w));
		content.add(plr2TimeDisplay, c);

		plr2PtsDisplay.setMinimumSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2PtsDisplay.setPreferredSize(new Dimension((int) (0.109375 * w), (int) (0.111 * h)));
		plr2PtsDisplay.setBorder(new LineBorder(Color.gray, 5));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 4;
		c.gridy = 3;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets((int) (0.054 * h), 0, 0, (int) (0.024 * w));
		content.add(plr2PtsDisplay, c);

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

		board = new Board(mainFrame, boardBg, wordDisplay, wordList);

		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);

		this.add(layers, new GridBagConstraints());

		mainFrame.setContentPane(this);
	}
}
