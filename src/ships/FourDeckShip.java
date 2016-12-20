package ships;

import java.util.Collection;

import board.*;

public class FourDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 1;
	public static final int NUMBER_OF_DECKS = 4;
	private static int currectNumberOfShips;
	
	private FourDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	private FourDeckShip(Collection<BoardCell> board) {
		super(board, NUMBER_OF_DECKS);
		currectNumberOfShips++;
	}
	
	public static FourDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > NUMBER_OF_DECKS) {
			throw new IllegalStateException("Four deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new FourDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " four-deck ship");
		}
	}
	
//	public static FourDeckShip buildShip(Collection<BoardCell> decks) {
//		return buildShip(decks.toArray(new BoardCell[decks.size()]));
//	}
	
	public static FourDeckShip buildShip(Collection<BoardCell> board) {
		return new FourDeckShip(board);
	}
}