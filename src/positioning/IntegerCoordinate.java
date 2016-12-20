package positioning;

public class IntegerCoordinate implements Coordinate<IntegerCoordinate, Integer> {
	private Integer coordinate;
	
	public IntegerCoordinate(Integer coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
	public Integer getValue() {
		return coordinate;
	}

	public void setCoordinate(Integer coordinate) {
		this.coordinate = coordinate;	
	}

	@Override
	public IntegerCoordinate getPreviousCoordinate() {
		return new IntegerCoordinate(coordinate - 1);
	}

	@Override
	public IntegerCoordinate getNextCoordinate() {
		return new IntegerCoordinate(coordinate + 1);
	}
	
	@Override
	public boolean equals(Object otherCoordinate) {
		IntegerCoordinate coordinateToCompare = (IntegerCoordinate) otherCoordinate;
		if (this.getValue().equals(coordinateToCompare.getValue())) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return String.valueOf(coordinate);
	}
}