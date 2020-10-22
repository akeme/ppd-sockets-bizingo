package mariana.jogo.client.Model;

import java.util.LinkedList;

import mariana.jogo.client.EnumConstants.*;



public class Board {
	
	private Position[][] positions;
	
	public Board(){
		positions = new Position[8][8];
		
		setPositions();
		assignPlayerIDs();
		//printPositionDetails();
	}
	
	private void setPositions() {
		boolean rowInitialFilled, isFilled;
		int count = 0;
		
		//Rows
		for(int r=0;r<Bizingo.NUM_ROWS.getValue();r++){
			rowInitialFilled = (r%2 == 1) ? true : false;
			
			//Columns
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				isFilled = (rowInitialFilled && c%2 == 0) ? true : (!rowInitialFilled && c%2 == 1) ? true : false;
				count++;
				
				positions[r][c] = new Position(count, r, c, isFilled);
//				System.out.println(i+" ---- " + rowInitialFilled + " + "+ k + " ---"+isFilled);
			}
		}		
	}

	public Position[][] getPositions(){
		return this.positions;
	}
	
	public int getTotlaPositions(){
		return positions.length;
	}
	
	public void printPositionDetails(){
		for(int r=0;r<Bizingo.NUM_ROWS.getValue();r++){
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				/*System.out.println(positions[r][c].getPositionID() + "--" + positions[r][c].getPositionX() + "--" + positions[r][c].getPositionY()
						+ positions[r][c].getPlayerID());*/
				System.out.println(positions[r][c].getPositionID() + " --"+ positions[r][c].isPossibleToMove());
			}
		}
	}
	
	private void assignPlayerIDs() {
		
		//Rows 0-2 for player ONE
		for(int r=0;r<3;r++){					
			//Columns
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				if(positions[r][c].getIsFilled()){
					positions[r][c].setPlayerID(Bizingo.PLAYER_ONE.getValue());
				}
			}
		}
		
		//Rows 5-7 for player TWO
		for(int r=5;r<8;r++){					
			//Columns
			for(int c=0;c<Bizingo.NUM_COLS.getValue();c++){
				if(positions[r][c].getIsFilled()){
					positions[r][c].setPlayerID(Bizingo.PLAYER_TWO.getValue());
				}
			}
		}
	}
	
	public LinkedList<Position> findPlayablePositions(Position selectedPosition){
		
		LinkedList<Position> playablePositions = new LinkedList<Position>();
		
		int selectedRow = selectedPosition.getPositionX();
		int selectedCol = selectedPosition.getPositionY();
		
		int movableRow = (selectedPosition.getPlayerID()==1) ? selectedRow+1 : selectedRow-1;
		
		//check two front positions
		twoFrontPositions(playablePositions, movableRow, selectedCol);
		crossJumpFront(playablePositions, (selectedPosition.getPlayerID()==1) ? movableRow+1 : movableRow-1, selectedCol, movableRow);
		if(selectedPosition.isKing()){
			movableRow = (selectedPosition.getPlayerID()==1) ? selectedRow-1 : selectedRow+1;
			twoFrontPositions(playablePositions, movableRow , selectedCol);
			crossJumpFront(playablePositions, (selectedPosition.getPlayerID()==1) ? movableRow-1 : movableRow+1, selectedCol, movableRow);
		}
		return playablePositions;		
	}
	
	//check two front positions
	private void twoFrontPositions(LinkedList<Position> pack, int movableRow, int selectedCol){
		
		if(movableRow>=0 && movableRow<8){
			//right Corner
			if(selectedCol>=0 && selectedCol<7){
				Position rightCorner = positions[movableRow][selectedCol+1];
				if(rightCorner.getPlayerID()==0){
					rightCorner.setPossibleToMove(true);
					pack.add(rightCorner);
				}
			}
			
			//left upper corner
			if(selectedCol>0 && selectedCol <=8){
				Position leftCorner = positions[movableRow][selectedCol-1];
				if(leftCorner.getPlayerID()==0){
					leftCorner.setPossibleToMove(true);
					pack.add(leftCorner);
				}
			}
		}
	}
	
	//cross jump - two front
	private void crossJumpFront(LinkedList<Position> pack, int movableRow, int selectedCol, int middleRow){
		
		int middleCol;
		
		if(movableRow>=0 && movableRow<8){
			//right upper Corner
			if(selectedCol>=0 && selectedCol<6){
				Position rightCorner = positions[movableRow][selectedCol+2];				
				middleCol = (selectedCol+selectedCol+2)/2;				
				if(rightCorner.getPlayerID()==0 && isOpponentInbetween(middleRow, middleCol)){
					rightCorner.setPossibleToMove(true);
					pack.add(rightCorner);
				}
			}
			
			//left upper corner
			if(selectedCol>1 && selectedCol <=7){
				Position leftCorner = positions[movableRow][selectedCol-2];
				middleCol = (selectedCol+selectedCol-2)/2;
				if(leftCorner.getPlayerID()==0 && isOpponentInbetween(middleRow, middleCol)){
					leftCorner.setPossibleToMove(true);
					pack.add(leftCorner);
				}
			}
		}
	}
	
	private boolean isOpponentInbetween(int row, int col){
		return positions[row][col].isOpponentPosition();
	}
}
