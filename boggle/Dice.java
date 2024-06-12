package boggle;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;

public class Dice extends JLabel {
	private JLabel center = new JLabel();
	private char[] faces;
	private int top;
	private Random rand = new Random();
	private boolean selected = false;

	private ImageIcon defaultBg = new ImageIcon(getClass().getResource("assets/DieDefault.png"));
	private ImageIcon holdBg = new ImageIcon(getClass().getResource("assets/DieHold.png"));
	private ImageIcon newBg = new ImageIcon(getClass().getResource("assets/DieNew.png"));
	private ImageIcon oldBg = new ImageIcon(getClass().getResource("assets/DieFound.png"));

	private int width, height;

	public Dice(char[] faces, int width, int height) {
		this.faces = faces;
		roll();

		this.width = width;
		this.height = height;

		// 1 smaller pixel on the size to make sure it stays within the cell (can be larger due to rounding errors)
		defaultBg = new ImageIcon(defaultBg.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));
		holdBg = new ImageIcon(holdBg.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));
		newBg = new ImageIcon(newBg.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));
		oldBg = new ImageIcon(oldBg.getImage().getScaledInstance(width - 1, height - 1, Image.SCALE_SMOOTH));

		setOpaque(false);
		setFocusable(false);
		setIcon(defaultBg);
		setLayout(new GridBagLayout());

		center.setOpaque(false);
		center.setFocusable(false);
		center.setPreferredSize(new Dimension((int) (0.8 * getPreferredSize().width), (int) (0.8 * getPreferredSize().height)));
		add(center, new GridBagConstraints());

		setFont(new Font("Arial", Font.PLAIN, 75)); // May need to make font size dynamic as well
		setText(String.valueOf(faces[top]));
		setHorizontalTextPosition(JButton.CENTER);
		setVerticalTextPosition(JButton.CENTER);
	}

	public char getTopFace() {
		return faces[top];
	}

	public void roll() {
		top = rand.nextInt(6);
		setText(String.valueOf(faces[top]));
	}

	public void setFaces(char[] faces) {
		this.faces = faces;
	}
	public char[] getFaces() {
		return faces;
	}

	public void select() {
		setIcon(holdBg);
		selected = true;
	}
	public void deselect() {
		setIcon(defaultBg);
		selected = false;
	}
	public boolean isSelected() {
		return selected;
	}

	public void valid() {
		setIcon(newBg);
	}
	public void old() {
		setIcon(oldBg);
	}

	public JLabel getCenter() {
		return center;
	}
}
