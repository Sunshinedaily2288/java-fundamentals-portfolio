package com.matharsa.algorithms.microservices.statistical_loops;

public class StatsEngine {

    // Calculates the average of the array
    public double getAverage(double[] numbers) {
        double sum = 0;
        for (double num : numbers) {
            sum += num;
        }
        return sum / numbers.length;
    }

    // Finds the highest number
    public double getMax(double[] numbers) {
        double max = numbers[0];
        for (double num : numbers) {
            if (num > max) max = num;
        }
        return max;
    }

    // Finds the lowest number
    public double getMin(double[] numbers) {
        double min = numbers[0];
        for (double num : numbers) {
            if (num < min) min = num;
        }
        return min;
    }
}

