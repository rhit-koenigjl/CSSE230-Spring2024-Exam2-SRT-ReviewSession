package solution;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

public class Testing {

	private BinarySearchTree exampleT1() {
		BinarySearchTree bst = new BinarySearchTree();
		final Integer array[] = { -1, -3, 2, -8, -2, 1, 3, -11, -7, 4 };
		for (int k = 0; k < array.length; k++) {
			bst.insert(array[k]);
		}
		return bst;
	}
	
	@Test
	public void test_hasPositiveProduct() {
		BinarySearchTree bst = exampleT1();
		assertTrue(bst.hasPositiveProduct());
		bst.insert(-100);
		assertFalse(bst.hasPositiveProduct());
		bst.insert(0);
		assertFalse(bst.hasPositiveProduct());
	}
	
	@Test
	public void test_rejectedNodes() {
		BinarySearchTree bst = exampleT1();
		assertNull(bst.rejectedNodes(5));
		ArrayList<Integer> expected = new ArrayList<Integer>();
		expected.add(-3);
		expected.add(1);
		assertTrue(expected.equals(bst.rejectedNodes(4)));
		bst.insert(5);
		assertEquals(expected, bst.rejectedNodes(4));
		assertNull(bst.rejectedNodes(0));
	}
	
	@Test
	public void test_pruneAtDepth() {
		BinarySearchTree bst = exampleT1();
		assertTrue(bst.size() == 10);
		bst.pruneAtDepth(2);
		assertTrue(bst.size() == 7);
		bst.pruneAtDepth(1);
		assertTrue(bst.size() == 3);
		bst.pruneAtDepth(0);
		assertTrue(bst.size() == 1);
	}

}
