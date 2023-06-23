/*******************************************************************
Don't forget to replace this with your name!
Include any (and all) comments you deem important!
********************************************************************
Lab 4
Original code by Salamander T. Treefrog
This implements a binary tree. The linked node is a nested class.
Usually, you would call the main class BinaryTree, but it is Lab4
in this code just to keep the file name "Lab4.java"

Goal:
Alter the "add" method of Lab4 so it keeps the tree balanced.
Salamander has it sorted, but not balanced.
When altering the add method, try to keep the complexity down. In
other words: Don't write a for loop inside a for loop inside a for
loop. If you can keep it down to O(n) or better, that is preferred.

The main function pushes random values into the list. It then checks
to see if a value exists (it shouldn't) and if another value exists
(it should). You can see how this could be used in Lab 2 to keep a
list of values fetched from the foreign list and look for duplicates
without all the complexity issues.
********************************************************************/
public class Lab4<T extends Comparable<T>> {
	/** BEGIN EDITING HERE **/
	/**
	This adds a value to the tree.
	The value to add is "val" in the parameter.
	The tree is a LinkedNode tree.
	The root node in the tree is named "root".
	Change this function so the tree remains balanced.
	*/
	public void add(T val) {
		LinkedNode<T> t = new LinkedNode<T>(val); // Make a new node
		if(root==null) root = t; // Empty tree, so make root the new node
		else {
			// In the class, we talked about pushing items into the top
			// That is a naive insert and it can lead to an unsorted tree
			// The following inserts new nodes at the leaf nodes
			// Doing so lets us do a naive balancing because we know the tree is sorted
			// Start with "p" being the root node
			LinkedNode<T> p = root;
			// Now, we want to see if the leaf to use is null
			// Problem: When we hit the leaf, we need to know which node was the parent
			// That is what p is for
			// We will see if t goes to p's left or right and set the appropriate side as c
			// As long as c is not null, keep going down the tree
			// Initially, let c = p because it just works if you do that
			LinkedNode<T> c = p;
			while(c != null) {
				p = c; // The current child is going to be the new parent as we step down the tree
				if(c.get().compareTo(val)>0) { // val goes to the left
					c = c.getLeft(); // c goes down to the left child
				} else { // val goes to the right
					c = c.getRight(); // c goes down to the right child
				}
			}
			// c is null, so it is a leaf node
			// We insert t to p's left or right (gotta check again)
			if(p.get().compareTo(val)>0) { // It goes to the left
				p.setLeft(t);
			} else { // It goes to the right
				p.setRight(t);
			}

			// Balance the root
			root = balance(root);
		}
	}
	// This is a recursive function to balance the tree at a specific node
	// It returns the new node that moves into the position where the old
	// node used to be.
	public LinkedNode<T> balance(LinkedNode<T> r) {
		// Safety catch - we can't balance null
		if(r == null) return null;
		// Is left too deep? If so, rotate right.
		if(depth(r.getLeft()) - depth(r.getRight()) > 1) {
			// left's right needs to become r
			// r's left needs to become left's right
			LinkedNode<T> n = r.getLeft();
			r.setLeft(n.getRight());
			n.setRight(r);
			r = n;
		}
		// Repeat checking to see if I need to rotate left.
		if(depth(r.getRight()) - depth(r.getLeft()) > 1) {
			LinkedNode<T> n = r.getRight();
			r.setRight(n.getLeft());
			n.setLeft(r);
			r = n;
		}
		// Now, balance both the left and right
		// This is easy because this function is recursive
		r.setLeft(balance(r.getLeft()));
		r.setRight(balance(r.getRight()));
		// Make sure the new r is returned
		return r;
	}

	// If you want to write more functions, add them here
	/** STOP EDITING HERE **/

	public int depth() { // Get the depth of the tree
		return depth(root);
	}
	public int depth(LinkedNode<T> n) { // Get the depth from a node
		if(n==null) return 0;
		return 1+Math.max(depth(n.getLeft()), depth(n.getRight()));
	}
	public boolean contains(T val) { // Does the tree contain a value
		return contains(val, root);
	}
	public boolean contains(T val, LinkedNode<T> n) { // Recursive tree search
		if(n == null) return false; // Leaf node - return false
		if(n.get().compareTo(val)==0) return true; // If the node has the value, return true
		if(val.compareTo(n.get())<0) return contains(val, n.getLeft()); // val<node, check to the left
		else return contains(val, n.getRight()); // otherwise, check to the right
	}
	private LinkedNode<T> root; // The top node in the LinkedTree
	public Lab4() { root = null; } // Initially, the tree is empty
	public String toString() {	// This prints out the tree in a pretty way
		return toString(root);
	}
	public String toString(LinkedNode<T> node) { // Recursive part of the toString method
		if(node == null) return "";
		return "["+toString(node.getLeft())+"<"+node+">"+toString(node.getRight())+"]";
	}
	/**
	The main method for Lab4
	*/
	public static void main(String[] args) {
		Lab4<Integer> tree = new Lab4<>(); // Create an empty list
		// The following adds values to the list, one at a time, and prints the list
		// When the add function is fixed, you should see the list in order after each add
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(3);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(1);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(4);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(5);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(9);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(2);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(6);
		System.out.println(tree);
		System.out.println(tree.depth());
		tree.add(8);
		System.out.println(tree);
		System.out.println(tree.depth());
		if(tree.contains(7)) System.out.println("The tree contains 7");
		else System.out.println("The tree does NOT contain 7");
		if(tree.contains(4)) System.out.println("The tree contains 4");
		else System.out.println("The tree does NOT contain 4");
	}
	
	/**
	This is a nested linked node class for the Lab4 linked list
	*/
	class LinkedNode<T extends Comparable<T>> {
		private T value; // The node's value
		LinkedNode<T> left, right; // The next node
		public LinkedNode(T value) { // Construct with just a value
			this.value = value;
			this.left = this.right = null;
		}
		public LinkedNode(T value, LinkedNode<T> left, LinkedNode<T> right) { // Construct with a value and a next
			this.value = value;
			this.left = left;
			this.right = right;
		}
		public T get() { return value; } // Get the value
		public LinkedNode<T> getLeft() { return left; } // Get the left node
		public LinkedNode<T> getRight() { return right; } // Get the right node
		public void set(T value) { this.value = value; } // Set the value
		public void setLeft(LinkedNode<T> left) { this.left = left; } // Set left
		public void setRight(LinkedNode<T> right) { this.right = right; } // Set right
		public String toString() { return ""+value; } // Return the value as a string
	}
}
