/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class displays the Guide screen and its other panels
 */
public class GuideScreen extends JPanel {
	// Card Layout to switch between different pages.
	private CardLayout cardLayout;

	// Default icon for return button.
	private ImageIcon returnButtonDefault;
	// Hover icon for return button.
	private ImageIcon returnButtonHover;
	// Press icon for return button.
	private ImageIcon returnButtonPress;

	// Default button for previous button.
	private ImageIcon previousButtonDefault;
	// Hover button for previous button.
	private ImageIcon previousButtonHover;
	// Press button for previous button.
	private ImageIcon previousButtonPress;

	// Default button for next button.
	private ImageIcon nextButtonDefault;
	// Hover button for next button.
	private ImageIcon nextButtonHover;
	// Press button for next button.
	private ImageIcon nextButtonPress;

	// Panel for the main menu.
	private JPanel menu;
	// ID for menu page
	private final String MENU_PAGE = "Menu Page";
	// Layered pane for the main menu.
	private JLayeredPane menuLayeredPane;
	// Menu background image.
	private ImageIcon menuBackgroundImage;
	// Music background image.
	private JLabel menuBackground;
	// Holds menu buttons.
	private JPanel menuContent;
	// Button to return to menu.
	private OptionButton menuReturnButton;

	// Button to game Information screen
	private OptionButton gameInformationButton;
	// Default button for gameInformation button.
	private ImageIcon gameInformationButtonDefault;
	// Hover button for gameInformation button.
	private ImageIcon gameInformationButtonHover;
	// Press button for gameInformation button.
	private ImageIcon gameInformationButtonPress;

	// Button to points breakdown screen
	private OptionButton pointsBreakdownButton;
	// Default button for points breakdown button.
	private ImageIcon pointsBreakdownButtonDefault;
	// Hover button for points breakdown button.
	private ImageIcon pointsBreakdownButtonHover;
	// Press button for points breakdown button.
	private ImageIcon pointsBreakdownButtonPress;

	// Button to settings info screen
	private OptionButton settingsInformationButton;
	// Default button for settings Information button.
	private ImageIcon settingsInformationButtonDefault;
	// Hover button for settings Information button.
	private ImageIcon settingsInformationButtonHover;
	// Press button for settings Information button.
	private ImageIcon settingsInformationButtonPress;

	// Panel for the first page of game info
	private JPanel gameInformationOne;
	// ID for game info page 1.
	private final String GAME_INFORMATION_PAGE_1 = "Game Info Page 1";
	// Layered pane for game info page 1.
	private JLayeredPane gameInformationOneLayeredPane;
	// Image for the game info page 1.
	private ImageIcon gameInformationOneImage;
	// Label to hold the game info page 1 display.
	private JLabel gameInformationOneLabel;
	// Holds game info page 1 buttons.
	private JPanel gameInformationOneContent;
	// Button for game info page 1 return.
	private OptionButton gameInformationOneReturnButton;
	// Button for game info page 1 next.
	private OptionButton gameInformationOneNextButton;

	// Panel for the second page of game info
	private JPanel gameInformationTwo;
	// ID for game info page 2
	private String GAME_INFORMATION_PAGE_2 = "Game Information Page 2";
	// Layered pane for game info page 2
	private JLayeredPane gameInformationTwoLayeredPane;
	// Image for game info page 2
	private ImageIcon gameInformationTwoImage;
	// Label to hold the game info page 2 display
	private JLabel gameInformationTwoLabel;
	// Holds game info page 2 buttons
	private JPanel gameInformationTwoContent;
	// Button for game info page 2 return
	private OptionButton gameInformationTowReturnButton;
	// Button for game info page 2 previous
	private OptionButton gameInformationTwoPreviousButton;
	// Button for game info page 2 next
	private OptionButton gameInformationTwoNextButton;

	// Panel for the third page of game info
	private JPanel gameInformationThree;
	// ID for game info page 3.
	private String GAME_INFORMATION_PAGE_3 = "Game Information Page 3";
	// Layered pane for game info page 3.
	private JLayeredPane gameInformationThreeLayeredPane;
	// Image for game info page 3.
	private ImageIcon gameInformationThreeImage;
	// Label to hold the game info page 3 display.
	private JLabel gameInformationThreeLabel;
	// Holds game info page 3 buttons.
	private JPanel gameInformationThreeContent;
	// Button for game info page 3 return.
	private OptionButton gameInformationThreeReturnButton;
	// Button for game info page 3 previous.
	private OptionButton gameInformationThreePreviousButton;

