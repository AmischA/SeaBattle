package ships;

import java.util.*;

import board.BoardCell;

public class Ship {
	public enum ShipState {ALIVE, DESTROYED};

	public static final int ONE_DECK_SHIP = 4;
	public static final int TWO_DECK_SHIP = 3;
	public static final int THREE_DECK_SHIP = 2;
	public static final int FOUR_DECK_SHIP = 1;
	
	private final int numberOfDecks;
	private ShipState state;							
	private ArrayList<BoardCell> deckList;
	
	public Ship(int numberOfDecks) {		
		this.numberOfDecks = numberOfDecks;
	}
	
	public Ship(int numberOfDecks, BoardCell ...decks) {		
		this.numberOfDecks = numberOfDecks;
		buildShip(decks);
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
	
	public ArrayList<BoardCell> getDeckList() {
		return new ArrayList<>(deckList);
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