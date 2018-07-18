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

    @Override
    public String getPieceImagePath(){
        if(pieceColor == Player.WHITE){
            return imagePathWhite;
        }
        
        return imagePathBlack;
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
	if (?? && isInSection(queenSideTower)) super.addMove(queenSideTower);
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
	if (?? && isInSection(kingSideTower)) super.addMove(kingSideTower);
    }
    
    @Override
    public void addAllPossibleMoves() {
	if (??) {
	    posMoves.clear();
	    if (pieceColor == Player.BLACK) {
		moveBlackForward();
		attackBlack();
	    } else {
		moveWhiteForward();
		attackWhite();
	    }
	}
	if (??) {
	    posMoves.clear();
	    Position pos = new Position(this.currentPos.getRow() -??, 
		    this.currentPos.getColumn() - ??);

	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow() - ??,this.currentPos.getColumn());
	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow() - ??, this.currentPos.getColumn() +?? );
	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow(), this.currentPos.getColumn() -??);
	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow(), this.currentPos.getColumn() +??);
	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow() +??, this.currentPos.getColumn() -??);
	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow() +??, this.currentPos.getColumn());
	    checkSpace(pos);

	    pos = new Position(this.currentPos.getRow() +??, this.currentPos.getColumn() +??);
	    checkSpace(pos);
	}
	if (??) {
	    posMoves.clear();

	    Bishop bishop = new Bishop(this.pieceColor, this.currentPos, this.board);
	    Tower tower = new Tower(this.pieceColor, this.currentPos, this.board);
	    ArrayList<Move> bishopMoves = bishop.getMoves();
	    ArrayList<Move> towerMoves = tower.getMoves();

	    bishopMoves.addAll(towerMoves);
	    // for (Move m : bishopMoves) {
	    for (int i=0; i < bishopMoves.size(); i++) {
		Move m = bishopMoves.get(i);
		if (?? && isInSection(m.getEndPos())) super.addMove(m.getEndPos());
	    }
	}
	if (??) {
	    posMoves.clear();
	    checkNorthWest();
	    checkNorthEast();
	    checkSouthWest();
	    checkSouthEast();
	}
	if (??) {
	    posMoves.clear();

	     //?? north, ?? west:
	    Position pos = new Position(this.currentPos.getRow() +??, this.currentPos.getColumn() -??);
	    checkEncounter(pos);

	    //?? north, ?? east:
	    pos = new Position(this.currentPos.getRow() + ??, this.currentPos.getColumn() +??);
	    checkEncounter(pos);

	    //?? north, ?? west:
	    pos = new Position(this.currentPos.getRow()+??, this.currentPos.getColumn() -??);
	    checkEncounter(pos);

	    //?? north, ?? east:
	    pos = new Position(this.currentPos.getRow()+??, this.currentPos.getColumn()+??);
	    checkEncounter(pos);

	    //?? south, ?? west:
	    pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn() -??);
	    checkEncounter(pos);

	    //?? south, ?? east:
	    pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn()+??);
	    checkEncounter(pos);

	    //?? south, ?? east:
	    pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn()+??);
	    checkEncounter(pos);

	    //?? south, ?? west:
	    pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn()-??);
	    checkEncounter(pos);
	}
	if (??) {
	    posMoves.clear();
	    goNorth();
	    goSouth();
	    goWest();
	    goEast();
	}
    }

    private void goNorth() {
        int column = currentPos.getColumn();
        for (int i = currentPos.getRow() - ??; i >= 0; i--) {
            Position pos = new Position(i, column);
            if (board.isFree(pos)) {
		if (?? && isInSection(pos)) super.addMove(pos);
            } else {
                checkEncounter(pos);
                return;
            }
        }
    }

    private void goSouth() {
        int column = currentPos.getColumn();
        for (int i = currentPos.getRow() + ??; i < 8; i++) {
            Position pos = new Position(i, column);
            if (board.isFree(pos)) {
		if (?? && isInSection(pos)) super.addMove(pos);
            } else {
                checkEncounter(pos);
                return;
            }

        }
    }

    private void goWest() {
        int row = currentPos.getRow();
        for (int i = currentPos.getColumn() - ??; i >= 0; i--) {
            Position pos = new Position(row, i);
            if (board.isFree(pos)) {
		if (?? && isInSection(pos)) super.addMove(pos);
            } else {
                checkEncounter(pos);
                return;
            }
        }
    }

    private void goEast() {
        int row = currentPos.getRow();
        for (int i = currentPos.getColumn() + ??; i < 8; i++) {
            Position pos = new Position(row, i);
            if (board.isFree(pos)) {
		if (?? && isInSection(pos)) super.addMove(pos);
            } else {
                checkEncounter(pos);
                return;
            }
        }
    }
    
    public boolean isMoved(){
        return moved;
    }    
    
    private void checkNorthWest() {
        int row = currentPos.getRow() +??;
        int column = currentPos.getColumn() -??;
        
        while (row < 8 && column >= 0) {
            Position pos = new Position(row,column);
            
            if (!board.isFree(pos)) {
                checkEncounter(pos);
                return;
            } else {
		if (?? && isInSection(pos)) super.addMove(pos);
            }
            row++;
            column--;
        }
    }

    private void checkNorthEast() {
        int row =  currentPos.getRow() + ??;
        int column = currentPos.getColumn() + ??;
        
        while (row < 8 && column < 8) {
            Position pos = new Position(row,column);
            if (!board.isFree(pos)) {
                checkEncounter(pos);
                return;
            } else {
		if (?? && isInSection(pos)) super.addMove(pos);
            }
            row++;
            column++;
        }
    }

    private void checkSouthWest() {
        int row = currentPos.getRow() - ??;
        int column = currentPos.getColumn() - ??;
        while (row >= 0 && column >= 0) {
            Position pos = new Position(row, column);
            if (!board.isFree(pos)) {
                checkEncounter(pos);
                return;
            } else {
		if (?? && isInSection(pos)) super.addMove(pos);
            }
            row--;
            column--;
        }
    }

    private void checkSouthEast() {
        int row = currentPos.getRow() - ??;
        int column = currentPos.getColumn() + ??;
        while (row >= 0 && column < 8) {
            Position pos = new Position(row,column);
            if (!board.isFree(pos)) {
                checkEncounter(pos);
                return;
            } else {
		if (?? && isInSection(pos)) super.addMove(pos);
            }
            row--;
            column++;
        }
    }

    private void checkEncounter(Position pos) {
        if (board.containsEnemy(this.currentPos,pos)) {
	    if (?? && isInSection(pos)) super.addMove(pos);
        }
    }
    
    private void checkSpace(Position pos){
	if (board.isOutOfBounds(pos)) {
	    // assert false;
	} else if(board.containsEnemy(this.currentPos,pos)){
	    if (?? && isInSection(pos)) super.addMove(pos);
        }else if(board.isFree(pos)){
	    if (?? && isInSection(pos)) super.addMove(pos);
        } else {
	    // assert false;
	}
    }    
    
    private void moveBlackForward() {
        Position pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn());
        if(board.isFree(pos)){
	    if (?? && isInSection(pos)) super.addMove(pos);
        } else {
            return;
        }
        
        if (isInStartPos()) {
            pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn());
            if (board.isFree(pos)) {
		if (?? && isInSection(pos)) super.addMove(pos);
            }
        }
    }

    private void attackBlack() {
        //Check to the left
        Position pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn()-??);
        if (board.containsEnemy(this.currentPos,pos)) {
	    if (?? && isInSection(pos)) super.addMove(pos);
        }

        //Check to the right:
        pos = new Position(this.currentPos.getRow()-??, this.currentPos.getColumn()+??);
        if (board.containsEnemy(this.currentPos,pos)) {
	    if (?? && isInSection(pos)) super.addMove(pos);
        }
    }

    private void moveWhiteForward() {
        Position pos = new Position(this.currentPos.getRow()+??, this.currentPos.getColumn());
        if (board.isFree(pos)) {
	    if (?? && isInSection(pos)) super.addMove(pos);
        } else {
            return;
        } 

        //If in startpos
        if (isInStartPos()) {
            pos = new Position(this.currentPos.getRow()+??, this.currentPos.getColumn());
            if (board.isFree(pos)) {
		if (?? && isInSection(pos)) super.addMove(pos);
            }
        }
    }

    private void attackWhite() {
        //Check to the left
        Position pos = new Position(this.currentPos.getRow()+??, this.currentPos.getColumn()-??);
        if (board.containsEnemy(this.currentPos,pos)) {
	    if (?? && isInSection(pos)) super.addMove(pos);
        }

        //Check to the right:
        pos = new Position(this.currentPos.getRow()+??, this.currentPos.getColumn()+??);
        if (board.containsEnemy(this.currentPos,pos)) {
            if (?? && isInSection(pos)) super.addMove(pos);
        }
    }

    private boolean isInStartPos() {
        if (pieceColor == Player.BLACK) {
            return this.currentPos.getRow() == ??;
        } else {
            return this.currentPos.getRow() == ??;
        }
    }

}
