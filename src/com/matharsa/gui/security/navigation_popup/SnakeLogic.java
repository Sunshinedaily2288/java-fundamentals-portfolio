package com.matharsa.gui.security.navigation_popup;

import java.util.Random;

public class SnakeLogic {
    public final int SCREEN_WIDTH = 600;
    public final int SCREEN_HEIGHT = 600;
    public final int UNIT_SIZE = 25;
    public final int[] x = new int[(SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE)];
    public final int[] y = new int[(SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE)];

    public int bodyParts = 6;
    public int applesEaten = 0;
    public int appleX, appleY;
    public char direction = 'R';
    public boolean running = false;
    public boolean gameStarted = false;

    public void newApple() {
        Random random = new Random();
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move() {
        for(int i = bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction) {
            case 'U' -> y[0] -= UNIT_SIZE;
            case 'D' -> y[0] += UNIT_SIZE;
            case 'L' -> x[0] -= UNIT_SIZE;
            case 'R' -> x[0] += UNIT_SIZE;
        }
    }
}

