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
	// Holds title animation.
	private final JLabel title;

	// Layered Pane.
	private final JLayeredPane layers = new JLayeredPane();

	// Background.
	private final JLabel background = new JLabel();
	// Content.
	private final JPanel content = new JPanel();

	// Background image.
	private final ImageIcon backgroundImg = new ImageIcon(getClass().getResource("assets/MenuScreenBg.png"));
	// Title animation of menu.
	private final ImageIcon titleGif = new ImageIcon(getClass().getResource("assets/Title.gif"));

	// Play Button.
	private final OptionButton playBtn;
	// Default Play Button.
	private final ImageIcon playDefault = new ImageIcon(getClass().getResource("assets/PlayMenuBtnDefault.png"));
	// Hover Play Button.
	private final ImageIcon playHover = new ImageIcon(getClass().getResource("assets/PlayMenuBtnHover.png"));
	// Press Play Button.
	private final ImageIcon playPress = new ImageIcon(getClass().getResource("assets/PlayMenuBtnPress.png"));

	// Guide Button.
	private final OptionButton guideBtn;
	// Default Guide Button.
	private final ImageIcon guideDefault = new ImageIcon(getClass().getResource("assets/GuideMenuBtnDefault.png"));
	// Hover Guide Button.
	private final ImageIcon guideHover = new ImageIcon(getClass().getResource("assets/GuideMenuBtnHover.png"));
	// Press Guide Button
	private final ImageIcon guidePress = new ImageIcon(getClass().getResource("assets/GuideMenuBtnPress.png"));

	// Settings Button.
	private final OptionButton settingsBtn;
	// Default Settings Button.
	private final ImageIcon settingsDefault = new ImageIcon(getClass().getResource("assets/SettingsMenuBtnDefault.png"));
	// Hover Settings Button.
	private final ImageIcon settingsHover = new ImageIcon(getClass().getResource("assets/SettingsMenuBtnHover.png"));
	// Press Settings Button.
	private final ImageIcon settingsPress = new ImageIcon(getClass().getResource("assets/SettingsMenuBtnPress.png"));

	// Credits Button.
	private final OptionButton creditsBtn;
	// Default Settings Button.
	private final ImageIcon creditsDefault = new ImageIcon(getClass().getResource("assets/CreditsMenuBtnDefault.png"));
	// Hover Settings Button.
	private final ImageIcon creditsHover = new ImageIcon(getClass().getResource("assets/CreditsMenuBtnHover.png"));
	// Press Settings Button.
	private final ImageIcon creditsPress = new ImageIcon(getClass().getResource("assets/CreditsMenuBtnPress.png"));

	// Exit Button.
	private final OptionButton exitBtn;
	// Default Exit Button.
	private final ImageIcon exitDefault = new ImageIcon(getClass().getResource("assets/ExitMenuBtnDefault.png"));
	// Hover Exit Button.
	private final ImageIcon exitHover = new ImageIcon(getClass().getResource("assets/ExitMenuBtnHover.png"));
	// Pres Exit Button.
	private final ImageIcon exitPress = new ImageIcon(getClass().getResource("assets/ExitMenuBtnPress.png"));

	// Menu background music.
	private AudioInputStream menuBgm;
	// Background music clip.
	private Clip bgmClip;

	// Main game frame.
	private final Boggle mainFrame;

	// Width.
	private final int w;
	// Height.
	private final int h;

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

		// Set background colour, layout and dimensions.
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

		// Set content layout and dimensions.
		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(w, h));

		// Boggle title.
		title = new JLabel();
		title.setIcon(new ImageIcon(titleGif.getImage().getScaledInstance((int) (0.4 * w), (int) (0.25 * h), Image.SCALE_DEFAULT)));
		title.setMinimumSize(new Dimension((int) (0.4 * w), (int) (0.25 * h)));
		title.setPreferredSize(new Dimension((int) (0.4 * w), (int) (0.25 * h)));
		title.setOpaque(false);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.05 * w));
		content.add(title, c);

		// Play Button.
		playBtn = new OptionButton(0.23 * w, 0.1 * h, playDefault, playHover, playPress, e -> mainFrame.modeSelectionScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets((int) (0.05 * h), 0, 0, (int) (0.135 * w));
		content.add(playBtn, c);

		// Guide Button.
		guideBtn = new OptionButton(0.23 * w, 0.1 * h, guideDefault, guideHover, guidePress, e -> mainFrame.guideScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		content.add(guideBtn, c);

		// Settings Button.
		settingsBtn = new OptionButton(0.23 * w, 0.1 * h, settingsDefault, settingsHover, settingsPress, e -> mainFrame.settingsScreen(0));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		content.add(settingsBtn, c);

		// Credits Button.
		creditsBtn = new OptionButton(0.23 * w, 0.1 * h, creditsDefault, creditsHover, creditsPress, e -> mainFrame.creditsScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 4;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		content.add(creditsBtn, c);

		// Exit Button.
		exitBtn = new OptionButton(0.23 * w, 0.1 * h, exitDefault, exitHover, exitPress, e -> mainFrame.exitScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 5;
		c.insets = new Insets((int) (0.02 * h), 0, 0, (int) (0.135 * w));
		c.weightx = 1;
		c.weighty = 1;
		content.add(exitBtn, c);

		// Add and layer the buttons to the menu screen.
		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);
		this.add(layers, new GridBagConstraints());
	}

	/**
	 * This method stars the background music
	 */
	public void startBgm() {
		if (bgmClip != null && bgmClip.isRunning()) {
			// Continue playing background music if running.
			return;
		}
		try {
			// Set menu background music.
			menuBgm = AudioSystem.getAudioInputStream(getClass().getResource("assets/MenuBGM.wav"));
			bgmClip = AudioSystem.getClip();
			// Open sound as a clip.
			bgmClip.open(menuBgm);
		} catch (Exception e) {
			// Handle if unable to open the background music.
			System.err.println("Error: Unable to start Menu BGM");
			e.printStackTrace();
		}
		// Loop background music indefinitely.
		bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * This method will stop the menu background music
	 */
	public void stopBgm() {
		// Stop the background music and close the clip.
		bgmClip.stop();
		bgmClip.close();
		try {
			// Close the menu background.
			menuBgm.close();
		} catch (IOException e) {
			// Handle if unable to reset the menu background music.
			System.err.println("Error: Unable to reset Menu BGM");
			e.printStackTrace();
		}
	}
}