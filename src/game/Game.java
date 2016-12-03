package game;

import java.util.List;

public class Game {
	
	private Board board;
	private Symbol currentPlayer = Symbol.X;
	private int numOfMoves = 0;
	private static final int TOTAL_MOVES = 9;
	
	public Game(Board board) {
		this.board = board;
	}
	
	public Board copyCurrentBoard() {
		Board boardCopy = new Board();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Symbol symb = board.getSquare(i,j).occupiedBy();
				boardCopy.getSquare(i, j).setOccupier(symb);
				
			}
		}
		return boardCopy;
	}
	
	public Symbol getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void applyMove(int x, int y) {
		board.applyMove(x,  y, currentPlayer);
		numOfMoves++;
		currentPlayer = currentPlayer == Symbol.X ? Symbol.O : Symbol.X;
	}
	
	public void undoLastMove() {
		board.undoLastMove();
		numOfMoves--;
		currentPlayer = currentPlayer == Symbol.X ? Symbol.O : Symbol.X;
	}
	
	public int scoreMove(Symbol symb) {
		if (hasWon(symb)) {
			return 10;
		} else if (!hasWon(Symbol.X) && !hasWon(Symbol.O)) {
			return 0;
		} else {
			return -10;
		}
	}
	
	public boolean isOver() {
		return hasWon(Symbol.O) || hasWon(Symbol.X);
	}
	
	public List<Square> getAvailableMoves() {
		return board.getAvailableSquares();
	}
	
	public boolean hasWon(Symbol symb) {
		for (int i = 0; i < 3; i++) {
			if (columnWin(symb, i) || rowWin(symb, i)) {
				return true;
			}
		}
		return diagonalWin(symb);
	}
	
	private boolean columnWin(Symbol symb, int colNum) {
		Symbol sq1 = board.getSquare(colNum, 0).occupiedBy();
		Symbol sq2 = board.getSquare(colNum, 1).occupiedBy();
		Symbol sq3 = board.getSquare(colNum, 2).occupiedBy();
		return symb == sq1 && sq1 == sq2 && sq2 == sq3;
	}
	
	private boolean rowWin(Symbol symb, int rowNum) {
		Symbol sq1 = board.getSquare(0, rowNum).occupiedBy();
		Symbol sq2 = board.getSquare(1, rowNum).occupiedBy();
		Symbol sq3 = board.getSquare(2, rowNum).occupiedBy();
		return symb == sq1 && sq1 == sq2 && sq2 == sq3;
	}
	
	private boolean diagonalWin(Symbol symb) {
		Symbol centerSq = board.getSquare(1, 1).occupiedBy();
		if (symb != centerSq) {
			return false;
		}
		Symbol topRight = board.getSquare(0, 0).occupiedBy();
		Symbol bottomLeft = board.getSquare(2, 2).occupiedBy();
		Symbol bottomRight = board.getSquare(0, 2).occupiedBy();
		Symbol topLeft = board.getSquare(2, 0).occupiedBy();
		return (topRight == bottomLeft && bottomLeft == symb) ||
				(bottomRight == topLeft && topLeft == symb);
	}

}
