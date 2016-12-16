package board;

import java.util.*;

import Ships.Ship;

public class GameBoardState {
	private ArrayList<Ship> shipList;
	private ArrayList<BoardCell> board;
	
	public GameBoardState() {
	
	}
	
	public void addShip(Ship newShip) {
		
		shipList.add(newShip);
	}
	
	public void removeShip(Ship shipToRemove) {
		shipList.remove(shipToRemove);
	}
	
	public static void main(String[] args) {
		BoardCell cell1 = new BoardCell('A', 1, BoardCellState.DESTROYED);
		BoardCell cell2 = new BoardCell('A', 15, BoardCellState.ALIVE);
		
		System.out.println(cell1.equals(cell2));
	}
		
}