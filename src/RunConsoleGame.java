import board.*;
import ships.*;

public class RunConsoleGame {

	public static void main(String[] args) {
		new RunConsoleGame().start();
	}
	
	public void start() {
		GameBoard board = new GameBoard();
		
		Ship oneDeckShip = OneDeckShip.buildShip(new BoardCell('A', 9));
		
		Ship twoDeckShip = TwoDeckShip.buildShip(new BoardCell('A', 1), new BoardCell('A', 3));
		
//		Ship threeDeckShip = ThreeDeckShip.buildShip(new BoardCell('F', 0), new BoardCell('G', 0), new BoardCell('H', 0));
//		
//		Ship fourDeckShip = FourDeckShip.buildShip(new BoardCell('D', 5), new BoardCell('E', 5), 
//									 new BoardCell('G', 5), new BoardCell('F', 5));
//		
		board.addShipToBoard(oneDeckShip);
		board.addShipToBoard(twoDeckShip);
//		board.addShipToBoard(threeDeckShip);
//		board.addShipToBoard(fourDeckShip);
		
		System.out.println(board);
	}
}