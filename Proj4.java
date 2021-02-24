
import java.util.*;
import java.util.Scanner;

public class Proj4 {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		// prompt for how many students user wants to input
		final int TOTAL_EXAMS = 4;
		final int MAX_COUNT = 999;
		
		// Curve for class
		final double GRADE_A = 90;
		final double GRADE_B = 80;
		final double GRADE_C = 70;
		final double GRADE_D = 60;
		
		
		
		// create array for storing names and students scores
		String score;
		String WID = "";
		
		String [] studentNames = new String [MAX_COUNT];
		int [][] examScores= new int [MAX_COUNT][TOTAL_EXAMS]; 
		int [] WIDs = new int [MAX_COUNT];
		
		// for i at zero, scan through number of students, set by user input
		int i;
		int j;
		int totalStudents = 1; // counter for amount of students determined by user
		String userChoice;
		
		for (i = 0; i < MAX_COUNT; i++) { // get TOTAL_STUDENTS amount of names, get WIDs 
			System.out.print("Please enter the name of Student " + (i+1) + ": ");
			studentNames[i] = scnr.nextLine();
			//while loop to check if WID is 9 digits long
			System.out.print("Please enter the WID of Student " + (i+1) + ": ");
			WID = scnr.nextLine();
			WIDs[i] = Integer.parseInt(WID);
			while (WID.length() != 9) { // check WIDs
			  System.out.println("Invalid WID, must be 9-digits.");
			  System.out.print("Please enter the WID of Student " + (i+1) + ": ");
			  WID = scnr.nextLine();
			  WIDs[i] = Integer.parseInt(WID);
			}
			for (j = 0; j < examScores[i].length; j++) { // iterate exam scores
				if (j < examScores[i].length - 1) { // for first 3 exams
					System.out.print("Please enter score for exam " + (j+1) + ": ");
					score = scnr.nextLine();
					examScores[i][j] = Integer.parseInt(score); // obtain exam score
					while (examScores[i][j] > 50 || examScores[i][j] < 0) {
						System.out.println("Invalid score, please enter 0-50 only");
						System.out.print("Please enter score for exam " + (j+1) + ": ");
						score = scnr.nextLine();
						examScores[i][j] = Integer.parseInt(score);
					}
				} else { // for last exam
					System.out.print("Please enter score for final exam " + (j+1) + ": ");
					score = scnr.nextLine();
					examScores[i][j] = Integer.parseInt(score); // obtain exam score
					while (examScores[i][j] > 100 || examScores[i][j] < 0) {
						System.out.println("Invalid score, please enter 0-100 only");
						System.out.print("Please enter score for exam " + (j+1) + ": ");
						score = scnr.nextLine();
						examScores[i][j] = Integer.parseInt(score);
					}
				}
			}
		  // prompt for user
		  System.out.print("Do you wish to enter another? (y/n)");
		  userChoice = scnr.nextLine();
		  if (userChoice.charAt(0) == 'y' || userChoice.charAt(0) == 'Y') {
			  totalStudents++;
			  continue;
		  } else if (userChoice.charAt(0) == 'n'  || userChoice.charAt(0) == 'N') {
			  break;
		  }
		}
	  ///	Format names from studentNames list
		String first, last;
		
		for (i=0; i < totalStudents; i++) {
			String studentName = studentNames[i];
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(studentName, " ");
			
			while(st.hasMoreTokens()) {
				first = st.nextToken();
				last = st.nextToken();
				sb.append(last);
				sb.append(", " );
				sb.append(first);
				studentName = sb.toString();
				studentNames[i] = studentName.toUpperCase();
			}
		}
		
		/// // display name, wid, average exam score as percentage(%45) and final grade
		double examSum = 0.0;
		double examPercentage;
		
		System.out.println("***Class Results***");
		for (i = 0; i < totalStudents; i++) {
			System.out.println(studentNames[i]);
			System.out.println(WIDs[i]);
			for (j = 0; j < examScores[i].length; j++) {
				examSum += examScores[i][j];
			}
			examPercentage = examSum / TOTAL_EXAMS;
			System.out.println("Exam percentage: " + examPercentage); // print average score
			if (examPercentage >= GRADE_A) {
				System.out.println("Final grade: A");
			} else if (examPercentage >= GRADE_B) {
				System.out.println("Final grade: B");
			} else if (examPercentage >= GRADE_C) {
				System.out.println("Final grade: C");
			} else if (examPercentage >= GRADE_D) {
				System.out.println("Final grade: D");
			} else {
				System.out.println("Final grade: F");
			}
			
			// read for a 'enter' press from user
			System.out.print("Press enter to display next student...");
			userChoice = scnr.nextLine();			
		}
		
		
		
		
		
		
	}
}