	// Panel for the points breakdown screen.
	private JPanel pointsBreakdown;
	// ID for points breakdown.
	private String POINTS_BREAKDOWN_PAGE = "Points Breakdown Page";
	// Layered pane points breakdown.
	private JLayeredPane pointsBreakdownLayeredPane;
	// Image for points breakdown.
	private ImageIcon pointsBreakdownImage;
	// Label to hold points breakdown display.
	private JLabel pointsBreakdownLabel;
	// Holds points breakdown buttons.
	private JPanel pointsBreakdownContent;
	// Button for points breakdown return.
	private OptionButton pointsBreakdownReturnButton;

	// Panel for the settings info page 1.
	private JPanel settingsInformationOne;
	// ID for settings info page 1.
	private String SETTINGS_INFORMATION_PAGE_1 = "Settings Information Page 1";
	// Layered pane setting info page 1.
	private JLayeredPane settingsInformationOneLayeredPane;
	// Image for settings info page 1.
	private ImageIcon settingsInformationOneImage;
	// Label to hold settings info page 1 display.
	private JLabel settingsInformationOneLabel;
	// Holds points settings info page 1 display.
	private JPanel settingsInformationOneContent;
	// Button for settings info page 1 return,
	private OptionButton settingsInformationOneReturnButton;
	// Button for settings info page 1 next.
	private OptionButton settingsInformationOneNextButton;

	// Panel for the settings information page 2.
	private JPanel settingsInformationTwo;
	// ID for settings information page 2.
	private String SETTINGS_INFORMATION_PAGE_2 = "Settings Information Page 2";
	// Layered pane for settings information page 2.
	private JLayeredPane settingsInformationTwoLayeredPane;
	// Image for settings information page 2.
	private ImageIcon settingsInformationTwoImage;
	// Label to hold settings information page 2 display.
	private JLabel settingsInformationTwoLabel;
	// Holds settings information page 2 buttons.
	private JPanel settingsInformationTwoContent;
	// Button for settings information page 2 return.
	private OptionButton settingsInformationTwoReturnButton;
	// Button for settings information page 2 previous.
	private OptionButton settingsInformationTwoPreviousButton;
	// Button for settings information page 2 next.
	private OptionButton settingsInformationTwoNextButton;

	// Panel for the settings information page 3.
	private JPanel settingsInformationThree;
	// ID for settings information page 3.
	private String SETTINGS_INFORMATION_PAGE_3 = "Settings Information Page 3";
	// Layered pane for settings information page 3
	private JLayeredPane settingsInformationThreeLayer;
	// Image for settings information page 3.
	private ImageIcon settingsInformationThreeImage;
	// Label to hold settings information page 3 display.
	private JLabel settingsInformationThreeLabel;
	// Holds settings information page 3 buttons.
	private JPanel settingsInformationThreeContent;
	// Button for settings information page 3 return.
	private OptionButton settingsInformationThreeReturnButton;
	// Button for settings information page 3 previous.
	private OptionButton settingsInformationThreePreviousButton;
	// Button for settings information page 3 next.
	private OptionButton settingsInformationThreeNextButton;

	// Panel for the settings information page 4.
	private JPanel settingsInformationFour;
	// ID for settings information page 4.
	private String SETTINGS_INFORMATION_PAGE_4 = "Settings Information Page 4";
	// Layered pane for settings information page 4.
	private JLayeredPane settingsInformationFourLayeredPane;
	// Image for settings information page 4.
	private ImageIcon settingsInformationFourImage;
	// Label to hold settings information page 4 display.
	private JLabel settingsInformationFourLabel;
	// Holds settings information page 4 buttons.
	private JPanel settingsInformationFourContent;
	// Button for settings information page 4 return.
	private OptionButton settingsInformationFourReturnButton;
	// Button for settings information page 4 previous.
	private OptionButton settingsInformationFourPreviousBtn;

