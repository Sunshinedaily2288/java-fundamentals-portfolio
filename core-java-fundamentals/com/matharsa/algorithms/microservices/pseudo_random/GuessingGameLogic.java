package com.matharsa.algorithms.microservices.pseudo_random;

import java.util.Random;

public class GuessingGameLogic {
    private int secretNumber;
    private int attempts;

    public GuessingGameLogic() {
        Random rand = new Random();
        this.secretNumber = rand.nextInt(100) + 1;
        this.attempts = 0;
    }

    // Renamed this to match your GUI's call
    public String processGuess(int guess) {
        attempts++;
        if (guess < secretNumber) return "Too Low! Try again.";
        if (guess > secretNumber) return "Too High! Try again.";
        return "CORRECT";
    }

    public int getAttempts() {
        return attempts;
    }
}

