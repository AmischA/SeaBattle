package board;

import java.util.*;
import java.lang.reflect.*;

import board.BoardCell.BoardCellState;
import ships.*;

public class GameBoard {
	private enum BuildingDirection {UP, RIGHT, DOWN, LEFT};

	private final int boardWidth;
	private final int boardHeight;
		
	private GameBoardState gameState;	
	
	public GameBoard(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		gameState = new GameBoardState(boardWidth, boardHeight);
	}
	
	public void addShipToBoard(Ship shipToAdd) {
		gameState.addShip(shipToAdd);
	}
	
	public void fire(BoardCell cell) {
		
	}
	
	public BoardCellState getCellState(BoardCell cell) {
		return gameState.getCellState(cell);
	}

	public void autoGenerateShipFleet() {
//		for (int i = 0; i < FourDeckShip.MAXIMUM_NUMBER_OF_SHIPS; i++) {
//			Ship fourDeckShip = FourDeckShip.buildShip(generateShipDecks(FourDeckShip.NUMBER_OF_DECKS));
//			addShipToBoard(fourDeckShip);
//		}
//		for (int i = 0; i < ThreeDeckShip.MAXIMUM_NUMBER_OF_SHIPS; i++) {
//			
//		}
//		for (int i = 0; i < TwoDeckShip.MAXIMUM_NUMBER_OF_SHIPS; i++) {
//			
//		}
//		for (int i = 0; i < OneDeckShip.MAXIMUM_NUMBER_OF_SHIPS; i++) {
//			
//		}
		Collection<Ship> generatedShips = generateShipsByClass(FourDeckShip.class, 1);
		for (Ship ship : generatedShips) {
			addShipToBoard(ship);
		}
//		Ship fourDeckShip = FourDeckShip.buildShip(generateShipDecks(FourDeckShip.NUMBER_OF_DECKS));
//		addShipToBoard(fourDeckShip);
	}
	
	private Collection<Ship> generateShipsByClass(Class<? extends Ship> shipClass, int numberOfShips) {
		Collection<Ship> generatedShips = new HashSet<>();
		for (int i = 0; i < numberOfShips; i++) {
			generatedShips.add(createShipByClass(shipClass));
		}
		if (generatedShips.size() == 0) {
			throw new RuntimeException("Can't generate " + numberOfShips + " ships by class type " + shipClass);
		}
		return generatedShips;
	}
	
	private Ship createShipByClass(Class<? extends Ship> shipClass) {
		try {
			Method shipFactoryMethod = shipClass.getMethod("buildShip", Collection.class);
			Field fieldNumberOfDecks = shipClass.getField("NUMBER_OF_DECKS");
			Collection<BoardCell> shipDecks = generateShipDecks(fieldNumberOfDecks.getInt(int.class));
			Ship newShip = (Ship) shipFactoryMethod.invoke(Collection.class, shipDecks);
			return newShip;
		} catch (Exception e) {
			RuntimeException exception = new RuntimeException("Can't create a ship by class type " + shipClass);
			exception.initCause(e);
			throw exception;
		}
	}
	
	/*
	 * randomly generates a sequential number of decks for a ship, iterating through a list of available 
	 * empty board cells 
	 */
	private Collection<BoardCell> generateShipDecks(int howManyDecks) {
		Random randomNumberGenerator = new Random();
		List<BoardCell> listOfEmptyCells = gameState.getEmptyCells();
		Collections.shuffle(listOfEmptyCells);
		
		for (BoardCell cell : listOfEmptyCells) {
			List<BuildingDirection> directions = new ArrayList<>(Arrays.asList(BuildingDirection.values()));
			while(!directions.isEmpty()) {
				BuildingDirection currentDirection = directions.remove(randomNumberGenerator.nextInt(directions.size()));
				Collection<BoardCell> newShip = tryToBuildShip(cell, howManyDecks, currentDirection);
				if (newShip != null) {
					return newShip;
				}
			}
		}
		throw new IllegalStateException("Can't find a place on the board to build a ship with so many decks");
	}
	
	/*
	 * tries to build a ship if it's possible for the current board starting from known cell 
	 * with given number of decks and building direction (UP, DOWN, LEFT or RIGHT).
	 * returns null if can't build a ship  
	 */
	private Collection<BoardCell> tryToBuildShip(BoardCell currentCell, int howManyDecks, BuildingDirection direction) {
		Collection<BoardCell> result = new HashSet<>();
		for (int i = 0; i < howManyDecks; i++) {
			BoardCell neighbourCell = gameState.findCell(nextNeighbour(currentCell, direction));			
			if (neighbourCell != null && neighbourCell.getState() == BoardCellState.EMPTY) {
				result.add(neighbourCell);
				currentCell = neighbourCell;
			} else {
				return null;
			}
		}
		return result;
	}
	
	private BoardCell nextNeighbour(BoardCell cell, BuildingDirection direction) {
		switch(direction) {
			case UP: return cell.getUpperNeighbour();
			case RIGHT: return cell.getRightNeighbour();
			case DOWN: return cell.getLowerNeighbour();
			default: return cell.getLeftNeighbour();
		}
	}
	
	@Override
	public String toString() {
		return convertBoardToString();
	}	

	private String convertBoardToString() {
		StringBuilder stringBoard = new StringBuilder();
		stringBoard.append("  ");
		for (int i = 0; i < boardWidth; i++) {
			stringBoard.append(i + " ");
		}
		stringBoard.append("\n");
		
		NavigableSet<BoardCell> gameBoard = gameState.getCells();
		for (int row = 0; row < boardHeight; row++) {
			stringBoard.append((char) (row + 'A') + " ");
			for (int column = 0; column < boardWidth; column++) {
				stringBoard.append(getCharacterBasedOnState(gameBoard.pollFirst().getState()) + " ");
			}
			stringBoard.append("\n");
		}	
				
		return stringBoard.toString();
	}
	
	private char getCharacterBasedOnState(BoardCell.BoardCellState state) {
		if (state == BoardCellState.HIT || state == BoardCellState.DESTROYED) {
			return 'x';
		} else if (state == BoardCellState.ALIVE) {
			return '#';
		} else if (state == BoardCellState.ADJACENT) {
			return 'o';
		} else {
			return ' ';
		}
	}
}