package board;

import java.util.*;
import java.lang.reflect.*;

import board.BoardCell.BoardCellState;
import ships.*;

public class GameBoard {
	public enum FireResult {MISSED, HIT, DESTROYED, FIRED_BEFORE};

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
	
	public FireResult fire(BoardCell cell) {
		BoardCell targetCell = gameState.findCell(cell);
		if (targetCell == null) {
			throw new IllegalArgumentException("There's no such cell on the board with coordinates " + 
												cell.getCoordinates());
		}
		FireResult result = getFireResult(targetCell.getState());
		switch(result) {
			case MISSED: targetCell.setState(BoardCellState.MISSED);
						 break;
			case HIT:	 targetCell.setState(BoardCellState.HIT);
						 Ship hitShip = gameState.findShipByCell(targetCell);
						 hitShip.setDeckState(targetCell, BoardCellState.HIT);
						 if (!hitShip.isAlive()) {
							 result = FireResult.DESTROYED;
						 }
						 break;
		}
		return result;
	}
	
	private FireResult getFireResult(BoardCellState currentCellState) {
		if (currentCellState == BoardCellState.HIT || currentCellState == BoardCellState.MISSED) {
			return FireResult.FIRED_BEFORE;
		} else if (currentCellState == BoardCellState.EMPTY || currentCellState == BoardCellState.ADJACENT) {
			return FireResult.MISSED;
		} else {
			return FireResult.HIT;
		}
	}
	
	public BoardCellState getCellState(BoardCell cell) {
		return gameState.getCellState(cell);
	}

	public void autoGenerateShipFleet() {
		autoGenerateSpecificDeckShips(FourDeckShip.class);
		autoGenerateSpecificDeckShips(ThreeDeckShip.class);
		autoGenerateSpecificDeckShips(TwoDeckShip.class);
		autoGenerateSpecificDeckShips(OneDeckShip.class);
	}
	
	private void autoGenerateSpecificDeckShips(Class<? extends Ship> shipClass) {
		try {
			Field maximumNumberOfShipsField = shipClass.getField("MAXIMUM_NUMBER_OF_SHIPS");
			int maximumNumberOfShips = maximumNumberOfShipsField.getInt(Integer.class);
			for (int i = 0; i < maximumNumberOfShips; i++) {
				addShipToBoard(createShipByClass(shipClass));
			}
		}
		catch(Exception e) {
			RuntimeException exception = new RuntimeException("Can't get a field \"MAXIMUM_NUMBER_OF_SHIPS\"" +
																" of class " + shipClass.getName());
			exception.initCause(e);
			throw exception;
		}
	}
	
	private Ship createShipByClass(Class<? extends Ship> shipClass) {
		try {
			Method shipFactoryMethod = shipClass.getMethod("buildShip", GameBoardState.class);
			Ship newShip = (Ship) shipFactoryMethod.invoke(null, gameState);
			return newShip;
		} catch (Exception e) {
			RuntimeException exception = new RuntimeException("Can't create a ship by class type " + shipClass.getName());
			exception.initCause(e);
			throw exception;
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
		if (state == BoardCellState.HIT) {
			return 'x';
		} else if (state == BoardCellState.ALIVE) {
			return '#';
		} else if (state == BoardCellState.ADJACENT) {
			return ' ';
		} else {
			return ' ';
		}
	}
}