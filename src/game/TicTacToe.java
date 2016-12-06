package game;

import java.util.Scanner;

public class TicTacToe {
	
	private static UnbeatablePlayer computer;
	private static boolean computerFirst = false;
	private static Board board = new Board();
	private static Game game = new Game(board);
	
	public static void main(String[] args) {
	
		board.displayBoard();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter X or O to choose your player (X goes first!)");
		while (!scanner.hasNext("[XOxo]")) {
			System.out.println("Please enter either X or O");
			scanner.next();
		}
		String choice = scanner.next();
		if (choice.equals("O")) {
			System.out.println("In that case, the computer will go first.");
			computerFirst = true;
			computer = new UnbeatablePlayer(Symbol.X, game);
		} else if (choice.equals("X")) {
			System.out.println("Then you shall go first.");
			computer = new UnbeatablePlayer(Symbol.O, game);
		}
		while (!game.isOver()) {
			if(!computerFirst) {
				challengerMove(scanner);
				if (game.isOver()) {
					break;
				}
				computerMove(scanner);
			} else {
				computerMove(scanner);
				if (game.isOver()) {
					break;
				}
				challengerMove(scanner);
			}
		}
		scanner.close();
		if (game.getResult() == Symbol.NONE) {
			System.out.println("You drew!");
		} else {
			System.out.println("Player " + game.getResult() + " won!");
		}

	}
	
	private static void challengerMove(Scanner scanner) {
		System.out.println("Enter a positive integer, between 0 and 2, for your x coordinate: ");
		while (!scanner.hasNextInt()) {
			System.out.println("Oops, try again, this time with an integer...");
			scanner.next();
		}
		int x = scanner.nextInt();
		while (x > 2 || x < 0) {
			System.out.println("Nope! Try an integer between 0 and 2!");
			x = scanner.nextInt();
		}
		System.out.println("Good! Now for your y coordinate: ");
		while (!scanner.hasNextInt()) {
			System.out.println("Oops, try again, this time with an integer...");
			scanner.next();
		}
		int y = scanner.nextInt();
		while (y > 2 || y < 0) {
			System.out.println("Nope! Try an integer between 0 and 2!");
			y = scanner.nextInt();
		}
		game.applyMove(x, y);
		board.displayBoard();
	}
	
	private static void computerMove(Scanner scanner) {
		computer.makeMove();
		System.out.println("The computer chose...");
		board.displayBoard();
	}

}
