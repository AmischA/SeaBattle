package ships;

import board.BoardCell;

public class FourDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 1;
	private static int currectNumberOfShips;
	
	private FourDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	public static FourDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > 4) {
			throw new IllegalStateException("Four deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new FourDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " four-deck ship");
		}
	}
}