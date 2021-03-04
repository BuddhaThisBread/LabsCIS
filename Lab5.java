import java.io.*;
import java.util.*;

public class Lab5 {
  private static final Object[] monthNames = null;

public static void main(String[] args) throws IOException {
	// open scanner 
    Scanner scnr = new Scanner(new File("Lab5_Dates.txt"));
    
    // hold days in each month and month names
    int [] daysInEachMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String [] monthNames = {"January", "February", "March", "April", "May", "June", "July",
    						"August", "September", "October", "November", "December"};
    
    final int DATE_FORMAT = 3; // hold month date and year
    final int MAX_DATES = 50; // max dates 
    
    String monthDateYear [] [] = new String [DATE_FORMAT][MAX_DATES]; // array to hold dates from file
    
    int totalDates = 0; // hold number of dates from file
    int j = 0; // j iterates after each line from file
    int i = 0;
    while (scnr.hasNext()) { // assign line data into array
	    	String line = scnr.nextLine(); // get line
	    	StringTokenizer st = new StringTokenizer(line, "/");
	    	String month = ""; // hold data from file
	    	String date = "";
	    	String year = "";
	    	while(st.hasMoreTokens()) { // token data from line
	    		month = st.nextToken();
	    		date = st.nextToken();
	    		year = st.nextToken();
	    	}
	    		for (i = 0; i < DATE_FORMAT; i++) {
	    			if (i == 0) {
	    				monthDateYear[i][j] = month; 
	    			} else if (i == 1) {
	    				monthDateYear[i][j] = date; 
	    			} else if (i == 2) {
	    				monthDateYear[i][j] = year; 
	    			}
	    		}
	    		j++; // move to next date
	    		totalDates++; // find total number of dates from file
    	} // end of line 
    	scnr.close();
    	for (j = 0; j < totalDates; j++) {
	    	do {
	    		System.out.printf("%s", monthNames[Integer.parseInt(monthDateYear[i][j])]);
	    	} while(i < DATE_FORMAT);
    	}
    
  	}
}

    // YOU DO THIS: Use ANOTHER Loop that will display the CURRENT date in the correct format
    // followed by the SUBSEQUENT date in the correct format, followed by a blank line.

   /* Things to consider:
   	-If it is Dec 31, subsequent date is January 1, "year + 1" 
   	-If it is not Dec but is last day of the month, subsequent date is "next month" 1, year 
   	-Otherwise, just increase the day
   */



   	
   // Don't forget to close the file!	
