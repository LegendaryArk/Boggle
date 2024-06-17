/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class OptionButton extends JButton implements ActionListener, MouseListener {
	private Dimension size;

	private ImageIcon defaultIcon;
	private ImageIcon hoverIcon;
	private ImageIcon pressIcon;

	private AudioInputStream clickSfx;
	private Clip clickClip;

	ButtonClicked action;

	public OptionButton(double width, double height, ImageIcon defaultIcon, ImageIcon hoverIcon, ImageIcon pressIcon, ButtonClicked action) {
		size = new Dimension((int) width, (int) height);

		this.action = action;

		this.defaultIcon = defaultIcon;
		this.hoverIcon = hoverIcon;
		this.pressIcon = pressIcon;
		this.defaultIcon = new ImageIcon(defaultIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
		this.hoverIcon = new ImageIcon(hoverIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));
		this.pressIcon = new ImageIcon(pressIcon.getImage().getScaledInstance(size.width, size.height, Image.SCALE_SMOOTH));

		setMinimumSize(size);
		setPreferredSize(size);
		setIcon(this.defaultIcon);
		setOpaque(false);
		setFocusable(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);

		addMouseListener(this);
		addActionListener(this);

		try {
			clickSfx = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("assets/ClickSound.wav"));
			clickClip = AudioSystem.getClip();
			clickClip.open(clickSfx);
		} catch (UnsupportedAudioFileException e) {
			System.err.println("Error: Invalid file type, unable to play sound effect");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error: Unable to read sound file");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			System.err.println("Error: No line to read");
			e.printStackTrace();
		}
	}

	/**
	 * Gets the default icon.
	 *
	 * @return The default icon
	 */
	public ImageIcon getDefaultIcon() {
		return defaultIcon;
	}

	/**
	 * Gets the hover icon.
	 *
	 * @return The hover icon
	 */
	public ImageIcon getHoverIcon() {
		return hoverIcon;
	}

	/**
	 * Gets the press icon.
	 *
	 * @return The press icon
	 */
	public ImageIcon getPressIcon() {
		return pressIcon;
	}

	/**
	 * Invoked when an action occurs.
	 * The function of the button.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		clickClip.start();

		action.clicked(e);

		setIcon(defaultIcon);
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Invoked when a mouse button has been pressed on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		setIcon(pressIcon);
	}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		setIcon(defaultIcon);
	}

	/**
	 * Invoked when the mouse enters a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (getIcon() == pressIcon) {
			return;
		}

		setIcon(hoverIcon);
	}

	/**
	 * Invoked when the mouse exits a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (getIcon() == pressIcon) {
			return;
		}

		setIcon(defaultIcon);
	}
}
