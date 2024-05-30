package boggle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GameScreen extends JPanel {
	private JLayeredPane layers = new JLayeredPane();
	private JLabel background = new JLabel();
	private JPanel content = new JPanel();
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
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));
		GridBagConstraints c = new GridBagConstraints();

		layers.setBackground(Color.black);
//		layers.setLayout(new GridBagLayout());
//		layers.setBounds(0, 0, w, h);
		layers.setPreferredSize(new Dimension(w, h));

		ImageIcon bg = new ImageIcon(getClass().getResource("assets/GameScreenBg.png"));
		background.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBounds(0, 0, w, h);
		background.setBackground(Color.black);
		layers.add(background);
		layers.setLayer(background, 0);


		content.setOpaque(false);
		content.setBounds(0, 0, w, h);
		content.setLayout(new GridBagLayout());
//		content.setBackground(Color.gray);

//		wordDisplay.setBounds(50, 1000, 1000, 500);
		wordDisplay.setPreferredSize(new Dimension((int) (0.39 * w), (int) (0.111 * h)));
		wordDisplay.setBorder(new LineBorder(Color.black, 5, true));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
//		c.ipadx = (int) (0.4427 * w);
		c.insets = new Insets((int) (0.4427 * w), (int) (0.06574 * h), 0, 0);
//		c.weightx = 0.1;
//		c.weighty = 0.1;
		content.add(wordDisplay, c);

		layers.add(content);
		layers.setLayer(content, 1);

		c = new GridBagConstraints();
		c.gridheight = h;
		c.gridwidth = w;
		c.gridx = Toolkit.getDefaultToolkit().getScreenSize().width / 2;
		c.gridy = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		this.add(layers, c);

		mainFrame.setContentPane(this);
	}
}
