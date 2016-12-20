package positioning;

public interface Coordinate<C, V> {

	V getValue();
	
	C getPreviousCoordinate();
	
	C getNextCoordinate();
}