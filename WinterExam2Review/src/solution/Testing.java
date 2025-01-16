package solution;

import static org.junit.Assert.*;

import java.util.ArrayList;


import org.junit.Test;

public class Testing {
	
	@Test
	public void testHasPositiveProduct_useEmptyBST_returnFalse() {
		BinarySearchTree bst = new BinarySearchTree();
		assertFalse(bst.hasPositiveProduct());
	}
	
	@Test
	public void testHasPositiveProduct_useNegativeBST_returnFalse() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(-10);
		bst.insert(-5);
		bst.insert(-15);
		bst.insert(-25);
		bst.insert(-1);
		assertFalse(bst.hasPositiveProduct());
	}
	
	@Test
	public void testHasPositiveProduct_usePositiveBST_returnTrue() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(-10);
		bst.insert(-5);
		bst.insert(-15);
		bst.insert(-25);
		bst.insert(1);
		assertTrue(bst.hasPositiveProduct());
	}
	
	@Test
	public void testRejectedNodes_useEmptyBST_returnNull() {
		BinarySearchTree bst = new BinarySearchTree();
		ArrayList<Integer> l = new ArrayList<Integer>();
		int[] solution = {};
		l = bst.rejectedNodes(0);
		assertEquals(l, null);
	}

	@Test
	public void testRejectedNodes_useSmallBST_returnEmptyList() {
		BinarySearchTree bst = new BinarySearchTree();
		ArrayList<Integer> l = new ArrayList<Integer>();
		bst.insert(5);
		bst.insert(2);
		bst.insert(7);
		bst.insert(3);
		bst.insert(1);
		bst.insert(8);
		bst.insert(6);
		int[] solution = {7, 1};
		l = bst.rejectedNodes(3);
		assertEquals(solution.length, l.size());
		for (int i = 0; i < solution.length;i ++) {
			assertEquals(solution[i], (int) l.get(i));
		}
	}

	@Test
	public void testRejectedNodes_useTallBST_returnEmptyList() {
		BinarySearchTree bst = new BinarySearchTree();
		ArrayList<Integer> l;
		bst.insert(1);
		bst.insert(-1);
		bst.insert(10);
		bst.insert(9);
		bst.insert(100);
		bst.insert(99);
		bst.insert(1000);
		bst.insert(999);
		bst.insert(10000);
		bst.insert(9999);
		bst.insert(100000);
		bst.insert(99999);
		bst.insert(1000000);
		bst.insert(999999);
		bst.insert(10000000);
		bst.insert(9999999);
		bst.insert(100000000);

		int[] solution = {-1,9,99,999,9999,99999,999999,9999999};
		l = bst.rejectedNodes(100000000);
		assertEquals(solution.length, l.size());
		for (int i = 0; i < solution.length;i ++) {
			assertEquals(solution[i], (int) l.get(i));
		}
	}

	@Test
	public void testFillHalfTrees_useEmptyBST_assertEmpty() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.fillHalfTrees();
		String result = bst.toString();
		assertEquals("[]", result);
	}

	@Test
	public void testFillHalfTrees_useSmallTree_assertFilled() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(0);
		bst.insert(1);
		bst.fillHalfTrees();
		String result = bst.toString();
		assertEquals("[-1, 0, 1]", result);
	}

	@Test
	public void testFillHalfTrees_useWingedTree_assertFilled() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(0);
		bst.insert(2);
		bst.insert(4);
		bst.insert(6);
		bst.insert(8);
		bst.insert(10);
		bst.insert(12);
		bst.insert(-2);
		bst.insert(-4);
		bst.insert(-6);
		bst.insert(-8);
		bst.insert(-10);
		bst.insert(-12);
		bst.fillHalfTrees();
		String result = bst.toString();
		assertEquals("[-12, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12]", result);
	}

	@Test
	public void testFillHalfTrees_useLargeTree_assertFilled() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(0);
		bst.insert(Integer.MIN_VALUE);
		bst.insert(Integer.MAX_VALUE);
		bst.insert(-121212);
		bst.insert(999999999);
		bst.insert(12345);
		bst.insert(25555);
		bst.insert(66);
		bst.insert(-18);
		bst.insert(-88);
		bst.insert(-6);
		bst.insert(12315);
		bst.insert(7);
		bst.insert(2);
		bst.insert(123);
		bst.insert(2525);
		bst.insert(-100000);
		bst.insert(-12);
		bst.insert(-500);
		bst.insert(132);
		bst.insert(280);
		bst.insert(233);
		bst.insert(230);
		bst.insert(1543);
		bst.insert(233);
		bst.insert(12);
		bst.insert(-16);
		bst.insert(55);
		bst.insert(1000);
		bst.insert(8);
		String result = bst.toString();
		assertEquals("[-2147483648, -121212, -100000, -500, -88, -18, -16, -12, -6, 0, 2, 7, 8, 12, 55, 66, 123, 132, 230, 233, 280, 1000, 1543, 2525, 12315, 12345, 25555, 999999999, 2147483647]", result);
	}

	@Test 
	public void getAllBetween_useEmptyTree_assertEmptyTree() {
		BinarySearchTree bst = new BinarySearchTree();
		String result = bst.getAllBetween(-1, 1);
		assertEquals("<>", result);
	}

	@Test 
	public void getAllBetween_useSmallTree_assertEntireTree() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(0);
		bst.insert(1);
		bst.insert(-1);
		String result = bst.getAllBetween(-1, 1);
		assertEquals("<-1,0,1>", result);
	}

	@Test
	public void getAllBetween_useMediumTree_assertEntireTree() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(4);
		bst.insert(2);
		bst.insert(1);
		bst.insert(3);
		bst.insert(6);
		bst.insert(5);
		bst.insert(7);
		String result = bst.getAllBetween(1, 7);
		assertEquals("<1,2,3,4,5,6,7>", result);
	}

	@Test
	public void getAllBetween_useMediumTree_assertMiddleOfTree() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(4);
		bst.insert(2);
		bst.insert(1);
		bst.insert(3);
		bst.insert(6);
		bst.insert(5);
		bst.insert(7);
		String result = bst.getAllBetween(3, 5);
		assertEquals("<3,4,5>", result);
	}

	@Test
	public void getAllBetween_useMessyTree_assertPartOfTree() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(1);
		bst.insert(17);
		bst.insert(4);
		bst.insert(26);
		bst.insert(12);
		bst.insert(19);
		bst.insert(22);
		bst.insert(165);
		bst.insert(14);
		bst.insert(33);
		bst.insert(37);
		bst.insert(23);
		bst.insert(76);
		bst.insert(56);
		bst.insert(10);
		String result = bst.getAllBetween(2, 20);
		assertEquals("<4,10,12,14,17,19>", result);
		result = bst.getAllBetween(20, 40);
		assertEquals("<22,23,26,33,37>", result);
		result = bst.getAllBetween(40, 200);
		assertEquals("<56,76,165>", result);
	}

	@Test
	public void testMakeLeavesOdd_useEmptyTree_assertEmpty() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.makeAllLeavesOdd();
		assertEquals("[]", bst.toString());
	}

	@Test
	public void testMakeLeavesOdd_useNuancedTree_assertFillsUp() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(8);
		bst.insert(5);
		bst.insert(2);
		bst.insert(6);
		bst.insert(9);
		bst.insert(10);
		bst.makeAllLeavesOdd();
		assertEquals("[1, 2, 1, 5, 3, 6, 3, 8, 9, 5, 10, 5]", bst.toString());
	}

	@Test
	public void testMakeLeavesOdd_useSinglePowerOfTwoTree_assertFillsUp() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.insert(8);
		bst.makeAllLeavesOdd();
		assertEquals("[1, 2, 1, 4, 1, 2, 1, 8, 1, 2, 1, 4, 1, 2, 1]", bst.toString());
	}
}
