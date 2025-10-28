package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics2D;

import enity.Player;

import tile.TileManager;

import java.awt.Graphics;
public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;//16*16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;//48*48 tile
        // hang
    public final int maxScreenCol = 16;
        // cot
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;//768 pixel
    public final int screenHeight = tileSize * maxScreenRow;//576 pixel

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    // Thread: bắt đầu và dừng khi 1 luồng bắt đầu
    // giữ cho chương trình chạy đến khi dừng 

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);// kiem tra va cham
    public Player player = new Player(this, keyH);

    // // set player's default position
    // int playerX = 100;
    // int playerY = 100;
    // // toc do di chuyen cua player la 4 pixels
    // int playerSpeed = 4;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        // thêm keylistener vào gamepanel
        this.addKeyListener(keyH);
        // cho phép JPanel nhận sự kiện từ bàn phím
        this.setFocusable(true);
    }
    // khoi tao luong
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    // sleep method
    // public void run(){
    //     double drawInterval = 1000000000/FPS; // nanosecond/ 0.0166 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval;// thoi gian hien tai + khoang thoi gian de ve tiep theo
    //     // loop begin, program update and draw
    //     //game loop
    //     while(gameThread != null){
    //         // kiem tra thoi gian hien tai
           

    //         //System.out.println("The game loop is running");
    //         // 1 update information such as character position
    //         update();
    //         // 2 draw: draw screen with the updated information
    //         repaint();
    //         // bao nhieu time cho lan ve tiep theo
    //         try {
    //             double remainingTime = nextDrawTime - System.nanoTime();
    //             remainingTime = remainingTime/1000000;// chuyen nanosecond sang milisecond
    //             if(remainingTime < 0){
    //                 remainingTime = 0;
    //             }
    //             Thread.sleep((long) remainingTime);
    //             // thoi gian ngu ket thuc, luong duoc danh thuc
    //             nextDrawTime += drawInterval;
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
    // method delta
    public void run(){
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
       player.update();
    }
    // method draw everything tren java
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //draw here
        tileM.draw(g2);

        player.draw(g2);
        g2.dispose();// ban  hoaan tat
    }
}
