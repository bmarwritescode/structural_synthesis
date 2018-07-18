package Chessboard;

import enums.Player;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Tower;
import sjakk.Position;

/**
 *
 * @author Joakim
 */
public abstract class ChessboardBuilder {

    public static Chessboard build() {
        // Piece pieces[][] = new Piece[8][8];
	TwoDArray pieces = new TwoDArray(8,8);
        Chessboard chessboard = new Chessboard();
        
        addWhitePieces(pieces, chessboard, false, false);
        addBlackPieces(pieces, chessboard, false, false);
	setWhiteSpaces(pieces, chessboard);
        chessboard.setPieces(pieces);
        chessboard.setKingPosition(new Position(0,3), new Position(7,3));
        return chessboard;
    }


    public static Chessboard buildWithSuperPawns() {
        // Piece pieces[][] = new Piece[8][8];
	TwoDArray pieces = new TwoDArray(8,8);
        Chessboard chessboard = new Chessboard();
        
        addWhitePieces(pieces, chessboard, true, false);
        addBlackPieces(pieces, chessboard, true, false);
	setWhiteSpaces(pieces, chessboard);
        chessboard.setPieces(pieces);
        chessboard.setKingPosition(new Position(0,3), new Position(7,3));
        return chessboard;
    }

    public static Chessboard buildWithAdvisors() {
        // Piece pieces[][] = new Piece[8][8];
	TwoDArray pieces = new TwoDArray(8,8);
        Chessboard chessboard = new Chessboard();
        
        addWhitePieces(pieces, chessboard, false, true);
        addBlackPieces(pieces, chessboard, false, true);
	setWhiteSpaces(pieces, chessboard);
        chessboard.setPieces(pieces);
        chessboard.setKingPosition(new Position(0,3), new Position(7,3));
        return chessboard;
    }

    public static Chessboard copy(Chessboard old){
        Chessboard newBoard = new Chessboard();
        // Piece pieces[][] = new Piece[8][8];
        TwoDArray pieces = new TwoDArray(8,8);
        // for(Piece piece: old.getPieceList()){
	ArrayList<Piece> oldPieces = old.getPieceList();
        // for(Piece piece: old.getPieceList()){
        for(int i=0; i < oldPieces.size(); i++) {
	    Piece piece = oldPieces.get(i);
            // pieces[piece.getPosition().getRow()]
            //         [piece.getPosition().getColumn()] = clonePiece(newBoard,piece);
	    Position pos = piece.getPosition();
	    pieces.set(pos.getRow(), pos.getColumn(), clonePiece(newBoard, piece));
        }        
        newBoard.setPieces(pieces);
        newBoard.setKingPosition(old.kingPosition(Player.WHITE),
                old.kingPosition(Player.BLACK));
        return newBoard;
    }

    private static Piece clonePiece(Chessboard board, Piece piece) {
        if(piece == null){
            return null;
        }
        
        if (piece instanceof Pawn) {
            return new Pawn(board, piece);
        }
        if (piece instanceof Tower) {
            return new Tower(board, piece);
        }
        if (piece instanceof Knight) {
            return new Knight(board, piece);
        }
        if (piece instanceof Queen) {
            return new Queen(board, piece);
        }
        if (piece instanceof King) {
            return new King(board, piece);
        }
        if (piece instanceof Bishop) {
            return new Bishop(board, piece);
        }

        // throw new IllegalStateException("Piece instance not found");
	return null;
    }

    private static void setWhiteSpaces(TwoDArray pieces, Chessboard board) {
	for(int i=2; i<6; i++) {
	    for(int j=0; j<8; j++) {
		pieces.set(i,j,null);
	    }
	}
    }
    