	/**
	 * Constructor
	 * @param mainFrame main frame for game
	 */
	public GuideScreen(Boggle mainFrame) {
		// Get the width and height of the frame
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// Instantiate cardLayout.
		cardLayout = new CardLayout();

		// Return Button Icons.
		returnButtonDefault = new ImageIcon(
				getClass().getResource("assets/ReturnBtnDefault.png"));
		returnButtonHover = new ImageIcon(
				getClass().getResource("assets/ReturnBtnHover.png"));
		returnButtonPress = new ImageIcon(
				getClass().getResource("assets/ReturnBtnPress.png"));

		// Previous Button Icons.
		previousButtonDefault = new ImageIcon(
				getClass().getResource("assets/PreviousBtnDefault.png"));
		previousButtonHover = new ImageIcon(
				getClass().getResource("assets/PreviousBtnHover.png"));
		previousButtonPress = new ImageIcon(
				getClass().getResource("assets/PreviousBtnPress.png"));

		// Next Button Icons.
		nextButtonDefault = new ImageIcon(
				getClass().getResource("assets/NextBtnDefault.png"));
		nextButtonHover = new ImageIcon(
				getClass().getResource("assets/NextBtnHover.png"));
		nextButtonPress = new ImageIcon(
				getClass().getResource("assets/NextBtnPress.png"));

		// Set background colour to black
		this.setBackground(Color.BLACK);
		this.setLayout(cardLayout);
		// Set dimensions of the screen
		this.setPreferredSize(new Dimension(width, height));

		// Used to set the location of the components.
		GridBagConstraints constraints;

		/* Menu Page. */
		menu = new JPanel();
		menu.setBackground(Color.BLACK);
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(width, height));

		// Menu Layered.
		menuLayeredPane = new JLayeredPane();
		menuLayeredPane.setBackground(Color.BLACK);
		menuLayeredPane.setLayout(new GridBagLayout());
		menuLayeredPane.setPreferredSize(new Dimension(width, height));

