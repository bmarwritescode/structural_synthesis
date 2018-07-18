/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sjakk;

import Chessboard.Chessboard;
import enums.Player;
import pieces.*;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Joakim
 */
public class ChessboardTest {

    private Chessboard chessboard;

    public ChessboardTest() {
    }

    harness public void mn() {
	// setUpNormalBoard();
	// testBoardDimensions();
	// testPrepareBoardPieceColors();
	// testPrepareBoardPieceType();
	// testPrepareBoardRightNumberOfPieces();
	// testUpdateMoveSimple();
	// testUpdateAttackingMove();	

	// setUpNormalBoard();
	// testContainsEnemyOnEnemyPosition();
	// testContainsEnemyOnFriendlyPosition();
	// testContainsEnemyOnEmptyPosition();
	// testContainsEnemyOnOwnPosition();
	// testIsFreeOnFreeSquare();
	// testIsFreeOnOccupiedSquare();    
	// testIsOutOfBounds();

	// setUpBoardWithSuperPawns();
	// testUpdateMoveSimpleWithSuperPawn();	

	setUpBoardWithAdvisors();
	testUpdateMoveSimpleWithAdvisors();		
    }
    
    @Before
    public void setUpNormalBoard() {
        chessboard = ChessboardBuilder.build();
    }

    @Before
    public void setUpBoardWithSuperPawns() {
        chessboard = ChessboardBuilder.buildWithSuperPawns();
    }

    @Before
    public void setUpBoardWithAdvisors() {
        chessboard = ChessboardBuilder.buildWithAdvisors();
    }

    @After
    public void tearDown() {
        chessboard = null;
    }

    @Test
    public void testBoardDimensions() {
        // Piece board[][] = chessboard.getBoard();
        TwoDArray board = chessboard.getPieces();
        Assert.assertEquals("Dimension is not 8x8", 8, board.length);
	// Piece[] row = board.getRow(0);
        // Assert.assertEquals("Dimension is not 8x8", 8, row.length);
    }

    @Test
    public void testPrepareBoardPieceColors() {
        // Piece board[][] = chessboard.getBoard();
        TwoDArray board = chessboard.getPieces();
        //Check colors
        for (int x = 0; x < 2; x++) {
            for (int y = 0; y < 8; y++) {
		Piece p = board.get(x,y);
                Assert.assertEquals("Wrong piece color", Player.WHITE,
                        p.getPieceColor());
            }
        }

        for (int x = 7; x < 5; x--) {
            for (int y = 0; y < 8; y++) {
		Piece p = board.get(x,y);		
                Assert.assertEquals("Wrong piece color", Player.BLACK,
                        p.getPieceColor());
            }
        }
    }

