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
 * @version 1.0
 * @since 17th March 2019
 * Class to create the Queen and to specify the legal moves for the Queen Piece
 */
public class Queen extends Piece{
    private final static int[] POTENTIAL_MOVE_VECTOR_LOCATIONS = {-9, -8, -7, -1, 1, 7, 8, 9};

    public Queen(int piecePosition, Colour pieceColour){
        super(piecePosition, pieceColour);
    }

   @Override
    public Collection<Move> calculateLegalMoves(final Board board){
        final List<Move> legalMoves = new ArrayList<>();
        for(final int potentialMove : POTENTIAL_MOVE_VECTOR_LOCATIONS){
            int potentialDestinationLocation = this.piecePosition;
            while(BoardUtils.isValidTileLocation(potentialDestinationLocation)){                
                if(isFirstColumnExclusion(potentialDestinationLocation, potentialMove)
                        || isEigthColumnExclusion(potentialDestinationLocation, potentialMove)){
                    break;
                }
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
    private static boolean isFirstColumnExclusion(final int currentLocation, final int potentialOffset){
        return BoardUtils.FIRST_COLUMN[currentLocation] && (potentialOffset == -9 || potentialOffset == 7 || potentialOffset == -1);
    }
    private static boolean isEigthColumnExclusion(final int currentLocation, final int potentialOffset){
        return BoardUtils.EIGTH_COLUMN[currentLocation] && (potentialOffset == 9 || potentialOffset == -7 || potentialOffset == 1);
    }
   
}
