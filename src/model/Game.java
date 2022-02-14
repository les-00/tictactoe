package model;

import javafx.scene.control.Button;

public class Game {

	//Entities for game space grid
	private char ch_X = 'X';
	private char ch_O = 'O';
	private char ch_Space = '#';
	private char ch_Winner = ch_Space;
	private char[][] space = new char[3][3];
	private int numblocksLeft = 9;
	
	public Game() {
		
		initGame();
	}
	
	
	/**
	 * Helper to set stage to space blocks
	 */
	private void initGame()
	{
		//initialize game space
		for(int r = 0; r < 3; r++)
		{
			for(int c = 0; c <3; c++)
			{
				space[r][c] = ch_Space;
			}
		}
	}
	
	
	/**
	 * Move character to game space via
	 * provided coordinates
	 * @param charCurrent
	 * character to place
	 * @param row
	 * character row position 
	 * @param column
	 * character column position
	 * @return
	 * true if character placement is made,
	 * false otherwise
	 */
	public boolean placeCharacter(char charCurrent, int row, int column)
	{
		//Check if occupied
		if(space[row][column] == ch_Space)
		{
			space[row][column] = charCurrent;
			--numblocksLeft;
			return true;
		}
		
		return false;
	}
	
	/**
	 * Clears the stage after game conclusion
	 */
	public void clearGame()
	{
		initGame();
		ch_Winner = ch_Space;
		numblocksLeft = 9;
	}
	
	
	
	/**
	 * Function to check for game winner
	 * @return
	 * True if there's a winner, false otherwise
	 */
	public boolean isThereWinner()
	{
		boolean isX = checkWinner(ch_X);
		boolean isY = checkWinner(ch_O);
		
		return isX || isY;
	}
	
	/**
	 * Helper to check if a given entitiy(Character) wins the game
	 * @param Entity
	 * @return
	 * True if the provided entity is a winner, false otherwise
	 */
	private boolean checkWinner(char Entity)
	{
		//Check for win Horizontally
		int[] arr = {0,1,2};
		for(int r: arr)
		{
			if(space[r][0] == Entity && space[r][1] == Entity && space[r][2] == Entity)
			{
				ch_Winner = Entity;
				return true;
			}
		}
		
		//Check for win Vertically
		for(int c: arr)
		{
			if(space[0][c] == Entity && space[1][c] == Entity && space[2][c] == Entity)
			{
				ch_Winner = Entity;
				return true;
			}
		}
		
		//Check for win diagonally d1
		if(space[0][0] == Entity && space[1][1] == Entity && space[2][2] == Entity)
		{
			ch_Winner = Entity;
			return true;
		}
		//Check for win diagonally d2
		if(space[0][2] == Entity && space[1][1] == Entity && space[2][0] == Entity)
		{
			ch_Winner = Entity;
			return true;
		}
		return false;
	}


	public boolean isGameDraw()
	{
		if(numblocksLeft <= 0)
			return true;
		
		return false;
	}
	

	/**
	 * @return the space
	 */
	public char[][] getSpace() {
		return space;
	}



	/**
	 * @return the ch_Winner
	 */
	public char getWinner() {
		return ch_Winner;
	}
	
	
}
