/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

public class Player {
	protected int points;
	protected Clock timer;

	protected ImageIcon plrTurnInd = new ImageIcon(getClass().getResource("assets/PlrTurnIndicator.png"));
	protected JLabel plrDisplay;
	protected JLabel ptsDisplay;

	public Player(JLabel plrDisplay, JLabel ptsDisplay, Clock timer) {
		this.plrDisplay = plrDisplay;
		this.ptsDisplay = ptsDisplay;
		plrTurnInd = new ImageIcon(plrTurnInd.getImage().getScaledInstance(plrDisplay.getMinimumSize().width,plrDisplay.getMinimumSize().height, Image.SCALE_SMOOTH));

		points = 0;
		this.timer = timer;

		ptsDisplay.setText(String.valueOf(points));
	}

	public void addPoints(int increment) {
		points += increment;
		ptsDisplay.setText(String.valueOf(points));
	}

	public void resetPoints() {
		points = 0;
		ptsDisplay.setText(String.valueOf(points));
	}

	public int getPoints() {
		return points;
	}

	public Clock getTimer() {
		return timer;
	}

	public void startTurn() {
		timer.start();

		plrDisplay.setIcon(plrTurnInd);
	}

	public void endTurn() {
		timer.pause();

		plrDisplay.setIcon(null);
	}
}