package ships;

import board.BoardCell;

public class OneDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 4;
	private static int currectNumberOfShips;
	
	private OneDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	public static OneDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > 1) {
			throw new IllegalStateException("One deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new OneDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " one-deck ships");
		}
	}
}