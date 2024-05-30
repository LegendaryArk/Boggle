package boggle;

import javax.swing.*;
import java.awt.*;


public class MenuScreen extends JPanel{
	private JLayeredPane panel;
	private ImageIcon backgroundImage;
	private Boggle mainFrame;

	public MenuScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		this.setBounds(0, 0, mainFrame.getScreenWidth(), mainFrame.getScreenHeight());

	}
}
