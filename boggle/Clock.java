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
	private int millisecondsRemaining;
	// Time remaining in seconds.
	private int secondsRemaining;
	// Time remaining in minutes.
	private int minutesRemaining;
	// Displays time.
	private JLabel timeLabel;
	// Clock format for seconds.
	private String secondString;
	// Clock format for minutes.
	private String minuteString;
	// Board
	private Board board;
	// Main control loop of the timer.
	private Timer timer;

	/**
	 * Constructor
	 * @param timeLabel the label for the timer
	 * @param startTime the initial time
	 * @param board the board of the game
	 */
	public Clock(JLabel timeLabel, int startTime, Board board) {
		// Initialize variables.
		millisecondsRemaining = 0;
		secondsRemaining = 0;
		minutesRemaining = 0;
		secondString = String.format("%02d", secondsRemaining);
		minuteString = String.format("%02d", minutesRemaining);

		// Instantiate timer.
		timer = new Timer(1000, e -> {
			// Inline method (lambda).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Decrease time for every 1000 milliseconds.
			millisecondsRemaining -= 1000;

			// Calculate the timer in minutes and seconds.
			minutesRemaining = (millisecondsRemaining / 60000) % 60;
			secondsRemaining = (millisecondsRemaining / 1000) % 60;
			// Format and update the clock.
			secondString = String.format("%02d", secondsRemaining);
			minuteString = String.format("%02d", minutesRemaining);
			timeLabel.setText(minuteString + ":" + secondString);

			// Set time text to red if clock is less than or equal to 10 seconds.
			if (millisecondsRemaining <= 10000) {
				timeLabel.setForeground(Color.RED);
			}
			// Reset board when clock is less than or equal to 0 seconds.
			if (millisecondsRemaining <= 0) {
				board.clearBoard(0);
				board.switchTurn();
			}
		});

		this.timeLabel = timeLabel;
		reset(startTime);
		this.board = board;
	}

	/**
	 * This method starts the clock.
	 */
	public void start() {
		timer.start();
	}

	/**
	 * This method pauses the clock.
	 */
	public void pause() {
		timer.stop();
	}

	/**
	 * This method increments the timer.
	 * @param increment The amount of time to increment.
	 */
	public void increment(int increment) {
		// Increase time by the increment
		millisecondsRemaining += increment;
		// Calculate the timer in minutes and seconds.
		minutesRemaining = (millisecondsRemaining / 60000) % 60;
		secondsRemaining = (millisecondsRemaining / 1000) % 60;
		// Format and update the clock
		minuteString = String.format("%02d", minutesRemaining);
		secondString = String.format("%02d", secondsRemaining);
		timeLabel.setText(minuteString + ":" + secondString);
	}

	/**
	 * This method resets the timer.
	 * @param time The time to reset the timer to.
	 */
	public void reset(int time) {
		pause();
		// Set the remaining time to the saved time
		millisecondsRemaining = time;
		// Calculate the timer in minutes and seconds.
		minutesRemaining = (time / 60000) % 60;
		secondsRemaining = (time / 1000) % 60;
		// Format and update the clock
		minuteString = String.format("%02d", minutesRemaining);
		secondString = String.format("%02d", secondsRemaining);
		timeLabel.setText(minuteString + ":" + secondString);
	}
}