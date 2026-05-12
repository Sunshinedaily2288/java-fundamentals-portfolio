package project08.number.guessing.game;

import javax.swing.JOptionPane;

public class GuessingGame_OptionPane_GUI {
    private GuessingGameLogic engine = new GuessingGameLogic();

    public void start() {
        boolean won = false;
        JOptionPane.showMessageDialog(null, "I'm thinking of a number between 1 and 100!");

        while (!won) {
            String input = JOptionPane.showInputDialog(null, "Enter your guess:", "Guessing Game", JOptionPane.QUESTION_MESSAGE);

            if (input == null) return; // Exit if user clicks Cancel

            try {
                int guess = Integer.parseInt(input);
                String result = engine.processGuess(guess);

                if (result.equals("CORRECT")) {
                    JOptionPane.showMessageDialog(null, "🎉 Success! You found it in " + engine.getAttempts() + " tries!");
                    won = true;
                } else {
                    JOptionPane.showMessageDialog(null, result);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number!");
            }
        }
    }
}

