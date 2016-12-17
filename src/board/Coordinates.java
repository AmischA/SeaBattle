/*
 * class Coordinates<R, C>, where R - row coordinate type and
 * C - column coordinate type, encapsulates two coordinates on the board
 */

package board;

public class Coordinates {
	private Character rowCoordinate;
	private Integer columnCoordinate;
	
	public Coordinates(Character rowCoordinate, Integer columnCoordinate) {
		this.rowCoordinate = rowCoordinate;
		this.columnCoordinate = columnCoordinate;		
	}

	public Character getRowCoordinate() {
		return rowCoordinate;
	}

	public void setRowCoordinate(Character rowCoordinate) {
		this.rowCoordinate = rowCoordinate;
	}

	public Integer getColumnCoordinate() {
		return columnCoordinate;
	}

	public void setColumnCoordinate(Integer columnCoordinate) {
		this.columnCoordinate = columnCoordinate;
	}
	
	@Override
	public boolean equals(Object otherObject) {
		Coordinates coordinatesToCompare = (Coordinates) otherObject;
		if (this.getRowCoordinate() == coordinatesToCompare.getRowCoordinate() &&
				this.getColumnCoordinate() == coordinatesToCompare.getColumnCoordinate()) {
			return true;
		} else {
			return false;
		}
	}
}