import board.*;
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
//		Ship oneDeckShip = OneDeckShip.buildShip(new BoardCell('A', 9));
//		
//		Ship twoDeckShip = TwoDeckShip.buildShip(new BoardCell('A', 1), new BoardCell('A', 0));
//		
//		Ship threeDeckShip = ThreeDeckShip.buildShip(new BoardCell('F', 2), new BoardCell('F', 1), 
//														new BoardCell('F', 3));
//		
//		Ship fourDeckShip = FourDeckShip.buildShip(new BoardCell('D', 5), new BoardCell('E', 5), 
//									 new BoardCell('G', 5), new BoardCell('F', 5));
//		
//		board.addShipToBoard(oneDeckShip);
//		board.addShipToBoard(twoDeckShip);
//		board.addShipToBoard(threeDeckShip);
//		board.addShipToBoard(fourDeckShip);
		
		board.autoGenerateShipFleet();
				
		System.out.println(board);
	}
}
