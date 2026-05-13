package com.matharsa.gui.security.navigation_popup;

import javax.swing.JFrame;

public class GameLauncher {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new Snake_GUI());
        frame.setTitle("Project 10: Snake Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}

