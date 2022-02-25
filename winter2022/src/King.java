
public class King extends Piece {
	public King(int x, int y, Side side, Board board) {
		// TODO: Call super constructor
		super(x, y, side, board);
	}

	@Override
	public boolean canMove(int destX, int destY) {

		// TODO: Check piecerules.md for the movement rule for this piece :)
		// check if with given arguments the allowed position expression evaluates to
		// true
		// if yes return boolean true if NOT...
		if (Math.abs(this.x - destX) <= 1 && Math.abs(this.y - destY) <= 1) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	// Symbols: "♚" : "♔"
	public String getSymbol() {
		return this.getSide() == Side.BLACK ? "♚" : "♔";
	}
}
