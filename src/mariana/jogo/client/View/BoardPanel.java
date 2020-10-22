package mariana.jogo.client.View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.LinkedList;

import javax.sound.midi.ControllerEventListener;
import javax.swing.*;


import mariana.jogo.client.Model.Board;
import mariana.jogo.client.Model.Position;
import mariana.jogo.client.EnumConstants.SessionVariable;
import mariana.jogo.client.Handler.*;

/**
 * Client Application -> ClientApp
 * 
 * ClientApp
 */
public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Dimension panelSize = new Dimension(720,720);
	private Board boardModel;
	private MyMouseListener listener;
	private LinkedList<PositionPanel> panels;
	private Position[][] positions;

	public BoardPanel(MyMouseListener listener){
		setPreferredSize(panelSize);
		setLayout(new GridLayout(13,23));
		
		boardModel = new Board();
		positions = boardModel.getPositions();
		this.listener = listener;		
		panels = new LinkedList<PositionPanel>();		
		
		initializePositionPanels();
		
		System.out.println(boardModel.getTotlaPositions());		
	}
	
	private void initializePositionPanels() {
		for(int i=0;i<8;i++){
			for(int k=0;k<8;k++){
				PositionPanel sPanel = new PositionPanel(positions[i][k]);
				if(sPanel.getPosition().isPossibleToMove() || sPanel.getPosition().getPlayerID()==SessionVariable.myID.getValue()){
					sPanel.addMouseListener(listener);
				}
				panels.add(sPanel);
				add(sPanel);				
			}			
		}
	}
	
	public void repaintPanels(){
		for(PositionPanel panel : panels){
			panel.setListner(listener);	
		}
		
		repaint();
	}

	public LinkedList<Position> getPlayablePositions(Position s){
		return boardModel.findPlayablePositions(s);		
	}
	
	public Position getPosition(int ID){
		return panels.get(ID-1).getPosition();
	}
}
