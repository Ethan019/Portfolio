/*******************************************************************
Combined Regular and Priority Queue
This queue is weirdly named Lab5 instead of something better like
CombinedQueue. It combines a regular queue and a priority queue.
Items can be added to the regular queue using addReg or to the
priority queue with addPri. You cannot add to the combined queue
directly. Then, when you pop, it pops from the combined queue.
The combined queue can hold four items. If it has less than 4 items
in it, an element from either the regular or priority queue will
get moved from the queue it is in to the back of the combined queue.

That is the problem this week. The combine() function always pops
from the priority queue first - every time. So, if the priority
queue always has something in it, the regular queue never gets to
move over. You are to rewrite the combine() function to make sure
that the regular queue will eventually get into the combined queue.
You get to decide the specifics. There are no rules about how many
priority elements get to move per regular element or how long an
element can be in the queue. The catch is that with the added
responsibility of creating your own rules, you have to describe and
defend them. You are required to add a comment to the beginning of
the function that explains the rules you've implemented AND why you
believe those rules are fair. What is fair?

Example: You've spent a lot of money to get your kids in the
priority line at some amusement park ride. You expect that line to
get you on the ride quickly, right? If it doesn't move quickly, you
won't think that is fair.
Example: You don't have a lot of disposable cash. You can't afford
the priority line. But, you expect that you will get on the ride in
a reasonable amount of time, right? If you wait for hours while the
richer priority customers wait minutes, you won't that is fair.

!!!IMPORTANT NOTE!!!
The combined queue cannot have more than 4 elements in it at any
point in time. Right now, there is a terrible bug in this program.
The combined queue often has 5 elements in it. As you rewrite the
combined queue, see if you can fix that bug and limit the combined
queue to a maximum of 4 elements.
********************************************************************/
import java.util.LinkedList;
public class Lab5 {
	// BEGIN EDITING HERE
	// CREATE VARIABLES YOU MAY NEED, SUCH AS:
	// public int count=0;
	/********************************************
	Requirement: Replace this comment here with
	a comment that explains how your rules work
	and why you believe it is fair.
	*********************************************/
	// A round robin counter (see comments below)
	private int counter = 0;
	public void combine() {
		// Originally, this checked >4 to limit to 4 elements
		// But, that means it can have 4 and you are adding one right now
		// That makes 5 elements - too many
		// If it has 4 or more, don't add any
		// Change > to >=
		if(com.size()>=4) return; // Limit to 4 elements
		// The next problem is that the priority queue will keep the regular queue out of it
		// We need to tell the priority queue that sometimes the regular queue can go
		// The solution proposed here:
		// Make a counter
		// If the counter is 0 or 1, pop the priority queue
		// If the counter is 2, pop the regular queue
		// Increment the counter every pop so it counts 0 1 2 0 1 2 0 1 2...
		// The counter has to be declared outside this function or it will reset to zero every time this function is called
		if(counter<2) { // Priority gets a turn for 0 and 1
			if(pri.size()>0) com.add(pri.pop()); // Something is in priority, so pop it over to combined
			else if(reg.size()>0) com.add(reg.pop()); // If priority is empty, pop something from regular to combined
		} else { // Regular's turn when counter is 2
			if(reg.size()>0) com.add(reg.pop()); // Something is in priority, so pop it over to combined
			else if(pri.size()>0) com.add(pri.pop()); // If priority is empty, pop something from regular to combined
		}
		// Increment the counter
		// %3 forces it to be 0, 1, 2, 0, 1, 2...
		counter = (counter+1)%3;
	}
	// DO NOT EDIT BELOW HERE

	// The three lists: regular, priority, and combined
	private LinkedList<Integer> reg, pri, com;
	// Constructor: Make three empty FIFO queues
	public Lab5() {
		reg = new LinkedList<Integer>();
		pri = new LinkedList<Integer>();
		com = new LinkedList<Integer>();
	}
	// Add an element to the regular queue
	public void addReg(Integer i) {
		reg.add(i);
		combine();
	}
	// Add an element to the priority queue
	public void addPri(Integer i) {
		pri.add(i);
		combine();
	}
	// Pop an element off the combined queue
	public Integer pop() {
		Integer i = com.pop();
		combine();
		return i;
	}
	// Show all three queues as a string
	public String toString() {
		String s = "REG:"+reg+"  ";
		s+= "PRI:"+pri+"  ";
		s+= "COM:"+com;
		if(com.size()>4) s+= "  WARNING: COMBINED LINE EXCEEDS 4";
		return s;
	}

	// Create the three queues and push/pop elements on/off it
	public static void main(String[] args) {
		Lab5 qs = new Lab5();
		qs.addReg(1);
		System.out.println(qs);
		qs.addPri(11);
		System.out.println(qs);
		qs.addPri(12);
		System.out.println(qs);
		qs.addPri(13);
		System.out.println(qs);
		qs.addReg(2);
		qs.pop();
		System.out.println(qs);
		qs.addPri(14);
		System.out.println(qs);
		qs.addPri(15);
		qs.pop();
		System.out.println(qs);
		qs.addPri(16);
		System.out.println(qs);
		qs.addReg(3);
		qs.pop();
		System.out.println(qs);
		qs.addReg(4);
		System.out.println(qs);
		qs.addPri(17);
		System.out.println(qs);
		qs.addPri(18);
		System.out.println(qs);
		qs.addPri(19);
		System.out.println(qs);
		qs.addReg(5);
		qs.pop();
		System.out.println(qs);
		qs.addPri(20);
		System.out.println(qs);
		qs.addPri(21);
		qs.pop();
		System.out.println(qs);
		qs.addPri(22);
		System.out.println(qs);
		qs.addReg(6);
		qs.pop();
		System.out.println(qs);
		qs.pop();
		System.out.println(qs);
		qs.pop();
		System.out.println(qs);
		qs.pop();
		System.out.println(qs);
		qs.pop();
		System.out.println(qs);
		qs.pop();
		System.out.println(qs);
	}
}
