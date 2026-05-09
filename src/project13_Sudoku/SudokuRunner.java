package project13_Sudoku;

import javax.swing.*;
import java.awt.*;

public class SudokuRunner extends JFrame {
    private SudokuEngine engine = new SudokuEngine();
    private JButton[][] buttons = new JButton[4][4];
    private JLabel timerLabel = new JLabel("Time: 00:00");
    private Timer timer;
    private int secondsElapsed = 0;

    public SudokuRunner() {
        setTitle("4x4 Sudoku with Timer");
        setSize(400, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Timer Logic
        timer = new Timer(1000, e -> {
            secondsElapsed++;
            int mins = secondsElapsed / 60, secs = secondsElapsed % 60;
            timerLabel.setText(String.format("Time: %02d:%02d", mins, secs));
        });
        timer.start();

        JPanel topPanel = new JPanel();
        topPanel.add(timerLabel);
        add(topPanel, BorderLayout.NORTH);

        // Grid
        JPanel gridPanel = new JPanel(new GridLayout(4, 4));
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                buttons[r][c] = new JButton("");
                buttons[r][c].setFont(new Font("Arial", Font.BOLD, 24));
                if ((r < 2 && c < 2) || (r >= 2 && c >= 2)) buttons[r][c].setBackground(Color.LIGHT_GRAY);
                final int row = r, col = c;
                buttons[r][c].addActionListener(e -> handleMove(row, col));
                gridPanel.add(buttons[r][c]);
            }
        }
        add(gridPanel, BorderLayout.CENTER);

        // Buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton hintBtn = new JButton("Hint");
        hintBtn.addActionListener(e -> {
            int[] hint = engine.requestHint();
            if (hint != null) JOptionPane.showMessageDialog(this, "Try " + hint[2] + " at (" + hint[0] + "," + hint[1] + ")");
        });

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(e -> resetGame());

        bottomPanel.add(hintBtn);
        bottomPanel.add(resetBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void handleMove(int r, int c) {
        String input = JOptionPane.showInputDialog(this, "Enter 1-4:");
        if (input == null) return;
        try {
            int num = Integer.parseInt(input);
            String result = engine.placeNumber(r, c, num);
            if (result.equals("SUCCESS") || result.equals("COMPLETE")) {
                buttons[r][c].setText(String.valueOf(num));
                if (result.equals("COMPLETE")) {
                    timer.stop();
                    JOptionPane.showMessageDialog(this, "Solved in " + timerLabel.getText() + "!");
                }
            } else JOptionPane.showMessageDialog(this, "Move Error: " + result);
        } catch (Exception e) { JOptionPane.showMessageDialog(this, "Invalid entry!"); }
    }

    private void resetGame() {
        engine.reset();
        secondsElapsed = 0;
        timerLabel.setText("Time: 00:00");
        timer.restart();
        for (int r = 0; r < 4; r++) for (int c = 0; c < 4; c++) buttons[r][c].setText("");
    }

    public static void main(String[] args) { new SudokuRunner(); }
}



