package solution;

import java.util.ArrayList;

/**
 *
 * Exam 2. Tree methods solution.
 * 
 * @author JL Koenig
 */

public class BinarySearchTree {

	BinaryNode root;

	// The -17 is arbitrary -any int would be fine since we never refer to it.
	final BinaryNode NULL_NODE = new BinaryNode(-17);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	public boolean hasPositiveProduct() {
		if (root == NULL_NODE) {
			return false;
		}
		return root.product() > 0;
	}

	public ArrayList<Integer> rejectedNodes(int item) {
		ArrayList<Integer> l = new ArrayList<>();
		root.rejectedNodes(item, l);
		return l;
	}

	public void fillHalfTrees() {
		root.fillHalfTrees();
	}

	public String getAllBetween(int start, int end) {
		StringBuilder sb = new StringBuilder();
		sb.append('<');
		root.getAllBetween(start, end, sb);
		if (sb.toString().length() == 1) {
			return "<>";
		}
		String stringWithComma = sb.toString();
		return stringWithComma.substring(0, stringWithComma.length() - 1) + '>';
	}

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

		public int product() {
			if (this == NULL_NODE)
				return 1;
			return data * right.product() * left.product();
		}

		public void rejectedNodes(int item, ArrayList<Integer> list) {
			if (this == NULL_NODE) {
				return;
			}

			if (item == data) {
				return;
			}

			if (item > data) {
				if (left != NULL_NODE) {
					list.add(left.data);
				}
				right.rejectedNodes(item, list);
			} else if (item < data) {
				if (right != NULL_NODE) {
					list.add(right.data);
				}
				left.rejectedNodes(item, list);
			}
		}

		public void fillHalfTrees() {
			if (this == NULL_NODE || (this.right == NULL_NODE && this.left == NULL_NODE)) {
				return;
			}

			if (this.right != NULL_NODE) {
				if (this.left == NULL_NODE) {
					this.left = new BinaryNode(this.data - 1);
				}
				this.right.fillHalfTrees();
			}

			if (this.left != NULL_NODE) {
				if (this.right == NULL_NODE) {
					this.right = new BinaryNode(this.data + 1);
				}
				this.left.fillHalfTrees();
			}
		}

		public void getAllBetween(int start, int end, StringBuilder sb) {
			if (this == NULL_NODE) {
				return;
			}

			if (this.data > end) {
				this.left.getAllBetween(start, end, sb);
			} else if (this.data < start) {
				this.right.getAllBetween(start, end, sb);
			} else {
				this.left.getAllBetween(start, end, sb);
				sb.append(Integer.toString(this.data));
				sb.append(',');
				this.right.getAllBetween(start, end, sb);
			}
		}

		public void makeAllLeavesOdd() {
			if (this == NULL_NODE) {
				return;
			}

			if (this.left == NULL_NODE && this.right == NULL_NODE && this.data % 2 == 0 && this.data != 0) {
				this.left = new BinaryNode(data / 2);
				this.right = new BinaryNode(data / 2);
			}

			this.left.makeAllLeavesOdd();
			this.right.makeAllLeavesOdd();
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
			return Math.max(left.height(), right.height()) + 1;
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