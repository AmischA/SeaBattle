package board;

public class BoardCell implements Comparable<BoardCell> {
	public enum BoardCellState {EMPTY, ADJACENT, ALIVE, HIT, DESTROYED};
	private Character rowCoordinate;
	private Integer columnCoordinate;
	private BoardCellState state;
	
	public BoardCell(Character rowCoordinate, Integer columnCoordinate, BoardCellState state) {
		this.rowCoordinate = Character.toUpperCase(rowCoordinate);
		this.columnCoordinate = columnCoordinate;
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
	
	public Character getRowCoordinate() {
		return rowCoordinate;
	}
	
	public Integer getColumnCoordinate() {
		return columnCoordinate;
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
 		if (this.getRowCoordinate() == cellToCompare.getRowCoordinate() &&
 				this.getColumnCoordinate() == cellToCompare.getColumnCoordinate()) {
 			return true;
 		} else {
 			return false;
 		}
	}
}