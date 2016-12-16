package board;

import java.util.*;

import ships.Ship;

public class GameBoardState {
	public enum BoardState {ACTIVE, FINISHED};

	private final int boardWidth;
	private final int boardHeight;
	private ArrayList<Ship> shipList;
	private NavigableSet<BoardCell> board;
	private BoardState state;
	
	public GameBoardState(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.state = BoardState.ACTIVE;
		fillBoardWithEmptyCells();
	}
	
	private void fillBoardWithEmptyCells() {
		for (Character row = 0; row < boardHeight; row++) {
			for (Integer column = 0; column < boardWidth; column++) {
				board.add(new BoardCell((char) ('A' + row), column));
			}
		}
	}
	
	public void addShip(Ship newShip) {
		if (canAddShip(newShip)) {
			shipList.add(newShip);
			board.addAll(newShip.getDeckList());
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
		if (board.ceiling(deck).getState() != BoardCell.BoardCellState.EMPTY) {
			return false;
		}
		return true;
	}
	
	public void setBoardCell(BoardCell.BoardCellState state) {
		
	}
	
	private BoardCell findCell(BoardCell cellToFind) {
		for (BoardCell currentCell : board) {
			if (currentCell.equals(cellToFind)) {
				return currentCell;
			}
		}
		return null;
	}
	
	public void removeShip(Ship shipToRemove) {
		shipList.remove(shipToRemove);
	}
}