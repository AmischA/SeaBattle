package ships;

import java.util.Collection;

import board.*;

public class OneDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 4;
	public static final int NUMBER_OF_DECKS = 1;
	private static int currectNumberOfShips;
	
	private OneDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	private OneDeckShip(Collection<BoardCell> board) {
		super(board, NUMBER_OF_DECKS);
		currectNumberOfShips++;
	}
	
	public static OneDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > NUMBER_OF_DECKS) {
			throw new IllegalStateException("One deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new OneDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " one-deck ships");
		}
	}
	
//	public static OneDeckShip buildShip(Collection<BoardCell> decks) {
//		return buildShip(decks.toArray(new BoardCell[decks.size()]));
//	}
	
	public static OneDeckShip buildShip(Collection<BoardCell> board) {
		return new OneDeckShip(board);
	}
}