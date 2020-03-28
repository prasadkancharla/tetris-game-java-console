package com.tetris.core;

import com.tetris.core.block.Block;
import com.tetris.core.block.Generator;
import com.tetris.core.io.InputCommand;
import com.tetris.core.io.InputHandler;
import com.tetris.core.io.OutputHandler;

import java.util.Random;

public class Game {
    private static final int MIN_COLUMNS = 10;
    private static final int MIN_ROWS = 10;
    private static final Random rand = new Random();

    private final Board board;
    private final Generator blockGenerator = new Generator();
    private final InputHandler in;
    private final OutputHandler out;
    private Block currentBlock;
    private boolean isGameOver = false;

    public Game(int rows, int columns, InputHandler in, OutputHandler out) throws InvalidGameException {
        if (!isValid(rows, columns)) {
            throw new InvalidGameException("Invalid Game: Rows should be at least " + MIN_ROWS +
                    " and Columns should be at least " +  MIN_COLUMNS);
        }
        this.board = new Board(rows, columns);
        this.in = in;
        this.out = out;
    }

    public void start() {
        try {
            generateNextBlock();

            while(!isGameOver) {
                executeCommand(in.readNextCommand());
            }

            out.print("--GAME OVER--");
        } catch (Exception e) {
            out.print(e.getMessage());
        }
    }

    private boolean isValid(int rows, int columns) {
        return rows >= MIN_ROWS && columns >= MIN_COLUMNS;
    }

    private void executeCommand(InputCommand command) throws Exception {
        Block ghostBlock = (Block) currentBlock.clone();

        switch (command) {
            case MOVE_LEFT:
                ghostBlock.moveLeft();
                break;
            case MOVE_RIGHT:
                ghostBlock.moveRight();
                break;
            case ROTATE_ANTI_CLOCKWISE:
                ghostBlock.rotateAntiClockwise();
                break;
            case ROTATE_CLOCKWISE:
                ghostBlock.rotateClockwise();
                break;
            case MOVE_DOWN:
                ghostBlock.moveDown();
                break;
        }

        if (board.canPlaceBlockOver(ghostBlock)) {
            currentBlock = ghostBlock;

            if (cannotMakeFurtherValidMove(currentBlock)){
                fixCurrentBlockOnBoard();
                generateNextBlock();
            } else {
                refreshView();
            }
        }
    }

    private void refreshView() {
        out.print(board.getStateWithBlockOver(currentBlock));
    }

    private void generateNextBlock() throws Exception{
        currentBlock = blockGenerator.getNextBlock();
        currentBlock.setPos(0, rand.nextInt(board.getWidth() - currentBlock.getWidth()));
        refreshView();

        if (!board.canPlaceBlockOver(currentBlock) || cannotMakeFurtherValidMove(currentBlock)) {
            gameOver();
        }
    }

    private void gameOver() {
        isGameOver = true;
    }

    private void fixCurrentBlockOnBoard() {
        board.fixBlock(currentBlock);
    }

    private boolean cannotMakeFurtherValidMove(Block block) throws CloneNotSupportedException {
        Block ghostBlock = (Block) block.clone();

        // Assuming that every move takes user at least one step down and checking only
        // for this move to check if game can continue.
        ghostBlock.moveDown();
        return !board.canPlaceBlockOver(ghostBlock);
    }
}


