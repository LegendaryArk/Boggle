/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */

package boggle;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * This class keeps track of the words found and its point value and displays
 * it to the players.
 */
public class WordTable {
	// Scroll pane to allow user to scroll through words entered.
	private JScrollPane scrollPane;
	// Data to hold the words entered and its point values.
	private DefaultTableModel data;
	// The table to display the words and point values
	private JTable words;

	// The width and height of the screen
	private int width;
	private int height;

	/**
	 * Constructor
	 * @param mainFrame The parent frame of the game
	 * @param background The background of the word table
	 * @param scrollPane The scroll pane to allow user to scroll through words
	 */
	public WordTable(Boggle mainFrame, JPanel background,
	                 JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
		
		width = mainFrame.getScreenWidth();
		height = mainFrame.getScreenHeight();

		// Makes the table cells unable to be edited.
		data = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		// Adds the two columns
		data.addColumn("Words");
		data.addColumn("Points");

		// Sets the style of each cell
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setOpaque(false);
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		// The table displaying the words and points
		words = new JTable();
		words.setDefaultRenderer(Object.class, renderer);
		words.setModel(data);
		words.setFont(new Font("Calibri", Font.PLAIN, 25));
		words.setRowHeight(30);
		words.setTableHeader(null);
		words.setMinimumSize(background.getMinimumSize());
		words.setPreferredSize(background.getPreferredSize());
		words.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		words.setColumnSelectionAllowed(false);
		words.setRowSelectionAllowed(false);
		words.getColumnModel().getColumn(0).setMinWidth(
				(int) (0.7 * background.getMinimumSize().width));
		words.getColumnModel().getColumn(0).setPreferredWidth(
				(int) (0.7 * background.getPreferredSize().width));
		words.setShowGrid(false);
		words.setOpaque(false);
		background.add(words);

		// Sets the look of the scroll bar
		this.scrollPane.getVerticalScrollBar().setPreferredSize(
				new Dimension((int) (0.007 * width), (int) (0.802 * height)));
		this.scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
	}

	/**
	 * Adds a word to the table
	 * @param word The word that is entered
	 * @param points The number of points that the word is worth
	 */
	public void addWord(String word, int points) {
		data.addRow(new Object[]{word, points});
	}

	/**
	 * Removes all the words and points in the table
	 */
	public void clear() {
		data.setRowCount(0);
	}

	/**
	 * The custom scroll bar GUI
	 */
	private static class ScrollBarUI extends BasicScrollBarUI {
		private final Dimension d = new Dimension();

		/**
		 * Does not create an increase button.
		 * @param orientation the orientation.
		 * @return a JButton with a width and height of 0.
		 */
		@Override
		protected JButton createIncreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return d;
				}
			};
		}

		/**
		 * Does not create a decrease button.
		 * @param orientation the orientation.
		 * @return a JButton with a width and height of 0.
		 */
		@Override
		protected JButton createDecreaseButton(int orientation) {
			return new JButton() {
				@Override
				public Dimension getPreferredSize() {
					return d;
				}
			};
		}

		/**
		 * Does not paint the track, makes it invisible
		 * @param g the graphics
		 * @param c the component
		 * @param trackBounds the track bounds
		 */
		@Override
		protected void paintTrack(Graphics g, JComponent c,
		                          Rectangle trackBounds) {}

		/**
		 * Paints the thumb.
		 * Makes a more modern looking one compared to the default.
		 * @param g the graphics
		 * @param c the component
		 * @param trackBounds the thumb bounds
		 */
		@Override
		protected void paintThumb(Graphics g, JComponent c,
		                          Rectangle trackBounds) {
			Graphics2D g2 = (Graphics2D) g.create();

			Color color;
			JScrollBar sb = (JScrollBar) c;
			if (!sb.isEnabled() || trackBounds.width > trackBounds.height) {
				// Invalid scrollbar
				return;
			} else if (isDragging) { // Press colour
				color = new Color(143, 143, 143);
			} else if (isThumbRollover()) { // Hover colour
				color = new Color(166, 166, 166);
			} else { // Default
				color = new Color(200, 200, 200);
			}

			// Draw the scrollbar
			g2.setPaint(color);
			g2.fillRoundRect(trackBounds.x, trackBounds.y, trackBounds.width,
					trackBounds.height, 5, 5);
			g2.dispose();
		}

		/**
		 * Set the bounds of the thumb and force a repaint that includes
		 * the old thumbBounds and the new one.
		 *
		 * @param x set the x location of the thumb
		 * @param y set the y location of the thumb
		 * @param width set the width of the thumb
		 * @param height set the height of the thumb
		 */
		@Override
		protected void setThumbBounds(int x, int y, int width, int height) {
			super.setThumbBounds(x, y, width, height);
			scrollbar.repaint();
		}
	}
}