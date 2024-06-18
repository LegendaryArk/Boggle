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

/**
 * This class contains a method which creates a button given its dimensions,
 * ImageIcons.
 */
public class OptionButton extends JButton
		implements ActionListener, MouseListener {
	// Size of the button
	private Dimension size;

	// Default icon for button.
	private ImageIcon defaultIcon;
	// Hover icon for button.
	private ImageIcon hoverIcon;
	// Press icon for button.
	private ImageIcon pressIcon;

	// Click sound effect audio.
	private AudioInputStream clickSoundEffect;
	// Click sound clip.
	private Clip clickClip;

	// Button clicked action.
	private ButtonClicked action;

	// If the button is a radio button.
	private boolean isRadioButton;

	/**
	 *
	 * This method creates a button given its dimensions, ImageIcons, and
	 * action.
	 *
	 * @param isRadioButton changes how the mouseReleased method works.
	 * @param width width of button.
	 * @param height height of button.
	 * @param defaultIcon ImageIcon of the button when it is not hovered over.
	 * @param hoverIcon ImageIcon of the button when it is hovered.
	 * @param pressIcon ImageIcon of the button when it is pressed.
	 * @param action action.
	 */
	public OptionButton(boolean isRadioButton, double width, double height,
	                    ImageIcon defaultIcon, ImageIcon hoverIcon,
	                    ImageIcon pressIcon, ButtonClicked action) {
		// Initialize instance variables.
		this.isRadioButton = isRadioButton;
		this.size = new Dimension((int) width, (int) height);
		this.action = action;

		// Resizing the icons.
		this.defaultIcon = defaultIcon;
		this.hoverIcon = hoverIcon;
		this.pressIcon = pressIcon;
		this.defaultIcon = new ImageIcon(defaultIcon.getImage()
				.getScaledInstance(size.width, size.height,
						Image.SCALE_SMOOTH));
		this.hoverIcon = new ImageIcon(hoverIcon.getImage()
				.getScaledInstance(size.width, size.height,
						Image.SCALE_SMOOTH));
		this.pressIcon = new ImageIcon(pressIcon.getImage()
				.getScaledInstance(size.width, size.height,
						Image.SCALE_SMOOTH));

		// Setting the dimensions.
		setMinimumSize(size);
		setPreferredSize(size);

		// Setting the default Icon.
		setIcon(this.defaultIcon);

		// Making the button visible.
		setOpaque(false);
		setFocusable(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);

		// Adding the MouseListener and ActionListener.
		addMouseListener(this);
		addActionListener(this);

		// Getting the sfx of the button being clicked.
		try {
			// Path to the sound effects.
			clickSoundEffect = AudioSystem.getAudioInputStream(getClass()
					.getResourceAsStream("assets/ClickSound.wav"));
			clickClip = AudioSystem.getClip();
			// Open and play clip.
			clickClip.open(clickSoundEffect);

			// Catching possible errors.
		} catch (UnsupportedAudioFileException e) {
			// Handle if file type is invalid.
			System.err.println(
					"Error: Invalid file type, unable to play sound effect");
			e.printStackTrace();
		} catch (IOException e) {
			// Handle if unable to read sound file.
			System.err.println("Error: Unable to read sound file");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// Handle if there is no line to read.
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
		clickClip.stop();
		clickClip.setFramePosition(0);
		clickClip.start();
		setIcon(pressIcon);

		action.clicked(e);

		if (!isRadioButton) {
			setIcon(defaultIcon);
		}
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
	public void mousePressed(MouseEvent e) {}

	/**
	 * Invoked when a mouse button has been released on a component.
	 *
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// If it is a radio button, do not reset the icon after mouse is
		// released.
		if (isRadioButton) {
			return;
		}

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