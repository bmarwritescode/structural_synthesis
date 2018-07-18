package Chessboard;

import enums.Player;
import pieces.*;
import java.util.ArrayList;
import sjakk.Chess;
import sjakk.Move;
import sjakk.Position;

/**
 *
 * @author Joakim
 */
public class Chessboard {

    // private Piece pieces[][];
    private TwoDArray pieces;
    
    
    private Position whiteKingPosition;
    private Position blackKingPosition;
    
    private ArrayList<Piece> blackPieces;
    private ArrayList<Piece> whitePieces;
    public Chessboard(){
        
    }

    // protected void setPieces(Piece pieces[][]){
    protected void setPieces(TwoDArray pieces){
        this.pieces = pieces;
    }
    
    protected void setBlackPieces(ArrayList<Piece> bPieces){
        this.blackPieces = bPieces;
    }
    
    protected void setWhitePieces(ArrayList<Piece> wPieces){
        this.blackPieces = wPieces;
    } 
    
    protected void setKingPosition(Position white, Position black){
        whiteKingPosition = white;
        blackKingPosition = black;
    }
    
    // protected Piece[][] getPieces(){
    protected TwoDArray getPieces(){
        return pieces;
    }
    
    public ArrayList<Piece> getPieceList() {
        ArrayList<Piece> all = getPieceList(Player.BLACK);
        all.addAll(getPieceList(Player.WHITE));
        return all;
    }

