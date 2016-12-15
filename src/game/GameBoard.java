package game;

enum BoardCellState {EMPTY, ALIVE, HIT, DESTROYED};

public class GameBoard {

	private final int boardWidth;
	private final int boardHeight;
	private static final int DAFAULT_BOARD_WIDTH = 10;
	private static final int DEFAULT_BOARD_HEIGHT = 10; 
	
	private BoardCellState[][] boardState = null;
	
	public GameBoard(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		boardState = new BoardCellState[boardWidth][boardHeight];
	}
	
	public GameBoard() {
		this(DAFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
	}
	
	private String convertBoardToString() {
		StringBuilder board = new StringBuilder();
		board.append("  ");
		for (int i = 0; i < boardWidth; i++) {
			board.append(i + " ");
		}
		board.append("\n");
		
		for (int boardRow = 0; boardRow < boardHeight; boardRow++) {
			board.append((char) (boardRow + 'A'));
			for (int boardColumn = 0; boardColumn < boardWidth; boardColumn++) {
				board.append(getCharacterBasedOnState(boardState[boardRow][boardColumn]));
			}
			board.append("\n");
		}	
				
		return board.toString();
	}
	
	private char getCharacterBasedOnState(BoardCellState state) {
		if (state == BoardCellState.EMPTY || state == BoardCellState.ALIVE) {
			return 'x';
		} else {
			return ' ';
		}
	}
	
	@Override
	public String toString() {
		return convertBoardToString();
	}	
}