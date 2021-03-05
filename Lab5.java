import java.io.*;
import java.util.*;

public class Lab5 {

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
    int currentDate = 0;
    int currentMonth = 0;
    int currentYear = 0;
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
    		i = 0;
    		System.out.print("Current Date: "); // print initial statement
	    	do { // prints dates from file
		    	if (i == 0) {	
		    		currentMonth = Integer.parseInt(monthDateYear[i][j]) - 1; // store month
		    		System.out.printf("%s ", monthNames[currentMonth]);
		    		i++;
		    	} else if (i == 1) {
		    		currentDate = Integer.parseInt(monthDateYear[i][j]); // store date
		    		System.out.printf("%d, ", currentDate);
		    		i++;
		    	} else if (i == 2) {
		    		currentYear = Integer.parseInt(monthDateYear[i][j]);
		    		System.out.printf("%d\n", currentYear);
		    		i++;
		    	}
	    	} while(i < DATE_FORMAT); // end of current date
	    	
	    	if (currentDate == daysInEachMonth[currentMonth]) { // check if on final day of month
	    		if (currentMonth == (monthNames.length - 1) ) { // check if on final month of year
	    			currentMonth = 0;
	    			currentYear += 1;
	    			currentDate = 1;
	    			System.out.printf("Subsequent Date: %s %d, %d\n\n", monthNames[currentMonth], currentDate, currentYear);
	    		} else { // if the next month isnt january
	    			currentMonth += 1;
	    			currentDate = 1;
	    			System.out.printf("Subsequent Date: %s %d, %d\n\n", monthNames[currentMonth], currentDate, currentYear);
	    		}
	    	} else { // if next date doens't transition to next month
	    		currentDate += 1;
	    		System.out.printf("Subsequent Date: %s %d, %d\n\n", monthNames[currentMonth], currentDate, currentYear);
	    	}
    	} // end of printing out dates 
  	}
}
