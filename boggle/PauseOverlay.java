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
	private final JLayeredPane layers = new JLayeredPane();
	private final JLabel background = new JLabel();

	private final JPanel options = new JPanel();

	private final OptionButton restartBtn;
	private final ImageIcon restartDefault = new ImageIcon(getClass().getResource("assets/RestartBtnDefault.png"));
	private final ImageIcon restartHover = new ImageIcon(getClass().getResource("assets/RestartBtnHover.png"));
	private final ImageIcon restartPress = new ImageIcon(getClass().getResource("assets/RestartBtnPress.png"));

	private final OptionButton homeBtn;
	private final ImageIcon homeDefault = new ImageIcon(getClass().getResource("assets/HomeBtnDefault.png"));
	private final ImageIcon homeHover = new ImageIcon(getClass().getResource("assets/HomeBtnHover.png"));
	private final ImageIcon homePress = new ImageIcon(getClass().getResource("assets/HomeBtnPress.png"));

	Boggle mainFrame;

	public PauseOverlay(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		GridBagConstraints c;

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));
		this.addMouseListener(this);

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		ImageIcon bg = new ImageIcon(getClass().getResource("assets/PauseOverlayBg.png"));
		background.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 1;
		layers.add(background, c);
		layers.setLayer(background, 0);

		options.setLayout(new GridBagLayout());
		options.setOpaque(false);
		options.setPreferredSize(new Dimension(w, h));

		homeBtn = new OptionButton(0.2 * w, 0.15 * h, homeDefault, homeHover, homePress, e -> {
			mainFrame.getGameScreen().stopBgm();
			mainFrame.menuScreen();
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, (int) (0.05 * h), (int) (0.05 * w));
		c.weightx = 1;
		c.weighty = 1;
		options.add(homeBtn, c);

		restartBtn = new OptionButton(0.2 * w, 0.15 * h, restartDefault, restartHover, restartPress, e -> mainFrame.gameScreen(mainFrame.isAI()));
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.gridx = 1;
		c.gridy = 0;
		c.insets = new Insets(0, (int) (0.05 * w), (int) (0.05 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		options.add(restartBtn, c);

		layers.add(options, new GridBagConstraints());
		layers.setLayer(options, 1);

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