package boggle;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class IntroScreen extends JPanel {
	private JLabel introGif = new JLabel();
	private JLabel fact;
	private AudioInputStream introSound;
	private Clip introClip;
	private JLayeredPane layers = new JLayeredPane();
	private JPanel content = new JPanel();
	public IntroScreen(Boggle mainFrame) {
		GridBagConstraints c;

		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setBounds(0, 0, w, h);
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		ImageIcon x = new ImageIcon(getClass().getResource("assets/IntroGif.gif"));
		introGif.setIcon(new ImageIcon(x.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
		introGif.setBounds(0, 0, w, h);
		introGif.setBackground(Color.black);
		introGif.setVisible(true);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		layers.add(introGif, c);
		layers.setLayer(introGif, 0);

		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(w, h));

		fact = new JLabel(new ImageIcon(getClass().getResource("assets/fact.png")));
		fact.setMinimumSize(new Dimension((int) (0.8 * w), (int) (0.8 * h)));
		fact.setPreferredSize(new Dimension((int) (0.8 * w), (int) (0.8 * h)));
		fact.setBounds(0, 0, w, h);
		fact.setVisible(false);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets((int)(h * 0.55), 0, 0, 0);
		layers.add(fact, c);
		layers.setLayer(fact, 1);
		content.add(fact, c);

		try {
			introSound = AudioSystem.getAudioInputStream(getClass().getResource("assets/Opening.wav"));
			introClip = AudioSystem.getClip();
			introClip.open(introSound);
			introClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}

		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);
		this.add(layers, new GridBagConstraints());

		mainFrame.setContentPane(this);
		Timer timer = new Timer(6500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				introClip.stop();
				introClip.close();
				introGif.setVisible(false);
				fact.setVisible(false);
			}
		});
		timer.setRepeats(false);
		timer.start();
		Timer timer2 = new Timer(2500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				fact.setVisible(true);
			}
		});
		timer2.setRepeats(false);
		timer2.start();
	}
}