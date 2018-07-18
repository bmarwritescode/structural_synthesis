package pieces;

import enums.Player;
import Chessboard.Chessboard;
import sjakk.Position;


/**
 *
 * @author Joakim
 */
public class SuperPawn extends Piece {

    private final String imagePathWhite = "images/wpawn.png";
    private final String imagePathBlack = "images/bpawn.png";

    // public SuperPawn(Player color, Position pos, Chessboard board) {
    public SuperPawn(int color, Position pos, Chessboard board) {
        super(color, pos, board);
    }
    
   public SuperPawn(Chessboard board, Piece other){
        super(board,other);
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
        if (pieceColor == Player.BLACK) {
            moveBlackForward();
            attackBlack();
        } else {
            moveWhiteForward();
            attackWhite();
        }
    }

    private void moveBlackForward() {
        Position pos = new Position(this.currentPos.getRow()-2, this.currentPos.getColumn());
        if(board.isFree(pos)){
            super.addMove(pos);
        } else {
            return;
        }
        
        if (isInStartPos()) {
            pos = new Position(this.currentPos.getRow()-3, this.currentPos.getColumn());
            if (board.isFree(pos)) {
                super.addMove(pos);
            }
        }
    }

    private void attackBlack() {
        //Check to the left
        Position pos = new Position(this.currentPos.getRow()-2, this.currentPos.getColumn()-2);
        if (board.containsEnemy(this.currentPos,pos)) {
            super.addMove(pos);
        }

        //Check to the right:
        pos = new Position(this.currentPos.getRow()-2, this.currentPos.getColumn()+2);
        if (board.containsEnemy(this.currentPos,pos)) {
            super.addMove(pos);
        }
    }

    private void moveWhiteForward() {
        Position pos = new Position(this.currentPos.getRow()+2, this.currentPos.getColumn());
        if (board.isFree(pos)) {
             super.addMove(pos);
        } else {
            return;
        } 

        //If in startpos
        if (isInStartPos()) {
            pos = new Position(this.currentPos.getRow()+3, this.currentPos.getColumn());
            if (board.isFree(pos)) {
                super.addMove(pos);
            }
        }
    }

    private void attackWhite() {
        //Check to the left
        Position pos = new Position(this.currentPos.getRow()+2, this.currentPos.getColumn()-2);
        if (board.containsEnemy(this.currentPos,pos)) {
            super.addMove(pos);
        }

        //Check to the right:
        pos = new Position(this.currentPos.getRow()+2, this.currentPos.getColumn()+2);
        if (board.containsEnemy(this.currentPos,pos)) {
            super.addMove(pos);
        }
    }

    private boolean isInStartPos() {
        if (pieceColor == Player.BLACK) {
            return this.currentPos.getRow() == 6;
        } else {
            return this.currentPos.getRow() == 1;
        }
    }

}
