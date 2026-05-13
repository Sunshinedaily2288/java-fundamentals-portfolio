package com.matharsa.algorithms.microservices.statistical_loops;

import javax.swing.JOptionPane;

public class StatsGUI {
    private StatsEngine engine = new StatsEngine();

    public void display() {
        // 1. Ask HOW MANY numbers the user wants to enter
        String countInput = JOptionPane.showInputDialog(null,
                "How many numbers would you like to analyze?",
                "Flexible Stats Tool", JOptionPane.QUESTION_MESSAGE);

        if (countInput == null || countInput.isEmpty()) return;
        int count = Integer.parseInt(countInput);

        // 2. Create the array with that specific size
        double[] numbers = new double[count];

        // 3. Loop based on the user's count
        for (int i = 0; i < count; i++) {
            String input = JOptionPane.showInputDialog(null,
                    "Enter number " + (i + 1) + " of " + count + ":",
                    "Input Data", JOptionPane.QUESTION_MESSAGE);

            if (input == null) return;
            numbers[i] = Double.parseDouble(input);
        }

        // 4. Calculations (The engine stays exactly the same!)
        double avg = engine.getAverage(numbers);
        double max = engine.getMax(numbers);
        double min = engine.getMin(numbers);

        String result = String.format("Results for %d numbers:\nAverage: %.2f\nMax: %.2f\nMin: %.2f",
                count, avg, max, min);

        JOptionPane.showMessageDialog(null, result, "Statistics Summary", JOptionPane.INFORMATION_MESSAGE);
    }
}

