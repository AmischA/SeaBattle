/*
 * class Coordinates<R, C>, where R - row coordinate type and
 * C - column coordinate type, encapsulates two coordinates on the board
 */

package board;

public class Coordinates<R extends Number, C> {
	private R rowCoordinate;
	private C columnCoordinate;
	
	public Coordinates(R rowCoordinate, C columnCoordinate) {
		this.rowCoordinate = rowCoordinate;
		this.columnCoordinate = columnCoordinate;		
	}

	public R getRowCoordinate() {
		return rowCoordinate;
	}

	public void setRowCoordinate(R rowCoordinate) {
		this.rowCoordinate = rowCoordinate;
	}

	public C getColumnCoordinate() {
		return columnCoordinate;
	}

	public void setColumnCoordinate(C columnCoordinate) {
		this.columnCoordinate = columnCoordinate;
	}
}