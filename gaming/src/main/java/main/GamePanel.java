package main;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import object.SuperObject;
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
    public AssetSetter aSetter = new AssetSetter(this);

    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];// 10 objects


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
    // thiết lập vật phẩm trong game
    public void setupGame(){
        aSetter.setObject();
    }

    // khoi tao luong
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    
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
        //tile
        tileM.draw(g2);

        //object
        for(int i =0; i < obj.length; i++){
            if(obj[i] != null){// tranh null pointer exception
                obj[i].draw(g2, this);
            }
        }

        //PLAYER
        player.draw(g2);
        g2.dispose();// ban  hoaan tat
    }
}
