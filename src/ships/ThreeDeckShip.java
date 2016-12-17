package ships;

import board.BoardCell;

public class ThreeDeckShip extends Ship {
	public static final int MAXIMUM_NUMBER_OF_SHIPS = 2;
	private static int currectNumberOfShips;
	
	private ThreeDeckShip(BoardCell ...decks) {
		super(decks);
		currectNumberOfShips++;
	}
	
	public static ThreeDeckShip buildShip(BoardCell ...decks) {
		if (decks.length > 3) {
			throw new IllegalStateException("Three deck ship can't have " + decks.length + " decks");
		}
		if (currectNumberOfShips < MAXIMUM_NUMBER_OF_SHIPS) {
			return new ThreeDeckShip(decks);
		} else {
			throw new IllegalStateException("Can't have more than " + MAXIMUM_NUMBER_OF_SHIPS + " three deck ships");
		}
	}
}