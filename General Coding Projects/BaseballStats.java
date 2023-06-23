//****************************************************************************************
// NAME: Ethan Massingill
// DATE: 10/22/2020
// HOURS: About 2 hours
// CLASS: CSCI 200 Fall 2020
// ASSSIGNMENT: mini program-chap05
//****************************************************************************************




	import java.util.Scanner;
	import java.io.*;
	import java.text.DecimalFormat;

	public class BaseballStats {

	    public static void main (String[] args) throws IOException {

	    	Scanner fileScan, lineScan;
	    	String fileName;
	    	String line;          //a line from the file
	    	String playerName;
	    	int numHits, numWalks, numSacrifices, numOuts;
	    	double batA;
	    	String action;
	    	Scanner scan = new Scanner (System.in);

	    	System.out.print ("Enter the name of the input file: ");
	    	fileName = scan.nextLine();
	    	fileScan = new Scanner (new File(fileName));

	    	// Read and process each line of the file
	    	while (fileScan.hasNext()) {
	    		line = fileScan.nextLine();
	    		lineScan = new Scanner(line);
	    		lineScan.useDelimiter(",");

	    		numHits = 0;
	    		numOuts = 0;
	    		numSacrifices = 0;
	    		numWalks = 0;
	    		
	    		
	    		playerName = lineScan.next();

	    		while (lineScan.hasNext()) {
	    			action = lineScan.next();
	    			if (action.equals("1") || action.equals("2") || action.equals("3")
	          || action.equals("4"))
	    			    numHits++;
	    			else if (action.equals("o"))
	    			    numOuts++;
	    			else if (action.equals("s"))
	    			    numSacrifices++;
	    			else if (action.equals("w"))
	    				numWalks++;
	    			
	          // add code to handle counting number of walks
	    		}
	    		//print statistics for the player
	    		System.out.println ("\nStatistics for " + playerName + "... ");
	    		System.out.println ("Hits: " + numHits);
	    		System.out.println ("Outs: " + numOuts);
	    		System.out.println ("Walks: " + numWalks);
	    		System.out.println ("Sacrifices: " + numSacrifices);
	    		

	        // compute total number of at bats: https://www.wikihow.com/Calculate-a-Batting-Average
	    		if (numHits !=0 || numOuts !=0) {
	    		 batA = (double) numHits/(numOuts+numHits); 
	    		 DecimalFormat avgF = new DecimalFormat(".000");
	    		 System.out.println("Batting Average: "+ avgF.format(batA));
	    		 
	    		}
	    		 else if (numHits<=0 && numOuts<=0)
	    			 System.out.println("No at bats");
	    		
	    		
	    		
	    			
	    		
	        // create a DecimalFormat object that limits the number of decimal digits to 3
	    		
	        // output the batting average BUT BE CAREFUL not to d
	        // consider the situation where a batter has no at bats
	        // for this condition output "No at bats"
	        // otherwise output "Batting Average: " followed by the average formatted as specified above
	    	
	        // EXTRA CREDIT: compute slugging percentage and output similar to batting average

	      }
	    }
	}

