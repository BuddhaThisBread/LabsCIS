/* 
	<Lab6.java>
	<Favian Lininger / C / Friday - 330:530>
	
	<Program reads a file, which is entered via cmd line by the user, and extracts 
	<the full names from said txt file. Program then re-formats string to display
	<only the initials of the full name e.g. Favian Ray Lininger -> F. R. L.
*/

import java.lang.StringBuilder;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.lang.String;

public class Lab6 {
	
	public static String getInitial(String fullName) {
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(fullName, " ");
		String first; // store names
		String second;
		String third;
		String name = "";
		
		while(st.hasMoreTokens()) { // obtain initials
			first = st.nextToken().substring(0,1);
			second = st.nextToken().substring(0,1);
			third = st.nextToken().substring(0,1);
			sb.append(first + ". " + second + ". " + third + ". "); // A. B. C. format
		}
		name = sb.toString();
		return name;
	} // end getInitial

	public static void printInitials(String[] allNames) {
		for (int i = 0; i < allNames.length; i++) {
			System.out.println(getInitial(allNames[i]));
		}       
	} // end printInitials

	public static String[] getNames(String filename) throws IOException {
		Scanner inFile = new Scanner(new File(filename));
		int size = Integer.parseInt(inFile.nextLine());
		
		String [] names = new String [size];
		for (int i = 0; i < size; i++) { // read names and insert them into array names
			names [i] = inFile.nextLine();
		}
		inFile.close();
		return names; // return array names object 
	} // end getNames

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		// System.out.print("Enter the name of the input file: ");
		// String fname = s.nextLine();

		// Call the getNames method to get back an array of
		// names from the file
		
		if (args.length != 1) {
			System.out.print("Usage: java Lab6 filename");
			return;
		}
		String fname = args[0];
		int list = getNames(fname).length;
		String [] allNamesList = new String [list];
		for (int i = 0; i < list; i++) {
			 allNamesList[i] = getInitial(getNames(fname)[i]);
		}
		printInitials(allNamesList);
		s.close();
        
		// Call printInitials (passing the names array) 
        // to print the initials for each name. You should
		// have just ONE line of code to call that method
        
	} // end main
} // end class
