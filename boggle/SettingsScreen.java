package boggle;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SettingsScreen extends JPanel {
	private CardLayout cl;

	private ImageIcon returnDefault = new ImageIcon(getClass().getResource("assets/ReturnBtnDefault.png"));
	private ImageIcon returnHover = new ImageIcon(getClass().getResource("assets/ReturnBtnHover.png"));
	private ImageIcon returnPress = new ImageIcon(getClass().getResource("assets/ReturnBtnPress.png"));

	private JPanel menu = new JPanel();
	private final String MENU_PAGE = "Menu Page";
	private JLayeredPane menuLayer = new JLayeredPane();
	private JLabel menuBg = new JLabel();
	private ImageIcon menuBgImg = new ImageIcon(getClass().getResource("assets/SettingsScreenMenuBg.png"));
	private JPanel menuContent = new JPanel();
	private OptionButton menuReturnBtn;

	private OptionButton generalBtn;
	private ImageIcon generalDefault = new ImageIcon(getClass().getResource("assets/GeneralBtnDefault.png"));
	private ImageIcon generalHover = new ImageIcon(getClass().getResource("assets/GeneralBtnHover.png"));
	private ImageIcon generalPress = new ImageIcon(getClass().getResource("assets/GeneralBtnPress.png"));

	private OptionButton timeBtn;
	private ImageIcon timeDefault = new ImageIcon(getClass().getResource("assets/TimeBtnDefault.png"));
	private ImageIcon timeHover = new ImageIcon(getClass().getResource("assets/TimeBtnHover.png"));
	private ImageIcon timePress = new ImageIcon(getClass().getResource("assets/TimeBtnPress.png"));

	private OptionButton difficultyBtn;
	private ImageIcon difficultyDefault = new ImageIcon(getClass().getResource("assets/DifficultyBtnDefault.png"));
	private ImageIcon difficultyHover = new ImageIcon(getClass().getResource("assets/DifficultyBtnHover.png"));
	private ImageIcon difficultyPress = new ImageIcon(getClass().getResource("assets/DifficultyBtnPress.png"));

	private OptionButton musicBtn;
	private ImageIcon musicDefault = new ImageIcon(getClass().getResource("assets/MusicBtnDefault.png"));
	private ImageIcon musicHover = new ImageIcon(getClass().getResource("assets/MusicBtnHover.png"));
	private ImageIcon musicPress = new ImageIcon(getClass().getResource("assets/MusicBtnPress.png"));

	private ImageIcon incDefault = new ImageIcon(getClass().getResource("assets/SettingsIncBtnDefault.png"));
	private ImageIcon incHover = new ImageIcon(getClass().getResource("assets/SettingsIncBtnHover.png"));
	private ImageIcon incPress = new ImageIcon(getClass().getResource("assets/SettingsIncBtnPress.png"));
	private ImageIcon decDefault = new ImageIcon(getClass().getResource("assets/SettingsDecBtnDefault.png"));
	private ImageIcon decHover = new ImageIcon(getClass().getResource("assets/SettingsDecBtnHover.png"));
	private ImageIcon decPress = new ImageIcon(getClass().getResource("assets/SettingsDecBtnPress.png"));

	private ImageIcon radioDefault = new ImageIcon(getClass().getResource("assets/RadioBtnDefault.png"));
	private ImageIcon radioHover = new ImageIcon(getClass().getResource("assets/RadioBtnHover.png"));
	private ImageIcon radioSelect = new ImageIcon(getClass().getResource("assets/RadioBtnSelected.png"));

	private JPanel general = new JPanel();
	private ImageIcon generalBgImg = new ImageIcon(getClass().getResource("assets/GeneralSettingsScreenBg.png"));
	private final String GENERAL_PAGE = "General Page";
	private JLayeredPane generalLayer = new JLayeredPane();
	private JLabel generalBg = new JLabel();
	private JPanel generalContent = new JPanel();
	private OptionButton generalReturnBtn;

	private int targetPts;
	private JLabel targetPtsDisplay = new JLabel();
	private OptionButton targetPtsInc;
	private OptionButton targetPtsDec;

	private int minWordLen;
	private JLabel minWordLenDisplay = new JLabel();
	private OptionButton minWordLenInc;
	private OptionButton minWordLenDec;

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

		this.cl = new CardLayout();

		GridBagConstraints c;

		this.setBackground(Color.black);
		this.setLayout(cl);
		this.setPreferredSize(new Dimension(w, h));

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
		c.gridheight = 4;
		menuLayer.add(menuBg, c);
		menuLayer.setLayer(menuBg, 0);

		menuContent.setLayout(new GridBagLayout());
		menuContent.setBackground(Color.black);
		menuContent.setPreferredSize(new Dimension(w, h));
		menuContent.setOpaque(false);

		menuReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> {
			mainFrame.updateSettings();
			switch (this.prevScreen) {
				case 0 -> mainFrame.menuScreen();
				case 1 -> mainFrame.gameScreen(mainFrame.isAgainstAI());
			}
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		menuContent.add(menuReturnBtn, c);

		generalBtn = new OptionButton(0.3 * w, 0.14 * h, generalDefault, generalHover, generalPress, e -> cl.show(this, GENERAL_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.3 * h), 0, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(generalBtn, c);

		timeBtn = new OptionButton(0.3 * w, 0.14 * h, timeDefault, timeHover, timePress, e -> cl.show(this, TIME_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(timeBtn, c);

		difficultyBtn = new OptionButton(0.3 * w, 0.14 * h, difficultyDefault, difficultyHover, difficultyPress, e -> cl.show(this, DIFFICULTY_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(difficultyBtn, c);

		musicBtn = new OptionButton(0.3 * w, 0.14 * h, musicDefault, musicHover, musicPress, e -> cl.show(this, MUSIC_PAGE));
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.insets = new Insets(0, 0, (int) (0.05 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		menuContent.add(musicBtn, c);

		menuLayer.add(menuContent, new GridBagConstraints());
		menuLayer.setLayer(menuContent, 1);

		menu.add(menuLayer, new GridBagConstraints());

		add(menu, MENU_PAGE);
		cl.show(this, MENU_PAGE);

		/* General Settings Page */
		general.setBackground(Color.black);
		general.setLayout(new GridBagLayout());
		general.setPreferredSize(new Dimension(w, h));

		generalLayer.setBackground(Color.black);
		generalLayer.setLayout(new GridBagLayout());
		generalLayer.setPreferredSize(new Dimension(w, h));

		generalBg.setIcon(new ImageIcon(generalBgImg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		generalBg.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.gridheight = 2;
		generalLayer.add(generalBg, c);
		generalLayer.setLayer(generalBg, 0);

		generalContent.setLayout(new GridBagLayout());
		generalContent.setBackground(Color.black);
		generalContent.setPreferredSize(new Dimension(w, h));
		generalContent.setOpaque(false);

		generalReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		generalContent.add(generalReturnBtn, c);

		minWordLen = 3;
		minWordLenDisplay.setMinimumSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		minWordLenDisplay.setPreferredSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		minWordLenDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		minWordLenDisplay.setVerticalAlignment(SwingConstants.CENTER);
		minWordLenDisplay.setText(String.valueOf(minWordLen));
		minWordLenDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets((int) (0.497 * h), 0, 0, (int) (0.058 * w));
		c.weightx = 1;
		c.weighty = 0;
		generalContent.add(minWordLenDisplay, c);

		minWordLenInc = new OptionButton(0.06 * w, 0.06 * w, incDefault, incHover, incHover, e -> {
			minWordLen++;
			minWordLenDisplay.setText(String.valueOf(minWordLen));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.2 * w), 0, (int) (0.02 * w));
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(minWordLenInc, c);

		minWordLenDec = new OptionButton(0.06 * w, 0.06 * w, decDefault, decHover, decHover, e -> {
			minWordLen--;
			if (minWordLen < 1) {
				minWordLen = 1;
			}
			minWordLenDisplay.setText(String.valueOf(minWordLen));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.02 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(minWordLenDec, c);

		targetPts = 15;
		targetPtsDisplay.setMinimumSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		targetPtsDisplay.setPreferredSize(new Dimension((int) (0.18 * w), (int) (0.16 * h)));
		targetPtsDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		targetPtsDisplay.setVerticalAlignment(SwingConstants.CENTER);
		targetPtsDisplay.setText(String.valueOf(targetPts));
		targetPtsDisplay.setFont(new Font("Verdana", Font.PLAIN, 70));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.insets = new Insets((int) (0.497 * h), (int) (0.06 * w), 0, 0);
		c.weightx = 1;
		c.weighty = 0;
		generalContent.add(targetPtsDisplay, c);

		targetPtsInc = new OptionButton(0.06 * w, 0.06 * w, incDefault, incHover, incHover, e -> {
			targetPts++;
			targetPtsDisplay.setText(String.valueOf(targetPts));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), 0, 0, (int) (0.02 * w));
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(targetPtsInc, c);

		targetPtsDec = new OptionButton(0.06 * w, 0.06 * w, decDefault, decHover, decHover, e -> {
			targetPts--;
			if (targetPts < 1) {
				targetPts = 1;
			}
			targetPtsDisplay.setText(String.valueOf(targetPts));
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 3;
		c.gridy = 1;
		c.insets = new Insets((int) (0.03 * h), (int) (0.02 * w), 0, (int) (0.2 * w));
		c.weightx = 1;
		c.weighty = 1;
		generalContent.add(targetPtsDec, c);

		generalLayer.add(generalContent, new GridBagConstraints());
		generalLayer.setLayer(generalContent, 1);
		general.add(generalLayer, new GridBagConstraints());

		add(general, GENERAL_PAGE);

		/* Time Settings Page */
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

		timeReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
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

		initTimeInc = new OptionButton(0.06 * w, 0.06 * w, incDefault, incHover, incPress, e -> {
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

		initTimeDec = new OptionButton(0.06 * w, 0.06 * w, decDefault, decHover, decPress, e -> {
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

		timeIncrementInc = new OptionButton(0.06 * w, 0.06 * w, incDefault, incHover, incHover, e -> {
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

		timeIncrementDec = new OptionButton(0.06 * w, 0.06 * w, decDefault, decHover, decHover, e -> {
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

		difficultyReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		difficultyContent.add(difficultyReturnBtn, c);

		easyBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

		medBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

		hardBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

		impossibleBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

		musicReturnBtn = new OptionButton(0.06 * w, 0.06 * w, returnDefault, returnHover, returnPress, e -> cl.show(this, MENU_PAGE));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.04 * h), (int) (0.04 * h), 0, 0);
		musicContent.add(musicReturnBtn, c);

		defaultMusicBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

		calmMusicBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

		intenseMusicBtn = new OptionButton(0.05 * w, 0.05 * w, radioDefault, radioHover, radioSelect, e -> {
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

	public int getMinWordLen() {
		return minWordLen;
	}
	public int getTargetPts() {
		return targetPts;
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