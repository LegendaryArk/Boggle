package boggle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WordList {
	JPanel bg;
	JScrollPane scroll;
	DefaultTableModel data;
	JTable words;

	Boggle mainFrame;

	int w, h;

	public WordList(Boggle mainFrame, JPanel bg, JScrollPane scrollPane) {
		this.bg = bg;
		this.scroll = scrollPane;

		this.mainFrame = mainFrame;
		w = mainFrame.getScreenWidth();
		h = mainFrame.getScreenHeight();

		data = new DefaultTableModel();
		data.addColumn("Words");
		data.addColumn("Points");

		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setOpaque(false);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		words = new JTable();
		words.setDefaultRenderer(Object.class, renderer);
		words.setModel(data);
		words.setFont(new Font("Calibri", Font.PLAIN, 25));
		words.setRowHeight(30);
		words.setTableHeader(null);
		words.setMinimumSize(bg.getMinimumSize());
		words.setPreferredSize(bg.getPreferredSize());
		words.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		words.getColumnModel().getColumn(0).setMinWidth((int) (0.7 * bg.getMinimumSize().width));
		words.getColumnModel().getColumn(0).setPreferredWidth((int) (0.7 * bg.getPreferredSize().width));
		words.setShowGrid(false);
		words.setOpaque(false);
		bg.add(words);

		scroll.getVerticalScrollBar().setPreferredSize(new Dimension((int) (0.007 * w), (int) (0.802 * h)));
		scroll.getVerticalScrollBar().setUI(new ScrollBarUI());
	}

	public void addWord(String word, int pt) {
		GridBagConstraints c = new GridBagConstraints();

		JLabel wordLabel = new JLabel(word);
		wordLabel.setMinimumSize(new Dimension((int) (0.18 * w), (int) (0.02 * h)));
		wordLabel.setPreferredSize(new Dimension((int) (0.18 * w), (int) (0.02 * h)));
		c.gridx = 0;
		c.gridy = 1;

		JLabel ptLabel = new JLabel(String.valueOf(pt));
		wordLabel.setMinimumSize(new Dimension((int) (0.05475 * w), (int) (0.02 * h)));
		wordLabel.setPreferredSize(new Dimension((int) (0.05475 * w), (int) (0.02 * h)));

		data.addRow(new Object[] { word, pt });
	}

	private static class ScrollBarUI extends BasicScrollBarUI {
		private final Dimension d = new Dimension();

		@Override
		protected JButton createIncreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return d;
				}
			};
		}
		@Override
		protected JButton createDecreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return d;
				}
			};
		}

		@Override
		protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {}

		@Override
		protected void paintThumb(Graphics g, JComponent c, Rectangle trackBounds) {
			Graphics2D g2 = (Graphics2D) g.create();

			Color color;
			JScrollBar sb = (JScrollBar)c;
			if(!sb.isEnabled() || trackBounds.width > trackBounds.height) {
				return;
			} else if(isDragging) {
				color = new Color(143, 143, 143);
			} else if(isThumbRollover()) {
				color = new Color(166, 166, 166);
			} else {
				color = new Color(200, 200, 200);
			}

			g2.setPaint(color);
			g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height,5,5);
			g2.dispose();
		}

		@Override
		protected void setThumbBounds(int x, int y, int width, int height) {
			super.setThumbBounds(x, y, width, height);
			scrollbar.repaint();
		}
	}
}