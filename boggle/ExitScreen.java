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
	// Exit animation.
	private ImageIcon exitAnimation;
	// Exit background.
	private JLabel background;
	// Sound effect of exit animation.
	private AudioInputStream exitSoundEffect;
	// Clip for exit sound effect.
	private Clip exitClip;
	// Timer for exit animation life span.
	private Timer exitTimer;

	/**
	 * Constructor
	 * @param mainFrame
	 */
	public ExitScreen(Boggle mainFrame) {

		// Get the width and height of the screen.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		this.setBackground(Color.BLACK);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(width, height));

		// Instantiate the exitAnimation as the exit animation.
		exitAnimation = new ImageIcon(
				getClass().getResource("assets/Closing.gif"));
		background = new JLabel();
		// Resize the exitAnimation based on the width and height.
		background.setIcon(new ImageIcon(exitAnimation.getImage()
				.getScaledInstance(width, height, Image.SCALE_DEFAULT)));
		// Set background to black
		background.setBackground(Color.BLACK);
		// Add the background to in the GridBagConstraints.
		add(background, new GridBagConstraints());
	}

	/**
	 * This method starts the exit animation.
	 */
	public void start() {
		try {
			// Get the exit animation sound effect.
			exitSoundEffect = AudioSystem.getAudioInputStream(
					getClass().getResource("assets/Closing.wav"));
			exitClip = AudioSystem.getClip();
			// Open sound as a clip.
			exitClip.open(exitSoundEffect);
		} catch (UnsupportedAudioFileException e) {
			// Handle if the audio file is incorrect.
			System.err.println("Error: Invalid file type," +
					"unable to play sound effect");
			e.printStackTrace();
		} catch (IOException e) {
			// Handle if there is an issue reading the audio file.
			System.err.println("Error: Unable to read sound file");
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// Handle if the audio file is not found.
			System.err.println("Error: No line to read");
			e.printStackTrace();
		}
		// Timer to close game after 4.5 seconds.
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		exitTimer = new Timer(0, e -> System.exit(0));
		exitTimer.setInitialDelay(4500); // Initial delay of 4.5 seconds.
		exitTimer.start();

		// Play exit sound.
		exitClip.start();
	}
}