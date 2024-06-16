package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock {
	int timeRemaining = 0; // In ms
	int seconds = 0;
	int minutes = 0;
	boolean started = false;

	private JLabel timeLabel;

	private String secStr = String.format("%02d", seconds);
	private String minStr = String.format("%02d", minutes);

	private Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			timeRemaining -= 1000;
			minutes = (timeRemaining / 60000) % 60;
			seconds = (timeRemaining / 1000) % 60;
			secStr = String.format("%02d", seconds);
			minStr = String.format("%02d", minutes);
			timeLabel.setText(minStr + ":" + secStr);

			if (timeRemaining <= 10000) {
				timeLabel.setForeground(Color.RED);
			}
			if (timeRemaining <= 0) {
				board.clearBoard(0);
				board.switchTurn();
			}
		}
	});

	private Board board;

	Clock(JLabel timeLabel, int startTime, Board board) {
		this.timeLabel = timeLabel;
		reset(startTime);

		this.board = board;
	}

	void start() {
		timer.start();
		started = true;
	}
	void pause() {
		timer.stop();
		started = false;
	}
	void increment(int increment) {
		timeRemaining += increment;
		minutes = (timeRemaining / 60000) % 60;
		seconds = (timeRemaining / 1000) % 60;
		minStr = String.format("%02d", minutes);
		secStr = String.format("%02d", seconds);
		timeLabel.setText(minStr + ":" + secStr);
	}
	void reset(int time) {
		pause();
		timeRemaining = time;
		minutes = (time / 60000) % 60;
		seconds = (time / 1000) % 60;
		minStr = String.format("%02d", minutes);
		secStr = String.format("%02d", seconds);
		timeLabel.setText(minStr + ":" + secStr);
	}
}
