/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the settings screen.
 */
public class SettingsScreen extends JPanel {
	// Card Layout to switch between different pages.
	private CardLayout cardLayout;

	// Return button icons.
	private ImageIcon returnButtonDefault;
	private ImageIcon returnButtonHover;
	private ImageIcon returnButtonPress;

	// Increment button icons.
	private ImageIcon incrementButtonDefault;
	private ImageIcon incrementButtonHover;
	private ImageIcon incrementButtonPress;

	// Decrement button icons.
	private ImageIcon decrementButtonDefault;
	private ImageIcon decrementButtonHover;
	private ImageIcon decrementButtonPress;

	// Radio button icons.
	private ImageIcon radioButtonDefault;
	private ImageIcon radioButtonHover;
	private ImageIcon radioButtonSelect;


	// Settings menu components.
	private JPanel menu;
	private final String MENU_PAGE = "Menu Page";
	private JLayeredPane menuLayeredPane;
	private ImageIcon menuBackgroundImage;
	private JLabel menuBackground;
	private JPanel menuContent;
	private OptionButton menuReturnButton;

	// General settings menu button.
	private OptionButton generalButton;
	private ImageIcon generalButtonDefault;
	private ImageIcon generalButtonHover;
	private ImageIcon generalButtonPress;

	// Time control settings menu button.
	private OptionButton timeButton;
	private ImageIcon timeButtonDefault;
	private ImageIcon timeButtonHover;
	private ImageIcon timeButtonPress;
	
	// Difficulty settings menu button.
	private OptionButton difficultyButton;
	private ImageIcon difficultyButtonDefault;
	private ImageIcon difficultyButtonHover;
	private ImageIcon difficultyButtonPress;

	// Music settings menu button.
	private OptionButton musicButton;
	private ImageIcon musicButtonDefault;
	private ImageIcon musicButtonHover;
	private ImageIcon musicButtonPress;

	private JPanel general;
	private ImageIcon generalBackgroundImage;
	private final String GENERAL_PAGE = "General Page";
	private JLayeredPane generalLayer;
	private JLabel generalBackground;
	private JPanel generalContent;
	private OptionButton generalReturnButton;

	private int minimumWordLength;
	private JLabel minimumWordLengthDisplay;
	private OptionButton minimumWordLengthIncrementButton;
	private OptionButton minimumWordLengthDecrementButton;

	private int targetPoints;
	private JLabel targetPointsDisplay;
	private OptionButton targetPointsIncrementButton;
	private OptionButton targetPointsDecrementButton;

	private JPanel time = new JPanel();
	private ImageIcon timeBgImg = new ImageIcon(getClass().getResource("assets/TimeSettingsScreenBg.png"));
	private final String TIME_PAGE = "Time Page";
	private JLayeredPane timeLayer = new JLayeredPane();
	private JLabel timeBg = new JLabel();
	private JPanel timeContent = new JPanel();
	private OptionButton timeReturnBtn;

	private int initTime;
	private JLabel initTimeDisplay = new JLabel();
	private OptionButton initTimeInc;
	private OptionButton initTimeDec;

	private int timeIncrement;
	private JLabel timeIncrementDisplay = new JLabel();
	private OptionButton timeIncrementInc;
	private OptionButton timeIncrementDec;

	private JPanel difficulty = new JPanel();
	private ImageIcon difficultyBgImg = new ImageIcon(getClass().getResource("assets/DifficultySettingsScreenBg.png"));
	private final String DIFFICULTY_PAGE = "Difficulty Page";
	private JLayeredPane difficultyLayer = new JLayeredPane();
	private JLabel difficultyBg = new JLabel();
	private JPanel difficultyContent = new JPanel();
	private OptionButton difficultyReturnBtn;

	private int aiDifficulty;
	private OptionButton easyBtn;
	private OptionButton medBtn;
	private OptionButton hardBtn;
	private OptionButton impossibleBtn;

	private JPanel music = new JPanel();
	private ImageIcon musicBgImg = new ImageIcon(getClass().getResource("assets/MusicSettingsScreenBg.png"));
	private final String MUSIC_PAGE = "Music Page";
	private JLayeredPane musicLayer = new JLayeredPane();
	private JLabel musicBg = new JLabel();
	private JPanel musicContent = new JPanel();
	private OptionButton musicReturnBtn;