    @Test
    public void testPrepareBoardPieceType() {
        // Piece board[][] = chessboard.getBoard();
        TwoDArray board = chessboard.getPieces();
	boolean rightPiece;
	rightPiece = board.get(0,0) instanceof Tower;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,1) instanceof Knight;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,2) instanceof Bishop;	
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,3) instanceof King;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,4) instanceof Queen;	
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,5) instanceof Bishop;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,6) instanceof Knight;	
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(0,7) instanceof Tower;
        Assert.assertTrue(rightPiece);

	rightPiece = board.get(7,0) instanceof Tower;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,1) instanceof Knight;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,2) instanceof Bishop;	
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,3) instanceof King;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,4) instanceof Queen;	
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,5) instanceof Bishop;
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,6) instanceof Knight;	
        Assert.assertTrue(rightPiece);
	rightPiece = board.get(7,7) instanceof Tower;
        Assert.assertTrue(rightPiece);
	
        //Test pawns
        for (int y = 0; y < 8; y++) {
	    rightPiece = board.get(1,y) instanceof Pawn;
	    Assert.assertTrue(rightPiece);
	    rightPiece = board.get(6,y) instanceof Pawn;
	    Assert.assertTrue(rightPiece);	    
        }
    }

    public void isNullPiece(Piece p) {
	if (p != null) {
	    if (p instanceof Pawn) {
		assert false;
	    }
	    if (p instanceof Knight) {
		assert false;
	    }
	    if (p instanceof Tower) {
		assert false;
	    }
	    if (p instanceof Bishop) {
		assert false;
	    }
	    if (p instanceof King) {
		assert false;
	    }
	    if (p instanceof Queen) {
		assert false;
	    }
	    assert false;		    
	}     
    }
    
    @Test
    public void testPrepareBoardRightNumberOfPieces() {
        // Piece[][] board = chessboard.getBoard();
        TwoDArray board = chessboard.getPieces();
        //Checks middleground for pieces
        for (int x = 2; x < 6; x++) {
            for (int y = 0; y < 8; y++) {
		Assert.assertNull(board.get(x,y));
            }
        }
	// isNullPiece(board.get(2,0));
	// isNullPiece(board.get(2,1));
	// isNullPiece(board.get(2,2));
	// isNullPiece(board.get(2,3));	
	// isNullPiece(board.get(2,4));
	// isNullPiece(board.get(2,5));
	// isNullPiece(board.get(2,6));
	// isNullPiece(board.get(2,7));	

	// isNullPiece(board.get(3,0));
	// isNullPiece(board.get(3,1));
	// isNullPiece(board.get(3,2));
	// isNullPiece(board.get(3,3));	
	// isNullPiece(board.get(3,4));
	// isNullPiece(board.get(3,5));
	// isNullPiece(board.get(3,6));
	// isNullPiece(board.get(3,7));	

	// isNullPiece(board.get(4,0));
	// isNullPiece(board.get(4,1));
	// isNullPiece(board.get(4,2));
	// isNullPiece(board.get(4,3));	
	// isNullPiece(board.get(4,4));
	// isNullPiece(board.get(4,5));
	// isNullPiece(board.get(4,6));
	// isNullPiece(board.get(4,7));	

	// isNullPiece(board.get(5,0));
	// isNullPiece(board.get(5,1));
	// isNullPiece(board.get(5,2));
	// isNullPiece(board.get(5,3));	
	// isNullPiece(board.get(5,4));
	// isNullPiece(board.get(5,5));
	// isNullPiece(board.get(5,6));
	// isNullPiece(board.get(5,7));	
    }

    @Test
    public void testUpdateMoveSimple() {
        // Piece oldBoard[][] = chessboard.getBoard();
        TwoDArray oldBoard = chessboard.getPieces();
        //Moves pawn at e2 to e 4.
        Position pos = new Position(1, 4);
        Position endPos = new Position(3, 4);
        Move move = new Move(pos, endPos);
        chessboard.updateMove(move);
        // Piece newBoard[][] = chessboard.getBoard();
        TwoDArray newBoard = chessboard.getPieces();

        //Assert board not changes apart from move
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x != 1 && y != 4) || (x != 3 && y != 4)) {
		    Piece p1 = oldBoard.get(x,y);
		    Piece p2 = newBoard.get(x,y);
                    Assert.assertEquals(p1, p2);
                }
            }
        }

        //Check that move has been updated.
	Piece p3 = newBoard.get(1,4);
        Assert.assertNull(p3);
	Piece p4 = newBoard.get(3,4);
	boolean movedPiece = p4 instanceof Pawn;
        Assert.assertTrue(movedPiece);
    }

    @Test
    public void testUpdateMoveSimpleWithSuperPawn() {
        // Piece oldBoard[][] = chessboard.getBoard();
        TwoDArray oldBoard = chessboard.getPieces();

	Piece mySuperPawn = oldBoard.get(1,4);

	Position startPos = new Position(1,4);
	Position endPosClose = new Position(3,4);
	Position endPosFar = new Position(4,4);

	Move moveClose = new Move(startPos, endPosClose);
	Move moveFar = new Move(startPos, endPosFar);

	ArrayList<Move> possibleMoves = mySuperPawn.getMoves();
	assert possibleMoves.contains(moveClose);
	assert possibleMoves.contains(moveFar);
	assert possibleMoves.size() == 2;
    }
    
    @Test
    public void testUpdateMoveSimpleWithAdvisors() {
        // // Piece oldBoard[][] = chessboard.getBoard();
        TwoDArray oldBoard = chessboard.getPieces();

	Piece myAdvisor = oldBoard.get(0,4);

	Position startPos = new Position(0,4);
	Position endPosLeft = new Position(0,3);
	Position endPosRight = new Position(0,5);

	Move moveLeft = new Move(startPos, endPosLeft);
	Move moveRight = new Move(startPos, endPosRight);

	ArrayList<Move> possibleMoves = myAdvisor.getMoves();
	assert possibleMoves.contains(moveLeft);
	assert possibleMoves.contains(moveRight);
	assert possibleMoves.size() == 2;

        chessboard.updateMove(moveLeft);

	startPos = new Position(0,3);
	endPosRight = new Position(0,4);

	moveRight = new Move(startPos, endPosRight);
	possibleMoves = myAdvisor.getMoves();
	posssibleMoves.contains(moveRight);
	assert possibleMoves.size() == 1;

        chessboard.updateMove(moveRight);

	startPos = new Position(0,4);
	endPosRight = new Position(0,5);

	moveRight = new Move(startPos, endPosRight);
        chessboard.updateMove(moveRight);
	
	startPos = new Position(0,5);
	endPosLeft = new Position(0,4);

	moveLeft = new Move(startPos, endPosLeft);
	possibleMoves = myAdvisor.getMoves();
	posssibleMoves.contains(moveLeft);
	assert possibleMoves.size() == 1;
    }
    
    @Test
    public void testUpdateAttackingMove() {
        // Piece oldBoard[][] = chessboard.getBoard();
        TwoDArray oldBoard = chessboard.getPieces();

        //Attacks A7 with pawn at a2.. Illegal moves works here

        Position pos = new Position(1, 0);
        Position endPos = new Position(6, 0);
        Move move = new Move(pos, endPos);
        chessboard.updateMove(move);
        // Piece newBoard[][] = chessboard.getBoard();
        TwoDArray newBoard = chessboard.getPieces();

	
        //Assert board not changes apart from move
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if ((x != 1 && y != 0) || (x != 6 && y != 0)) {
		    Piece p1 = oldBoard.get(x,y);
		    Piece p2 = newBoard.get(x,y);
                    Assert.assertEquals(p1, p2);
                }
            }
        }

        //Check that move has been updated.
	Piece p3 = newBoard.get(1,0);
        Assert.assertNull(p3);
	Piece p4 = newBoard.get(6,0);
	boolean pieceMoved = p4 instanceof Pawn;
        Assert.assertTrue(pieceMoved);
    }

    @Test
    public void testContainsEnemyOnEnemyPosition() {
        Position white = new Position(0, 0);
        Position black = new Position(6, 0);
        Assert.assertTrue(chessboard.containsEnemy(white, black));
    }

    @Test
    public void testContainsEnemyOnFriendlyPosition() {
        Position white = new Position(0, 0);
        Position friendly = new Position(0, 5);
        Assert.assertFalse(chessboard.containsEnemy(white, friendly));
    }

    @Test
    public void testContainsEnemyOnEmptyPosition() {
        Position white = new Position(0, 0);
        Position empty = new Position(5, 5);
        Assert.assertFalse(chessboard.containsEnemy(white, empty));

    }

    @Test
    public void testContainsEnemyOnOwnPosition() {
        Position white = new Position(0, 0);
        Position selff = new Position(0, 0);
        Assert.assertFalse(chessboard.containsEnemy(white, selff));
    }

    @Test
    public void testIsFreeOnFreeSquare() {
        Position pos = new Position(4,4);
        Assert.assertTrue(chessboard.isFree(pos));
    }
    
    @Test
    public void testIsFreeOnOccupiedSquare() {
        Position pos = new Position(0,0);
        Assert.assertFalse(chessboard.isFree(pos));    
    }
    
    @Test
    public void testIsOutOfBounds() {
        Position pos = new Position(5,5); //Mid point
        Assert.assertFalse(chessboard.isOutOfBounds(pos));
        
        pos = new Position(-1,-1);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(-1,0);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(-1,7);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(-1,8);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(0,-1);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(0,0);
        Assert.assertFalse(chessboard.isOutOfBounds(pos));
        
        pos = new Position(0,7);
        Assert.assertFalse(chessboard.isOutOfBounds(pos));
        
        pos = new Position(0,8);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(7,-1);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(7,0);
        Assert.assertFalse(chessboard.isOutOfBounds(pos));
        
        pos = new Position(7,7);
        Assert.assertFalse(chessboard.isOutOfBounds(pos));
        
        pos = new Position(7,8);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(8,-1);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(8,0);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(8,7);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
        
        pos = new Position(8,8);
        Assert.assertTrue(chessboard.isOutOfBounds(pos));
    }

    // @Test
    // public void testIsInCheck() {
    //     fail("Not implemented");
    // }
}
