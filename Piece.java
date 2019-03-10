package com.chess.engine.pieces;
import com.chess.engine.Colour;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import java.util.List;
/**
 * @author Krysten Lawrence
 * @version 1.0
 * @since 10th March 2019
 */
public abstract class Piece{
    protected final int piecePosition;
    protected final Colour pieceColour;
    public abstract List<Move> calculateLegalMoves(final Board board);
    
    Piece(final int piecePosition, final Colour pieceColour){
        this.piecePosition = piecePosition;
        this.pieceColour = pieceColour;
    }
    
    public Colour getPieceColour(){
        return this.pieceColour;
    }   

}
