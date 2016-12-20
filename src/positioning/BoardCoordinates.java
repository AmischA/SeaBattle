package positioning;

public class BoardCoordinates implements TwoDimentionalCoordinates<CharacterCoordinate, IntegerCoordinate> {
	private CharacterCoordinate rowCoordinate;
	private IntegerCoordinate columnCoordinate;
	
	public BoardCoordinates(CharacterCoordinate rowCoordinate, IntegerCoordinate columnCoordinate) {
		this.rowCoordinate = rowCoordinate;
		this.columnCoordinate = columnCoordinate;
	}

	@Override
	public CharacterCoordinate getRowCoordinate() {
		return rowCoordinate;
	}

	@Override
	public IntegerCoordinate getColumnCoordinate() {
		return columnCoordinate;
	}

	@Override
	public BoardCoordinates getLeftNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate, columnCoordinate.getPreviousCoordinate());
	}

	@Override
	public BoardCoordinates getUpperLeftNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate.getPreviousCoordinate(), columnCoordinate.getPreviousCoordinate());
	}

	@Override
	public BoardCoordinates getLowerLeftNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate.getNextCoordinate(), columnCoordinate.getPreviousCoordinate());
	}

	@Override
	public BoardCoordinates getRightNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate, columnCoordinate.getNextCoordinate());
	}

	@Override
	public BoardCoordinates getUpperRightNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate.getPreviousCoordinate(), columnCoordinate.getNextCoordinate());
	}

	@Override
	public BoardCoordinates getLowerRightNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate.getNextCoordinate(), columnCoordinate.getNextCoordinate());
	}

	@Override
	public BoardCoordinates getUpperNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate.getPreviousCoordinate(), columnCoordinate);
	}

	@Override
	public BoardCoordinates getLowerNeihbourCoordinates() {
		return new BoardCoordinates(rowCoordinate.getNextCoordinate(), columnCoordinate);
	}
	
	@Override 
	public boolean equals(Object otherObject) {
		BoardCoordinates boardCoordinatesToCompare = (BoardCoordinates) otherObject;
		if (this.getRowCoordinate().equals(boardCoordinatesToCompare.getRowCoordinate()) &&
				this.getColumnCoordinate().equals(boardCoordinatesToCompare.getColumnCoordinate())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "(" + rowCoordinate + ", " + columnCoordinate + ")";
	}
}