package com.chess.engine.board;
import com.chess.engine.pieces.Piece;

/**
 * @author Krysten Lawrence
 * @version 1.1
 * @since 13th March 2019
 */
public abstract class Move{
    final Board board;
    final Piece movedPiece;
    final int destinationLocation;
    
    Move(final Board board, final Piece movedPiece, final int destinationLocation){
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationLocation = destinationLocation;        
    }
    
    public static final class AttackingMove extends Move{
        final Piece attackedPiece;
        public AttackingMove(final Board board, final Piece movedPiece, 
                final int destinationLocation, final Piece attackedPiece){
        super(board, movedPiece, destinationLocation);
        this.attackedPiece = attackedPiece;
        }
    } 
    public static final class MajorMove extends Move{
        public MajorMove(final Board board, final Piece movedPiece, 
                final int destinationLocation){
        super(board, movedPiece, destinationLocation);
        }
    } 
}
