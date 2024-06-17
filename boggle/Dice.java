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
 * This class is an implementation of a Boggle dice that can be rolled and selected.
 */
public class Dice extends JLabel {
	// Detection box for selecting the dice.
	private JLabel hitbox;
	// Faces of the dice.
	private char[] faces;
	// Top face of the dice.
	private int top;
	// Random Number Generator.
	private Random random;
	// Stores if the dice is selected.
	private boolean selected;


	private ImageIcon defaultBackground;
	private ImageIcon holdBackground;
	private ImageIcon newBackground;
	private ImageIcon oldBackground;

	private int width;
	private int height;

	public Dice(char[] faces, int width, int height) {


		this.faces = faces;
		this.width = width;
		this.height = height;
		hitbox = new JLabel();
		random = new Random();
		selected = false;
		roll();
		
		defaultBackground = new ImageIcon(getClass().getResource("assets/DieDefault.png"));
		holdBackground = new ImageIcon(getClass().getResource("assets/DieHold.png"));
		newBackground = new ImageIcon(getClass().getResource("assets/DieNew.png"));
		oldBackground = new ImageIcon(getClass().getResource("assets/DieFound.png"));
		// 1 smaller pixel on the size to make sure it stays within the cell (can be larger due to rounding errors)
		defaultBackground = new ImageIcon(defaultBackground.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));
		holdBackground = new ImageIcon(holdBackground.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));
		newBackground = new ImageIcon(newBackground.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));
		oldBackground = new ImageIcon(oldBackground.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));

		setOpaque(false);
		setFocusable(false);
		setIcon(defaultBackground);
		setLayout(new GridBagLayout());

		hitbox.setOpaque(false);
		hitbox.setFocusable(false);
		hitbox.setPreferredSize(new Dimension((int) (0.8 * getPreferredSize().width), (int) (0.8 * getPreferredSize().height)));
		add(hitbox, new GridBagConstraints());

		setFont(new Font("Arial", Font.PLAIN, 75)); // May need to make font size dynamic as well
		setText(String.valueOf(faces[top]));
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
	}

	public char getTopFace() {
		return faces[top];
	}

	public void roll() {
		top = random.nextInt(6);
		setText(String.valueOf(faces[top]));
	}

	public void setFaces(char[] faces) {
		this.faces = faces;
	}

	public char[] getFaces() {
		return faces;
	}

	public void select() {
		setIcon(holdBackground);
		selected = true;
	}

	public void deselect() {
		setIcon(defaultBackground);
		selected = false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void valid() {
		setIcon(newBackground);
	}

	public void old() {
		setIcon(oldBackground);
	}

	public JLabel getCenter() {
		return hitbox;
	}
}
