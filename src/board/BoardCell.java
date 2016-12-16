package board;

enum BoardCellState {EMPTY, ALIVE, HIT, DESTROYED};

public class BoardCell {
	private char rowCoordinate;
	private int columnCoordinate;
	private BoardCellState state;
	
	public BoardCell(char rowCoordinate, int columnCoordinate, BoardCellState state) {
		this.rowCoordinate = rowCoordinate;
		this.columnCoordinate = columnCoordinate;
		this.state = state;
	}
	
	public void setState(BoardCellState state) {
		this.state = state;
	}
	
	public BoardCellState getState() {
		return state;
	}
	
	public char getRowCoordinate() {
		return rowCoordinate;
	}
	
	public int getColumnCoordinate() {
		return columnCoordinate;
	}
	
	@Override
	public boolean equals(Object otherBoardCell) {
		BoardCell cellToCompare = (BoardCell) otherBoardCell;
 		if (this.getRowCoordinate() == cellToCompare.getRowCoordinate() &&
 				this.getColumnCoordinate() == cellToCompare.getColumnCoordinate()) {
 			return true;
 		} else {
 			return false;
 		}
	}
}