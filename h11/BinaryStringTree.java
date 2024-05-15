
package h11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

/**
 * @author Benjamin Strauss, Edo Biagioni, Adam Graham
 *
 */

public class BinaryStringTree {

	//TODO add all class variables here
	BinaryNode root;
	int size = 0;
	
	private void checkInvariants() {
        checkInvariants(root);
    }

	private void checkInvariants(BinaryNode node) {
		if (node != null) {
            // Check that the value is always 1 or more
            if (node.value < 1) {
                throw new IllegalStateException("Invariant violation: Node value less than 1");
            }

            // Recursively check invariants for left and right subtrees
            checkInvariants(node.left);
            checkInvariants(node.right);
        }
    }

	public BinaryStringTree() { }
	

	/**
	 * Construct a new BinaryStringTree using the words in the given file
	 * @param fileName
	 * @throws FileNotFoundException 
	 */
	public BinaryStringTree(String fileName) throws FileNotFoundException {
		/* TODO
		 * hint: to read a file word-by-word, use Scanner.next()
		 */
		checkInvariants();
		File file = new File(fileName);
		Scanner reader = new Scanner(file);
		while(reader.hasNext()) {

			// TODO: Get the next tokens of the file and add it to the tree
			// Hint: You can use the tree's add() method to add words to the tree
			String word = reader.next();

			// TODO: Use a StringBuilder to build the string to add to the tree
			// This string should only contain alphabetic characters
			// Ex: "x,y.z" --> "xyz"
			// Ex: "item1" --> "item"
			StringBuilder builder = new StringBuilder();
			for(char c : word.toCharArray()) {
				if(Character.isJavaIdentifierStart(c)) {
					builder.append(c);
				}

			}
			add(builder.toString());
		}
		reader.close();
		checkInvariants();
	}



	/**
	 * 
	 * @param key
	 * @return the number of occurrences of the given String, or
	 *         0 if the String is not found
	 */
	public long occurrences(String key) {
		//TODO: call a private recursive helper method that you design
		checkInvariants();
		return occurrences(key, root);
	}

	private long occurrences(String key, BinaryNode node) {
		// Base case - node is null
		if(node == null)
			return 0;

		// Check if 'node' has the key we are looking for
		if(key.compareTo(node.key) == 0) {
			return node.value;
		}

		// If the key we are looking for is LESS than the node's key,
		// recursively search the LEFT subtree of the node
		else if(key.compareTo(node.key) < 0) {
			return occurrences(key, node.left);
		}

		// If the key we are looking for is GREATER than the node's key,
		// recursively search the RIGHT subtree of the node
		else {
			return occurrences(key, node.right);
		}
	}

	public Set keys() {
		java.util.Set<String> result = new java.util.HashSet<>();
		// TODO: call a private recursive helper method to fill the
		// set with all the keys in the tree
		checkInvariants();
		return keys(root, result);
	}

	private Set keys(BinaryNode node, Set set) {
		// Base case
		if(node == null)
			return set;

		// Add the key for the current node
		set.add(node.key);

		// Recursively add all the keys in the left subtree
		keys(node.left, set);

		// Recursively add all the keys in the right subtree
		keys(node.right, set);

		// Return the set
		return set;
	}


	public void add(String key) {
		// TODO: if the key is not in the tree, add it with a value 1
		//       if it is in the tree, just increment the value
		checkInvariants();
		if(root == null) {
			root = new BinaryNode(key);
			size++;
		}
		else {
			add(key, root);
			checkInvariants();
		}
	}

	private void add(String key, BinaryNode node) {
		int comparison = key.compareTo(node.key);

		if(comparison == 0) {
			node.value++;
		}
		else if(comparison < 0) {
			if(node.left == null) {
				node.left = new BinaryNode(key);
				size++;
			}
			else {
				add(key, node.left);
			}

		}
		else {
			if(node.right == null) {
				node.right = new BinaryNode(key);
				size++;
			}
			else {
				add(key, node.right);
			}
		}		
	}

	/**
	 * Removes one occurrence of the given key from the tree, usually
	 * by decrementing the value associated with the key.
	 * However, if the key has a value of 1, deletes the node
	 * If the key is not in the tree, does nothing.
	 * @param key
	 */
	public void removeOne(String key) {
		//TODO
		checkInvariants();
		removeOne(key, root);
		checkInvariants();
	}

	private void removeOne(String key, BinaryNode node) {
		// Base case
		if(node == null) {
			return ;
		}

		int comparison = key.compareTo(node.key);

		if(comparison < 0) {
			removeOne(key, node.left);
		}

		else if(comparison > 0) {
			removeOne(key, node.right);
		}

		else {
			if(node.value > 1) {
				node.value--;
			}
			else {
				remove(key);
			}
		}
	}

