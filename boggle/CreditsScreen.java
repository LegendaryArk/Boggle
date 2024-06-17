/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

/**
 * This class displays the credit screen
 */
public class CreditsScreen extends JPanel {
	private JLabel creditsGif;
	private JLayeredPane layers;
	private JPanel content;

	private OptionButton returnBtn;
	private ImageIcon returnDefault;
	private ImageIcon returnHover;
	private ImageIcon returnPress;

	private Boggle mainFrame;

	public CreditsScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		
		//Background
		creditsGif = new JLabel();
		content = new JPanel();

		//LayeredPane
		layers = new JLayeredPane();


		//GridBagConstraints
		GridBagConstraints c;

		//Dimensions of the screen
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		//Background colour, location, and size.
		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		// Path to the background GIF.
		ImageIcon x = new ImageIcon(getClass().getResource("assets/Credits.gif"));

		creditsGif.setIcon(new ImageIcon(x.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		creditsGif.setBounds(0, 0, w, h);
		creditsGif.setBackground(Color.black);
		creditsGif.setVisible(true);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(creditsGif, c);
		layers.setLayer(creditsGif, 0);

		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(w, h));

		// Path to return button image.
		returnDefault = new ImageIcon(
				getClass().getResource("assets/ReturnBtnDefault.png"));

		// Path to return button when hovered image.
		returnHover = new ImageIcon(
				getClass().getResource("assets/ReturnBtnHover.png"));

		// Path to return button pressed image.
		returnPress = new ImageIcon(
				getClass().getResource("assets/ReturnBtnPress.png"));

		// Setting the return button size and location.
		returnBtn = new OptionButton(0.06 * w, 0.06 * w,
				returnDefault, returnHover, returnPress,
				e -> mainFrame.menuScreen());
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(
				(int) (0.04 * h), (int) (0.04 * h), 0,0);

		// Adding return button to content panel.
		content.add(returnBtn, c);

		// Adding 
		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);
		this.add(layers, new GridBagConstraints());
	}
}