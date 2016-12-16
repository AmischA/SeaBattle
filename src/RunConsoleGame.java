import board.GameBoard;

public class RunConsoleGame {

	public static void main(String[] args) {
		new RunConsoleGame().start();
	}
	
	public void start() {
		GameBoard board = new GameBoard();
		System.out.println(board);
		
		
	}
}