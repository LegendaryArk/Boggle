package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuScreen extends JPanel implements MouseListener {
	private JLayeredPane pane = new JLayeredPane();
	private JLabel background = new JLabel();
	private Boggle mainFrame;
	// Dimensions.
	private int w;
	private int h;
	// Images
	private ImageIcon backgroundImage = new ImageIcon(getClass().getResource("assets/MenuScreenBg.png"));
	private ImageIcon playButton = new ImageIcon(getClass().getResource("assets/PlayButton.png"));
	private ImageIcon playButtonHl = new ImageIcon(getClass().getResource("assets/PlayButtonHover.png"));
	// Button
	private JLabel playBtn;

	public MenuScreen(Boggle mainFrame) {
		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();

		this.setBackground(Color.yellow);
		this.setLayout(null);
		this.setBounds(0, 0, w, h);

		pane.setBounds(0, 0, w, h);

		background.setIcon(new ImageIcon(backgroundImage.getImage().getScaledInstance(w,h,Image.SCALE_SMOOTH)));
		background.setBounds(0,0,w,h);
		background.setBackground(Color.black);
		background.setVisible(true);

		pane.add(background, 0);
		pane.setVisible(true);
		this.add(pane);
		mainFrame.setContentPane(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Handle mouse click
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Handle mouse press
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Handle mouse release
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButtonHl.getImage().getScaledInstance((w / 10), (h / 20), Image.SCALE_SMOOTH)));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButton.getImage().getScaledInstance((w / 10), (h / 20), Image.SCALE_SMOOTH)));
		}
	}
}
