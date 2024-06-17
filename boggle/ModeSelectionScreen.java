/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import java.awt.*;

public class ModeSelectionScreen extends JPanel {
	private final JLayeredPane layers = new JLayeredPane();
	private final ImageIcon bg = new ImageIcon(getClass().getResource("assets/GameOptionScreenBg.png"));
	private final JLabel background = new JLabel();
	private final JPanel content = new JPanel();

	private final OptionButton plrVsPlrBtn;
	private final ImageIcon plrVsPlrDefault = new ImageIcon(getClass().getResource("assets/PlrVsPlrBtnDefault.png"));
	private final ImageIcon plrVsPlrHover = new ImageIcon(getClass().getResource("assets/PlrVsPlrBtnHover.png"));
	private final ImageIcon plrVsPlrPress = new ImageIcon(getClass().getResource("assets/PlrVsPlrBtnPress.png"));

	private final OptionButton plrVsAIBtn;
	private final ImageIcon plrVsAIDefault = new ImageIcon(getClass().getResource("assets/PlrVsAIBtnDefault.png"));
	private final ImageIcon plrVsAIHover = new ImageIcon(getClass().getResource("assets/PlrVsAIBtnHover.png"));
	private final ImageIcon plrVsAIPress = new ImageIcon(getClass().getResource("assets/PlrVsAIBtnPress.png"));

	private final Boggle mainFrame;

	public ModeSelectionScreen(Boggle mainFrame) {
		this.mainFrame = mainFrame;
		int w = mainFrame.getScreenWidth(), h = mainFrame.getScreenHeight();

		GridBagConstraints c;

		this.setBackground(Color.black);
		this.setLayout(new GridBagLayout());
		this.setPreferredSize(new Dimension(w, h));

		layers.setBackground(Color.black);
		layers.setLayout(new GridBagLayout());
		layers.setPreferredSize(new Dimension(w, h));

		background.setIcon(new ImageIcon(bg.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH)));
		background.setBackground(Color.black);
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		layers.add(background, c);
		layers.setLayer(background, 0);

		content.setLayout(new GridBagLayout());
		content.setOpaque(false);
		content.setPreferredSize(new Dimension(w, h));

		plrVsPlrBtn = new OptionButton(0.3 * w, 0.2 * h, plrVsPlrDefault, plrVsPlrHover, plrVsPlrPress, e -> {
			mainFrame.getMenuScreen().stopBgm();
			mainFrame.gameScreen(false);
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets((int) (0.35 * h), 0, 0, 0);
		c.weightx = 1;
		c.weighty = 1;
		content.add(plrVsPlrBtn, c);

		plrVsAIBtn = new OptionButton(0.3 * w, 0.2 * h, plrVsAIDefault, plrVsAIHover, plrVsAIPress, e -> {
			mainFrame.getMenuScreen().stopBgm();
			mainFrame.gameScreen(true);
		});
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 0, (int) (0.2 * h), 0);
		c.weightx = 1;
		c.weighty = 1;
		content.add(plrVsAIBtn, c);

		layers.add(content, new GridBagConstraints());
		layers.setLayer(content, 1);

		this.add(layers, new GridBagConstraints());
	}
}
