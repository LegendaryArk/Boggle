/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */
package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class holds all the traits of a player in one unit.
 */
public class Player {
	// Number of points a player has scored.
	protected int points;
	// Timer of player.
	protected Clock timer;

	// Player turn indicator.
	protected ImageIcon playerTurnIndicator;
	// Player display.
	protected JLabel playerDisplay;
	// Points display.
	protected JLabel pointsDisplay;

	/**
	 * Constructor
	 * @param playerDisplay player display
	 * @param pointsDisplay points display
	 * @param timer timer
	 */
	public Player(JLabel playerDisplay, JLabel pointsDisplay, Clock timer) {
		// Instantiate variables.
		this.playerDisplay = playerDisplay;
		this.pointsDisplay = pointsDisplay;
		playerTurnIndicator = new ImageIcon(getClass().getResource("assets/PlrTurnIndicator.png"));
		playerTurnIndicator = new ImageIcon(playerTurnIndicator.getImage().getScaledInstance(playerDisplay.getMinimumSize().width,playerDisplay.getMinimumSize().height, Image.SCALE_SMOOTH));
		points = 0;
		this.timer = timer;

		// Set points display to '0'.
		pointsDisplay.setText(String.valueOf(points));
	}

	/**
	 * This method increments the number of points the player has.
	 * @param increment the number to increment by.
	 */
	public void addPoints(int increment) {
		// Increment points.
		points += increment;
		// Update pointsDisplay.
		pointsDisplay.setText(String.valueOf(points));
	}

	/**
	 * This method resets the number of points the player has.
	 */
	public void resetPoints() {
		// Set points to 0.
		points = 0;
		// Update pointsDisplay.
		pointsDisplay.setText(String.valueOf(points));
	}

	/**
	 * This method gets the number of points the player has.
	 * @return the number of points the player has
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * This method returns the timer of the player.
	 * @return the timer of the player
	 */
	public Clock getTimer() {
		return timer;
	}

	/**
	 * This method is called to start the player's turn.
	 */
	public void startTurn() {
		// Start timer.
		timer.start();
		// Show playerDisplay.
		playerDisplay.setIcon(playerTurnIndicator);
	}

	/**
	 * This method is called to end the player's turn.
	 */
	public void endTurn() {
		// Pause timer.
		timer.pause();
		// Hide playerDisplay.
		playerDisplay.setIcon(null);
	}
}