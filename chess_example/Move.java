package sjakk;

import pieces.Piece;

/**
 *
 * @author Joakim
 */
public class Move {
    private Position startPos;
    private Position endPos;
    private Piece movingPiece;
    private boolean castling;
    
    public Move(Position startPos, Position endPos, Piece movingPiece){
        this.startPos = startPos;
        this.endPos = endPos;
        this.movingPiece = movingPiece;
    }
    
    //For castling
    public Move(Position startPos, Position endPos){
        this.startPos = startPos;
        this.endPos = endPos;
        castling = true;
    }

    public boolean equals(Object o) {
	if (o != null && o instanceof Move) {
	    return this.startPos.equals(o.startPos) &&
		this.endPos.equals(o.endPos);
	}
	return false;
    }
    
    public Position getEndPos(){
        return endPos;
    }
    public Position getStartPos(){
        return startPos;
    }
    
    public Piece getMovingPiece(){
        return movingPiece;
    }
    
    public void setMovingPiece(Piece piece){
        movingPiece = piece;
    }
    
    @Override
    public String toString(){
	String s1 = positionToString(startPos);
	String s2 = positionToString(endPos);
	String s3 = s1.concat("-->");
	String result = s3.concat(s2);
        // String result = positionToString(startPos) + "->" + positionToString(endPos);
        return result;        
    }
    
    private String positionToString(Position pos){
        char letter = (char) (pos.getColumn() + 65);
	char[] letters = new char[1];
	letters[0] = letter;
	String letter_str = new String(letters, 0, 1);
	String s1 = letter_str.concat("");
	String result = s1.concat(Integer.toString(pos.getRow()+1));
        // String result = letter + "" + (pos.getRow()+1);
        return result;        
    }
    
}