	/**
	 * Removes the given String from the tree by deleting the node
	 * @param key
	 */
	public void remove(String key) {
		//TODO (go right, left most from there)
		checkInvariants();
		remove(key, root);
		checkInvariants();
	}

	private void remove(String key, BinaryNode node) {
		// Base case
		if(node == null) {
			return;
		}

		int comparison = key.compareTo(node.key);

		if(comparison < 0) {
			if(key == node.left.key) {
				BinaryNode node2 = node.left;
				while(node2.right != null) {
					node2 = node2.right;
				}
				BinaryNode node3 = node.left.right;
				node.left = null;
				node.left = node2;
				node.left.right = node3;
			}
			remove(key, node.left);
		}

		else if(comparison > 0) {
			if(key == node.right.key) {
				BinaryNode node2 = node.right;
				while(node2.left != null) {
					node2 = node2.right;
				}
				BinaryNode node3 = node.right.left;
				node.right = null;
				node.right = node2;
				node.right.left = node3;
			}
			remove(key, node.right);
		}
	}


	/**
	 * 
	 * @return number of nodes in the tree == the number of unique Strings
	 */
	public int size() {
		//TODO
		checkInvariants();
		return size;
	}

	/**
	 * 
	 * @return the height of the tree.  An empty tree has height 0,
	 *         a tree with one node has height 1
	 */
	public int height() {
		//TODO
		checkInvariants();
		return height(root);
	}

	private int height(BinaryNode node) {
		// Base case
		if(node == null)
			return 0;

		return 1 + Math.max(height(node.left), height(node.right));
	}

	public String toString() {
		return toString(root, new StringBuilder(), "");

	}

	private String toString(BinaryNode node, StringBuilder builder, String spacer) {
		// Base case
		if(node == null)
			return "";

		// Append the current node's key:value pair to the StringBuilder
		builder.append(spacer + node + "\n");
		spacer += " ";

		// Recursively call toString() for the left and right subtrees
		toString(node.left, builder, spacer);
		toString(node.right, builder, spacer);

		// Return the String representation of the tree
		return builder.toString();

	}

	// =========================== Test Methods ===========================
	// Prints top 10 most frequent key:value pairs from the tree
	public void printTop10() {
		checkInvariants();
		ArrayList<BinaryNode> nodes = getNodes(root, new ArrayList<BinaryNode>());
		BinaryNode[] array = new BinaryNode[nodes.size()];

		// TODO: Add every BinaryNode from 'nodes' to 'array'
		nodes.toArray(array);

		// TODO: Sort 'array' using Array.sort()
		Arrays.sort(array);


		// TODO: Print the 10 indices of the array with the most frequent words
		// Hint: Most frequent words will be at the end of the array

		// Don't go beyond length of array
		int maxCount = Math.min(10,  array.length);

		for(int i = array.length - 1; i >= array.length - maxCount; i--) {
			BinaryNode node = array[i];
			System.out.println(node.key + ": " + node.value);
		}
		checkInvariants();
	}

	// Add all nodes in the tree to an ArrayList and return it to the caller
	// Hint: Very similar to the keys() method
	private ArrayList<BinaryNode> getNodes(BinaryNode node, ArrayList<BinaryNode> list) {
		// Base case
		if(node == null) {
			return list;
		}

		list.add(node);

		getNodes(node.left, list);

		getNodes(node.right, list);

		return list;
	}

	public class BinaryNode implements Comparable<BinaryNode>{
		// BinaryNode variables
		String key;
		long value;
		BinaryNode left;
		BinaryNode right;

		public BinaryNode(String key) {
			checkInvariants();
			this.key = key;
			this.value = 1;
			checkInvariants();
		}

		public static void add(String key2) {
			// TODO Auto-generated method stub

		}

		@Override
		public int compareTo(BinaryNode o) {
			// TODO Auto-generated method stub
			checkInvariants();
			return (int) (this.value - o.value);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		BinaryStringTree tree = new BinaryStringTree();
		tree.add("Hello");
		tree.add("World");
		tree.add("Welcome");
		tree.add("To");
		tree.add("ICS");
		tree.add("211");
		tree.checkInvariants();
		System.out.println(tree);


		System.out.println("=============Constitution=============");
		BinaryStringTree tree2 = new BinaryStringTree("constitution.txt");
		tree2.checkInvariants();
		tree2.printTop10();
		System.out.println("\n" + "Tree height: " + tree2.height() + "\n");

		System.out.println("=============Movie Script=============");
		BinaryStringTree tree3 = new BinaryStringTree("script.txt");
		tree3.checkInvariants();
		tree3.printTop10();
		System.out.println("\n" + "Tree height: " + tree3.height() + "\n");

	}
}