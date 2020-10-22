package mariana.jogo.server.Model;

import mariana.jogo.server.EnumConstants.*;
/**
 * Server Application -> Board
 */
public class Position {
	
	private int PositionID;
	private int PositionX;
	private int PositionY;	
	private boolean filled;
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

	public int getPositionID() {
		return PositionID;
	}

	public void setPositionID(int positionID) {
		PositionID = positionID;
	}

	public int getPositionX() {
		return PositionX;
	}

	public void setPositionX(int positionX) {
		PositionX = positionX;
	}

	public int getPositionY() {
		return PositionY;
	}

	public void setPositionY(int positionY) {
		PositionY = positionY;
	}
	
	

}