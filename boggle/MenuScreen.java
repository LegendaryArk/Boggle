package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuScreen extends JPanel implements MouseListener {

	private GridBagConstraints c = new GridBagConstraints();

	private JLayeredPane pane = new JLayeredPane();
	private JLabel background = new JLabel();
	private Boggle mainFrame;
	// Dimensions.
	private int w;
	private int h;
	// Images
	private ImageIcon backgroundImage = new ImageIcon(getClass().getResource("assets/MenuScreenBg.png"));

	private ImageIcon playButton = new ImageIcon(getClass().getResource("assets/PlayButton.png"));
	private ImageIcon playButtonHl = new ImageIcon(getClass().getResource("assets/PlayButtonHover.png"));

	private ImageIcon guideButton = new ImageIcon(getClass().getResource("assets/GuideButton.png"));
	private ImageIcon guideButtonHl = new ImageIcon(getClass().getResource("assets/GuideButtonHover.png"));

	private ImageIcon settingsButton = new ImageIcon(getClass().getResource("assets/SettingsButton.png"));
	private ImageIcon settingsButtonHl = new ImageIcon(getClass().getResource("assets/SettingsButtonHover.png"));

	private ImageIcon creditsButton = new ImageIcon(getClass().getResource("assets/CreditsButton.png"));
	private ImageIcon creditsButtonHl = new ImageIcon(getClass().getResource("assets/CreditsButtonHover.png"));

	private ImageIcon exitButton = new ImageIcon(getClass().getResource("assets/ExitButton.png"));
	private ImageIcon exitplayButtonHl = new ImageIcon(getClass().getResource("assets/ExitButtonHover.png"));

	// Button
	private JLabel playBtn;
	private JLabel guideBtn;
	private JLabel settingsBtn;
	private JLabel creditsBtn;
	private JLabel exitBtn;

	public MenuScreen(Boggle mainFrame) {
		ImageIcon titleGif;
		JLabel backgroundLabel;
		JLabel title;

		titleGif = new ImageIcon(getClass().getResource("assets/Title.gif"));

		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);

		pane.setBounds(0, 0, w, h);
		pane.setLayout(new GridBagLayout());

		background.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBounds(0, 0, w, h);
		background.setBackground(Color.black);
		background.setVisible(true);
		background.setPreferredSize(new Dimension(w, h));
		background.setMinimumSize(new Dimension(w, h));
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 4;
		pane.add(background, c);
		pane.setLayer(background, 0); // Add background to layer 0

		title = new JLabel();
		title.setIcon(new ImageIcon(titleGif.getImage().getScaledInstance((int)(0.4 * w), (int) (0.4 * h), Image.SCALE_DEFAULT)));
		title.setMinimumSize(new Dimension((int) (0.4 * w), (int)(0.4 * h)));
		title.setPreferredSize(new Dimension((int) (0.4 * w), (int)(0.4 * h)));
		title.setOpaque(false);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,(int) (0.45 * w),(int)(0.55 *h),0);
		c.weightx = 1;
		c.weighty = 1;
		pane.add(title, c);
		pane.setLayer(title, 1); // Add title to layer 1

		playBtn = createButton(playButton, 0,(int) (0.45 * w),(int)(0.2 *h),0);
		guideBtn = createButton(guideButton, 0,(int) (0.45 * w),0,0);
		settingsBtn = createButton(settingsButton, (int) (0.2 * h),(int) (0.45 * w),0,0);
		creditsBtn = createButton(creditsButton, (int) (0.4 * h),(int) (0.45 * w),0,0);
		exitBtn = createButton(exitButton, (int) (0.6 * h),(int) (0.45 * w),0,0);

		this.add(pane, new GridBagConstraints());
		mainFrame.setContentPane(this);
	}


	private JLabel createButton (ImageIcon image, int top, int left, int bottom, int right){
		JLabel btn = new JLabel();
		btn.setIcon(new ImageIcon(image.getImage().getScaledInstance((int)(0.1 * w), (int)(0.05 * h), Image.SCALE_SMOOTH)));
		btn.setBounds((int)(0.667 * w), (int) (0.333*h) , (int)(0.1*w), (int)(0.05 * h));
		btn.addMouseListener(this);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(top,left,bottom,right);
		c.weightx = 1;
		c.weighty = 1;
		pane.add(btn, c);
		pane.setLayer(btn, 2);// Add play button to layer 2
		return btn;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Handle mouse click
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Handle mouse press
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Handle mouse release
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButtonHl.getImage().getScaledInstance((int)(w *0.1), (int)(h * 0.05), Image.SCALE_SMOOTH)));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButton.getImage().getScaledInstance((int)(w * 0.1), (int)(h * 0.05), Image.SCALE_SMOOTH)));
		}
	}
}
