import board.*;
import ships.*;

public class RunConsoleGame {

	public static void main(String[] args) {
		new RunConsoleGame().start();
	}
	
	public void start() {
		ConsoleGame game = new ConsoleGame(10, 10);
		game.startConsoleGame();
	}
}