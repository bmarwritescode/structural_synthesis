/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Queen extends Piece {

    private final String imagePathWhite = "images/wqueen.png";
    private final String imagePathBlack = "images/bqueen.png";

    // public Queen(Player color, Position pos, Chessboard board) {
    public Queen(int color, Position pos, Chessboard board) {
        super(color, pos, board);
    }
    
    public Queen(Chessboard board, Piece other){
        super(board,other);
    }

    @Override
    public String getPieceImagePath() {
        if (this.pieceColor == Player.WHITE) {
            return imagePathWhite;
        }

        return imagePathBlack;
    }

    @Override
    public void addAllPossibleMoves() {
        posMoves.clear();

        Bishop bishop = new Bishop(this.pieceColor, this.currentPos, this.board);
        Tower tower = new Tower(this.pieceColor, this.currentPos, this.board);
        ArrayList<Move> bishopMoves = bishop.getMoves();
        ArrayList<Move> towerMoves = tower.getMoves();

        bishopMoves.addAll(towerMoves);
        // for (Move m : bishopMoves) {
        for (int i=0; i < bishopMoves.size(); i++) {
	    Move m = bishopMoves.get(i);
            super.addMove(m.getEndPos());
        }

    }
}
