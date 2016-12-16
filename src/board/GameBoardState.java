package board;

import java.util.*;

import Ships.Ship;

public class GameBoardState {
	private ArrayList<Ship> shipList;
	private NavigableSet<BoardCell> board;
	
	public GameBoardState(Collection<BoardCell> board) {
		this.board = new TreeSet<>(board);
	}
	
	public void addShip(Ship newShip) {
		if (canAddShip(newShip)) {
			shipList.add(newShip);
		} else {
			throw new IllegalStateException("Can't add a ship to the board");
		}
	}
	
	private boolean canAddShip(Ship ship) {
		ArrayList<BoardCell> deckList = ship.getDeckList();
		for (BoardCell deck : deckList) {
			if (!canAddDeck(deck)) {
				return false;
			}
		}
		return true;
	}
	
	private boolean canAddDeck(BoardCell deck) {
		if (!board.contains(deck)) {
			return false;
		}
		if (board.ceiling(deck).getState() != BoardCellState.EMPTY) {
			return false;
		}
		return true;
	}
	
	public void removeShip(Ship shipToRemove) {
		shipList.remove(shipToRemove);
	}
	
	public static void main(String[] args) {
		BoardCell cell1 = new BoardCell('A', 1, BoardCellState.DESTROYED);
		BoardCell cell2 = new BoardCell('A', 15, BoardCellState.ALIVE);
		
	}		
}