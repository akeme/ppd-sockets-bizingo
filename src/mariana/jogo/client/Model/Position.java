package mariana.jogo.client.Model;

import mariana.jogo.client.EnumConstants.*;

/**
 * Client Application -> ClientApp
 * 
 * ClientApp
 */


public class Position {
	
	private int PositionID;
	private int PositionX;
	private int PositionY;	
	private boolean isKing;
	private boolean filled;
	private boolean selected;
	private boolean isPossibleToMove;
	private int playerID;	
	
	//Constructor
	public Position(int PositionID, int PositionX, int PositionY, boolean isFilled){
		this.PositionID=PositionID;
		this.PositionX=PositionX;
		this.PositionY=PositionY;
		this.setFilled(isFilled);
		
		if(this.filled){
			this.playerID = Bizingo.EMPTY_POSITION.getValue();
		}
		
		this.isKing = false;
		this.selected = false;
		this.isPossibleToMove = false;
	}

	public boolean getIsFilled() {
		return filled;
	}

	private void setFilled(boolean filled) {
		this.filled = filled;
	}
	
	public void setPlayerID(int ID){
		this.playerID=ID;
	}
	
	public int getPlayerID(){
		return this.playerID;
	}
	
	public int getPositionID(){
		return this.PositionID;
	}
	
	public int getPositionX(){
		return this.PositionX;
	}
	
	public int getPositionY(){
		return this.PositionY;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public boolean isPossibleToMove() {
		return isPossibleToMove;
	}

	public void setPossibleToMove(boolean isPossibleToMove) {
		this.isPossibleToMove = isPossibleToMove;
	}
	
	public boolean isOpponentPosition(){
		if(playerID!=SessionVariable.myID.getValue() && playerID!=Bizingo.EMPTY_POSITION.getValue())
			return true;
		else
			return false;
	}

	public boolean isKing() {
		return isKing;
	}

	public void setKing() {
		this.isKing = true;
	}
	
	public void removeKing(){
		this.isKing = false;
	}
}
