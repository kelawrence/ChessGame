package com.chess.engine.board;
/**
 * @author Krysten Lawrence
 * @version 1.0
 * @since 13th March 2019
 * Class for useful static contacts and methods
 */
public class BoardUtils{
    public static final boolean[] FIRST_COLUMN = initColumn(0); 
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGTH_COLUMN = initColumn(7);
    public static final int NUM_TILES = 64;
    public static final int NUM_TILES_PER_ROW = 8;

    private BoardUtils(){
        throw new RuntimeException("Instaces cannot be created of the BoardUtils class.");
    }
    public static boolean isValidTileLocation(final int location){
        return location >= 0 && location < NUM_TILES;
    }
    
    private static boolean[] initColumn(int columnNumber){
        final boolean[] column = new boolean[NUM_TILES];
        do{
            column[columnNumber] = true;
            columnNumber += NUM_TILES_PER_ROW;
        } while(columnNumber < NUM_TILES);
        return column;
    }    
}
