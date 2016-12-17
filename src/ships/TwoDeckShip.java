package ships;

import board.BoardCell;

public class TwoDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 3;
	private static int currectNumberOfShips;
	
	private TwoDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	public static TwoDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > 2) {
			throw new IllegalStateException("Two deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new TwoDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " two-deck ships");
		}
	}
}