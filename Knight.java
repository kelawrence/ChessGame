package com.chess.engine.pieces;
import com.chess.engine.Colour;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Krysten Lawrence
 * @version 1.0
 * @since 10th March 2019
 */
public class Knight extends Piece{
    private final static int[] POTENTIAL_MOVE_LOCATION = {-17, -15, -10, -6, 6, 10, 15, 17};
    
    public Knight(int piecePosition, Colour pieceColour){
        super(piecePosition, pieceColour);
    }
    
    @Override
    public List<Move> calculateLegalMoves(Board board){
        int potentialDestinationLocation;
        final List<Move> legalMoves = new ArrayList<>();
        for(final int potentialMove : POTENTIAL_MOVE_LOCATION){
            potentialDestinationLocation = this.piecePosition + potentialMove;
            if(true/*if valid tile location*/){
                final Tile potentialDestinationTile = board.getTile(potentialDestinationLocation);
                if(!potentialDestinationTile.hasPiece()){
                    legalMoves.add(new Move());
                }
                else{
                    final Piece occupyingPiece = potentialDestinationTile.getPiece();
                    final Colour pieceColour = occupyingPiece.getPieceColour();
                    if(this.pieceColour != pieceColour){
                        legalMoves.add(new Move());
                    }
                }
            }
            
        }
        return ImmutableList.copyOf(legalMoves);
    }
    
}
