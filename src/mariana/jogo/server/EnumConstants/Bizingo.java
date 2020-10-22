package mariana.jogo.server.EnumConstants;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Server Application
 * Game Constants
 */
public enum Bizingo {	
	
	NUM_ROWS(13),
	NUM_COLS(23),
	TOTAL_PIECESN(16), //normal pieces
	TOTAL_PIECESC(2),  //captain
	LIGHT_COLOR(75),
	DARK_COLOR(82),
	PLAYABLE_CELL(157),
//	TOTAL_SQUARES(64),
//	PLAYABLE_SQUARES(32),
	EMPTY_POSITION(0),
	PLAYER_ONE(1),
	PLAYER_TWO(2),
	YOU_WIN(90),
	YOU_LOSE(91),
	RESTART(92),
	GIVE_UP(93);

	
	private int value;
	
	private Bizingo(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return this.value;
	}
	
	
	/* Some more Constants 
	  public static int PLAYER1_WON = 1; // Indicate player 1 won
	  public static int PLAYER2_WON = 2; // Indicate player 2 won
	  public static int DRAW = 3; // Indicate a draw
	  public static int CONTINUE = 4; // Indicate to continue
	  */
}
