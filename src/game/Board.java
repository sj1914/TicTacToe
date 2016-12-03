package game;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	private Square[][] board = new Square[3][3];
	private int lastMoveX;
	private int lastMoveY;
	
	public Board() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Square sq = new Square(i, j);
				sq.setOccupier(Symbol.NONE);
				board[j][i] = sq;
			}
		}
	}
	
	public Square getSquare(int x, int y) {
		return board[y][x];
	}
	
	public boolean applyMove(int x, int y, Symbol symb) {
		Square sq = getSquare(x, y);
		if (sq.occupiedBy() == Symbol.NONE) {
			sq.setOccupier(symb);
			lastMoveX = x;
			lastMoveY = y;
			return true;
		}
		return false;
	}
	
	public void undoLastMove() {
		Square sq = getSquare(lastMoveX, lastMoveY);
		sq.setOccupier(Symbol.NONE);
	}
	
	public List<Square> getAvailableSquares() {
		List<Square> availableMoves = new ArrayList<Square>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Square sq = board[i][j];
				if (sq.occupiedBy() == Symbol.NONE) {
					availableMoves.add(sq);
				}
			}
		}
		return availableMoves;
	}
	
	public void displayBoard() {
		System.out.println("  0 1 2");
		for (int i = 0; i < board.length; i++) {
			System.out.print(i + " ");
			for (int j = 0; j < board.length; j++) {
				Square sq = board[i][j];
				if (sq.occupiedBy() == Symbol.NONE) {
					System.out.print(" ");
				} else {
					System.out.print(sq.occupiedBy());
				}
				if (j < board.length - 1) {
					System.out.print("|");
				}
			}
			System.out.println();
			if (i < board.length - 1) {
				System.out.println(" -------  ");
			}
		}
	}

}
