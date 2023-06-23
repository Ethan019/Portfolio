/*******************************************************************
Lab 3 Example Solution
********************************************************************
Don't forget to replace this with your name!
Include any (and all) comments you deem important!
********************************************************************
Lab 3
Original code by Salamander T. Treefrog
This implements a linked list. The linked node is a nested class.
Usually, you would call the main class LinkedList, but it is Lab3
in this code just to keep the file name "Lab3.java"

Goal 1:
Alter the "add" method of Lab3 so it keeps the list in order.
You can keep it in ascending or descending order - whichever you
prefer.
When altering the add method, try to keep the complexity down. In
other words: Don't write a for loop inside a for loop inside a for
loop. If you can keep it down to O(n) or better, that is preferred.

Goal 2:
Alter the "contains" method so it is better than O(n).

The main function pushes random values into the list. It then checks
to see if a value exists (it shouldn't) and if another value exists
(it should). You can see how this could be used in Lab 2 to keep a
list of values fetched from the foreign list and look for duplicates
without all the complexity issues.
********************************************************************/
public class Lab3<T extends Comparable<T>> {
	/** BEGIN EDITING HERE **/
	/**
	This adds a value to the list.
	The value to add is "val" in the parameter.
	The list is a LinkedNode list.
	The first node in the list is named "first".
	Change this function so the list is always in order.
	*/
	public void add(T val) {
		if(first == null) first = new LinkedNode<T>(val);
		// Special case: Only one node, so we need to sort it out
		else if(first.getNext() == null) {
			if(first.get().compareTo(val)>0) { // first>val, val is new first
				LinkedNode<T> n = new LinkedNode<>(val, first);
				first = n;
			} else { // val<first, insert val after first
				first.setNext(new LinkedNode<T>(val));
			}
		} else {
			LinkedNode<T> p = first;
			// Look for a node where the next node is either >=val or null
			while(p.getNext()!=null && p.getNext().get().compareTo(val)<0) p = p.getNext();
			// Make the new node have p's next as its next
			LinkedNode<T> n = new LinkedNode<>(val, p.getNext());
			// Then, make the new node p's next
			p.setNext(n);
		}
	}
	/**
	This checks to see if a value is in the list.
	If "val" is in the list, it returns true. Otherwise, it returns false.
	The list is a LinkedNode list.
	The first node in the list is named "first".
	Assume the list is in order because you SHOULD have updated
	the add method BEFORE you worked on this method.
	*/
	public boolean contains(T val) {
		// Stop the search if the node's value is greater than val
		for(LinkedNode<T> n = first; n != null && n.get().compareTo(val)<=0; n = n.getNext())
			if(n.get().compareTo(val)==0)
				return true;
		return false;
	}
	/** STOP EDITING HERE **/

	private LinkedNode<T> first; // The first node in the LinkedList
	public Lab3() { first = null; } // Initially, the list is empty
	public T get(int i) { // Get a value at index i (first index is 0)
		LinkedNode<T> n = first;
		while(n!=null && i>0) {
			n = n.getNext();
			i--;
		}
		if(n == null) return null;
		return n.get();
	}
	public void set(int i, T value) { // Set the value at index i (first index is 0)
		LinkedNode<T> n = first;
		while(n!=null && i>0) {
			n = n.getNext();
			i--;
		}
		if(n != null) n.set(value);
	}
	public String toString() {	// This prints out the list in a pretty way
		String s = "[";
		for(LinkedNode<T> n = first; n!=null; n=n.getNext()) s+= " "+n;
		s+= " ]";
		return s;
	}
	/**
	The main method for Lab3
	*/
	public static void main(String[] args) {
		Lab3<Integer> list = new Lab3<>(); // Create an empty list
		// The following adds values to the list, one at a time, and prints the list
		// When the add function is fixed, you should see the list in order after each add
		System.out.println(list);
		list.add(3);
		System.out.println(list);
		list.add(1);
		System.out.println(list);
		list.add(4);
		System.out.println(list);
		list.add(5);
		System.out.println(list);
		list.add(9);
		System.out.println(list);
		list.add(2);
		System.out.println(list);
		list.add(6);
		System.out.println(list);
		list.add(8);
		System.out.println(list);
		if(list.contains(7)) System.out.println("The list contains 7");
		else System.out.println("The list does NOT contain 7");
		if(list.contains(4)) System.out.println("The list contains 4");
		else System.out.println("The list does NOT contain 4");
	}

	/**
	This is a nested linked node class for the Lab3 linked list
	*/
	class LinkedNode<T> {
		private T value; // The node's value
		LinkedNode<T> next; // The next node
		public LinkedNode(T value) { // Construct with just a value
			this.value = value;
			this.next = null;
		}
		public LinkedNode(T value, LinkedNode<T> next) { // Construct with a value and a next
			this.value = value;
			this.next = next;
		}
		public T get() { return value; } // Get the value
		public LinkedNode<T> getNext() { return next; } // Get the next node
		public void set(T value) { this.value = value; } // Set the value
		public void setNext(LinkedNode<T> next) { this.next = next; } // Set next
		public String toString() { return ""+value; } // Return the value as a string
	}
}
