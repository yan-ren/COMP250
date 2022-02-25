
public class Pawn extends Piece {

	public Pawn(int x, int y, Side side, Board board) {
		// TODO: Call super constructor
		// called super
		super(x, y, side, board);
	}

	@Override
	public boolean canMove(int destX, int destY) {
		// if first move, can move one or two forward
		// black pawn
		if (this.getSide() == Side.BLACK) {
			// black first move
			if (y == 1 && (destY - y <= 2 && destY - y > 0) && destX == x && this.board.get(destX, destY) == null) {
				return true;
			}
			// black move, move first move, can only move one step forward
			else if (destY - y == 1 && destX == x && this.board.get(destX, destY) == null) {
				return true;
				// black move diagonal
			} else if (destY - y == 1 && Math.abs(destX - x) == 1 && this.board.get(destX, destY) != null
					&& board.get(destX, destY).getSide() == Side.WHITE) {
				return true;
			}
		}

		// white pawn
		if (this.getSide() == Side.WHITE) {
			// white first move
			if (y == 6 && (destY - y >= -2 && destY - y < 0) && destX == x && this.board.get(destX, destY) == null) {
				return true;
			}
			// white move, not first move, can only move one step forward
			else if (destY - y == -1 && destX == x && this.board.get(destX, destY) == null) {
				return true;
			}
			// white move, diagonal
			else if (destY - y == -1 && Math.abs(destX - x) == 1 && this.board.get(destX, destY) != null
					&& this.board.get(destX, destY).getSide() == Side.BLACK) {
				return true;
			}
		}

		return false;
	}

	@Override
	// Symbols: "♟" : "♙"
	public String getSymbol() {
		return this.getSide() == Side.BLACK ? "♟" : "♙";
	}
}
