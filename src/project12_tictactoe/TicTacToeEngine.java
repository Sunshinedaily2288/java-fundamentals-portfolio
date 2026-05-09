package project12_tictactoe;

public class TicTacToeEngine {
    private TicTacToeModel model;
    private char currentPlayer;

    public TicTacToeEngine() {
        this.model = new TicTacToeModel();
        this.currentPlayer = 'X';
    }

    public String processTurn(int r, int c) {
        if (model.makeMove(r, c, currentPlayer)) {
            if (model.checkWin(currentPlayer)) return "WIN";
            if (model.isFull()) return "DRAW";

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            return "SUCCESS";
        }
        return "INVALID";
    }

    public char getCurrentPlayer() { return currentPlayer; }
    public void restart() {
        model.resetBoard();
        currentPlayer = 'X';
    }
}

