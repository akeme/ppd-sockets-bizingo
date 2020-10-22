package mariana.jogo.server.Model;
import mariana.jogo.server.EnumConstants.*;
import mariana.jogo.server.Model.Position.*;

/**
 * Server Application -> Game
 * @author  Siyar
 * 
 * Game Model
 */
public class Game {
	private Position[][] position;
	
	//Constructor
	public Game(){
		position = new Position[13][23];
	//	position = new Position;
		initializePosition();
		assignPlayerIDs();
	}
	
	public int[][] initialPosition;
	
	

	/*
	//Initialize 64 Squares with ID, Row, Col & isFilled
	private void initializeSquares() {
		boolean rowInitialFilled, isFilled;
		int count = 0;
		
		//Rows
		for(int r=0;r<Checkers.NUM_ROWS.getValue();r++){
			rowInitialFilled = (r%2 == 1) ? true : false;
			
			//Columns
			for(int c=0;c<Checkers.NUM_COLS.getValue();c++){
				isFilled = (rowInitialFilled && c%2 == 0) ? true : (!rowInitialFilled && c%2 == 1) ? true : false;
				count++;
				
				squares[r][c] = new Square(count, r, c, isFilled);
			}
		}		
	}
	*/
	
	private void initializePosition() {
		int[][] initialPosition = {{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	}	,
				{	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	}	,
				{	-1	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	-1	}	,
				{	-1	,	-1	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
		};
	
	}
	
	private void assignPlayerIDs() {
		boolean rowInitialFilled, isFilled;
		
		int[][] initialConfig = {{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	2	,	1	,	2	,	1	,	2	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	2	,	1	,	2	,	1	,	2	,	1	,	2	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	0	,	1	,	2	,	1	,	2	,	1	,	2	,	1	,	2	,	1	,	2	,	1	,	0	,	-1	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	0	,	1	,	2	,	1	,	3	,	1	,	2	,	1	,	2	,	1	,	3	,	1	,	2	,	1	,	0	,	-1	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	-1	,	-1	,	-1	}	,
				{	-1	,	-1	,	0	,	1	,	0	,	4	,	0	,	5	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	5	,	0	,	4	,	0	,	1	,	0	,	-1	,	-1	}	,
				{	-1	,	0	,	1	,	0	,	1	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	1	,	0	,	1	,	0	,	-1	}	,
				{	-1	,	1	,	0	,	1	,	0	,	1	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	4	,	0	,	1	,	0	,	1	,	0	,	1	,	-1	}	,
				{	-1	,	-1	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	0	,	1	,	-1	,	-1	}	,
				{	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	,	-1	}}; 
		
		
	}
	
	
	
	
	
	/*
	private void assignPlayerIDs() {
		
		//Rows 0-2 for player ONE
		for(int r=0;r<3;r++){					
			//Columns
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				if(squares[r][c].getIsFilled()){
					squares[r][c].setPlayerID(Bizingo.PLAYER_ONE.getValue());
				}
			}
		}
		
		//Rows 5-7 for player TWO
		for(int r=5;r<8;r++){					
			//Columns
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				if(squares[r][c].getIsFilled()){
					squares[r][c].setPlayerID(Checkers.PLAYER_TWO.getValue());
				}
			}
		}
	}*/

	public Position[][] getPosition(){
		return this.position;
	}
	
	public int getTotalPosition(){
		return position.length;
	}
	
	public void printPositionDetails(){
		for(int r=0;r<Bizingo.NUM_ROWS.getValue();r++){
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				System.out.println(position[r][c].getPositionID() + "--" + position[r][c].getPositionX() + "--" + position[r][c].getPositionY()
						+ position[r][c].getPlayerID());
			}
		}
	}

	//ajeitar - não é um quadrado
	public Position getPosition(int from) {
		
		for(Position [] bRows:position){
			for(Position b: bRows){
				if(b.getPositionID()==from){
					return b;
				}
					
			}
		}		
		return null;
	}
	
	public void printAvailablePositionDetails(){
		
		int playerOne = 0;
		int playerTwo = 0;
		for(int r=0;r<Bizingo.NUM_ROWS.getValue();r++){
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				if(position[r][c].getPlayerID()==1)
					playerOne++;
				
				if(position[r][c].getPlayerID()==2)
					playerTwo++;
			}
		}
		
		System.out.println("Player 1 has " + playerOne);
		System.out.println("Player 2 has " + playerTwo);
	}
	
	public boolean isOver(){
		
		int playerOne = 0;
		int playerTwo = 0;
		for(int r=0;r<Bizingo.NUM_ROWS.getValue();r++){
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				if(position[r][c].getPlayerID()==1)
					playerOne++;
				
				if(position[r][c].getPlayerID()==2)
					playerTwo++;
			}
		}
		
		if(playerOne==0 || playerTwo==0){
			return true;
		}
		
		return false;
	}
}
