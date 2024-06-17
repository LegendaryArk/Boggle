/**
 * @author noah.sun
 * 2024.05.31
 */

package boggle;

import java.awt.event.ActionEvent;

/**
 * Interface for Clicking Buttons
 *
 * Interfaces: https://www.geeksforgeeks.org/interfaces-in-java/
 */
public interface ButtonClicked {
	/**
	 * Function for when the button is clicked.
	 *
	 * @param e The component that was clicked
	 */
	void clicked(ActionEvent e);
}