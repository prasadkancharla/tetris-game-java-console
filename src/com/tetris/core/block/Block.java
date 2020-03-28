package com.tetris.core.block;

public class Block implements Cloneable {
    private int[][] data;
    private int posX = 0;
    private int posY = 0;

    public Block(int[][] data) {
        this.data = data;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int x) {
        posX = x;
    }

    public void setPosY(int y) {
        posY = y;
    }

    public void setPos(int x, int y) {
        setPosX(x);
        setPosY(y);
    }

    public int getHeight() {
        return data.length;
    }

    public int getWidth() {
        return data.length > 0 ? data[0].length: 0;
    }

    public boolean isCellFilledAt(int x, int y) {
        if (x < 0 || x >= getHeight() || y < 0 || y >= getWidth()) {
            return false;
        }

        return data[x][y] > 0;
    }

    public void moveLeft() {
        posY -= 1;
        moveDown();
    }

    public void moveRight() {
        posY += 1;
        moveDown();
    }

    public void moveDown() {
        posX += 1;
    }

    public void rotateAntiClockwise() {
        int n = getHeight();
        int m = getWidth();
        int[][] newData = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newData[i][j] = data[j][m - i - 1];
            }
        }
        data = newData;
        moveDown();
    }

    public void rotateClockwise() {
        int n = getHeight();
        int m = getWidth();
        int[][] newData = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newData[j][n - i - 1] = data[i][j];
            }
        }
        data = newData;
        moveDown();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Block cloned = (Block)super.clone();
        cloned.data = data.clone();
        cloned.setPos(posX, posY);

        return cloned;
    }
}
