import java.io.IOException;

import board.*;
import board.GameBoard.FireResult;
import players.*;
import ships.*;

public class ConsoleGame {
	private static final int DAFAULT_BOARD_WIDTH = 10;
	private static final int DEFAULT_BOARD_HEIGHT = 10; 
	
	private GameBoard board;
	private Player human;
	private Player computer;

	public ConsoleGame(int boardWidth, int boarHeight) {
		board = new GameBoard(boardWidth, boarHeight);	
	}
	
	public ConsoleGame() {
		this(DAFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
	}

	public void startConsoleGame() {
//		Ship oneDeckShip = TwoDeckShip.buildShip(new BoardCell('A', 9), new BoardCell('B', 9));
//		
//		Ship twoDeckShip = TwoDeckShip.buildShip(new BoardCell('B', 9), new BoardCell('C', 9));
		
		Ship threeDeckShip = ThreeDeckShip.buildShip(new BoardCell('F', 2), new BoardCell('F', 1), 
														new BoardCell('F', 3));
//		
//		Ship fourDeckShip = FourDeckShip.buildShip(new BoardCell('D', 5), new BoardCell('E', 5), 
//									 new BoardCell('G', 5), new BoardCell('F', 5));
//		
//		board.addShipToBoard(oneDeckShip);
//		board.addShipToBoard(twoDeckShip);
//		board.addShipToBoard(threeDeckShip);
//		board.addShipToBoard(fourDeckShip);
		
//		board.autoGenerateShipFleet();

		board.addShipToBoard(threeDeckShip);
			
		Runtime runtime = Runtime.getRuntime();	
						
		System.out.println(board);	
		FireResult result = board.fire(new BoardCell('F', 1));
		String output = getOutputBasedOnFire(result);
		System.out.println(output);			
		
		System.out.println("\n" + board);
		result = board.fire(new BoardCell('F', 2));
		output = getOutputBasedOnFire(result);
		System.out.println(output);
		
		System.out.println("\n" + board);
		result = board.fire(new BoardCell('F',3));
		output = getOutputBasedOnFire(result);
		System.out.println(output);
		
		System.out.println("\n" + board);
	}
	
	private String getOutputBasedOnFire(FireResult result) {
		switch (result) {
			case MISSED: 	return "You missed";
			case HIT: 		return "You hit a ship";
			case DESTROYED: return "You destroyed a ship";
			default: 		return "You fired there before";
		}
	}
}
