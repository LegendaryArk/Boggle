package boggle;

import javax.swing.*;
import java.awt.*;

public class GuideScreen extends JPanel {
	private CardLayout cl;

	private ImageIcon returnDefault = new ImageIcon(getClass().getResource("assets/ReturnBtnDefault.png"));
	private ImageIcon returnHover = new ImageIcon(getClass().getResource("assets/ReturnBtnHover.png"));
	private ImageIcon returnPress = new ImageIcon(getClass().getResource("assets/ReturnBtnPress.png"));

	private ImageIcon prevDefault = new ImageIcon(getClass().getResource("assets/PreviousBtnDefault.png"));
	private ImageIcon prevHover = new ImageIcon(getClass().getResource("assets/PreviousBtnHover.png"));
	private ImageIcon prevPress = new ImageIcon(getClass().getResource("assets/PreviousBtnPress.png"));

	private ImageIcon nextDefault = new ImageIcon(getClass().getResource("assets/NextBtnDefault.png"));
	private ImageIcon nextHover = new ImageIcon(getClass().getResource("assets/NextBtnHover.png"));
	private ImageIcon nextPress = new ImageIcon(getClass().getResource("assets/NextBtnPress.png"));

	private JPanel menu = new JPanel();
	private final String MENU_PAGE = "Menu Page";
	private JLayeredPane menuLayer = new JLayeredPane();
	private ImageIcon menuBgImg = new ImageIcon(getClass().getResource("assets/GuideScreenMenuBg.png"));
	private JLabel menuBg = new JLabel();
	private JPanel menuContent = new JPanel();
	private OptionButton menuReturnBtn;

	private OptionButton gameInfoBtn;
	private ImageIcon gameInfoDefault = new ImageIcon(getClass().getResource("assets/GameInfoBtnDefault.png"));
	private ImageIcon gameInfoHover = new ImageIcon(getClass().getResource("assets/GameInfoBtnHover.png"));
	private ImageIcon gameInfoPress = new ImageIcon(getClass().getResource("assets/GameInfoBtnPress.png"));

	private OptionButton pointsBreakdownBtn;
	private ImageIcon pointsBreakdownDefault = new ImageIcon(getClass().getResource("assets/PointsBreakdownBtnDefault.png"));
	private ImageIcon pointsBreakdownHover = new ImageIcon(getClass().getResource("assets/PointsBreakdownBtnHover.png"));
	private ImageIcon pointsBreakdownPress = new ImageIcon(getClass().getResource("assets/PointsBreakdownBtnPress.png"));

	private OptionButton settingsInfoBtn;
	private ImageIcon settingsInfoDefault = new ImageIcon(getClass().getResource("assets/SettingsInfoBtnDefault.png"));
	private ImageIcon settingsInfoHover = new ImageIcon(getClass().getResource("assets/SettingsInfoBtnHover.png"));
	private ImageIcon settingsInfoPress = new ImageIcon(getClass().getResource("assets/SettingsInfoBtnPress.png"));

	private JPanel gameInfo1 = new JPanel();
	private final String GAME_INFO_PAGE_1 = "Game Info Page 1";
	private JLayeredPane gameInfo1Layer = new JLayeredPane();
	private ImageIcon gameInfo1Img = new ImageIcon(getClass().getResource("assets/GuideScreenGameInfo1.png"));
	private JLabel gameInfo1Pg = new JLabel();
	private JPanel gameInfo1Content = new JPanel();
	private OptionButton gameInfo1ReturnBtn;
	private OptionButton gameInfo1NextBtn;

	private JPanel gameInfo2 = new JPanel();
	private final String GAME_INFO_PAGE_2 = "Game Info Page 2";
	private JLayeredPane gameInfo2Layer = new JLayeredPane();
	private ImageIcon gameInfo2Img = new ImageIcon(getClass().getResource("assets/GuideScreenGameInfo2.png"));
	private JLabel gameInfo2Pg = new JLabel();
	private JPanel gameInfo2Content = new JPanel();
	private OptionButton gameInfo2ReturnBtn;
	private OptionButton gameInfo2PrevBtn;
	private OptionButton gameInfo2NextBtn;