		// Menu Background.
		menuBackgroundImage = new ImageIcon(
				getClass().getResource("assets/GuideScreenMenuBg.png"));
		menuBackground = new JLabel();
		menuBackground.setIcon(new ImageIcon(menuBackgroundImage.getImage()
				.getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		menuBackground.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 3;
		menuLayeredPane.add(menuBackground, constraints);
		menuLayeredPane.setLayer(menuBackground, 0);

		// Menu Buttons.
		menuContent = new JPanel();
		menuContent.setLayout(new GridBagLayout());
		menuContent.setBackground(Color.BLACK);
		menuContent.setPreferredSize(new Dimension(width, height));
		menuContent.setOpaque(false);

		// Menu Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		menuReturnButton = new OptionButton(false, 0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> mainFrame.menuScreen());
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets(
				(int) (0.04 * height), (int) (0.04 * height), 0,0);
		menuContent.add(menuReturnButton, constraints);

		// Game Information Menu Button.
		gameInformationButtonDefault = new ImageIcon(
				getClass().getResource("assets/GameInfoBtnDefault.png"));
		gameInformationButtonHover = new ImageIcon(
				getClass().getResource("assets/GameInfoBtnHover.png"));
		gameInformationButtonPress = new ImageIcon(
				getClass().getResource("assets/GameInfoBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationButton = new OptionButton(false,
				0.5 * width, 0.15 * height,
				gameInformationButtonDefault, gameInformationButtonHover,
				gameInformationButtonPress,
				e -> cardLayout.show(this, GAME_INFORMATION_PAGE_1));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.35 * height), 0, 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(gameInformationButton, constraints);

		// Points Breakdown Menu Button.
		pointsBreakdownButtonDefault = new ImageIcon(getClass()
				.getResource("assets/PointsBreakdownBtnDefault.png"));
		pointsBreakdownButtonHover = new ImageIcon(getClass()
				.getResource("assets/PointsBreakdownBtnHover.png"));
		pointsBreakdownButtonPress = new ImageIcon(getClass()
				.getResource("assets/PointsBreakdownBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		pointsBreakdownButton = new OptionButton(false, 0.5 * width,
				0.15 * height, pointsBreakdownButtonDefault,
				pointsBreakdownButtonHover, pointsBreakdownButtonPress,
				e -> cardLayout.show(this, POINTS_BREAKDOWN_PAGE));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(pointsBreakdownButton, constraints);

		// Settings Information Menu Button.
		settingsInformationButtonDefault = new ImageIcon(getClass()
				.getResource("assets/SettingsInfoBtnDefault.png"));
		settingsInformationButtonHover = new ImageIcon(getClass()
				.getResource("assets/SettingsInfoBtnHover.png"));
		settingsInformationButtonPress = new ImageIcon(getClass()
				.getResource("assets/SettingsInfoBtnPress.png"));
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationButton = new OptionButton(false, 0.5 * width,
				0.15 * height, settingsInformationButtonDefault,
				settingsInformationButtonHover, settingsInformationButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_1));
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.insets = new Insets(0, 0, (int) (0.05 * height), 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		menuContent.add(settingsInformationButton, constraints);

		// Adding buttons to the layered pane.
		menuLayeredPane.add(menuContent, new GridBagConstraints());
		menuLayeredPane.setLayer(menuContent, 1);

		// Add the screen to the panel.
		menu.add(menuLayeredPane, new GridBagConstraints());

		// Add the page to the main panel.
		add(menu, MENU_PAGE);
		// Display the menu page as the initial screen.
		cardLayout.show(this, MENU_PAGE);

		/* Game Information Page 1. */
		gameInformationOne = new JPanel();
		gameInformationOne.setBackground(Color.BLACK);
		gameInformationOne.setLayout(new GridBagLayout());
		gameInformationOne.setPreferredSize(new Dimension(width, height));

		// Game Information Page 1 Layers.
		gameInformationOneLayeredPane = new JLayeredPane();
		gameInformationOneLayeredPane.setBackground(Color.BLACK);
		gameInformationOneLayeredPane.setLayout(new GridBagLayout());
		gameInformationOneLayeredPane.setPreferredSize(
				new Dimension(width, height));

		// Game Information 1.
		gameInformationOneImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenGameInfo1.png"));
		gameInformationOneLabel = new JLabel();
		gameInformationOneLabel.setIcon(new ImageIcon(gameInformationOneImage
				.getImage().getScaledInstance(width, height,
						Image.SCALE_SMOOTH)));
		gameInformationOneLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		gameInformationOneLayeredPane.add(gameInformationOneLabel,
				constraints);
		gameInformationOneLayeredPane.setLayer(gameInformationOneLabel, 0);

		// Game Information 1 Buttons.
		gameInformationOneContent = new JPanel();
		gameInformationOneContent.setLayout(new GridBagLayout());
		gameInformationOneContent.setBackground(Color.BLACK);
		gameInformationOneContent.setPreferredSize(
				new Dimension(width, height));
		gameInformationOneContent.setOpaque(false);

		// Game Information 1 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationOneReturnButton = new OptionButton(false, 0.06 * width,
				0.06 * width, returnButtonDefault, returnButtonHover,
				returnButtonPress, e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		gameInformationOneContent.add(gameInformationOneReturnButton,
				constraints);

		// Game Information 1 Next Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationOneNextButton = new OptionButton(false, 0.05 * width,
				0.05 * width, nextButtonDefault, nextButtonHover,
				nextButtonPress,
				e -> cardLayout.show(this, GAME_INFORMATION_PAGE_2));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height), 0, 0,
				(int) (0.05 * height));
		constraints.weightx = 1;
		constraints.weighty = 1;
		gameInformationOneContent.add(gameInformationOneNextButton,
				constraints);

		// Add the buttons to the layered pane.
		gameInformationOneLayeredPane.add(gameInformationOneContent,
				new GridBagConstraints());
		gameInformationOneLayeredPane.setLayer(gameInformationOneContent, 1);

