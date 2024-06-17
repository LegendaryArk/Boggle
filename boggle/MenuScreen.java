/**
 * @author brian.cheang
 * 2024.06.12
 */
package boggle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This class contains the panel for the menu screen.
 */
public class MenuScreen extends JPanel {
	private JLabel title;

	// Layered Pane
	private JLayeredPane layers = new JLayeredPane();

	// Background
	private JLabel background = new JLabel();

	private JPanel content = new JPanel();

	// Images
	private ImageIcon backgroundImg = new ImageIcon(getClass().getResource("assets/MenuScreenBg.png"));
	private ImageIcon titleGif = new ImageIcon(getClass().getResource("assets/Title.gif"));

	// Button
	private OptionButton playBtn;
	private ImageIcon playDefault = new ImageIcon(getClass().getResource("assets/PlayMenuBtnDefault.png"));
	private ImageIcon playHover = new ImageIcon(getClass().getResource("assets/PlayMenuBtnHover.png"));
	private ImageIcon playPress = new ImageIcon(getClass().getResource("assets/PlayMenuBtnPress.png"));

	private OptionButton guideBtn;
	private ImageIcon guideDefault = new ImageIcon(getClass().getResource("assets/GuideMenuBtnDefault.png"));
	private ImageIcon guideHover = new ImageIcon(getClass().getResource("assets/GuideMenuBtnHover.png"));
	private ImageIcon guidePress = new ImageIcon(getClass().getResource("assets/GuideMenuBtnPress.png"));

	private OptionButton settingsBtn;
	private ImageIcon settingsDefault = new ImageIcon(getClass().getResource("assets/SettingsMenuBtnDefault.png"));
	private ImageIcon settingsHover = new ImageIcon(getClass().getResource("assets/SettingsMenuBtnHover.png"));
	private ImageIcon settingsPress = new ImageIcon(getClass().getResource("assets/SettingsMenuBtnPress.png"));

	private OptionButton creditsBtn;
	private ImageIcon creditsDefault = new ImageIcon(getClass().getResource("assets/CreditsMenuBtnDefault.png"));
	private ImageIcon creditsHover = new ImageIcon(getClass().getResource("assets/CreditsMenuBtnHover.png"));
	private ImageIcon creditsPress = new ImageIcon(getClass().getResource("assets/CreditsMenuBtnPress.png"));

	private OptionButton exitBtn;
	private ImageIcon exitDefault = new ImageIcon(getClass().getResource("assets/ExitMenuBtnDefault.png"));
	private ImageIcon exitHover = new ImageIcon(getClass().getResource("assets/ExitMenuBtnHover.png"));
	private ImageIcon exitPress = new ImageIcon(getClass().getResource("assets/ExitMenuBtnPress.png"));

	private AudioInputStream menuBgm;
	private Clip bgmClip;

	private Boggle mainFrame;
	private int w, h;

	/**
	 * Constructor the main panel which contains all the objects.
	 *
	 * @param mainFrame, class Boggle.
	 */
	public MenuScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		// The dimensions of the players of screen.
		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();

		GridBagConstraints c;

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));

		// Layered Pane
		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		// Background
		background.setIcon(new ImageIcon(backgroundImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 6;
		layers.add(background, c);
		layers.setLayer(background, 0); // Add background to layer 0

		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(w, h));

		// Boggle logo
		title = new JLabel();
		title.setIcon(new ImageIcon(titleGif.getImage().getScaledInstance((int) (0.4 * w), (int) (0.25 * h), Image.SCALE_DEFAULT)));
		title.setMinimumSize(new Dimension((int) (0.4 * w), (int)(0.25 * h)));
		title.setPreferredSize(new Dimension((int) (0.4 * w), (int)(0.25 * h)));
		title.setOpaque(false);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.05 * w));
		content.add(title, c);

		// Buttons
		playBtn = new OptionButton(0.23 * w, 0.1 * h, playDefault, playHover, playPress, e -> mainFrame.modeSelectionScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets((int) (0.05 * h), 0, 0, (int) (0.135 * w));
		content.add(playBtn, c);

		guideBtn = new OptionButton(0.23 * w, 0.1 * h, guideDefault, guideHover, guidePress, e -> mainFrame.guideScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		content.add(guideBtn, c);

		settingsBtn = new OptionButton(0.23 * w, 0.1 * h, settingsDefault, settingsHover, settingsPress, e -> mainFrame.settingsScreen(0));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		content.add(settingsBtn, c);

		creditsBtn = new OptionButton(0.23 * w, 0.1 * h, creditsDefault, creditsHover, creditsPress, e -> mainFrame.creditsScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		content.add(creditsBtn, c);

		exitBtn = new OptionButton(0.23 * w, 0.1 * h, exitDefault, exitHover, exitPress, e -> System.exit(0));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		c.weightx = 1;
		c.weighty = 1;
		content.add(exitBtn, c);

		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);

		this.add(layers, new GridBagConstraints());
	}

	public void startBgm() {
		if (bgmClip != null && bgmClip.isRunning()) {
			return;
		}

		try {
			menuBgm = AudioSystem.getAudioInputStream(getClass().getResource("assets/MenuBGM.wav"));
			bgmClip = AudioSystem.getClip();
			bgmClip.open(menuBgm);
		} catch (Exception e) {
			System.err.println("Error: Unable to start Menu BGM");
			e.printStackTrace();
		}
		bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stopBgm() {
		bgmClip.stop();
		bgmClip.close();
		try {
			menuBgm.close();
		} catch (IOException e) {
			System.err.println("Error: Unable to reset Menu BGM");
			e.printStackTrace();
		}
	}
}