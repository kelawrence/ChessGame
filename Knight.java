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
 * Class to create the Knight and to specify the legal moves for the Knight Piece
 */
public class Knight extends Piece{
    private final static int[] POTENTIAL_MOVE_LOCATION = {-17, -15, -10, -6, 6, 10, 15, 17};
    
    public Knight(int piecePosition, Colour pieceColour){
        super(piecePosition, pieceColour);
    }
    
    @Override
    public Collection<Move> calculateLegalMoves(final Board board){
        final List<Move> legalMoves = new ArrayList<>();
        for(final int potentialMove : POTENTIAL_MOVE_LOCATION){
            final int potentialDestinationLocation = this.piecePosition + potentialMove;
            if(BoardUtils.isValidTileLocation(potentialDestinationLocation)){
                if(isFirstColumnExlclusion(this.piecePosition, potentialMove)
                        || isSecondColumnExlclusion(this.piecePosition, potentialMove)
                        || isSeventhColumnExlclusion(this.piecePosition, potentialMove)
                        || isEigthColumnExlclusion(this.piecePosition, potentialMove)){
                    continue;
                }
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
                }
            }
        }
        return ImmutableList.copyOf(legalMoves);
    } 
    
    private static boolean isFirstColumnExlclusion(final int currentLocation, final int potentialOffset){
        return BoardUtils.FIRST_COLUMN[currentLocation] && ((potentialOffset == -17 || potentialOffset == -10
                || potentialOffset == 6 || potentialOffset == 15));
    }    
    private static boolean isSecondColumnExlclusion(final int currentLocation, final int potentialOffset){
        return BoardUtils.SECOND_COLUMN[currentLocation] && ((potentialOffset == -10 || potentialOffset == 6));                  
    }
    private static boolean isSeventhColumnExlclusion(final int currentLocation, final int potentialOffset){
        return BoardUtils.SEVENTH_COLUMN[currentLocation] && ((potentialOffset == 10 || potentialOffset == -6));                  
    }
    private static boolean isEigthColumnExlclusion(final int currentLocation, final int potentialOffset){
        return BoardUtils.EIGTH_COLUMN[currentLocation] && ((potentialOffset == 17 || potentialOffset == 10
                || potentialOffset == -6 || potentialOffset == -15));
    }
 
 }
