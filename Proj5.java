

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.lang.String;

public class Proj5 {

	public static void main(String[] args) throws IOException{
		Scanner scnr = new Scanner (System.in);
		int [][] landArray = readBoard("life2.txt");

		while (true) {
			System.out.print(boardDisplay(landArray));
			try {
				Thread.sleep(500);
			}
				catch (InterruptedException e) {}
			landArray = update(landArray);
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
			int colCount = colStart;
			do { // scan current row
				if (landArray[iRef][jRef - 1] == 1) {
					lifeForm++;
					colCount++;
					jRef += incrementAmount;
				} else if (landArray[iRef][jRef - 1] == 0) {
					colCount++;
					jRef += incrementAmount;
				}
			} while(colCount < 3);
			return lifeForm;
		}
		
		/*
		 	scanSecondRow - scans row below reference cell
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
			do { // scan last row
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

				totalLifeForm += scanCurrentRow(landArray, iRef, jRef + 1, 2, 1); // scan first row, start at 1st col
				totalLifeForm += scanFinalRow(landArray, iRef, jRef + 1, 1, 0);
				return totalLifeForm;
			} // end of upper left corner 
			
			else if(jRef - 1 < 0 && iRef + 1 == landArray.length) { // bottom left corner

				totalLifeForm += scanFirstRow(landArray, iRef, (jRef + 1), 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, (jRef + 1), 2, 1);
				return totalLifeForm;
			} // end of bot left corner
			
			else if (jRef - 1 < 0) { // if on left edge of board

				totalLifeForm += scanFirstRow(landArray, iRef, jRef + 1, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef + 1, 1, 1);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef + 1, 1, 1);
				return totalLifeForm;
			} // end of left edge border case
			
			else if ((iRef - 1 < 0) && (jRef != landArray[0].length - 1)){ // top edge 

				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 2, 2);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 1);
				return totalLifeForm;
			} // end of top edge 
			
			else if ((jRef == landArray[0].length - 1) && (iRef > 0 && iRef < landArray.length - 1)) { // for right edge 

				totalLifeForm += scanFirstRow(landArray, iRef, jRef, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 0, 3);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 1);
				return totalLifeForm;
			} // end of right edge
			
			else if ((iRef == landArray.length - 1) && (jRef == landArray[0].length - 1)) { // bottom right corner

				totalLifeForm += scanFirstRow(landArray, iRef, jRef, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 0, 2);
				return totalLifeForm;
			} // end of bot right corner
			
			else if((jRef == landArray[0].length - 1) && (iRef - 1 < 0)) { // top right corner

				totalLifeForm += scanCurrentRow(landArray, iRef, jRef, 0, 2);
				totalLifeForm += scanFinalRow(landArray, iRef, jRef, 1, 1);
				return totalLifeForm;
			} // end of top right
			
			else if((jRef > 0 && jRef < landArray[0].length) && (iRef == landArray.length - 1)) { // bot edge

				totalLifeForm += scanFirstRow(landArray, iRef, jRef + 1, 1, 1);
				totalLifeForm += scanCurrentRow(landArray, iRef, jRef + 2, 1, 2);
				return totalLifeForm;
			} // end bot edge
			
			else { // everything else

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
