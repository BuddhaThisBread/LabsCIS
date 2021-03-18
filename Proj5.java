/*
 	<Proj5.java>
 	<Favian Lininger / C / Friday - 330:530>
 	
 	<Program reads in a file from user, which contains a grid of 1s and 0s and 
 	<converts said grid into integers. Program then proceeds to play game of life.
 	<Program scans each element from grid and looks for any living neighbors, based 
 	<how many neighbors are living, the reference cell will die or if it was already dead
 	<resurect.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class Proj5 {

	public static void main(String[] args) throws IOException{
		Scanner scnr = new Scanner (System.in);
		
		if (args.length != 1) { // correct user input
			System.out.print("Usage: java Proj5 filename");
			return;
		}
		
		int [][] landArray = readBoard(args[0]);
		scnr.close();
		
		while (true) {
			System.out.print(boardDisplay(landArray)); // display array
			try {
				Thread.sleep(300); // delay iteration .3sec
			}
				catch (InterruptedException e) {}
			landArray = update(landArray); // pass updated array
		}
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
		 	scanFirstRow - scans row above current reference cell
		 	@int [][] landArray - obtains the 2D grid containing the 1's and 0's
	 		@int iRef - obtains the current cells's location with respect to its row
	 		@int jRef - obtains the current cell's location with respect to its column
	 		@int incrementAmount - changes amount to increment jRef by
	 		@int colStart - starts scanning at a certain column
	 		@return int lifeForm - returns amount of neighboring cells that are alive
		*/

		public static int scanFirstRow(int[][] landArray, int iRef, int jRef, int incrementAmount, int colStart) {
			int lifeForm = 0;
			int colCount = colStart;
			do { // scan first row
				if (landArray[iRef - 1][jRef - 1] == 1) {
					lifeForm++;
					colCount++;
					jRef += incrementAmount;
				} else if (landArray[iRef - 1][jRef - 1] == 0) {
					colCount++;
					jRef += incrementAmount;
				}
			} while(colCount < 3);
			return lifeForm;
		}
		
		/*
		 	scanSecondRow - scans current row reference cell is on
		 	@int [][] landArray - obtains the 2D grid containing the 1's and 0's
	 		@int iRef - obtains the current cells's location with respect to its row
	 		@int jRef - obtains the current cell's location with respect to its column
	 		@int incrementAmount - changes amount to increment jRef by
	 		@int colStart - starts scanning at a certain column
	 		@return int lifeForm - returns amount of neighboring cells that are alive
		*/
		
		public static int scanCurrentRow(int[][] landArray, int iRef, int jRef, int incrementAmount, int colStart) {
			int lifeForm = 0;
			int colCount = colStart; // depending on case, scan only 2 instead of 3
			do { 
				if (landArray[iRef][jRef - 1] == 1) { // checks if living
					lifeForm++;
					colCount++;
					jRef += incrementAmount;
				} else if (landArray[iRef][jRef - 1] == 0) {
					colCount++;
					jRef += incrementAmount;
				}
			} while(colCount < 3); // keep track of columns scanned 
			return lifeForm;
		}
		
		/*
		 	scanFinalRow - scans row below reference cell
		 	@int [][] landArray - obtains the 2D grid containing the 1's and 0's
	 		@int iRef - obtains the current cells's location with respect to its row
	 		@int jRef - obtains the current cell's location with respect to its column
	 		@int incrementAmount - changes amount to increment jRef by
	 		@int colStart - starts scanning at a certain column
	 		@return int lifeForm - returns amount of neighboring cells that are alive
		*/
		
		public static int scanFinalRow(int[][] landArray, int iRef, int jRef, int incrementAmount, int colStart) {
			int lifeForm = 0;
			int colCount = colStart;
			do {
				if (landArray[iRef + 1][jRef - 1] == 1) {
					lifeForm++;
					colCount++;
					jRef += incrementAmount;
				} else if (landArray[iRef + 1][jRef - 1] == 0) {
					colCount++;
					jRef += incrementAmount;
				}
			} while(colCount < 3);
			
		return lifeForm;
		}
		
		/*
		 	neighbors searches the 2D grid (the array containing the grid) keeps 
		 		a running total of living cells surrounding the current cell being looked at
		 	@int [][] landArray - obtains the 2D grid containing the 1's and 0's
		 	@iRef - obtains the current cells's location with respect to its row
		 	@jRef - obtains the current cell's location with respect to its column
		 	@return int lifeForm - returns amount of neighboring cells that are alive
		*/
		
		public static int neighbors(int[][] landArray, int iRef, int jRef) {
			int totalLifeForm = 0; // what we want to return
			
			if (iRef - 1 < 0 && jRef - 1 < 0) { // upper left corner
				// start with currentRow as were starting at top of array
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef + 1, 2, 1); // scan first row, start at 1st col
				totalLifeForm += scanFinalRow(landArray, iRef, jRef + 1, 1, 0);
				return totalLifeForm;
			} // end of upper left corner 
			
			else if(jRef - 1 < 0 && iRef + 1 == landArray.length) { // bottom left corner
				// skip finalRow as that's out of bounds
				totalLifeForm += scanFirstRow(landArray, iRef, (jRef + 1), 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, (jRef + 1), 2, 1);
				return totalLifeForm;
			} // end of bot left corner
			
			else if (jRef - 1 < 0) { // if on left edge of board
				// start jRef at 1, so as to skip the first column, as thats out of bounds
				totalLifeForm += scanFirstRow(landArray, iRef, jRef + 1, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef + 1, 1, 1);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef + 1, 1, 1);
				return totalLifeForm;
			} // end of left edge border case
			
			else if ((iRef - 1 < 0) && (jRef != landArray[0].length - 1)){ // top edge 
				// skip FirstRow, as were already at first row, index 0
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 2, 2);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 1);
				return totalLifeForm;
			} // end of top edge 
			
			else if ((jRef == landArray[0].length - 1) && (iRef > 0 && iRef < landArray.length - 1)) { // for right edge 
				// only check for 2 indexes as thrid is out of bounds, set colCount to 1 to ignore last column
				totalLifeForm += scanFirstRow(landArray, iRef, jRef, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 0, 3);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 1);
				return totalLifeForm;
			} // end of right edge
			
			else if ((iRef == landArray.length - 1) && (jRef == landArray[0].length - 1)) { // bottom right corner
				// skip last row as its out of bounds
				totalLifeForm += scanFirstRow(landArray, iRef, jRef, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 0, 2);
				return totalLifeForm;
			} // end of bot right corner
			
			else if((jRef == landArray[0].length - 1) && (iRef - 1 < 0)) { // top right corner
				// skip first row as its out of bounds
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 0, 2);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 1);
				return totalLifeForm;
			} // end of top right
			
			else if((jRef > 0 && jRef < landArray[0].length) && (iRef == landArray.length - 1)) { // bot edge
				// skip final row as its out of bounds
				totalLifeForm += scanFirstRow(landArray, iRef, jRef + 1, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef + 2, 1, 2);
				return totalLifeForm;
			} // end bot edge
			
			else { // everything else
				// takes care of everything else :)
				totalLifeForm += scanFirstRow(landArray, iRef, jRef, 1, 0);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 2, 1);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 0);
				return totalLifeForm;
			} // last of it
		}
		
		/*
		 	update determines if a cell lives or dies or is brought back to life
		 	@int [][] landArray - takes 2D grid of game board
		 	@int iRef - refers to row of landArray
		 	@int jRef - refers to column of landArray
		 	@return int [][] landArray - returns updated array
		*/
		
		public static int [][] update(int [][] landArray) {
			int[][] updateArray = new int [landArray.length][landArray[0].length];
			for (int i = 0; i < landArray.length; i++) {
				for (int j = 0; j < landArray[0].length; j++) {
					int life = neighbors(landArray, i, j);
					if((landArray[i][j] == 1) && (life < 2)) { // if reference cell alive
							updateArray[i][j] = 0;
					}
					else if ((landArray[i][j] == 1) && (life > 3)) {
						updateArray[i][j] = 0;
					}
					else if ((landArray[i][j] == 0) && (life == 3)) { // if cell dead
							updateArray[i][j] = 1;
					}
					else {
						updateArray[i][j] = landArray[i][j];
					}
				} // end of inner loop
			} // end of outer loop
			return updateArray;
		}

}
