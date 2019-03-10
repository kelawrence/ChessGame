package com.chess.engine.board; 
import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Krysten Lawrence
 * @version 1.0
 * @since 10th March 2019
 * This class is used to define the tiles of the chess board and also contains 
 * classes that state whether or not there is a piece on the tile.
 * It also creates the board and makes it so that once created, cannot be 
 * changed.
 */
public abstract class Tile
{
    protected final int tileLocation;
    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllEmptyTiles();
    public abstract boolean hasPiece();
    public abstract Piece getPiece();
    
    private Tile(int tileLocation){
        this.tileLocation = tileLocation;
    }
    
    private static Map<Integer, EmptyTile> createAllEmptyTiles(){
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for(int i=0; i<64; i++){
            emptyTileMap.put(i,new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap);
    }
    
    public static Tile createTile(final int tileLocation, final Piece piece){
        return piece != null ? new OccupiedTile(tileLocation, piece) : EMPTY_TILES_CACHE.get(tileLocation);
    }
    
    public static final class EmptyTile extends Tile{
        EmptyTile(final int location){
            super(location);
        }
        @Override
        public boolean hasPiece(){
            return false;
        }
        @Override
        public Piece getPiece(){
            return null;
        }
    }
    
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        private OccupiedTile(int location, Piece pieceOnTile){
            super(location);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean hasPiece(){
            return true;
        }
        @Override
        public Piece getPiece(){
            return this.pieceOnTile;
        }
    }
}
