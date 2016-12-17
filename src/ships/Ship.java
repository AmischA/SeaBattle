/*
 *This class represents an abstract ship and encapsulates all needed behaviour 
 */

package ships;

import java.util.*;

import board.BoardCell;
import board.BoardCell.BoardCellState;

abstract public class Ship {
	public enum ShipState {ALIVE, DESTROYED};
	
	private final int numberOfDecks;
	private ShipState state;							
	private SortedSet<BoardCell> deckList;
	
	public Ship(int numberOfDecks, BoardCell ...decks) {	
		if (numberOfDecks < 1) {
			throw new IllegalArgumentException("Ship can't have a number of decks fewer than 1");
		}
		if (numberOfDecks != decks.length) {
			throw new IllegalArgumentException("Number of decks doesn't match");
		}
		
		this.numberOfDecks = numberOfDecks;
		state = ShipState.ALIVE;
		deckList = new TreeSet<>();
		Arrays.sort(decks);
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
		} else {
			throw new IllegalArgumentException("Can't build a ship from given coordinates");
		}
		
		if (deckList.size() > numberOfDecks) {
			throw new IllegalStateException("Ship can't have more than " + getNumberOfDecks()
												+ " decks");
		} 
	}
	
	private boolean canAddDeck(BoardCell deckToAdd) {
		if (deckList.size() == 0) {
			return true;
		} else if (deckList.size() > 0) {
			for (BoardCell cellOnBoard : generatePositionsToAddDeck(deckList)) {
				if (deckToAdd.equals(cellOnBoard)) {
					return true;
				}
			}
			return false;	
		} else {
			return false;
		}
	}
	
	// generate all positions on the board where a new ship's deck can be added based on current ship's decks
	private Collection<BoardCell> generatePositionsToAddDeck(SortedSet<BoardCell> currentDeckList) {	
		Collection<BoardCell> generatedPositions = new TreeSet<BoardCell>();		
		if (currentDeckList.size() == 1) {
			BoardCell singleDeck = currentDeckList.first();					
			Collections.addAll(generatedPositions, singleDeck.getLeftNeighbour(), singleDeck.getRightNeighbour(),
								singleDeck.getUpperNeighbour(), singleDeck.getLowerNeighbour());												
		} else if (currentDeckList.size() > 1) {
			BoardCell firstDeck = currentDeckList.first();
			BoardCell lastDeck = currentDeckList.last();						
			if (firstDeck.getRowCoordinate() == lastDeck.getRowCoordinate()) {
				Collections.addAll(generatedPositions, firstDeck.getLeftNeighbour(), lastDeck.getRightNeighbour());
			} else if (firstDeck.getColumnCoordinate() == lastDeck.getColumnCoordinate()) {
				Collections.addAll(generatedPositions, firstDeck.getUpperNeighbour(), lastDeck.getLowerNeighbour());
			}
		}
		return generatedPositions;
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
}