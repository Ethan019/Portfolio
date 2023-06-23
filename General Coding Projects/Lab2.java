/* DO NOT EDIT THE import CODE OR THE CLASS DECLARATION */
import java.util.Random; // Used to generate random Integers
public class Lab2 {
	/* DO NOT EDIT THE FUNCTION DECLARATION */
	/**
	* search for a repeated value in a list.
	* Returns true if a value is repeated.
	* Remember that you can use the following:
	* size    This is the number of items in the list.
	* get(i)  That will get the value at index i from the list.
	*/
	private static boolean search() {
		/* START EDITING HERE */
		/* Read the assignment to understand Salamander's current algorithm here. */
		for(int i=0; i<size; i++) {
			// Minimum requirement met by changing j=0 to j=i+1
			for(int j=i+1; j<size; j++) {
					if(get(i)==get(j)) return true;
				}
			}
		}
		return false;
		/* DO NOT EDIT BELOW HERE */
	}

	/* DO NOT EDIT ANY OF THE CODE BELOW HERE */
	private static int size;	// Size of the list
	private static int[] list;	// The integer list
	private static int getCounter;	// A count of how many times "get" is called
	public static void main(String[] args) {
		// Set the size of the list
		size = 10;
		if(args.length > 0) size = Integer.parseInt(args[0]);
		// Create and fill the list
		list = new int[size];
		Random rand = new Random();
		for(int i=0; i<size; i++) list[i] = rand.nextInt();
		// If you uncomment the following, it will be forced to have duplicate values:
		//forceDuplicate();
		// Reset the getCounter to zero
		getCounter = 0;
		// If search returns true, print Duplicate (otherwise, print No duplicate)
		if(search()) System.out.print("Duplicate");
		else System.out.print("No duplicate");
		// Print the number of checks made
		System.out.println(" in "+getCounter+" checks.");
	}
	// Get the value at index i in the list
	private static int get(int i) {
		getCounter++;	// Count every call to get
		int v = list[i];	// Get the value
		/* YOU CAN TEMPORARILY UNCOMMENT THE FOLLOWING LINE TO SEE WHAT INDEXES AND VALUES ARE FETCHED */
		/* JUST MAKE SURE YOU COMMENT IT OUT AGAIN BEFORE SUBMITTING */
		//System.out.println(i+":"+v);
		return v;	// Return the value
	}
	// Swap two values in the list
	private static void swap(int i, int j) {
		getCounter++; // Count this as a single access
		int t = list[i];
		list[i] = list[j];
		list[j] = t;
	}
	// Force the list to have a random duplicate value
	private static void forceDuplicate() {
		int i, j;
		i = (int)(Math.random()*size);
		do {
			j = (int)(Math.random()*size);
		} while(i==j);
		list[i] = list[j];
	}
}
