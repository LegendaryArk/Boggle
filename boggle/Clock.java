/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class implements a clock in the game screen.
 */
public class Clock {
	// Time remaining in milliseconds.
	private int timeRemaining = 0;
	// Time remaining in seconds.
	private int seconds = 0;
	// Time remaining in minutes.
	private int minutes = 0;
	// Check if time has started.
	private boolean started;
	// Displays time.
	private JLabel timeLabel;
	// Clock format for seconds.
	private String secStr = String.format("%02d", seconds);
	// Clock format for minutes.
	private String minStr = String.format("%02d", minutes);

	private Board board;

	// Main control loop of the timer.
	private Timer timer = new Timer(1000, e -> {
		// Inline method (lambda).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

		// Decrease time for every 1000 milliseconds.
		timeRemaining -= 1000;

		// Calculate the timer in minutes and seconds.
		minutes = (timeRemaining / 60000) % 60;
		seconds = (timeRemaining / 1000) % 60;
		// Format and update the clock.
		secStr = String.format("%02d", seconds);
		minStr = String.format("%02d", minutes);
		timeLabel.setText(minStr + ":" + secStr);

		// Set time text to red if clock is less than or equal to 10 seconds.
		if (timeRemaining <= 10000) {
			timeLabel.setForeground(Color.RED);
		}
		// Reset board when clock is less than or equal to 0 seconds.
		if (timeRemaining <= 0) {
			board.clearBoard(0);
			board.switchTurn();
		}
	});

	/**
	 * Constructor
	 * @param timeLabel the label for the timer
	 * @param startTime the initial time
	 * @param board the board of the game
	 */
	public Clock(JLabel timeLabel, int startTime, Board board) {
		this.timeLabel = timeLabel;
		reset(startTime);

		this.board = board;

		started = false;
	}

	/**
	 * This method starts the clock.
	 */
	public void start() {
		timer.start();
		started = true;
	}

	/**
	 * This method pauses the clock.
	 */
	public void pause() {
		timer.stop();
		started = false;
	}

	/**
	 * This method increments the timer.
	 * @param increment
	 */
	public void increment(int increment) {
		// Increase time by the increment
		timeRemaining += increment;
		// Calculate the timer in minutes and seconds.
		minutes = (timeRemaining / 60000) % 60;
		seconds = (timeRemaining / 1000) % 60;
		// Format and update the clock
		minStr = String.format("%02d", minutes);
		secStr = String.format("%02d", seconds);
		timeLabel.setText(minStr + ":" + secStr);
	}

	/**
	 * This method resets the timer.
	 * @param time
	 */
	public void reset(int time) {
		pause();
		// Set the remaining time to the saved time
		timeRemaining = time;
		// Calculate the timer in minutes and seconds.
		minutes = (time / 60000) % 60;
		seconds = (time / 1000) % 60;
		// Format and update the clock
		minStr = String.format("%02d", minutes);
		secStr = String.format("%02d", seconds);
		timeLabel.setText(minStr + ":" + secStr);
	}
}