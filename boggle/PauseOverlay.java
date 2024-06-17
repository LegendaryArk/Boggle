/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PauseOverlay extends JPanel implements MouseListener {

	// Creating the LayeredPane, panel, and background label.
	private JLayeredPane layers = new JLayeredPane();
	private JLabel background = new JLabel();
	private JPanel options = new JPanel();

	// Creating the restart button.
	private OptionButton restartBtn;

	// Path to the restart button default image.
	private ImageIcon restartDefault = new ImageIcon(getClass().
			getResource("assets/RestartBtnDefault.png"));
	// Path to the restart button hover image.
	private ImageIcon restartHover = new ImageIcon(getClass().
			getResource("assets/RestartBtnHover.png"));
	// Path to the restart button pressed image.
	private ImageIcon restartPress = new ImageIcon(getClass().
			getResource("assets/RestartBtnPress.png"));

	// Creating the home button.
	private OptionButton homeBtn;
	
	// Path to the home button default image.
	private ImageIcon homeDefault = new ImageIcon(getClass().
			getResource("assets/HomeBtnDefault.png"));
	// Path to the home button hover image.
	private ImageIcon homeHover = new ImageIcon(getClass().
			getResource("assets/HomeBtnHover.png"));
	// Path to the home button pressed image.
	private ImageIcon homePress = new ImageIcon(getClass().getResource("assets/HomeBtnPress.png"));

	Boggle mainFrame;

	/**
	 * Constructor, this method creates a screen which shows up when the player
	 * clicks the pause button.
	 * @param mainFrame
	 */
	public PauseOverlay(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		// Setting the dimensions of the panel.
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		// Adding GridBagLayout.
		GridBagConstraints c;

		// Setting the colour and dimensions of the main panel.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));
		// Adding MouseListener to the main panel.
		this.addMouseListener(this);

		// Setting the colour and dimensions of the LayeredPane.
		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		// Path to the pause overlay button image.
		ImageIcon bg = new ImageIcon(getClass().
				getResource("assets/PauseOverlayBg.png"));
		background.setIcon(new ImageIcon(bg.getImage().
				getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		// Positioning the background using GridBagConstraints.
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		// Adding the background to the LayeredPane.
		layers.add(background, c);
		// Setting the background to the 0th layer of the LayeredPane.
		layers.setLayer(background, 0);

		// Positioning the options panel.
		options.setLayout(new GridBagLayout());
		options.setOpaque(false);
		options.setPreferredSize(new Dimension(w, h));

		// Creating the home button.
		homeBtn = new OptionButton(0.2 * w, 0.15 * h,
				homeDefault, homeHover, homePress, e -> {
			mainFrame.getGameScreen().stopBgm();
			mainFrame.menuScreen();
		});

		// Positioning the home button using GridBagLayout.
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, (int) (0.05 * h), (int) (0.05 * w));
		c.weightx = 1;
		c.weighty = 1;
		options.add(homeBtn, c);

		// Creating the restart button.
		restartBtn = new OptionButton(0.2 * w, 0.15 * h,
				restartDefault, restartHover, restartPress, e -> mainFrame.
				gameScreen(mainFrame.isAI()));
		// Creating the home button.
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, (int) (0.05 * w), (int) (0.05 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		// Adding the restart button to the options panel.
		options.add(restartBtn, c);
		
		// Adding the options panel to the LayeredPane.
		layers.add(options, new GridBagConstraints());
		// Setting the options panel as the 1th layer on the LayeredPane.
		layers.setLayer(options, 1);

		// Adding the LayeredPane to the main panel.
		this.add(layers, new GridBagConstraints());
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		mainFrame.gameScreen(mainFrame.isAI());
	}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/**
	 * Invoked when the mouse exits a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}