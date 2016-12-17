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
		board = new TreeSet<>();
		shipList = new ArrayList<>();
		fillBoardWithEmptyCells();
	}
	
	private void fillBoardWithEmptyCells() {
		for (Character row = 0; row < boardHeight; row++) {
			for (Integer column = 0; column < boardWidth; column++) {
				board.add(new BoardCell((char) ('A' + row), column));
			}
		}
	}
	
	public NavigableSet<BoardCell> getBoard() {
		return new TreeSet<>(board);
	}
	
	public void addShip(Ship newShip) {
		if (state == BoardState.ACTIVE && canAddShip(newShip)) {
			shipList.add(newShip);
			for (BoardCell deck : newShip.getDeckList()) {
				BoardCell cellOnBoard = findCell(deck);
				cellOnBoard.setState(BoardCell.BoardCellState.ALIVE);
				setAdjacentCells(deck);
			}
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
	
	public void setAdjacentCells(BoardCell currentCell) {
		
		BoardCell leftCell = currentCell.getLeftNeighbour();
		BoardCell upperLeftCell = currentCell.getUpperLeftNeighbour();
		BoardCell lowerLeftCell = currentCell.getLowerLeftNeighbour();
		BoardCell rightCell = currentCell.getRightNeighbour();	
		BoardCell upperRightCell = currentCell.getUpperRightNeighbour();
		BoardCell lowerRightCell = currentCell.getLowerRightNeighbour();
		BoardCell upperCell = currentCell.getUpperNeighbour();
		BoardCell lowerCell = currentCell.getLowerNeighbour();
		
		ArrayList<BoardCell> listOfAdjacentCells = new ArrayList<>();
		Collections.addAll(listOfAdjacentCells, leftCell, upperLeftCell, lowerLeftCell, rightCell, upperRightCell,
							lowerRightCell, upperCell, lowerCell);
		
		setCellsState(listOfAdjacentCells, BoardCell.BoardCellState.ADJACENT);
	}
	
	private void setCellsState(Collection<BoardCell> inputCellList, BoardCell.BoardCellState toWhatStateToSet) {
		for (BoardCell inputCell : inputCellList) {
			BoardCell cellOnBoard = findCell(inputCell);
			if (cellOnBoard != null && cellOnBoard.getState() == BoardCell.BoardCellState.EMPTY) {
				cellOnBoard.setState(toWhatStateToSet);
			}
		}
	}
	
	private BoardCell findCell(BoardCell cellToFind) {
		for (BoardCell currentCell : board) {
			if (currentCell.equalsWithoutState(cellToFind)) {
				return currentCell;
			}
		}
		return null;
	}
	
	public void removeShip(Ship shipToRemove) {
		shipList.remove(shipToRemove);
	}
}