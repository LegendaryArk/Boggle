package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OptionButton extends JLabel implements MouseListener {
	private Dimension size;

	private ImageIcon defaultIcon;
	private ImageIcon hoverIcon;
	private ImageIcon pressIcon;

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

		addMouseListener(this);
	}

	public ImageIcon getDefaultIcon() {
		return defaultIcon;
	}
	public ImageIcon getHoverIcon() {
		return hoverIcon;
	}
	public ImageIcon getPressIcon() {
		return pressIcon;
	}

	/**
	 * Invoked when the mouse button has been clicked (pressed
	 * and released) on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		action.clicked(e);
	}

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
