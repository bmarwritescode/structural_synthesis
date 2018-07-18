package pieces;

import enums.Player;
import Chessboard.Chessboard;
import sjakk.Position;

/**
 *
 * @author Joakim
 */
public class King extends Piece {
    private final String imagePathWhite = "images/wking.png";
    private final String imagePathBlack = "images/bking.png";
    
    private boolean moved;
    
    // public King(Player color, Position pos, Chessboard board) {
    public King(int color, Position pos, Chessboard board) {
        super(color, pos, board);
    }
    
    public King(Chessboard board, Piece other) {
        super(board, other);
    }
    
    @Override
    public void updateCurrentPos(Position pos) {
        this.currentPos = pos;
        moved = true;
    }
    
    @Override
    public String getPieceImagePath(){
        if(pieceColor == Player.WHITE){
            return imagePathWhite;
        }
        
        return imagePathBlack;
    }

    @Override
    protected void addAllPossibleMoves() {        
        posMoves.clear();
        Position pos = new Position(this.currentPos.getRow() -1, 
                this.currentPos.getColumn() - 1);
        
        checkSpace(pos);
       
        pos = new Position(this.currentPos.getRow() - 1,this.currentPos.getColumn());
        checkSpace(pos);
        
        pos = new Position(this.currentPos.getRow() - 1, this.currentPos.getColumn() +1 );
        checkSpace(pos);
        
        pos = new Position(this.currentPos.getRow(), this.currentPos.getColumn() -1);
        checkSpace(pos);

        pos = new Position(this.currentPos.getRow(), this.currentPos.getColumn() +1);
        checkSpace(pos);
        
        pos = new Position(this.currentPos.getRow() +1, this.currentPos.getColumn() -1);
        checkSpace(pos);
        
        pos = new Position(this.currentPos.getRow() +1, this.currentPos.getColumn());
        checkSpace(pos);
        
        pos = new Position(this.currentPos.getRow() +1, this.currentPos.getColumn() +1);
        checkSpace(pos);
        
    }
    
    private void checkCastling(){
        queenSideCastling();
        kingSideCastling();
        
    }
    
    private void queenSideCastling() {
        if (moved) {
            return;
        }
        for(int column = 4; column<7; column++){
            Position pos = new Position(this.currentPos.getRow(),column);
            if(!board.isFree(pos)){
                return;
            }
        }
        
        Position queenSideTower = new Position(this.currentPos.getRow(), 7);
        if (board.containsEnemy(this.currentPos, queenSideTower)) {
            return;
        }

        if (!board.isUnmovedTower(queenSideTower)) {
            return;
        }
        super.addMove(queenSideTower);
    }
    
    private void kingSideCastling(){
        if (moved) {
            return;
        }
        for(int column = 2; column > 0; column--){
            Position pos = new Position(this.currentPos.getRow(),column);
            if(!board.isFree(pos)){
                return;
            }
        }
        
        Position kingSideTower = new Position(this.currentPos.getRow(), 0);
        if (board.containsEnemy(this.currentPos, kingSideTower)) {
            return;
        }

        if (!board.isUnmovedTower(kingSideTower)) {
            return;
        }
        super.addMove(kingSideTower);
    }
    
    private void checkSpace(Position pos){
        if(board.containsEnemy(this.currentPos,pos)){
            super.addMove(pos);
        }else if(board.isFree(pos)){
            super.addMove(pos);
        }
    }    
}
