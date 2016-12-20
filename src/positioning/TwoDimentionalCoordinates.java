package positioning;

public interface TwoDimentionalCoordinates<R extends Coordinate<R, ?>, C extends Coordinate<C, ?>> {
	
	R getRowCoordinate();
	
	C getColumnCoordinate();
	
	TwoDimentionalCoordinates<R, C>  getLeftNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getUpperLeftNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getLowerLeftNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getRightNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getUpperRightNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getLowerRightNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getUpperNeihbourCoordinates();
	
	TwoDimentionalCoordinates<R, C> getLowerNeihbourCoordinates();
}