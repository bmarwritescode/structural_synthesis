package pieces;

import enums.Player;
import java.util.ArrayList;
import Chessboard.Chessboard;
import sjakk.Move;
import sjakk.Position;

/**
 *
 * @author Joakim
 */
public abstract class Piece{
    // protected ArrayList<Move> posMoves = new ArrayList<Move>();
    // protected Player pieceColor;
    // protected Position currentPos;
    // protected Chessboard board;

    protected ArrayList<Move> posMoves;
    protected int pieceColor;
    protected Position currentPos;
    protected Chessboard board;
    
    @Conditional(addMove)
    public boolean isInSection(Position p) {
	if (??) {
	    int leftRow = ??;
	    int rightRow = ??;
	    int upCol = ??;
	    int lowCol = ??;

	    return p.getColumn() <= upCol && p.getColumn() >= lowCol &&
		p.getRow() <= rightRow && p.getRow() >= leftRow;
	}
	return true;
    }
    
    // public Piece(Player color, Position position, Chessboard board) {
    public Piece(int color, Position position, Chessboard board) {
        currentPos = position;
        pieceColor = color;
        this.board = board;
	posMoves = new ArrayList<Move>();
    }
    
    //Cloning
     public Piece(Chessboard board, Piece other) {
        pieceColor = other.getPieceColor();
        this.board = board;
        currentPos = new Position(other.getPosition());
	posMoves = new ArrayList<Move>();	
    }

    abstract protected void addAllPossibleMoves();

    // abstract public String getPieceImagePath();
        
    // public Player getPieceColor(){
    public int getPieceColor(){
        return pieceColor;
    }
            
    public void updateCurrentPos(Position pos, Move move){
	ArrayList<Move> moves = getMoves();

	if (moves.contains(move)) {
	    this.currentPos = pos;
	} else {
	    assert false;
	}
    }
    
    public Position getPosition(){
        return currentPos;
    }

    protected void addMove(Position destination) {
	if (isInSection(destination)) {
	    Piece thisPiece = this;
	    Move move = new Move(currentPos, destination, thisPiece);
	    posMoves.add(move);
	}
    }
    
    public ArrayList<Move> getMoves(){
	posMoves.clear();
        this.addAllPossibleMoves();
        return posMoves;
    }
    
}
