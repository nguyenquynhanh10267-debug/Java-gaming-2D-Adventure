package enity;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.KeyHandler;
//import java.awt.Color;

import main.GamePanel;

public class Player extends Enity{
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth /2 - (gp.tileSize /2);
        screenY = gp.screenHeight /2 - (gp.tileSize/2); // vi tri player luon o giua man hinh
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        // vi tri khoi dau cua player trong the gioi
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            // upload image
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/oldman_right_2.png"));

        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void update(){ // call 60 times per second
         if(keyH.upPressed == true || keyH.downPressed == true ||
                     keyH.leftPressed == true || keyH.rightPressed == true){
            // neu co 1 phim duoc nhan thi moi di chuyen
            // thay doi vi tri player theo key pressed
            if(keyH.upPressed == true){
            // nguoi choi di chuyen len tren
                direction = "up";
                worldY -= speed;
            
            } else if(keyH.downPressed == true){
                // nguoi choi di chuyen xuong duoi
                direction = "down";
                worldY += speed;
            
            } else if(keyH.leftPressed == true){
                // nguoi choi di chuyen sang trai
                direction = "left";
                worldX -= speed;
            
            } else if(keyH.rightPressed == true){
                // nguoi choi di chuyen sang phai
                direction = "right";
                worldX += speed;
            
            }
            // them bo dem de thay doi sprite
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum ==1){
                    spriteNum =2;
                } else if (spriteNum ==2){
                    spriteNum =1;
                }
            spriteCounter =0;// reset lai bo dem
            }
        }
        
    }
    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum ==1){
                    image = up1;
                }
                if(spriteNum ==2){
                    image = up2;
                }
        
                break;
            case "down":
                if(spriteNum ==1){
                    image = down1;
                }
                if(spriteNum ==2){
                    image = down2;
                }
                
                break;
            case "left":
                if(spriteNum ==1){
                    image = left1;
                }
                if(spriteNum ==2){
                    image = left2;
                }
                
                break;
            case "right":
                if(spriteNum ==1){
                    image = right1;
                }
                if(spriteNum ==2){
                    image = right2;
                }
                
                break;
        }
        g2.drawImage(image, screenX,screenY, gp.tileSize, gp.tileSize, null);
    }

}
