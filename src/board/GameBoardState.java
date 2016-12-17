package board;

import java.util.*;

import ships.Ship;
import board.BoardCell.*;

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
	
	public NavigableSet<BoardCell> getCells() {
		return new TreeSet<>(board);
	}
	
	public List<BoardCell> getEmptyCells() {
		List<BoardCell> result = new ArrayList<>();
		for (BoardCell cell : getCells()) {
			if (cell.getState() == BoardCellState.EMPTY) {
				result.add(cell);
			}
		}
		return result;
	}
	
	// is the cell does not exists null is returned
	public BoardCell.BoardCellState getCellState(BoardCell cellToTest) {
		return findCell(cellToTest).getState();
	}
	
	public void addShip(Ship newShip) {
		if (state == BoardState.ACTIVE && canAddShip(newShip)) {
			shipList.add(newShip);
			for (BoardCell deck : newShip.getDeckList()) {
				BoardCell cellOnBoard = findCell(deck);
				cellOnBoard.setState(BoardCellState.ALIVE);
				setAdjacentCells(deck, BoardCellState.ADJACENT);
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
		if (board.ceiling(deck).getState() != BoardCellState.EMPTY) {
			return false;
		}
		return true;
	}
	
	private void setAdjacentCells(BoardCell currentCell, BoardCellState toWhatStateToSet) {
		
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
		
		setCellsState(listOfAdjacentCells, toWhatStateToSet);
	}
	
	private void setCellsState(Collection<BoardCell> inputCellList, BoardCellState toWhatStateToSet) {
		for (BoardCell inputCell : inputCellList) {
			BoardCell cellOnBoard = findCell(inputCell);
			if (cellOnBoard != null && cellOnBoard.getState() == BoardCellState.EMPTY) {
				cellOnBoard.setState(toWhatStateToSet);
			}
		}
	}
	
	public BoardCell findCell(BoardCell cellToFind) {
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