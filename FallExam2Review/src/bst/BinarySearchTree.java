package BST;

import java.util.ArrayList;

/**
 *
 * Exam 2. Tree methods.
 * 
 * @author
 */

/*
 * TODO: Directions: Implement the methods below. See the paper for details.
 */
public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17); 

	public BinarySearchTree() {
		root = NULL_NODE;
	}
	
	

	/**
	 * Returns true if the total sum of the tree is positive.
	 * No edit to the insert method should be done.
	 * Should be implemented using tree recursion in O(n) time
	 */
	public boolean hasPositiveProduct () {
		return false; // TODO implement this method.
	}
	
	/**
	 * Returns an ArrayList of integers,
	 * which correspond to the data of the rejected nodes in a binary search.
	 * If the search fails, return null;
	 * 
	 * For example: suppose you have a root node -> 1, with a right -> 2, and a left -> 4.
	 * If the search completes at 4, the value 2 should be added to the ArrayList
	 * 
	 * This can also be though of the paths not traveled algorythm.
	 * At any point that you would recurse left, add the value in right, 
	 * if at any point you would recurse right, add the value at left.
	 */
	public ArrayList<Integer> rejectedNodes(int item) {
		return null; // TODO implement this method.
	}
	
	/**
	 * Modifies the binary tree, so that no nodes exist with a depth greater than specified.
	 */
	public void pruneAtDepth(int depth) {
		return; // TODO implement this method.
	}
	
	

	// The next methods are used by the unit tests
	public void insert(int e) {
		root = root.insert(e);
	}
	
	public int height() {
		return root.height();
	}
	
	public int size() {
		return root.size();
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}

	@Override
	public String toString() {
		return this.toArrayList().toString();
	}
	public ArrayList<Integer> toArrayList() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		root.toArrayList(list);
		return list;
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public int data;
		public BinaryNode left;
		public BinaryNode right;
		
		public BinaryNode(int element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}				

		// The rest of the methods are used by the unit tests and for debugging
		
		public BinaryNode insert(int e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public int height() {
			if (this == NULL_NODE) {
				return -1;
			}
			return Math.max(left.height(), right.height())+1;
		}

		public int size() {
			if (this == NULL_NODE) {
				return 0;
			}
			return left.size() + right.size() + 1;
		}
		
		public void toArrayList(ArrayList<Integer> list) {
			if (this == NULL_NODE) {
				return;
			}
			left.toArrayList(list);
			list.add(this.data);
			right.toArrayList(list);
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}
			String myInfo = indent + String.format("%d\n", this.data);
			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}
	}

};