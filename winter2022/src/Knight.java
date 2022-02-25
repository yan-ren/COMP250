
public class Knight extends Piece {
	public Knight (int x, int y, Side side, Board board) {
        // TODO: Call super constructor
		//called super 
    	super (x, y,side,board);
    }

    @Override
    public boolean canMove(int destX, int destY) {

        //TODO: Check piecerules.md for the movement rule for this piece :)
    	//check if with given arguments the allowed position expression evaluates to true 
    	//if yes return boolean true if NOT...
    	
    	
    	// **You are responsable for implmenting the rules that the Knight can hop off 
    	
    	// look at another rproject 
    	
    	//System.out.println("Board Value of Knight" + board.get(destX, destY));
    	//System.out.println("destX x=" +destX + "destY y= "+destY  );
    	if (((Math.abs(this.x - destX) == 2 && Math.abs(this.y  - destY) == 1) || (Math.abs(this.x - destX) == 1 && Math.abs(this.y  - destY) == 2) ) ){
//    		if (board.get(destX, destY)==null) {
//    			System.out.println("returns true+2");
//    			return true;
//    			
//    		}else {
//    			System.out.println(board.get(destX, destY).getSide());
//    			System.out.println(this.getSide());
//    			if (board.get(destX, destY).getSide()!= this.getSide() ) {
//    				System.out.println("returns false+3");
//    				return true;
//    			}
//    			System.out.println("returns false+2");
//    			return false;
//    		}
//    		
    		return true;
    	}else {
    		//System.out.println("returns false");
    		return false;
    	}
    
    }

    @Override
    //Symbols: "♞" : "♘"
    public String getSymbol() {
        return this.getSide() == Side.BLACK ?  "♞" : "♘";
    }

}