	private JPanel gameInfo3 = new JPanel();
	private final String GAME_INFO_PAGE_3 = "Game Info Page 3";
	private JLayeredPane gameInfo3Layer = new JLayeredPane();
	private ImageIcon gameInfo3Img = new ImageIcon(getClass().getResource("assets/GuideScreenGameInfo3.png"));
	private JLabel gameInfo3Pg = new JLabel();
	private JPanel gameInfo3Content = new JPanel();
	private OptionButton gameInfo3ReturnBtn;
	private OptionButton gameInfo3PrevBtn;

	private JPanel ptsBreakdown = new JPanel();
	private final String POINTS_BREAKDOWN_PAGE = "Points Breakdown Page";
	private JLayeredPane ptsBreakdownLayer = new JLayeredPane();
	private ImageIcon ptsBreakdownImg = new ImageIcon(getClass().getResource("assets/GuideScreenPointsBreakdown.png"));
	private JLabel ptsBreakdownPg = new JLabel();
	private JPanel ptsBreakdownContent = new JPanel();
	private OptionButton ptsBreakdownReturnBtn;

	private JPanel settingsInfo1 = new JPanel();
	private final String SETTINGS_INFO_PAGE_1 = "Settings Info Page 1";
	private JLayeredPane settingsInfo1Layer = new JLayeredPane();
	private ImageIcon settingsInfo1Img = new ImageIcon(getClass().getResource("assets/GuideScreenSettingsInfo1.png"));
	private JLabel settingsInfo1Pg = new JLabel();
	private JPanel settingsInfo1Content = new JPanel();
	private OptionButton settingsInfo1ReturnBtn;
	private OptionButton settingsInfo1NextBtn;

	private JPanel settingsInfo2 = new JPanel();
	private final String SETTINGS_INFO_PAGE_2 = "Settings Info Page 2";
	private JLayeredPane settingsInfo2Layer = new JLayeredPane();
	private ImageIcon settingsInfo2Img = new ImageIcon(getClass().getResource("assets/GuideScreenSettingsInfo2.png"));
	private JLabel settingsInfo2Pg = new JLabel();
	private JPanel settingsInfo2Content = new JPanel();
	private OptionButton settingsInfo2ReturnBtn;
	private OptionButton settingsInfo2PrevBtn;
	private OptionButton settingsInfo2NextBtn;

	private JPanel settingsInfo3 = new JPanel();
	private final String SETTINGS_INFO_PAGE_3 = "Settings Info Page 3";
	private JLayeredPane settingsInfo3Layer = new JLayeredPane();
	private ImageIcon settingsInfo3Img = new ImageIcon(getClass().getResource("assets/GuideScreenSettingsInfo3.png"));
	private JLabel settingsInfo3Pg = new JLabel();
	private JPanel settingsInfo3Content = new JPanel();
	private OptionButton settingsInfo3ReturnBtn;
	private OptionButton settingsInfo3PrevBtn;
	private OptionButton settingsInfo3NextBtn;

	private JPanel settingsInfo4 = new JPanel();
	private final String SETTINGS_INFO_PAGE_4 = "Settings Info Page 4";
	private JLayeredPane settingsInfo4Layer = new JLayeredPane();
	private ImageIcon settingsInfo4Img = new ImageIcon(getClass().getResource("assets/GuideScreenSettingsInfo4.png"));
	private JLabel settingsInfo4Pg = new JLabel();
	private JPanel settingsInfo4Content = new JPanel();
	private OptionButton settingsInfo4ReturnBtn;
	private OptionButton settingsInfo4PrevBtn;

