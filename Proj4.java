
import java.util.Scanner;

public class Proj4 {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		
		// prompt for how many students user wants to input
		String totalStudents = scnr.nextLine();
		final int TOTAL_STUDENTS = Integer.parseInt(totalStudents);
		final int TOTAL_EXAMS = 4;
		
		String score;
		String WID = "";
		// create array for storing names and students scores
		String [] studentNames = new String [TOTAL_STUDENTS];
		int [][] examScores = new int [TOTAL_STUDENTS][TOTAL_EXAMS]; 
		int [] WIDs = new int [TOTAL_STUDENTS];
		
		// for i at zero, scan through number of students, set by user input
		int i;
		int j;
		
		for (i = 0; i < TOTAL_STUDENTS; i++) { // get TOTAL_STUDENTS amount of names, get WIDs 
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
		}
	
	  ///	
		
		 
	}
}