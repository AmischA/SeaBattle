/*
 *This class represents an abstract ship and encapsulates all needed behaviour 
 */

package ships;

import java.util.*;

import board.*;
import board.BoardCell.BoardCellState;

abstract public class Ship {
	public enum BuildingDirection {UP, RIGHT, DOWN, LEFT};
	
	private final int numberOfDecks;					
	private SortedSet<BoardCell> deckList;
	
	public Ship(BoardCell ...decks) {	
		if (decks.length < 1) {
			throw new IllegalArgumentException("Ship can't have a number of decks fewer than 1");
		}	
		this.numberOfDecks = decks.length;
		deckList = new TreeSet<>();
		buildShip(decks);
	}
	
	public Ship(Collection<BoardCell> board, int numberOfDecks) {
		this.numberOfDecks = numberOfDecks;
		deckList = new TreeSet<>();
		Collection<BoardCell> shipDecks = new ArrayList<>(generateShipDecks(board, numberOfDecks));
		buildShip(shipDecks.toArray(new BoardCell[0]));	
	}
	
	private void buildShip(BoardCell ...decks) {		
		Arrays.sort(decks);
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
	
	
	/*
	 * randomly generates a sequential number of decks for a ship, iterating through a list of available 
	 * empty board cells 
	 */
	private Collection<BoardCell> generateShipDecks(Collection<BoardCell> inputListOfCells, int howManyDecks) {
		Random randomNumberGenerator = new Random();
		List<BoardCell> board = new ArrayList<>(inputListOfCells);
		Collections.shuffle(board);
		
		for (BoardCell cell : board) {
			if (cell.getState() != BoardCellState.EMPTY) {
				continue;
			}
			List<BuildingDirection> directions = new ArrayList<>(Arrays.asList(BuildingDirection.values()));
			while(!directions.isEmpty()) {
				BuildingDirection currentDirection = directions.remove(randomNumberGenerator.nextInt(directions.size()));
				Collection<BoardCell> shipDecks = tryToBuildShip(board, cell, howManyDecks, currentDirection);
				if (shipDecks != null) {
					return shipDecks;
				}
			}
		}
		throw new IllegalStateException("Can't find a place on the board to build a ship with so many decks");
	}
	
	/*
	 * tries to build a ship if it's possible for the current board starting from known cell 
	 * with given number of decks and building direction (UP, DOWN, LEFT or RIGHT).
	 * Returns a collection of decks if can build a ship.
	 * Returns null if can't build a ship  
	 */
	private Collection<BoardCell> tryToBuildShip(List<BoardCell> board, BoardCell currentCell, 
													int howManyDecks, BuildingDirection direction) {
		Collection<BoardCell> result = new HashSet<>();
		for (int i = 0; i < howManyDecks; i++) {
			BoardCell neighbourCell = nextNeighbour(currentCell, direction);			
			if (board.contains(neighbourCell)) {
				result.add(neighbourCell);
				currentCell = neighbourCell;
			} else {
				return null;
			}
		}
		return result;
	}
	
	private BoardCell nextNeighbour(BoardCell cell, BuildingDirection direction) {
		switch(direction) {
			case UP: return cell.getUpperNeighbour();
			case RIGHT: return cell.getRightNeighbour();
			case DOWN: return cell.getLowerNeighbour();
			default: return cell.getLeftNeighbour();
		}
	}
	
	public int getNumberOfDecks() {
		return numberOfDecks;
	}
	
	public void setDeckState(BoardCell cell, BoardCellState state) {
		for (BoardCell deck : getDeckList()) {
			if (deck.equalsWithoutState(cell)) {
				deck.setState(state);
				return;
			}
		}
		throw new IllegalArgumentException("Can't set a deck's state. This ship doesn't have suck deck");
	}
	
	public boolean isAlive() {
		int countAliveDecks = 0;
		for (BoardCell deck : getDeckList()) {
			if (deck.getState() == BoardCellState.ALIVE) {
				countAliveDecks++;
			}
		}
		return countAliveDecks > 0;
	}
	
	public ArrayList<BoardCell> getDeckList() {
		return new ArrayList<>(deckList);
	}
	
	@Override
	public boolean equals(Object otherShip) {
		Ship shipToCompare = (Ship) otherShip;
		return this.getDeckList().equals(shipToCompare.getDeckList());
	}
}