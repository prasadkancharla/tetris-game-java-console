package com.tetris.core.block;

import java.util.Random;

public class Generator {
    private final Block[] blocks = new Block[] {

            // ****
            new Block(new int[][]{
                    {1,1,1,1}
            }),

            // *
            // *
            // **
            new Block(new int[][]{
                    {1,0},
                    {1,0,},
                    {1,1,}
            }),

            //  *
            //  *
            // **
            new Block(new int[][]{
                    {0,1},
                    {0,1},
                    {1,1}
            }),

            //  *
            // **
            // *
            new Block(new int[][]{
                    {0,1},
                    {1,1},
                    {1,0}
            }),

            // **
            // **
            new Block(new int[][]{
                    {1,1},
                    {1,1}
            })
    };
    private final static Random rand = new Random();

    public Block getNextBlock() throws Exception {
        if (blocks.length == 0) {
            throw new Exception("No blocks defined");
        }
        return blocks[rand.nextInt(blocks.length)];
    }
}