	private Boggle mainFrame;

	public GuideScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.cl = new CardLayout();

		this.setBackground(Color.black);
		this.setLayout(cl);
		this.setPreferredSize(new Dimension(w, h));

		GridBagConstraints c;

		/* Menu Screen */
		menu.setBackground(Color.black);
		menu.setLayout(new GridBagLayout());
		menu.setPreferredSize(new Dimension(w, h));

		menuLayer.setBackground(Color.black);
		menuLayer.setLayout(new GridBagLayout());
		menuLayer.setPreferredSize(new Dimension(w, h));

		menuBg.setIcon(new ImageIcon(menuBgImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		menuBg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 3;
		menuLayer.add(menuBg, c);
		menuLayer.setLayer(menuBg, 0);

		menuContent.setLayout(new GridBagLayout());
		menuContent.setBackground(Color.black);
		menuContent.setPreferredSize(new Dimension(w, h));
		menuContent.setOpaque(false);

		menuReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> mainFrame.menuScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		menuContent.add(menuReturnBtn, c);

		gameInfoBtn = new OptionButton(0.5 * w, 0.15 * h, gameInfoDefault, gameInfoHover, gameInfoPress, e -> cl.show(this, GAME_INFO_PAGE_1));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.35 * h), 0, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(gameInfoBtn, c);

		pointsBreakdownBtn = new OptionButton(0.5 * w, 0.15 * h, pointsBreakdownDefault, pointsBreakdownHover, pointsBreakdownPress, e -> cl.show(this, POINTS_BREAKDOWN_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(pointsBreakdownBtn, c);

		settingsInfoBtn = new OptionButton(0.5 * w, 0.15 * h, settingsInfoDefault, settingsInfoHover, settingsInfoPress, e -> cl.show(this, SETTINGS_INFO_PAGE_1));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(0, 0, (int) (0.05 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(settingsInfoBtn, c);

		menuLayer.add(menuContent, new GridBagConstraints());
		menuLayer.setLayer(menuContent, 1);

		menu.add(menuLayer, new GridBagConstraints());

		add(menu, MENU_PAGE);
		cl.show(this, MENU_PAGE);

		/* Game Info Page 1 */
		gameInfo1.setBackground(Color.black);
		gameInfo1.setLayout(new GridBagLayout());
		gameInfo1.setPreferredSize(new Dimension(w, h));

		gameInfo1Layer.setBackground(Color.black);
		gameInfo1Layer.setLayout(new GridBagLayout());
		gameInfo1Layer.setPreferredSize(new Dimension(w, h));

		gameInfo1Pg.setIcon(new ImageIcon(gameInfo1Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		gameInfo1Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		gameInfo1Layer.add(gameInfo1Pg, c);
		gameInfo1Layer.setLayer(gameInfo1Pg, 0);

		gameInfo1Content.setLayout(new GridBagLayout());
		gameInfo1Content.setBackground(Color.black);
		gameInfo1Content.setPreferredSize(new Dimension(w, h));
		gameInfo1Content.setOpaque(false);

		gameInfo1ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		gameInfo1Content.add(gameInfo1ReturnBtn, c);

		gameInfo1NextBtn = new OptionButton(0.05 * w, 0.05 * w, nextDefault, nextHover, nextPress, e -> cl.show(this, GAME_INFO_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		gameInfo1Content.add(gameInfo1NextBtn, c);

		gameInfo1Layer.add(gameInfo1Content, new GridBagConstraints());
		gameInfo1Layer.setLayer(gameInfo1Content, 1);

		gameInfo1.add(gameInfo1Layer, new GridBagConstraints());

		add(gameInfo1, GAME_INFO_PAGE_1);

		/* Game Info Page 2 */
		gameInfo2.setBackground(Color.black);
		gameInfo2.setLayout(new GridBagLayout());
		gameInfo2.setPreferredSize(new Dimension(w, h));

		gameInfo2Layer.setBackground(Color.black);
		gameInfo2Layer.setLayout(new GridBagLayout());
		gameInfo2Layer.setPreferredSize(new Dimension(w, h));

		gameInfo2Pg.setIcon(new ImageIcon(gameInfo2Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		gameInfo2Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		gameInfo2Layer.add(gameInfo2Pg, c);
		gameInfo2Layer.setLayer(gameInfo2Pg, 0);

		gameInfo2Content.setLayout(new GridBagLayout());
		gameInfo2Content.setBackground(Color.black);
		gameInfo2Content.setPreferredSize(new Dimension(w, h));
		gameInfo2Content.setOpaque(false);

		gameInfo2ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		gameInfo2Content.add(gameInfo2ReturnBtn, c);

		gameInfo2PrevBtn = new OptionButton(0.05 * w, 0.05 * w, prevDefault, prevHover, prevPress, e -> cl.show(this, GAME_INFO_PAGE_1));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		gameInfo2Content.add(gameInfo2PrevBtn, c);

		gameInfo2NextBtn = new OptionButton(0.05 * w, 0.05 * w, nextDefault, nextHover, nextPress, e -> cl.show(this, GAME_INFO_PAGE_3));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		gameInfo2Content.add(gameInfo2NextBtn, c);

		gameInfo2Layer.add(gameInfo2Content, new GridBagConstraints());
		gameInfo2Layer.setLayer(gameInfo2Content, 1);

		gameInfo2.add(gameInfo2Layer, new GridBagConstraints());

		add(gameInfo2, GAME_INFO_PAGE_2);

		/* Game Info Page 3 */
		gameInfo3.setBackground(Color.black);
		gameInfo3.setLayout(new GridBagLayout());
		gameInfo3.setPreferredSize(new Dimension(w, h));

		gameInfo3Layer.setBackground(Color.black);
		gameInfo3Layer.setLayout(new GridBagLayout());
		gameInfo3Layer.setPreferredSize(new Dimension(w, h));

		gameInfo3Pg.setIcon(new ImageIcon(gameInfo3Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		gameInfo3Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		gameInfo3Layer.add(gameInfo3Pg, c);
		gameInfo3Layer.setLayer(gameInfo3Pg, 0);

		gameInfo3Content.setLayout(new GridBagLayout());
		gameInfo3Content.setBackground(Color.black);
		gameInfo3Content.setPreferredSize(new Dimension(w, h));
		gameInfo3Content.setOpaque(false);

		gameInfo3ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		gameInfo3Content.add(gameInfo3ReturnBtn, c);

		gameInfo3PrevBtn = new OptionButton(0.05 * w, 0.05 * w, prevDefault, prevHover, prevPress, e -> cl.show(this, GAME_INFO_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		gameInfo3Content.add(gameInfo3PrevBtn, c);

		gameInfo3Layer.add(gameInfo3Content, new GridBagConstraints());
		gameInfo3Layer.setLayer(gameInfo3Content, 1);

		gameInfo3.add(gameInfo3Layer, new GridBagConstraints());

		add(gameInfo3, GAME_INFO_PAGE_3);

		/* Points Breakdown Page */
		ptsBreakdown.setBackground(Color.black);
		ptsBreakdown.setLayout(new GridBagLayout());
		ptsBreakdown.setPreferredSize(new Dimension(w, h));

		ptsBreakdownLayer.setBackground(Color.black);
		ptsBreakdownLayer.setLayout(new GridBagLayout());
		ptsBreakdownLayer.setPreferredSize(new Dimension(w, h));

		ptsBreakdownPg.setIcon(new ImageIcon(ptsBreakdownImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		ptsBreakdownPg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		ptsBreakdownLayer.add(ptsBreakdownPg, c);
		ptsBreakdownLayer.setLayer(ptsBreakdownPg, 0);

		ptsBreakdownContent.setLayout(new GridBagLayout());
		ptsBreakdownContent.setBackground(Color.black);
		ptsBreakdownContent.setPreferredSize(new Dimension(w, h));
		ptsBreakdownContent.setOpaque(false);

		ptsBreakdownReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		ptsBreakdownContent.add(ptsBreakdownReturnBtn, c);

		ptsBreakdownLayer.add(ptsBreakdownContent, new GridBagConstraints());
		ptsBreakdownLayer.setLayer(ptsBreakdownContent, 1);

		ptsBreakdown.add(ptsBreakdownLayer, new GridBagConstraints());

		add(ptsBreakdown, POINTS_BREAKDOWN_PAGE);

		/* Settings Info Page 1 */
		settingsInfo1.setBackground(Color.black);
		settingsInfo1.setLayout(new GridBagLayout());
		settingsInfo1.setPreferredSize(new Dimension(w, h));

		settingsInfo1Layer.setBackground(Color.black);
		settingsInfo1Layer.setLayout(new GridBagLayout());
		settingsInfo1Layer.setPreferredSize(new Dimension(w, h));

		settingsInfo1Pg.setIcon(new ImageIcon(settingsInfo1Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInfo1Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInfo1Layer.add(settingsInfo1Pg, c);
		settingsInfo1Layer.setLayer(settingsInfo1Pg, 0);

		settingsInfo1Content.setLayout(new GridBagLayout());
		settingsInfo1Content.setBackground(Color.black);
		settingsInfo1Content.setPreferredSize(new Dimension(w, h));
		settingsInfo1Content.setOpaque(false);

		settingsInfo1ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo1Content.add(settingsInfo1ReturnBtn, c);

		settingsInfo1NextBtn = new OptionButton(0.05 * w, 0.05 * w, nextDefault, nextHover, nextPress, e -> cl.show(this, SETTINGS_INFO_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo1Content.add(settingsInfo1NextBtn, c);

		settingsInfo1Layer.add(settingsInfo1Content, new GridBagConstraints());
		settingsInfo1Layer.setLayer(settingsInfo1Content, 1);

		settingsInfo1.add(settingsInfo1Layer, new GridBagConstraints());

		add(settingsInfo1, SETTINGS_INFO_PAGE_1);

		/* Settings Info Page 2 */
		settingsInfo2.setBackground(Color.black);
		settingsInfo2.setLayout(new GridBagLayout());
		settingsInfo2.setPreferredSize(new Dimension(w, h));

		settingsInfo2Layer.setBackground(Color.black);
		settingsInfo2Layer.setLayout(new GridBagLayout());
		settingsInfo2Layer.setPreferredSize(new Dimension(w, h));

		settingsInfo2Pg.setIcon(new ImageIcon(settingsInfo2Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInfo2Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInfo2Layer.add(settingsInfo2Pg, c);
		settingsInfo2Layer.setLayer(settingsInfo2Pg, 0);

		settingsInfo2Content.setLayout(new GridBagLayout());
		settingsInfo2Content.setBackground(Color.black);
		settingsInfo2Content.setPreferredSize(new Dimension(w, h));
		settingsInfo2Content.setOpaque(false);

		settingsInfo2ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		settingsInfo2Content.add(settingsInfo2ReturnBtn, c);

		settingsInfo2PrevBtn = new OptionButton(0.05 * w, 0.05 * w, prevDefault, prevHover, prevPress, e -> cl.show(this, SETTINGS_INFO_PAGE_1));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo2Content.add(settingsInfo2PrevBtn, c);

		settingsInfo2NextBtn = new OptionButton(0.05 * w, 0.05 * w, nextDefault, nextHover, nextPress, e -> cl.show(this, SETTINGS_INFO_PAGE_3));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo2Content.add(settingsInfo2NextBtn, c);

		settingsInfo2Layer.add(settingsInfo2Content, new GridBagConstraints());
		settingsInfo2Layer.setLayer(settingsInfo2Content, 1);

		settingsInfo2.add(settingsInfo2Layer, new GridBagConstraints());

		add(settingsInfo2, SETTINGS_INFO_PAGE_2);

		/* Settings Info Page 3 */
		settingsInfo3.setBackground(Color.black);
		settingsInfo3.setLayout(new GridBagLayout());
		settingsInfo3.setPreferredSize(new Dimension(w, h));

		settingsInfo3Layer.setBackground(Color.black);
		settingsInfo3Layer.setLayout(new GridBagLayout());
		settingsInfo3Layer.setPreferredSize(new Dimension(w, h));

		settingsInfo3Pg.setIcon(new ImageIcon(settingsInfo3Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInfo3Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInfo3Layer.add(settingsInfo3Pg, c);
		settingsInfo3Layer.setLayer(settingsInfo3Pg, 0);

		settingsInfo3Content.setLayout(new GridBagLayout());
		settingsInfo3Content.setBackground(Color.black);
		settingsInfo3Content.setPreferredSize(new Dimension(w, h));
		settingsInfo3Content.setOpaque(false);

		settingsInfo3ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		settingsInfo3Content.add(settingsInfo3ReturnBtn, c);

		settingsInfo3PrevBtn = new OptionButton(0.05 * w, 0.05 * w, prevDefault, prevHover, prevPress, e -> cl.show(this, SETTINGS_INFO_PAGE_2));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo3Content.add(settingsInfo3PrevBtn, c);

		settingsInfo3NextBtn = new OptionButton(0.05 * w, 0.05 * w, nextDefault, nextHover, nextPress, e -> cl.show(this, SETTINGS_INFO_PAGE_4));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), 0, 0, (int) (0.05 * h));
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo3Content.add(settingsInfo3NextBtn, c);

		settingsInfo3Layer.add(settingsInfo3Content, new GridBagConstraints());
		settingsInfo3Layer.setLayer(settingsInfo3Content, 1);

		settingsInfo3.add(settingsInfo3Layer, new GridBagConstraints());

		add(settingsInfo3, SETTINGS_INFO_PAGE_3);

		/* Settings Info Page 4 */
		settingsInfo4.setBackground(Color.black);
		settingsInfo4.setLayout(new GridBagLayout());
		settingsInfo4.setPreferredSize(new Dimension(w, h));

		settingsInfo4Layer.setBackground(Color.black);
		settingsInfo4Layer.setLayout(new GridBagLayout());
		settingsInfo4Layer.setPreferredSize(new Dimension(w, h));

		settingsInfo4Pg.setIcon(new ImageIcon(settingsInfo4Img.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		settingsInfo4Pg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		settingsInfo4Layer.add(settingsInfo4Pg, c);
		settingsInfo4Layer.setLayer(settingsInfo4Pg, 0);

		settingsInfo4Content.setLayout(new GridBagLayout());
		settingsInfo4Content.setBackground(Color.black);
		settingsInfo4Content.setPreferredSize(new Dimension(w, h));
		settingsInfo4Content.setOpaque(false);

		settingsInfo4ReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		settingsInfo4Content.add(settingsInfo4ReturnBtn, c);

		settingsInfo4PrevBtn = new OptionButton(0.05 * w, 0.05 * w, prevDefault, prevHover, prevPress, e -> cl.show(this, SETTINGS_INFO_PAGE_3));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.22 * h), (int) (0.05 * h), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		settingsInfo4Content.add(settingsInfo4PrevBtn, c);

		settingsInfo4Layer.add(settingsInfo4Content, new GridBagConstraints());
		settingsInfo4Layer.setLayer(settingsInfo4Content, 1);

		settingsInfo4.add(settingsInfo4Layer, new GridBagConstraints());

		add(settingsInfo4, SETTINGS_INFO_PAGE_4);
	}
}