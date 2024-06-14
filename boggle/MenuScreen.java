/**
 * @author brian.cheang
 * 2024.06.12
 */
package boggle;

/**
 * This class contains the panel for the menu screen.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuScreen extends JPanel implements MouseListener {

	//GridBagConstraints
	private GridBagConstraints c = new GridBagConstraints();

	//Layered Pane
	private JLayeredPane pane = new JLayeredPane();

	//Background
	private JLabel background = new JLabel();
	private Boggle mainFrame;

	// Dimensions.
	private int w;
	private int h;
	// Images
	private ImageIcon backgroundImage = new ImageIcon(getClass().getResource("assets/MenuScreenBg.png"));

	private ImageIcon playButton = new ImageIcon(getClass().getResource("assets/PlayButton.png"));
	private ImageIcon playButtonHl = new ImageIcon(getClass().getResource("assets/PlayButtonHover.png"));

	private ImageIcon guideButton = new ImageIcon(getClass().getResource("assets/GuideButton.png"));
	private ImageIcon guideButtonHl = new ImageIcon(getClass().getResource("assets/GuideButtonHover.png"));

	private ImageIcon settingsButton = new ImageIcon(getClass().getResource("assets/SettingsButton.png"));
	private ImageIcon settingsButtonHl = new ImageIcon(getClass().getResource("assets/SettingsButtonHover.png"));

	private ImageIcon creditsButton = new ImageIcon(getClass().getResource("assets/CreditsButton.png"));
	private ImageIcon creditsButtonHl = new ImageIcon(getClass().getResource("assets/CreditsButtonHover.png"));

	private ImageIcon exitButton = new ImageIcon(getClass().getResource("assets/ExitButton.png"));
	private ImageIcon exitButtonHl = new ImageIcon(getClass().getResource("assets/ExitButtonHover.png"));

	// Button
	private JLabel playBtn;
	private JLabel guideBtn;
	private JLabel settingsBtn;
	private JLabel creditsBtn;
	private JLabel exitBtn;

	/**
	 * Constructor the main panel which contains all the objects.
	 *
	 * @param mainFrame, class Boggle.
	 */

	public MenuScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;

		/**
		 * Images and Labels for the title and background.
		 */
		ImageIcon titleGif;
		JLabel backgroundLabel;
		JLabel title;

		titleGif = new ImageIcon(getClass().getResource("assets/Title.gif"));

		/**
		 * The dimensions of the players of screen.
		 */
		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();

		//Layered Pane
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		pane.setBounds(0, 0, w, h);
		pane.setLayout(new GridBagLayout());

		//Background
		background.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBounds(0, 0, w, h);
		background.setBackground(Color.black);
		background.setVisible(true);
		background.setPreferredSize(new Dimension(w, h));
		background.setMinimumSize(new Dimension(w, h));
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 4;
		pane.add(background, c);
		pane.setLayer(background, 0); // Add background to layer 0

		//Boggle logo
		title = new JLabel();
		title.setIcon(new ImageIcon(titleGif.getImage().getScaledInstance((int)(0.4 * w), (int) (0.4 * h), Image.SCALE_DEFAULT)));
		title.setMinimumSize(new Dimension((int) (0.4 * w), (int)(0.4 * h)));
		title.setPreferredSize(new Dimension((int) (0.4 * w), (int)(0.4 * h)));
		title.setOpaque(false);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,(int) (0.45 * w),(int)(0.5 *h),0);
		c.weightx = 1;
		c.weighty = 1;
		pane.add(title, c);
		pane.setLayer(title, 1); // Add title to layer 1

		//Buttons
		playBtn = createButton(playButton, 0,(int) (0.45 * w),(int)(0.2 *h),0 ,(int)(0.12 * w), (int)(0.08 * h));
		guideBtn = createButton(guideButton, 0,(int) (0.45 * w),0,0 ,(int)(0.15 * w), (int)(0.08 * h));
		settingsBtn = createButton(settingsButton, (int) (0.2 * h),(int) (0.45 * w),0,0,(int)(0.24 * w), (int)(0.08 * h));
		creditsBtn = createButton(creditsButton, (int) (0.4 * h),(int) (0.45 * w),0,0,(int)(0.21 * w), (int)(0.08 * h));
		exitBtn = createButton(exitButton, (int) (0.6 * h),(int) (0.45 * w),0,0,(int)(0.12 * w), (int)(0.08 * h));

		this.add(pane, new GridBagConstraints());
		mainFrame.setContentPane(this);
	}

	/**
	 *
	 * @param image path to image.
	 * @param top how much to move the image up.
	 * @param left how much to move the image to the left.
	 * @param bottom how much to move the image down.
	 * @param right how much to move the image to the right.
	 * @param sizex the width.
	 * @param sizey the height.
	 * @return the button, JLabel.
	 */
	private JLabel createButton (ImageIcon image, int top, int left, int bottom, int right, int sizex, int sizey){
		//The return object.
		JLabel btn = new JLabel();

		//Setting the ImageIcon, dimensions, and position of button.
		btn.setIcon(new ImageIcon(image.getImage().getScaledInstance(sizex, sizey, Image.SCALE_SMOOTH)));
		btn.setBounds((int)(0.667 * w), (int) (0.333*h) , (int)(0.1*w), (int)(0.05 * h));
		btn.addMouseListener(this);

		//GridBagLayout
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(top,left,bottom,right);
		c.weightx = 1;
		c.weighty = 1;
		pane.add(btn, c);
		pane.setLayer(btn, 2);// Add play button to layer 2

		//Return Statement.
		return btn;
	}

	/**
	 * This method handles mouse clicks
	 * @param e the event to be processed
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// Check which button did the player click.
		playBtn.setIcon(new ImageIcon(playButton.getImage().getScaledInstance((int)(w *0.12), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		guideBtn.setIcon(new ImageIcon(guideButton.getImage().getScaledInstance((int)(w *0.15), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		settingsBtn.setIcon(new ImageIcon(settingsButton.getImage().getScaledInstance((int)(w *0.24), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		creditsBtn.setIcon(new ImageIcon(creditsButton.getImage().getScaledInstance((int)(w *0.21), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		exitBtn.setIcon(new ImageIcon(exitButton.getImage().getScaledInstance((int)(w *0.12), (int)(h * 0.08), Image.SCALE_SMOOTH)));


		if (e.getSource() == playBtn) mainFrame.gameScreen();
		else if (e.getSource() == guideBtn) mainFrame.gameScreen();
		else if (e.getSource() == settingsBtn) mainFrame.gameScreen();
		else if (e.getSource() == creditsBtn) mainFrame.creditsScreen();
		else if (e.getSource() == exitBtn) System.exit(0);
	}
	/**
	 * This method handles the mouse being pressed.
	 * @param e the event to be processed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Handle mouse press
	}

	/**
	 * This method handles the mouse is released after being pressed.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// Handle mouse release
	}

	/**
	 * This method handles the event when the mouse enters an object.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButtonHl.getImage().getScaledInstance((int)(w *0.12), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == guideBtn) {
			guideBtn.setIcon(new ImageIcon(guideButtonHl.getImage().getScaledInstance((int)(w *0.15), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == settingsBtn) {
			settingsBtn.setIcon(new ImageIcon(settingsButtonHl.getImage().getScaledInstance((int)(w *0.24), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == creditsBtn) {
			creditsBtn.setIcon(new ImageIcon(creditsButtonHl.getImage().getScaledInstance((int)(w *0.21), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == exitBtn) {
			exitBtn.setIcon(new ImageIcon(exitButtonHl.getImage().getScaledInstance((int)(w *0.12), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}

	}

	/**
	 * This method handles the event when the mouse exits an object.
	 * @param e the event to be processed
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButton.getImage().getScaledInstance((int)(w *0.12), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == guideBtn) {
			guideBtn.setIcon(new ImageIcon(guideButton.getImage().getScaledInstance((int)(w *0.15), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == settingsBtn) {
			settingsBtn.setIcon(new ImageIcon(settingsButton.getImage().getScaledInstance((int)(w *0.24), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == creditsBtn) {
			creditsBtn.setIcon(new ImageIcon(creditsButton.getImage().getScaledInstance((int)(w *0.21), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}
		if (e.getSource() == exitBtn) {
			exitBtn.setIcon(new ImageIcon(exitButton.getImage().getScaledInstance((int)(w *0.12), (int)(h * 0.08), Image.SCALE_SMOOTH)));
		}

	}
}
