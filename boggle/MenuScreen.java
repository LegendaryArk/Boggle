package boggle;

import javax.swing.*;
import java.awt.*;


public class MenuScreen extends JPanel{
	private JLayeredPane panel;
	private ImageIcon backgroundImage;
	private Boggle mainFrame;
	//Dimensions.
	private int w = mainFrame.getScreenWidth();
	private int l = mainFrame.getScreenHeight();
	//Images

	public MenuScreen(Boggle mainFrame) {

		//Main Frame
		this.mainFrame = mainFrame;
		this.setBounds(0, 0, w, l);

		//

	}
}
