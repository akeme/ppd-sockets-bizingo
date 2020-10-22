package mariana.jogo.client.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import mariana.jogo.client.Model.Position;
import mariana.jogo.client.EnumConstants.*;
import mariana.jogo.client.Handler.MyMouseListener;

/**
 * Client Application -> PositionPanel
 * @author Keerthikan
 * 
 * Position Panel
 */
public class PositionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private Position position;
	private Border defaultBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.black, Color.gray);
	//private Border focusedBorder = BorderFactory.createEtchedBorder(WHEN_FOCUSED, Color.white, Color.gray);
	private Border thickBorder = BorderFactory.createLineBorder(Colors.PURPLE.getColor(),5);
	private boolean focused;
	private MouseHandler handler;
	
	//Constructor
	public PositionPanel(Position s){
		this.position = s;
		this.focused = false;
		handler = new MouseHandler();
		
		setListener();		
	}
	
	protected void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponents(g2);
		
		//Fill black color
		g2.setColor(Colors.BLACK.getColor());
		if(position.getIsFilled()){
			g2.fillRect(0, 0, getWidth(), getHeight());
		}
		
		//fill piece color
		int positionplayerID = position.getPlayerID();
		if(isClicked()){
			g2.setColor(Colors.getFocusedColor(positionplayerID));
			paint(g2);
		}else{
			if(positionplayerID==1 || positionplayerID == 2){
				if(focused){
					g2.setColor(Colors.getFocusedColor(positionplayerID));
				}else{
					g2.setColor(Colors.getMyDefaultColor(positionplayerID));					
				}
				paint(g2);
			}
		}
		
		//Hover effect for possible move
		if(position.isPossibleToMove()){
			if(focused){				
				setBorder(thickBorder);
			}else{				
				setBorder(null);
			}
		}else{
			setBorder(null);
		}
		
		//King
		if(position.isKing() && position.getIsFilled()){
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Arial",Font.BOLD,25));
			g2.drawString("K", getWidth()/2-8, getHeight()/2+8);
		}
	}
	
	public void setListener(){
		if(position.isPossibleToMove() || position.getPlayerID()==SessionVariable.myID.getValue()){
			this.removeMouseListener(handler);
			this.addMouseListener(handler);
		}else{
			this.removeMouseListener(handler);
		}
	}
	
	public void setListner(MyMouseListener MyListner){
		setListener();
		if(position.isPossibleToMove() || position.getPlayerID()==SessionVariable.myID.getValue()){
			this.removeMouseListener(MyListner);
			this.addMouseListener(MyListner);
		}else{
			this.removeMouseListener(MyListner);
		}
	}
	
	//return Position
	public Position getPosition(){
		return this.position;
	}
	
	//return clicked
	public boolean isClicked(){
		return this.position.isSelected();
	}
	
	//reset clicked to false
	public void resetClicked(){
		this.position.setSelected(false);
	}
	
	//reset clicked to true
	public void setClicked(){
		this.position.setSelected(true);
	}
	
	
	private void paint(Graphics2D g2){
		int padding= 24;
		g2.fillOval(padding/2, padding/2, getWidth()-padding, getHeight()-padding);			
	}
	
	/**
	 * My Mouse Listener 
	 */
	class MouseHandler extends MouseAdapter {
		
		public void mouseEntered(MouseEvent e){	
			super.mouseEntered(e);
			focused = true;
			repaint();
		}
		
		public void mouseExited(MouseEvent e){
			super.mouseExited(e);
			focused = false;
			repaint();
		}
		
		public void mousePressed(MouseEvent e) {
			/*
			if(isClicked()){
				System.out.println("deselect - "+position.getPositionID());
				resetClicked();
				repaint();
			}
			else if(position.getPlayerID()==SessionVariable.myID.getValue()){
				System.out.println("select - "+position.getPositionID());
				setClicked();
				repaint();		
			}*/
		}
	}
}
