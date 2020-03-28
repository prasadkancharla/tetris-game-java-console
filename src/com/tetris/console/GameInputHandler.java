package com.tetris.console;

import com.tetris.core.io.InputCommand;
import com.tetris.core.io.InputHandler;
import java.util.Scanner;

public class GameInputHandler implements InputHandler {
    private final Scanner in = new Scanner(System.in);

    @Override
    public InputCommand readNextCommand() {
        String command = in.nextLine();
        switch (command) {
            case "a":
                return InputCommand.MOVE_LEFT;
            case "d":
                return InputCommand.MOVE_RIGHT;
            case "w":
                return InputCommand.ROTATE_ANTI_CLOCKWISE;
            case "s":
                return InputCommand.ROTATE_CLOCKWISE;
        }

        // Any other input will be considered as Move Down operation
        return InputCommand.MOVE_DOWN;
    }
}