	private int musicType;
	private OptionButton defaultMusicBtn;
	private OptionButton calmMusicBtn;
	private OptionButton intenseMusicBtn;

	private Boggle mainFrame;
	private int prevScreen;

	public SettingsScreen(Boggle mainFrame, int prevScreen) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.prevScreen = prevScreen;

		this.cardLayout = new CardLayout();

		// Return Button Icons.
		returnButtonDefault = new ImageIcon(getClass().getResource("assets/ReturnBtnDefault.png"));
		returnButtonHover = new ImageIcon(getClass().getResource("assets/ReturnBtnHover.png"));
		returnButtonPress = new ImageIcon(getClass().getResource("assets/ReturnBtnPress.png"));

		// Increment Button Icons.
		incrementButtonDefault = new ImageIcon(getClass().getResource("assets/SettingsIncBtnDefault.png"));
		incrementButtonHover = new ImageIcon(getClass().getResource("assets/SettingsIncBtnHover.png"));
		incrementButtonPress = new ImageIcon(getClass().getResource("assets/SettingsIncBtnPress.png"));

		// Decrement Button Icons.
		decrementButtonDefault = new ImageIcon(getClass().getResource("assets/SettingsDecBtnDefault.png"));
		decrementButtonHover = new ImageIcon(getClass().getResource("assets/SettingsDecBtnHover.png"));
		decrementButtonPress = new ImageIcon(getClass().getResource("assets/SettingsDecBtnPress.png"));

		// Radio Button Icons.
		radioButtonDefault = new ImageIcon(getClass().getResource("assets/RadioBtnDefault.png"));
		radioButtonHover = new ImageIcon(getClass().getResource("assets/RadioBtnHover.png"));
		radioButtonSelect = new ImageIcon(getClass().getResource("assets/RadioBtnSelected.png"));

		GridBagConstraints c;

		// Panel setup.
		this.setBackground(Color.black);
		this.setLayout(cardLayout);
		this.setPreferredSize(new Dimension(w, h));

