package board;

import java.util.*;

import ships.Ship;

public class GameBoard {

	private static final int DAFAULT_BOARD_WIDTH = 10;
	private static final int DEFAULT_BOARD_HEIGHT = 10; 
	
	private final int boardWidth;
	private final int boardHeight;
		
	private GameBoardState gameState;	
	
	public GameBoard(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		gameState = new GameBoardState(boardWidth, boardHeight);
	}
	
	public GameBoard() {
		this(DAFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
	}
	
	public void addShipToBoard(Ship shipToAdd) {
		gameState.addShip(shipToAdd);
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
		
		NavigableSet<BoardCell> gameBoard = gameState.getBoard();
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
		if (state == BoardCell.BoardCellState.HIT || state == BoardCell.BoardCellState.DESTROYED) {
			return 'x';
		} else if (state == BoardCell.BoardCellState.ALIVE) {
			return '#';
		} else if (state == BoardCell.BoardCellState.ADJACENT) {
			return 'o';
		} else {
			return ' ';
		}
	}
}