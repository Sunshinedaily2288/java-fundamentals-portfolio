package com.matharsa.gui.security.navigation_popup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Snake_GUI extends JPanel implements ActionListener {
    SnakeLogic logic = new SnakeLogic();
    Timer timer;

    public Snake_GUI() {
        this.setPreferredSize(new Dimension(logic.SCREEN_WIDTH, logic.SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        logic.newApple();
        timer = new Timer(80, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!logic.gameStarted) {
            drawInstructions(g);
        } else if (logic.running) {
            drawGame(g);
        } else {
            drawGameOver(g);
        }
    }

    public void drawInstructions(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced", Font.BOLD, 30));
        g.drawString("SNAKE ADVENTURE", 160, 150);
        g.setFont(new Font("Monospaced", Font.PLAIN, 18));
        g.drawString("Use ARROWS to Move", 200, 250);
        g.drawString("Eat RED APPLES to Grow", 185, 300);
        g.setColor(Color.green);
        g.drawString("Press any Arrow Key to Start", 145, 450);
    }

    public void drawGame(Graphics g) {
        g.setColor(Color.red);
        g.fillOval(logic.appleX, logic.appleY, logic.UNIT_SIZE, logic.UNIT_SIZE);
        for(int i = 0; i < logic.bodyParts; i++) {
            g.setColor(i == 0 ? Color.green : new Color(45, 180, 0));
            g.fillRect(logic.x[i], logic.y[i], logic.UNIT_SIZE, logic.UNIT_SIZE);
        }
    }

    public void drawGameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Monospaced", Font.BOLD, 50));
        g.drawString("GAME OVER", 165, 280);
        g.setColor(Color.white);
        g.setFont(new Font("Monospaced", Font.PLAIN, 20));
        g.drawString("Final Score: " + logic.applesEaten, 220, 330);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (logic.running) {
            logic.move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public void checkApple() {
        if((logic.x[0] == logic.appleX) && (logic.y[0] == logic.appleY)) {
            logic.bodyParts++;
            logic.applesEaten++;
            logic.newApple();
        }
    }

    public void checkCollisions() {
        for(int i = logic.bodyParts; i > 0; i--) {
            if((logic.x[0] == logic.x[i]) && (logic.y[0] == logic.y[i])) logic.running = false;
        }
        if(logic.x[0] < 0 || logic.x[0] >= logic.SCREEN_WIDTH || logic.y[0] < 0 || logic.y[0] >= logic.SCREEN_HEIGHT) {
            logic.running = false;
        }
        if(!logic.running) timer.stop();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (!logic.gameStarted) {
                logic.gameStarted = true;
                logic.running = true;
            }
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT  -> { if(logic.direction != 'R') logic.direction = 'L'; }
                case KeyEvent.VK_RIGHT -> { if(logic.direction != 'L') logic.direction = 'R'; }
                case KeyEvent.VK_UP    -> { if(logic.direction != 'D') logic.direction = 'U'; }
                case KeyEvent.VK_DOWN  -> { if(logic.direction != 'U') logic.direction = 'D'; }
            }
        }
    }
}

