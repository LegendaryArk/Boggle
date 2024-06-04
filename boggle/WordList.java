package boggle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;

public class WordList {
	JPanel bg;
	JScrollPane scroll;
	ArrayList<JLabel> words, pts;

	Boggle mainFrame;

	int w, h;

	public WordList(Boggle mainFrame, JPanel bg, JScrollPane scrollPane) {
		this.bg = bg;
		this.scroll = scrollPane;

		this.mainFrame = mainFrame;
		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();

		words = new ArrayList<>();
		pts = new ArrayList<>();

		scroll.getVerticalScrollBar().setUI(new ScrollBarUI());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension((int) (0.007 * w), (int) (0.802 * h)));
	}

	public void addWord(String word, int pt) {
		GridBagConstraints c = new GridBagConstraints();

		JLabel wordLabel = new JLabel(word);
		wordLabel.setMinimumSize(new Dimension((int) (0.18 * w), (int) (0.02 * h)));
		wordLabel.setPreferredSize(new Dimension((int) (0.18 * w), (int) (0.02 * h)));
		c.gridx = 0;
		c.gridy = 1;
		bg.add(wordLabel, c);
		words.add(wordLabel);

		JLabel ptLabel = new JLabel(String.valueOf(pt));
		wordLabel.setMinimumSize(new Dimension((int) (0.05475 * w), (int) (0.02 * h)));
		wordLabel.setPreferredSize(new Dimension((int) (0.05475 * w), (int) (0.02 * h)));
		bg.add(wordLabel);
		pts.add(ptLabel);
	}

	private static class ScrollBarUI extends BasicScrollBarUI {
		public ScrollBarUI() {}

		@Override
		protected JButton createIncreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension();
				}
			};
		}
		@Override
		protected JButton createDecreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension();
				}
			};
		}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle r) {
			Graphics2D g2 = (Graphics2D) g.create();

			Color color;
			JScrollBar sb = (JScrollBar)c;
			if(!sb.isEnabled() || r.width>r.height) {
				return;
			} else if(isDragging) {
				color = new Color(143, 143, 143);
			} else if(isThumbRollover()) {
				color = new Color(166, 166, 166);
			} else {
				color = new Color(200, 200, 200);
			}

			g2.setPaint(color);
			g2.fillRoundRect(r.x, r.y, r.width, r.height,5,5);
			g2.dispose();
		}

		@Override
		protected void setThumbBounds(int x, int y, int width, int height) {
			super.setThumbBounds(x, y, width, height);
			scrollbar.repaint();
		}
	}
}