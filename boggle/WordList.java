/**
 * @author noah.sun
 * @author jack.yuan
 * 2024.05.31
 */
package boggle;

import java.util.ArrayList;

/**
 * This class implements a word list that checks if a word is contained in the list by sorting the list using merge sort and then recursive binary search.
 */
public class WordList extends ArrayList<String> {

	/**
	 * Constructor
	 */
	public WordList() {
		super();
	}

	/**
	 * This method adds a word to the word list.
	 * @param s element whose presence in this collection is to be ensured
	 * @return if the element is successfully added
	 */
	public boolean add(String s) {
		return super.add(s.toUpperCase());
	}

	/**
	 * This method checks if the word is contained in the word list.
	 * @param s word
	 * @return if the word is contained in the word list
	 */
	public boolean contains(String s) {
		mergeSort(this);
		return binarySearch(this, 0, this.size() - 1, s.toUpperCase());
	}

	/**
	 *
	 * @param list word list to be binary searched
	 * @param l left boundary
	 * @param r right boundary
	 * @param s word to be searched for
	 * @return if the word is contained in the word list
	 */
	private boolean binarySearch(ArrayList<String> list, int l, int r, String s) {
		if (l > r) return false;
		int m = (l + r) / 2;
		if (s.equals(list.get(m))) return true;
		if (s.compareTo(list.get(m)) < 0) return binarySearch(list, l, m - 1, s);
		else return binarySearch(list, m + 1, r, s);
	}

	/**
	 * This method sorts a word list by using merge sort.
	 * @param list word list to be sorted.
	 */
	private void mergeSort(ArrayList<String> list) {
		// If the size of the list is less than or equal to one.
		if (list.size() <= 1) {
			return;
		}

		// Instantiate left and right child lists.
		ArrayList<String> left = new ArrayList<>();
		ArrayList<String> right = new ArrayList<>();
		
		// Split list into left and right child lists.
		int m = list.size() / 2;
		for (int i = 0; i < list.size(); i++) {
			if (i < m) left.add(list.get(i));
			else right.add(list.get(i));
		}
		
		// Sort left child list.
		mergeSort(left);
		// Sort right child list.
		mergeSort(right);
		// Merge child lists into parent list.
		merge(left, right, list);
	}

	/**
	 * This method merges two sorted list into one.
	 * @param left left list
	 * @param right right list
	 * @param list the list to be merged into
	 */
	private void merge(ArrayList<String> left, ArrayList<String> right, ArrayList<String> list) {
		// Instantiates pointers.
		int l = 0, r = 0, i = 0;

		// Iterate through both child lists simultaneously, adding the smaller element to the parent list.
		while (l < left.size() && r < right.size()) {
			if (left.get(l).compareTo(right.get(r)) < 0) list.set(i++, left.get(l++));
			else list.set(i++, right.get(r++));
		}

		// Add the remaining elements from the left list to the parent list.
		while (l < left.size()) {
			list.set(i++, left.get(l++));
		}

		// Add the remaining elements from the right list to the parent list.
		while (r < right.size()) {
			list.set(i++, right.get(r++));
		}
	}
}