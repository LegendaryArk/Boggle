package boggle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class MenuScreen extends JPanel implements MouseListener{
	private JLayeredPane panel;
	private ImageIcon backgroundImage;
	private Boggle mainFrame;
	//Dimensions.
	private int w;
	private int h;
	//Images
	private ImageIcon playButton = new ImageIcon(getClass().getResource("assets/PlayButton.png"));
	private ImageIcon playButtonHl = new ImageIcon(getClass().getResource("assets/PlayButtonHover.png"));
	//Button
	private JLabel playBtn;


	public MenuScreen(Boggle mainFrame) {
		//Main Frame
		this.mainFrame = mainFrame;
		panel = new JLayeredPane();

		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();
		this.setBounds(0, 0, w, h);

		//Background
		ImageIcon backgroundImage = new ImageIcon(getClass().getResource("assets/MenuScreenBg.png"));
		JLabel backgroundLabel = new JLabel();

		backgroundLabel.setIcon(new ImageIcon (backgroundImage.getImage().getScaledInstance(w,h,Image.SCALE_SMOOTH)));
		backgroundLabel.setBounds(0,0,w,h);
		backgroundLabel.setOpaque(true);

		//Title
		ImageIcon titleGif = new ImageIcon(getClass().getResource("assets/Title.gif"));
		JLabel title = new JLabel();

		title.setIcon(new ImageIcon(titleGif.getImage().getScaledInstance(((2*w)/5),(2*h/5),Image.SCALE_DEFAULT)));
		title.setBounds(w/2,h/64,(2*w/5), (2*h/5));
		title.setOpaque(false);

		//Play Button
		playBtn = new JLabel();
		playBtn.setIcon(new ImageIcon(playButton.getImage().getScaledInstance((w/10),(h/20),Image.SCALE_SMOOTH)));
		playBtn.setBounds(2*w/3,h/3,(w/10),(h/20));
		playBtn.addMouseListener(this);

		//Implimentation
		mainFrame.setContentPane(this);

		this.add(panel);
		panel.add(playBtn);
		panel.add(title);
		panel.add(backgroundLabel);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButtonHl.getImage().getScaledInstance((w/10),(h/20),Image.SCALE_SMOOTH)));
		}

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == playBtn) {
			playBtn.setIcon(new ImageIcon(playButton.getImage().getScaledInstance((w/10),(h/20),Image.SCALE_SMOOTH)));
		}
	}
}
