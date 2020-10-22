package mariana.jogo.client.Handler;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.border.Border;


import mariana.jogo.client.Model.Position;
import mariana.jogo.client.View.PositionPanel;

public class MyMouseListener extends MouseAdapter{
	
	private PositionPanel positionPanel;
	private Controller controller;
	
	public void setController(Controller c){
		this.controller = c;
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		
		
		try{			
			if(controller.isHisTurn()){
				ToggleSelectPiece(e);
			}else{
				JOptionPane.showMessageDialog(null, "Not your turn",
					"Error", JOptionPane.ERROR_MESSAGE, null);
			}
		}catch(Exception ex){
			System.out.println("Error");
		}	
		
		
	}
	
	private void ToggleSelectPiece(MouseEvent e){
		try{
			positionPanel = (PositionPanel) e.getSource();
			Position s = positionPanel.getPosition();
			
			//if position is already selected - deselect
			if(s.isSelected()){
				System.out.println("deselect - "+s.getPositionID());
				controller.positionDeselected();				
			}
			//else select
			else{
				System.out.println("select - "+s.getPositionID());
				controller.positionSelected(s);
			}
		}catch(Exception ex){
			System.out.println("error");
		}
	}
}
