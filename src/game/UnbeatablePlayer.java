package game;

import java.util.List;

public class UnbeatablePlayer {
	
	private Symbol symb;
	private Game game;
	Board board;
	public static final int MIN_VALUE = -100;
	public static final int MAX_VALUE = +100;

	public UnbeatablePlayer(Symbol symb, Game game) {
		this.symb = symb;
		this.game = game;
	}
	
	public void makeMove() {
		Square bestMove = minimax(game);
		game.applyMove(bestMove.getX(), bestMove.getY());
	}
	
	public Square minimax(Game game) {
		int highestScore = MIN_VALUE;
		Square bestMove = null;
		
		List<Square> availableMoves = game.getAvailableMoves();
		int score = 0;
		for (Square move : availableMoves) {
			System.out.println("Root branch: " + game.getCurrentPlayer() + " " + move.getX() + " " + move.getY());
			score = min(game, move);
			System.out.println(score);
			if (score >= highestScore) {
				highestScore = score;
				bestMove = move;
			}
		}
		return bestMove;
	}
	
	private int min(Game game, Square move) {
		Game cloneGame = new Game(game.copyCurrentBoard());
		if (cloneGame.isOver()) {
//			System.out.println("Score at end min: " + score(game));
			return score(cloneGame);
		}
		List<Square> availableMoves = cloneGame.getAvailableMoves();
//		for (Square x : availableMoves) {
//			System.out.println("MOVE: " + x.getX() + ", " + x.getY());
//		}
		cloneGame.applyMove(move.getX(), move.getY());
//		cloneGame.copyCurrentBoard().displayBoard();
		availableMoves = cloneGame.getAvailableMoves();
//		for (Square x : availableMoves) {
//			System.out.println("MOVE: " + x.getX() + ", " +  x.getY());
//		}
		int lowestScore = MAX_VALUE;
		int score = 0;
		for (Square newMove : availableMoves) {
//			System.out.println("Min branch: " + game.getCurrentPlayer() + " " + newMove.getX() + " " + newMove.getY());
			score = max(cloneGame, newMove);
			if (score < lowestScore) {
				lowestScore = score;
			}
		}
		return lowestScore;
		
	}
	
	private int max(Game game, Square move) {
		if (game.isOver()) {
//			System.out.println("Score at end max: " + score(game));
			return score(game);
		}
		game.applyMove(move.getX(), move.getY());
		int highestScore = MIN_VALUE;
		List<Square> availableMoves = game.getAvailableMoves();
		int score = 0;
		for (Square newMove : availableMoves) {
//			System.out.println("Max branch: " + game.getCurrentPlayer() + " " + newMove.getX() + " " + newMove.getY()); 
			score = min(game, newMove);
			if (score > highestScore) {
				highestScore = score;
			}
		}
		return highestScore;
	}
	
	private int score(Game game) {
		Symbol result = game.getResult();
		if (symb == result) {
			return 10;
		} else if (result == (symb == Symbol.X ? Symbol.O : Symbol.X)){
			return -10;
		} else {
			return 0;
		}
				
	}

	
}
