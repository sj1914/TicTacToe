package game;

public class Square {
	
	private int x;
	private int y;
	private Symbol occupier;
	
	// initialises square with given coordinates 
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//returns x coordinate 0-2
	public int getX() {
		return x;
	}
	
	// returns y coordinate 0-2
	public int getY() {
		return y;
	}
	
	public Symbol occupiedBy() {
		return occupier;
	}
	
	public void setOccupier(Symbol symbol) {
		occupier = symbol;
	}

}