		/* Menu Screen. */
		menu = new JPanel();
		menu.setBackground(Color.black);
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(w, h));

		// Settings Menu Layer Pane.
		menuLayeredPane = new JLayeredPane();
		menuLayeredPane.setBackground(Color.black);
		menuLayeredPane.setLayout(new GridBagLayout());
		menuLayeredPane.setPreferredSize(new Dimension(w, h));

		// Menu Background.
		menuBackgroundImage = new ImageIcon(getClass().getResource("assets/SettingsScreenMenuBg.png"));
		menuBackground = new JLabel();
		menuBackground.setIcon(new ImageIcon(menuBackgroundImage.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		menuBackground.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 4;
		menuLayeredPane.add(menuBackground, c);
		menuLayeredPane.setLayer(menuBackground, 0);

		// Menu Buttons.
		menuContent = new JPanel();
		menuContent.setLayout(new GridBagLayout());
		menuContent.setBackground(Color.black);
		menuContent.setPreferredSize(new Dimension(w, h));
		menuContent.setOpaque(false);

		// Menu Return Button.
		menuReturnButton = new OptionButton(0.06 * w, 0.06 * w, returnButtonDefault, returnButtonHover, returnButtonPress, e -> {
			mainFrame.updateSettings();
			switch (this.prevScreen) {
				case 0 -> mainFrame.menuScreen();
				case 1 -> mainFrame.gameScreen(mainFrame.isAI());
			}
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		menuContent.add(menuReturnButton, c);

		// General Settings Menu Button.
		generalButtonDefault = new ImageIcon(getClass().getResource("assets/GeneralBtnDefault.png"));
		generalButtonHover = new ImageIcon(getClass().getResource("assets/GeneralBtnHover.png"));
		generalButtonPress = new ImageIcon(getClass().getResource("assets/GeneralBtnPress.png"));
		generalButton = new OptionButton(0.3 * w, 0.14 * h, generalButtonDefault, generalButtonHover, generalButtonPress, e -> cardLayout.show(this, GENERAL_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.3 * h), 0, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(generalButton, c);

		// Time Settings Menu Button.
		timeButtonDefault = new ImageIcon(getClass().getResource("assets/TimeBtnDefault.png"));
		timeButtonHover = new ImageIcon(getClass().getResource("assets/TimeBtnHover.png"));
		timeButtonPress = new ImageIcon(getClass().getResource("assets/TimeBtnPress.png"));
		timeButton = new OptionButton(0.3 * w, 0.14 * h, timeButtonDefault, timeButtonHover, timeButtonPress, e -> cardLayout.show(this, TIME_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(timeButton, c);

		// Difficulty Settings Menu Button.
		difficultyButtonDefault = new ImageIcon(getClass().getResource("assets/DifficultyBtnDefault.png"));
		difficultyButtonHover = new ImageIcon(getClass().getResource("assets/DifficultyBtnHover.png"));
		difficultyButtonPress = new ImageIcon(getClass().getResource("assets/DifficultyBtnPress.png"));
		difficultyButton = new OptionButton(0.3 * w, 0.14 * h, difficultyButtonDefault, difficultyButtonHover, difficultyButtonPress, e -> cardLayout.show(this, DIFFICULTY_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(difficultyButton, c);

		// Music Settings Menu Button.
		musicButtonDefault = new ImageIcon(getClass().getResource("assets/MusicBtnDefault.png"));
		musicButtonHover = new ImageIcon(getClass().getResource("assets/MusicBtnHover.png"));
		musicButtonPress = new ImageIcon(getClass().getResource("assets/MusicBtnPress.png"));
		musicButton = new OptionButton(0.3 * w, 0.14 * h, musicButtonDefault, musicButtonHover, musicButtonPress, e -> cardLayout.show(this, MUSIC_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, (int) (0.05 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(musicButton, c);

		// Add the buttons to the layered pane
		menuLayeredPane.add(menuContent, new GridBagConstraints());
		menuLayeredPane.setLayer(menuContent, 1);

		// Add the screen to the panel.
		menu.add(menuLayeredPane, new GridBagConstraints());

		// Add the page to the main panel.
		add(menu, MENU_PAGE);
		// Set initial page to the menu page.
		cardLayout.show(this, MENU_PAGE);

		/* General Settings Page */
		general = new JPanel();
		general.setBackground(Color.black);
		general.setLayout(new GridBagLayout());
		general.setPreferredSize(new Dimension(w, h));

		// General Settings Layered Pane.
		generalLayer = new JLayeredPane();
		generalLayer.setBackground(Color.black);
		generalLayer.setLayout(new GridBagLayout());
		generalLayer.setPreferredSize(new Dimension(w, h));

		// General Settings Background.
		generalBackgroundImage = new ImageIcon(getClass()
				.getResource("assets/GeneralSettingsScreenBg.png"));
		generalBackground = new JLabel();
		generalBackground.setIcon(new ImageIcon(generalBackgroundImage
				.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		generalBackground.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 2;
		generalLayer.add(generalBackground, c);
		generalLayer.setLayer(generalBackground, 0);

		// General Settings Displays and Buttons
		generalContent = new JPanel();
		generalContent.setLayout(new GridBagLayout());
		generalContent.setBackground(Color.black);
		generalContent.setPreferredSize(new Dimension(w, h));
		generalContent.setOpaque(false);

		// General Settings Return Button
		generalReturnButton = new OptionButton(0.06 * w, 0.06 * w,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		generalContent.add(generalReturnButton, c);

		// General Settings: Minimum Word Length.
		minimumWordLength = 3; // Default minimum word length.
		// Minimum Word Length Display Label.
		minimumWordLengthDisplay = new JLabel();
		minimumWordLengthDisplay.setMinimumSize(
				new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		minimumWordLengthDisplay.setPreferredSize(
				new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		minimumWordLengthDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		minimumWordLengthDisplay.setVerticalAlignment(SwingConstants.CENTER);
		minimumWordLengthDisplay.setText(String.valueOf(minimumWordLength));
		minimumWordLengthDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets((int) (0.497 * h), 0, 0, (int) (0.058 * w));
		c.weightx = 1;
		c.weighty = 0;
		generalContent.add(minimumWordLengthDisplay, c);

		// Minimum Word Length Increment Button.
		minimumWordLengthIncrementButton = new OptionButton(0.06 * w, 0.06 * w,
				incrementButtonDefault, incrementButtonHover,
				incrementButtonHover, e -> {
			minimumWordLength++;
			minimumWordLengthDisplay.setText(String.valueOf(minimumWordLength));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.2 * w), 0, (int) (0.02 * w));
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(minimumWordLengthIncrementButton, c);

		// Minimum Word Length Decrement Button.
		minimumWordLengthDecrementButton = new OptionButton(0.06 * w, 0.06 * w,
				decrementButtonDefault, decrementButtonHover,
				decrementButtonHover, e -> {
			minimumWordLength--;
			if (minimumWordLength < 1) {
				minimumWordLength = 1;
			}
			minimumWordLengthDisplay.setText(String.valueOf(minimumWordLength));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.02 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(minimumWordLengthDecrementButton, c);

		// General Settings: Target Points.
		targetPoints = 15; // Default target points
		// Target Points Display Label.
		targetPointsDisplay = new JLabel();
		targetPointsDisplay.setMinimumSize(
				new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		targetPointsDisplay.setPreferredSize(
				new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		targetPointsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		targetPointsDisplay.setVerticalAlignment(SwingConstants.CENTER);
		targetPointsDisplay.setText(String.valueOf(targetPoints));
		targetPointsDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets((int) (0.497 * h), (int) (0.06 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 0;
		generalContent.add(targetPointsDisplay, c);

		// Target Points Increment Button
		targetPointsIncrementButton = new OptionButton(0.06 * w, 0.06 * w,
				incrementButtonDefault, incrementButtonHover,
				incrementButtonHover, e -> {
			targetPoints++;
			targetPointsDisplay.setText(String.valueOf(targetPoints));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), 0, 0, (int) (0.02 * w));
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(targetPointsIncrementButton, c);

		// Target Points Decrement Button
		targetPointsDecrementButton = new OptionButton(0.06 * w, 0.06 * w,
				decrementButtonDefault, decrementButtonHover,
				decrementButtonHover, e -> {
			targetPoints--;
			if (targetPoints < 1) {
				targetPoints = 1;
			}
			targetPointsDisplay.setText(String.valueOf(targetPoints));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets(
				(int) (0.03 * h), (int) (0.02 * w), 0, (int) (0.2 * w));
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(targetPointsDecrementButton, c);

		generalLayer.add(generalContent, new GridBagConstraints());
		generalLayer.setLayer(generalContent, 1);
		general.add(generalLayer, new GridBagConstraints());

		add(general, GENERAL_PAGE);

		/* Time Settings Page */
		time = new JPanel();
		time.setBackground(Color.black);
		time.setLayout(new GridBagLayout());
		time.setPreferredSize(new Dimension(w, h));

		timeLayer.setBackground(Color.black);
		timeLayer.setLayout(new GridBagLayout());
		timeLayer.setPreferredSize(new Dimension(w, h));

		timeBg.setIcon(new ImageIcon(timeBgImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		timeBg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 2;
		timeLayer.add(timeBg, c);
		timeLayer.setLayer(timeBg, 0);

		timeContent.setLayout(new GridBagLayout());
		timeContent.setBackground(Color.black);
		timeContent.setPreferredSize(new Dimension(w, h));
		timeContent.setOpaque(false);

		timeReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnButtonDefault, returnButtonHover, returnButtonPress, e -> cardLayout.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		timeContent.add(timeReturnBtn, c);

		initTime = 60000;
		initTimeDisplay.setMinimumSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		initTimeDisplay.setPreferredSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		initTimeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		initTimeDisplay.setVerticalAlignment(SwingConstants.CENTER);
		initTimeDisplay.setText(String.format("%02d:%02d", (initTime / 60000) % 60, (initTime / 1000) % 60));
		initTimeDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets((int) (0.497 * h), 0, 0, (int) (0.058 * w));
		c.weightx = 1;
		c.weighty = 0;
		timeContent.add(initTimeDisplay, c);

		initTimeInc = new OptionButton(0.06 * w, 0.06 * w, incrementButtonDefault, incrementButtonHover, incrementButtonPress, e -> {
			initTime += 5000;
			initTimeDisplay.setText(String.format("%02d:%02d", (initTime / 60000) % 60, (initTime / 1000) % 60));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.2 * w), 0, (int) (0.02 * w));
		c.weightx = 1;
		c.weighty = 1;
		timeContent.add(initTimeInc, c);

		initTimeDec = new OptionButton(0.06 * w, 0.06 * w, decrementButtonDefault, decrementButtonHover, decrementButtonPress, e -> {
			initTime -= 5000;
			if (initTime < 5000) { // Minimum initial time of 5 seconds
				initTime = 5000;
			}
			initTimeDisplay.setText(String.format("%02d:%02d", (initTime / 60000) % 60, (initTime / 1000) % 60));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.02 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		timeContent.add(initTimeDec, c);

		timeIncrement = 5000;
		timeIncrementDisplay.setMinimumSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		timeIncrementDisplay.setPreferredSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		timeIncrementDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		timeIncrementDisplay.setVerticalAlignment(SwingConstants.CENTER);
		timeIncrementDisplay.setText(String.format("%02d:%02d", (timeIncrement / 60000) % 60, (timeIncrement / 1000) % 60));
		timeIncrementDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets((int) (0.497 * h), (int) (0.06 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 0;
		timeContent.add(timeIncrementDisplay, c);

		timeIncrementInc = new OptionButton(0.06 * w, 0.06 * w, incrementButtonDefault, incrementButtonHover, incrementButtonHover, e -> {
			timeIncrement += 1000;
			timeIncrementDisplay.setText(String.format("%02d:%02d", (timeIncrement / 60000) % 60, (timeIncrement / 1000) % 60));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), 0, 0, (int) (0.02 * w));
		c.weightx = 1;
		c.weighty = 1;
		timeContent.add(timeIncrementInc, c);

		timeIncrementDec = new OptionButton(0.06 * w, 0.06 * w, decrementButtonDefault, decrementButtonHover, decrementButtonHover, e -> {
			timeIncrement -= 1000;
			if (timeIncrement < 0) {
				timeIncrement = 0;
			}
			timeIncrementDisplay.setText(String.format("%02d:%02d", (timeIncrement / 60000) % 60, (timeIncrement / 1000) % 60));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.02 * w), 0, (int) (0.2 * w));
		c.weightx = 1;
		c.weighty = 1;
		timeContent.add(timeIncrementDec, c);

		timeLayer.add(timeContent, new GridBagConstraints());
		timeLayer.setLayer(timeContent, 1);
		time.add(timeLayer, new GridBagConstraints());

		add(time, TIME_PAGE);

		/* Difficulty Settings Page */
		difficulty.setBackground(Color.black);
		difficulty.setLayout(new GridBagLayout());
		difficulty.setPreferredSize(new Dimension(w, h));

		difficultyLayer.setBackground(Color.black);
		difficultyLayer.setLayout(new GridBagLayout());
		difficultyLayer.setPreferredSize(new Dimension(w, h));

		difficultyBg.setIcon(new ImageIcon(difficultyBgImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		difficultyBg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 2;
		difficultyLayer.add(difficultyBg, c);
		difficultyLayer.setLayer(difficultyBg, 0);

		difficultyContent.setLayout(new GridBagLayout());
		difficultyContent.setBackground(Color.black);
		difficultyContent.setPreferredSize(new Dimension(w, h));
		difficultyContent.setOpaque(false);

		difficultyReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnButtonDefault, returnButtonHover, returnButtonPress, e -> cardLayout.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		difficultyContent.add(difficultyReturnBtn, c);

		easyBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			easyBtn.setIcon(easyBtn.getPressIcon());
			medBtn.setIcon(medBtn.getDefaultIcon());
			hardBtn.setIcon(hardBtn.getDefaultIcon());
			impossibleBtn.setIcon(impossibleBtn.getDefaultIcon());
			aiDifficulty = 0;
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.15 * h), (int) (0.105 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		difficultyContent.add(easyBtn, c);

		medBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			easyBtn.setIcon(easyBtn.getDefaultIcon());
			medBtn.setIcon(medBtn.getPressIcon());
			hardBtn.setIcon(hardBtn.getDefaultIcon());
			impossibleBtn.setIcon(impossibleBtn.getDefaultIcon());
			aiDifficulty = 1;
		});
		medBtn.setIcon(medBtn.getPressIcon());
		aiDifficulty = 1;
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.15 * h), (int) (0.092 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		difficultyContent.add(medBtn, c);

		hardBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			easyBtn.setIcon(easyBtn.getDefaultIcon());
			medBtn.setIcon(medBtn.getDefaultIcon());
			hardBtn.setIcon(hardBtn.getPressIcon());
			impossibleBtn.setIcon(impossibleBtn.getDefaultIcon());
			aiDifficulty = 2;
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 2;
		c.gridy = 0;
		c.insets = new Insets((int) (0.15 * h), 0, 0, (int) (0.092 * w));
		c.weightx = 1;
		c.weighty = 1;
		difficultyContent.add(hardBtn, c);

		impossibleBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			easyBtn.setIcon(easyBtn.getDefaultIcon());
			medBtn.setIcon(medBtn.getDefaultIcon());
			hardBtn.setIcon(hardBtn.getDefaultIcon());
			impossibleBtn.setIcon(impossibleBtn.getPressIcon());
			aiDifficulty = 3;
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 3;
		c.gridy = 0;
		c.insets = new Insets((int) (0.15 * h), 0, 0, (int) (0.12 * w));
		c.weightx = 1;
		c.weighty = 1;
		difficultyContent.add(impossibleBtn, c);

		difficultyLayer.add(difficultyContent, new GridBagConstraints());
		difficultyLayer.setLayer(difficultyContent, 1);
		difficulty.add(difficultyLayer, new GridBagConstraints());

		add(difficulty, DIFFICULTY_PAGE);

		/* Music Settings Page */
		music.setBackground(Color.black);
		music.setLayout(new GridBagLayout());
		music.setPreferredSize(new Dimension(w, h));

		musicLayer.setBackground(Color.black);
		musicLayer.setLayout(new GridBagLayout());
		musicLayer.setPreferredSize(new Dimension(w, h));

		musicBg.setIcon(new ImageIcon(musicBgImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		musicBg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		musicLayer.add(musicBg, c);
		musicLayer.setLayer(musicBg, 0);

		musicContent.setLayout(new GridBagLayout());
		musicContent.setBackground(Color.black);
		musicContent.setPreferredSize(new Dimension(w, h));
		musicContent.setOpaque(false);

		musicReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnButtonDefault, returnButtonHover, returnButtonPress, e -> cardLayout.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		musicContent.add(musicReturnBtn, c);

		defaultMusicBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			defaultMusicBtn.setIcon(defaultMusicBtn.getPressIcon());
			calmMusicBtn.setIcon(calmMusicBtn.getDefaultIcon());
			intenseMusicBtn.setIcon(intenseMusicBtn.getDefaultIcon());
			musicType = 0;
		});
		defaultMusicBtn.setIcon(defaultMusicBtn.getPressIcon());
		musicType = 0;
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.35 * h), (int) (0.24 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		musicContent.add(defaultMusicBtn, c);

		calmMusicBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			defaultMusicBtn.setIcon(defaultMusicBtn.getDefaultIcon());
			calmMusicBtn.setIcon(calmMusicBtn.getPressIcon());
			intenseMusicBtn.setIcon(intenseMusicBtn.getDefaultIcon());
			musicType = 1;
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets((int) (0.045 * h), (int) (0.24 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		musicContent.add(calmMusicBtn, c);

		intenseMusicBtn = new OptionButton(0.05 * w, 0.05 * w, radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			defaultMusicBtn.setIcon(defaultMusicBtn.getDefaultIcon());
			calmMusicBtn.setIcon(calmMusicBtn.getDefaultIcon());
			intenseMusicBtn.setIcon(intenseMusicBtn.getPressIcon());
			musicType = 2;
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, (int) (0.24 * w), (int) (0.19 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		musicContent.add(intenseMusicBtn, c);

		musicLayer.add(musicContent, new GridBagConstraints());
		musicLayer.setLayer(musicContent, 1);
		music.add(musicLayer, new GridBagConstraints());

		add(music, MUSIC_PAGE);
	}

	public void setPrevScreen(int prevScreen) {
		this.prevScreen = prevScreen;
	}

	public int getMinimumWordLength() {
		return minimumWordLength;
	}

	public int getTargetPoints() {
		return targetPoints;
	}

	public int getInitTime() {
		return initTime;
	}

	public int getTimeIncrement() {
		return timeIncrement;
	}

	public int getAIDifficulty() {
		return aiDifficulty;
	}

	public int getMusicType() {
		return musicType;
	}
}