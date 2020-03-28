package com.tetris.core;

import com.tetris.core.block.Block;
import java.util.Arrays;

public class Board {
    private final int columns;
    private final int rows;
    private final int[][] plane;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        plane = new int[rows][columns];
    }

    public boolean canPlaceBlockOver(Block block) {
        int posX = block.getPosX();
        int posY = block.getPosY();

        // checking
        // if the block collides with the plane non-empty cells
        // & the block is not crossing the plane boundaries
        // based on its relative position on the plane
        for (int i = posX; i < posX + block.getHeight(); i++) {
            for (int j = posY; j < posY + block.getWidth(); j++){

                if (block.isCellFilledAt(i - posX, j - posY) &&
                        (i < 0 || i >= rows || j < 0 || j >= columns || plane[i][j] == 1)
                ) {
                    return false;
                }
            }
        }

        return true;
    }

    public void fixBlock(Block block) {
        fillPlaneWithBlock(plane, block);
    }

    public int[][] getStateWithBlockOver(Block block) {
        int[][] state = Arrays.stream(plane).map(int[]::clone).toArray(int[][]::new); // deep copying
        fillPlaneWithBlock(state, block);
        return state;
    }

    private void fillPlaneWithBlock(int[][] plane, Block block) {
        int posX = block.getPosX();
        int posY = block.getPosY();

        // filling plane with block coordinates starting from its position on the plane
        for (int i = posX; i < posX + block.getHeight(); i++) {
            for (int j = posY; j < posY + block.getWidth(); j++){
                if (block.isCellFilledAt(i - posX, j - posY)) {
                    plane[i][j] = 1;
                }
            }
        }
    }

    public int getWidth() {
        return columns;
    }
}
