package positioning;

public class CharacterCoordinate implements Coordinate<CharacterCoordinate, Character> {
	private Character coordinate;
	
	public CharacterCoordinate(Character coordinate) {
		this.coordinate = coordinate;
	}
	
	@Override
	public Character getValue() {
		return coordinate;
	}

	public void setCoordinate(Character coordinate) {
		this.coordinate = coordinate;	
	}

	@Override
	public CharacterCoordinate getPreviousCoordinate() {
		return new CharacterCoordinate((char) (coordinate - 1));
	}

	@Override
	public CharacterCoordinate getNextCoordinate() {
		return new CharacterCoordinate((char) (coordinate + 1));
	}
	
	@Override
	public boolean equals(Object otherCoordinate) {
		CharacterCoordinate coordinateToCompare = (CharacterCoordinate) otherCoordinate;
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