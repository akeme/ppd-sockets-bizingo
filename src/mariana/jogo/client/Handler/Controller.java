package mariana.jogo.client.Handler;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import mariana.jogo.client.EnumConstants.Bizingo;
import mariana.jogo.client.Model.*;
import mariana.jogo.client.View.BoardPanel;



/**
 * Client Application -> Controller
 * 
 * ClientApp
 */public class Controller implements Runnable {
		private boolean continueToPlay;
		private boolean waitingForAction;
		private boolean isOver;
		
		//Network
		private DataInputStream fromServer;
		private DataOutputStream toServer;
		
		private BoardPanel boardPanel;
		private Player player;
		
		//Data
		private LinkedList<Position> selectedPositions;
		private LinkedList<Position> playablePositions;
		//private LinkedList<Position> crossablePositions;
		
		public Controller(Player player, DataInputStream input, DataOutputStream output){
			this.player = player;
			this.fromServer = input;
			this.toServer= output;
			
			selectedPositions = new LinkedList<Position>();
			playablePositions = new LinkedList<Position>();
		}
		
		public void setBoardPanel(BoardPanel panel){
			this.boardPanel = panel;
		}
		
		@Override
		public void run() {
			continueToPlay = true;
			waitingForAction = true;
			isOver=false;
			
			try {
				
				//Player One
				if(player.getPlayerID()==Bizingo.PLAYER_ONE.getValue()){
					//wait for the notification to start
					fromServer.readInt();
					player.setMyTurn(true);
				}
						
				while(continueToPlay && !isOver){
					if(player.getPlayerID()==Bizingo.PLAYER_ONE.getValue()){
						waitForPlayerAction();
						if(!isOver)
							receiveInfoFromServer();
					}else if(player.getPlayerID()==Bizingo.PLAYER_TWO.getValue()){
						receiveInfoFromServer();
						if(!isOver)
							waitForPlayerAction();
					}
				}
				
				if(isOver){
					JOptionPane.showMessageDialog(null, "Game is over",
							"Information", JOptionPane.INFORMATION_MESSAGE, null);
					System.exit(0);
				}
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Connection lost",
						"Error", JOptionPane.ERROR_MESSAGE, null);
				System.exit(0);
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "Connection interrupted",
						"Error", JOptionPane.ERROR_MESSAGE, null);
			}			
		}
		
		private void receiveInfoFromServer() throws IOException {
			player.setMyTurn(false);
			int from = fromServer.readInt();
			if(from==Bizingo.YOU_LOSE.getValue()){
				from = fromServer.readInt();
				int to = fromServer.readInt();
				updateReceivedInfo(from, to);
				isOver=true;
			}else if(from==Bizingo.YOU_WIN.getValue()){
				isOver=true;
				continueToPlay=false;
			}else{
				int to = fromServer.readInt();
				updateReceivedInfo(from, to);
			}
		}	

		private void sendMove(Position from, Position to) throws IOException {
			toServer.writeInt(from.getPositionID());
			toServer.writeInt(to.getPositionID());
		}

		private void waitForPlayerAction() throws InterruptedException {
			player.setMyTurn(true);
			while(waitingForAction){
				Thread.sleep(100);
			}
			waitingForAction = true;		
		}
		
		public void move(Position from, Position to){
			to.setPlayerID(from.getPlayerID());
			from.setPlayerID(Bizingo.EMPTY_POSITION.getValue());
			checkCrossJump(from, to);
			checkKing(from, to);
			positionDeselected();
			
			waitingForAction = false;
			try {
				sendMove(from, to);
			} catch (IOException e) {
				System.out.println("Sending failed");
			}		
		}
		
		//When a position is selected
		public void positionSelected(Position p) {
			
			if(selectedPositions.isEmpty()){
				addToSelected(p);
			}		
			//if one is already selected, check if it is possible move
			else if(selectedPositions.size()>=1){
				if(playablePositions.contains(p)){
					move(selectedPositions.getFirst(),p);
				}else{
					positionDeselected();
					addToSelected(p);
				}
			}
		}
		
		private void addToSelected(Position p){
			p.setSelected(true);
			selectedPositions.add(p);
			getPlayablePositions(p);
		}

		public void positionDeselected() {
			
			for(Position position:selectedPositions)
				position.setSelected(false);
			
			selectedPositions.clear();
			
			for(Position position:playablePositions){
				position.setPossibleToMove(false);
			}
			
			playablePositions.clear();
			boardPanel.repaintPanels();
		}
		
		
		private void getPlayablePositions(Position p){
			playablePositions.clear();		
			playablePositions = boardPanel.getPlayablePositions(p);
			
			for(Position position:playablePositions){
				System.out.println(position.getPositionID());
			}		
			boardPanel.repaintPanels();
		}
		
		public boolean isHisTurn(){
			return player.isMyTurn();
		}
		
		private void checkCrossJump(Position from, Position to){		
			if(Math.abs(from.getPositionX()-to.getPositionX())==2){		
				int middleRow = (from.getPositionX() + to.getPositionX())/2;
				int middleCol = (from.getPositionY() + to.getPositionY())/2;
				
				Position middlePosition = boardPanel.getPosition((middleRow*8)+middleCol+1);
				middlePosition.setPlayerID(Bizingo.EMPTY_POSITION.getValue());
				middlePosition.removeKing();
			}
		}
		
		private void checkKing(Position from, Position movedPosition){		
			if(from.isKing()){
				movedPosition.setKing();
				from.removeKing();
			}else if(movedPosition.getPositionX()==7 && movedPosition.getPlayerID()==Bizingo.PLAYER_ONE.getValue()){
				movedPosition.setKing();
			}else if(movedPosition.getPositionX()==0 && movedPosition.getPlayerID()==Bizingo.PLAYER_TWO.getValue()){
				movedPosition.setKing();
			}
		}
		
		private void updateReceivedInfo(int from, int to){
			Position fromPosition = boardPanel.getPosition(from);
			Position toPosition = boardPanel.getPosition(to);
			toPosition.setPlayerID(fromPosition.getPlayerID());
			fromPosition.setPlayerID(Bizingo.EMPTY_POSITION.getValue());
			checkCrossJump(fromPosition, toPosition);
			checkKing(fromPosition, toPosition);
			boardPanel.repaintPanels();
		}
	}
