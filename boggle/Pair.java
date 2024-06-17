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

	/**
	 * This is the constructor.
	 * @param x the first value to store.
	 * @param y the second value to store.
	 */
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * This is a getter method that gets the first value of the pair.
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * This is a getter method that gets the second value of the pair.
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * This is a setter method that sets the first value of
	 * the pair.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * This is a setter method that sets the second value of
	 * the pair.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
}