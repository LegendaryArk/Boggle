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

	// Return Button Icons.
	private ImageIcon returnButtonDefault;
	private ImageIcon returnButtonHover;
	private ImageIcon returnButtonPress;

	// Increment Button Icons.
	private ImageIcon incrementButtonDefault;
	private ImageIcon incrementButtonHover;
	private ImageIcon incrementButtonPress;

	// Decrement Button Icons.
	private ImageIcon decrementButtonDefault;
	private ImageIcon decrementButtonHover;
	private ImageIcon decrementButtonPress;

	// Radio Button Icons.
	private ImageIcon radioButtonDefault;
	private ImageIcon radioButtonHover;
	private ImageIcon radioButtonSelect;

	/* Settings Menu Components. */
	// Main menu panel
	private JPanel menu;
	// ID for the Menu Page used for the Card Layout
	private final String MENU_PAGE = "Menu Page";
	// The layered pane used to display the menu on top of the background.
	private JLayeredPane menuLayeredPane;
	// The background image for the menu.
	private ImageIcon menuBackgroundImage;
	// The label that displays the background image.
	private JLabel menuBackground;
	// The panel that contains the menu buttons.
	private JPanel menuContent;
	// The return button that takes the user back to the main menu.
	private OptionButton menuReturnButton;

	// General Settings Menu Button.
	private OptionButton generalButton;
	// The default icon for the general settings menu button.
	private ImageIcon generalButtonDefault;
	// The hover icon for the general settings menu button.
	private ImageIcon generalButtonHover;
	// The pressed icon for the general settings menu button.
	private ImageIcon generalButtonPress;

	// Time Control Settings Menu Button.
	private OptionButton timeButton;
	// The default icon for the time control settings menu button.
	private ImageIcon timeButtonDefault;
	// The hover icon for the time control settings menu button.
	private ImageIcon timeButtonHover;
	// The pressed icon for the time control settings menu button.
	private ImageIcon timeButtonPress;
	
	// Difficulty Settings Menu Button.
	private OptionButton difficultyButton;
	// The default icon for the difficulty settings menu button.
	private ImageIcon difficultyButtonDefault;
	// The hover icon for the difficulty settings menu button.
	private ImageIcon difficultyButtonHover;
	// The pressed icon for the difficulty settings menu button.
	private ImageIcon difficultyButtonPress;

	// Music Settings Menu Button.
	private OptionButton musicButton;
	// The default icon for the music settings menu button.
	private ImageIcon musicButtonDefault;
	// The hover icon for the music settings menu button.
	private ImageIcon musicButtonHover;
	// The pressed icon for the music settings menu button.
	private ImageIcon musicButtonPress;

	/* General Settings Components. */
	// General Settings Panel.
	private JPanel general;
	// ID for the General Settings Page used for the Card Layout
	private final String GENERAL_PAGE = "General Page";
	// The Layered pane used to display the general settings on top of the
	// background.
	private JLayeredPane generalLayer;
	// The background image for the general settings.
	private ImageIcon generalBackgroundImage;
	// The label that displays the background image.
	private JLabel generalBackground;
	// The panel that contains the general settings' displays and buttons.
	private JPanel generalContent;
	// The button that returns the user to the settings menu.
	private OptionButton generalReturnButton;

	// The minimum length of a word for it to be considered to be valid
	private int minimumWordLength;
	// Displays the minimum word length.
	private JLabel minimumWordLengthDisplay;
	// Increases the minimum word length.
	private OptionButton minimumWordLengthIncrementButton;
	// Decreases the minimum word length.
	private OptionButton minimumWordLengthDecrementButton;

	// The number of points required for a player to win.
	private int targetPoints;
	// Displays the target points.
	private JLabel targetPointsDisplay;
	// Increases the target points.
	private OptionButton targetPointsIncrementButton;
	// Decreases the target points.
	private OptionButton targetPointsDecrementButton;

	/* Time Control Settings Components. */
	// The panel for the time control settings.
	private JPanel time;
	// ID for the Time Control Settings Page used for the Card Layout
	private final String TIME_PAGE = "Time Page";
	// The layered pane used to display the time control settings on top of the
	// background.
	private JLayeredPane timeLayer;
	// The background image for the time control settings.
	private ImageIcon timeBackgroundImage;
	// The label that displays the background image.
	private JLabel timeBackground;
	// The panel that contains the time control settings' displays and buttons.
	private JPanel timeContent;
	// The button that returns the user to the settings menu.
	private OptionButton timeReturnButton;

	// The initial time each player starts with for the game.
	private int initialTime;
	// Displays each player's initial time.
	private JLabel initialTimeDisplay;
	// Increases the initial time.
	private OptionButton initialTimeIncrementButton;
	// Decreases the initial time.
	private OptionButton initialTimeDecrementButton;

	// The amount of time added to a player's time after each turn.
	private int timeIncrement;
	// Displays the time increment.
	private JLabel timeIncrementDisplay;
	// Increases the time increment.
	private OptionButton timeIncrementIncrementButton;
	// Decreases the time increment.
	private OptionButton timeIncrementDecrementButton;

	/* AI Difficulty Settings Components. */
	// The panel for the AI difficulty settings.
	private JPanel difficulty;
	// ID for the AI Difficulty Settings Page used for the Card Layout
	private final String DIFFICULTY_PAGE = "Difficulty Page";
	// The layered pane used to display the AI difficulty settings on top of
	// the background
	private JLayeredPane difficultyLayer;
	// The background image for the AI difficulty settings.
	private ImageIcon difficultyBackgroundImage;
	// The label that displays the AI background image.
	private JLabel difficultyBackground;
	// The panel that contains the AI difficulty settings' displays and
	// buttons.
	private JPanel difficultyContent;
	// The button that returns the user to the settings menu.
	private OptionButton difficultyReturnButton;

	// Difficulty of the AI (How long the words it can find are).
	// 0 - easy, 1 - medium, 2 - hard, 3 - impossible
	private int aiDifficulty;
	// Easy AI Difficulty Radio Button.
	private OptionButton easyButton;
	// Medium AI Difficulty Radio Button.
	private OptionButton mediumButton;
	// Hard AI Difficulty Radio Button.
	private OptionButton hardButton;
	// Impossible AI Difficulty Radio Button.
	private OptionButton impossibleButton;

	/* Music Settings Components. */
	// The panel for the music settings.
	private JPanel music;
	// ID for the Music Settings Page used for the Card Layout.
	private final String MUSIC_PAGE = "Music Page";
	// The layered pane used to display the music settings on top of the
	// background.
	private JLayeredPane musicLayer;
	// The background image for the music settings.
	private ImageIcon musicBackgroundImage;
	// The label that displays the music background image.
	private JLabel musicBackground;
	// The panel that contains the music settings' displays and buttons.
	private JPanel musicContent;
	// The button that returns the user to the settings menu.
	private OptionButton musicReturnButton;

	// The type of music that is in the background when playing the game.
	// 0 - default music, 1 - calm music, 2 - intense music.
	private int musicType;
	// Default music option radio button.
	private OptionButton defaultMusicButton;
	// Calm music option radio button.
	private OptionButton calmMusicButton;
	// Intense music option radio button.
	private OptionButton intenseMusicButton;

	/**
	 * Constructor.
	 * @param mainFrame The parent frame of the game.
	 */
	public SettingsScreen(Boggle mainFrame) {
		// Dimensions of the screen.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// Layout used to switch between different pages.
		this.cardLayout = new CardLayout();

		// Return Button Icons.
		returnButtonDefault = new ImageIcon(getClass()
				.getResource("assets/ReturnBtnDefault.png"));
		returnButtonHover = new ImageIcon(getClass()
				.getResource("assets/ReturnBtnHover.png"));
		returnButtonPress = new ImageIcon(getClass()
				.getResource("assets/ReturnBtnPress.png"));

		// Increment Button Icons.
		incrementButtonDefault = new ImageIcon(getClass()
				.getResource("assets/SettingsIncBtnDefault.png"));
		incrementButtonHover = new ImageIcon(getClass()
				.getResource("assets/SettingsIncBtnHover.png"));
		incrementButtonPress = new ImageIcon(getClass()
				.getResource("assets/SettingsIncBtnPress.png"));

		// Decrement Button Icons.
		decrementButtonDefault = new ImageIcon(getClass()
				.getResource("assets/SettingsDecBtnDefault.png"));
		decrementButtonHover = new ImageIcon(getClass()
				.getResource("assets/SettingsDecBtnHover.png"));
		decrementButtonPress = new ImageIcon(getClass()
				.getResource("assets/SettingsDecBtnPress.png"));

		// Radio Button Icons.
		radioButtonDefault = new ImageIcon(getClass()
				.getResource("assets/RadioBtnDefault.png"));
		radioButtonHover = new ImageIcon(getClass()
				.getResource("assets/RadioBtnHover.png"));
		radioButtonSelect = new ImageIcon(getClass()
				.getResource("assets/RadioBtnSelected.png"));

		// Constraints used to place components in the GridBagLayout.
		GridBagConstraints constraints;

		// Panel setup.
		this.setBackground(Color.BLACK);
		this.setLayout(cardLayout);
		this.setPreferredSize(new Dimension(width, height));

		/* Menu Screen. */
		menu = new JPanel();
		menu.setBackground(Color.BLACK);
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(width, height));

		// Settings Menu Layer Pane.
		menuLayeredPane = new JLayeredPane();
		menuLayeredPane.setBackground(Color.BLACK);
		menuLayeredPane.setLayout(new GridBagLayout());
		menuLayeredPane.setPreferredSize(new Dimension(width, height));

		// Menu Background.
		menuBackgroundImage = new ImageIcon(getClass()
				.getResource("assets/SettingsScreenMenuBg.png"));
		menuBackground = new JLabel();
		menuBackground.setIcon(new ImageIcon(menuBackgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		menuBackground.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 4;
		menuLayeredPane.add(menuBackground, constraints);
		menuLayeredPane.setLayer(menuBackground, 0);

		// Menu Buttons.
		menuContent = new JPanel();
		menuContent.setLayout(new GridBagLayout());
		menuContent.setBackground(Color.BLACK);
		menuContent.setPreferredSize(new Dimension(width, height));
		menuContent.setOpaque(false);

		// Menu Return Button.
		menuReturnButton = new OptionButton(false, 0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			mainFrame.updateSettings();
			mainFrame.menuScreen();
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0, 0);
		menuContent.add(menuReturnButton, constraints);

		// General Settings Menu Button.
		generalButtonDefault = new ImageIcon(getClass()
				.getResource("assets/GeneralBtnDefault.png"));
		generalButtonHover = new ImageIcon(getClass()
				.getResource("assets/GeneralBtnHover.png"));
		generalButtonPress = new ImageIcon(getClass()
				.getResource("assets/GeneralBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		generalButton = new OptionButton(false, 0.3 * width, 0.14 * height,
				generalButtonDefault, generalButtonHover, generalButtonPress,
				e -> cardLayout.show(this, GENERAL_PAGE));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.3 * height), 0, 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(generalButton, constraints);

		// Time Settings Menu Button.
		timeButtonDefault = new ImageIcon(getClass()
				.getResource("assets/TimeBtnDefault.png"));
		timeButtonHover = new ImageIcon(getClass()
				.getResource("assets/TimeBtnHover.png"));
		timeButtonPress = new ImageIcon(getClass()
				.getResource("assets/TimeBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		timeButton = new OptionButton(false, 0.3 * width, 0.14 * height,
				timeButtonDefault, timeButtonHover, timeButtonPress,
				e -> cardLayout.show(this, TIME_PAGE));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(timeButton, constraints);

		// Difficulty Settings Menu Button.
		difficultyButtonDefault = new ImageIcon(getClass()
				.getResource("assets/DifficultyBtnDefault.png"));
		difficultyButtonHover = new ImageIcon(getClass()
				.getResource("assets/DifficultyBtnHover.png"));
		difficultyButtonPress = new ImageIcon(getClass()
				.getResource("assets/DifficultyBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		difficultyButton = new OptionButton(false, 0.3 * width, 0.14 * height,
				difficultyButtonDefault, difficultyButtonHover,
				difficultyButtonPress,
				e -> cardLayout.show(this, DIFFICULTY_PAGE));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(difficultyButton, constraints);

		// Music Settings Menu Button.
		musicButtonDefault = new ImageIcon(getClass()
				.getResource("assets/MusicBtnDefault.png"));
		musicButtonHover = new ImageIcon(getClass()
				.getResource("assets/MusicBtnHover.png"));
		musicButtonPress = new ImageIcon(getClass()
				.getResource("assets/MusicBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		musicButton = new OptionButton(false, 0.3 * width, 0.14 * height,
				musicButtonDefault, musicButtonHover, musicButtonPress,
				e -> cardLayout.show(this, MUSIC_PAGE));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 0, (int) (0.05 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(musicButton, constraints);

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
		general.setBackground(Color.BLACK);
		general.setLayout(new GridBagLayout());
		general.setPreferredSize(new Dimension(width, height));

		// General Settings Layered Pane.
		generalLayer = new JLayeredPane();
		generalLayer.setBackground(Color.BLACK);
		generalLayer.setLayout(new GridBagLayout());
		generalLayer.setPreferredSize(new Dimension(width, height));

		// General Settings Background.
		generalBackgroundImage = new ImageIcon(getClass()
				.getResource("assets/GeneralSettingsScreenBg.png"));
		generalBackground = new JLabel();
		generalBackground.setIcon(new ImageIcon(generalBackgroundImage
				.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		generalBackground.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		generalLayer.add(generalBackground, constraints);
		generalLayer.setLayer(generalBackground, 0);

		// General Settings' Displays and Buttons
		generalContent = new JPanel();
		generalContent.setLayout(new GridBagLayout());
		generalContent.setBackground(Color.BLACK);
		generalContent.setPreferredSize(new Dimension(width, height));
		generalContent.setOpaque(false);

		// General Settings Return Button
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		generalReturnButton = new OptionButton(false, 
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0, 0);
		generalContent.add(generalReturnButton, constraints);

		// General Settings: Minimum Word Length.
		minimumWordLength = 3; // Default minimum word length.
		// Minimum Word Length Display Label.
		minimumWordLengthDisplay = new JLabel();
		minimumWordLengthDisplay.setMinimumSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		minimumWordLengthDisplay.setPreferredSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		minimumWordLengthDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		minimumWordLengthDisplay.setVerticalAlignment(SwingConstants.CENTER);
		minimumWordLengthDisplay.setText(String.valueOf(minimumWordLength));
		minimumWordLengthDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(
				(int) (0.497 * height), 0, 0, (int) (0.058 * width));
		constraints.weightx = 1;
		constraints.weighty = 0;
		generalContent.add(minimumWordLengthDisplay, constraints);

		// Minimum Word Length Increment Button.
		minimumWordLengthIncrementButton = new OptionButton(false, 
				0.06 * width, 0.06 * width, 
				incrementButtonDefault, incrementButtonHover,
				incrementButtonHover, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			minimumWordLength++; // Increase minimum word length by 1.
			if (minimumWordLength > 6) { // Maximum minimum word length is 6.
				minimumWordLength = 6;
			}
			minimumWordLengthDisplay.setText(String.valueOf(minimumWordLength));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets((int) (0.03 * height),
				(int) (0.2 * width), 0, (int) (0.02 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		generalContent.add(minimumWordLengthIncrementButton, constraints);

		// Minimum Word Length Decrement Button.
		minimumWordLengthDecrementButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				decrementButtonDefault, decrementButtonHover,
				decrementButtonHover, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			minimumWordLength--; // Decrease minimum word length by 1.
			if (minimumWordLength < 1) { // Minimum minimum word length is 1.
				minimumWordLength = 1;
			}
			minimumWordLengthDisplay.setText(String.valueOf(minimumWordLength));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(
				(int) (0.03 * height), (int) (0.02 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		generalContent.add(minimumWordLengthDecrementButton, constraints);

		// General Settings: Target Points.
		targetPoints = 15; // Default target points
		// Target Points Display Label.
		targetPointsDisplay = new JLabel();
		targetPointsDisplay.setMinimumSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		targetPointsDisplay.setPreferredSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		targetPointsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		targetPointsDisplay.setVerticalAlignment(SwingConstants.CENTER);
		targetPointsDisplay.setText(String.valueOf(targetPoints));
		targetPointsDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(
				(int) (0.497 * height), (int) (0.06 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 0;
		generalContent.add(targetPointsDisplay, constraints);

		// Target Points Increment Button
		targetPointsIncrementButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				incrementButtonDefault, incrementButtonHover,
				incrementButtonHover, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			targetPoints++; // Increase target points by 1.
			targetPointsDisplay.setText(String.valueOf(targetPoints));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.insets = new Insets(
				(int) (0.03 * height), 0, 0, (int) (0.02 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		generalContent.add(targetPointsIncrementButton, constraints);

		// Target Points Decrement Button
		targetPointsDecrementButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				decrementButtonDefault, decrementButtonHover,
				decrementButtonHover, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			targetPoints--; // Decrease the target point by 1.
			if (targetPoints < 1) { // Minimum target point is 1.
				targetPoints = 1;
			}

			targetPointsDisplay.setText(String.valueOf(targetPoints));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.insets = new Insets((int) (0.03 * height),
				(int) (0.02 * width), 0, (int) (0.2 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		generalContent.add(targetPointsDecrementButton, constraints);

		// Add the displays and buttons to the layered pane.
		generalLayer.add(generalContent, new GridBagConstraints());
		generalLayer.setLayer(generalContent, 1);
		general.add(generalLayer, new GridBagConstraints());

		// Add the page to the main panel.
		add(general, GENERAL_PAGE);

		/* Time Control Settings Page */
		time = new JPanel();
		time.setBackground(Color.BLACK);
		time.setLayout(new GridBagLayout());
		time.setPreferredSize(new Dimension(width, height));

		// Time Control Settings Layered Pane.
		timeLayer = new JLayeredPane();
		timeLayer.setBackground(Color.BLACK);
		timeLayer.setLayout(new GridBagLayout());
		timeLayer.setPreferredSize(new Dimension(width, height));

		// Time Control Settings Background.
		timeBackgroundImage = new ImageIcon(getClass()
				.getResource("assets/TimeSettingsScreenBg.png"));
		timeBackground = new JLabel();
		timeBackground.setIcon(new ImageIcon(timeBackgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		timeBackground.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		timeLayer.add(timeBackground, constraints);
		timeLayer.setLayer(timeBackground, 0);

		// Time Control Settings' Displays and Buttons.
		timeContent = new JPanel();
		timeContent.setLayout(new GridBagLayout());
		timeContent.setBackground(Color.BLACK);
		timeContent.setPreferredSize(new Dimension(width, height));
		timeContent.setOpaque(false);

		// Time Control Settings Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		timeReturnButton = new OptionButton(false, 0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0, 0);
		timeContent.add(timeReturnButton, constraints);

		// Time Control Settings: Initial Time.
		initialTime = 60000; // Default initial time of 1 minute.
		// Initial Time Display Label.
		initialTimeDisplay = new JLabel();
		initialTimeDisplay.setMinimumSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		initialTimeDisplay.setPreferredSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		initialTimeDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		initialTimeDisplay.setVerticalAlignment(SwingConstants.CENTER);
		// Uses String.format to force 2 digits.
		// https://www.w3schools.com/java/ref_string_format.asp
		initialTimeDisplay.setText(String.format("%02d:%02d",
				(initialTime / 60000) % 60, (initialTime / 1000) % 60));
		initialTimeDisplay.setFont(new Font("Verdana",
				Font.PLAIN, 70));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(
				(int) (0.497 * height), 0, 0, (int) (0.058 * width));
		constraints.weightx = 1;
		constraints.weighty = 0;
		timeContent.add(initialTimeDisplay, constraints);

		// Initial Time Increase Button.
		initialTimeIncrementButton = new OptionButton(false, 
				0.06 * width, 0.06 * width, 
				incrementButtonDefault, incrementButtonHover,
				incrementButtonPress, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			initialTime += 5000; // Increase by 5 seconds

			// Uses String.format to force 2 digits.
			// https://www.w3schools.com/java/ref_string_format.asp
			initialTimeDisplay.setText(String.format("%02d:%02d",
					(initialTime / 60000) % 60, (initialTime / 1000) % 60));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets((int) (0.03 * height),
				(int) (0.2 * width), 0, (int) (0.02 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		timeContent.add(initialTimeIncrementButton, constraints);

		// Initial Time Decrease Button.
		initialTimeDecrementButton = new OptionButton(false, 
				0.06 * width, 0.06 * width, 
				decrementButtonDefault, decrementButtonHover,
				decrementButtonPress, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			initialTime -= 5000; // Decrease by 5 seconds
			if (initialTime < 5000) { // Minimum initial time of 5 seconds
				initialTime = 5000;
			}

			// Uses String.format to force 2 digits.
			// https://www.w3schools.com/java/ref_string_format.asp
			initialTimeDisplay.setText(String.format("%02d:%02d",
					(initialTime / 60000) % 60, (initialTime / 1000) % 60));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.insets = new Insets(
				(int) (0.03 * height), (int) (0.02 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		timeContent.add(initialTimeDecrementButton, constraints);

		// Time Control Settings: Time Increment.
		timeIncrement = 5000; // Default increment of 5 seconds.
		// Time Increment Display Label.
		timeIncrementDisplay = new JLabel();
		timeIncrementDisplay.setMinimumSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		timeIncrementDisplay.setPreferredSize(
				new Dimension((int) (0.18 * width), (int) (0.16 * height)));
		timeIncrementDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		timeIncrementDisplay.setVerticalAlignment(SwingConstants.CENTER);
		// Uses String.format to force 2 digits.
		// https://www.w3schools.com/java/ref_string_format.asp
		timeIncrementDisplay.setText(String.format("%02d:%02d",
				(timeIncrement / 60000) % 60, (timeIncrement / 1000) % 60));
		timeIncrementDisplay.setFont(new Font("Verdana",
				Font.PLAIN, 70));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		constraints.insets = new Insets(
				(int) (0.497 * height), (int) (0.06 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 0;
		timeContent.add(timeIncrementDisplay, constraints);

		// Time Increment Increase Button.
		timeIncrementIncrementButton = new OptionButton(false, 
				0.06 * width, 0.06 * width,
				incrementButtonDefault, incrementButtonHover,
				incrementButtonHover, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			timeIncrement += 1000; // Increase by 1 second

			// Uses String.format to force 2 digits.
			// https://www.w3schools.com/java/ref_string_format.asp
			timeIncrementDisplay.setText(String.format("%02d:%02d",
					(timeIncrement / 60000) % 60,
					(timeIncrement / 1000) % 60));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 2;
		constraints.gridy = 1;
		constraints.insets = new Insets(
				(int) (0.03 * height), 0, 0, (int) (0.02 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		timeContent.add(timeIncrementIncrementButton, constraints);

		// Time Increment Decrease Button.
		timeIncrementDecrementButton = new OptionButton(false,
				0.06 * width, 0.06 * width, 
				decrementButtonDefault, decrementButtonHover,
				decrementButtonHover, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			timeIncrement -= 1000; // Decrease by 1 second
			if (timeIncrement < 2000) { // Minimum time increment of 1 seconds
				timeIncrement = 2000;
			}

			// Uses String.format to force 2 digits.
			// https://www.w3schools.com/java/ref_string_format.asp
			timeIncrementDisplay.setText(String.format("%02d:%02d",
					(timeIncrement / 60000) % 60,
					(timeIncrement / 1000) % 60));
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.insets = new Insets((int) (0.03 * height),
				(int) (0.02 * width), 0, (int) (0.2 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		timeContent.add(timeIncrementDecrementButton, constraints);

		// Add the displays and buttons to the time control settings page.
		timeLayer.add(timeContent, new GridBagConstraints());
		timeLayer.setLayer(timeContent, 1);
		time.add(timeLayer, new GridBagConstraints());

		// Add the time control settings page to the main panel.
		add(time, TIME_PAGE);

		/* AI Difficulty Settings Page. */
		difficulty = new JPanel();
		difficulty.setBackground(Color.BLACK);
		difficulty.setLayout(new GridBagLayout());
		difficulty.setPreferredSize(new Dimension(width, height));

		// AI Difficulty Settings Layered Pane.
		difficultyLayer = new JLayeredPane();
		difficultyLayer.setBackground(Color.BLACK);
		difficultyLayer.setLayout(new GridBagLayout());
		difficultyLayer.setPreferredSize(new Dimension(width, height));

		// AI Difficulty Settings Background.
		difficultyBackgroundImage = new ImageIcon(getClass()
				.getResource("assets/DifficultySettingsScreenBg.png"));
		difficultyBackground = new JLabel();
		difficultyBackground.setIcon(new ImageIcon(difficultyBackgroundImage
				.getImage().getScaledInstance(width, height,
						Image.SCALE_SMOOTH)));
		difficultyBackground.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 2;
		difficultyLayer.add(difficultyBackground, constraints);
		difficultyLayer.setLayer(difficultyBackground, 0);

		// AI Difficulty Settings' Buttons.
		difficultyContent = new JPanel();
		difficultyContent.setLayout(new GridBagLayout());
		difficultyContent.setBackground(Color.BLACK);
		difficultyContent.setPreferredSize(new Dimension(width, height));
		difficultyContent.setOpaque(false);

		// AI Difficulty Settings Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		difficultyReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0, 0);
		difficultyContent.add(difficultyReturnButton, constraints);

		// Easy AI Difficulty Radio Button.
		easyButton = new OptionButton(true,
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select easy difficulty radio
			// button.
			mediumButton.setIcon(mediumButton.getDefaultIcon());
			hardButton.setIcon(hardButton.getDefaultIcon());
			impossibleButton.setIcon(impossibleButton.getDefaultIcon());
			aiDifficulty = 0;
			System.out.println("Switched AI Difficulty to Easy");
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.15 * height), (int) (0.105 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		difficultyContent.add(easyButton, constraints);

		// Medium AI Difficulty Radio Button.
		mediumButton = new OptionButton(true, 
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select medium difficulty radio
			// button.
			easyButton.setIcon(easyButton.getDefaultIcon());
			hardButton.setIcon(hardButton.getDefaultIcon());
			impossibleButton.setIcon(impossibleButton.getDefaultIcon());
			aiDifficulty = 1;
			System.out.println("Switched AI Difficulty to Medium");
		});
		mediumButton.setIcon(mediumButton.getPressIcon());
		aiDifficulty = 1;
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_START;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.15 * height), (int) (0.092 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		difficultyContent.add(mediumButton, constraints);

		// Hard AI Difficulty Radio Button.
		hardButton = new OptionButton(true, 
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select hard difficulty radio
			// button.
			easyButton.setIcon(easyButton.getDefaultIcon());
			mediumButton.setIcon(mediumButton.getDefaultIcon());
			impossibleButton.setIcon(impossibleButton.getDefaultIcon());
			aiDifficulty = 2;
			System.out.println("Switched AI Difficulty to Hard");
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.15 * height), 0, 0, (int) (0.092 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		difficultyContent.add(hardButton, constraints);

		// Impossible AI Difficulty Radio Button.
		impossibleButton = new OptionButton(true,
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select impossible difficulty radio
			// button.
			easyButton.setIcon(easyButton.getDefaultIcon());
			mediumButton.setIcon(mediumButton.getDefaultIcon());
			hardButton.setIcon(hardButton.getDefaultIcon());
			aiDifficulty = 3;
			System.out.println("Switched AI Difficulty to Impossible");
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.gridx = 3;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.15 * height), 0, 0, (int) (0.12 * width));
		constraints.weightx = 1;
		constraints.weighty = 1;
		difficultyContent.add(impossibleButton, constraints);

		// Add the buttons to the AI difficulty settings page.
		difficultyLayer.add(difficultyContent, new GridBagConstraints());
		difficultyLayer.setLayer(difficultyContent, 1);
		difficulty.add(difficultyLayer, new GridBagConstraints());

		// Add the AI difficulty settings page to the main panel.
		add(difficulty, DIFFICULTY_PAGE);

		/* Music Settings Page */
		music = new JPanel();
		music.setBackground(Color.BLACK);
		music.setLayout(new GridBagLayout());
		music.setPreferredSize(new Dimension(width, height));

		// Music Settings Layered Pane.
		musicLayer = new JLayeredPane();
		musicLayer.setBackground(Color.BLACK);
		musicLayer.setLayout(new GridBagLayout());
		musicLayer.setPreferredSize(new Dimension(width, height));

		// Music Settings Background.
		musicBackgroundImage = new ImageIcon(getClass()
				.getResource("assets/MusicSettingsScreenBg.png"));
		musicBackground = new JLabel();
		musicBackground.setIcon(new ImageIcon(musicBackgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		musicBackground.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		musicLayer.add(musicBackground, constraints);
		musicLayer.setLayer(musicBackground, 0);

		// Music Settings' Buttons.
		musicContent = new JPanel();
		musicContent.setLayout(new GridBagLayout());
		musicContent.setBackground(Color.BLACK);
		musicContent.setPreferredSize(new Dimension(width, height));
		musicContent.setOpaque(false);

		// Music Settings Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		musicReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0, 0);
		musicContent.add(musicReturnButton, constraints);

		// Default Music Radio Button.
		defaultMusicButton = new OptionButton(true,
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select default music radio button.
			defaultMusicButton.setIcon(defaultMusicButton.getPressIcon());
			calmMusicButton.setIcon(calmMusicButton.getDefaultIcon());
			intenseMusicButton.setIcon(intenseMusicButton.getDefaultIcon());
			musicType = 0;
			System.out.println("Switched Music to Default");
		});
		defaultMusicButton.setIcon(defaultMusicButton.getPressIcon());
		musicType = 0;
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.35 * height), (int) (0.24 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		musicContent.add(defaultMusicButton, constraints);

		// Intense Music Radio Button.
		intenseMusicButton = new OptionButton(true,
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select intense music radio button.
			defaultMusicButton.setIcon(defaultMusicButton.getDefaultIcon());
			calmMusicButton.setIcon(calmMusicButton.getDefaultIcon());
			intenseMusicButton.setIcon(intenseMusicButton.getPressIcon());
			musicType = 1;
			System.out.println("Switched Music to Intense");
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.insets = new Insets(
				(int) (0.045 * height), (int) (0.24 * width), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		musicContent.add(intenseMusicButton, constraints);

		// Calm Music Radio Button.
		calmMusicButton = new OptionButton(true,
				0.05 * width, 0.05 * width,
				radioButtonDefault, radioButtonHover, radioButtonSelect, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Reset other radio buttons and select calm music radio button.
			defaultMusicButton.setIcon(defaultMusicButton.getDefaultIcon());
			calmMusicButton.setIcon(calmMusicButton.getPressIcon());
			intenseMusicButton.setIcon(intenseMusicButton.getDefaultIcon());
			musicType = 2;
			System.out.println("Switched Music to Calm");
		});
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(
				0, (int) (0.24 * width), (int) (0.19 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		musicContent.add(calmMusicButton, constraints);

		// Add the buttons to the music settings page.
		musicLayer.add(musicContent, new GridBagConstraints());
		musicLayer.setLayer(musicContent, 1);
		music.add(musicLayer, new GridBagConstraints());

		// Add the music settings page to the main panel.
		add(music, MUSIC_PAGE);
	}

	/**
	 * Gets the minimum word length.
	 * @return The minimum word length.
	 */
	public int getMinimumWordLength() {
		return minimumWordLength;
	}

	/**
	 * Gets the target points.
	 * @return The target points.
	 */
	public int getTargetPoints() {
		return targetPoints;
	}

	/**
	 * Gets the initial time.
	 * @return the initial time.
	 */
	public int getInitialTime() {
		return initialTime;
	}

	/**
	 * Gets the time increment.
	 * @return the time increment.
	 */
	public int getTimeIncrement() {
		return timeIncrement;
	}

	/**
	 * Gets the difficulty of the AI.
	 * @return 0 - easy, 1 - medium, 2 - hard, 3 - impossible.
	 */
	public int getAIDifficulty() {
		return aiDifficulty;
	}

	/**
	 * Gets the type of the background music played during the game.
	 * @return 0 - changes depending on the mode and AI difficulty,
	 *          1 - calm music, 2 - intense music.
	 */
	public int getMusicType() {
		return musicType;
	}
}