/*******************************************************************
Lab 1 example solution
********************************************************************/
import java.util.ArrayList;
import java.util.Collections;

public class Lab1 {
	public static void main(String[] args) {
		// Make a BP list
		ArrayList<BP> list = new ArrayList<>();
		// Add some BPs to the list
		list.add(new BP(140,90));
		list.add(new BP(130,100));
		list.add(new BP(160,50));
		list.add(new BP(120,50));
		// Sort the list (requires compareTo in the BP class)
		Collections.sort(list);
		// Calculate the middle index
		// It helps here to make a list of size:index to work out the math
		// 1:0, 2:0, 3:1, 4:1, 5:2, 6:2...
		// I want (size-1)/2. Integer division drops the ".5"
		int i = list.size();
		int m = (i-1)/2;
		// Fetch the median BP and print it
		BP med = list.get(m);
		System.out.println("The median one is "+med);
	}
}