    // private static void addWhitePieces(Piece pieces[][], Chessboard board) {
    private static void addWhitePieces(TwoDArray pieces, Chessboard board, boolean includeSuperPawns, boolean includeAdvisors) {
	Position pos = new Position(0, 0);
	if (!includeAdvisors) {
	    pieces.set(0,0, new Tower(Player.WHITE, pos, board));
	    // pieces[0][0] = new Tower(Player.WHITE, pos, board);

	    pos = new Position(0, 1);
	    pieces.set(0, 1, new Knight(Player.WHITE, pos, board));
	    // pieces[0][1] = new Knight(Player.WHITE, pos, board);

	    pos = new Position(0, 2);
	    pieces.set(0, 2, new Bishop(Player.WHITE, pos, board));
	    // pieces[0][2] = new Bishop(Player.WHITE, pos, board);

	    pos = new Position(0, 3);
	    pieces.set(0, 3, new King(Player.WHITE, pos, board));
	    // pieces[0][3] = new King(Player.WHITE, pos, board);
	}
	
        pos = new Position(0, 4);
	if (includeAdvisors) {
	    pieces.set(0, 4, new Advisor(Player.WHITE, pos, board));
	} else {
	    pieces.set(0, 4, new Queen(Player.WHITE, pos, board));
	}
        // pieces[0][4] = new Queen(Player.WHITE, pos, board);

	if (!includeAdvisors) {
	    pos = new Position(0, 5);
	    pieces.set(0, 5, new Bishop(Player.WHITE, pos, board));
	    // pieces[0][5] = new Bishop(Player.WHITE, pos, board);

	    pos = new Position(0, 6);
	    pieces.set(0, 6, new Knight(Player.WHITE, pos, board));
	    // pieces[0][6] = new Knight(Player.WHITE, pos, board);

	    pos = new Position(0, 7);
	    pieces.set(0, 7, new Tower(Player.WHITE, pos, board));
	    // pieces[0][7] = new Tower(Player.WHITE, pos, board);
	}

        for (int i = 0; i < 8; i++) {
            pos = new Position(1, i);
	    if (includeSuperPawns) {
		pieces.set(1, i, new SuperPawn(Player.WHITE, pos, board));
	    } else {
		pieces.set(1, i, new Pawn(Player.WHITE, pos, board));
	    }
            // pieces[1][i] = new Pawn(Player.WHITE, pos, board);
        }
    }

    // private static void addBlackPieces(Piece pieces[][], Chessboard board) {
    private static void addBlackPieces(TwoDArray pieces, Chessboard board, boolean includeSuperPawns, boolean includeAdvisors) {
        Position pos = new Position(7, 0);
	if (!includeAdvisors) {
	    pieces.set(7, 0, new Tower(Player.BLACK, pos, board));
	    // pieces[7][0] = new Tower(Player.BLACK, pos, board);

	    pos = new Position(7, 1);
	    pieces.set(7, 1, new Knight(Player.BLACK, pos, board));
	    // pieces[7][1] = new Knight(Player.BLACK, pos, board);

	    pos = new Position(7, 2);
	    pieces.set(7, 2, new Bishop(Player.BLACK, pos, board));
	    // pieces[7][2] = new Bishop(Player.BLACK, pos, board);

	    pos = new Position(7, 3);
	    pieces.set(7, 3, new King(Player.BLACK, pos, board));
	    // pieces[7][3] = new King(Player.BLACK, pos, board);
	}
	
        pos = new Position(7, 4);
	if (includeAdvisors) {
	    pieces.set(7, 4, new Advisor(Player.BLACK, pos, board));
	} else {
	    pieces.set(7, 4, new Queen(Player.BLACK, pos, board));
	}
        // pieces[7][4] = new Queen(Player.BLACK, pos, board);

	if (!includeAdvisors) {
	    pos = new Position(7, 5);
	    pieces.set(7, 5, new Bishop(Player.BLACK, pos, board));
	    // pieces[7][5] = new Bishop(Player.BLACK, pos, board);

	    pos = new Position(7, 6);
	    pieces.set(7, 6, new Knight(Player.BLACK, pos, board));
	    // pieces[7][6] = new Knight(Player.BLACK, pos, board);

	    pos = new Position(7, 7);
	    pieces.set(7, 7, new Tower(Player.BLACK, pos, board));
	    // pieces[7][7] = new Tower(Player.BLACK, pos, board);
	}
	    
        for (int i = 0; i < 8; i++) {
            pos = new Position(6, i);
	    if (includeSuperPawns) {
		pieces.set(6, i, new SuperPawn(Player.BLACK, pos, board));
	    } else {
		pieces.set(6, i, new Pawn(Player.BLACK, pos, board));
	    }
            // pieces[6][i] = new Pawn(Player.BLACK, pos, board);
        }
    }

}
