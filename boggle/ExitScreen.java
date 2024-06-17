/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * This class plays the exit animation and closes the game
 */
public class ExitScreen extends JPanel {
	// Exit animation
	private ImageIcon closingGif;
	// Exit background
	private JLabel background;
	// Sound effect of exit animation
	private AudioInputStream closingSfx;
	// Clip for closing sound effect
	private Clip closingClip;
	// Timer for exit animation life span
	private Timer exit;
	// Parent frame of the game
	private Boggle mainFrame;

	/**
	 * Constructor
	 * @param mainFrame
	 */
	public ExitScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		// Get the width and height of the frame
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));

		// Instantiate the closingGif as the exit animation
		closingGif = new ImageIcon(getClass().getResource("assets/Closing.gif"));
		background = new JLabel();
		// Scale the closingGif based on the width and height
		background.setIcon(new ImageIcon(closingGif.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		// Set background to black
		background.setBackground(Color.black);
		// Add the background to in the GridBagConstraints
		add(background, new GridBagConstraints());
	}

	/**
	 * This method starts the exit animation
	 */
	public void start() {
		try {
			// Get the exit animation sound effect
			closingSfx = AudioSystem.getAudioInputStream(getClass().getResource("assets/Closing.wav"));
			closingClip = AudioSystem.getClip();
			// Open sound as a clip
			closingClip.open(closingSfx);
		} catch (UnsupportedAudioFileException e) {
			// Handle if the audio file is incorrect
			System.err.println("Error: Invalid file type, unable to play sound effect");
			e.printStackTrace();
		} catch (IOException e) {
			// Handle if there is an issue reading the audio file
			System.err.println("Error: Unable to read sound file");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// Handle if the audio file is not found
			System.err.println("Error: No line to read");
			e.printStackTrace();
		}
		// Timer to close game after 4500 milliseconds
		exit = new Timer(0, e -> System.exit(0));
		exit.setInitialDelay(4500);
		exit.start();

		// Play exit sound
		closingClip.start();
	}
}