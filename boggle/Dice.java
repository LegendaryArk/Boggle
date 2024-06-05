package boggle;

import java.awt.*;
import java.util.Random;
import javax.swing.*;

public class Dice extends JButton {
	private char[] faces;
	private int top;
	private Random rand = new Random();
	private boolean selected = false;

	public Dice(char[] faces) {
		this.faces = faces;
		roll();

		setOpaque(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);
		setFocusable(false);
		setIcon(new ImageIcon(getClass().getResource("assets/DieDefault.png")));

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
	}

	public void select() {
		setIcon(new ImageIcon(this.getClass().getResource("assets/DieHold.png")));
		selected = true;
	}
	public void deselect() {
		setIcon(new ImageIcon(this.getClass().getResource("assets/DieDefault.png")));
		selected = false;
	}
	public boolean isSelected() {
		return selected;
	}

	public void valid() {
		setIcon(new ImageIcon(this.getClass().getResource("assets/DieNew.png")));
	}
	public void old() {
		setIcon(new ImageIcon(this.getClass().getResource("assets/DieFound.png")));
	}
}