		// Add the screen to the panel.
		gameInformationOne.add(gameInformationOneLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(gameInformationOne, GAME_INFORMATION_PAGE_1);

		/* Game Information Page 2. */
		gameInformationTwo = new JPanel();
		gameInformationTwo.setBackground(Color.BLACK);
		gameInformationTwo.setLayout(new GridBagLayout());
		gameInformationTwo.setPreferredSize(new Dimension(width, height));

		// Game Information Page 2 Layers.
		gameInformationTwoLayeredPane = new JLayeredPane();
		gameInformationTwoLayeredPane.setBackground(Color.BLACK);
		gameInformationTwoLayeredPane.setLayout(new GridBagLayout());
		gameInformationTwoLayeredPane.setPreferredSize(
				new Dimension(width, height));

		// Game Information Page 2.
		gameInformationTwoImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenGameInfo2.png"));
		gameInformationTwoLabel = new JLabel();
		gameInformationTwoLabel.setIcon(new ImageIcon(gameInformationTwoImage
				.getImage().getScaledInstance(width, height,
						Image.SCALE_SMOOTH)));
		gameInformationTwoLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		gameInformationTwoLayeredPane.add(gameInformationTwoLabel,
				constraints);
		gameInformationTwoLayeredPane.setLayer(gameInformationTwoLabel, 0);

		// Game Information Page 2 Buttons.
		gameInformationTwoContent = new JPanel();
		gameInformationTwoContent.setLayout(new GridBagLayout());
		gameInformationTwoContent.setBackground(Color.BLACK);
		gameInformationTwoContent.setPreferredSize(
				new Dimension(width, height));
		gameInformationTwoContent.setOpaque(false);

		// Game Information Page 2 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationTowReturnButton = new OptionButton(false, 0.06 * width,
				0.06 * width, returnButtonDefault, returnButtonHover,
				returnButtonPress, e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		gameInformationTwoContent.add(gameInformationTowReturnButton,
				constraints);

		// Game Information Page 2 Previous Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationTwoPreviousButton = new OptionButton(false,
				0.05 * width, 0.05 * width, previousButtonDefault,
				previousButtonHover, previousButtonPress,
				e -> cardLayout.show(this, GAME_INFORMATION_PAGE_1));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height),
				(int) (0.05 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		gameInformationTwoContent.add(gameInformationTwoPreviousButton,
				constraints);

		// Game Information Page 2 Next Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationTwoNextButton = new OptionButton(false, 0.05 * width,
				0.05 * width, nextButtonDefault, nextButtonHover,
				nextButtonPress,
				e -> cardLayout.show(this, GAME_INFORMATION_PAGE_3));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height), 0, 0,
				(int) (0.05 * height));
		constraints.weightx = 1;
		constraints.weighty = 1;
		gameInformationTwoContent.add(gameInformationTwoNextButton,
				constraints);

		// Add the buttons to the layered pane.
		gameInformationTwoLayeredPane.add(gameInformationTwoContent,
				new GridBagConstraints());
		gameInformationTwoLayeredPane.setLayer(gameInformationTwoContent, 1);

		// Add the screen to the panel.
		gameInformationTwo.add(gameInformationTwoLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(gameInformationTwo, GAME_INFORMATION_PAGE_2);

		/* Game Information Page 3. */
		gameInformationThree = new JPanel();
		gameInformationThree.setBackground(Color.BLACK);
		gameInformationThree.setLayout(new GridBagLayout());
		gameInformationThree.setPreferredSize(new Dimension(width, height));

		// Game Information Page 3 Layers.
		gameInformationThreeLayeredPane = new JLayeredPane();
		gameInformationThreeLayeredPane.setBackground(Color.BLACK);
		gameInformationThreeLayeredPane.setLayout(new GridBagLayout());
		gameInformationThreeLayeredPane.setPreferredSize(new Dimension(width,
				height));

		// Game Information Page 3.
		gameInformationThreeImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenGameInfo3.png"));
		gameInformationThreeLabel = new JLabel();
		gameInformationThreeLabel.setIcon(new ImageIcon(
				gameInformationThreeImage.getImage()
						.getScaledInstance(width, height,
								Image.SCALE_SMOOTH)));
		gameInformationThreeLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		gameInformationThreeLayeredPane.add(gameInformationThreeLabel,
				constraints);
		gameInformationThreeLayeredPane.setLayer(gameInformationThreeLabel, 0);

		// Game Information Page 3 Buttons.
		gameInformationThreeContent = new JPanel();
		gameInformationThreeContent.setLayout(new GridBagLayout());
		gameInformationThreeContent.setBackground(Color.BLACK);
		gameInformationThreeContent.setPreferredSize(
				new Dimension(width, height));
		gameInformationThreeContent.setOpaque(false);

		// Game Information Page 3 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationThreeReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width, returnButtonDefault,
				returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		gameInformationThreeContent.add(gameInformationThreeReturnButton,
				constraints);

		// Game Information Page 3 Previous Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		gameInformationThreePreviousButton = new OptionButton(false,
				0.05 * width, 0.05 * width, previousButtonDefault,
				previousButtonHover, previousButtonPress,
				e -> cardLayout.show(this, GAME_INFORMATION_PAGE_2));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height),
				(int) (0.05 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		gameInformationThreeContent.add(gameInformationThreePreviousButton,
				constraints);

		// Add the buttons to the layered pane.
		gameInformationThreeLayeredPane.add(gameInformationThreeContent,
				new GridBagConstraints());
		gameInformationThreeLayeredPane.setLayer(gameInformationThreeContent,
				1);

		// Add the screen to the panel.
		gameInformationThree.add(gameInformationThreeLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(gameInformationThree, GAME_INFORMATION_PAGE_3);

		/* Points Breakdown Page. */
		pointsBreakdown = new JPanel();
		pointsBreakdown.setBackground(Color.BLACK);
		pointsBreakdown.setLayout(new GridBagLayout());
		pointsBreakdown.setPreferredSize(new Dimension(width, height));

		// Points Breakdown Page Layers.
		pointsBreakdownLayeredPane = new JLayeredPane();
		pointsBreakdownLayeredPane.setBackground(Color.BLACK);
		pointsBreakdownLayeredPane.setLayout(new GridBagLayout());
		pointsBreakdownLayeredPane.setPreferredSize(
				new Dimension(width, height));

		// Points Breakdown Page.
		pointsBreakdownImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenPointsBreakdown.png"));
		pointsBreakdownLabel = new JLabel();
		pointsBreakdownLabel.setIcon(new ImageIcon(pointsBreakdownImage
				.getImage().getScaledInstance(width, height,
						Image.SCALE_SMOOTH)));
		pointsBreakdownLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		pointsBreakdownLayeredPane.add(pointsBreakdownLabel, constraints);
		pointsBreakdownLayeredPane.setLayer(pointsBreakdownLabel, 0);

		// Points Breakdown Page Buttons.
		pointsBreakdownContent = new JPanel();
		pointsBreakdownContent.setLayout(new GridBagLayout());
		pointsBreakdownContent.setBackground(Color.BLACK);
		pointsBreakdownContent.setPreferredSize(new Dimension(width, height));
		pointsBreakdownContent.setOpaque(false);

		// Points Breakdown Page Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		pointsBreakdownReturnButton = new OptionButton(false, 0.06 * width,
				0.06 * width, returnButtonDefault, returnButtonHover,
				returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		pointsBreakdownContent.add(pointsBreakdownReturnButton, constraints);

		// Add buttons to the layered pane.
		pointsBreakdownLayeredPane.add(pointsBreakdownContent,
				new GridBagConstraints());
		pointsBreakdownLayeredPane.setLayer(pointsBreakdownContent, 1);

		// Add the screen to the panel.
		pointsBreakdown.add(pointsBreakdownLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(pointsBreakdown, POINTS_BREAKDOWN_PAGE);

		/* Settings Information Page 1. */
		settingsInformationOne = new JPanel();
		settingsInformationOne.setBackground(Color.BLACK);
		settingsInformationOne.setLayout(new GridBagLayout());
		settingsInformationOne.setPreferredSize(new Dimension(width, height));

		// Settings Information Page 1 Layers.
		settingsInformationOneLayeredPane = new JLayeredPane();
		settingsInformationOneLayeredPane.setBackground(Color.BLACK);
		settingsInformationOneLayeredPane.setLayout(new GridBagLayout());
		settingsInformationOneLayeredPane.setPreferredSize(
				new Dimension(width, height));

		// Settings Information Page 1.
		settingsInformationOneImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo1.png"));
		settingsInformationOneLabel = new JLabel();
		settingsInformationOneLabel.setIcon(
				new ImageIcon(settingsInformationOneImage.getImage().
						getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		settingsInformationOneLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		settingsInformationOneLayeredPane.add(settingsInformationOneLabel,
				constraints);
		settingsInformationOneLayeredPane.setLayer(settingsInformationOneLabel,
				0);

		// Settings Information Page 1 Buttons.
		settingsInformationOneContent = new JPanel();
		settingsInformationOneContent.setLayout(new GridBagLayout());
		settingsInformationOneContent.setBackground(Color.BLACK);
		settingsInformationOneContent.setPreferredSize(new Dimension(width,
				height));
		settingsInformationOneContent.setOpaque(false);

		// Settings Information Page 1 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationOneReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationOneContent.add(settingsInformationOneReturnButton,
				constraints);

		// Settings Information Page 1 Next Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationOneNextButton = new OptionButton(false,
				0.05 * width, 0.05 * width,
				nextButtonDefault, nextButtonHover, nextButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_2));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height), 0, 0,
				(int) (0.05 * height));
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationOneContent.add(settingsInformationOneNextButton,
				constraints);

		// Add buttons to the layered pane.
		settingsInformationOneLayeredPane.add(settingsInformationOneContent,
				new GridBagConstraints());
		settingsInformationOneLayeredPane.setLayer(
				settingsInformationOneContent, 1);

		// Add the screen to the panel.
		settingsInformationOne.add(settingsInformationOneLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(settingsInformationOne, SETTINGS_INFORMATION_PAGE_1);

		/* Settings Information Page 2. */
		settingsInformationTwo = new JPanel();
		settingsInformationTwo.setBackground(Color.BLACK);
		settingsInformationTwo.setLayout(new GridBagLayout());
		settingsInformationTwo.setPreferredSize(new Dimension(width, height));

		// Settings Information Page 2 Layers.
		settingsInformationTwoLayeredPane = new JLayeredPane();
		settingsInformationTwoLayeredPane.setBackground(Color.BLACK);
		settingsInformationTwoLayeredPane.setLayout(new GridBagLayout());
		settingsInformationTwoLayeredPane.setPreferredSize(
				new Dimension(width, height));

		// Settings Information Page 2.
		settingsInformationTwoImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo2.png"));
		settingsInformationTwoLabel = new JLabel();
		settingsInformationTwoLabel.setIcon(new ImageIcon(
				settingsInformationTwoImage.getImage()
						.getScaledInstance(width, height,
								Image.SCALE_SMOOTH)));
		settingsInformationTwoLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		settingsInformationTwoLayeredPane.add(settingsInformationTwoLabel,
				constraints);
		settingsInformationTwoLayeredPane.setLayer(settingsInformationTwoLabel,
				0);

		// Settings Information Page 2 Button.
		settingsInformationTwoContent = new JPanel();
		settingsInformationTwoContent.setLayout(new GridBagLayout());
		settingsInformationTwoContent.setBackground(Color.BLACK);
		settingsInformationTwoContent.setPreferredSize(
				new Dimension(width, height));
		settingsInformationTwoContent.setOpaque(false);

		// Settings Information Page 2 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationTwoReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		settingsInformationTwoContent.add(settingsInformationTwoReturnButton,
				constraints);

		// Settings Information Page 2 Previous Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationTwoPreviousButton = new OptionButton(false,
				0.05 * width, 0.05 * width,
				previousButtonDefault, previousButtonHover, previousButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_1));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height),
				(int) (0.05 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationTwoContent.add(settingsInformationTwoPreviousButton,
				constraints);

		// Settings Information Page 2 Next Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationTwoNextButton = new OptionButton(false,
				0.05 * width, 0.05 * width,
				nextButtonDefault, nextButtonHover, nextButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_3));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height), 0, 0,
				(int) (0.05 * height));
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationTwoContent.add(settingsInformationTwoNextButton,
				constraints);

		// Add the buttons to the layered pane.
		settingsInformationTwoLayeredPane.add(settingsInformationTwoContent,
				new GridBagConstraints());
		settingsInformationTwoLayeredPane.setLayer(
				settingsInformationTwoContent, 1);

		// Add the screen to the panel.
		settingsInformationTwo.add(settingsInformationTwoLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(settingsInformationTwo, SETTINGS_INFORMATION_PAGE_2);

		/* Settings Information Page 3 */
		settingsInformationThree = new JPanel();
		settingsInformationThree.setBackground(Color.BLACK);
		settingsInformationThree.setLayout(new GridBagLayout());
		settingsInformationThree.setPreferredSize(
				new Dimension(width, height));

		// Settings Information Page 3 Layers.
		settingsInformationThreeLayer = new JLayeredPane();
		settingsInformationThreeLayer.setBackground(Color.BLACK);
		settingsInformationThreeLayer.setLayout(new GridBagLayout());
		settingsInformationThreeLayer.setPreferredSize(
				new Dimension(width, height));

		// Settings Information Page 3.
		settingsInformationThreeImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo3.png"));
		settingsInformationThreeLabel = new JLabel();
		settingsInformationThreeLabel.setIcon(new ImageIcon(
				settingsInformationThreeImage.getImage()
						.getScaledInstance(width, height,
								Image.SCALE_SMOOTH)));
		settingsInformationThreeLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		settingsInformationThreeLayer.add(settingsInformationThreeLabel,
				constraints);
		settingsInformationThreeLayer.setLayer(settingsInformationThreeLabel,
				0);

		// Settings Information Page 3 Buttons.
		settingsInformationThreeContent = new JPanel();
		settingsInformationThreeContent.setLayout(new GridBagLayout());
		settingsInformationThreeContent.setBackground(Color.BLACK);
		settingsInformationThreeContent.setPreferredSize(
				new Dimension(width, height));
		settingsInformationThreeContent.setOpaque(false);

		// Settings Information Page 3 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationThreeReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		settingsInformationThreeContent.add(
				settingsInformationThreeReturnButton, constraints);

		// Settings Information Page 3 Previous Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationThreePreviousButton = new OptionButton(false,
				0.05 * width, 0.05 * width,
				previousButtonDefault, previousButtonHover,
				previousButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_2));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height),
				(int) (0.05 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationThreeContent.add(
				settingsInformationThreePreviousButton, constraints);

		// Settings Information Page 3 Next Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationThreeNextButton = new OptionButton(false,
				0.05 * width, 0.05 * width,
				nextButtonDefault, nextButtonHover, nextButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_4));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_END;
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height), 0, 0,
				(int) (0.05 * height));
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationThreeContent.add(settingsInformationThreeNextButton,
				constraints);

		// Add the buttons to the layered pane.
		settingsInformationThreeLayer.add(settingsInformationThreeContent,
				new GridBagConstraints());
		settingsInformationThreeLayer.setLayer(settingsInformationThreeContent,
				1);

		// Add the screen to the panel.
		settingsInformationThree.add(settingsInformationThreeLayer,
				new GridBagConstraints());

		// Add the buttons to the main panel.
		add(settingsInformationThree, SETTINGS_INFORMATION_PAGE_3);

		/* Settings Information Page 4. */
		settingsInformationFour = new JPanel();
		settingsInformationFour.setBackground(Color.BLACK);
		settingsInformationFour.setLayout(new GridBagLayout());
		settingsInformationFour.setPreferredSize(new Dimension(width, height));

		// Settings Information Page 4 Layers.
		settingsInformationFourLayeredPane = new JLayeredPane();
		settingsInformationFourLayeredPane.setBackground(Color.BLACK);
		settingsInformationFourLayeredPane.setLayout(new GridBagLayout());
		settingsInformationFourLayeredPane.setPreferredSize(
				new Dimension(width, height));

		// Settings Information Page 4.
		settingsInformationFourImage = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo4.png"));
		settingsInformationFourLabel = new JLabel();
		settingsInformationFourLabel.setIcon(new ImageIcon(
				settingsInformationFourImage.getImage()
						.getScaledInstance(width, height,
								Image.SCALE_SMOOTH)));
		settingsInformationFourLabel.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		settingsInformationFourLayeredPane.add(settingsInformationFourLabel,
				constraints);
		settingsInformationFourLayeredPane.setLayer(
				settingsInformationFourLabel, 0);

		// Settings Information Page 4 Buttons.
		settingsInformationFourContent = new JPanel();
		settingsInformationFourContent.setLayout(new GridBagLayout());
		settingsInformationFourContent.setBackground(Color.BLACK);
		settingsInformationFourContent.setPreferredSize(
				new Dimension(width, height));
		settingsInformationFourContent.setOpaque(false);

		// Settings Information Page 4 Return Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationFourReturnButton = new OptionButton(false,
				0.06 * width, 0.06 * width,
				returnButtonDefault, returnButtonHover, returnButtonPress,
				e -> cardLayout.show(this, MENU_PAGE));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.04 * height),
				(int) (0.04 * height), 0, 0);
		settingsInformationFourContent.add(settingsInformationFourReturnButton,
				constraints);

		// Settings Information Page 4 Previous Button.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		settingsInformationFourPreviousBtn = new OptionButton(false,
				0.05 * width, 0.05 * width,
				previousButtonDefault, previousButtonHover,
				previousButtonPress,
				e -> cardLayout.show(this, SETTINGS_INFORMATION_PAGE_3));
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.FIRST_LINE_START;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.insets = new Insets((int) (0.22 * height),
				(int) (0.05 * height), 0, 0);
		constraints.weightx = 1;
		constraints.weighty = 1;
		settingsInformationFourContent.add(settingsInformationFourPreviousBtn,
				constraints);

		// Add the buttons to the layered pane.
		settingsInformationFourLayeredPane.add(settingsInformationFourContent,
				new GridBagConstraints());
		settingsInformationFourLayeredPane.setLayer(
				settingsInformationFourContent, 1);

		// Add the screen to the panel.
		settingsInformationFour.add(settingsInformationFourLayeredPane,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(settingsInformationFour, SETTINGS_INFORMATION_PAGE_4);
	}
}