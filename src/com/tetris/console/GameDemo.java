package com.tetris.console;

import com.tetris.core.Game;
import com.tetris.core.InvalidGameException;

public class GameDemo {

    public static void main(String[] args) {
        try {
            Game game = new Game(15, 15, new GameInputHandler(), new GameOutputHandler());
            game.start();
        } catch (Exception | InvalidGameException e) {
            System.out.println(e.getMessage());
        }
    }
}
