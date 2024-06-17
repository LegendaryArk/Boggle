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
	private JLabel title;

	// Layered Pane.
	private JLayeredPane layers;

	// Background image.
	private ImageIcon backgroundImage;
	// Background.
	private JLabel background;
	// Content.
	private JPanel content;

	// Title animation of menu.
	private ImageIcon titleAnimation;

	// Play Button.
	private OptionButton playButton;
	// Default Play Button.
	private ImageIcon playDefault;
	// Hover Play Button.
	private ImageIcon playHover;
	// Press Play Button.
	private ImageIcon playPress;

	// Guide Button.
	private OptionButton guideButton;
	// Default Guide Button Icon.
	private ImageIcon guideDefault;
	// Hover Guide Button Icon.
	private ImageIcon guideHover;
	// Press Guide Button Icon.
	private ImageIcon guidePress;

	// Settings Button.
	private OptionButton settingsButton;
	// Default Settings Button Icon.
	private ImageIcon settingsDefault;
	// Hover Settings Button Icon.
	private ImageIcon settingsHover;
	// Press Settings Button Icon.
	private ImageIcon settingsPress;

	// Credits Button.
	private OptionButton creditsButton;
	// Default Settings Button Icon.
	private ImageIcon creditsDefault;
	// Hover Settings Button Icon.
	private ImageIcon creditsHover;
	// Press Settings Button Icon.
	private ImageIcon creditsPress;

	// Exit Button.
	private OptionButton exitButton;
	// Default Exit Button Icon.
	private ImageIcon exitDefault;
	// Hover Exit Button Icon.
	private ImageIcon exitHover;
	// Pres Exit Button Icon.
	private ImageIcon exitPress;

	// Menu background music.
	private AudioInputStream menuBackgroundMusic;
	// Background music clip.
	private Clip backgroundMusicClip;

	// The width of the screen.
	private int width;
	// The height of the screen.
	private int height;

	/**
	 * Constructor the main panel which contains all the objects.
	 *
	 * @param mainFrame, class Boggle.
	 */
	public MenuScreen(Boggle mainFrame) {;
		// The dimensions of the players of screen.
		width = mainFrame.getScreenWidth();
		height = mainFrame.getScreenHeight();

		// Used to set the position of the components.
		GridBagConstraints contraints;

		// Set up the main panel.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Layered Pane
		layers = new JLayeredPane();
		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(width, height));

		// Background
		backgroundImage = new ImageIcon(getClass()
				.getResource("assets/MenuScreenBg.png"));
		background = new JLabel();
		background.setIcon(new ImageIcon(backgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		contraints = new GridBagConstraints();
		contraints.gridx = 0;
		contraints.gridy = 0;
		contraints.gridwidth = 1;
		contraints.gridheight = 6;
		layers.add(background, contraints);
		layers.setLayer(background, 0); // Add background to layer 0

		// Set content layout and dimensions.
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(width, height));

		// Boggle title.
		titleAnimation = new ImageIcon(getClass()
				.getResource("assets/Title.gif"));
		title = new JLabel();
		title.setIcon(new ImageIcon(titleAnimation.getImage()
				.getScaledInstance((int) (0.4 * width), (int) (0.25 * height),
						Image.SCALE_DEFAULT)));
		title.setMinimumSize(new Dimension((int) (0.4 * width),
				(int) (0.25 * height)));
		title.setPreferredSize(
				new Dimension((int) (0.4 * width), (int) (0.25 * height)));
		title.setOpaque(false);
		contraints = new GridBagConstraints();
		contraints.anchor = GridBagConstraints.FIRST_LINE_END;
		contraints.gridx = 0;
		contraints.gridy = 0;
		contraints.insets = new Insets((int) (0.02 * height), 0,
				0, (int) (0.05 * width));
		content.add(title, contraints);

		// Play Button.
		playDefault = new ImageIcon(getClass()
				.getResource("assets/PlayMenuBtnDefault.png"));
		playHover = new ImageIcon(getClass()
				.getResource("assets/PlayMenuBtnHover.png"));
		playPress = new ImageIcon(getClass()
				.getResource("assets/PlayMenuBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		playButton = new OptionButton(false,
				0.23 * width, 0.1 * height, playDefault,
				playHover, playPress, e -> mainFrame.modeSelectionScreen());
		contraints = new GridBagConstraints();
		contraints.anchor = GridBagConstraints.FIRST_LINE_END;
		contraints.gridx = 0;
		contraints.gridy = 1;
		contraints.insets = new Insets((int) (0.05 * height),
				0, 0, (int) (0.135 * width));
		content.add(playButton, contraints);

		// Guide Button.
		guideDefault = new ImageIcon(getClass()
				.getResource("assets/GuideMenuBtnDefault.png"));
		guideHover = new ImageIcon(getClass()
				.getResource("assets/GuideMenuBtnHover.png"));
		guidePress = new ImageIcon(getClass()
				.getResource("assets/GuideMenuBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		guideButton = new OptionButton(false, 0.23 * width,
				0.1 * height, guideDefault,
				guideHover, guidePress, e -> mainFrame.guideScreen());
		contraints = new GridBagConstraints();
		contraints.anchor = GridBagConstraints.FIRST_LINE_END;
		contraints.gridx = 0;
		contraints.gridy = 2;
		contraints.insets = new Insets((int) (0.02 * height), 0, 0,
				(int) (0.135 * width));
		content.add(guideButton, contraints);

		// Settings Button.
		settingsDefault = new ImageIcon(getClass()
				.getResource("assets/SettingsMenuBtnDefault.png"));
		settingsHover = new ImageIcon(getClass()
				.getResource("assets/SettingsMenuBtnHover.png"));
		settingsPress = new ImageIcon(getClass()
				.getResource("assets/SettingsMenuBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsButton = new OptionButton(false, 0.23 * width,
				0.1 * height,
				settingsDefault, settingsHover, settingsPress,
				e -> mainFrame.settingsScreen());
		contraints = new GridBagConstraints();
		contraints.anchor = GridBagConstraints.FIRST_LINE_END;
		contraints.gridx = 0;
		contraints.gridy = 3;
		contraints.insets = new Insets((int) (0.02 * height), 0, 0,
				(int) (0.135 * width));
		content.add(settingsButton, contraints);

		// Credits Button.
		creditsDefault = new ImageIcon(getClass()
				.getResource("assets/CreditsMenuBtnDefault.png"));
		creditsHover = new ImageIcon(getClass()
				.getResource("assets/CreditsMenuBtnHover.png"));
		creditsPress = new ImageIcon(getClass()
				.getResource("assets/CreditsMenuBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		creditsButton = new OptionButton(false, 0.23 * width,
				0.1 * height, creditsDefault,
				creditsHover, creditsPress, e -> mainFrame.creditsScreen());
		contraints = new GridBagConstraints();
		contraints.anchor = GridBagConstraints.FIRST_LINE_END;
		contraints.gridx = 0;
		contraints.gridy = 4;
		contraints.insets = new Insets((int) (0.02 * height), 0, 0,
				(int) (0.135 * width));
		content.add(creditsButton, contraints);

		// Exit Button.
		exitDefault = new ImageIcon(getClass()
				.getResource("assets/ExitMenuBtnDefault.png"));
		exitHover = new ImageIcon(getClass()
				.getResource("assets/ExitMenuBtnHover.png"));
		exitPress = new ImageIcon(getClass()
				.getResource("assets/ExitMenuBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		exitButton = new OptionButton(false, 0.23 * width,
				0.1 * height, exitDefault,
				exitHover, exitPress, e -> mainFrame.exitScreen());
		contraints = new GridBagConstraints();
		contraints.anchor = GridBagConstraints.FIRST_LINE_END;
		contraints.gridx = 0;
		contraints.gridy = 5;
		contraints.insets = new Insets((int) (0.02 * height), 0, 0,
				(int) (0.135 * width));
		contraints.weightx = 1;
		contraints.weighty = 1;
		content.add(exitButton, contraints);

		// Add and layer the buttons to the menu screen.
		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);
		this.add(layers, new GridBagConstraints());
	}

	/**
	 * This method stars the background music
	 */
	public void startBackgroundMusic() {
		if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
			// Continue playing background music if running.
			return;
		}
		try {
			// Set menu background music.
			menuBackgroundMusic = AudioSystem.getAudioInputStream(getClass()
					.getResource("assets/MenuBGM.wav"));
			backgroundMusicClip = AudioSystem.getClip();
			// Open sound as a clip.
			backgroundMusicClip.open(menuBackgroundMusic);
		} catch (Exception e) {
			// Handle if unable to open the background music.
			System.err.println("Error: Unable to start Menu BGM");
			e.printStackTrace();
		}
		// Loop background music indefinitely.
		backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	/**
	 * This method will stop the menu background music
	 */
	public void stopBackgroundMusic() {
		// Stop the background music and close the clip.
		backgroundMusicClip.stop();
		backgroundMusicClip.close();
		try {
			// Close the menu background.
			menuBackgroundMusic.close();
		} catch (IOException e) {
			// Handle if unable to reset the menu background music.
			System.err.println("Error: Unable to reset Menu BGM");
			e.printStackTrace();
		}
	}
}