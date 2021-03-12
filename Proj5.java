

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class Proj5 {

	public static void main(String[] args) {
		Scanner scnr = new Scanner (System.in);
		
		
	}
	
	/*
	 	realBoard obtains board information from txt file, amount of rows and columns.
	 	@String fName - passes name of file from user keyboard 
	 	@return landArray - returns array created from given rows and columns 
	 */
	public static int [][] realBoard(String fName) throws IOException {
		Scanner getLand = new Scanner (new File(fName));
		
		int rows = Integer.parseInt(getLand.nextLine());
		int cols = Integer.parseInt(getLand.nextLine());
		int [][]landArray = new int [rows][cols]; 
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {// generate land from 1's and 0's from txt file
				if (Integer.parseInt(landArray[i][j]) == 0) {
					
				}
			}
		}
		return landArray;
		
		
	}

}
