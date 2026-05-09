package project12_tictactoe;

public class TicTacToeModel {
    private final int SIZE = 3;
    private char[][] board;

    public TicTacToeModel() {
        board = new char[SIZE][SIZE];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) board[i][j] = ' ';
        }
    }

    public boolean makeMove(int r, int c, char player) {
        if (r >= 0 && r < SIZE && c >= 0 && c < SIZE && board[r][c] == ' ') {
            board[r][c] = player;
            return true;
        }
        return false;
    }

    public boolean checkWin(char p) {
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) return true; // Rows
            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) return true; // Cols
        }
        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) return true; // Diag 1
        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) return true; // Diag 2
        return false;
    }

    public boolean isFull() {
        for (char[] row : board) {
            for (char cell : row) if (cell == ' ') return false;
        }
        return true;
    }
}
