package boggle;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IntroScreen extends JPanel {
	private JLabel introGif = new JLabel();
	private JTextPane fact;
	private AudioInputStream introSound;
	private Clip introClip;
	private JLayeredPane layers = new JLayeredPane();
	private JPanel content = new JPanel();

	private ArrayList<String> funFacts = new ArrayList<>();

	private Boggle mainFrame;

	public IntroScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		GridBagConstraints c;

		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		ImageIcon bg = new ImageIcon(getClass().getResource("assets/IntroGif.gif"));
		introGif.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		introGif.setBounds(0, 0, w, h);
		introGif.setBackground(Color.black);
		introGif.setVisible(true);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(introGif, c);
		layers.setLayer(introGif, 0);

		try {
			Scanner sc = new Scanner(new File("src/Boggle/boggle/resources/funFacts.txt"));
			while (sc.hasNextLine()) {
				funFacts.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error: Unable to find and load dictionary");
		}

		fact = new JTextPane();
		fact.setMinimumSize(new Dimension((int) (0.9 * w), (int) (0.15 * h)));
		fact.setPreferredSize(new Dimension((int) (0.9 * w), (int) (0.15 * h)));
		StyledDocument doc = fact.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		fact.setOpaque(false);
		fact.setFocusable(false);
		fact.setEditable(false);
		fact.setFont(new Font("MV Boli", Font.BOLD, 40));
		Random randFact = new Random();
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

		try {
			introSound = AudioSystem.getAudioInputStream(getClass().getResource("assets/Opening.wav"));
			introClip = AudioSystem.getClip();
			introClip.open(introSound);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.add(layers, new GridBagConstraints());

//		mainFrame.setContentPane(this);

	}

	public void start() {
		introClip.start();

		Timer timer = new Timer(0, e -> {
			introClip.stop();
			introClip.close();
			introGif.setVisible(false);
			fact.setVisible(false);
		});
		timer.setInitialDelay(6500);
		timer.setRepeats(false);
		timer.start();

		Timer timer2 = new Timer(0, e -> fact.setVisible(true));
		timer2.setInitialDelay(2500);
		timer2.setRepeats(false);
		timer2.start();

		Timer timer3 = new Timer(0, e -> mainFrame.menuScreen());
		timer3.setInitialDelay(6000);
		timer3.setRepeats(false);
		timer3.start();
	}
}