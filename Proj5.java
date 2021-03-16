

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class Proj5 {

	public static void main(String[] args) throws IOException{
		Scanner scnr = new Scanner (System.in);
		int [][] landArray = readBoard("life1.txt");
		
///////////////TEST__OUTPUT///////////////////
System.out.print(boardDisplay(landArray));

System.out.print(lookingForLife(landArray, 7, 2));
///////////////TEST__OUTPUT///////////////////
	
	}
	
	/*
	 	realBoard obtains board information from txt file, amount of rows and columns.
	 	@String fName - passes name of file from user keyboard 
	 	@return landArray - returns array created from given rows and columns 
	*/
	public static int [][] readBoard(String fName) throws IOException {
		Scanner getLand = new Scanner (new File(fName));
		
		int rows = Integer.parseInt(getLand.nextLine());
		int cols = Integer.parseInt(getLand.nextLine());
		int [][] landArray = new int [rows][cols]; 
		char [] rowOfLife = new char [cols];
		
		for (int i = 0; i < rows; i++) {
			String row = getLand.nextLine(); // obtain row
			rowOfLife = row.toCharArray();  // create char array of row
			for (int j = 0; j < cols; j++) { 
				if (rowOfLife[j] == '0') {
					landArray[i][j] = 0; // covert chars to integers, store in landArray
				} else if (rowOfLife[j] == '1') {
					landArray[i][j] = 1;
				}
			}
		}
		
		return landArray;
	}
	
		/*
			boardDisplay converts the 0's and 1's into periods and stars respectively
			@int [][] landArray - accepts a 2-D array (the Array containing the grid)
			@return String gameOfLife - returns the "board" as a grid of periods and stars
		 */
	
		public static String boardDisplay(int [][] landArray) {
		 	String gameOfLife; // contains the whole board
		 	StringBuilder rowStack = new StringBuilder(landArray.length);
		 	for (int i = 0; i < landArray.length; i++) { 
				StringBuilder row = new StringBuilder(landArray[i].length);
				for (int j = 0; j < landArray[i].length; j++) { // generate land from 1's and 0's from txt file
					if (landArray[i][j] == 0) { // place a period if non-living / dead
						row.append(".");
					} else if (landArray[i][j] == 1) { // place a star if living
						row.append("*");
					}
				}
				rowStack.append(row); // after scanning a row, convert to string and append to gameOfLife String
				rowStack.append("\n");
		 	}
		 	gameOfLife = rowStack.toString();
		 	return gameOfLife;
		}
		
		/*
		 	lookingForLife searches the 2D grid (the array containing the grid) keeps 
		 		a running total of living cells surrounding the current cell being looked at
		 	@int [][] landArray - obtains the 2D grid containing the 1's and 0's
		 	@iRef - obtains the current cells's location with respect to its row
		 	@jRef - obtains the current cell's location with respect to its column
		 	@return int lifeForm - returns amount of neighboring cells that are alive
		*/
		
		public static int lookingForLife(int[][] landArray, int iRef, int jRef) {
			
			int resetJ = jRef; // reset jRef
			int colCount = 0; // track col were checking
			int lifeForm = 0; // what we want to return
			
			do {
				if (landArray[iRef - 1][jRef - 1] == 1) {
					lifeForm++;
					colCount++;
					jRef++; // move to next neighbor
				} else if (landArray[iRef - 1][jRef - 1] == 0) {
					colCount++;
					jRef++;
				}
			} while(colCount < 3);
			
			jRef = resetJ; // reset everything
			colCount = 0;
			
			do {
				if (landArray[iRef][jRef - 1] == 1) {
					lifeForm++;
					jRef += 2;
					colCount++;
				} else if (landArray[iRef][jRef - 1] == 0) {
					jRef += 2;
					colCount++;
				}
			} while(colCount < 2);
			
			jRef = resetJ;
			colCount = 0;
			
			do {
				if (landArray[iRef + 1][jRef - 1] == 1) {
					lifeForm++;
					colCount++;
					jRef++; // move to next neighbor
				} else if (landArray[iRef + 1][jRef - 1] == 0) {
					colCount++;
					jRef++;
				}
			} while(colCount < 3);
			
			return lifeForm;
		}

}
