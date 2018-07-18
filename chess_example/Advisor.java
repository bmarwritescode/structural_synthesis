package pieces;

import enums.Player;
import Chessboard.Chessboard;
import sjakk.Position;


/**
 *
 * @author Joakim
 */
public class Advisor extends Piece {

    private final String imagePathWhite = "images/wpawn.png";
    private final String imagePathBlack = "images/bpawn.png";

    private boolean moved;
    
    // public Advisor(Player color, Position pos, Chessboard board) {
    public Advisor(int color, Position pos, Chessboard board) {
        super(color, pos, board);
    }    
    
   public Advisor(Chessboard board, Piece other){
        super(board,other);
    }    
    
    @Conditional(addMove)
    public boolean isInSection(Position p) {
	int leftRow = 0;
	int rightRow = 2;
	int lowCol = 3;
	int upCol = 5;

	return p.getColumn() <= upCol && p.getColumn() >= lowCol &&
	    p.getRow() <= rightRow && p.getRow() >= leftRow;
	return true;
    }

    @Override
    public String getPieceImagePath(){
        if(pieceColor == Player.WHITE){
            return imagePathWhite;
        }
        
        return imagePathBlack;
    }

    @Override
    public void addAllPossibleMoves() {
	posMoves.clear();
	Position pos = new Position(this.currentPos.getRow(), 
		this.currentPos.getColumn() - 1);

	checkSpace(pos);

	pos = new Position(this.currentPos.getRow(), this.currentPos.getColumn()+1);
	checkSpace(pos);

	pos = new Position(this.currentPos.getRow() - 1, this.currentPos.getColumn());
	checkSpace(pos);

	pos = new Position(this.currentPos.getRow()+1, this.currentPos.getColumn());
	checkSpace(pos);

    }

    private void checkSpace(Position pos){
	if (board.isOutOfBounds(pos)) {
	    // assert false;
	}
        else if(board.containsEnemy(this.currentPos,pos)){
	    if (isInSection(pos)) super.addMove(pos);
        }else if(board.isFree(pos)){
	    if (isInSection(pos)) super.addMove(pos);
        } else {
	    // assert false;
	}
    }    
}