    // public ArrayList<Piece> getPieceList(Player player) {
    public ArrayList<Piece> getPieceList(int player) {
        ArrayList<Piece> pieceList = new ArrayList<Piece>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
		Piece p = pieces.get(x,y);
                if (p != null && p.getPieceColor()
                        == player) {
                    pieceList.add(p);
                }
            }
        }
        return pieceList;
    }

    public Piece getPiece(Position pos) {
        // return pieces[pos.getRow()][pos.getColumn()];
	return pieces.get(pos.getRow(), pos.getColumn());
    }

    public ArrayList<Move> getLegalMovesFrom(Position pos){
        ArrayList<Move> moves = new ArrayList<Move>();
        if(isOutOfBounds(pos)){
            return moves;
        }
        // Piece piece = pieces[pos.getRow()][pos.getColumn()];
        Piece piece = pieces.get(pos.getRow(),pos.getColumn());
        if(piece == null){
            return moves;
        }

	ArrayList<Move> pieceMoves = piece.getMoves();
	for (int i=0; i < pieceMoves.size(); i++) {
	    Move move = pieceMoves.get(i);
            if(!movesPlayerIntoCheck(move)){
                moves.add(move);
            }
	}
        // for(Move move: piece.getMoves()){
        //     if(!movesPlayerIntoCheck(move)){
        //         moves.add(move);
        //     }
        // }
        return moves;
    }
    
    private ArrayList<Move> getLegalMoves(Piece piece){
        ArrayList<Move> moves = new ArrayList<Move>();

        if(piece == null){
            return moves;
        }
        
	ArrayList<Move> pMoves = piece.getMoves();
        // for(Move move: piece.getMoves(){)
        for(int i=0; i < pMoves.size(); i++) {
	    Move move = pMoves.get(i);
            if(!movesPlayerIntoCheck(move)){
                moves.add(move);
            }
        }
        return moves;
    }    
        
    // public ArrayList<Move> getLegalMoves(Player player){
    public ArrayList<Move> getLegalMoves(int player){
        ArrayList<Move> moves = new ArrayList<Move>();
	ArrayList<Piece> pieces = getPieceList(player);
        // for(Piece piece: getPieceList(player)){
        for(int i=0; i < pieces.size(); i++){
	    Piece piece = pieces.get(i);
            moves.addAll(getLegalMoves(piece));
        }
        return moves;
    }
    
    // /**
    //  * Updates a move on the chessboard piece array. Does not enforce any rules!
    //  *
    //  * @param move
    //  */
    public void updateMove(Move move) {
        // Piece movedPiece = pieces[move.getStartPos().getRow()][move.getStartPos().getColumn()];
	Position pos = move.getStartPos();
        Piece movedPiece = pieces.get(pos.getRow(), pos.getColumn());      
        movedPiece.updateCurrentPos(move.getEndPos(), move);

        // pieces[move.getStartPos().getRow()][move.getStartPos().getColumn()] = null;
        pieces.set(pos.getRow(), pos.getColumn(), null);

        // pieces[move.getEndPos().getRow()][move.getEndPos().getColumn()] = movedPiece;
	Position endPos = move.getEndPos();
        pieces.set(endPos.getRow(), endPos.getColumn(), movedPiece);
        if(movedPiece instanceof King){
            updateKingPosition(move);
        }
        if(movedPiece instanceof Pawn){
            checkPawnPromotion(move);
        }        
    }
    
    private void updateCastlingMove(Move move){
	Position startPos = move.getStartPos();
	Position endPos = move.getEndPos();
        int kingX = startPos.getRow();
        int kingY = endPos.getColumn();
        // Piece king = pieces[kingX][kingY];
        Piece king = pieces.get(kingX, kingY);
        int towerX = endPos.getRow();
        int towerY = endPos.getColumn();
        // Piece tower = pieces[towerX][towerY];
        Piece tower = pieces.get(towerX, towerY);
        
        // pieces[kingX][kingY] = null;
	// pieces[towerX][towerY] = null;
        pieces.set(kingX, kingY, null);
	pieces.set(towerX, towerY, null);
        
        if(towerY > kingY){ //Queenside
            Position kPos = new Position(kingX,kingY-2 );
            king.updateCurrentPos(kPos, move);
        }
        
    }
    
    private void checkPawnPromotion(Move move) {
        int row = move.getEndPos().getRow();

        if (row == 7 || row == 0) {
            
	    Piece p = move.getMovingPiece();
            // Player pieceColor = p.getPieceColor();
            int pieceColor = p.getPieceColor();
	    Piece thisPiece = this;
            Queen queen = new Queen(pieceColor, move.getEndPos(), thisPiece);
	    Position endPos = move.getEndPos();
            int column = endPos.getColumn();
            // pieces[row][column] = queen;
            pieces.set(row, column, queen);
            move.setMovingPiece(queen);
        }
    }
    
    private void updateKingPosition(Move move){
        Piece piece = move.getMovingPiece();
        if(piece.getPieceColor() == Player.WHITE){
            whiteKingPosition = move.getEndPos();
        } else {
            blackKingPosition = move.getEndPos();
        }
    }
    
    public boolean containsEnemy(Position startPos, Position endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
        if (isFree(endPos)) {
            return false;
        }
        // Player movingPieceColor = pieces[startPos.getRow()][startPos.getColumn()].getPieceColor();
	Piece p1 = pieces.get(startPos.getRow(), startPos.getColumn());
        int movingPieceColor = p1.getPieceColor();

	Piece p2 = pieces.get(endPos.getRow(), endPos.getColumn());
        int recivingPieceColor = p2.getPieceColor();

        return (movingPieceColor != recivingPieceColor);

    }

    public boolean isFree(Position endPos) {
        if (isOutOfBounds(endPos)) {
            return false;
        }
	Piece p = pieces.get(endPos.getRow(), endPos.getColumn());
        return (p == null);
    }

    public boolean isOutOfBounds(Position endPos) {
        return endPos.getRow() >= 8 || endPos.getRow() < 0
                || endPos.getColumn() >= 8 || endPos.getColumn() < 0;
    }

    // /**
    //  * Retrives the other players possible moves and checks if he can hit the king
    //  * @param color
    //  * @return 
    //  */
    // public boolean isInCheck(Player player) {
    public boolean isInCheck(int player) {
        Position kingPos = kingPosition(player);

        // Player opponent = Chess.switchPlayer(player);
        int opponent = Chess.switchPlayer(player);
        ArrayList<Piece> pieceList = getPieceList(opponent);
	for(int i=0; i < pieceList.size(); i++) {
	    Piece p = pieceList.get(i);
	    ArrayList<Move> moves = p.getMoves();
	    for(int j=0; j<moves.size(); j++) {
		Move m = moves.get(i);
		Position endPos = m.getEndPos();
                if (endPos.equals(kingPos)) {
                    return true;
                }		
	    }
	}
        // for (Piece p : pieceList) {
        //     for (Move m : p.getMoves()) {
        //         if (m.getEndPos().equals(kingPos)) {
        //             return true;
        //         }
        //     }
        // }
        return false;
    }

    // public Position kingPosition(Player player) {
    public Position kingPosition(int player) {
        if(player == Player.WHITE){
            return whiteKingPosition;
        } else {
            return blackKingPosition;
        }
    }
    
    public boolean isUnmovedTower(Position pos){
        // Piece piece = pieces[pos.getRow()][pos.getColumn()];
        Piece piece = pieces.get(pos.getRow(), pos.getColumn());
        if(!(piece instanceof Tower)){
            return false;
        }
        Tower tower = (Tower) piece;
       
        
        if(tower.isMoved()){
            return false;
        }
        
        return true;
    }

    public boolean movesPlayerIntoCheck(Move move) {
        // Player player = move.getMovingPiece().getPieceColor();
        int player = move.getMovingPiece().getPieceColor();

	Chessboard thisBoard = this;
        Chessboard newBoard = ChessboardBuilder.copy(thisBoard);
        newBoard.updateMove(move);
        if (newBoard.isInCheck(player)) {
            return true;
        }

        return false;

    }

    public boolean isValidMove(Move move) {

        if (!isIsolatedValidMove(move)) {
            return false;
        }
        
        if (movesPlayerIntoCheck(move)) {
            return false;
        }
        return true;
    }

    private boolean isIsolatedValidMove(Move move) {
        Piece piece = move.getMovingPiece();
        Position candidate = move.getEndPos();
        //Check that the move is in the possible moves list
        // for (Move m : piece.getMoves()) {
	ArrayList<Move> moves = piece.getMoves();
        for (int i=0; i<moves.size(); i++) {
	    Move m = moves.get(i);
            Position endPos = m.getEndPos();
            if (candidate.equals(endPos)) {
                return true;
            }
        }
        return false;
    }


}
