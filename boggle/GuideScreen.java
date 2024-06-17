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
	private CardLayout cl;

	// Default icon for return button.
	private ImageIcon returnDefault;
	// Hover icon for return button.
	private ImageIcon returnHover;
	// Press icon for return button.
	private ImageIcon returnPress;

	// Default button for previous button.
	private ImageIcon prevDefault;
	// Hover button for previous button.
	private ImageIcon prevHover;
	// Press button for previous button.
	private ImageIcon prevPress;

	// Default button for next button.
	private ImageIcon nextDefault;
	// Hover button for next button.
	private ImageIcon nextHover;
	// Press button for next button.
	private ImageIcon nextPress;

	// Panel for the main menu.
	private JPanel menu;
	// ID for menu page
	private final String MENU_PAGE = "Menu Page";
	// Layered pane for the main menu.
	private JLayeredPane menuLayer;
	// Menu background image.
	private ImageIcon menuBgImg;
	// Music background image.
	private JLabel menuBg;
	// Holds menu buttons.
	private JPanel menuContent;
	// Button to return to menu.
	private OptionButton menuReturnBtn;

	// Button to game Information screen
	private OptionButton gameInformationBtn;
	// Default button for gameInformation button.
	private ImageIcon gameInformationDefault;
	// Hover button for gameInformation button.
	private ImageIcon gameInformationHover;
	// Press button for gameInformation button.
	private ImageIcon gameInformationPress;

	// Button to points breakdown screen
	private OptionButton pointsBreakdownBtn;
	// Default button for points breakdown button.
	private ImageIcon pointsBreakdownDefault;
	// Hover button for points breakdown button.
	private ImageIcon pointsBreakdownHover;
	// Press button for points breakdown button.
	private ImageIcon pointsBreakdownPress;

	// Button to settings info screen
	private OptionButton settingsInformationBtn;
	// Default button for settings Information button.
	private ImageIcon settingsInformationDefault;
	// Hover button for settings Information button.
	private ImageIcon settingsInformationHover;
	// Press button for settings Information button.
	private ImageIcon settingsInformationPress;

	// Panel for the first page of game info
	private JPanel gameInformation1;
	// ID for game info page 1.
	private final String GAME_Information_PAGE_1 = "Game Info Page 1";
	// Layered pane for game info page 1.
	private JLayeredPane gameInformation1Layer;
	// Image for the game info page 1.
	private ImageIcon gameInformation1Img;
	// Label to hold the game info page 1 display.
	private JLabel gameInformation1Pg;
	// Holds game info page 1 buttons.
	private JPanel gameInformation1Content;
	// Button for game info page 1 return.
	private OptionButton gameInformation1ReturnBtn;
	// Button for game info page 1 next.
	private OptionButton gameInformation1NextBtn;

	// Panel for the second page of game info
	private JPanel gameInformation2;
	// ID for game info page 2
	private String GAME_Information_PAGE_2 = "Game Information Page 2";
	// Layered pane for game info page 2
	private JLayeredPane gameInformation2Layer;
	// Image for game info page 2
	private ImageIcon gameInformation2Img;
	// Label to hold the game info page 2 display
	private JLabel gameInformation2Pg;
	// Holds game info page 2 buttons
	private JPanel gameInformation2Content;
	// Button for game info page 2 return
	private OptionButton gameInformation2ReturnBtn;
	// Button for game info page 2 previous
	private OptionButton gameInformation2PrevBtn;
	// Button for game info page 2 next
	private OptionButton gameInformation2NextBtn;

	// Panel for the third page of game info
	private JPanel gameInformation3;
	// ID for game info page 3.
	private String GAME_Information_PAGE_3 = "Game Information Page 3";
	// Layered pane for game info page 3.
	private JLayeredPane gameInformation3Layer;
	// Image for game info page 3.
	private ImageIcon gameInformation3Img;
	// Label to hold the game info page 3 display.
	private JLabel gameInformation3Pg;
	// Holds game info page 3 buttons.
	private JPanel gameInformation3Content;
	// Button for game info page 3 return.
	private OptionButton gameInformation3ReturnBtn;
	// Button for game info page 3 previous.
	private OptionButton gameInformation3PrevBtn;

	// Panel for the points breakdown screen.
	private JPanel pointsBreakdown;
	// ID for points breakdown.
	private String POINTS_BREAKDOWN_PAGE = "Points Breakdown Page";
	// Layered pane points breakdown.
	private JLayeredPane pointsBreakdownLayer;
	// Image for points breakdown.
	private ImageIcon pointsBreakdownImg;
	// Label to hold points breakdown display.
	private JLabel pointsBreakdownPg;
	// Holds points breakdown buttons.
	private JPanel pointsBreakdownContent;
	// Button for points breakdown return.
	private OptionButton pointsBreakdownReturnBtn;

	// Panel for the settings info page 1.
	private JPanel settingsInformation1;
	// ID for settings info page 1.
	private String SETTINGS_INFORMATION_PAGE_1 = "Settings Information Page 1";
	// Layered pane setting info page 1.
	private JLayeredPane settingsInformation1Layer;
	// Image for settings info page 1.
	private ImageIcon settingsInformation1Img;
	// Label to hold settings info page 1 display.
	private JLabel settingsInformation1Pg;
	// Holds points settings info page 1 display.
	private JPanel settingsInformation1Content;
	// Button for settings info page 1 return,
	private OptionButton settingsInformation1ReturnBtn;
	// Button for settings info page 1 next.
	private OptionButton settingsInformation1NextBtn;

	// Panel for the settings information page 2.
	private JPanel settingsInformation2;
	// ID for settings information page 2.
	private String SETTINGS_INFORMATION_PAGE_2 = "Settings Information Page 2";
	// Layered pane for settings information page 2.
	private JLayeredPane settingsInformation2Layer;
	// Image for settings information page 2.
	private ImageIcon settingsInformation2Img;
	// Label to hold settings information page 2 display.
	private JLabel settingsInformation2Pg;
	// Holds settings information page 2 buttons.
	private JPanel settingsInformation2Content;
	// Button for settings information page 2 return.
	private OptionButton settingsInformation2ReturnBtn;
	// Button for settings information page 2 previous.
	private OptionButton settingsInformation2PrevBtn;
	// Button for settings information page 2 next.
	private OptionButton settingsInformation2NextBtn;

	// Panel for the settings information page 3.
	private JPanel settingsInformation3;
	// ID for settings information page 3.
	private String SETTINGS_INFORMATION_PAGE_3 = "Settings Information Page 3";
	// Layered pane for settings information page 3
	private JLayeredPane settingsInformation3Layer;
	// Image for settings information page 3.
	private ImageIcon settingsInformation3Img;
	// Label to hold settings information page 3 display.
	private JLabel settingsInformation3Pg;
	// Holds settings information page 3 buttons.
	private JPanel settingsInformation3Content;
	// Button for settings information page 3 return.
	private OptionButton settingsInformation3ReturnBtn;
	// Button for settings information page 3 previous.
	private OptionButton settingsInformation3PrevBtn;
	// Button for settings information page 3 next.
	private OptionButton settingsInformation3NextBtn;

	// Panel for the settings information page 4.
	private JPanel settingsInformation4 = new JPanel();
	// ID for settings information page 4.
	private String SETTINGS_INFORMATION_PAGE_4 = "Settings Information Page 4";
	// Layered pane for settings information page 4.
	private JLayeredPane settingsInformation4Layer;
	// Image for settings information page 4.
	private ImageIcon settingsInformation4Img;
	// Label to hold settings information page 4 display.
	private JLabel settingsInformation4Pg;
	// Holds settings information page 4 buttons.
	private JPanel settingsInformation4Content;
	// Button for settings information page 4 return.
	private OptionButton settingsInformation4ReturnBtn;
	// Button for settings information page 4 previous.
	private OptionButton settingsInformation4PrevBtn;

	// Main frame for game.
	private Boggle mainFrame;

	/**
	 * Constructor
	 * @param mainFrame main frame for game
	 */
	public GuideScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		// Get the width and height of the frame
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		// Set frame layout to Card Layout
		this.cl = new CardLayout();

		// Return Button Icons.
		returnDefault = new ImageIcon(
				getClass().getResource("assets/ReturnBtnDefault.png"));
		returnHover = new ImageIcon(
				getClass().getResource("assets/ReturnBtnHover.png"));
		returnPress = new ImageIcon(
				getClass().getResource("assets/ReturnBtnPress.png"));

		// Previous Button Icons.
		prevDefault = new ImageIcon(
				getClass().getResource("assets/PreviousBtnDefault.png"));
		prevHover = new ImageIcon(
				getClass().getResource("assets/PreviousBtnHover.png"));
		prevPress = new ImageIcon(
				getClass().getResource("assets/PreviousBtnPress.png"));

		// Next Button Icons.
		nextDefault = new ImageIcon(
				getClass().getResource("assets/NextBtnDefault.png"));
		nextHover = new ImageIcon(
				getClass().getResource("assets/NextBtnHover.png"));
		nextPress = new ImageIcon(
				getClass().getResource("assets/NextBtnPress.png"));

		// Set background colour to black
		this.setBackground(Color.black);
		this.setLayout(cl);
		// Set dimensions of the screen
		this.setPreferredSize(new Dimension(w, h));


		GridBagConstraints c;

		/* Menu Page. */
		menu = new JPanel();
		menu.setBackground(Color.black);
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(w, h));

		// Menu Layered.
		menuLayer = new JLayeredPane();
		menuLayer.setBackground(Color.black);
		menuLayer.setLayout(new GridBagLayout());
		menuLayer.setPreferredSize(new Dimension(w, h));

		// Menu Background.
		menuBgImg = new ImageIcon(
				getClass().getResource("assets/GuideScreenMenuBg.png"));
		menuBg = new JLabel();
		menuBg.setIcon(new ImageIcon(menuBgImg.getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		menuBg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		menuLayer.add(menuBg, c);
		menuLayer.setLayer(menuBg, 0);

		// Menu Buttons.
		menuContent = new JPanel();
		menuContent.setLayout(new GridBagLayout());
		menuContent.setBackground(Color.black);
		menuContent.setPreferredSize(new Dimension(w, h));
		menuContent.setOpaque(false);

		// Menu Return Button.
		menuReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> mainFrame.menuScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(
				(int) (0.04 * h), (int) (0.04 * h), 0,0);
		menuContent.add(menuReturnBtn, c);

		// Game Information Menu Button.
		gameInformationDefault = new ImageIcon(
				getClass().getResource("assets/GameInfoBtnDefault.png"));
		gameInformationHover = new ImageIcon(
				getClass().getResource("assets/GameInfoBtnHover.png"));
		gameInformationPress = new ImageIcon(
				getClass().getResource("assets/GameInfoBtnPress.png"));
		gameInformationBtn = new OptionButton(0.5 * w, 0.15 * h,
				gameInformationDefault, gameInformationHover,
				gameInformationPress,
				e -> cl.show(this, GAME_Information_PAGE_1));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.35 * h), 0, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(gameInformationBtn, c);

		// Points Breakdown Menu Button.
		pointsBreakdownDefault = new ImageIcon(getClass()
				.getResource("assets/PointsBreakdownBtnDefault.png"));
		pointsBreakdownHover = new ImageIcon(getClass()
				.getResource("assets/PointsBreakdownBtnHover.png"));
		pointsBreakdownPress = new ImageIcon(getClass()
				.getResource("assets/PointsBreakdownBtnPress.png"));
		pointsBreakdownBtn = new OptionButton(0.5 * w, 0.15 * h,
				pointsBreakdownDefault, pointsBreakdownHover,
				pointsBreakdownPress,
				e -> cl.show(this, POINTS_BREAKDOWN_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(pointsBreakdownBtn, c);

		// Settings Information Menu Button.
		settingsInformationDefault = new ImageIcon(getClass()
				.getResource("assets/SettingsInfoBtnDefault.png"));
		settingsInformationHover = new ImageIcon(getClass()
				.getResource("assets/SettingsInfoBtnHover.png"));
		settingsInformationPress = new ImageIcon(getClass()
				.getResource("assets/SettingsInfoBtnPress.png"));
		settingsInformationBtn = new OptionButton(0.5 * w, 0.15 * h,
				settingsInformationDefault, settingsInformationHover,
				settingsInformationPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_1));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, (int) (0.05 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(settingsInformationBtn, c);

		// Adding buttons to the layered pane.
		menuLayer.add(menuContent, new GridBagConstraints());
		menuLayer.setLayer(menuContent, 1);

		// Add the screen to the panel.
		menu.add(menuLayer, new GridBagConstraints());

		// Add the page to the main panel.
		add(menu, MENU_PAGE);
		// Display the menu page as the initial screen.
		cl.show(this, MENU_PAGE);

		/* Game Information Page 1. */
		gameInformation1 = new JPanel();
		gameInformation1.setBackground(Color.black);
		gameInformation1.setLayout(new GridBagLayout());
		gameInformation1.setPreferredSize(new Dimension(w, h));

		// Game Information Page 1 Layers.
		gameInformation1Layer = new JLayeredPane();
		gameInformation1Layer.setBackground(Color.black);
		gameInformation1Layer.setLayout(new GridBagLayout());
		gameInformation1Layer.setPreferredSize(new Dimension(w, h));

		// Game Information 1.
		gameInformation1Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenGameInfo1.png"));
		gameInformation1Pg = new JLabel();
		gameInformation1Pg.setIcon(new ImageIcon(gameInformation1Img.getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		gameInformation1Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		gameInformation1Layer.add(gameInformation1Pg, c);
		gameInformation1Layer.setLayer(gameInformation1Pg, 0);

		// Game Information 1 Buttons.
		gameInformation1Content = new JPanel();
		gameInformation1Content.setLayout(new GridBagLayout());
		gameInformation1Content.setBackground(Color.black);
		gameInformation1Content.setPreferredSize(new Dimension(w, h));
		gameInformation1Content.setOpaque(false);

		// Game Information 1 Return Button.
		gameInformation1ReturnBtn = new OptionButton(0.06 * w,
				0.06 * w, returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		gameInformation1Content.add(gameInformation1ReturnBtn, c);

		// Game Information 1 Next Button.
		gameInformation1NextBtn = new OptionButton(0.05 * w, 0.05 * w,
				nextDefault, nextHover, nextPress,
				e -> cl.show(this, GAME_Information_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		gameInformation1Content.add(gameInformation1NextBtn, c);

		// Add the buttons to the layered pane.
		gameInformation1Layer.add(gameInformation1Content,
				new GridBagConstraints());
		gameInformation1Layer.setLayer(gameInformation1Content, 1);

		// Add the screen to the panel.
		gameInformation1.add(gameInformation1Layer, new GridBagConstraints());

		// Add the page to the main panel.
		add(gameInformation1, GAME_Information_PAGE_1);

		/* Game Information Page 2. */
		gameInformation2 = new JPanel();
		gameInformation2.setBackground(Color.black);
		gameInformation2.setLayout(new GridBagLayout());
		gameInformation2.setPreferredSize(new Dimension(w, h));

		// Game Information Page 2 Layers.
		gameInformation2Layer = new JLayeredPane();
		gameInformation2Layer.setBackground(Color.black);
		gameInformation2Layer.setLayout(new GridBagLayout());
		gameInformation2Layer.setPreferredSize(new Dimension(w, h));

		// Game Information Page 2.
		gameInformation2Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenGameInfo2.png"));
		gameInformation2Pg = new JLabel();
		gameInformation2Pg.setIcon(new ImageIcon(gameInformation2Img.getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		gameInformation2Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		gameInformation2Layer.add(gameInformation2Pg, c);
		gameInformation2Layer.setLayer(gameInformation2Pg, 0);

		// Game Information Page 2 Buttons.
		gameInformation2Content = new JPanel();
		gameInformation2Content.setLayout(new GridBagLayout());
		gameInformation2Content.setBackground(Color.black);
		gameInformation2Content.setPreferredSize(new Dimension(w, h));
		gameInformation2Content.setOpaque(false);

		// Game Information Page 2 Return Button.
		gameInformation2ReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		gameInformation2Content.add(gameInformation2ReturnBtn, c);

		// Game Information Page 2 Previous Button.
		gameInformation2PrevBtn = new OptionButton(0.05 * w, 0.05 * w,
				prevDefault, prevHover, prevPress,
				e -> cl.show(this, GAME_Information_PAGE_1));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		gameInformation2Content.add(gameInformation2PrevBtn, c);

		// Game Information Page 2 Next Button.
		gameInformation2NextBtn = new OptionButton(0.05 * w, 0.05 * w,
				nextDefault, nextHover, nextPress,
				e -> cl.show(this, GAME_Information_PAGE_3));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		gameInformation2Content.add(gameInformation2NextBtn, c);

		// Add the buttons to the layered pane.
		gameInformation2Layer.add(gameInformation2Content,
				new GridBagConstraints());
		gameInformation2Layer.setLayer(gameInformation2Content, 1);

		// Add the screen to the panel.
		gameInformation2.add(gameInformation2Layer, new GridBagConstraints());

		// Add the page to the main panel.
		add(gameInformation2, GAME_Information_PAGE_2);

		/* Game Information Page 3. */
		gameInformation3 = new JPanel();
		gameInformation3.setBackground(Color.black);
		gameInformation3.setLayout(new GridBagLayout());
		gameInformation3.setPreferredSize(new Dimension(w, h));

		// Game Information Page 3 Layers.
		gameInformation3Layer = new JLayeredPane();
		gameInformation3Layer.setBackground(Color.black);
		gameInformation3Layer.setLayout(new GridBagLayout());
		gameInformation3Layer.setPreferredSize(new Dimension(w, h));

		// Game Information Page 3.
		gameInformation3Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenGameInfo3.png"));
		gameInformation3Pg = new JLabel();
		gameInformation3Pg.setIcon(new ImageIcon(gameInformation3Img.getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		gameInformation3Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		gameInformation3Layer.add(gameInformation3Pg, c);
		gameInformation3Layer.setLayer(gameInformation3Pg, 0);

		// Game Information Page 3 Buttons.
		gameInformation3Content = new JPanel();
		gameInformation3Content.setLayout(new GridBagLayout());
		gameInformation3Content.setBackground(Color.black);
		gameInformation3Content.setPreferredSize(new Dimension(w, h));
		gameInformation3Content.setOpaque(false);

		// Game Information Page 3 Return Button.
		gameInformation3ReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		gameInformation3Content.add(gameInformation3ReturnBtn, c);

		// Game Information Page 3 Previous Button.
		gameInformation3PrevBtn = new OptionButton(0.05 * w, 0.05 * w,
				prevDefault, prevHover, prevPress,
				e -> cl.show(this, GAME_Information_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		gameInformation3Content.add(gameInformation3PrevBtn, c);

		// Add the buttons to the layered pane.
		gameInformation3Layer.add(gameInformation3Content,
				new GridBagConstraints());
		gameInformation3Layer.setLayer(gameInformation3Content, 1);

		// Add the screen to the panel.
		gameInformation3.add(gameInformation3Layer, new GridBagConstraints());

		// Add the page to the main panel.
		add(gameInformation3, GAME_Information_PAGE_3);

		/* Points Breakdown Page. */
		pointsBreakdown = new JPanel();
		pointsBreakdown.setBackground(Color.black);
		pointsBreakdown.setLayout(new GridBagLayout());
		pointsBreakdown.setPreferredSize(new Dimension(w, h));

		// Points Breakdown Page Layers.
		pointsBreakdownLayer = new JLayeredPane();
		pointsBreakdownLayer.setBackground(Color.black);
		pointsBreakdownLayer.setLayout(new GridBagLayout());
		pointsBreakdownLayer.setPreferredSize(new Dimension(w, h));

		// Points Breakdown Page.
		pointsBreakdownImg = new ImageIcon(getClass()
				.getResource("assets/GuideScreenPointsBreakdown.png"));
		pointsBreakdownPg = new JLabel();
		pointsBreakdownPg.setIcon(new ImageIcon(pointsBreakdownImg.getImage()
				.getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		pointsBreakdownPg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		pointsBreakdownLayer.add(pointsBreakdownPg, c);
		pointsBreakdownLayer.setLayer(pointsBreakdownPg, 0);

		// Points Breakdown Page Buttons.
		pointsBreakdownContent = new JPanel();
		pointsBreakdownContent.setLayout(new GridBagLayout());
		pointsBreakdownContent.setBackground(Color.black);
		pointsBreakdownContent.setPreferredSize(new Dimension(w, h));
		pointsBreakdownContent.setOpaque(false);

		// Points Breakdown Page Return Button.
		pointsBreakdownReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		pointsBreakdownContent.add(pointsBreakdownReturnBtn, c);

		// Add buttons to the layered pane.
		pointsBreakdownLayer.add(pointsBreakdownContent,
				new GridBagConstraints());
		pointsBreakdownLayer.setLayer(pointsBreakdownContent, 1);

		// Add the screen to the panel.
		pointsBreakdown.add(pointsBreakdownLayer, new GridBagConstraints());

		// Add the page to the main panel.
		add(pointsBreakdown, POINTS_BREAKDOWN_PAGE);

		/* Settings Information Page 1. */
		settingsInformation1 = new JPanel();
		settingsInformation1.setBackground(Color.black);
		settingsInformation1.setLayout(new GridBagLayout());
		settingsInformation1.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 1 Layers.
		settingsInformation1Layer = new JLayeredPane();
		settingsInformation1Layer.setBackground(Color.black);
		settingsInformation1Layer.setLayout(new GridBagLayout());
		settingsInformation1Layer.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 1.
		settingsInformation1Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo1.png"));
		settingsInformation1Pg = new JLabel();
		settingsInformation1Pg.setIcon(new ImageIcon(settingsInformation1Img
				.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInformation1Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInformation1Layer.add(settingsInformation1Pg, c);
		settingsInformation1Layer.setLayer(settingsInformation1Pg, 0);

		// Settings Information Page 1 Buttons.
		settingsInformation1Content = new JPanel();
		settingsInformation1Content.setLayout(new GridBagLayout());
		settingsInformation1Content.setBackground(Color.black);
		settingsInformation1Content.setPreferredSize(new Dimension(w, h));
		settingsInformation1Content.setOpaque(false);

		// Settings Information Page 1 Return Button.
		settingsInformation1ReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation1Content.add(settingsInformation1ReturnBtn, c);

		// Settings Information Page 1 Next Button.
		settingsInformation1NextBtn = new OptionButton(0.05 * w, 0.05 * w,
				nextDefault, nextHover, nextPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation1Content.add(settingsInformation1NextBtn, c);

		// Add buttons to the layered pane.
		settingsInformation1Layer.add(settingsInformation1Content,
				new GridBagConstraints());
		settingsInformation1Layer.setLayer(settingsInformation1Content, 1);

		// Add the screen to the panel.
		settingsInformation1.add(settingsInformation1Layer,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(settingsInformation1, SETTINGS_INFORMATION_PAGE_1);

		/* Settings Information Page 2. */
		settingsInformation2 = new JPanel();
		settingsInformation2.setBackground(Color.black);
		settingsInformation2.setLayout(new GridBagLayout());
		settingsInformation2.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 2 Layers.
		settingsInformation2Layer = new JLayeredPane();
		settingsInformation2Layer.setBackground(Color.black);
		settingsInformation2Layer.setLayout(new GridBagLayout());
		settingsInformation2Layer.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 2.
		settingsInformation2Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo2.png"));
		settingsInformation2Pg = new JLabel();
		settingsInformation2Pg.setIcon(new ImageIcon(settingsInformation2Img
				.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInformation2Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInformation2Layer.add(settingsInformation2Pg, c);
		settingsInformation2Layer.setLayer(settingsInformation2Pg, 0);

		// Settings Information Page 2 Button.
		settingsInformation2Content = new JPanel();
		settingsInformation2Content.setLayout(new GridBagLayout());
		settingsInformation2Content.setBackground(Color.black);
		settingsInformation2Content.setPreferredSize(new Dimension(w, h));
		settingsInformation2Content.setOpaque(false);

		// Settings Information Page 2 Return Button.
		settingsInformation2ReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress, 
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		settingsInformation2Content.add(settingsInformation2ReturnBtn, c);

		// Settings Information Page 2 Previous Button.
		settingsInformation2PrevBtn = new OptionButton(0.05 * w, 0.05 * w,
				prevDefault, prevHover, prevPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_1));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation2Content.add(settingsInformation2PrevBtn, c);

		// Settings Information Page 2 Next Button.
		settingsInformation2NextBtn = new OptionButton(0.05 * w, 0.05 * w,
				nextDefault, nextHover, nextPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_3));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation2Content.add(settingsInformation2NextBtn, c);

		// Add the buttons to the layered pane.
		settingsInformation2Layer.add(settingsInformation2Content,
				new GridBagConstraints());
		settingsInformation2Layer.setLayer(settingsInformation2Content, 1);

		// Add the screen to the panel.
		settingsInformation2.add(settingsInformation2Layer,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(settingsInformation2, SETTINGS_INFORMATION_PAGE_2);

		/* Settings Information Page 3 */
		settingsInformation3 = new JPanel();
		settingsInformation3.setBackground(Color.black);
		settingsInformation3.setLayout(new GridBagLayout());
		settingsInformation3.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 3 Layers.
		settingsInformation3Layer = new JLayeredPane();
		settingsInformation3Layer.setBackground(Color.black);
		settingsInformation3Layer.setLayout(new GridBagLayout());
		settingsInformation3Layer.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 3.
		settingsInformation3Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo3.png"));
		settingsInformation3Pg = new JLabel();
		settingsInformation3Pg.setIcon(new ImageIcon(settingsInformation3Img
				.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInformation3Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInformation3Layer.add(settingsInformation3Pg, c);
		settingsInformation3Layer.setLayer(settingsInformation3Pg, 0);

		// Settings Information Page 3 Buttons.
		settingsInformation3Content = new JPanel();
		settingsInformation3Content.setLayout(new GridBagLayout());
		settingsInformation3Content.setBackground(Color.black);
		settingsInformation3Content.setPreferredSize(new Dimension(w, h));
		settingsInformation3Content.setOpaque(false);

		// Settings Information Page 3 Return Button.
		settingsInformation3ReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		settingsInformation3Content.add(settingsInformation3ReturnBtn, c);

		// Settings Information Page 3 Previous Button.
		settingsInformation3PrevBtn = new OptionButton(0.05 * w, 0.05 * w,
				prevDefault, prevHover, prevPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation3Content.add(settingsInformation3PrevBtn, c);

		// Settings Information Page 3 Next Button.
		settingsInformation3NextBtn = new OptionButton(0.05 * w, 0.05 * w,
				nextDefault, nextHover, nextPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_4));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation3Content.add(settingsInformation3NextBtn, c);

		// Add the buttons to the layered pane.
		settingsInformation3Layer.add(settingsInformation3Content,
				new GridBagConstraints());
		settingsInformation3Layer.setLayer(settingsInformation3Content, 1);

		// Add the screen to the panel.
		settingsInformation3.add(settingsInformation3Layer,
				new GridBagConstraints());

		// Add the buttons to the main panel.
		add(settingsInformation3, SETTINGS_INFORMATION_PAGE_3);

		/* Settings Information Page 4. */
		settingsInformation4 = new JPanel();
		settingsInformation4.setBackground(Color.black);
		settingsInformation4.setLayout(new GridBagLayout());
		settingsInformation4.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 4 Layers.
		settingsInformation4Layer = new JLayeredPane();
		settingsInformation4Layer.setBackground(Color.black);
		settingsInformation4Layer.setLayout(new GridBagLayout());
		settingsInformation4Layer.setPreferredSize(new Dimension(w, h));

		// Settings Information Page 4.
		settingsInformation4Img = new ImageIcon(getClass()
				.getResource("assets/GuideScreenSettingsInfo4.png"));
		settingsInformation4Pg = new JLabel();
		settingsInformation4Pg.setIcon(new ImageIcon(settingsInformation4Img
				.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInformation4Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInformation4Layer.add(settingsInformation4Pg, c);
		settingsInformation4Layer.setLayer(settingsInformation4Pg, 0);

		// Settings Information Page 4 Buttons.
		settingsInformation4Content = new JPanel();
		settingsInformation4Content.setLayout(new GridBagLayout());
		settingsInformation4Content.setBackground(Color.black);
		settingsInformation4Content.setPreferredSize(new Dimension(w, h));
		settingsInformation4Content.setOpaque(false);

		// Settings Information Page 4 Return Button.
		settingsInformation4ReturnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		settingsInformation4Content.add(settingsInformation4ReturnBtn, c);

		// Settings Information Page 4 Previous Button.
		settingsInformation4PrevBtn = new OptionButton(0.05 * w, 0.05 * w,
				prevDefault, prevHover, prevPress,
				e -> cl.show(this, SETTINGS_INFORMATION_PAGE_3));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInformation4Content.add(settingsInformation4PrevBtn, c);

		// Add the buttons to the layered pane.
		settingsInformation4Layer.add(settingsInformation4Content,
				new GridBagConstraints());
		settingsInformation4Layer.setLayer(settingsInformation4Content, 1);

		// Add the screen to the panel.
		settingsInformation4.add(settingsInformation4Layer,
				new GridBagConstraints());

		// Add the page to the main panel.
		add(settingsInformation4, SETTINGS_INFORMATION_PAGE_4);
	}
}