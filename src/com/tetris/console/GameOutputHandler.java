package com.tetris.console;

import com.tetris.core.io.OutputHandler;

public class GameOutputHandler implements OutputHandler {

    @Override
    public void print(int[][] plane) {
        clearConsole();

        for (int[] row: plane) {
            System.out.print("# ");
            for (int cell : row) {
                System.out.print(cell > 0 ? "* ": "  ");
            }
            System.out.println("# ");
        }
        if (plane.length > 0) {
            System.out.print("# ");
            for (int i = 0; i < plane[0].length; i++) {
                System.out.print("# ");
            }
            System.out.println("# ");
        }

        System.out.println("a (return): move piece left\n" +
                "d (return): move piece right\n" +
                "w (return): rotate piece counter clockwise\n" +
                "s (return): rotate piece clockwise\n" +
                "any other character (return): move piece down\n");
    }

    @Override
    public void print(String message) {
        clearConsole();
        System.out.println(message);
    }

    private static void clearConsole() {
        for (int i = 0; i < 10; ++i) System.out.println();
    }
}
