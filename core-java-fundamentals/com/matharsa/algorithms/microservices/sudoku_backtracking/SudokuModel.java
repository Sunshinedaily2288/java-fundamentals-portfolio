package com.matharsa.algorithms.microservices.sudoku_backtracking;

public class SudokuModel {
    private final int SIZE = 4;
    private int[][] board = new int[SIZE][SIZE];

    public void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) board[i][j] = 0;
        }
    }

    public boolean isValidMove(int row, int col, int num) {
        // Row and Column check
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) return false;
        }
        // 2x2 Sub-grid check
        int boxRow = (row / 2) * 2, boxCol = (col / 2) * 2;
        for (int r = boxRow; r < boxRow + 2; r++) {
            for (int c = boxCol; c < boxCol + 2; c++) {
                if (board[r][c] == num) return false;
            }
        }
        return true;
    }

    public int[] findHint() {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (board[r][c] == 0) {
                    for (int num = 1; num <= 4; num++) {
                        if (isValidMove(r, c, num)) return new int[]{r, c, num};
                    }
                }
            }
        }
        return null;
    }

    public void setCell(int r, int c, int v) { board[r][c] = v; }
    public int getCell(int r, int c) { return board[r][c]; }
    public boolean isFull() {
        for (int[] row : board) for (int val : row) if (val == 0) return false;
        return true;
    }
}


