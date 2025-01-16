package bst;

import java.util.ArrayList;

/**
 *
 * Exam 2. Tree methods.
 * 
 * @author
 */

public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17); 

	public BinarySearchTree() {
		root = NULL_NODE;
	}
	
	

	/**
	 * Returns true if the total product of the tree is positive.
	 * No edit to the insert method should be done.
	 * Should be implemented using tree recursion in O(n) time.
	 * 
	 * If the tree is empty, return false.
	 */
	public boolean hasPositiveProduct () {
		if (root == NULL_NODE)
			return false;
		
		return root.product() > 0;
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
		ArrayList<Integer> l = new ArrayList<Integer>();
		boolean success = root.collectRejectedNodes(item, l);
		return success ? l : null;
	}
	
	/**
	 * Modifies the binary tree so that any node that only had one subtree now has another.
	 * This tree will be a single node with a value +/- 1 from the parent node, depending on whether it is the right or the left.
	 * (right +1, left -1)
	 * 
	 * NOTE: This will break other future operations on this BST,
	 * since there can now be duplicate elements.
	 */
	public void fillHalfTrees() {
		root.fillHalfTrees();
	}
	
	/**
	 * (HARD PROBLEM)
	 * Produce a string that contains all elements that are between (inclusive) start and end.
	 * For example if a tree contains the integers 6, 8, 9, 12, 13, 103,
	 * the all getAllBetween(7, 13); would return "<8,9,12,13>"
	 * Conversely the call getAllBetween(2, 4); would return "<>"
	 * 
	 * This method should run in time log(n) + m where m is the size of the input range
	 * 
	 * As a fun note, this is a similar problem to one that you will see your Editor Trees M3 homework
	 */
	public String getAllBetween(int start, int end) {
		if (root == NULL_NODE)
			return "<>";
		
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		root.getAllBetween(sb, start, end);
		String s = sb.toString();
		return sb.toString().substring(0, sb.length() - 1) + '>'; // TODO implement this method;
	}
	
	/**
	 * (HARD PROBLEM)
	 * Suppose you wanted your tree to only ever have odd leaf nodes. I know, somewhat strange, but bear with me.
	 * This method should imodify the tree and ensure that all of the leaf nodes ware odd by halving them.
	 * 
	 * Supposed there was previously a leaf node with a value of 6, 
	 * after the call, this leaf node would have two children each with a value of 3.
	 * 
	 * But what if the leaf node had a value of 8?
	 * The expectation is that the node will be "split" into two four nodes, 
	 * which will each be split into two "two" nodes and each of those will be split into a 1 node.
	 * 
	 * NOTE: Once this operation is performed you can no longer perform other BST Operations.
	 * Why? because supposed you take a leaf of value 6, and give it two children with values 3 each, now the order in the BST is no longer enforced.
	 */
	public void makeAllLeavesOdd() {
		root.makeAllLeavesOdd();
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

		public void makeAllLeavesOdd() {
			if (this == NULL_NODE)
				return;
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				if (data % 2 == 0) {
					right = new BinaryNode(data / 2);
					left = new BinaryNode(data / 2);
				}
			}
			
			left.makeAllLeavesOdd();
			right.makeAllLeavesOdd();
		}

		public void getAllBetween(StringBuilder sb, int start, int end) {
			if (this == NULL_NODE)
				return;
			
			if (data >= start && data <= end) {
				left.getAllBetween(sb, start, end);
				sb.append(Integer.toString(data));
				sb.append(',');
				right.getAllBetween(sb, start, end);
			}
			
			if (data >= end) {
				left.getAllBetween(sb, start, end);
			}
			
			if (data <= start) {
				right.getAllBetween(sb, start, end);
			}
		}

		public void fillHalfTrees() {
			if (this == NULL_NODE)
				return;
			if (right != NULL_NODE && left == NULL_NODE)
				left = new BinaryNode(data - 1);
			if (left != NULL_NODE && right == NULL_NODE)
				right = new BinaryNode(data + 1);
			
			left.fillHalfTrees();
			right.fillHalfTrees();
		}

		public boolean collectRejectedNodes(int item, ArrayList<Integer> l) {
			if (this == NULL_NODE)
				return false;
			if (item < data) {
				if (right != NULL_NODE)
					l.add(right.data);
				return left.collectRejectedNodes(item, l);
			}
			if (item > data) {
				if (left != NULL_NODE)
					l.add(left.data);
				return right.collectRejectedNodes(item, l);
			}
			return true;
			
		}

		public int product() {
			if (this == NULL_NODE)
				return 1;
			return left.product() * data * right.product();
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