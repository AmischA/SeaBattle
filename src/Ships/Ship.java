package Ships;

import java.util.ArrayList;

import board.BoardCell;

enum ShipState {ALIVE, DESTROYED};

public class Ship {
	private static final int ONE_DECK_SHIP = 4;
	private static final int TWO_DECK_SHIP = 3;
	private static final int THREE_DECK_SHIP = 2;
	private static final int FOUR_DECK_SHIP = 1;
	
	private final int numberOfDecks;
	private ShipState state;							
	private ArrayList<BoardCell> deckList;
	
	public Ship(int numberOfDecks) {
		
		this.numberOfDecks = numberOfDecks;
	}
	
	public int getNumberOfDecks() {
		return numberOfDecks;
	}
	
	public void setState(ShipState state) {
		this.state = state; 
	}
	
	public ShipState getState() {
		return state;
	}
	
	public void buildShip(BoardCell ...decks) {
		for (BoardCell newDeck : decks) {
			addDeck(newDeck);
		}
		state = ShipState.ALIVE;
	}
	
	private void addDeck(BoardCell deckToAdd) {
		if (deckList.size() > numberOfDecks) {
			throw new IllegalStateException("Ship can't have more than " + getNumberOfDecks()
												+ " decks");
		} 
		deckList.add(deckToAdd);
	}
}