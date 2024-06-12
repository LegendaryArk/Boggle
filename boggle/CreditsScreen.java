package boggle;

import javax.swing.*;
import java.awt.*;

public class CreditsScreen extends JPanel {
	private JLabel creditsGif = new JLabel();
	private JPanel content = new JPanel();
	private JLayeredPane layers = new JLayeredPane();

	public CreditsScreen(Boggle mainFrame) {
		GridBagConstraints c;

		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		ImageIcon x = new ImageIcon(getClass().getResource("assets/CreditsGif.gif"));
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

		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);
		this.add(layers, new GridBagConstraints());

		mainFrame.setContentPane(this);

		Timer timer = new Timer(10000, actionEvent -> creditsGif.setVisible(false));
		timer.setRepeats(false);
		timer.start();
	}
}