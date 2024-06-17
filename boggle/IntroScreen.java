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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class plays the intro screen then switches to the menu
 */
public class IntroScreen extends JPanel {
	// Intro animation.
	private JLabel introGif = new JLabel();
	// Text to display a random fact.
	private  JTextPane fact;
	// Sound effect of intro animation.
	private AudioInputStream introSound;
	// Clip for intro sound effect.
	private Clip introClip;
	// Layered pane to layer the animation and the fact.
	private JLayeredPane layers = new JLayeredPane();
	// Arraylist to store facts.
	private ArrayList<String> funFacts = new ArrayList<>();
	// Parent frame of the game.
	private Boggle mainFrame;

	/**
	 * Constructor
	 * @param mainFrame main frame of the game
	 */
	public IntroScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		GridBagConstraints c;

		// Get the width and height of the frame.
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		// Set the colour, layout, bounds and dimensions of the background.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		// Set the colour, layout, bounds and dimensions of the layered pane.
		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		// Set the intro gif to the background.
		ImageIcon bg = new ImageIcon(getClass().getResource("assets/IntroGif.gif"));
		introGif.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		// Set bounds, colour and visibility of the intro gif.
		introGif.setBounds(0, 0, w, h);
		introGif.setBackground(Color.black);
		introGif.setVisible(true);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(introGif, c);
		layers.setLayer(introGif, 0);

		// Read the funFacts text file
		try {
			Scanner sc = new Scanner(new File("src/Boggle/boggle/resources/funFacts.txt"));
			while (sc.hasNextLine()) {
				// Add facts to the ArrayList
				funFacts.add(sc.nextLine());
			}
			// Close scanner
			sc.close();
		} catch (FileNotFoundException e) {
			// Handle if the file is not found
			e.printStackTrace();
			System.err.println("Error: Unable to find and load dictionary");
		}

		// Set the pane for the fun fact text
		fact = new JTextPane();
		// Set dimensions and preferred size of the fact
		fact.setMinimumSize(new Dimension((int) (0.9 * w), (int) (0.15 * h)));
		fact.setPreferredSize(new Dimension((int) (0.9 * w), (int) (0.15 * h)));
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
		fact.setText(funFacts.get(randFact.nextInt(funFacts.size())));
		fact.setVisible(false);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(0, 0, (int) (0.18 * h), 0);
		layers.add(fact, c);
		layers.setLayer(fact, 1);

		// Add layered panes to the panel
		this.add(layers, new GridBagConstraints());

		// Play the intro sound
		try {
			// Get the exit animation sound effect
			introSound = AudioSystem.getAudioInputStream(getClass().getResource("assets/Opening.wav"));
			introClip = AudioSystem.getClip();
			// Open sound as a clip
			introClip.open(introSound);
		} catch (Exception e) {
			// Handle if audio file is unable to open
			e.printStackTrace();
			System.err.println("Error: Unable to play opening sound");
		}
	}

	/**
	 * This method starts the intro animation sound effect
	 */
	public void start() {
		// Start the intro animation clip
		introClip.start();

		// Timer to close intro animation after 4800 milliseconds
		Timer timer = new Timer(0, e -> {
			// Stop and close the intro clip after timer reaches 0 milliseconds
			introClip.stop();
			introClip.close();
			// Switch frame to the menu screen
			mainFrame.menuScreen();
		});
		// Set timer for 4800 milliseconds
		timer.setInitialDelay(4800);
		timer.setRepeats(false);
		// Start the timer
		timer.start();

		// Timer to display the fact after 2500 milliseconds
		Timer timer2 = new Timer(0, e -> fact.setVisible(true));
		// Set timer for 2500 milliseconds
		timer2.setInitialDelay(2500);
		timer2.setRepeats(false);
		// Start the timer
		timer2.start();
	}
}