package project12.tictactoe;

import javax.swing.*;
import java.awt.*;

public class TicTacToeRunner extends JFrame {
    private TicTacToeEngine engine;
    private JButton[][] buttons = new JButton[3][3];

    public TicTacToeRunner() {
        engine = new TicTacToeEngine();

        // 1. Show Instructions first
        showInstructions();

        // 2. Setup the Visual Window
        setTitle("Tic Tac Toe Simulator");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3, 5, 5)); // 3x3 grid with gaps

        initializeGrid();
        setVisible(true);
    }

    private void showInstructions() {
        String msg = "Welcome to Tic Tac Toe!\n\n" +
                "Instructions:\n" +
                "1. Players 'X' and 'O' take turns.\n" +
                "2. Click any empty square to place your mark.\n" +
                "3. Get 3 in a row (horizontal, vertical, or diagonal) to win!\n" +
                "4. If the grid fills up with no winner, it's a draw.";
        JOptionPane.showMessageDialog(this, msg, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }

    private void initializeGrid() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c] = new JButton(" ");
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[r][c].setFocusPainted(false);

                final int row = r;
                final int col = c;

                // When button is clicked, it talks to the Engine
                buttons[r][c].addActionListener(e -> handleMove(row, col));

                add(buttons[r][c]);
            }
        }
    }

    private void handleMove(int r, int c) {
        char playerBeforeMove = engine.getCurrentPlayer();
        String result = engine.processTurn(r, c);

        if (!result.equals("INVALID")) {
            // Update the button text visually
            buttons[r][c].setText(String.valueOf(playerBeforeMove));

            if (result.equals("WIN")) {
                JOptionPane.showMessageDialog(this, "Congratulations! Player " + playerBeforeMove + " wins!");
                resetGame();
            } else if (result.equals("DRAW")) {
                JOptionPane.showMessageDialog(this, "It's a tie!");
                resetGame();
            }
        }
    }

    private void resetGame() {
        engine.restart();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                buttons[r][c].setText(" ");
            }
        }
    }

    public static void main(String[] args) {
        // Runs the GUI
        SwingUtilities.invokeLater(() -> new TicTacToeRunner());
    }
}


