package ships;

import java.util.Collection;

import board.BoardCell;

public class ThreeDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 2;
	public static final int NUMBER_OF_DECKS = 3;
	private static int currectNumberOfShips;
	
	private ThreeDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	public static ThreeDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > NUMBER_OF_DECKS) {
			throw new IllegalStateException("Three deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new ThreeDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " three deck ships");
		}
	}
	
	public static ThreeDeckShip buildShip(Collection<BoardCell> decks) {
		return buildShip(decks.toArray(new BoardCell[decks.size()]));
	}
}