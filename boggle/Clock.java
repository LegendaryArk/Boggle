package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Clock {
	int timeRemaining = 0;
	int seconds = 0;
	int minutes = 0;
	boolean started = false;

	private JLabel timeLabel;

	String secStr = String.format("%02d", seconds);
	String minStr = String.format("%02d", minutes);

	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			timeRemaining -= 1000;
			minutes = (timeRemaining / 60000) % 60;
			seconds = (timeRemaining / 1000) % 60;
			secStr = String.format("%02d", seconds);
			minStr = String.format("%02d", minutes);
			timeLabel.setText(minStr + ":" + secStr);

			if (timeRemaining <= 0) {
				timeLabel.setForeground(Color.RED);
				timer.stop();
			}
		}
	});

	Clock(JLabel timeLabel, int startTime) {
		this.timeLabel = timeLabel;
		reset(startTime);
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
