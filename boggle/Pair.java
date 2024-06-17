/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

/**
 * This class provides a way to store two integers as a single unit.
 */
public class Pair {
	private int x, y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
