package com.chess.engine.pieces;
import com.chess.engine.Colour;
import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Krysten Lawrence
 * @version 1.1
 * @since 13th March 2019
 * Class to create the Bishop and to specify the legal moves for the Bishop Piece
 */
public class Bishop extends Piece{
    private final static int[] POTENTIAL_MOVE_VECTOR_LOCATIONS = {-9, -7, 7, 9};

    public Bishop(int piecePosition, Colour pieceColour){
        super(piecePosition, pieceColour);
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board){
        final List<Move> legalMoves = new ArrayList<>();
        for(final int potentialMove : POTENTIAL_MOVE_VECTOR_LOCATIONS){
            int potentialDestinationLocation = this.piecePosition;
            while(BoardUtils.isValidTileLocation(potentialDestinationLocation)){
                potentialDestinationLocation += potentialMove;
                if(BoardUtils.isValidTileLocation(piecePosition)){
                final Tile potentialDestinationTile = board.getTile(potentialDestinationLocation);
                if(!potentialDestinationTile.hasPiece()){
                    legalMoves.add(new Move.MajorMove(board, this, potentialDestinationLocation));
                }
                else{
                    final Piece occupyingPiece = potentialDestinationTile.getPiece();
                    final Colour pieceColour = occupyingPiece.getPieceColour();
                    if(this.pieceColour != pieceColour){
                        legalMoves.add(new Move.AttackingMove(board, this, potentialDestinationLocation, occupyingPiece));
                    }
                    break;
                    }
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    }
}
