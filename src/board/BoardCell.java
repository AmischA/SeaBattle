/*
 *Single board cell on the game board
 */

package board;

public class BoardCell implements Comparable<BoardCell> {
	public enum BoardCellState {EMPTY, ADJACENT, ALIVE, HIT, MISSED};
	
	private Coordinates coordinates;
	private BoardCellState state;
	
	public BoardCell(Character rowCoordinate, Integer columnCoordinate, BoardCellState state) {
		coordinates = new Coordinates(Character.toUpperCase(rowCoordinate), columnCoordinate);
		this.state = state;
	}
	
	public BoardCell(Character rowCoordinate, Integer columnCoordinate) {
		this(rowCoordinate, columnCoordinate, BoardCellState.EMPTY);
	}
	
	public void setState(BoardCellState state) {
		this.state = state;
	}
	
	public BoardCellState getState() {
		return state;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
	public Character getRowCoordinate() {
		return coordinates.getRowCoordinate();
	}
	
	public Integer getColumnCoordinate() {
		return coordinates.getColumnCoordinate();
	}
	
	public BoardCell getLeftNeighbour() {
		return new BoardCell(getRowCoordinate(), getColumnCoordinate() - 1);
	}
	
	public BoardCell getUpperLeftNeighbour() {
		return new BoardCell((char) (getRowCoordinate() - 1), getColumnCoordinate() - 1);
	}
	
	public BoardCell getLowerLeftNeighbour() {
		return new BoardCell((char) (getRowCoordinate() + 1), getColumnCoordinate() - 1);
	}
	
	public BoardCell getRightNeighbour() {
		return new BoardCell(getRowCoordinate(), getColumnCoordinate() + 1);
	}
	
	public BoardCell getUpperRightNeighbour() {
		return new BoardCell((char) (getRowCoordinate() - 1), getColumnCoordinate() + 1);
	}
	
	public BoardCell getLowerRightNeighbour() {
		return new BoardCell((char) (getRowCoordinate() + 1), getColumnCoordinate() + 1);
	}
	
	public BoardCell getUpperNeighbour() {
		return new BoardCell((char) (getRowCoordinate() - 1), getColumnCoordinate());
	}
	
	public BoardCell getLowerNeighbour() {
		return new BoardCell((char) (getRowCoordinate() + 1), getColumnCoordinate());
	}
	
	@Override
	public String toString() {
		return "(" + getRowCoordinate() + ", " + getColumnCoordinate() + ", " + getState() + ")";
	}
	
	@Override
	public int compareTo(BoardCell cellToCompare) {
		int compareRowCoordinates = this.getRowCoordinate().compareTo(cellToCompare.getRowCoordinate());	
		
		if (compareRowCoordinates == 0) {
			int compareColumnCoordinates = this.getColumnCoordinate().compareTo(cellToCompare.getColumnCoordinate());
			return compareColumnCoordinates;
		}
		 	
		return compareRowCoordinates;
	}
	
	@Override
	public boolean equals(Object otherBoardCell) {
		BoardCell cellToCompare = (BoardCell) otherBoardCell;
		if (equalsWithoutState(cellToCompare)) {
			return this.getState() == cellToCompare.getState();
		} else {
			return false;
		}
	}
	
	public boolean equalsWithoutState(BoardCell cellToCompare) {
 		if (this.getCoordinates().equals(cellToCompare.getCoordinates())) {
 			return true;
 		} else {
 			return false;
 		}
	}
}