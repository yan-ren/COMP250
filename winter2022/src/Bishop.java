public class Bishop extends Piece {
    public Bishop(int x, int y, Side side, Board board) {
        // TODO: Call super constructor
        super(x, y, side, board);
    }

    @Override
    public boolean canMove(int destX, int destY) {
        // TODO: Check piecerules.md for the movement rule for this piece :)
        // check if with given arguments the allowed position expression evaluates to
        // true
        // if yes return boolean true if NOT...
        if (Math.abs(this.x - destX) == Math.abs(this.y - destY)) {
            return (true);
        } else {
            return false;
        }

    }

    @Override
    public String getSymbol() {
        return this.getSide() == Side.BLACK ? "♝" : "♗";
    }
}
