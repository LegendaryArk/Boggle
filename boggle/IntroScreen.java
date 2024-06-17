/**
 * @author jack.yuan
 * @author noah.sun
 * 2024.05.31
 */

package boggle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class plays the intro screen then switches to the menu
 */
public class IntroScreen extends JPanel {
	// Intro animation.
	private JLabel introAnimation;
	// Text to display a random fact.
	private JTextPane fact;
	// Sound effect of intro animation.
	private AudioInputStream introSound;
	// Clip for intro sound effect.
	private Clip introClip;
	// Layered pane to layer the animation and the fact.
	private JLayeredPane layeredPane;
	// Arraylist to store facts.
	private ArrayList<String> facts;
	// Parent frame of the game.
	private Boggle mainFrame;

	/**
	 * Constructor
	 * @param mainFrame main frame of the game
	 */
	public IntroScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		GridBagConstraints constraints;

		// Get the width and height of the frame.
		int width = mainFrame.getScreenWidth();
		int height = mainFrame.getScreenHeight();

		// Set the colour, layout, bounds and dimensions of the background.
		this.setBackground(Color.BLACK);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, width, height);
		this.setPreferredSize(new Dimension(width, height));

		// Set the colour, layout, bounds and dimensions of the layered pane.
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.BLACK);
		layeredPane.setLayout(new GridBagLayout());
		layeredPane.setPreferredSize(new Dimension(width, height));

		// Set the intro gif to the background.
		ImageIcon bg = new ImageIcon(
				getClass().getResource("assets/IntroGif.gif"));
		introAnimation = new JLabel();
		introAnimation.setIcon(new ImageIcon(
				bg.getImage().getScaledInstance(width, height,
						Image.SCALE_DEFAULT)));
		// Set colour of the intro gif.
		introAnimation.setBackground(Color.BLACK);
		constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		layeredPane.add(introAnimation, constraints);
		layeredPane.setLayer(introAnimation, 0);

		// Read the facts text file.
		facts = new ArrayList<>();
		Scanner sc = new Scanner(getClass()
				.getResourceAsStream("resources/funFacts.txt"));
		while (sc.hasNextLine()) {
			// Add facts to the ArrayList
			facts.add(sc.nextLine());
		}
		// Close scanner
		sc.close();

		// Set the pane for the fun fact text
		fact = new JTextPane();
		// Set dimensions and preferred size of the fact
		fact.setMinimumSize(new Dimension((int) (0.9 * width),
				(int) (0.15 * height)));
		fact.setPreferredSize(new Dimension((int) (0.9 * width),
				(int) (0.15 * height)));
		// Change attributes of the text.
		StyledDocument doc = fact.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		fact.setOpaque(false);
		fact.setFocusable(false);
		fact.setEditable(false);
		fact.setFont(new Font("MV Boli", Font.BOLD, 40));
		Random randFact = new Random();
		// Randomize and display the random fact.
		fact.setText(facts.get(randFact.nextInt(facts.size())));
		fact.setVisible(false);
		constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.insets = new Insets(0, 0, (int) (0.18 * height), 0);
		layeredPane.add(fact, constraints);
		layeredPane.setLayer(fact, 1);

		// Add layered panes to the panel
		this.add(layeredPane, new GridBagConstraints());

		// Play the intro sound
		try {
			// Get the exit animation sound effect
			introSound = AudioSystem.getAudioInputStream(
					getClass().getResource("assets/Opening.wav"));
			introClip = AudioSystem.getClip();
			// Open sound as a clip
			introClip.open(introSound);
		} catch (Exception e) {
			// Handle if audio file is unable to open
			System.err.println("Error: Unable to play opening sound");
			e.printStackTrace();
		}
	}

	/**
	 * This method starts the intro animation sound effect
	 */
	public void start() {
		// Start the intro animation clip
		introClip.start();

		// Timer to close intro animation after 4.8 seconds
		Timer timer = new Timer(0, e -> {
			// Inline method (lambda expressions).
			// https://www.geeksforgeeks.org/lambda-expressions-java-8/.

			// Stop and close the intro clip
			introClip.stop();
			introClip.close();
			// Switch frame to the menu screen
			mainFrame.menuScreen();
		});
		// Set timer for 4.8 seconds
		timer.setInitialDelay(4800);
		timer.setRepeats(false);
		// Start the timer
		timer.start();

		// Timer to display the fact after 2.5 seconds
		// Inline method (lambda expressions).
		// https://www.geeksforgeeks.org/lambda-expressions-java-8/.
		Timer timer2 = new Timer(0, e -> fact.setVisible(true));
		// Set timer for 2500 milliseconds
		timer2.setInitialDelay(2500);
		timer2.setRepeats(false);
		// Start the timer
		timer2.start();
	}
}