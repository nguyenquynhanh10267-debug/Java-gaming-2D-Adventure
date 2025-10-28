package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        //ten game
        window.setTitle("2D Adventure");
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();// dong goi JPanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.setupGame();
        gamePanel.startGameThread();
        
    }
}
