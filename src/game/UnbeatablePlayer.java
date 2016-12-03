package game;

import java.util.List;

public class UnbeatablePlayer {
	
	private Symbol symb;
	private Game realGame;
	private Square optimalSquare;
	Board board;

	public UnbeatablePlayer(Symbol symb, Game realGame) {
		this.symb = symb;
		this.realGame = realGame;
	}
	
	public void makeMove() {
		board = realGame.copyCurrentBoard();
		Game fakeGame = new Game(board);
		Square bestMove = miniMax(fakeGame, realGame.movesLeft());
		realGame.applyMove(bestMove.getX(), bestMove.getY());
	}
	
	public Square miniMax(Game fakeGame, int depth) {
		if(fakeGame.isOver()){
			return optimalSquare;
		} else {
			List<Square> legal = fakeGame.getAvailableMoves();
			if(fakeGame.getCurrentPlayer() == symb) {
				int bestScore = -1000;
				for (Square sq : legal) {
					int result =  applyMoveAndScore(sq, fakeGame);
					if (result > bestScore) {
						bestScore = result;
						optimalSquare = sq;
					}
					board = realGame.copyCurrentBoard();
					fakeGame = new Game(board);
				}
			return optimalSquare;
			} else {
				int bestScore = 1000;
				for (Square sq : legal) {
					int result = applyMoveAndScore(sq, fakeGame);
					if (result < bestScore) {
						bestScore  = result;
						optimalSquare = sq;
					}
					board = realGame.copyCurrentBoard();
					fakeGame = new Game(board);
				}
			return optimalSquare;
			}
		}
	}
	
	private int applyMoveAndScore(Square sq, Game game) {
		int x = sq.getX();
		int y = sq.getY();
		game.applyMove(x, y);
		return game.scoreMove(symb);
	}
	
}
