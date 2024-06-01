package boggle;

import java.util.Random;

public class Dice {
	private char[] faces;
	private int top;
	private Random rand = new Random();

	public Dice(char[] faces) {
		this.faces = faces;
		roll();
	}

	public char getTopFace() {
		return faces[top];
	}

	public void roll() {
		top = rand.nextInt(6);
	}
}
