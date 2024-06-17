/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * This class is an implementation of a Boggle dice that can be rolled and
 * selected.
 */
public class Dice extends JLabel {
	// Detection box for selecting the dice.
	private JLabel detectionBox;
	// Faces of the dice.
	private char[] faces;
	// Top face of the dice.
	private int top;
	// Random Number Generator.
	private Random random;
	// Stores if the dice is selected.
	private boolean selected;
	// Default background image for dice.
	private ImageIcon defaultBackground;
	// Hold background image for dice.
	private ImageIcon holdBackground;
	// New background image for dice.
	private ImageIcon newBackground;
	// Old background image for dice.
	private ImageIcon oldBackground;

	/**
	 * Constructor.
	 * @param faces Array of characters.
	 * @param width width of dice.
	 * @param height height of dice.
	 */
	public Dice(char[] faces, int width, int height) {
		// Instance variables.
		this.faces = faces;
		detectionBox = new JLabel();
		random = new Random();
		selected = false;
		roll();

		// Set background images for the different states of the dice.
		defaultBackground = new ImageIcon(getClass()
				.getResource("assets/DieDefault.png"));
		holdBackground = new ImageIcon(getClass()
				.getResource("assets/DieHold.png"));
		newBackground = new ImageIcon(getClass()
				.getResource("assets/DieNew.png"));
		oldBackground = new ImageIcon(getClass()
				.getResource("assets/DieFound.png"));

		// 1 smaller pixel on the size to make sure it stays within the cell.
		// (can be larger due to rounding errors).
		defaultBackground = new ImageIcon(defaultBackground.getImage()
				.getScaledInstance(width - 1, height - 1,
						Image.SCALE_SMOOTH));
		holdBackground = new ImageIcon(holdBackground.getImage()
				.getScaledInstance(width - 1, height - 1,
						Image.SCALE_SMOOTH));
		newBackground = new ImageIcon(newBackground.getImage()
				.getScaledInstance(width - 1, height - 1,
						Image.SCALE_SMOOTH));
		oldBackground = new ImageIcon(oldBackground.getImage()
				.getScaledInstance(width - 1, height - 1,
						Image.SCALE_SMOOTH));

		// Set up the button.
		this.setOpaque(false);
		this.setFocusable(false);
		this.setIcon(defaultBackground);
		this.setLayout(new GridBagLayout());

		// Set the detection box for the dice.
		detectionBox.setOpaque(false);
		detectionBox.setFocusable(false);
		detectionBox.setPreferredSize(new Dimension((int) (0.8 * getPreferredSize().width), (int) (0.8 * getPreferredSize().height)));
		add(detectionBox, new GridBagConstraints());

		// Set the attributes of the dice text.
		setFont(new Font("Arial", Font.PLAIN, 75));
		setText(String.valueOf(faces[top]));
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
	}

	/**
	 * This method gets the top face.
	 * @return the top face.
	 */
	public char getTopFace() {
		return faces[top];
	}

	/**
	 * This method rolls the dices.
	 */
	public void roll() {
		top = random.nextInt(6);
		setText(String.valueOf(faces[top]));
	}

	/**
	 * This method sets the faces of the dice.
	 * @param faces of dice.
	 */
	public void setFaces(char[] faces) {
		this.faces = faces;
	}

	/**
	 * This method gets the faces of the dice.
	 * @return the faces.
	 */
	public char[] getFaces() {
		return faces;
	}

	/**
	 * This method changes the background when selecting.
	 */
	public void select() {
		setIcon(holdBackground);
		selected = true;
	}

	/**
	 * This method changes the background when deselecting.
	 */
	public void deselect() {
		setIcon(defaultBackground);
		selected = false;
	}

	/**
	 * This method checks if dice is selected.
	 * @return True if the dice is selected.
	 *         False if th dice is not selected.
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * This method changes the background if word is valid.
	 */
	public void valid() {
		setIcon(newBackground);
	}

	/**
	 * This method changes the background if word is old.
	 */
	public void old() {
		setIcon(oldBackground);
	}

	/**
	 * This method gets the center of the detection box.
	 * @return the detection box
	 */
	public JLabel getCenter() {
		return detectionBox;
	}
}