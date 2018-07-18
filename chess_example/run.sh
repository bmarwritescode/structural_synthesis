DP=../structural_synthesis/chess2

cd ../../java-sketch/

# ./jsk.sh model/lang/Object.java model/util/List.java model/util/ArrayList.java ${DP}/ChessboardTest.java ${DP}/Player.java ${DP}/Position.java ${DP}/Piece.java ${DP}/Move.java ${DP}/Chessboard.java ${DP}/TwoDArray.java ${DP}/ChessboardBuilder.java ${DP}/Chess.java ${DP}/Tower.java ${DP}/Queen.java ${DP}/Bishop.java ${DP}/Pawn.java ${DP}/Knight.java ${DP}/King.java ${DP}/SuperPawn.java ${DP}/Advisor.java

./jsk.sh model/lang/Object.java model/util/List.java model/util/ArrayList.java ${DP}/ChessboardTest.java ${DP}/Player.java ${DP}/Position.java ${DP}/Piece.java ${DP}/Move.java ${DP}/Chessboard.java ${DP}/TwoDArray.java ${DP}/ChessboardBuilder.java ${DP}/Chess.java ${DP}/Tower.java ${DP}/Queen.java ${DP}/Bishop.java ${DP}/Pawn.java ${DP}/Knight.java ${DP}/King.java ${DP}/SuperPawn.java ${DP}/Advisor_syn.java

# ./jsk.sh model/lang/Object.java axioms/util/ArrayList.java model/lang/String.java model/lang/CharacterSequence.java model/lang/Number.java model/lang/Integer.java model/org/junit ${DP}/ChessboardTest.java ${DP}/Player.java ${DP}/Position.java ${DP}/Piece.java ${DP}/Move.java ${DP}/Chessboard.java ${DP}/TwoDArray.java ${DP}/ChessboardBuilder.java ${DP}/Chess.java ${DP}/Tower.java ${DP}/Queen.java ${DP}/Bishop.java ${DP}/Pawn.java ${DP}/Knight.java ${DP}/King.java ${DP}/SuperPawn.java ${DP}/Advisor_syn.java  --no-lib

cd ${DP}
