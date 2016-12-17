package ships;

import java.util.*;

import board.BoardCell;
import board.BoardCell.BoardCellState;

abstract public class Ship {
	public enum ShipState {ALIVE, DESTROYED};
	
	private final int numberOfDecks;
	private ShipState state;							
	private ArrayList<BoardCell> deckList;
	
	public Ship(int numberOfDecks, BoardCell ...decks) {	
		if (numberOfDecks < 1) {
			throw new IllegalArgumentException("Ship can't have a number of decks fewer than 1");
		}
		if (numberOfDecks != decks.length) {
			throw new IllegalArgumentException("Number of decks doesn't match");
		}
		
		this.numberOfDecks = numberOfDecks;
		state = ShipState.ALIVE;
		deckList = new ArrayList<>();
		buildShip(decks);
	}
	
	public Ship(BoardCell ...decks) {
		this(decks.length, decks);
	}
	
	private void buildShip(BoardCell ...decks) {
		for (BoardCell newDeck : decks) {
			addDeck(newDeck);
		}
	}
	
	private void addDeck(BoardCell deckToAdd) {
		if (canAddDeck(deckToAdd)) {
			deckList.add(deckToAdd);
		}
		if (deckList.size() > numberOfDecks) {
			throw new IllegalStateException("Ship can't have more than " + getNumberOfDecks()
												+ " decks");
		} 
	}
	
	private boolean canAddDeck(BoardCell deckToAdd) {
		Character deckToAddRowCoordinate = deckToAdd.getRowCoordinate();
		Integer deckToAddColumnCoordinate = deckToAdd.getColumnCoordinate();
		if (deckList.size() == 0) {
			return true;
		} else if (deckList.size() == 1) {
			char directionOne = deckList.get(0).getRowCoordinate();
			int directionTwo = deckList.get(0).getColumnCoordinate();
			if (deckToAddRowCoordinate == directionOne  && deckToAddColumnCoordinate == directionTwo|| deckToAdd.getColumnCoordinate() == directionTwo) {
				return true;
			}
		} else if (deckList.size() > 1) {
			if (deckList.get(0).getRowCoordinate() == deckList.get(1).getRowCoordinate()) {
				if ((deckList.get(0).getColumnCoordinate() == deckToAdd.getColumnCoordinate()) 
						&& deckList.get(deckList.size()).getRowCoordinate() == deckToAdd.getRowCoordinate()) {
					return true;
				}
			} else if (deckList.get(0).getColumnCoordinate() == deckList.get(1).getColumnCoordinate()) {
				if (deckList.get(0).getColumnCoordinate() == deckToAdd.getColumnCoordinate()
						&& deckList.get(deckList.size()).getRowCoordinate() == deckToAdd.getRowCoordinate()) {
					return true;
				}
			}
	
		}
		throw new IllegalArgumentException("Can't build a ship from given coordinates");
	}
	
//	private ArrayList<BoardCell> generatePossitionsToAddDeck() {
//		if (deckList.size() == 1) {
//			Character rowCoordinate = deckList.get(0).getRowCoordinate();
//			Integer columnCoordinate = deckList.get(0).getColumnCoordinate();
//			
//		}
//	}
	
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
}